package a2;


import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2016, 1, 1);

        Stream.iterate(start, date -> date.plusDays(1))
                .limit(366)
                .forEach(System.out::println);

        /* Opgave 2
             Schrijf een functie weekToString die het volgende doet:
               - Gegeven een lijst van LocalDate, retourneer de dagen van de maand in een string.
               - Format een dag met String.format("%3d", day)
         */
        List<LocalDate> week = Stream.iterate(LocalDate.of(2015, 12, 6), date -> date.plusDays(1))
                .limit(7)
                .collect(Collectors.toList());
        System.out.println(weekToString(week)); //Print "  6  7  8  9 10 11 12"
    }

    private static String weekToString(List<LocalDate> week) {
        return "";
    }
}
