package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    Optional<Users> findByEmailAndMatKhau(String email, String matKhau);

    // ✅ Lấy người dùng có trạng thái đăng nhập
    Optional<Users> findByTrangThaiTrue();
}
