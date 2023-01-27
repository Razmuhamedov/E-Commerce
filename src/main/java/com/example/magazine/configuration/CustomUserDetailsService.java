package com.example.magazine.configuration;

import com.example.magazine.entity.Profile;
import com.example.magazine.repository.ProfileRepo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;


@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final ProfileRepo profileRepo;

    public CustomUserDetailsService(ProfileRepo profileRepo) {
        this.profileRepo = profileRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Income: loadUserByUsername");
        Optional<Profile> optional =this.profileRepo.findByEmailAndDeletedAtIsNull(email);
        optional.orElseThrow(() -> new UsernameNotFoundException("Email not found!"));

        Profile profile =optional.get();
        System.out.println(profile);

        return new CustomUserDetails(profile);
    }
}
