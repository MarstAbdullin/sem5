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
public class Histrionic implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "city_name", referencedColumnName = "name")
    private City city;

    @ManyToMany
    @JoinTable(name = "troupe_histrionic",
            joinColumns = @JoinColumn(name = "histrionic_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "troupe_id", referencedColumnName = "id"))
    private List<Troupe> troupes;

}