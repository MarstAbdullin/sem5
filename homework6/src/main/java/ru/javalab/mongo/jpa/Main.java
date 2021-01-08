package ru.javalab.mongo.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepositoriesConfig.class);
        TroupesRepository troupesRepository = context.getBean(TroupesRepository.class);
        HistrionicsRepository histrionicsRepository = context.getBean(HistrionicsRepository.class);

        Histrionic histrionic1 = Histrionic.builder()
                .title("Satire theatre")
                .city("Moskwa")
                .genres(Arrays.asList("Light game", "Psyho", "druag", "sound game"))
                .build();

        Histrionic histrionic2 = Histrionic.builder()
                .title("Kachalov Theatre")
                .city("Kazan")
                .genres(Arrays.asList("Light game", "Psyho", "druag", "sound game"))
                .build();

        histrionicsRepository.save(histrionic1);
        histrionicsRepository.save(histrionic2);

        Troupe bolshoy = Troupe.builder()
                .title("Bolshoy")
                .inAct(true)
                .plannedCitiesNum(9)
                .peopleInGroupe(10)
                .genres(Arrays.asList("Light game", "Drama"))
                .keywords(Arrays.asList("Light game", "Drama", "fantasy"))
                .description("the best balley troupe")
                .build();

        Troupe mariinsky = Troupe.builder()
                .title("Mariinsky")
                .plannedCitiesNum(12)
                .peopleInGroupe(13)
                .genres(Arrays.asList("Light game", "Drama", "Psyho"))
                .inAct(true)
                .keywords(Arrays.asList("Psyho", "Light game", "fantasy"))
                .members(Arrays.asList("Artem", "Tolik", "Vlad"))
                .build();

        List<Histrionic> histrionics = Arrays.asList(histrionic1, histrionic2);
        bolshoy.setHistrionics(histrionics);
        mariinsky.setHistrionics(histrionics);

        troupesRepository.insert(bolshoy);
        troupesRepository.insert(mariinsky);

        mariinsky.setPlannedCitiesNum(13);

        troupesRepository.save(mariinsky);

        System.out.println(troupesRepository.find(Arrays.asList("Light game"), 10));

    }
}
