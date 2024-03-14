package com.example.auth.services;

import com.example.auth.entities.User;

public interface UserService {
  User SignUp (User user);
  User SignIn (User user);

}
