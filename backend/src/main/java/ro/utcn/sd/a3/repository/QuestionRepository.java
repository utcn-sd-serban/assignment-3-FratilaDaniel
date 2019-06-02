package ro.utcn.sd.a3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcn.sd.a3.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Integer> {
}
