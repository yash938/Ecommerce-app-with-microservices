package com.microservices.user_service.service;

import com.microservices.user_service.entity.User;

public interface UserService {

    User createUser(User user);
    User singleUser(int userId);
    User updateAccountStatus(int userId, double usedAmount);
}
