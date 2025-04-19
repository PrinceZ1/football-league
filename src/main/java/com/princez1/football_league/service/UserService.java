package com.princez1.football_league.service;



import com.princez1.football_league.dto.LoginRequest;
import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.UserDto;
import com.princez1.football_league.entity.User;
import com.princez1.football_league.enums.UserRole;
import com.princez1.football_league.exception.InvalidCredentialsException;
import com.princez1.football_league.exception.NotFoundException;
import com.princez1.football_league.mapper.EntityDtoMapper;
import com.princez1.football_league.repository.UserRepo;
import com.princez1.football_league.security.JwtUtils;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final EntityDtoMapper entityDtoMapper;

    @PostConstruct
    public void initAdminUser() {
        String adminEmail = "admin@gmail.com";
        if (userRepo.findByEmail(adminEmail).isEmpty()) {
            User admin = User.builder()
                    .fullName("Admin")
                    .email(adminEmail)
                    .password(passwordEncoder.encode("admin123"))
                    .phone("0987654321")
                    .role(UserRole.ADMIN)
                    .build();
            userRepo.save(admin);
            log.info("Admin user created: {}", admin);
        } else {
            log.info("Admin user already exists: {}", adminEmail);
        }
    }
    public Response createUserWithRole(UserDto userDto) {

        if (userRepo.findByEmail(userDto.getEmail()).isPresent()) {
            return Response.builder()
                    .status(400)
                    .message("Email already exists")
                    .user(null)
                    .build();
        }

        UserRole role;
        try {
            role = userDto.getRole() != null
                    ? UserRole.valueOf(userDto.getRole().toUpperCase())
                    : UserRole.USER;
        } catch (IllegalArgumentException e) {
            return Response.builder()
                    .status(400)
                    .message("Invalid role: " + userDto.getRole())
                    .user(null)
                    .build();
        }

        User user = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .phone(userDto.getPhone())
                .role(role)
                .build();

        User savedUser = userRepo.save(user);
        log.info("Created user with role: {}", savedUser);

        UserDto savedUserDto = entityDtoMapper.mapUserToDtoBasic(savedUser);
        return Response.builder()
                .status(200)
                .message("User created successfully")
                .user(savedUserDto)
                .build();
    }
    public Response registerUser(UserDto registrationRequest) {
        User user = User.builder()
                .fullName(registrationRequest.getFullName())
                .email(registrationRequest.getEmail())
                .password(passwordEncoder.encode(registrationRequest.getPassword()))
                .phone(registrationRequest.getPhone())
                .role(UserRole.USER)
                .build();

        User savedUser = userRepo.save(user);
        log.info("Registered user: {}", savedUser);

        UserDto userDto = entityDtoMapper.mapUserToDtoBasic(savedUser);
        return Response.builder()
                .status(200)
                .message("User Successfully Added")
                .user(userDto)
                .build();
    }

    public Response loginUser(LoginRequest loginRequest) {

        User user = userRepo.findByEmail(loginRequest.getEmail()).orElseThrow(()-> new NotFoundException("Email not found"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())){
            throw new InvalidCredentialsException("Password does not match");
        }
        String token = jwtUtils.generateToken(user);

        return Response.builder()
                .status(200)
                .message("User Successfully Logged In")
                .token(token)
                .expirationTime("6 Month")
                .role(user.getRole().name())
                .build();
    }

    public Response getAllUsers() {

        List<User> users = userRepo.findAll();
        List<UserDto> userDtos = users.stream()
                .map(entityDtoMapper::mapUserToDtoBasic)
                .toList();

        return Response.builder()
                .status(200)
                .userList(userDtos)
                .build();
    }

    public Response getUserInfo() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        Optional<User> userOptional = userRepo.findByEmail(username);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            UserDto userDto = entityDtoMapper.mapUserToDtoBasic(user);

            return Response.builder()
                    .status(200)
                    .message("User info retrieved successfully")
                    .user(userDto)
                    .build();
        } else {
            return Response.builder()
                    .status(404)
                    .message("User not found")
                    .user(null)
                    .build();
        }
    }
    public Response updateUserRole(int userId, String newRole) {
        UserRole role;
        try {
            role = UserRole.valueOf(newRole.toUpperCase());
        } catch (IllegalArgumentException e) {
            return Response.builder()
                    .status(400)
                    .message("Invalid role: " + newRole)
                    .user(null)
                    .build();
        }

        Optional<User> userOptional = userRepo.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setRole(role);
            User updatedUser = userRepo.save(user);

            UserDto userDto = entityDtoMapper.mapUserToDtoBasic(updatedUser);
            return Response.builder()
                    .status(200)
                    .message("User role updated successfully")
                    .user(userDto)
                    .build();
        } else {
            return Response.builder()
                    .status(404)
                    .message("User not found")
                    .user(null)
                    .build();
        }
    }
}

