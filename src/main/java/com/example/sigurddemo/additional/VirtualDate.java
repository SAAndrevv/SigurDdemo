package com.example.sigurddemo.additional;

import com.example.sigurddemo.event.DateEvent;
import com.example.sigurddemo.exception.DateException;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class VirtualDate {
    @NonNull
    private ApplicationEventPublisher applicationEventPublisher;
    private static final Calendar virtualDate = new GregorianCalendar(2022, Calendar.JANUARY, 1);

    @Scheduled(fixedDelay = 1000)
    public void nextDay() throws DateException {
        virtualDate.add(Calendar.DAY_OF_MONTH, 1);
        if (virtualDate.get(Calendar.YEAR) == 2023) {
            throw new DateException(virtualDate.getTime());
        }

        DateEvent dateEvent = new DateEvent(this, virtualDate.getTime());
        applicationEventPublisher.publishEvent(dateEvent);
    }
    public Date getDate() {
        return virtualDate.getTime();
    }

    public static long dateDifferenceInDays(Date startDate, Date endDate) {
        LocalDate start = LocalDate.fromDateFields(startDate);
        LocalDate end = LocalDate.fromDateFields(endDate);
        Days days = Days.daysBetween(start, end);
        return days.getDays();
    }

    public static Date generateRandomDateInInterval(Date startDate, int field, int count) {
        Calendar endDate = Calendar.getInstance();
        Date date = null;

        do {
            endDate.setTime(startDate);
            endDate.add(field, count);
            long random = ThreadLocalRandom.current().nextLong(startDate.getTime(), endDate.getTime().getTime());
             date = new Date(random);
        } while (virtualDate.getTime().after(date));

        return date;
    }

}
