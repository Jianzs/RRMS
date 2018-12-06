package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.ViewDetailFrame;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;
import com.zwl.rrms.util.DateUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ViewRecordBriefPanel extends JPanel {
    private JFrame frame;
    private ViewRecordEntity view;
    private HouseEntity house;

    public ViewRecordBriefPanel(ViewRecordEntity view, JFrame frame) {
        this.frame = frame;
        this.view = view;
        this.house = HouseController.getHouseById(view.getRoomId());

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,20,5,15));
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

        JLabel planPanel = new JLabel("计划看房时间：" + plantime2str(view.getPlanTime()));
        planPanel.setFont(new Font("Dialog", Font.BOLD, 18));
        rightPanel.add(planPanel);

        JLabel stateLabel = new JLabel("状态：".concat(state2str()));
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
                Session.getInstance().setViewRecord(ViewRecordController.getById(view.getId()));
                // TODO change to ViewDetailFrame
                FrameChange.enterFrame(frame, new ViewDetailFrame().getFrame());
            }
        });
    }

    private String plantime2str(Integer planTime) {
        switch (planTime){
            case House.FreeTime.WEEK: return "周内";
            case House.FreeTime.WEEKEND: return "周末";
            case House.FreeTime.ANY_TIME: return "任意";
        }
        return "null";
    }

    private String state2str() {
        StringBuilder sb = new StringBuilder();
        if (view.getAdminAck().equals(ViewRecord.ACK.ACK)) {
            sb.append("业务员已确认");
        } else if (view.getAdminAck().equals(ViewRecord.ACK.NO_RESPONSE)){
            sb.append("业务员未回复");
        } else {
            sb.append("业务员已拒绝");
        }
        sb.append(",");
        if (view.getRoomerAck().equals(ViewRecord.ACK.ACK)) {
            sb.append("房主已确认");
        } else if (view.getRoomerAck().equals(ViewRecord.ACK.NO_RESPONSE)){
            sb.append("房主未回复");
        } else {
            sb.append("房主已拒绝");
        }
        return sb.toString();
    }
}
