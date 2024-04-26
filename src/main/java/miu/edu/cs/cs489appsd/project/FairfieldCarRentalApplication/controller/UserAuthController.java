package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.controller;

import jakarta.validation.Valid;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.role.RoleResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user.UserAuthRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user.UserAuthResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user.UserRequest;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.dto.user.UserResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.Role;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.model.User;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.RoleService;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.UserService;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.util.JWTMgmtUtilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = {"/ffweb/api/v1/public/auth"})
public class UserAuthController {

    private JWTMgmtUtilityService jwtMgmtUtilityService;
    private AuthenticationManager authenticationManager;
    private UserService userService;
    private RoleService roleService;

    public UserAuthController(JWTMgmtUtilityService jwtMgmtUtilityService,
                              AuthenticationManager authenticationManager,
                              UserService userService, RoleService roleService) {
        this.jwtMgmtUtilityService = jwtMgmtUtilityService;
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.roleService = roleService;
    }

    @PostMapping(value = {"/signup"})
    public ResponseEntity<UserResponse> registerUser(@RequestBody UserRequest userRequest) throws Exception {
        User user = userService.getUserByUsername(userRequest.username());
        if (user != null) {
            throw new Exception("User Already Exist");
        } else {
            List<Role> roles = new ArrayList<>();
            userRequest.roleIds().forEach(roleId ->
                    roles.add(roleService.getRoleById(roleId))
            );

            String password = new BCryptPasswordEncoder().encode(userRequest.password());

            user = userService.registerNewUser(new User(0, userRequest.username(), password, userRequest.firstName(), userRequest.lastName(), userRequest.email(), true, true, true, true, roles));
        }

        List<RoleResponse> roleResponses = new ArrayList<>();
        user.getRoles().forEach(role ->
                roleResponses.add(new RoleResponse(role.getRoleId(), role.getRoleName()))
        );

        return new ResponseEntity(new UserResponse(
                user.getUserId(),
                user.getUsername(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.isAccountNonExpired(),
                user.isAccountNonLocked(),
                user.isCredentialsNonExpired(),
                user.isEnabled(),
                roleResponses
        ), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<UserAuthResponse> authenticateUser(@RequestBody  UserAuthRequest userAuthRequest) throws Exception {
        UserAuthResponse userAuthResponse = null;
        try {
            var username = userAuthRequest.username();
            var password = userAuthRequest.password();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username,
                            password)
            );
            var jwtToken = jwtMgmtUtilityService.generateToken(username);
            var user = userService.getUserByUsername(username);
            if (user != null) {
                userAuthResponse = new UserAuthResponse(jwtToken, user.getFirstName(), user.getLastName());
            }
        } catch (Exception ex) {
            System.out.println("UserAuthException is: " + ex);
            throw ex;
        }
        return ResponseEntity.ok(userAuthResponse);
    }



}
