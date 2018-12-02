package com.zwl.rrms.display.panel;

import com.zwl.rrms.display.FrameChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyHouseDetailBtn extends JPanel {
    private JFrame frame;
    public MyHouseDetailBtn(JFrame frame) {
        this.frame = frame;
        JPanel btnPanel = this;
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10,20,20,10));
        btnPanel.add(Box.createHorizontalGlue());
        JButton returnBtn = new JButton("返回");
        returnBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        btnPanel.add(returnBtn);
        btnPanel.add(Box.createHorizontalGlue());

        returnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.returnFrame(frame);
            }
        });
    }
}
