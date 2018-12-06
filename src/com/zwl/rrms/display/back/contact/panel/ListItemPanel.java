package com.zwl.rrms.display.back.contact.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.Contact;
import com.zwl.rrms.controller.ContactController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.back.contact.ContactDetailFrame;
import com.zwl.rrms.display.back.user.UserDetailFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListItemPanel extends JPanel {
    private static int color = 1;

    private ContactEntity contact;
    private JFrame frame;

    public ListItemPanel(ContactEntity contact, JFrame frame) {
        this.contact = contact;
        this.frame = frame;

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;

        UserEntity roomer = UserController.getUserById(contact.getRoomId());
        UserEntity renter = UserController.getUserById(contact.getRenterId());

        this.setLayout(new GridLayout(0, 5, 0, 0));

        JLabel roomerLabel = new JLabel(roomer.getName());
        roomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(roomerLabel);

        JLabel renterLabel = new JLabel(renter.getName());
        renterLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(renterLabel);

        JButton delBtn = new JButton("删除");
        delBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(delBtn);

        delBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (ContactController.delete(contact)) {
                    new MsgFrame("删除成功").display();
                } else {
                    new MsgFrame("删除失败").display();
                }
            }
        });

        JButton changeBtn = new JButton("点击修改");
        changeBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(changeBtn);

        JButton detailBtn = new JButton("查看详情");
        detailBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(detailBtn);

        detailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setBackContactDetail(contact);
                FrameChange.enterFrame(frame, new ContactDetailFrame().getFrame());
            }
        });
    }
}
