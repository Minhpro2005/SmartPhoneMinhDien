package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "ThanhToan")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ThanhToan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maThanhToan;

    @ManyToOne
    @JoinColumn(name = "maDonHang", nullable = false)
    private DonHang donHang;

    @Column(nullable = false, precision = 18, scale = 2)
    private BigDecimal soTien;

    @Temporal(TemporalType.DATE)
    private Date ngayThanhToan;

    @Column(nullable = false)
    private String phuongThucThanhToan;

    private String trangThai = "Chưa thanh toán";
}
