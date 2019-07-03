package ro.utcn.sd.a3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.a3.entity.AppUser;
import ro.utcn.sd.a3.exception.NotFoundException;
import ro.utcn.sd.a3.repository.AppUserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AppUserDetailsService implements UserDetailsService {
    private final AppUserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = repository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException("Unknown user!"));
        return new User(appUser.getName(), appUser.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_TEACHER")));
    }

    @Transactional
    public AppUser loadCurrentAppUser() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return repository.findByName(name).orElseThrow(NotFoundException::new);
    }
}
