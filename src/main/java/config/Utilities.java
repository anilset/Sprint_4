package config;

import java.time.LocalDate;

public class Utilities {
        public static final String URL = "https://qa-scooter.praktikum-services.ru/";
        public static final LocalDate CURRENT_DATE = LocalDate.now();
        public static final String TODAY = String.valueOf(CURRENT_DATE);
        public static final String TOMORROW = String.valueOf(CURRENT_DATE.plusDays(1));
        public static final String YESTERDAY = String.valueOf(CURRENT_DATE.minusDays(1));
        public static final String NEXT_YEAR = String.valueOf(CURRENT_DATE.plusYears(1));

    }

