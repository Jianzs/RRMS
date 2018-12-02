package com.zwl.rrms.display.panel;

import javax.swing.*;
import java.awt.*;

public class HouseStateAndSortPanel extends JPanel {
    public HouseStateAndSortPanel() {
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5,10,0,10));

        JLabel label_1 = new JLabel("状态");
        label_1.setFont(new Font("Dialog", Font.BOLD, 18));
        add(label_1);
        JComboBox comboBox = new JComboBox();
        comboBox.setToolTipText("");
        add(comboBox);

        add(Box.createHorizontalGlue());

        JLabel label_3 = new JLabel("排序");
        label_3.setFont(new Font("Dialog", Font.BOLD, 18));
        add(label_3);
        JButton button = new JButton("升序");
        button.setFont(new Font("Dialog", Font.BOLD, 18));
        add(button);
    }
}
