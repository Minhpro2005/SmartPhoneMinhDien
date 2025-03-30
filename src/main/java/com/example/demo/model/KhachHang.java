package com.example.demo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "KhachHang")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class KhachHang {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int maKH;

    @Column(nullable = false)
    private String tenKH;

    private String sdt;

    private String diaChi;

    private String email;

    // Khóa ngoại liên kết đến Users
    @ManyToOne
    @JoinColumn(name = "userID")
    private Users user;
}
