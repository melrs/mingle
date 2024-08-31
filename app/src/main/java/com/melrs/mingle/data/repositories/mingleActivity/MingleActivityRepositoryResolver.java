package com.melrs.mingle.data.repositories.mingleActivity;

public class MingleActivityRepositoryResolver {

    public static MingleActivityRepository resolve() {
        return new MingleActivityInMemoryRepository();
    }

}
