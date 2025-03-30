package com.example.demo.repository;

import com.example.demo.model.HoaDon;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhuyenMai;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface HoaDonRepository extends JpaRepository<HoaDon, Integer> {

    // ✅ Lấy danh sách hóa đơn theo khách hàng
    List<HoaDon> findByKhachHang(KhachHang khachHang);

    // ✅ Tìm hóa đơn theo khuyến mãi
    List<HoaDon> findByKhuyenMai(KhuyenMai khuyenMai);

    // ✅ Tìm hóa đơn theo người tạo
    List<HoaDon> findByNguoiTaoContainingIgnoreCase(String nguoiTao);

    // ✅ Lọc hóa đơn theo trạng thái
    List<HoaDon> findByTrangThai(boolean trangThai);

    // ✅ Tìm hóa đơn theo khoảng ngày
    List<HoaDon> findByNgayTaoBetween(Date startDate, Date endDate);
}
