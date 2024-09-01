package com.melrs.mingle.data;

import com.melrs.mingle.data.model.MingleUser;

import java.io.IOException;

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
public class LoginDataSource {

    public Result<MingleUser> login(String username, String password) {

        try {
            // TODO: handle loggedInUser authentication
            MingleUser fakeUser =
                    new MingleUser(
                            1,
                            "Jane Doe");
            return new Result.Success<>(fakeUser);
        } catch (Exception e) {
            return new Result.Error(new IOException("Error logging in", e));
        }
    }

    public void logout() {
        // TODO: revoke authentication
    }
}