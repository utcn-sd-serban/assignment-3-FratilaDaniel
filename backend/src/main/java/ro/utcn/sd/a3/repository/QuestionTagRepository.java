package ro.utcn.sd.a3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.sd.a3.entity.QuestionTag;

public interface QuestionTagRepository extends JpaRepository<QuestionTag, Integer> {
}
