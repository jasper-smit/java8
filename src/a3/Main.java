package a3;


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

        /* Opgave 3
             Niet alle weken beginnen op zondag, print whitespace als de week niet op zondag begint,
             zodat dezelfde dag van de week altijd op dezelfde plek wordt geprint
             pas de functie weekToString hiervoor aan
         */
        List<LocalDate> week = Stream.iterate(LocalDate.of(2015, 12, 1), date -> date.plusDays(1))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(weekToString(week)); //Print "        1  2  3  4  5"
    }

    private static String weekToString(List<LocalDate> week) {
        return week.stream()
                .map(LocalDate::getDayOfMonth)
                .map(dayOfMonth -> String.format("%3d", dayOfMonth))
                .collect(Collectors.joining());
    }
}

