package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.ApplySeeFrame;
import com.zwl.rrms.entity.HouseEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MarketHouseDetailBtn extends JPanel {
    private JFrame frame;
    private HouseEntity house;
    public MarketHouseDetailBtn(JFrame frame, HouseEntity house) {
        this.frame = frame;
        this.house = house;

        JPanel btnPanel = this;
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10,20,20,10));
        btnPanel.add(Box.createHorizontalGlue());
        JButton applySeeBtn = new JButton("申请看房");
        applySeeBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        btnPanel.add(applySeeBtn);

        applySeeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setHouse(house);
                FrameChange.enterFrame(frame, new ApplySeeFrame().getFrame());
            }
        });

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
