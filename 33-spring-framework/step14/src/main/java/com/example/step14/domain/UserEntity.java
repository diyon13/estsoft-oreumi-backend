package com.example.step14.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * users 테이블과 매핑되는 JPA 엔티티(entity)
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 50, message = "Username must be between 4 and 50 characters")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Username can only contain letters, numbers and underscores")
    @Column(name = "username", length = 50, nullable = false, unique = true)
    private String username;

    @NotBlank(message = "Password is required")
    @Column(name = "password", nullable = false)
    private String password;

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @NotBlank(message = "Email is required")
    @Size(min = 6, max = 100, message = "Email must be between 6 and 100 characters")
    @Email(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "Invalid email format")
    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private UserRole role = UserRole.USER;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public UserEntity(String username, String password, String name, String email, UserRole role) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    public UserEntity updateUsername(String username) {
        this.username = username;
        return this;
    }

    public UserEntity updatePassword(String password) {
        this.password = password;
        return this;
    }

    public UserEntity updateName(String name) {
        this.name = name;
        return this;
    }

    public UserEntity updateEmail(String email) {
        this.email = email;
        return this;
    }

    public UserEntity updateRole(UserRole role) {
        this.role = role;
        return this;
    }

    public UserEntity update(@NotNull UserDto userDto) {
        this.username = userDto.getUsername();
        this.password = userDto.getPassword();
        this.name = userDto.getName();
        this.email = userDto.getEmail();
        this.role = userDto.getRole();

        return this;
    }

    public static UserEntity from(@NotNull UserDto userDto) {
        return UserEntity.builder()
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .role(userDto.getRole())
                .build();
    }
}