package ro.utcn.sd.a3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.sd.a3.entity.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Integer> {
    Optional<AppUser> findByName(String name);
}
