package com.zwl.rrms.display.back.user.panel;

import com.zwl.rrms.display.back.user.UserAddFrame;
import com.zwl.rrms.display.back.user.UserListFrame;
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

        JButton userAddBtn = new JButton("添加用户");
        userAddBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(userAddBtn);

        userAddBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new UserAddFrame().getFrame());
            }
        });

        JButton userListBtn = new JButton("所有用户");
        userListBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(userListBtn);

        userListBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new UserListFrame().getFrame());
            }
        });
    }
}
