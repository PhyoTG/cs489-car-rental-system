package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.User;

public interface UserService {

    User getUserByUsername(String username);

    User registerNewUser(User user);
}
