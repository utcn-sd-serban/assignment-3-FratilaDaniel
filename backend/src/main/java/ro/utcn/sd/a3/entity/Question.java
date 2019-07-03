package ro.utcn.sd.a3.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
//@ToString(of = {"id", "authorId", "title", "text"})
public class Question{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer authorId;
    private String title;
    private String text;
    //@ToString.Exclude
    private Date creationDate;

    public Question(int authorId, String title, String text, Date creationDate) {
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.creationDate = creationDate;
    }
}