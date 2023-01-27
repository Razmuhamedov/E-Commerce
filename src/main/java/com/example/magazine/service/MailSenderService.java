package com.example.magazine.service;

import com.example.magazine.configuration.JwtTokenUtil;
import com.example.magazine.entity.Profile;
import com.example.magazine.repository.ProfileRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {
    private final MailSender mailSender;
    private final JwtTokenUtil jwtTokenUtil;
    private final ProfileRepo profileRepo;
    @Value("${servers.address}+/auth/verification/")
    private String url;


    public MailSenderService(MailSender mailSender, JwtTokenUtil jwtTokenUtil, ProfileRepo profileRepo) {
        this.mailSender = mailSender;
        this.jwtTokenUtil = jwtTokenUtil;
        this.profileRepo = profileRepo;
    }

    public Boolean sendEmailVerification(Profile profile) {
        String token = jwtTokenUtil.generateAccessToken(profile.getId(), profile.getEmail());
        String link = String.format(url + token);
        String subject = "Magazine of high technologies verification";
        String content = "Click this for verification your account:" + link;
        try{
            send(profile.getEmail(), subject, content);
        }catch (Exception e){
            profileRepo.delete(profile);
            return false;
        }
        return true;
    }

    public void send(String toAccount, String title, String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(toAccount);
        simpleMailMessage.setSubject(title);
        simpleMailMessage.setText(content);

        mailSender.send(simpleMailMessage);
    }
}
