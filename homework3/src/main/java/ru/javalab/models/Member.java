package ru.javalab.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Member {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String role;

    @ManyToOne
    @JoinColumn(name = "troupe_name", referencedColumnName = "name")
    private Troupe troupe;
}
