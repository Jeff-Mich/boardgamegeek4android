package com.boardgamegeek.data;

import android.content.Context;

import com.boardgamegeek.R;
import com.boardgamegeek.provider.BggContract.Collection;

public class YearPublishedDescendingSortData extends YearPublishedSortData {
	public YearPublishedDescendingSortData(Context context) {
		super(context);
		mOrderByClause = getClause(Collection.YEAR_PUBLISHED, true);
		mDescriptionId = R.string.menu_collection_sort_published_newest;
	}

	@Override
	public int getType() {
		return CollectionSortDataFactory.TYPE_YEAR_PUBLISHED_DESC;
	}
}