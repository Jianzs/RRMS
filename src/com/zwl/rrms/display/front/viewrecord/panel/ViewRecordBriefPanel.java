package com.zwl.rrms.display.front.viewrecord.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.house.HouseDetailFrame;
import com.zwl.rrms.display.front.house.panel.MarketHouseDetailBtn;
import com.zwl.rrms.display.front.viewrecord.ViewDetailFrame;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewRecordBriefPanel extends JPanel {
    private static int color = 1;


    private JFrame frame;
    private ViewRecordEntity view;
    private HouseEntity house;

    public ViewRecordBriefPanel(ViewRecordEntity view, JFrame frame) {
        this.frame = frame;
        this.view = view;
        this.house = HouseController.getHouseById(view.getRoomId());

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));

        setLayout(new GridLayout(1, 5));
        setBorder(BorderFactory.createEmptyBorder(10,20,5,15));

        JLabel neiLabel = new JLabel(house.getNeighborhood());
        neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(neiLabel);

        JLabel planPanel = new JLabel(plantime2str(view.getPlanTime()));
        planPanel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(planPanel);

        JLabel adminStateLabel = new JLabel(state2str(view.getAdminAck()));
        adminStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(adminStateLabel);

        JLabel roomerStateLabel = new JLabel(state2str(view.getRoomerAck()));
        roomerStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(roomerStateLabel);

        JButton detailBtn = new JButton("查看详情");
        detailBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        add(detailBtn);

        detailBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setViewRecord(ViewRecordController.getById(view.getId()));

                FrameChange.enterFrame(frame, new ViewDetailFrame().getFrame());
            }
        });

        color = (color + 1) % 2;
    }

    private String plantime2str(Integer planTime) {
        switch (planTime){
            case House.FreeTime.WEEK: return "周内";
            case House.FreeTime.WEEKEND: return "周末";
            case House.FreeTime.ANY_TIME: return "任意";
        }
        return "null";
    }

    private String state2str(Integer state) {
        StringBuilder sb = new StringBuilder();
        if (state.equals(ViewRecord.ACK.ACK)) {
            sb.append("同意");
        } else if (state.equals(ViewRecord.ACK.NO_RESPONSE)){
            sb.append("未回复");
        } else {
            sb.append("拒绝");
        }
        return sb.toString();
    }
}
