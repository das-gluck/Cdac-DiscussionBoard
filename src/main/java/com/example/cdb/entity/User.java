package com.example.cdb.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


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
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Exclude from response body
    private String password;
	
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
	@JsonManagedReference
	@ToString.Exclude // this is not required might delete later
	private List<Role> roleList = new ArrayList<>();

	
	
	// list of roles[USER,ADMIN]
	// convert to 
	// collection of simpleGrantedAuthority[roles{ADMIN,USER}]
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Collection<SimpleGrantedAuthority> roles =  roleList.stream().map(role-> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());
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
	@JsonManagedReference(value = "user-post")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY) // Exclude from request body
	//@ToString.Exclude
	List<Post> posts;
	
	@OneToMany(mappedBy = "user" , cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	@JsonManagedReference(value = "user-comment")
	@JsonProperty(access = JsonProperty.Access.READ_ONLY) // Exclude from request body
	@ToString.Exclude
	List<Comment> comments;
	
	
	@Transient
    private int followersCount;

    @Transient
    private int followingCount;

    // Methods to calculate counts
    public int getFollowersCount() {
        return this.followers != null ? this.followers.size() : 0;
    }

    public int getFollowingCount() {
        return this.following != null ? this.following.size() : 0;
    }
	
	
	// Many-to-many relationship with followers (Users following this user)
    @ManyToMany
    @JoinTable(
        name = "user_followers",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "follower_id")
    )
    @JsonIgnoreProperties({
        "mobileNumber", "email", "password","followersCount","followingCount", "followers", "following",
        "posts", "comments", "roleList", "createdAt", "authorities",
        "username", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"
    })
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ToString.Exclude
    private List<User> followers = new ArrayList<>();  // List of users following this user

    // Many-to-many relationship with users being followed (Users this user is following)
    @ManyToMany(mappedBy = "followers")
    @JsonIgnoreProperties({
        "mobileNumber", "email", "password","followersCount","followingCount", "followers", "following",
        "posts", "comments", "roleList", "createdAt", "authorities",
        "username", "enabled", "accountNonExpired", "credentialsNonExpired", "accountNonLocked"
    })
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ToString.Exclude
    private List<User> following = new ArrayList<>();  // List of users this user is following
	
}