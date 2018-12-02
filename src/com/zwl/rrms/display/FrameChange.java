package com.zwl.rrms.display;

import javax.swing.*;
import java.util.Stack;

public class FrameChange {
    private static Stack<JFrame> frameStack = new Stack<>();

    public static void enterFrame(JFrame curFrame, JFrame nexFrame){
        if (curFrame != null) {
            frameStack.push(curFrame);
            curFrame.dispose();
            System.out.println(curFrame.getTitle());
        }
        System.out.println("enter");
        System.out.println(nexFrame.getTitle());
        nexFrame.setVisible(true);
    }

    public static void returnFrame(JFrame frame) {
        System.out.println(frame.getTitle());
        System.out.println("return");
        frame.dispose();
        frame = frameStack.pop();
        frame.setVisible(true);
    }

}
