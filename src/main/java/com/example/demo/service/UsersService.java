package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Users;
import com.example.demo.repository.UsersRepository;



@Service
public class UsersService {

    @Autowired
    private UsersRepository usersRepository;

    // Lấy tất cả người dùng
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    // Tim người dùng theo ID
    public Optional<Users> getUserById(int id) {
        return usersRepository.findById(id);
    }

    // ✅ Thêm người dùng - gán ngày tạo nếu null
    public Users createUser(Users user) {
        if (user.getNgayTao() == null) {
            user.setNgayTao(new Date()); // Gán ngày tạo là ngày hiện tại
        }
        return usersRepository.save(user);
    }

    // Cập nhật người dùng
    public Users updateUser(int id, Users updatedUser) {
        Optional<Users> existingUserOpt = usersRepository.findById(id);
        if (existingUserOpt.isPresent()) {
            Users existingUser = existingUserOpt.get();
            existingUser.setHoTen(updatedUser.getHoTen());
            existingUser.setEmail(updatedUser.getEmail());
            existingUser.setMatKhau(updatedUser.getMatKhau());
            existingUser.setSdt(updatedUser.getSdt());
            existingUser.setVaiTro(updatedUser.getVaiTro());
            existingUser.setTrangThai(updatedUser.isTrangThai());
            return usersRepository.save(existingUser);
        } else {
            return null;
        }
    }

    // Xóa người dùng
    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }

    // Đăng nhập - kiểm tra email + mật khẩu
    public Optional<Users> login(String email, String matKhau) {
        return usersRepository.findByEmailAndMatKhau(email, matKhau);
    }

    // Kiểm tra vai trò người dùng
    public String getRoleName(int vaiTro) {
        switch (vaiTro) {
            case 1:
                return "Khách hàng";
            case 2:
                return "Nhân viên";
            case 3:
                return "Quản lý";
            default:
                return "Không xác định";
        }
    }
    
}
