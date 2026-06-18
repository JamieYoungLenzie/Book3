package com.sas.memex.book3.service;

import com.sas.memex.book3.interfaces.IDiceService;
import com.sas.memex.book3.die.Die;
import com.sas.memex.book3.die.IDie;
import org.springframework.stereotype.Service;

@Service
public class DiceService implements IDiceService {

    private DiceService() {
    }

    @Override
    public int oneD6() {
        IDie d1 = new Die(6);
        return d1.roll();
    }

    @Override
    public int twoD6() {
        return oneD6() + oneD6();
    }
}
