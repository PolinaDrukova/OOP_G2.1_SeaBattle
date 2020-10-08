package com.company;

import com.company.enum_state.Orientation;

public  interface InputListener {
    void makeShip(int x, int y, int palubs, Orientation o);
    void attack(int x, int y);
}
