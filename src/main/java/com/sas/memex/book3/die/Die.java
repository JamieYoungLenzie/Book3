package com.sas.memex.book3.die;

public class Die implements IDie {
    
    private int sides;
    
    public Die(int sides) {
        this.sides = sides;
    }
    
    @Override
    public int roll() {
        return (int) (Math.random() * sides + 1);
    }
}
