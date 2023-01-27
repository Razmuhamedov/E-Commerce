package com.example.magazine.controller;

import com.example.magazine.configuration.SecurityUtil;
import com.example.magazine.dto.profile.ProfileCreateDto;
import com.example.magazine.dto.profile.ProfileDto;
import com.example.magazine.service.ProfileService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }
////////////////////////*ADMIN*////////////////////////////
    @PostMapping("/secured/create")
    public ResponseEntity<?> createProfile(@RequestBody @Valid ProfileCreateDto dto){
        String result = profileService.createProfile(dto);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/secured/update/{id}")
    public ResponseEntity<?> updateProfile(@PathVariable("id") Long id,
                                           @RequestBody @Valid ProfileCreateDto dto){
        String result = profileService.updateProfile(id, dto);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/secured/delete/{id}")
    public ResponseEntity<?> deleteProfile(@PathVariable("id") Long id){
        String result = profileService.deleteProfile(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/delete/{id}")
    public ResponseEntity<?> softDeleteProfile(@PathVariable("id") Long id){
        String result = profileService.softDelete(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/byId/{id}")
    public ResponseEntity<?> getProfileById(@PathVariable("id") Long id){
        ProfileDto result = profileService.getProfileDto(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/byEmail/{email}")
    public ResponseEntity<?> getProfileByEmail(@PathVariable("email") String email){
        ProfileDto result = profileService.getProfileByEmail(email);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/all")
    public ResponseEntity<?> getAllProfiles(@RequestParam("page") int page,
                                            @RequestParam("size") int size){
        List<ProfileDto> result = profileService.getAllProfiles(page, size);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("/secured/{id}/status/{status}")
    public ResponseEntity<?> changeProfileStatus(@PathVariable("id") Long id,
                                                 @PathVariable("status") String status){
        String result = profileService.changeProfileStatus(id, status);
        return ResponseEntity.ok(result);
    }

    @PatchMapping("secured/{id}/role/{role}")
    public ResponseEntity<?> changeProfileRole(@PathVariable("id") Long id,
                                               @PathVariable("role") String role) {
        String result = profileService.changeProfileRole(id, role);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/secured/count")
    public ResponseEntity<?> countProfiles(){
        Long result = profileService.countProfiles();
        return ResponseEntity.ok(result);
    }

    //todo: filter
//////////////////////////*USER, MODERATOR*////////////////////////////////////////
    @GetMapping("/info")
    public ResponseEntity<?> getProfileInfo(){
        ProfileDto result = profileService.getProfileDto(SecurityUtil.getProfileId());
        return ResponseEntity.ok(result);
    }

    @PutMapping("/update}")
    public ResponseEntity<?> updateProfile(@RequestBody @Valid ProfileCreateDto dto){
        String result = profileService.updateProfile(SecurityUtil.getProfileId(), dto);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/delete")
    public ResponseEntity<?> deleteProfile(){
        String result = profileService.softDelete(SecurityUtil.getProfileId());
        return ResponseEntity.ok(result);
    }
}
