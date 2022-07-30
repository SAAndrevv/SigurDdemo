package com.example.sigurddemo.manager;

import com.example.sigurddemo.additional.RandomCardGeneration;
import com.example.sigurddemo.additional.VirtualDate;
import com.example.sigurddemo.event.FiringEmployeeEvent;
import com.example.sigurddemo.event.HiringEmployeeEvent;
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
    private ApplicationEventPublisher applicationEventPublisher;

    private Date nowVirtualDate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private int counter = 0;

    public void newVirtualDate(Date date) {
        nowVirtualDate = date;

        counter++;
        if (counter == 5) {
            firingEmployees();
            counter = 0;
        } else {
            hiringEmployees();
        }
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
                System.out.println("Can't find department");
            }
            employeeService.save(employee);
        } catch (DataIntegrityViolationException de) {
            System.out.println("Cant' save an employee " + de);
            return;
        }

        HiringEmployeeEvent hiringEmployeeEvent = new HiringEmployeeEvent(this, employee, nowVirtualDate);
        applicationEventPublisher.publishEvent(hiringEmployeeEvent);

        logger.info("{}", messageSource.getMessage("employee.hiring",
                new Object[] {nowVirtualDate, employee.getId(), employee.getHireTime(), employee.getDepartment().getName()}, null, null));
    }

    private void firingEmployees() {
        employeeService.getSpecificQuantityRandomEmployee().forEach(employee -> {
            if (checkFiringAfterHiring(employee)) {
                employee.setFiredTime(nowVirtualDate);
                employeeService.save(employee);

                logger.info("{}", messageSource.getMessage("employee.firing",
                        new Object[] {nowVirtualDate,
                                employee.getId(),
                                employee.getFiredTime(),
                                employee.getDepartment().getName(),
                                VirtualDate.dateDifferenceInDays(employee.getHireTime(), nowVirtualDate)},
                        null, null));

                FiringEmployeeEvent firingEmployeeEvent = new FiringEmployeeEvent(this, employee, nowVirtualDate);
                applicationEventPublisher.publishEvent(firingEmployeeEvent);
            } else {
                System.out.println("Can't firing employee");
            }
        }
        );
    }

    private boolean checkFiringAfterHiring(Employee employee) {
        return employee.getHireTime().before(nowVirtualDate);
    }

}
