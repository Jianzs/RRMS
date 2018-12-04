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

    interface Search {
        int RENTER_PHONE = 1;
        int ROOMER_PHONE = 2;
        int NEIGHBORHOOD = 3;
    }
}
