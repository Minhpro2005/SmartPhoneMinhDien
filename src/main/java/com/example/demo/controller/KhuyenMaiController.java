package com.example.demo.controller;

import com.example.demo.model.KhuyenMai;
import com.example.demo.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/khuyenmai") // Gốc: /smartphone/khuyenmai
public class KhuyenMaiController {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    // ✅ Lấy danh sách khuyến mãi
    @GetMapping
    public List<KhuyenMai> getAll() {
        return khuyenMaiService.getAll();
    }

    // ✅ Lấy khuyến mãi theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<KhuyenMai> getById(@PathVariable int id) {
        Optional<KhuyenMai> km = khuyenMaiService.getById(id);
        return km.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm khuyến mãi mới
    @PostMapping
    public ResponseEntity<KhuyenMai> create(@RequestBody KhuyenMai khuyenMai) {
        KhuyenMai created = khuyenMaiService.create(khuyenMai);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật khuyến mãi
    @PutMapping("/id/{id}")
    public ResponseEntity<KhuyenMai> update(@PathVariable int id, @RequestBody KhuyenMai khuyenMai) {
        KhuyenMai updated = khuyenMaiService.update(id, khuyenMai);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá khuyến mãi
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        khuyenMaiService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm theo tên khuyến mãi
    @GetMapping("/search")
    public List<KhuyenMai> searchByName(@RequestParam("keyword") String keyword) {
        return khuyenMaiService.searchByTen(keyword);
    }

    // ✅ Tìm theo khoảng ngày bắt đầu
    @GetMapping("/batdau")
    public List<KhuyenMai> searchByNgayBatDau(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return khuyenMaiService.searchByNgayBatDau(start, end);
    }

    // ✅ Tìm theo khoảng ngày kết thúc
    @GetMapping("/ketthuc")
    public List<KhuyenMai> searchByNgayKetThuc(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        return khuyenMaiService.searchByNgayKetThuc(start, end);
    }
}
