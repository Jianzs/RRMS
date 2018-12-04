package com.zwl.rrms;

import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.LoginFrame;

public class Manager {
    public static void main(String[] args) {
        FrameChange.enterFrame(null, new LoginFrame().getFrame());
    }
}
