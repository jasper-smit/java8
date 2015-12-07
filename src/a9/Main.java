package a9;

import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    /*
      Het einde!

      BONUSOPGAVE: Print de kalenders in een 4 bij 3 grid.

     */

    public static void main(String[] args) {
        LocalDate start = LocalDate.of(2015, 1, 1);

        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        Map<Integer, Map<Long, List<LocalDate>>> grouped = Stream.iterate(start, date -> date.plusDays(1))
                .limit(365)
                .collect(Collectors.groupingBy(LocalDate::getMonthValue, TreeMap::new,
                        Collectors.groupingBy(date -> date.getLong(weekFields.weekOfYear()), TreeMap::new, Collectors.toList())));

        String strings = grouped.keySet().stream()
                .flatMap(month -> monthToStrings(month, grouped.get(month)))
                .collect(Collectors.joining("\n"));

        System.out.println(strings);

    }

    private static Stream<String> monthToStrings(int monthNumber, Map<Long, List<LocalDate>> month) {
        List<String> monthNames = Arrays.asList("Januari", "Februari", "Maart", "April", "Mei", "Juni", "Juli", "Augustus", "September",
                "Oktober", "November", "December");
        return Stream.concat(
                Stream.of(monthNames.get(monthNumber - 1)),
                month.values().stream().map(Main::weekToString)
        );
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
