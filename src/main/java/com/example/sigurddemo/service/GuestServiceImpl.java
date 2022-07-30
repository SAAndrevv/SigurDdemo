package com.example.sigurddemo.service;

import com.example.sigurddemo.exception.SqlTransactionException;
import com.example.sigurddemo.model.Guest;
import com.example.sigurddemo.model.Person;
import com.example.sigurddemo.repository.GuestRepository;
import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class GuestServiceImpl extends AbstractCommonPersonService<Guest, GuestRepository>{

    public GuestServiceImpl(@NonNull GuestRepository repository) {
        super(repository);
    }

    public Guest deleteGuestByEmployeeId(Person person) throws Exception {
        Guest guest = repository.findGuestByPerson(person).orElse(null);
        if (guest != null) {
            guest.setVisitDate(null);
            repository.save(guest);
        } else {
            throw new SqlTransactionException("Can't remove Guest. Meeting not scheduled with employee ", person.getId());
        }

        return guest;
    }

}
