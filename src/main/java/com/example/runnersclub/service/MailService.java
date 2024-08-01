package com.example.runnersclub.service;

import com.example.runnersclub.dto.MailDto;
import com.example.runnersclub.entity.Member;
import com.example.runnersclub.repository.MemberRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private static final String senderEmail = "khg1222@gmail.com";
    private static int number;
    private final MemberRepository memberRepository;

    public static void createNumber(){
        number = (int) (Math.random() * (90000)) + 100000;
    }
    public MimeMessage createMail(String mail){{

    }
        createNumber();
        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            message.setFrom(senderEmail);
            message.setRecipients(MimeMessage.RecipientType.TO, mail);
            message.setSubject("[RunnersClub] 회원가입 이메일 인증입니다.");
            String body = "";
            body += "<h1>" + "[RunnersClub] 회원가입 이메일 인증입니다." + "</h1>";
//            body += "<h3>" + "요청하신 인증 번호입니다." + "</h3>";
            body += "<h2>" + number + "요청하신 인증 번호입니다." + "</h2>";
            body += "<h2>" + "인증번호 입력란에 인증번호를 입력해주시기바랍니다." + "</h2>";
            body += "<h2>" + "감사합니다." + "</h3>";
            message.setText(body,"UTF-8", "html");
        }catch (MessagingException e){
            e.printStackTrace();
        }
        return message;
    }

    // 임시 비밀번호 생성
    public static String getTempPassword(){
        char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String str = "";

        // 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            str += charSet[idx];
        }
        return str;
    }
    // 메일 내용을 생성하고 임시 비밀번호로 회원 비밀번호를 변경
    public MailDto createMailAndChangePassword(String memberEmail) {
        String str = getTempPassword();
        MailDto dto = new MailDto();
        dto.setAddress(memberEmail);
        dto.setTitle("[RunnersClub] 임시비밀번호 안내 이메일 입니다.");
        dto.setMessage("안녕하세요. RunnersClub 임시비밀번호 안내 관련 이메일 입니다. \n" + " 회원님의 임시 비밀번호는 "
                + str + " 입니다. \n" + "로그인 후에 비밀번호를 변경해주세요!");
        updatePassword(str, memberEmail);
        return dto;
    }
    public void mailSend(MailDto mailDto) {
        System.out.println("전송 완료!");
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(mailDto.getAddress());
        message.setSubject(mailDto.getTitle());
        message.setText(mailDto.getMessage());
        message.setFrom("khg1222@gmail.com");
        message.setReplyTo("khg1222@gmail.com");
        System.out.println("message"+message);
        javaMailSender.send(message);
    }

    //임시 비밀번호로 업데이트
    public boolean updatePassword(String str, String email){
        try {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodePw = encoder.encode(str);
            Member member = memberRepository.findByEmail(email);
           // member.updateOriginalPassword(str);
            member.updatePassword(encodePw);
            memberRepository.save(member);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public int sendMail(String mail){
        MimeMessage message = createMail(mail);
        javaMailSender.send(message);
        return number;
     }
}
