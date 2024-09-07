package com.melrs.mingle.data.repositories.mingleItem;

import android.content.Context;

public class MingleItemRepositoryResolver {

    private static MingleItemRepository repository;

    public static MingleItemRepository resolve(Context context) {
        return repository == null ? repository = new MingleItemDatabaseRepository(context) : repository;
    }

}
