package com.example.runnersclub.service;

import com.example.runnersclub.dto.MemberUpdateDto;
import com.example.runnersclub.entity.Member;
import com.example.runnersclub.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public Member saveMember(Member member) {
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember != null) {
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Member member = memberRepository.findByEmail(email);

        if (member == null) {
            throw new UsernameNotFoundException(email);
        }
        return User.builder()
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

    //비밀번호 일치확인
    @ResponseBody
    public boolean checkPassword(Member member, String checkPassword) {
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if (findMember == null) {
            throw new IllegalStateException("없는 회원입니다.");
        }
        String realPassword = member.getPassword();
        boolean matches = passwordEncoder.matches(checkPassword, realPassword);
        System.out.println(matches);
        return matches;
    }

    public Long updateMember(MemberUpdateDto memberUpdateDto) {
        Member member = memberRepository.findByEmail(memberUpdateDto.getEmail());
        member.updateUsername(memberUpdateDto.getName());
        member.updateAddress(memberUpdateDto.getZipcode());
        member.updateStreetAddress(memberUpdateDto.getStreetadr());
        member.updateDetailAddress(memberUpdateDto.getDetailadr());
        member.updateOriginalPassword(memberUpdateDto.getPassword());

        // 회원 비밀번호 수정을 위한 패스워드 암호화
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodePw = encoder.encode(memberUpdateDto.getPassword());
        member.updatePassword(encodePw);

        memberRepository.save(member);

        return member.getId();

    }
}