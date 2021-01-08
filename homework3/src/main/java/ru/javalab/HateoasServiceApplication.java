package ru.javalab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.javalab.models.Troupe;
import ru.javalab.models.City;
import ru.javalab.models.Histrionic;
import ru.javalab.models.Country;
import ru.javalab.models.Genre;
import ru.javalab.models.Member;
import ru.javalab.repositories.TroupeRepository;
import ru.javalab.repositories.CityRepository;
import ru.javalab.repositories.HistrionicRepository;
import ru.javalab.repositories.CountryRepository;
import ru.javalab.repositories.GenreRepository;
import ru.javalab.repositories.MemberRepository;

import static java.util.Arrays.asList;

@SpringBootApplication
public class HateoasServiceApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasServiceApplication.class, args);

        TroupeRepository troupeRepository = context.getBean(TroupeRepository.class);
        CityRepository cityRepository = context.getBean(CityRepository.class);
        HistrionicRepository histrionicRepository = context.getBean(HistrionicRepository.class);
        CountryRepository countryRepository = context.getBean(CountryRepository.class);
        GenreRepository genreRepository = context.getBean(GenreRepository.class);
        MemberRepository memberRepository = context.getBean(MemberRepository.class);

        Troupe bolshoy = Troupe.builder()
                .description("Best comedy troupe")
                .state("Planned")
                .name("Bolshoy theater")
                .build();

        Troupe mariinsky = Troupe.builder()
                .description("Best neoclassic troupe")
                .name("Mariinsky theatre")
                .state("Planned")
                .build();

        Troupe alexandrinsky = Troupe.builder()
                .description("Best classic troupe")
                .name("Alexandrinsky theater")
                .state("Deleted")
                .build();

        Troupe satire = Troupe.builder()
                .description("Satire troupe")
                .name("Moscow Academic Theater of Satire")
                .state("Deleted")
                .build();

        troupeRepository.saveAll(asList(bolshoy, mariinsky, alexandrinsky, satire));

        Genre comedy = Genre.builder()
                .name("Comedy")
                .troupes(asList(bolshoy, satire))
                .build();

        Genre classic = Genre.builder()
                .name("Classic")
                .troupes(asList(mariinsky, alexandrinsky))
                .build();

        genreRepository.saveAll(asList(comedy, classic));

        Member marat = Member.builder()
                .name("Маrat")
                .role("artist")
                .troupe(bolshoy)
                .build();

        Member rinat = Member.builder()
                .name("Rinat")
                .role("manager")
                .troupe(bolshoy)
                .build();

        Member vlad = Member.builder()
                .name("Vlad")
                .role("makeupman")
                .troupe(mariinsky)
                .build();

        Member marsel = Member.builder()
                .name("Marsel")
                .role("dresser")
                .troupe(mariinsky)
                .build();

        memberRepository.saveAll(asList(marat, rinat, vlad, marsel));

        Country rossia = Country.builder()
                .name("Rossia")
                .build();

        Country kazahstan = Country.builder()
                .name("Kazahstan")
                .build();

        countryRepository.saveAll(asList(rossia, kazahstan));

        City moskwa = City.builder()
                .name("Moskwa")
                .country(rossia)
                .build();

        City piter = City.builder()
                .name("Piter")
                .country(rossia)
                .build();

        City astana = City.builder()
                .name("Astana")
                .country(kazahstan)
                .build();

        City kazan = City.builder()
                .name("Kazan")
                .country(kazahstan)
                .build();

        cityRepository.saveAll(asList(astana, kazan, moskwa, piter));

        Histrionic histrionic1 = Histrionic.builder()
                .name("Bal vorov")
                .city(astana)
                .troupes(asList(bolshoy, mariinsky))
                .build();

        Histrionic histrionic2 = Histrionic.builder()
                .name("Nedorosl")
                .city(kazan)
                .troupes(asList(bolshoy, satire))
                .build();

        histrionicRepository.saveAll(asList(histrionic1, histrionic2));
    }
}
