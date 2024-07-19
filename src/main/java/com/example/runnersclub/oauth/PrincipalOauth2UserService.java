package com.example.runnersclub.oauth;

import com.example.runnersclub.constant.Role;
import com.example.runnersclub.entity.Member;
import com.example.runnersclub.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException{

        OAuth2User oAuth2User = super.loadUser(userRequest);
        log.info("getAttributes : {}",oAuth2User.getAttributes());

        //소셜 로그인 제공자 정보
        String provider = userRequest.getClientRegistration().getRegistrationId();
        String providerId = oAuth2User.getAttribute("sub");
        String name = provider+ "_" + providerId;


        String uuid = UUID.randomUUID().toString().substring(0,7);
        String password = bCryptPasswordEncoder.encode("패스워드" + uuid);

        String email = oAuth2User.getAttribute("email");
        Role role = Role.USER;

        Member byUserName = memberRepository.findByEmail(email);


        //DB에 없는 사용자 회원가입 처리
        if(byUserName == null){
            byUserName = Member.oauth2Register()
                    .password(password).email(email).role(role)
                    .provider(provider).providerId(providerId)
                    .build();
            memberRepository.save(byUserName);
        }

        return new PrincipalDetails(byUserName, oAuth2User.getAttributes());
    }
}
