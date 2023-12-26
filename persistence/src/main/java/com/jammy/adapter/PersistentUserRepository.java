package com.jammy.adapter;

import com.jammy.business.adapter.UserRepositoryAdapter;
import com.jammy.domain.models.User;
import com.jammy.entities.UserEntity;
import com.jammy.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class PersistentUserRepository implements UserRepositoryAdapter {
    private final UserRepository userRepository;

    public PersistentUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        var userEntity = UserBusinessModelToEntity(user);
        UserEntity persistedUser = userRepository.save(userEntity);
        return UserEntityToBusinessModel(persistedUser);
    }

    @Override
    public User findByUsername(String username) {
        var persistedUser = userRepository.findByUsername(username);
        return UserEntityToBusinessModel(persistedUser);
    }

    private User UserEntityToBusinessModel(UserEntity userEntity) {
        return new User(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword()
        );
    }

    private UserEntity UserBusinessModelToEntity(User user) {
        return new UserEntity(
                user.getId(),
                user.getUsername(),
                user.getPassword()
        );
    }
}
