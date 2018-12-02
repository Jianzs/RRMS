package com.zwl.rrms.display.panel;

import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.FrameChange;
import com.zwl.rrms.display.HouseDetailFrame;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;
import com.zwl.rrms.util.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RentHouseBriefPanel extends JPanel {
    private JFrame frame;
    private ContactEntity contact;
    private UserEntity roomer;
    private HouseEntity house;

    public RentHouseBriefPanel(ContactEntity contact, JFrame frame) {
        this.frame = frame;
        this.contact = contact;
        this.house = HouseController.getHouseById(contact.getRoomId());
        if(house != null)
            this.roomer = UserController.getUserById(house.getRoomerId());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 5, 15));
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new FlowLayout());
        add(leftPanel);

        JLabel neiLabel = new JLabel("小区名称：" + house.getNeighborhood());
        neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        leftPanel.add(neiLabel);

        add(Box.createHorizontalGlue());

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        add(rightPanel);

        JLabel planPanel = new JLabel("起始时间：" + DateUtil.date2str(contact.getStartTime()));
        planPanel.setFont(new Font("Dialog", Font.BOLD, 18));
        rightPanel.add(planPanel);

        JLabel stateLabel = new JLabel("终止时间：".concat(DateUtil.date2str(contact.getEndTime())));
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        rightPanel.add(stateLabel);

        add(Box.createHorizontalGlue());

        JButton detailBtn = new JButton("查看详情");
        detailBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        add(detailBtn);

        detailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // TODO change to RentDetailFrame
                FrameChange.enterFrame(frame, new HouseDetailFrame(house, MarketHouseDetailBtn.class).getFrame());
            }
        });
    }
}
