package com.zwl.rrms.constant;

public interface Contact {
    interface State {
        int NORMAL = 1;
        int DELETED = 2;
    }

    interface Search {
        int ROOMER_PHONE = 1;
        int RENTER_PHONE = 2;
        int NEIGHBORHOOD = 3;
    }
}
