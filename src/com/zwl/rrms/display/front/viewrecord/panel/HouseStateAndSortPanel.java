package com.zwl.rrms.display.front.viewrecord.panel;

import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.front.viewrecord.ViewHouseFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HouseStateAndSortPanel extends JPanel {
    private final JComboBox adminStateBox;
    private final JComboBox roomerStateBox;

    public HouseStateAndSortPanel(BaseFrame frame) {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        JLabel roomerState = new JLabel("房主状态");
        roomerState.setFont(new Font("Dialog", Font.BOLD, 18));
        add(roomerState);

        roomerStateBox = new JComboBox();
        roomerStateBox.setFont(new Font("Dialog", Font.BOLD, 18));
        add(roomerStateBox);

        roomerStateBox.addItem(new ComboItem("不限", -1));
        roomerStateBox.addItem(new ComboItem("未回复", ViewRecord.ACK.NO_RESPONSE));
        roomerStateBox.addItem(new ComboItem("已同意", ViewRecord.ACK.ACK));
        roomerStateBox.addItem(new ComboItem("已拒绝", ViewRecord.ACK.NAK));

        add(Box.createHorizontalGlue());

        JLabel renterStateLabel = new JLabel("业务员状态");
        renterStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(renterStateLabel);

        adminStateBox = new JComboBox();
        adminStateBox.setFont(new Font("Dialog", Font.BOLD, 18));
        add(adminStateBox);

        adminStateBox.addItem(new ComboItem("不限", -1));
        adminStateBox.addItem(new ComboItem("未回复", ViewRecord.ACK.NO_RESPONSE));
        adminStateBox.addItem(new ComboItem("已同意", ViewRecord.ACK.ACK));
        adminStateBox.addItem(new ComboItem("已拒绝", ViewRecord.ACK.NAK));

        add(Box.createHorizontalGlue());

        JButton filterBtn = new JButton("筛选");
        filterBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        add(filterBtn);

        filterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ((ViewHouseFrame) frame).fresh(((ComboItem)roomerStateBox.getSelectedItem()).getValue(),
                        ((ComboItem) adminStateBox.getSelectedItem()).getValue());
            }
        });

//
//        JLabel sortLabel = new JLabel("排序");
//        sortLabel.setFont(new Font("Dialog", Font.BOLD, 18));
//        add(sortLabel);
//        JButton sortBtn = new JButton("升序");
//        sortBtn.setFont(new Font("Dialog", Font.BOLD, 18));
//        add(sortBtn);
    }
}
