package com.zwl.rrms.display.front.house.panel;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.house.HouseDetailFrame;
import com.zwl.rrms.entity.HouseEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyHouseBriefPanel extends JPanel {
    private static int color = 1;
    private HouseEntity house;
    private JFrame frame;

    public MyHouseBriefPanel(HouseEntity house, JFrame frame) {
        this.house = house;
        this.frame = frame;

        if (color == 1)
            this.setBackground(new Color(218, 247, 166));
        else
            this.setBackground(new Color(121, 223, 214));
        color = (color + 1) % 2;

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(BorderFactory.createEmptyBorder(10,20,5,15));

        JLabel lblNewLabel = new JLabel("图片");
        lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        lblNewLabel.setPreferredSize(new Dimension(100, 100));
        ImageIcon icon = new ImageIcon(house.getPicture());
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, -1, Image.SCALE_DEFAULT));
        lblNewLabel.setIcon(icon);
        add(lblNewLabel);

        add(Box.createHorizontalGlue());

        JLabel neiLabel = new JLabel("小区名称：" + house.getNeighborhood());
        neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(neiLabel);

        add(Box.createHorizontalGlue());

        JLabel stateLabel = new JLabel("状态：".concat(state2str()));
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(stateLabel);

        add(Box.createHorizontalGlue());

        JLabel rentLabel = new JLabel("租金：" + house.getRent());
        rentLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        add(rentLabel);

        add(Box.createHorizontalGlue());

        JButton btnNewButton_5 = new JButton("查看详情");
        btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
        add(btnNewButton_5);

        btnNewButton_5.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                FrameChange.enterFrame(frame, new HouseDetailFrame(house, MyHouseDetailBtn.class).getFrame());
            }
        });
    }

    private String state2str() {
        switch (house.getState()) {
            case House.State.NOT_RENT: return "未被租用";
            case House.State.UNPAID: return "未付手续费";
            case House.State.RENTED: return "已被租用";
        }
        return "???";
    }
}
