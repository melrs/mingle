package com.melrs.mingle.data.mingleItem;

public class MingleItemRepositoryResolver {

    private static MingleItemRepository repository;

    public static MingleItemRepository resolve() {
        return repository == null ? repository = new MingleItemInMemoryRepository() : repository;
    }

}
