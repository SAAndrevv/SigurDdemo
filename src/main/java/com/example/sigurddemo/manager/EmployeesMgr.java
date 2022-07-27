package com.example.sigurddemo.manager;

import com.example.sigurddemo.additionalBeans.RandomCardGeneration;
import com.example.sigurddemo.additionalBeans.VirtualDate;
import com.example.sigurddemo.event.FiringEmployeeEvent;
import com.example.sigurddemo.event.HiringEmployeeEvent;
import com.example.sigurddemo.exception.DateException;
import com.example.sigurddemo.model.Department;
import com.example.sigurddemo.model.Employee;
import com.example.sigurddemo.service.DepartmentServiceImpl;
import com.example.sigurddemo.service.EmployeeServiceImpl;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;




@Component
@RequiredArgsConstructor
@EnableScheduling
@ComponentScan
public class EmployeesMgr {

    private final int BYTES = 16;
    private final String TYPE = "EMPLOYEE";

    @NonNull
    private final EmployeeServiceImpl employeeService;
    @NonNull
    private final DepartmentServiceImpl departmentService;
    @NonNull
    private final MessageSource messageSource;
    @NonNull
    private final VirtualDate virtualDate;
    @NonNull
    private ApplicationEventPublisher applicationEventPublisher;

    private Date nowVirtualDate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int counter = 0;

    @Scheduled(fixedDelay = 1000)
    private void generateVirtualDay() throws DateException {
        nowVirtualDate = virtualDate.getDate();

        counter++;
        if (counter == 5) {
            firingEmployees();
            counter = 0;
        } else {
            hiringEmployees();
        }

        virtualDate.nextDay();
    }

    private void hiringEmployees() {

        Employee employee = new Employee();

        try {
            employee.setCard(RandomCardGeneration.randomGeneration(BYTES));
            employee.setHireTime(nowVirtualDate);
            employee.setType(TYPE);
            try {
                if (departmentService.getRandomDepartment().isPresent()) {
                    Department department = departmentService.getRandomDepartment().get();
                    employee.setDepartment(department);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
            employeeService.save(employee);
        } catch (DataIntegrityViolationException de) {
            System.out.println(de);
            return;
        }

        HiringEmployeeEvent hiringEmployeeEvent = new HiringEmployeeEvent(this, employee);
        applicationEventPublisher.publishEvent(hiringEmployeeEvent);

        logger.info("{}", messageSource.getMessage("employee.hiring",
                new Object[] {virtualDate.getDate(), employee.getId(), employee.getHireTime(), employee.getDepartment().getId()}, null, null));
    }

    private void firingEmployees() {
        employeeService.getSpecificQuantityRandomEmployee().forEach(employee -> {
            employee.setFiredTime(nowVirtualDate);
            employeeService.save(employee);

            logger.info("{}", messageSource.getMessage("employee.firing",
                    new Object[] {virtualDate.getDate(),
                            employee.getId(),
                            employee.getFiredTime(),
                            employee.getDepartment().getId(),
                            VirtualDate.dateDifferenceInDays(employee.getHireTime(), nowVirtualDate)},
                    null, null));

            FiringEmployeeEvent firingEmployeeEvent = new FiringEmployeeEvent(this, employee);
            applicationEventPublisher.publishEvent(firingEmployeeEvent);
        }
        );
    }


}
