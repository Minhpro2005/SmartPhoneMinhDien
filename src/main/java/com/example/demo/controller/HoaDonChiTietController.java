package com.example.demo.controller;

import com.example.demo.model.HoaDon;
import com.example.demo.model.HoaDonChiTiet;
import com.example.demo.model.SanPham;
import com.example.demo.service.HoaDonChiTietService;
import com.example.demo.service.HoaDonService;
import com.example.demo.service.SanPhamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/chitiethoadon")
public class HoaDonChiTietController {

    @Autowired
    private HoaDonChiTietService chiTietService;

    @Autowired
    private HoaDonService hoaDonService;

    @Autowired
    private SanPhamService sanPhamService;

    // ✅ Lấy tất cả chi tiết hóa đơn
    @GetMapping
    public List<HoaDonChiTiet> getAll() {
        return chiTietService.getAll();
    }

    // ✅ Lấy chi tiết theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<HoaDonChiTiet> getById(@PathVariable int id) {
        Optional<HoaDonChiTiet> ct = chiTietService.getById(id);
        return ct.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Tạo mới chi tiết hóa đơn
    @PostMapping
    public ResponseEntity<HoaDonChiTiet> create(@RequestBody HoaDonChiTiet chiTiet) {
        HoaDonChiTiet created = chiTietService.create(chiTiet);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật chi tiết hóa đơn
    @PutMapping("/id/{id}")
    public ResponseEntity<HoaDonChiTiet> update(@PathVariable int id, @RequestBody HoaDonChiTiet chiTiet) {
        HoaDonChiTiet updated = chiTietService.update(id, chiTiet);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ✅ Xoá chi tiết hóa đơn
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        chiTietService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy chi tiết theo mã hóa đơn
    @GetMapping("/hoadon/{maHD}")
    public ResponseEntity<List<HoaDonChiTiet>> getByHoaDon(@PathVariable int maHD) {
        Optional<HoaDon> hoaDon = hoaDonService.getById(maHD);
        return hoaDon.map(hd -> ResponseEntity.ok(chiTietService.getByHoaDon(hd)))
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy chi tiết theo mã sản phẩm
    @GetMapping("/sanpham/{maSP}")
    public ResponseEntity<List<HoaDonChiTiet>> getBySanPham(@PathVariable int maSP) {
        Optional<SanPham> sp = sanPhamService.getById(maSP);
        return sp.map(s -> ResponseEntity.ok(chiTietService.getBySanPham(s)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Tìm chi tiết theo hóa đơn và sản phẩm
    @GetMapping("/hoadon/{maHD}/sanpham/{maSP}")
    public ResponseEntity<HoaDonChiTiet> getByHoaDonAndSanPham(@PathVariable int maHD, @PathVariable int maSP) {
        Optional<HoaDon> hoaDon = hoaDonService.getById(maHD);
        Optional<SanPham> sanPham = sanPhamService.getById(maSP);
        if (hoaDon.isPresent() && sanPham.isPresent()) {
            HoaDonChiTiet result = chiTietService.getByHoaDonAndSanPham(hoaDon.get(), sanPham.get());
            if (result != null) {
                return ResponseEntity.ok(result);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
