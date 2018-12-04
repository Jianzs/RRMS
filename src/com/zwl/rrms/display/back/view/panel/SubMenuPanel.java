package com.zwl.rrms.display.back.view.panel;

import javax.swing.*;
import java.awt.*;

public class SubMenuPanel extends JPanel {
    private JFrame frame;
    public SubMenuPanel(JFrame frame) {
        this.frame = frame;
        JPanel subMenuPanel = this;
        subMenuPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton view = new JButton("添加用户");
        subMenuPanel.add(view);



        JButton userListBtn = new JButton("所有用户");
        subMenuPanel.add(userListBtn);
    }
}
