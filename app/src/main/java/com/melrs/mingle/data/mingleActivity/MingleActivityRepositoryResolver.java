package com.melrs.mingle.data.mingleActivity;

public class MingleActivityRepositoryResolver {

    public static MingleActivityRepository resolve() {
        return new MingleActivityInMemoryRepository();
    }

}
