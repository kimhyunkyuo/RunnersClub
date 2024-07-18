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
public class Member {

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
    }
