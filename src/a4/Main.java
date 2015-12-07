package a4;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2016, 1, 1);
        Map<Integer, List<LocalDate>> grouped;
        Stream.iterate(start, date -> date.plusDays(1))
                .limit(366);
         /* Opgave 4
             - Groepeer de dagen per maand in de bovenstaande map. De key is de maand en de values zijn de dagen van de maand
             - Let op: Gebruik een gesorteerde map!
         */

        List<LocalDate> week = Stream.iterate(LocalDate.of(2015, 12, 1), date -> date.plusDays(1))
                .limit(5)
                .collect(Collectors.toList());
        System.out.println(weekToString(week)); //Print "        1  2  3  4  5"
    }

    private static String weekToString(List<LocalDate> week) {
        int weekStartDay = (week.get(0).getDayOfWeek().getValue()) % 7;
        return
                Stream.concat(
                        Collections.nCopies(weekStartDay, "   ").stream(),
                        week
                                .stream()
                                .map(LocalDate::getDayOfMonth)
                                .map(dayOfMonth -> String.format("%3d", dayOfMonth)))

                        .collect(Collectors.joining());
    }
}

