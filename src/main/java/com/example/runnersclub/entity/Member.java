package com.example.runnersclub.entity;

import com.example.runnersclub.constant.Role;
import com.example.runnersclub.dto.MemberFormDto;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Table(name = "member")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

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


}
