package com.example.sigurddemo.manager;


import com.example.sigurddemo.additionalBeans.RandomCardGeneration;
import com.example.sigurddemo.model.Employee;
import com.example.sigurddemo.model.Guest;
import com.example.sigurddemo.service.EmployeeServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;

@Component
@RequiredArgsConstructor
public class PassEmulator {
    private final int BYTES = 16;
    private Date nowVirtualDate;
    @NonNull
    private final EmployeeServiceImpl employeeService;
    @NonNull
    private Session session;
    @NonNull
    private final MessageSource messageSource;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Random rnd = new Random();

    public void newVirtualDate(Date date) {
        nowVirtualDate = date;

        for (int gen = 0; gen < 10; gen++) {
            byte[] cardGen = null;

            if (rnd.nextInt(5) == 0) {
                cardGen = RandomCardGeneration.randomGeneration(BYTES);

                try {
                    Object person = getPersonByCard(cardGen);
                    typeDefinitionPerson(person);

                } catch (IndexOutOfBoundsException iob) {
                    logger.info("{}", messageSource.getMessage("access.unknown",
                            new Object[] {cardGen}, null, null));
                }
            } else {
                Query query = session.createQuery("FROM Person ORDER BY function('RAND')");

                try {
                    Object person = query.list().get(0);
                    typeDefinitionPerson(person);
                } catch (IndexOutOfBoundsException iob) {
                    System.out.println("No person in db");
                }
            }
        }
    }

    private Object getPersonByCard(byte [] card) throws IndexOutOfBoundsException {
        Query query = session.createQuery("FROM Person WHERE card = :cardData");
        query.setParameter("cardData", card);

        return query.list().get(0);
    }

    private void typeDefinitionPerson(Object obj) {
        if (obj instanceof Employee) {
            checkEmployeeAccess((Employee) obj);
        } else if (obj instanceof Guest) {
            checkGuestAccess((Guest) obj);
        }
    }

    private void checkEmployeeAccess(Employee employee) {
        if (employee.getFiredTime() != null) {
            logger.info("{}", messageSource.getMessage("access.employee_hired",
                    new Object[] {nowVirtualDate, employee.getId(), employee.getDepartment().getName(), employee.getCard()}, null, null));
            return;
        }
        logger.info("{}", messageSource.getMessage("access.employee_fired",
                new Object[] {nowVirtualDate, employee.getId(), employee.getDepartment().getName(), employee.getCard()}, null, null));
    }

    private void checkGuestAccess(Guest guest) {
        if (guest.getVisitDate() != null) {
            Employee employee = employeeService.getEmployeeById(guest.getPerson().getId());
            logger.info("{}", messageSource.getMessage("access.guest_allow",
                    new Object[] {nowVirtualDate, guest.getId(), employee.getId(), employee.getDepartment().getId(), guest.getCard()}, null, null));
            return;
        }
        logger.info("{}", messageSource.getMessage("access.guest_denied",
                new Object[] {nowVirtualDate, guest.getId(), guest.getCard()}, null, null));
    }


}
