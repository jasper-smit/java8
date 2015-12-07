package a7;


import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2016, 1, 1);
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        Map<Integer, Map<Long, List<LocalDate>>> grouped = Stream.iterate(start, date -> date.plusDays(1))
                .limit(365)
                .collect(Collectors.groupingBy(LocalDate::getMonthValue, TreeMap::new,
                        Collectors.groupingBy(date -> date.getLong(weekFields.weekOfYear()), TreeMap::new, Collectors.toList())));


        String strings = monthToStrings(1, grouped.get(1))
                .collect(Collectors.joining("\n"));
        System.out.println(strings);
    }

    /* Opgave 7
        Breid monthToStrings uit, dat ook de naam van de maand geprint wordt:
Januari
              1  2  3
  4  5  6  7  8  9 10
 11 12 13 14 15 16 17
 18 19 20 21 22 23 24
 25 26 27 28 29 30 31
     */
    private static Stream<String> monthToStrings(int monthNumber, Map<Long, List<LocalDate>> month) {
        return month.values().stream().map(Main::weekToString);
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

