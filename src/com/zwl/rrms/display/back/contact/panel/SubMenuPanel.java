package com.zwl.rrms.display.back.contact.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.display.back.contact.ContactAddFrame;
import com.zwl.rrms.display.back.contact.ContactListFrame;
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

        JButton contactAddBtn = new JButton("添加协议");
        contactAddBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(contactAddBtn);

        contactAddBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setContact(true);
                FrameChange.enterFrame(frame, new ContactAddFrame().getFrame());
            }
        });

        JButton contactListBtn = new JButton("所有协议");
        contactListBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        subMenuPanel.add(contactListBtn);

        contactListBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Session.getInstance().setContact(false);
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new ContactListFrame().getFrame());
            }
        });
    }
}
