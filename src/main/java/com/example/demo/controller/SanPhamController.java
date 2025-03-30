package com.example.demo.controller;

import com.example.demo.model.SanPham;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/sanpham")
@CrossOrigin(origins = "http://localhost:5173")
public class SanPhamController {

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả sản phẩm
    @GetMapping
    public List<SanPham> getAll() {
        return sanPhamService.getAll();
    }

    // ✅ Lấy sản phẩm theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<SanPham> getById(@PathVariable int id) {
        Optional<SanPham> sp = sanPhamService.getById(id);
        return sp.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm sản phẩm mới
    @PostMapping
    public ResponseEntity<SanPham> create(@RequestBody SanPham sanPham) {
        SanPham created = sanPhamService.create(sanPham);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật sản phẩm
    @PutMapping("/id/{id}")
    public ResponseEntity<SanPham> update(@PathVariable int id, @RequestBody SanPham sanPham) {
        SanPham updated = sanPhamService.update(id, sanPham);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá sản phẩm
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        sanPhamService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm theo tên sản phẩm
    @GetMapping("/search")
    public List<SanPham> searchByTen(@RequestParam("keyword") String keyword) {
        return sanPhamService.searchByTen(keyword);
    }

    // ✅ Tìm theo hãng
    @GetMapping("/hang/{hang}")
    public List<SanPham> searchByHang(@PathVariable String hang) {
        return sanPhamService.searchByHang(hang);
    }

    // ✅ Tìm theo màu sắc
    @GetMapping("/mausac/{mauSac}")
    public List<SanPham> searchByMauSac(@PathVariable String mauSac) {
        return sanPhamService.searchByMauSac(mauSac);
    }

    // ✅ Tìm theo trạng thái (true: đang bán, false: ngừng bán)
    @GetMapping("/trangthai/{trangThai}")
    public List<SanPham> searchByTrangThai(@PathVariable boolean trangThai) {
        return sanPhamService.searchByTrangThai(trangThai);
    }
}
