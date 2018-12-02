package com.zwl.rrms.constant;

public interface House {
    interface Type {
        int BUNGALOW = 1;
        int BUILDING = 2;
        int TENEMENT = 3;
    }

    interface State {
        int UNPAID = 1;
        int NOT_RENT = 2;
        int RENTED = 3;
        int DELETED = 4;
    }

    interface FreeTime {
        int WEEKEND = 1;
        int WEEK = 2;
        int ANY_TIME = 3;
    }
}
