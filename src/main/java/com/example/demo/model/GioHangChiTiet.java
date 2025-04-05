package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "GioHangChiTiet")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHangChiTiet {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @ManyToOne
	    @JoinColumn(name = "maGioHang")
	    private GioHang gioHang;

	    @ManyToOne
	    @JoinColumn(name = "maSP")
	    private SanPham sanPham;

	    @ManyToOne
	    @JoinColumn(name = "maBienThe", nullable = true) // ✅ Thêm ràng buộc này
	    private BienTheSanPham bienThe;

	    @Column(name = "soLuong")
	    private int soLuong = 1;

	    @Column(nullable = false, precision = 18, scale = 2)
	    private BigDecimal giaBan;
}
