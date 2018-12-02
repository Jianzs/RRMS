package com.zwl.rrms.display;

import javax.swing.*;

public abstract class BaseFrame {
    protected JFrame frame;
    public void display() {
        frame.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
