package com.zwl.rrms.constant;

public interface User {
    interface Gender {
        int MALE = 1;
        int FEMALE = 2;
    }

    interface State {
        int NORMAL = 1;
        int DELETED = 2;
    }

    interface Type {
        int ORDINARY = 1;
        int ADMIN = 2;
    }

    interface Search {
        int PHONE = 1;
        int NAME = 2;
    }
}
