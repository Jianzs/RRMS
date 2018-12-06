package com.zwl.rrms.display.back.user.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.back.user.UserDetailFrame;
import com.zwl.rrms.display.back.user.UserListFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListItemPanel extends JPanel {
    private static int color = 1;

    private UserEntity user;
    private JFrame frame;

    public ListItemPanel(UserEntity user, JFrame frame) {
        this.user = user;
        this.frame = frame;

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;

        this.setLayout(new GridLayout(0, 5, 0, 0));

        JLabel nameLabel = new JLabel(user.getName());
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(nameLabel);

        JLabel phoneLabel = new JLabel(user.getPhone());
        phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(phoneLabel);

        JButton delBtn = new JButton("删除");
        delBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(delBtn);

        delBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (UserController.delete(user)) {
                    new MsgFrame("删除成功").display();
                } else {
                    new MsgFrame("删除失败").display();
                }
                FrameChange.enterFrame(frame, new UserListFrame().getFrame());
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
                Session.getInstance().setBackUserDetail(user);
                FrameChange.enterFrame(frame, new UserDetailFrame().getFrame());
            }
        });
    }
}
