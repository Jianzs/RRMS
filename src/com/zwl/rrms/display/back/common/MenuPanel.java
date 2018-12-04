package com.zwl.rrms.display.back.common;

import com.zwl.rrms.display.back.contact.ContactListFrame;
import com.zwl.rrms.display.back.house.HouseListFrame;
import com.zwl.rrms.display.back.user.UserListFrame;
import com.zwl.rrms.display.back.view.ViewListFrame;
import com.zwl.rrms.display.common.FrameChange;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuPanel extends JPanel {
    JFrame frame;
    public MenuPanel(JFrame frame) {
        this.frame = frame;
        JPanel menuPanel = this;
        menuPanel.setLayout(new GridLayout(4, 0, 0, 0));

        JButton houseBtn = new JButton("房屋管理");
        houseBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        menuPanel.add(houseBtn);

        houseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new HouseListFrame().getFrame());
            }
        });

        JButton userBtn = new JButton("用户管理");
        userBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        menuPanel.add(userBtn);

        userBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new UserListFrame().getFrame());
            }
        });

        JButton viewBtn = new JButton("看房管理");
        viewBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        menuPanel.add(viewBtn);

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new ViewListFrame().getFrame());
            }
        });

        JButton contactBtn = new JButton("协议管理");
        contactBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        menuPanel.add(contactBtn);

        contactBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new ContactListFrame().getFrame());
            }
        });
    }
}
