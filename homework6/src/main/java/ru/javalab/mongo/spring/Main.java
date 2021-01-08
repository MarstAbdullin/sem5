package ru.javalab.mongo.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(SimpleMongoConfig.class);
        MongoTemplate template = context.getBean(MongoTemplate.class);

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

        Troupe bolshoy = Troupe.builder()
                .title("Bolshoy")
                .inAct(true)
                .plannedCitiesNum(9)
                .peopleInGroupe(10)
                .genres(Arrays.asList("Light game", "Drama"))
                .keywords(Arrays.asList("Light game", "Drama", "fantasy" +
                        ""))
                .description("the best balley troupe")
                .build();

        Troupe mariinsky = Troupe.builder()
                .title("Mariinsky")
                .plannedCitiesNum(12)
                .peopleInGroupe(13)
                .genres(Arrays.asList("Light game", "Drama", "Psyho"))
                .inAct(false)
                .keywords(Arrays.asList("Psyho", "Light game", "fantasy"))
                .members(Arrays.asList("Artem", "Tolik", "Vlad"))
                .build();

        template.insert(histrionic1, "histrionics");
        template.insert(histrionic2, "histrionics");

        template.insert(bolshoy, "troupes");
        template.insert(mariinsky, "troupes");

        List<Troupe> troupes = template.find(new Query(
                where("inAct").is(true)
                        .orOperator(where("keywords").is("Light game"),
                                where("plannedCitiesNum").gt(10))), Troupe.class, "troupes");
        System.out.println(troupes);

        bolshoy.setPlannedCitiesNum(15);
        template.save(bolshoy);

        List<Troupe> troupes1 = template.find(new Query(
                where("inAct").is(true)
                        .orOperator(where("keywords").is("Light game"),
                                where("plannedCitiesNum").gt(10))), Troupe.class, "troupes");
        System.out.println(troupes1);

        template.dropCollection("troupes");
        template.dropCollection("histrionics");
    }
}
