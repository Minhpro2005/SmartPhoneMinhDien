package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Users;
import com.example.demo.service.UsersService;

@RestController
@RequestMapping("/smartphone/users")
@CrossOrigin(origins = "*") // Cho phép truy cập từ frontend
public class UserController {

    @Autowired
    private UsersService usersService;

    // Lấy tất cả người dùng
    @GetMapping
    public ResponseEntity<List<Users>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    // Lấy người dùng theo ID
    @GetMapping("/{id}")
    public ResponseEntity<Users> getUserById(@PathVariable int id) {
        Optional<Users> userOpt = usersService.getUserById(id);
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Tạo mới người dùng
    @PostMapping
    public ResponseEntity<Users> createUser(@RequestBody Users user) {
        Users createdUser = usersService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Cập nhật người dùng
    @PutMapping("/{id}")
    public ResponseEntity<Users> updateUser(@PathVariable int id, @RequestBody Users updatedUser) {
        Users user = usersService.updateUser(id, updatedUser);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Xóa người dùng
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable int id) {
        usersService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody Users loginRequest) {
        Optional<Users> userOpt = usersService.login(loginRequest.getEmail(), loginRequest.getMatKhau());
        return userOpt.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(401).build()); // Unauthorized nếu sai
    }

    // Kiểm tra vai trò
    @GetMapping("/role/{vaiTro}")
    public ResponseEntity<String> getRoleName(@PathVariable int vaiTro) {
        return ResponseEntity.ok(usersService.getRoleName(vaiTro));
    }
    
    
    @PutMapping("/{id}/status")
    public ResponseEntity<Users> updateLoginStatus(@PathVariable int id, @RequestBody LoginStatusDTO body) {
        Optional<Users> userOpt = usersService.getUserById(id);
        if (userOpt.isPresent()) {
            Users user = userOpt.get();
            user.setTrangThai(body.isTrangThai());
            Users updated = usersService.updateUser(id, user);
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
