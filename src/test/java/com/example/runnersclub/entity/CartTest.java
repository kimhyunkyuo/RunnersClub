package com.example.runnersclub.entity;

import com.example.runnersclub.dto.MemberFormDto;
import com.example.runnersclub.repository.CartRepository;
import com.example.runnersclub.repository.MemberRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
@TestPropertySource(locations = "classpath:application-test.properties")
class CartTest {


    @Autowired
    CartRepository cartRepository;

    @Autowired
    MemberRepository memberRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PersistenceContext
    EntityManager em;


    public Member createMember() {
        MemberFormDto memberFormDto = new MemberFormDto();
        memberFormDto.setEmail("test1@gmail.com");
        memberFormDto.setName("김현규");
        memberFormDto.setPassword("rlagusrb12");
        memberFormDto.setZipcode("8284");
        memberFormDto.setStreetadr("동아1차아파트");
        memberFormDto.setDetailadr("107동 601호");
        return Member.createMember(memberFormDto, passwordEncoder);

    }


    @Test
    @DisplayName("장바구니 회원 엔티티 매핑 조회 테스트")
    public void findCarAndMemberTest() {
        Member member = createMember();
        memberRepository.save(member);

        Cart cart = new Cart();
        cart.setMember(member);
        cartRepository.save(cart);

        em.flush();
        em.clear();

        Cart savedCart = cartRepository.findById(cart.getId())
                .orElseThrow(EntityNotFoundException::new);
        assertEquals(savedCart.getMember().getId(), member.getId());

    }






}