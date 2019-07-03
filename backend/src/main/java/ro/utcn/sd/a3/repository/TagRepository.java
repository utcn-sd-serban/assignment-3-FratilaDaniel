package ro.utcn.sd.a3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.sd.a3.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Integer> {
    Tag findByDescription(String description);
}
