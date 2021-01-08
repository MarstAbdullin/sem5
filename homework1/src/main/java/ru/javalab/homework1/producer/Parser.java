package ru.javalab.homework1.producer;

import ru.javalab.homework1.dto.DocumentDto;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Parser {


    private static SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");


    public static DocumentDto parse(String string) {
        String[] data = string.split(" ");
        if (isValid(data)) {
            try {
                return DocumentDto.builder()
                        .name(data[0])
                        .surname(data[1])
                        .passportSeries(Integer.valueOf(data[2]))
                        .passportNumber(Integer.valueOf(data[3]))
                        .date(format.parse(data[4]))
                        .build();
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        } else throw new IllegalStateException();


    }

    private static boolean isValid(String[] data) {
        if (data.length != 5) {
            return false;
        }
        try {
            Integer series = Integer.valueOf(data[2]);
            Integer number = Integer.valueOf(data[3]);
            format.parse(data[4]);
        } catch (NumberFormatException | ParseException e) {
            System.out.println(e);
            return false;
        }
        return true;
    }
}
