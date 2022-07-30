package com.example.sigurddemo.listener;

import com.example.sigurddemo.event.DateEvent;
import com.example.sigurddemo.manager.EmployeesMgr;
import com.example.sigurddemo.manager.GuestMgr;
import com.example.sigurddemo.manager.PassEmulator;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DateEventListener implements ApplicationListener<DateEvent> {

    @NonNull
    private final EmployeesMgr employeesMgr;

    @NonNull
    private final PassEmulator passEmulator;

    @Override
    public void onApplicationEvent(DateEvent event) {
        employeesMgr.newVirtualDate(event.getDate());
        passEmulator.newVirtualDate(event.getDate());

    }
}
