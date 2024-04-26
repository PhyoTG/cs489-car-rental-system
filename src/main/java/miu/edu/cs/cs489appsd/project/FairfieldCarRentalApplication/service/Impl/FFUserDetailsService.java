package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl;

import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class FFUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    public FFUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found for " + username));
    }
}