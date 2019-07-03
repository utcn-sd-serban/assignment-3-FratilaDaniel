package ro.utcn.sd.a3.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcn.sd.a3.entity.AppUser;
import ro.utcn.sd.a3.service.AppUserDetailsService;

@RestController
@RequiredArgsConstructor
public class AppUserController {
    private final AppUserDetailsService service;

    @GetMapping("/me")
    public AppUser readCurrent() {
        return service.loadCurrentAppUser();
    }
}
