package com.melrs.mingle.data.repositories.user;

public class UserRepositoryResolver {

    public static UserRepository resolve() {
        return new FirebaseUserRepository();
    }
}
