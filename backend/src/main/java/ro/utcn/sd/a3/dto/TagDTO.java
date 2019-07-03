package ro.utcn.sd.a3.dto;


import lombok.Data;
import ro.utcn.sd.a3.entity.Tag;


@Data
public class TagDTO {

    private int id;
    private String description;

    public static TagDTO ofEntity(Tag tag){
        TagDTO questionDTO = new TagDTO();
        questionDTO.setId(tag.getId());
        questionDTO.setDescription(tag.getDescription());
        return questionDTO;
    }
}
