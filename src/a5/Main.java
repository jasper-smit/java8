package a5;


import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2016, 1, 1);
        Map<Integer, List<LocalDate>> grouped =
                Stream.iterate(start, date -> date.plusDays(1))
                        .limit(366)
                        .collect(Collectors.groupingBy(LocalDate::getMonthValue, TreeMap::new, Collectors.toList()));

        /* Opgave 5
             Groepeer verder per week
             - Gebruik: Map<Integer, Map<Long, List<LocalDate>>>
             - Eerste key is de maand, tweede key is de week.
             - Gebruik weer een gesorteerde map
             - Maak een helper 'WeekField':  WeekFields weekFields = WeekFields.of(Locale.getDefault());
             - Je kan nu een week opvragen met: date.getLong(weekFields.weekOfYear())
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

