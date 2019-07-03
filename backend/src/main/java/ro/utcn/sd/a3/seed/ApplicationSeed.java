package ro.utcn.sd.a3.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.sd.a3.entity.Question;
import ro.utcn.sd.a3.entity.QuestionTag;
import ro.utcn.sd.a3.entity.Tag;
import ro.utcn.sd.a3.entity.AppUser;
import ro.utcn.sd.a3.repository.QuestionRepository;
import ro.utcn.sd.a3.repository.QuestionTagRepository;
import ro.utcn.sd.a3.repository.TagRepository;
import ro.utcn.sd.a3.repository.AppUserRepository;

import java.util.*;

@Component
@RequiredArgsConstructor
public class ApplicationSeed implements CommandLineRunner {
    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;


    private final QuestionRepository questionRepository;
    private final TagRepository tagRepository;
    private final QuestionTagRepository questionTagRepository;

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


        List<Question> questions = Arrays.asList(
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



        List<Tag> tags = Arrays.asList(new Tag("dogs"), new Tag("cats"), new Tag("eat"), new Tag("flight"));
        tagRepository.saveAll(tags);



        List<QuestionTag> questionTags = Arrays.asList(
                new QuestionTag(1,1),
                new QuestionTag(1,2),
                new QuestionTag(2,4),
                new QuestionTag(3,2));
        questionTagRepository.saveAll(questionTags);



        AppUser serban = new AppUser("serban", passwordEncoder.encode("password"));
        AppUser dani = new AppUser("dani", passwordEncoder.encode("pass"));

        appUserRepository.save(serban);
        appUserRepository.save(dani);

    }

    @Transactional
    public void clear() {
        appUserRepository.deleteAll();
        questionRepository.deleteAll();
    }
}
