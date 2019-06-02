package ro.utcn.sd.a3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"id", "authroId", "title", "text"})
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer authorId;
    private String title;
    private String text;
    //@ToString.Exclude
    private Date creationDate;
    //private List<String> tags;

//
//    public Question(int authorId, String title, String text, Calendar creationDate/*, List<String> tags*/) {
//        this.authorId = authorId;
//        this.title = title;
//        this.text = text;
//        this.creationDate = creationDate;
//        //this.tags
//        = tags;
//    }

    public Question(int authorId, String title, String text, java.util.Date creationDate) {
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
        //this.tags = tags;
    }

    public Question(int authorId, String title, String text, String date) {
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        //Calendar calendar = new BuddhistCalendar();

        try {
            this.creationDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //this.tags = tags;
    }


    public Question(int authorId, String title, String text, int year, int month, int day) {
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        String date = day + "/" + month + "/" + year;
        try {
            this.creationDate = new SimpleDateFormat("dd/mm/yyyy").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //this.tags = tags;
    }

//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
//    private String firstName;
//    private String lastName;
//    @OneToMany(fetch = FetchType.EAGER)
//    @JoinColumn
//    private List<Grade> grades;
}