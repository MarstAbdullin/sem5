package ru.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Troupe implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String description;
    private String state;

    @OneToMany(mappedBy = "troupe")
    private List<Member> members;

    public void publish() {
        if (this.state.equals("Planned")) {
            this.state = "Published";
        } else if (this.state.equals("Deleted")) {
            throw new IllegalStateException();
        }
    }

    @ManyToMany(mappedBy = "troupes")
    private List<Histrionic> histrionics;

    @ManyToMany(mappedBy = "troupes")
    private List<Genre> genres;

}
