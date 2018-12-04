package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.display.common.ComboItem;

import javax.swing.*;
import java.awt.*;

public class HouseStateAndSortPanel extends JPanel {
    public HouseStateAndSortPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        JLabel stateLabel = new JLabel("状态");
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(stateLabel);
        JComboBox stateBox = new JComboBox();
        stateBox.setToolTipText("");
        add(stateBox);

        add(Box.createHorizontalGlue());

        JLabel sortLabel = new JLabel("排序");
        sortLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(sortLabel);
        JButton sortBtn = new JButton("升序");
        sortBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        add(sortBtn);

        stateBox.addItem(new ComboItem("未租赁", House.State.NOT_RENT));
        stateBox.addItem(new ComboItem("已租赁", House.State.RENTED));
    }
}
