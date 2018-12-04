package com.zwl.rrms.display.back.house.panel;

import com.zwl.rrms.display.back.house.HouseAddFrame;
import com.zwl.rrms.display.back.house.HouseListFrame;
import com.zwl.rrms.display.common.FrameChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SubMenuPanel extends JPanel {
    private JFrame frame;

    public SubMenuPanel(JFrame frame) {
        this.frame = frame;

        JPanel subMenuPanel = this;
        subMenuPanel.setLayout(new GridLayout(1, 2, 0, 0));

        JButton houseAddBtn = new JButton("添加房产");
        houseAddBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(houseAddBtn);

        houseAddBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new HouseAddFrame().getFrame());
            }
        });

        JButton houseListBtn = new JButton("所有房产");
        houseListBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(houseListBtn);

        houseListBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new HouseListFrame().getFrame());
            }
        });
    }
}
