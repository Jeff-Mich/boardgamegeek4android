package com.boardgamegeek.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.boardgamegeek.R;
import com.boardgamegeek.events.CollectionStatusChangedEvent;
import com.boardgamegeek.io.Adapter;
import com.boardgamegeek.io.BggService;
import com.boardgamegeek.io.BuddyCollectionRequest;
import com.boardgamegeek.model.CollectionItem;
import com.boardgamegeek.model.CollectionResponse;
import com.boardgamegeek.ui.loader.BggLoader;
import com.boardgamegeek.ui.loader.Data;
import com.boardgamegeek.util.ActivityUtils;
import com.boardgamegeek.util.RandomUtils;
import com.boardgamegeek.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import timber.log.Timber;

public class BuddyCollectionFragment extends StickyHeaderListFragment implements
	LoaderManager.LoaderCallbacks<BuddyCollectionFragment.BuddyCollectionData> {
	private static final int BUDDY_GAMES_LOADER_ID = 1;
	private static final String STATE_STATUS_VALUE = "buddy_collection_status_value";
	private static final String STATE_STATUS_LABEL = "buddy_collection_status_entry";

	private BuddyCollectionAdapter adapter;
	private SubMenu subMenu;
	private String buddyName;
	private String statusValue;
	private String statusLabel;
	private String[] statusValues;
	private String[] statusEntries;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		final Intent intent = UIUtils.fragmentArgumentsToIntent(getArguments());
		buddyName = intent.getStringExtra(ActivityUtils.KEY_BUDDY_NAME);

		if (TextUtils.isEmpty(buddyName)) {
			Timber.w("Missing buddy name.");
			return;
		}

		statusEntries = getResources().getStringArray(R.array.pref_sync_status_entries);
		statusValues = getResources().getStringArray(R.array.pref_sync_status_values);

		setHasOptionsMenu(true);
		if (savedInstanceState == null) {
			statusValue = statusValues[0];
			statusLabel = statusEntries[0];
		} else {
			statusValue = savedInstanceState.getString(STATE_STATUS_VALUE);
			statusLabel = savedInstanceState.getString(STATE_STATUS_LABEL);
		}
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		setEmptyText(getString(R.string.empty_buddy_collection));
		reload();
	}

	@Override
	public void onListItemClick(View convertView, int position, long id) {
		super.onListItemClick(convertView, position, id);
		int gameId = (int) convertView.getTag(R.id.id);
		String gameName = (String) convertView.getTag(R.id.game_name);
		ActivityUtils.launchGame(getActivity(), gameId, gameName);
	}

	@Override
	public void onSaveInstanceState(@NonNull Bundle outState) {
		outState.putString(STATE_STATUS_VALUE, statusValue);
		outState.putString(STATE_STATUS_LABEL, statusLabel);
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.buddy_collection, menu);
		MenuItem mi = menu.findItem(R.id.menu_collection_status);
		if (mi != null) {
			subMenu = mi.getSubMenu();
			if (subMenu != null) {
				for (int i = 0; i < statusEntries.length; i++) {
					subMenu.add(1, Menu.FIRST + i, i, statusEntries[i]);
				}
				subMenu.setGroupCheckable(1, true, true);
			}
		}
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		MenuItem mi = menu.findItem(R.id.menu_collection_random_game);
		if (mi != null) {
			if (adapter != null && adapter.getCount() > 0) {
				mi.setVisible(true);
			} else {
				mi.setVisible(false);
			}
		}
		// check the proper submenu item
		if (subMenu != null) {
			for (int i = 0; i < subMenu.size(); i++) {
				MenuItem smi = subMenu.getItem(i);
				if (smi.getTitle().equals(statusLabel)) {
					smi.setChecked(true);
					break;
				}
			}
		}
		super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		String status = "";
		int i = id - Menu.FIRST;
		if (i >= 0 && i < statusValues.length) {
			status = statusValues[i];
		} else if (id == R.id.menu_collection_random_game) {
			final int index = RandomUtils.getRandom().nextInt(adapter.getCount());
			if (index < adapter.getCount()) {
				CollectionItem ci = adapter.getItem(index);
				ActivityUtils.launchGame(getActivity(), ci.gameId, ci.gameName());
				return true;
			}
		}

		if (!TextUtils.isEmpty(status) && !status.equals(statusValue)) {
			statusValue = status;
			statusLabel = statusEntries[i];

			reload();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void reload() {
		EventBus.getDefault().postSticky(new CollectionStatusChangedEvent(statusLabel));
		if (adapter != null) {
			adapter.clear();
		}
		getActivity().supportInvalidateOptionsMenu();
		setListShown(false);
		getLoaderManager().restartLoader(BUDDY_GAMES_LOADER_ID, null, this);
	}

	@Override
	public Loader<BuddyCollectionData> onCreateLoader(int id, Bundle data) {
		return new BuddyGamesLoader(getActivity(), buddyName, statusValue);
	}

	@Override
	public void onLoadFinished(Loader<BuddyCollectionData> loader, BuddyCollectionData data) {
		if (getActivity() == null) {
			return;
		}

		List<CollectionItem> list = new ArrayList<>();
		if (data != null) {
			list = data.list();
		}

		if (adapter == null) {
			adapter = new BuddyCollectionAdapter(getActivity(), list);
			setListAdapter(adapter);
		} else {
			adapter.setCollection(list);
		}
		adapter.notifyDataSetChanged();
		getActivity().supportInvalidateOptionsMenu();

		if (data == null) {
			setEmptyText(getString(R.string.empty_buddy_collection));
		} else if (data.hasError()) {
			setEmptyText(data.getErrorMessage());
		} else {
			if (isResumed()) {
				setListShown(true);
			} else {
				setListShownNoAnimation(true);
			}
			restoreScrollState();
		}
	}

	@Override
	public void onLoaderReset(Loader<BuddyCollectionData> loader) {
	}

	private static class BuddyGamesLoader extends BggLoader<BuddyCollectionData> {
		private final BggService bggService;
		private final String username;
		private final ArrayMap<String, String> options;

		public BuddyGamesLoader(Context context, String username, String status) {
			super(context);
			bggService = Adapter.create();
			this.username = username;
			options = new ArrayMap<>();
			options.put(status, "1");
			options.put(BggService.COLLECTION_QUERY_KEY_BRIEF, "1");
		}

		@Override
		public BuddyCollectionData loadInBackground() {
			BuddyCollectionData collection;
			try {
				CollectionResponse response = new BuddyCollectionRequest(bggService, username, options).execute();
				collection = new BuddyCollectionData(response);
			} catch (Exception e) {
				collection = new BuddyCollectionData(e);
			}
			return collection;
		}
	}

	static class BuddyCollectionData extends Data<CollectionItem> {
		private CollectionResponse mResponse;

		public BuddyCollectionData(CollectionResponse response) {
			mResponse = response;
		}

		public BuddyCollectionData(Exception e) {
			super(e);
		}

		@Override
		public List<CollectionItem> list() {
			if (mResponse == null || mResponse.items == null) {
				return new ArrayList<>();
			}
			return mResponse.items;
		}
	}

	public static class BuddyCollectionAdapter extends ArrayAdapter<CollectionItem> implements StickyListHeadersAdapter {
		private List<CollectionItem> mBuddyCollection;
		private final LayoutInflater mInflater;

		public BuddyCollectionAdapter(Activity activity, List<CollectionItem> collection) {
			super(activity, R.layout.row_text_2, collection);
			mInflater = activity.getLayoutInflater();
			setCollection(collection);
		}

		public void setCollection(List<CollectionItem> games) {
			mBuddyCollection = games;
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return mBuddyCollection.size();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			BuddyGameViewHolder holder;
			if (convertView == null) {
				convertView = mInflater.inflate(R.layout.row_text_2, parent, false);
				holder = new BuddyGameViewHolder(convertView);
				convertView.setTag(holder);
			} else {
				holder = (BuddyGameViewHolder) convertView.getTag();
			}

			CollectionItem game;
			try {
				game = mBuddyCollection.get(position);
			} catch (ArrayIndexOutOfBoundsException e) {
				return convertView;
			}
			if (game != null) {
				holder.title.setText(game.gameName());
				holder.text.setText(String.valueOf(game.gameId));

				convertView.setTag(R.id.id, game.gameId);
				convertView.setTag(R.id.game_name, game.gameName());
			}
			return convertView;
		}

		@Override
		public View getHeaderView(int position, View convertView, ViewGroup parent) {
			HeaderViewHolder holder;
			if (convertView == null) {
				holder = new HeaderViewHolder();
				convertView = mInflater.inflate(R.layout.row_header, parent, false);
				holder.text = (TextView) convertView.findViewById(android.R.id.title);
				convertView.setTag(holder);
			} else {
				holder = (HeaderViewHolder) convertView.getTag();
			}
			holder.text.setText(getHeaderText(position));
			return convertView;
		}

		@Override
		public long getHeaderId(int position) {
			return getHeaderText(position).charAt(0);
		}

		private String getHeaderText(int position) {
			if (position < mBuddyCollection.size()) {
				CollectionItem game = mBuddyCollection.get(position);
				if (game != null) {
					return game.gameSortName().substring(0, 1);
				}
			}
			return "-";
		}

		class BuddyGameViewHolder {
			public final TextView title;
			public final TextView text;

			public BuddyGameViewHolder(View view) {
				title = (TextView) view.findViewById(android.R.id.title);
				text = (TextView) view.findViewById(android.R.id.text1);
			}
		}

		class HeaderViewHolder {
			TextView text;
		}
	}
}
