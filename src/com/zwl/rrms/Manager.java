package com.zwl.rrms;

import com.zwl.rrms.display.FrameChange;
import com.zwl.rrms.display.LoginFrame;

public class Manager {
    public static void main(String[] args) {
        FrameChange.enterFrame(null, new LoginFrame().getFrame());
    }
}
