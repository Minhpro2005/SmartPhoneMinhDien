package com.example.demo.controller;

import com.example.demo.model.KhachHang;
import com.example.demo.service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone") 
public class KhachHangController {

    @Autowired
    private KhachHangService khachHangService;

    // ✅ Lấy tất cả khách hàng
    @GetMapping("/khachhang")
    public List<KhachHang> getAll() {
        return khachHangService.getAllKhachHang();
    }

    // ✅ Lấy khách hàng theo ID
    @GetMapping("/khachhang/{id}")
    public ResponseEntity<KhachHang> getById(@PathVariable int id) {
        Optional<KhachHang> kh = khachHangService.getKhachHangById(id);
        return kh.map(ResponseEntity::ok)
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm khách hàng mới
    @PostMapping("/khachhang")
    public ResponseEntity<KhachHang> create(@RequestBody KhachHang khachHang) {
        KhachHang created = khachHangService.createKhachHang(khachHang);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật khách hàng
    @PutMapping("/khachhang/{id}")
    public ResponseEntity<KhachHang> update(@PathVariable int id, @RequestBody KhachHang khachHang) {
        KhachHang updated = khachHangService.updateKhachHang(id, khachHang);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá khách hàng
    @DeleteMapping("/khachhang/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        khachHangService.deleteKhachHang(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm kiếm khách theo tên
    @GetMapping("/search")
    public List<KhachHang> searchByName(@RequestParam("keyword") String keyword) {
        return khachHangService.searchByTen(keyword);
    }

    // ✅ Tìm theo SDT
    @GetMapping("/sdt/{sdt}")
    public List<KhachHang> searchBySdt(@PathVariable String sdt) {
        return khachHangService.searchBySdt(sdt);
    }

    // ✅ Tìm theo Email
    @GetMapping("/email/{email}")
    public ResponseEntity<KhachHang> searchByEmail(@PathVariable String email) {
        KhachHang kh = khachHangService.searchByEmail(email);
        if (kh != null) {
            return ResponseEntity.ok(kh);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
