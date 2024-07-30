package com.example.runnersclub.entity;

import com.example.runnersclub.constant.Role;
import com.example.runnersclub.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
public class Member extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String originalpassword;

    private String password;

    private String zipcode;

    private String streetadr;

    private String detailadr;

    @Enumerated(EnumType.STRING)
    private Role role;

    // 소셜 로그인
    private String provider;
    private String providerId;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder) {
        return Member.builder()
                .name(memberFormDto.getName())
                .email(memberFormDto.getEmail())
                .originalpassword(memberFormDto.getPassword())
                .password(passwordEncoder.encode(memberFormDto.getPassword()))
                .zipcode(memberFormDto.getZipcode())
                .streetadr(memberFormDto.getStreetadr())
                .detailadr(memberFormDto.getDetailadr())
                .role(Role.ADMIN)
                .build();

    }

    @Builder(builderClassName = "OAuth2Register", builderMethodName = "oauth2Register")
    public Member(String name, String password, String email, Role role, String provider, String providerId) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
        this.provider = provider;
        this.providerId = providerId;
    }

    @Builder(builderClassName = "UserDetailRegister", builderMethodName = "userDetailRegister")
    public Member(String name, String password, String email, Role role) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.role = role;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.name = name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }


    /**
     * 회원수정 메소드
     */
    public void updateUsername(String name) { this.name = name; }

    public void updatePassword(String password) {
        this.password = password;
    }

    public void updateOriginalPassword(String originalpassword) {
        this.originalpassword = originalpassword;
    }

    public void updateAddress(String zipcode) { this.zipcode = zipcode; }

    public void updateStreetAddress(String streetadr) { this.streetadr = streetadr; }

    public void updateDetailAddress(String detailadr) { this.detailadr = detailadr; }

}

