package com.princez1.football_league.controller;

import com.princez1.football_league.dto.Response;
import com.princez1.football_league.dto.UserDto;
import com.princez1.football_league.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/admin/users")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> createUserWithRole(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.createUserWithRole(userDto));
    }

    @GetMapping("/get-all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> getAllUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @PutMapping("/{userId}/role")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateUserRole(@PathVariable int userId, @RequestParam String newRole) {
        return ResponseEntity.ok(userService.updateUserRole(userId, newRole));
    }

    @GetMapping("/my-info")
    public ResponseEntity<Response> getUserInfo(){
        return ResponseEntity.ok(userService.getUserInfo());
    }
}
