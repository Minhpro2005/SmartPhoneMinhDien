package com.example.demo.controller;

import com.example.demo.model.NhanVien;
import com.example.demo.service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/nhanvien") // Gốc URL: /smartphone/nhanvien
public class NhanVienController {

    @Autowired
    private NhanVienService nhanVienService;

    // ✅ Lấy danh sách tất cả nhân viên
    @GetMapping
    public List<NhanVien> getAll() {
        return nhanVienService.getAllNhanVien();
    }

    // ✅ Lấy nhân viên theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<NhanVien> getById(@PathVariable int id) {
        Optional<NhanVien> nv = nhanVienService.getNhanVienById(id);
        return nv.map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm nhân viên mới
    @PostMapping
    public ResponseEntity<NhanVien> create(@RequestBody NhanVien nhanVien) {
        NhanVien created = nhanVienService.createNhanVien(nhanVien);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật nhân viên
    @PutMapping("/id/{id}")
    public ResponseEntity<NhanVien> update(@PathVariable int id, @RequestBody NhanVien nhanVien) {
        NhanVien updated = nhanVienService.updateNhanVien(id, nhanVien);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá nhân viên
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        nhanVienService.deleteNhanVien(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm kiếm theo tên (chứa từ khoá)
    @GetMapping("/search")
    public List<NhanVien> searchByName(@RequestParam("keyword") String keyword) {
        return nhanVienService.searchByTen(keyword);
    }

    // ✅ Tìm theo email
    @GetMapping("/email/{email}")
    public ResponseEntity<NhanVien> searchByEmail(@PathVariable String email) {
        NhanVien nv = nhanVienService.searchByEmail(email);
        return nv != null ? ResponseEntity.ok(nv) : ResponseEntity.notFound().build();
    }

    // ✅ Tìm theo số điện thoại
    @GetMapping("/sdt/{sdt}")
    public List<NhanVien> searchBySdt(@PathVariable String sdt) {
        return nhanVienService.searchBySdt(sdt);
    }

    // ✅ Tìm theo CCCD
    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<NhanVien> searchByCccd(@PathVariable String cccd) {
        NhanVien nv = nhanVienService.searchByCccd(cccd);
        return nv != null ? ResponseEntity.ok(nv) : ResponseEntity.notFound().build();
    }
}
