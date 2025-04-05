package com.example.demo.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Users")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Users {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int userID;

	    private String hoTen;
	    private String email;
	    private String matKhau;
	    private String sdt; 
	    private int vaiTro;
	    
	    @Temporal(TemporalType.DATE)
	    private Date ngayTao = new Date(); // ✅ gán mặc định là ngày hiện tại

	    private boolean trangThai;
}
