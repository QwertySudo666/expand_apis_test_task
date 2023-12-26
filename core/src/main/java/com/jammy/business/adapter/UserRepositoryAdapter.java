package com.jammy.business.adapter;

import com.jammy.domain.models.User;

public interface UserRepositoryAdapter {
    User save(User user);

    User findByUsername(String username);
}
