package com.example.runnersclub.service;

import com.example.runnersclub.dto.MemberFormDto;
import com.example.runnersclub.entity.Member;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;

import javax.swing.plaf.PanelUI;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public Member CreateMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test1@gmail.com");
        memberFormDto.setName("김현규");
        memberFormDto.setPassword("rlagusrb12");
        memberFormDto.setAddress("신도림동 동아1차 아파트");
        return Member.createMember(memberFormDto, passwordEncoder);
    }

    @Test
    @DisplayName("회원가입테스트")
    public void saveMemberTest() {
        Member member = CreateMember();
        Member savedMember = memberService.saveMember(member);

        assertEquals(member.getEmail(), savedMember.getEmail());
        assertEquals(member.getName(), savedMember.getName());
        assertEquals(member.getAddress(), savedMember.getAddress());
        assertEquals(member.getPassword(), savedMember.getPassword());
        assertEquals(member.getRole(), savedMember.getRole());

    }

    @Test
    @DisplayName("회원가입 중복 테스트")
    public void saveDuplicateTest(){
        Member member1 = Member.createMember();
        Member member2 = Member.createMember();
        memberService.saveMember(member1);

        Throwable e= assertThrows(IllegalAccessException.class, () ->{
            memberService.saveMember(member2);

            assertEquals(",이미 가입된 회원입니다", e.getMessage());
        });
    }
}