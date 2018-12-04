package com.zwl.rrms.display.back.view.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.back.view.ViewDetailFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ListItemPanel extends JPanel {
    private static int color = 1;

    private ViewRecordEntity view;
    private JFrame frame;

    public ListItemPanel(ViewRecordEntity view, JFrame frame) {
        this.view = view;
        this.frame = frame;

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;


        HouseEntity house = HouseController.getHouseById(view.getRoomId());
        UserEntity roomer = UserController.getUserById(view.getRoomId());

        this.setLayout(new GridLayout(0, 6, 0, 0));

        JLabel neiLabel = new JLabel(house.getNeighborhood());
        neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(neiLabel);

        JLabel roomerLabel = new JLabel(roomer.getName());
        roomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(roomerLabel);

        JLabel stateLabel = new JLabel(state2str(view.getRoomerAck()));
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(stateLabel);

        JButton delBtn = new JButton("删除");
        delBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        this.add(delBtn);

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
                Session.getInstance().setBackViewRecordDetail(view);
                FrameChange.enterFrame(frame, new ViewDetailFrame().getFrame());
            }
        });
    }

    private String state2str(Integer roomerAck) {
        switch (roomerAck) {
            case ViewRecord.ACK.NO_RESPONSE: return "未回复";
            case ViewRecord.ACK.ACK: return "已同意";
            case ViewRecord.ACK.NAK: return "已拒绝";
        }
        return null;
    }
}
