package com.tconnect.entity;


import com.tconnect.dto.AccountType;
import com.tconnect.dto.UserDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User { 

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	private AccountType accountType;
	private Long profileId;

	public UserDTO toDTO() {
		return new UserDTO(this.id, this.name, this.email, this.password, this.accountType, this.profileId);
	}
}
