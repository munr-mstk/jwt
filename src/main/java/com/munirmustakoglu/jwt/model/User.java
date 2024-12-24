package com.munirmustakoglu.jwt.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(); // Eğer roller varsa buraya ekleyin
    }

    @Override
    public String getPassword() {
        return password; // Kullanıcının şifre bilgisi
    }

    @Override
    public String getUsername() {
        return username; // Kullanıcının kullanıcı adı bilgisi
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Hesap süresinin dolmadığını varsayıyoruz
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Hesabın kilitli olmadığını varsayıyoruz
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Kimlik bilgileri süresinin dolmadığını varsayıyoruz
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean isEnabled() {
        return true; // Kullanıcının etkin olduğunu varsayıyoruz


    }
}


