package org.example.evaexchange.Service;

import org.example.evaexchange.Entity.User;
import org.example.evaexchange.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public User createUser(User user) {
        return userRepo.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        if (userRepo.existsById(id)) {
            user.setId(id);
            return userRepo.save(user);
        }
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}