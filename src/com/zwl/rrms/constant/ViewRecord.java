package com.zwl.rrms.constant;

public interface ViewRecord {
    interface State {
        int NEW = 1;
        int CLOSED = 2;
    }

    interface ACK {
        int NO_RESPONSE = 3;
        int ACK = 1;
        int NAK = 2;
    }
}
