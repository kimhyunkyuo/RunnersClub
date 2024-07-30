package com.example.runnersclub.controller;

import com.example.runnersclub.dto.MailDto;
import com.example.runnersclub.dto.MemberFormDto;
import com.example.runnersclub.dto.MemberUpdateDto;
import com.example.runnersclub.entity.Member;
import com.example.runnersclub.repository.MemberRepository;
import com.example.runnersclub.service.MailService;
import com.example.runnersclub.service.MemberService;
import com.example.runnersclub.oauth.PrincipalDetails;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MailService mailService;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model) {
        model.addAttribute("memberFormDto", new MemberFormDto());

        return "member/memberForm";
    }
    @PostMapping(value = "/new")
    public String memberForm(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "member/memberForm";
        }
        try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
        }catch (IllegalStateException e){
            model.addAttribute("errorMessage", e.getMessage());
            return "member/memberForm";
        }
            return "redirect:/";
    }
    @GetMapping(value = "/login")
    public String loginMember(){
        return "/member/memberLoginForm";
    }

    @GetMapping(value = "/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요");
        return "/member/memberLoginForm";
    }
    @GetMapping("/form/loginInfo")
    @ResponseBody
    public String formLoginInfo(Authentication authentication, @AuthenticationPrincipal PrincipalDetails principalDetails){

        PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
        String member = principal.getUsername();
        System.out.println(member);

        String user1 = principalDetails.getUsername();
        System.out.println(user1);

        return member.toString();
    }
    @GetMapping("/oauth/loginInfo")
    @ResponseBody
    public String oauthLoginInfo(Authentication authentication, @AuthenticationPrincipal OAuth2User oAuth2UserPrincipal){
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        Map<String, Object> attributes = oAuth2User.getAttributes();
        System.out.println(attributes);

        Map<String, Object> attributes1 = oAuth2UserPrincipal.getAttributes();

        return attributes.toString();
    }

    @GetMapping("/loginInfo")
    @ResponseBody
    public String loginInfo(Authentication authentication) {
        String result = "";

        if (authentication.getPrincipal() instanceof PrincipalDetails) {
            PrincipalDetails principal = (PrincipalDetails) authentication.getPrincipal();
            result = "Form 로그인 : " + principal.getUsername();
        } else if (authentication.getPrincipal() instanceof OAuth2User) {
            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
            result = "OAuth2 로그인 : " + oAuth2User.getAttributes().toString();
        }

        return result;
    }
    //정보확인 폼
    @GetMapping("/myInfo")
    public String memberInfo(Principal principal, ModelMap modelMap){
        if (principal == null) {
            // 인증되지 않은 사용자 처리
            return "redirect:/members/login"; // 로그인 페이지로 리다이렉트
        }
        String loginId = principal.getName();
        Member member =  memberRepository.findByEmail(loginId);
        if (member == null) {
            // 회원 정보가 없는 경우 처리
            return "error/memberNotFound"; // 적절한 에러 페이지로 리다이렉트
        }
        modelMap.addAttribute("member", member);

        return "/member/myInfo";
    }

    //회원 비밀번호 찾기 폼
    @GetMapping(value = "/findMember")
    public String findMemberForm(Model model){
        return "/member/findMemberForm";
    }
    @RequestMapping(value = "/findId", method = RequestMethod.POST)
    @ResponseBody
    public String findId(@RequestParam("memberEmail") String memberEmail) {
        String email = String.valueOf(memberRepository.findByEmail(memberEmail));
        System.out.println("회원 이메일 = " + email);
        if(email == null) {
            return null;
        } else {
            return email;
        }
    }

    //비밀번호 찾기, 임시 비밀번호 이메일 전송
    @Transactional
    @PostMapping("/sendEmail")
    public String sendEmail(@RequestParam("memberEmail")String memberEmail){
        MailDto dto = mailService.createMailAndChangePassword(memberEmail);
        mailService.mailSend(dto);

        return "/member/memberLoginForm";
    }
    /* 회원 수정하기 전 비밀번호 확인 */
    @GetMapping("/checkPwdForm")
    public String checkPwdView(){
        return "member/passwordCheckForm";
    }
    //비밀번호 일치 확인
    @GetMapping("/checkPwd")
    @ResponseBody
    public boolean checkPassword(Principal principal, Member member, @RequestParam String checkPassword, Model model){
        String loginId = principal.getName();

        Member memberId = memberRepository.findByEmail(loginId);
        System.out.println(memberId.getPassword());
        return memberService.checkPassword(memberId, checkPassword);
    }
    // 회원 정보 변경 폼
    @GetMapping(value = "/updateForm")
    public String updateMemberForm(Principal principal, Model model) {
        String loginId = principal.getName();
        Member memberId = memberRepository.findByEmail(loginId);
        model.addAttribute("member", memberId);

        return "/member/memberUpdateForm";
    }
    // 회원 정보 변경
    @PostMapping(value = "/updateForm")
    public String updateMember(@Valid MemberUpdateDto memberUpdateDto, Model model) {
        model.addAttribute("member", memberUpdateDto);
        memberService.updateMember(memberUpdateDto);
        return "redirect:/members/myInfo";
    }
}