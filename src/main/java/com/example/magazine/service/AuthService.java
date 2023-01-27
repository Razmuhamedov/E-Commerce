package com.example.magazine.service;

import com.example.magazine.configuration.JwtTokenUtil;
import com.example.magazine.dto.auth.LoginDto;
import com.example.magazine.dto.auth.LoginResult;
import com.example.magazine.dto.profile.ProfileCreateDto;
import com.example.magazine.entity.Profile;
import com.example.magazine.exception.BadRequest;
import com.example.magazine.exception.EmailNotDelivered;
import com.example.magazine.repository.ProfileRepo;
import com.example.magazine.type.Role;
import com.example.magazine.type.ProfileStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    private final ProfileRepo profileRepo;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;
    private final JwtTokenUtil jwtTokenUtil;

    public AuthService(ProfileRepo profileRepo, PasswordEncoder passwordEncoder, MailSenderService mailSenderService, JwtTokenUtil jwtTokenUtil) {
        this.profileRepo = profileRepo;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    public String signUp(ProfileCreateDto dto) {
        Optional<Profile> optional = profileRepo.findByEmailAndDeletedAtIsNull(dto.getEmail());
        if(optional.isPresent()){
            throw new BadRequest("This email already exist!");
        }
        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setSurname(dto.getSurname());
        profile.setPhone(dto.getPhone());
        profile.setStatus(ProfileStatus.INACTIVE);
        profile.setAddress(dto.getAddress());
        profile.setPassword(passwordEncoder.encode(dto.getPassword()));
        profile.setImageId(dto.getImageId());
        profile.setRole(Role.ROLE_USER);
        profile.setCreatedAt(LocalDateTime.now());
        profileRepo.save(profile);
        if(mailSenderService.sendEmailVerification(profile)){
            return "We send link to your email. Please, confirm  to activate your account!";
        }
        throw new EmailNotDelivered("Email not delivered!");
    }

    public LoginResult signIn(LoginDto dto) {
        Optional<Profile> optional = profileRepo.findByEmailAndDeletedAtIsNull(dto.getEmail());
        if(optional.isEmpty()){
            throw new BadRequest("User not found!");
        }
        Profile profile = optional.get();
        if(!passwordEncoder.matches(dto.getPassword(), profile.getPassword())){
            throw new BadRequest("Password incorrect!");
        }
        String token = jwtTokenUtil.generateAccessToken(profile.getId(), profile.getEmail());
        return new LoginResult(profile.getEmail(), token);
    }

    public String verification(String token) {
        if(!jwtTokenUtil.validate(token)){
            throw new BadRequest("Token invalid");
        }
        String email = jwtTokenUtil.getEmail(token);
        Optional<Profile> optional = profileRepo.findByEmailAndDeletedAtIsNull(email);
        if(optional.isEmpty()){
            throw new BadRequest("User not found");
        }
        Profile profile = optional.get();
        if(profile.getStatus().equals(ProfileStatus.ACTIVE)){
            throw new BadRequest("User already verified");
        }
        profile.setVerifiedAt(LocalDateTime.now());
        profile.setStatus(ProfileStatus.ACTIVE);
        profileRepo.save(profile);
        return "Successful verified";
    }
}
