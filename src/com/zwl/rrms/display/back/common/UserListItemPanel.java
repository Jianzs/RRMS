package com.zwl.rrms.display.back.common;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserListItemPanel extends JPanel {
    private static int color = 1;

    private UserEntity user;

    public UserListItemPanel(UserEntity user, JFrame frame) {
        this.user = user;

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;

        this.setBorder(BorderFactory.createEmptyBorder(20, 30, 10, 30));

        this.setLayout(new GridLayout(0, 3, 0, 0));

        JLabel nameLabel = new JLabel(user.getName());
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(nameLabel);

        JLabel phoneLabel = new JLabel(user.getPhone());
        phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(phoneLabel);

        JButton choseBtn = new JButton("选择");
        choseBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(choseBtn);

        choseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setSelectedUser(user);
                frame.dispose();
            }
        });
    }
}
