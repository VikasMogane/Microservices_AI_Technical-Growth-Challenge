package com.example.demo.dto;

import lombok.*;

@Getter @Setter @NoArgsConstructor  @Builder
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    
    
	public UserDTO() {
		// TODO Auto-generated constructor stub
	}
	public UserDTO(Long id, String name, String email) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
    
    
}