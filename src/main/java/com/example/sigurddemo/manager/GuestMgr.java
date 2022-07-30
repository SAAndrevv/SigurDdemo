package com.example.sigurddemo.manager;

import com.example.sigurddemo.additional.RandomCardGeneration;
import com.example.sigurddemo.additional.VirtualDate;
import com.example.sigurddemo.model.Employee;
import com.example.sigurddemo.model.Guest;
import com.example.sigurddemo.service.GuestServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
@EnableScheduling
@ComponentScan
public class GuestMgr {

    private final String TYPE = "GUEST";
    private final int BYTES = 16;

    @NonNull
    private final GuestServiceImpl guestService;
    @NonNull
    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Random rnd = new Random();
    
    public void makingAnAppointment(Employee employee, Date date) {
        if (rnd.nextBoolean()) {
            Date visitDate = VirtualDate.generateRandomDateInInterval(employee.getHireTime(), Calendar.MONTH, 6);
            Guest guest = new Guest();

            try {
                guest.setVisitDate(visitDate);
                guest.setPerson(employee);
                guest.setType(TYPE);
                guest.setCard(RandomCardGeneration.randomGeneration(BYTES));
                guestService.save(guest);
            } catch (DataIntegrityViolationException de) {
                System.out.println(de);
                return;
            }

            logger.info("{}", messageSource.getMessage("guest.appointed",
                    new Object[] {guest.getId(),
                            employee.getId(),
                            employee.getDepartment().getId(),
                            guest.getVisitDate(),
                            VirtualDate.dateDifferenceInDays(date, guest.getVisitDate())},
                    null, null));

        }
    }

    public void cancelGuestMeeting(Employee employee, Date date) {
        try {
            Guest guest = guestService.deleteGuestByEmployeeId(employee);
            if (guest != null) {
                logger.info("{}", messageSource.getMessage("guest.canceled",
                        new Object[] {guest.getId(),
                                employee.getId(),
                                employee.getDepartment().getId(),
                                guest.getVisitDate(),
                                employee.getFiredTime()},
                        null, null));
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
