package by.antohakon.testproject.test;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class TestStrwam {


    public static void main(String[] args) {

        TestStrwam test = new TestStrwam();

//        List<SeaCreature> seaCreatures = Arrays.asList(
//                test.new SeaCreature(
//                        "Dolphin",
//                        15,
//                        500,
//                        new Species(
//                                "Dolphin",
//                                1000)),
//                test.new SeaCreature(
//                        "Whale",
//                        500,
//                        1000,
//                        new Species(
//                                "Whale",
//                                500)),
//                test.new SeaCreature(
//                        "Shark",
//                        8,
//                        1000,
//                        new Species(
//                                "Shark",
//                                2000)),
//                test.new SeaCreature(
//                        "Dolphin",
//                        20,
//                        400,
//                        new Species(
//                                "Dolphin",
//                                1000))
//        );

        List<SeaCreature> seaCreatures2 = Arrays.asList(
                SeaCreature.builder()
                        .name("Dolphin")
                        .population(15)
                        .weight(500)
                        .species(
                        Species.builder()
                                .name("Dolphin")
                                .population(1000)
                                .build()
                        )
                        .build(),
                SeaCreature.builder()
                        .name("Whale")
                        .population(500)
                        .weight(1000)
                        .species(
                                Species.builder()
                                        .name("Whale")
                                        .population(500)
                                        .build()
                        )
                        .build(),
                SeaCreature.builder()
                        .name("Shark")
                        .population(8)
                        .weight(1000)
                        .species(
                                Species.builder()
                                        .name("Shark")
                                        .population(1000)
                                        .build()
                        )
                        .build(),
                SeaCreature.builder()
                        .name("Dolphin")
                        .population(20)
                        .weight(400)
                        .species(
                                Species.builder()
                                        .name("Dolphin")
                                        .population(1000)
                                        .build()
                        )
                        .build()
        );

        //test.printSpecies(seaCreatures);
        test.printSpecies(seaCreatures2);

    }

    public void printSpecies(List<SeaCreature> seaCreatures) {

        long timeStart = System.currentTimeMillis();
        seaCreatures.stream()
                .filter(sc -> {
                    System.out.println("filter: " + sc.getName());
                  return sc.getWeight() >= 1000;
                })
                .map(SeaCreature::getSpecies)
                .distinct()
                .sorted(Comparator.comparing(Species::getPopulation))
                .map(Species::getName)
                .forEach(System.out::println);
        long timeEnd = System.currentTimeMillis();
        System.out.println("time: " + (timeEnd - timeStart));

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Species {

        private String name;
        private int population;

    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class SeaCreature {

        private String name;
        private int population;
        private int weight;
        private Species species;

    }

}
