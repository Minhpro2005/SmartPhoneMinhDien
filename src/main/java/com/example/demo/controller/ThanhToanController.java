package com.example.demo.controller;

import com.example.demo.model.DonHang;
import com.example.demo.model.ThanhToan;
import com.example.demo.service.DonHangService;
import com.example.demo.service.ThanhToanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/smartphone/thanhtoan")
public class ThanhToanController {

    @Autowired
    private ThanhToanService thanhToanService;

    @Autowired
    private DonHangService donHangService;

    // ✅ Lấy tất cả thanh toán
    @GetMapping
    public List<ThanhToan> getAll() {
        return thanhToanService.getAll();
    }

    // ✅ Lấy thanh toán theo ID
    @GetMapping("/id/{id}")
    public ResponseEntity<ThanhToan> getById(@PathVariable int id) {
        Optional<ThanhToan> tt = thanhToanService.getById(id);
        return tt.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Thêm mới thanh toán
    @PostMapping
    public ResponseEntity<ThanhToan> create(@RequestBody ThanhToan thanhToan) {
        ThanhToan created = thanhToanService.create(thanhToan);
        return ResponseEntity.ok(created);
    }

    // ✅ Cập nhật thanh toán
    @PutMapping("/id/{id}")
    public ResponseEntity<ThanhToan> update(@PathVariable int id, @RequestBody ThanhToan thanhToan) {
        ThanhToan updated = thanhToanService.update(id, thanhToan);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    // ✅ Xoá thanh toán theo ID
    @DeleteMapping("/id/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        thanhToanService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Lấy danh sách thanh toán theo đơn hàng
    @GetMapping("/donhang/{maDonHang}")
    public ResponseEntity<List<ThanhToan>> getByDonHang(@PathVariable int maDonHang) {
        Optional<DonHang> dh = donHangService.getById(maDonHang);
        return dh.map(donHang -> ResponseEntity.ok(thanhToanService.getByDonHang(donHang)))
                 .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Lấy theo trạng thái
    @GetMapping("/trangthai/{status}")
    public List<ThanhToan> getByTrangThai(@PathVariable String status) {
        return thanhToanService.getByTrangThai(status);
    }

    // ✅ Lấy theo phương thức thanh toán
    @GetMapping("/phuongthuc/{method}")
    public List<ThanhToan> getByPhuongThuc(@PathVariable String method) {
        return thanhToanService.getByPhuongThuc(method);
    }
}
