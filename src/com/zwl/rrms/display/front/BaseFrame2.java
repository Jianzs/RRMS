package com.zwl.rrms.display.front;

import javax.swing.*;

public abstract class BaseFrame2 {
    protected JFrame frame;
    public void display() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
