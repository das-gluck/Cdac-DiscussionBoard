package com.example.cdb.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
public class User implements UserDetails{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_id;
		
	@Column(name="first_name" , nullable = false, length=20)
	private String firstName;
	
	@Column(name="last_name" , nullable = false, length=20)
	private String lastName;
		
	@Column(name="mobile_number" , nullable = false, length=20)
	private String mobileNumber;
	
	// this is used for authentication
	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	@Getter(value = AccessLevel.NONE)
    private String password;

	@ElementCollection(fetch = FetchType.EAGER)
	@Column(name= "roles")
	private List<String> roleList = new ArrayList<String>(); 
	
	
	// list of roles[USER,ADMIN]
	// convert to 
	// collection of simpleGrantedAuthority[roles{ADMIN,USER}]
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> roles =  roleList.stream().map(role-> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
		return roles;
	}
	
	//Returns the username used to authenticate the user.
	@Override
	public String getUsername() {
		return this.email;
	}

	// Returns the password used to authenticate the user.
	@Override
	public String getPassword() {
		return this.password;
	}
		
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt = LocalDateTime.now();
		
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Post> posts;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	List<Comment> comments;
	
	
	
}
