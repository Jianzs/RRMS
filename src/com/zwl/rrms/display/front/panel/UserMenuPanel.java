package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.LoginFrame;
import com.zwl.rrms.display.front.house.HouseMarketFrame;
import com.zwl.rrms.display.front.house.MyHouseFrame;
import com.zwl.rrms.display.front.house.RentHouseFrame;
import com.zwl.rrms.display.front.user.UserInfoFrame;
import com.zwl.rrms.display.front.viewrecord.ViewHouseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserMenuPanel extends JPanel {
    JFrame frame;

    public UserMenuPanel(JFrame frame) {
        this.frame = frame;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(20,20,20,0));

        JButton marketBtn = new JButton("房源市场");
        marketBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(marketBtn);
        this.add(Box.createVerticalGlue());
        JButton viewBtn = new JButton("看房记录");
        viewBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(viewBtn);
        this.add(Box.createVerticalGlue());
        JButton rentBtn = new JButton("租房记录");
        rentBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(rentBtn);
        this.add(Box.createVerticalGlue());
        JButton myHouseBtn = new JButton("我的房屋");
        myHouseBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(myHouseBtn);
        this.add(Box.createVerticalGlue());
        JButton personBtn = new JButton("个人信息");
        personBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(personBtn);
        this.add(Box.createVerticalGlue());
        JButton returnBtn = new JButton("退出登录");
        returnBtn.setFont(new Font("Dialog", Font.BOLD, 20));
        this.add(returnBtn);


        returnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new LoginFrame().getFrame());
            }
        });

        marketBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new HouseMarketFrame().getFrame());
            }
        });

        viewBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new ViewHouseFrame().getFrame());
            }
        });

        rentBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new RentHouseFrame().getFrame());
            }
        });

        myHouseBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new MyHouseFrame().getFrame());
            }
        });

        personBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.enterFrame(frame, new UserInfoFrame().getFrame());
            }
        });
    }
}
