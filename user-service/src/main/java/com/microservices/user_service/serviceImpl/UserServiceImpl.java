package com.microservices.user_service.serviceImpl;

import com.microservices.user_service.entity.User;
import com.microservices.user_service.repository.UserRepo;
import com.microservices.user_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;
    @Override
    public User createUser(User user) {
        User save = userRepo.save(user);
        return save;
    }

    @Override
    public User singleUser(int userId) {
        User byId = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("user is not found"));
        return byId;
    }

    public User updateAccountStatus(int userId, double usedAmount) {
        User userDetailsFromDB = singleUser(userId);
        userDetailsFromDB.setAvailableAmount(userDetailsFromDB.getAvailableAmount() - usedAmount);
        return userRepo.save(userDetailsFromDB);
    }
}
