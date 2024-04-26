package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.User;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.UserRepository;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return userRepository.findUserByUsername(username)
                .orElse(null);
    }

    @Override
    public User registerNewUser(User user) {
        return userRepository.save(user);
    }
}
