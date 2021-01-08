package ru.javalab.mongo.jpa;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Document(collection = "troupes")
public class Troupe {

    @Id
    private String _id;

    private Integer plannedCitiesNum;
    private Integer peopleInGroupe;
    private String title;
    private String state;
    private String description;
    private Boolean inAct;

    private List<String> genres;
    private List<String> keywords;
    private List<String> members;

    @DBRef
    private List<Histrionic> histrionics;

}
