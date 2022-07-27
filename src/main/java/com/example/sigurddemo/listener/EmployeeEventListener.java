package com.example.sigurddemo.listener;

import com.example.sigurddemo.event.AbstractEmployeeEvent;
import com.example.sigurddemo.event.FiringEmployeeEvent;
import com.example.sigurddemo.event.HiringEmployeeEvent;
import com.example.sigurddemo.manager.GuestMgr;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeEventListener implements ApplicationListener<AbstractEmployeeEvent> {

    @NonNull
    private GuestMgr guestMgr;

    @Override
    public void onApplicationEvent(AbstractEmployeeEvent event) {
        if (event instanceof HiringEmployeeEvent) {
            guestMgr.makingAnAppointment(event.getEmployee());
        }
        else if (event instanceof FiringEmployeeEvent) {
            guestMgr.cancelGuestMeeting(event.getEmployee());
        }
    }
}
