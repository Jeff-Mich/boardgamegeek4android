package com.boardgamegeek.provider;

import android.net.Uri;
import android.net.Uri.Builder;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.boardgamegeek.util.StringUtils;

public class BggContract {

	public interface SyncColumns {
		String UPDATED = "updated";
	}

	public interface SyncListColumns {
		String UPDATED_LIST = "updated_list";
	}

	interface GamesColumns {
		String GAME_ID = "game_id";
		String GAME_NAME = "game_name";
		String GAME_SORT_NAME = "game_sort_name";
		String YEAR_PUBLISHED = "year_published";
		String IMAGE_URL = "image_url";
		String THUMBNAIL_URL = "thumbnail_url";
		String MIN_PLAYERS = "min_players";
		String MAX_PLAYERS = "max_players";
		String PLAYING_TIME = "playing_time";
		String NUM_PLAYS = "num_of_plays";
		String MINIMUM_AGE = "age";
		String DESCRIPTION = "description";
		String STATS_USERS_RATED = "usersrated";
		String STATS_AVERAGE = "average";
		String STATS_BAYES_AVERAGE = "bayes_average";
		String STATS_STANDARD_DEVIATION = "standard_deviation";
		String STATS_MEDIAN = "median";
		String STATS_NUMBER_OWNED = "number_owned";
		String STATS_NUMBER_TRADING = "number_trading";
		String STATS_NUMBER_WANTING = "number_wanting";
		String STATS_NUMBER_WISHING = "number_wishing";
		String STATS_NUMBER_COMMENTS = "number_commenting";
		String STATS_NUMBER_WEIGHTS = "number_weighting";
		String STATS_AVERAGE_WEIGHT = "average_weight";
		String LAST_VIEWED = "last_viewed";
		String STARRED = "starred";
	}

	interface GameRanksColumns {
		String GAME_RANK_ID = "gamerank_id";
		String GAME_RANK_TYPE = "gamerank_type";
		String GAME_RANK_NAME = "gamerank_name";
		String GAME_RANK_FRIENDLY_NAME = "gamerank_friendly_name";
		String GAME_RANK_VALUE = "gamerank_value";
		String GAME_RANK_BAYES_AVERAGE = "gamerank_bayes_average";
	}

	interface DesignersColumns {
		String DESIGNER_ID = "designer_id";
		String DESIGNER_NAME = "designer_name";
		String DESIGNER_DESCRIPTION = "designer_description";
	}

	interface ArtistsColumns {
		String ARTIST_ID = "artist_id";
		String ARTIST_NAME = "artist_name";
		String ARTIST_DESCRIPTION = "artist_description";
	}

	interface PublishersColumns {
		String PUBLISHER_ID = "publisher_id";
		String PUBLISHER_NAME = "publisher_name";
		String PUBLISHER_DESCRIPTION = "publisher_description";
	}

	interface MechanicsColumns {
		String MECHANIC_ID = "mechanic_id";
		String MECHANIC_NAME = "mechanic_name";
	}

	interface CategoriesColumns {
		String CATEGORY_ID = "category_id";
		String CATEGORY_NAME = "category_name";
	}

	interface GamesExpansionsColumns {
		String EXPANSION_ID = "expansion_id";
		String EXPANSION_NAME = "expansion_name";
		String INBOUND = "inbound";
	}

	interface CollectionColumns {
		String COLLECTION_ID = "collection_id";
		String COLLECTION_NAME = "collection_name";
		String COLLECTION_SORT_NAME = "collection_sort_name";
		String STATUS_OWN = "own";
		String STATUS_PREVIOUSLY_OWNED = "previously_owned";
		String STATUS_FOR_TRADE = "for_trade";
		String STATUS_WANT = "want";
		String STATUS_WANT_TO_PLAY = "want_to_play";
		String STATUS_WANT_TO_BUY = "want_to_buy";
		String STATUS_WISHLIST = "wishlist";
		String STATUS_WISHLIST_PRIORITY = "wishlist_priority";
		String STATUS_PREORDERED = "preordered";
		String COMMENT = "comment";
		String LAST_MODIFIED = "last_modified";
		String PRIVATE_INFO_PRICE_PAID_CURRENCY = "price_paid_currency";
		String PRIVATE_INFO_PRICE_PAID = "price_paid";
		String PRIVATE_INFO_CURRENT_VALUE_CURRENCY = "current_value_currency";
		String PRIVATE_INFO_CURRENT_VALUE = "current_value";
		String PRIVATE_INFO_QUANTITY = "quantity";
		String PRIVATE_INFO_ACQUISITION_DATE = "acquisition_date";
		String PRIVATE_INFO_ACQUIRED_FROM = "acquired_from";
		String PRIVATE_INFO_COMMENT = "private_comment";
	}

	interface BuddiesColumns {
		String BUDDY_ID = "buddy_id";
		String BUDDY_NAME = "buddy_name";
		String BUDDY_FIRSTNAME = "buddy_firtname";
		String BUDDY_LASTNAME = "buddy_lastname";
		String AVATAR_URL = "avatar_url";
		String PLAY_NICKNAME = "play_nickname";
	}

	interface GamePollsColumns {
		String POLL_NAME = "poll_name";
		String POLL_TITLE = "poll_title";
		String POLL_TOTAL_VOTES = "poll_total_votes";
	}

	interface GamePollResultsColumns {
		String POLL_ID = "poll_id";
		String POLL_RESULTS_KEY = "pollresults_key";
		String POLL_RESULTS_PLAYERS = "pollresults_players";
		String POLL_RESULTS_SORT_INDEX = "pollresults_sortindex";
	}

	interface GamePollResultsResultColumns {
		String POLL_RESULTS_ID = "pollresults_id";
		String POLL_RESULTS_RESULT_KEY = "pollresultsresult_key";
		String POLL_RESULTS_RESULT_LEVEL = "pollresultsresult_level";
		String POLL_RESULTS_RESULT_VALUE = "pollresultsresult_value";
		String POLL_RESULTS_RESULT_VOTES = "pollresultsresult_votes";
		String POLL_RESULTS_RESULT_SORT_INDEX = "pollresultsresult_sortindex";
	}

	interface GameColorsColumns {
		String COLOR = "color";
	}

	interface PlaysColumns {
		String PLAY_ID = "play_id";
		String DATE = "date";
		String QUANTITY = "quantity";
		String LENGTH = "length";
		String INCOMPLETE = "incomplete";
		String NO_WIN_STATS = "no_win_stats";
		String LOCATION = "location";
		String COMMENTS = "comments";
		String SYNC_STATUS = "sync_status";
	}

	interface PlayItemsColumns {
		String NAME = "name";
		String OBJECT_ID = "object_id";
	}

	interface PlayPlayersColumns {
		String USER_NAME = "user_name";
		String USER_ID = "user_id";
		String NAME = "name";
		String START_POSITION = "start_position";
		String COLOR = "color";
		String SCORE = "score";
		String NEW = "new";
		String RATING = "rating";
		String WIN = "win";
	}

	public static final String CONTENT_AUTHORITY = "com.boardgamegeek";

	private static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

	private static final String PATH_GAMES = "games";
	private static final String PATH_RANKS = "ranks";
	private static final String PATH_DESIGNERS = "designers";
	private static final String PATH_ARTISTS = "artists";
	private static final String PATH_PUBLISHERS = "publishers";
	private static final String PATH_MECHANICS = "mechanics";
	private static final String PATH_CATEGORIES = "categories";
	private static final String PATH_EXPANSIONS = "expansions";
	private static final String PATH_COLLECTION = "collection";
	private static final String PATH_BUDDIES = "buddies";
	private static final String PATH_POLLS = "polls";
	private static final String PATH_POLL_RESULTS = "results";
	private static final String PATH_POLL_RESULTS_RESULT = "result";
	private static final String PATH_THUMBNAILS = "thumbnails";
	private static final String PATH_COLORS = "colors";
	private static final String PATH_PLAYS = "plays";
	private static final String PATH_ITEMS = "items";
	private static final String PATH_PLAYERS = "players";
	private static final String PATH_LOCATIONS = "locations";

	public static class Thumbnails {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_THUMBNAILS).build();
	}

	public static class Games implements GamesColumns, BaseColumns, SyncColumns, SyncListColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_GAMES).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.game";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.game";

		public static final String DEFAULT_SORT = GamesColumns.GAME_SORT_NAME + " COLLATE NOCASE ASC";

		public static Uri buildGameUri(int gameId) {
			return getUriBuilder(gameId).build();
		}

		public static Uri buildRanksUri(int gameId) {
			return getUriBuilder(gameId, PATH_RANKS).build();
		}

		public static Uri buildRanksUri(int gameId, int rankId) {
			return getUriBuilder(gameId, PATH_RANKS, rankId).build();
		}

		public static Uri buildDesignersUri(int gameId) {
			return getUriBuilder(gameId, PATH_DESIGNERS).build();
		}

		public static Uri buildDesignersUri(int gameId, int designerId) {
			return getUriBuilder(gameId, PATH_DESIGNERS, designerId).build();
		}

		public static Uri buildDesignersUri(long rowId) {
			return getUriBuilder().appendPath(PATH_DESIGNERS).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildArtistsUri(int gameId) {
			return getUriBuilder(gameId, PATH_ARTISTS).build();
		}

		public static Uri buildArtistsUri(int gameId, int artistId) {
			return getUriBuilder(gameId, PATH_ARTISTS, artistId).build();
		}

		public static Uri buildArtistUri(long rowId) {
			return getUriBuilder().appendPath(PATH_ARTISTS).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildPublishersUri(int gameId) {
			return getUriBuilder(gameId, PATH_PUBLISHERS).build();
		}

		public static Uri buildPublishersUri(int gameId, int publisherId) {
			return getUriBuilder(gameId, PATH_PUBLISHERS, publisherId).build();
		}

		public static Uri buildPublisherUri(long rowId) {
			return getUriBuilder().appendPath(PATH_PUBLISHERS).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildMechanicsUri(int gameId) {
			return getUriBuilder(gameId, PATH_MECHANICS).build();
		}

		public static Uri buildMechanicsUri(int gameId, int mechanicId) {
			return getUriBuilder(gameId, PATH_MECHANICS, mechanicId).build();
		}

		public static Uri buildMechanicUri(long rowId) {
			return getUriBuilder().appendPath(PATH_MECHANICS).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildCategoriesUri(int gameId) {
			return getUriBuilder(gameId, PATH_CATEGORIES).build();
		}

		public static Uri buildCategoriesUri(int gameId, int categoryId) {
			return getUriBuilder(gameId, PATH_CATEGORIES, categoryId).build();
		}

		public static Uri buildCategoryUri(long rowId) {
			return getUriBuilder().appendPath(PATH_CATEGORIES).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildExpansionsUri(int gameId) {
			return getUriBuilder(gameId, PATH_EXPANSIONS).build();
		}

		public static Uri buildExpansionsUri(int gameId, int expansionId) {
			return getUriBuilder(gameId, PATH_EXPANSIONS, expansionId).build();
		}

		public static Uri buildExpansionUri(long rowId) {
			return getUriBuilder().appendPath(PATH_EXPANSIONS).appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildPollsUri(int gameId) {
			return getUriBuilder(gameId, PATH_POLLS).build();
		}

		public static Uri buildPollsUri(int gameId, String pollName) {
			return getUriBuilder(gameId, PATH_POLLS).appendPath(pollName).build();
		}

		public static Uri buildPollResultsUri(int gameId, String pollName) {
			return getUriBuilder(gameId, PATH_POLLS).appendPath(pollName).appendPath(PATH_POLL_RESULTS).build();
		}

		public static Uri buildPollResultsUri(int gameId, String pollName, String key) {
			return getUriBuilder(gameId, PATH_POLLS).appendPath(pollName).appendPath(PATH_POLL_RESULTS).appendPath(key)
					.build();
		}

		public static Uri buildPollResultsResultUri(int gameId, String pollName, String key) {
			return getUriBuilder(gameId, PATH_POLLS).appendPath(pollName).appendPath(PATH_POLL_RESULTS).appendPath(key)
					.appendPath(PATH_POLL_RESULTS_RESULT).build();
		}

		public static Uri buildPollResultsResultUri(int gameId, String pollName, String key, String key2) {
			return getUriBuilder(gameId, PATH_POLLS).appendPath(pollName).appendPath(PATH_POLL_RESULTS).appendPath(key)
					.appendPath(PATH_POLL_RESULTS_RESULT).appendPath(key2).build();
		}

		public static Uri buildColorsUri(int gameId) {
			return getUriBuilder(gameId, PATH_COLORS).build();
		}

		public static Uri buildColorsUri(int gameId, String color) {
			return getUriBuilder(gameId, PATH_COLORS).appendPath(color).build();
		}

		private static Builder getUriBuilder() {
			return CONTENT_URI.buildUpon();
		}

		private static Builder getUriBuilder(int gameId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(gameId));
		}

		private static Builder getUriBuilder(int gameId, String path) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(gameId)).appendPath(path);
		}

		private static Builder getUriBuilder(int gameId, String path, int id) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(gameId)).appendPath(path)
					.appendPath(String.valueOf(id));
		}

		public static int getGameId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}

		public static String getPollName(Uri uri) {
			return getPathValue(uri, PATH_POLLS);
		}

		public static String getPollResultsKey(Uri uri) {
			return getPathValue(uri, PATH_POLL_RESULTS);
		}

		public static String getPollResultsResultKey(Uri uri) {
			return getPathValue(uri, PATH_POLL_RESULTS_RESULT);
		}

		public static String getPathValue(Uri uri, String path) {
			if (TextUtils.isEmpty(path)) {
				return "";
			}
			boolean isNextValue = false;
			for (String segment : uri.getPathSegments()) {
				if (isNextValue) {
					return segment;
				}
				if (path.equals(segment)) {
					isNextValue = true;
				}
			}
			return "";
		}
	}

	public static class GameRanks implements GameRanksColumns, GamesColumns, BaseColumns {
		public static final Uri CONTENT_URI = Games.CONTENT_URI.buildUpon().appendPath(PATH_RANKS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.rank";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.rank";

		public static final String DEFAULT_SORT = GameRanksColumns.GAME_RANK_TYPE + " DESC,"
				+ GameRanksColumns.GAME_RANK_VALUE + "," + GameRanksColumns.GAME_RANK_FRIENDLY_NAME;

		public static Uri buildGameRankUri(int gameRankId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(gameRankId)).build();
		}

		public static int getRankId(Uri uri) {
			return StringUtils.parseInt(uri.getLastPathSegment());
		}
	}

	public static class Designers implements DesignersColumns, BaseColumns, SyncColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_DESIGNERS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.designer";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.designer";

		public static final String DEFAULT_SORT = DesignersColumns.DESIGNER_NAME + " COLLATE NOCASE ASC";

		public static Uri buildDesignerUri(int designerId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(designerId)).build();
		}

		public static int getDesignerId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class Artists implements ArtistsColumns, BaseColumns, SyncColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTISTS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.artist";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.artist";

		public static final String DEFAULT_SORT = ArtistsColumns.ARTIST_NAME + " COLLATE NOCASE ASC";

		public static Uri buildArtistUri(int artistId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(artistId)).build();
		}

		public static int getArtistId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class Publishers implements PublishersColumns, BaseColumns, SyncColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PUBLISHERS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.publisher";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.publisher";

		public static final String DEFAULT_SORT = PublishersColumns.PUBLISHER_NAME + " COLLATE NOCASE ASC";

		public static Uri buildPublisherUri(int publisherId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(publisherId)).build();
		}

		public static int getPublisherId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class Mechanics implements MechanicsColumns, BaseColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MECHANICS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.mechanic";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.mechanic";

		public static final String DEFAULT_SORT = MechanicsColumns.MECHANIC_NAME + " COLLATE NOCASE ASC";

		public static Uri buildMechanicUri(int mechanicId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(mechanicId)).build();
		}

		public static int getMechanicId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class Categories implements CategoriesColumns, BaseColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_CATEGORIES).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.category";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.category";

		public static final String DEFAULT_SORT = CategoriesColumns.CATEGORY_NAME + " COLLATE NOCASE ASC";

		public static Uri buildCategoryUri(int categoryId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(categoryId)).build();
		}

		public static int getCategoryId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class GamesExpansions implements GamesExpansionsColumns, GamesColumns, BaseColumns {
		public static final Uri CONTENT_URI = Games.CONTENT_URI.buildUpon().appendPath(PATH_EXPANSIONS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.expansion";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.expansion";

		public static final String DEFAULT_SORT = GamesExpansionsColumns.EXPANSION_NAME + " COLLATE NOCASE ASC";
	}

	public static class Collection implements CollectionColumns, GamesColumns, BaseColumns, SyncColumns,
			SyncListColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_COLLECTION).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.collection";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.collection";

		public static final String DEFAULT_SORT = CollectionColumns.COLLECTION_SORT_NAME + " COLLATE NOCASE ASC";

		public static Uri buildItemUri(int itemId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(itemId)).build();
		}

		public static int getItemId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class Buddies implements BuddiesColumns, BaseColumns, SyncColumns, SyncListColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_BUDDIES).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.buddy";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.buddy";

		public static final String DEFAULT_SORT = BuddiesColumns.BUDDY_LASTNAME + " COLLATE NOCASE ASC, "
				+ BuddiesColumns.BUDDY_FIRSTNAME + " COLLATE NOCASE ASC";

		public static final String NAME_SORT = BuddiesColumns.BUDDY_NAME + " COLLATE NOCASE ASC";

		public static Uri buildBuddyUri(int buddyId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(buddyId)).build();
		}

		public static int getBuddyId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static class GamePolls implements GamePollsColumns, GamesColumns, BaseColumns {
		public static final Uri CONTENT_URI = Games.CONTENT_URI.buildUpon().appendPath(PATH_POLLS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.boardgamepoll";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.boardgamepoll";

		public static final String DEFAULT_SORT = GamePollsColumns.POLL_TITLE + " COLLATE NOCASE ASC";
	}

	public static final class GamePollResults implements GamePollResultsColumns, GamePollsColumns, BaseColumns {
		public static final Uri CONTENT_URI = GamePolls.CONTENT_URI.buildUpon().appendPath(PATH_POLL_RESULTS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.boardgamepollresult";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.boardgamepollresult";

		public static final String DEFAULT_SORT = POLL_RESULTS_SORT_INDEX + " ASC";
	}

	public static final class GamePollResultsResult implements GamePollResultsResultColumns, GamePollResultsColumns,
			BaseColumns {
		public static final Uri CONTENT_URI = GamePollResults.CONTENT_URI.buildUpon()
				.appendPath(PATH_POLL_RESULTS_RESULT).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.boardgamepollresultsresult";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.boardgamepollresultsresult";

		public static final String DEFAULT_SORT = POLL_RESULTS_RESULT_SORT_INDEX + " ASC";
	}

	public static final class GameColors implements GameColorsColumns, GamesColumns, BaseColumns {
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.boardgamecolor";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.boardgamecolor";

		public static final String DEFAULT_SORT = COLOR + " ASC";
	}

	public static final class Plays implements PlaysColumns, SyncColumns, SyncListColumns, BaseColumns {
		public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_PLAYS).build();

		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.play";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.play";

		public static final String DEFAULT_SORT = DATE + " DESC";

		public static Uri buildPlayUri(int playId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(playId)).build();
		}

		public static Uri buildGameUri(int gameId) {
			return CONTENT_URI.buildUpon().appendPath(PATH_GAMES).appendPath(String.valueOf(gameId)).build();
		}

		public static Uri buildItemUri(int playId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(playId)).appendPath(PATH_ITEMS).build();
		}

		public static Uri buildItemUri(int playId, int objectId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(playId)).appendPath(PATH_ITEMS)
					.appendPath(String.valueOf(objectId)).build();
		}

		public static Uri buildPlayerUri(int playId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(playId)).appendPath(PATH_PLAYERS).build();
		}

		public static Uri buildPlayerUri(int playId, long rowId) {
			return CONTENT_URI.buildUpon().appendPath(String.valueOf(playId)).appendPath(PATH_PLAYERS)
					.appendPath(String.valueOf(rowId)).build();
		}

		public static Uri buildLocationsUri() {
			return CONTENT_URI.buildUpon().appendPath(PATH_LOCATIONS).build();
		}

		public static int getPlayId(Uri uri) {
			return StringUtils.parseInt(uri.getPathSegments().get(1));
		}
	}

	public static final class PlayItems implements PlayItemsColumns, PlaysColumns, BaseColumns {
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.playitem";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.playitem";

		public static final String DEFAULT_SORT = NAME + " ASC";

		public static int getPlayItemId(Uri uri) {
			return StringUtils.parseInt(uri.getLastPathSegment());
		}
	}

	public static final class PlayPlayers implements PlayPlayersColumns, PlaysColumns, BaseColumns {
		public static final String CONTENT_TYPE = "vnd.android.cursor.dir/vnd.boardgamegeek.playplayer";
		public static final String CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd.boardgamegeek.playplayer";

		public static final String DEFAULT_SORT = START_POSITION + " ASC, " + NAME + " ASC";

		public static long getPlayPlayerId(Uri uri) {
			return Long.valueOf(uri.getLastPathSegment());
		}
	}

	private BggContract() {
	}
}