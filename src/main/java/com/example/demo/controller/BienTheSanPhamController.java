package com.example.demo.controller;

import com.example.demo.model.BienTheSanPham;
import com.example.demo.model.SanPham;
import com.example.demo.service.BienTheSanPhamService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/bienthe")
@CrossOrigin(origins = "http://localhost:5173")
public class BienTheSanPhamController {

    @Autowired
    private BienTheSanPhamService bienTheService;

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả biến thể
    @GetMapping
    public List<BienTheSanPham> getAll() {
        return bienTheService.getAll();
    }

    // ✅ Lấy biến thể theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<BienTheSanPham> getById(@PathVariable int id) {
        Optional<BienTheSanPham> bt = bienTheService.getById(id);
        return bt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm biến thể mới
    @PostMapping
    public ResponseEntity<BienTheSanPham> create(@RequestBody BienTheSanPham bts) {
        BienTheSanPham created = bienTheService.create(bts);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật biến thể
    @PutMapping("/id/{id}")
    public ResponseEntity<BienTheSanPham> update(@PathVariable int id, @RequestBody BienTheSanPham bts) {
        BienTheSanPham updated = bienTheService.update(id, bts);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá biến thể
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        bienTheService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Tìm theo tên biến thể
    @GetMapping("/search")
    public List<BienTheSanPham> searchByTen(@RequestParam("keyword") String keyword) {
        return bienTheService.searchByTen(keyword);
    }

    // ✅ Lấy tất cả biến thể của 1 sản phẩm
    @GetMapping("/sanpham/{maSP}")
    public ResponseEntity<List<BienTheSanPham>> getBySanPham(@PathVariable int maSP) {
        Optional<SanPham> spOpt = sanPhamService.getById(maSP);
        if (spOpt.isPresent()) {
            List<BienTheSanPham> list = bienTheService.getBySanPham(spOpt.get());
            return ResponseEntity.ok(list);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Lọc theo trạng thái
    @GetMapping("/trangthai/{trangThai}")
    public List<BienTheSanPham> getByTrangThai(@PathVariable boolean trangThai) {
        return bienTheService.getByTrangThai(trangThai);
    }
}
