package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "GioHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GioHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maGioHang;

    @ManyToOne
    @JoinColumn(name = "maKH")
    private KhachHang khachHang;

    @Temporal(TemporalType.DATE)
    private Date ngayTao;

    private boolean trangThai = true;
}
