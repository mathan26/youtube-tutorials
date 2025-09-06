package com.mathan.java8.tutorials.date;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        System.out.println("---------LocalDate-----------------");

        LocalDate today  = LocalDate.now();
        System.out.println(today);

        LocalDate someDate = LocalDate.of(2025, 01, 10);
        System.out.println(someDate);

        LocalDate parsed = LocalDate.parse("2025-09-10");
        System.out.println(parsed);

        System.out.println(parsed.plusDays(10));


        System.out.println("---------LocalTime-----------------");


        LocalTime now = LocalTime.now();
        System.out.println(now);

        LocalDateTime dateTime = LocalDateTime.now();

        System.out.println(dateTime);

        System.out.println("---------Instant-----------------");

        Instant nowInstant = Instant.now();
        System.out.println(nowInstant);

        System.out.println("---------ZonedDateTime and ZoneId-----------------");

        ZoneId kolkata = ZoneId.of("Asia/Kolkata");
        System.out.println(kolkata);

        ZonedDateTime zonedDateTime = ZonedDateTime.now(kolkata);
        System.out.println(zonedDateTime);

        ZoneId newYork = ZoneId.of("America/New_York");
        ZonedDateTime zonedDateTimeAmerica = ZonedDateTime.now(newYork);
        System.out.println(zonedDateTimeAmerica);

        System.out.println("---------Period  and Duration-----------------");

        LocalDate start = LocalDate.of(2020, 1, 1);
        LocalDate end = LocalDate.of(2025, 9, 6);
        Period age = Period.between(start, end);
        System.out.println("Period:"+ age);
        Instant i1 = Instant.now();
        Instant i2 = i1.plusSeconds(5400);
        Duration d = Duration.between(i1, i2);
        System.out.println("Duration seconds: " + d.toSeconds());

        System.out.println("---------Formatting and Parsing-----------------");

        LocalDate today1 = LocalDate.now();
        System.out.println(today1);

        DateTimeFormatter formatedDate = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(formatedDate.format(today1));

    }
}
