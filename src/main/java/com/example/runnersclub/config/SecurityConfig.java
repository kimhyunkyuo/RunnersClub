package com.example.runnersclub.config;

import com.example.runnersclub.service.MemberService;
import com.example.runnersclub.oauth.PrincipalOauth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final MemberService memberService;
    private final PrincipalOauth2UserService principalOauth2UserService;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf
                        .ignoringRequestMatchers("/mail/**")
                        .disable())
                .authorizeHttpRequests(
                        (authorizeRequests) -> authorizeRequests
                                .requestMatchers("/css/**", "/js/**", "/img/**","mail/**").permitAll()
                                .requestMatchers("/","/main.html","/members/**","/member/memberForm").permitAll()
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated())

                .formLogin(formLogin -> formLogin
                .loginPage("/members/login") // 로그인 페이지 URL 설정
                .permitAll() // 로그인 페이지는 모두 접근 가능
                .defaultSuccessUrl("/",true) // 로그인 성공 시 리다이렉트할 URL 설정
                .failureUrl("/members/login/error") // 로그인 실패 시 리다이렉트할 URL 설정
                .usernameParameter("email") // 로그인 폼에서 사용자명 필드의 name 속성 값
                .passwordParameter("password") // 로그인 폼에서 비밀번호 필드의 name 속성 값
                )

                .logout(logout -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/members/logout"))  // 로그아웃 URL 설정
                        .logoutSuccessUrl("/") // 로그아웃 성공 시 리다이렉트할 URL 설정
                        .invalidateHttpSession(true) // 세션 무효화
                        .deleteCookies("JSESSIONID") // JSESSIONID 쿠키 삭제
                )

                .oauth2Login((oauth2) -> oauth2
                        .loginPage("/oauth2/authorization/google")
                        .defaultSuccessUrl("/")
                        .userInfoEndpoint(userInfoEndpoint -> userInfoEndpoint.userService(principalOauth2UserService))
                )

                .exceptionHandling(authenticationManager -> authenticationManager
                         .authenticationEntryPoint(new CustomAuthenticationEntryPoint()));

                return http.build();
    }

}
