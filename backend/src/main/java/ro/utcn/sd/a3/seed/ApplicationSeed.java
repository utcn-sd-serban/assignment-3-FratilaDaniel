package ro.utcn.sd.a3.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.a3.entity.Grade;
import ro.utcn.sd.a3.entity.Question;
import ro.utcn.sd.a3.entity.Student;
import ro.utcn.sd.a3.entity.Teacher;
import ro.utcn.sd.a3.repository.GradeRepository;
import ro.utcn.sd.a3.repository.QuestionRepository;
import ro.utcn.sd.a3.repository.StudentRepository;
import ro.utcn.sd.a3.repository.TeacherRepository;

import java.time.LocalDate;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ApplicationSeed implements CommandLineRunner {
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;
    private final GradeRepository gradeRepository;
    private final PasswordEncoder passwordEncoder;


    private final QuestionRepository questionRepository;
    @Override
    @Transactional
    public void run(String... args) {
        Calendar c1 = Calendar.getInstance();
        c1.set(2015, 3, 7);

        Calendar c2 = Calendar.getInstance();
        c2.set(Calendar.YEAR, 2019);
        c2.set(Calendar.MONTH, 11);
        c2.set(Calendar.DATE, 3);

        Calendar c3 = Calendar.getInstance();
        c3.set(Calendar.YEAR, 2018);
        c3.set(Calendar.MONTH, 0);
        c3.set(Calendar.DATE, -1);

        Date date1 = c1.getTime();
        Date date2 = c2.getTime();
        Date date3 = c3.getTime();


        List<Question> questions= Arrays.asList(
                new Question(1,
                        "Do dogs eat cats?",
                        "DummyText1",
                        date1),
                new Question(1,
                        "Do birds fly?",
                        "DummyText2",
                        date2),
                new Question(3,
                        "Do cats fly?",
                        "DummyText1",
                        date3)
        );
        questionRepository.saveAll(questions);


        Teacher serban = new Teacher(0, "serban", passwordEncoder.encode("password"));
        teacherRepository.save(serban);

        Student john = new Student(0, "John", "Doe", new LinkedList<>());
        studentRepository.save(john);
        Student jack = new Student(0, "Jack", "Black", new LinkedList<>());
        studentRepository.save(jack);
        Student jane = new Student(0, "Jane", "White", new LinkedList<>());
        studentRepository.save(jane);

        List<Grade> grades = Arrays.asList(
                new Grade(0, 5, LocalDate.now(), serban),
                new Grade(0, 6, LocalDate.now(), serban),
                new Grade(0, 7, LocalDate.now(), serban)
        );
        gradeRepository.saveAll(grades);

        john.setGrades(grades);
    }

    @Transactional
    public void clear() {
        gradeRepository.deleteAll();
        studentRepository.deleteAll();
        teacherRepository.deleteAll();
        questionRepository.deleteAll();
    }
}
