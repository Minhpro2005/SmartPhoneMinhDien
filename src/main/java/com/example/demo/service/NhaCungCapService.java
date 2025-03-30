package com.example.demo.service;

import com.example.demo.model.NhaCungCap;
import com.example.demo.repository.NhaCungCapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhaCungCapService {

    @Autowired
    private NhaCungCapRepository nhaCungCapRepository;

    // Lấy toàn bộ danh sách nhà cung cấp
    public List<NhaCungCap> getAll() {
        return nhaCungCapRepository.findAll();
    }

    // Lấy nhà cung cấp theo ID
    public Optional<NhaCungCap> getById(int id) {
        return nhaCungCapRepository.findById(id);
    }

    // Thêm nhà cung cấp mới
    public NhaCungCap create(NhaCungCap ncc) {
        return nhaCungCapRepository.save(ncc);
    }

    // Cập nhật nhà cung cấp
    public NhaCungCap update(int id, NhaCungCap updatedNcc) {
        Optional<NhaCungCap> existingOpt = nhaCungCapRepository.findById(id);
        if (existingOpt.isPresent()) {
            NhaCungCap existing = existingOpt.get();
            existing.setTenNCC(updatedNcc.getTenNCC());
            existing.setSdt(updatedNcc.getSdt());
            existing.setDiaChi(updatedNcc.getDiaChi());
            existing.setEmail(updatedNcc.getEmail());
            existing.setHinhAnh(updatedNcc.getHinhAnh());
            return nhaCungCapRepository.save(existing);
        }
        return null;
    }

    // Xoá nhà cung cấp
    public void delete(int id) {
        nhaCungCapRepository.deleteById(id);
    }

    // Tìm theo tên nhà cung cấp
    public List<NhaCungCap> searchByTen(String keyword) {
        return nhaCungCapRepository.findByTenNCCContainingIgnoreCase(keyword);
    }

    // Tìm theo số điện thoại
    public List<NhaCungCap> searchBySdt(String sdt) {
        return nhaCungCapRepository.findBySdt(sdt);
    }

    // Tìm theo email
    public NhaCungCap searchByEmail(String email) {
        return nhaCungCapRepository.findByEmail(email);
    }
}
