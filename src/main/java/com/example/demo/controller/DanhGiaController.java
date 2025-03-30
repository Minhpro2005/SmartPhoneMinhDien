package com.example.demo.controller;

import com.example.demo.model.DanhGia;
import com.example.demo.model.KhachHang;
import com.example.demo.model.SanPham;
import com.example.demo.service.DanhGiaService;
import com.example.demo.service.KhachHangService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/danhgia")
public class DanhGiaController {

    @Autowired
    private DanhGiaService danhGiaService;

    @Autowired
    private KhachHangService khachHangService;

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả đánh giá
    @GetMapping
    public List<DanhGia> getAll() {
        return danhGiaService.getAll();
    }

    // ✅ Lấy đánh giá theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<DanhGia> getById(@PathVariable int id) {
        Optional<DanhGia> dg = danhGiaService.getById(id);
        return dg.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm đánh giá
    @PostMapping
    public ResponseEntity<DanhGia> create(@RequestBody DanhGia danhGia) {
        DanhGia created = danhGiaService.create(danhGia);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật đánh giá
    @PutMapping("/id/{id}")
    public ResponseEntity<DanhGia> update(@PathVariable int id, @RequestBody DanhGia danhGia) {
        DanhGia updated = danhGiaService.update(id, danhGia);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xoá đánh giá theo ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        danhGiaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy đánh giá theo khách hàng
    @GetMapping("/khachhang/{maKH}")
    public ResponseEntity<List<DanhGia>> getByKhachHang(@PathVariable int maKH) {
        Optional<KhachHang> kh = khachHangService.getKhachHangById(maKH);
        return kh.map(khach -> ResponseEntity.ok(danhGiaService.getByKhachHang(khach)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy đánh giá theo sản phẩm
    @GetMapping("/sanpham/{maSP}")
    public ResponseEntity<List<DanhGia>> getBySanPham(@PathVariable int maSP) {
        Optional<SanPham> sp = sanPhamService.getById(maSP);
        return sp.map(sanPham -> ResponseEntity.ok(danhGiaService.getBySanPham(sanPham)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy đánh giá theo số sao
    @GetMapping("/sosao/{soSao}")
    public List<DanhGia> getBySoSao(@PathVariable int soSao) {
        return danhGiaService.getBySoSao(soSao);
    }
}
