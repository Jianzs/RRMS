package com.zwl.rrms.display.back.house.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.back.house.HouseAddFrame;
import com.zwl.rrms.display.back.house.HouseDetailFrame;
import com.zwl.rrms.display.back.user.UserDetailFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListItemPanel extends JPanel {
    private static int color = 1;

    private HouseEntity house;
    private UserEntity roomer;
    private JFrame frame;

    public ListItemPanel(HouseEntity house, JFrame frame) {
        this.house = house;
        this.frame = frame;
        this.roomer = UserController.getUserById(house.getRoomerId());

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;

        this.setLayout(new GridLayout(0, 6, 0, 0));

        JLabel nameLabel = new JLabel(house.getNeighborhood());
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(nameLabel);

        JLabel roomerLabel = new JLabel(roomer.getName());
        roomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(roomerLabel);

        JLabel stateLabel = new JLabel(state2str(house.getState()));
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(stateLabel);

        JButton delBtn = new JButton("删除");
        delBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(delBtn);

        JButton changeBtn = new JButton("点击修改");
        changeBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(changeBtn);

        changeBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setModify(true);
                Session.getInstance().setBackHouseDetail(house);
                FrameChange.enterFrame(frame, new HouseAddFrame().getFrame());
            }
        });

        JButton detailBtn = new JButton("查看详情");
        detailBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(detailBtn);

        detailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setBackHouseDetail(house);
                FrameChange.enterFrame(frame, new HouseDetailFrame().getFrame());
            }
        });
    }

    private String state2str(Integer state) {
        switch (state) {
            case House.State.NOT_RENT: return "未租赁";
            case House.State.RENTED: return "已租赁";
            case House.State.UNPAID: return "未付手续费";
            case House.State.DELETED: return "已删除";
        }
        return "null";
    }
}
