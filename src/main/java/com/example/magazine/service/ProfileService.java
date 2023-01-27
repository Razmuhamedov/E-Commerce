package com.example.magazine.service;

import com.example.magazine.dto.profile.ProfileCreateDto;
import com.example.magazine.dto.profile.ProfileDto;
import com.example.magazine.entity.Profile;
import com.example.magazine.exception.BadRequest;
import com.example.magazine.repository.ProfileRepo;
import com.example.magazine.type.Role;
import com.example.magazine.type.ProfileStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProfileService {

    private final ProfileRepo profileRepo;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepo profileRepo, PasswordEncoder passwordEncoder) {
        this.profileRepo = profileRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public String createProfile(ProfileCreateDto dto) {
        check(dto);
        Profile profile = new Profile();
        profile.setCreatedAt(LocalDateTime.now());
        profile.setRole(Role.ROLE_USER);
        profile.setStatus(ProfileStatus.ACTIVE);
        convertToEntity(dto, profile);
        profileRepo.save(profile);
        return "Profile created successful";

    }

    public String updateProfile(Long id, ProfileCreateDto dto) {
        //todo: check()
        Profile profile = getProfileById(id);
        profile.setUpdatedAt(LocalDateTime.now());
        convertToEntity(dto, profile);
        profileRepo.save(profile);
        return "Profile updated successful";
    }

    public String deleteProfile(Long id) {
        Profile profile = getEntity(id);
        profileRepo.delete(profile);
        return "Profile deleted from database!";
    }

    public String softDelete(Long id) {
        Profile profile = getProfileById(id);
        profile.setDeletedAt(LocalDateTime.now());
        profile.setStatus(ProfileStatus.BLOCKED);
        profileRepo.save(profile);
        return "Profile deleted!";
    }

    public ProfileDto getProfileDto(Long id){
        return convertToDto(getProfileById(id), new ProfileDto());
    }

    private Profile getProfileById(Long id) {
        Optional<Profile> optional = profileRepo.findByIdAndDeletedAtIsNull(id);
        if(optional.isEmpty()){
            throw new BadRequest("User not found or it was already deleted!");
        }
        return optional.get();
    }

    public ProfileDto getProfileByEmail(String email) {
        Optional<Profile> optional = profileRepo.findByEmailAndDeletedAtIsNull(email);
        if(optional.isPresent()) {
            return convertToDto(optional.get(), new ProfileDto());
        }
        throw new BadRequest("Profile not found!");
    }

    public List<ProfileDto> getAllProfiles(int page, int size) {
        PageRequest request = PageRequest.of(page, size);
        Page<Profile> pages = profileRepo.getAllByDeletedAt(request);
        return pages.stream().map(profile -> convertToDto(profile, new ProfileDto())).collect(Collectors.toList());
    }

    public String changeProfileStatus(Long id, String status) {
        Profile profile = getEntity(id);
        profile.setStatus(ProfileStatus.valueOf(status));
        profileRepo.save(profile);
        return "Profile status changed to " + status;
    }

    public String changeProfileRole(Long id, String role){
        Profile profile = getEntity(id);
        profile.setRole(Role.valueOf(role));
        profileRepo.save(profile);
        return "Profile role changed to " + role;
    }
    //todo: refactoring count by deletedAt is null
    public Long countProfiles() {
        return profileRepo.countByDeletedAt;
    }

    private ProfileDto convertToDto(Profile profile, ProfileDto dto){
        dto.setId(profile.getId());
        dto.setName(profile.getName());
        dto.setImage(profile.getImage());
        dto.setEmail(profile.getEmail());
        dto.setAddress(profile.getAddress());
        dto.setRole(profile.getRole());
        dto.setSurname(profile.getSurname());
        dto.setPassword(profile.getPassword());
        dto.setPhone(profile.getPhone());
        dto.setStatus(profile.getStatus());
        return dto;
    }

    private void convertToEntity(ProfileCreateDto dto, Profile profile){
        profile.setName(dto.getName());
        profile.setSurname(dto.getSurname());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());
        profile.setPassword(passwordEncoder.encode(dto.getPassword()));
        profile.setAddress(dto.getAddress());
        profile.setImageId(dto.getImageId());
    }

    private void check(ProfileCreateDto dto){
        Optional<Profile> optional = profileRepo.findByEmailOrPhoneAndDeletedAtIsNull(dto.getEmail(), dto.getPhone());
        if(optional.isPresent()){
            throw new BadRequest("Profile with this email or phone already exist");
        }
    }

    private Profile getEntity(Long id){
        Optional<Profile> optional = profileRepo.findById(id);
        if(optional.isEmpty()){
            throw new BadRequest("Profile not found!");
        }
        return optional.get();
    }
}
