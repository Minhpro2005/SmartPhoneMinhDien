package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "DonHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DonHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maDonHang;

    @ManyToOne
    @JoinColumn(name = "maKH", nullable = false)
    private KhachHang khachHang;

    @Temporal(TemporalType.DATE)
    private Date ngayDat;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal tongTien;

    @Column(nullable = false)
    private String phuongThucThanhToan;

    @Column(nullable = false)
    private String trangThai = "Chờ xử lý";

    @Column(nullable = false)
    private String diaChiGiaoHang;

    @Column(nullable = false, length = 15)
    private String sdtGiaoHang;
}
