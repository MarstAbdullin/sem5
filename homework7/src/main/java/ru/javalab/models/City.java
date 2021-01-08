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
public class City implements Serializable {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany
    private List<Histrionic> histrionics;

    @ManyToOne
    @JoinColumn(name = "country_name", referencedColumnName = "name")
    private Country country;

    @ManyToMany
    @JoinTable(name = "troupe_city",
            joinColumns = @JoinColumn(name = "city_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "troupe_id", referencedColumnName = "id"))
    private List<Troupe> troupe;
}
