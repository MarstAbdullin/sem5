package ru.javalab.homework1.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDto implements Serializable {

    private String name;
    private String surname;
    private Integer passportSeries;
    private Integer passportNumber;
    private Date date;

}
