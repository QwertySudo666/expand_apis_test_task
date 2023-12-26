package com.jammy.business.facade;

import com.jammy.business.adapter.UserRepositoryAdapter;
import com.jammy.domain.models.User;

public class UserFacade {
    private UserRepositoryAdapter userRepositoryAdapter;

    public UserFacade(UserRepositoryAdapter userRepositoryAdapter) {
        this.userRepositoryAdapter = userRepositoryAdapter;
    }

    public User save(User user) {
        return userRepositoryAdapter.save(user);
    }

    public User findByUsername(String username) {
        return userRepositoryAdapter.findByUsername(username);
    }
}
