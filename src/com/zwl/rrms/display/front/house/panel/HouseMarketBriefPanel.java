package com.zwl.rrms.display.front.house.panel;

import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.ScaleIcon;
import com.zwl.rrms.display.front.house.HouseDetailFrame;
import com.zwl.rrms.entity.HouseEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class HouseMarketBriefPanel extends JPanel {
	private static int color = 1;
	private static final String PATH = "/home/jian/workspace/rrms/pic/";

	private JFrame frame;
	private HouseEntity house;

	public HouseMarketBriefPanel(HouseEntity house, JFrame frame) {
		this.frame = frame;
		this.house = house;

		if (color == 1)
			this.setBackground(new Color(218, 247, 166));
		else
			this.setBackground(new Color(121, 223, 214));
		color = (color + 1) % 2;

		setLayout(new GridLayout(1, 4, 10, 30));
		setBorder(BorderFactory.createEmptyBorder(10,20,5,15));

		JLabel lblNewLabel = new JLabel(new ScaleIcon(house.getPicture()));
//		lblNewLabel.setFont(new Font("Dialog", Font.BOLD, 18));
//		lblNewLabel.setPreferredSize(new Dimension(100, 100));
//		ImageIcon icon = new ImageIcon(PATH + house.getPicture());
//		icon = new ImageIcon(icon.getImage().getScaledInstance(100, -1, Image.SCALE_DEFAULT));
//		lblNewLabel.setIcon(icon);
		add(lblNewLabel);

//		add(Box.createHorizontalGlue());

		JLabel lblNewLabel_1 = new JLabel(house.getNeighborhood());
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblNewLabel_1);

//		add(Box.createHorizontalGlue());

		JLabel lblNewLabel_2 = new JLabel(String.format("%.2f", house.getRent()));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblNewLabel_2);

//		add(Box.createHorizontalGlue());

		JButton btnNewButton_5 = new JButton("查看详情");
		btnNewButton_5.setFont(new Font("Dialog", Font.BOLD, 18));
		add(btnNewButton_5);

		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.enterFrame(frame, new HouseDetailFrame(house, MarketHouseDetailBtn.class).getFrame());
			}
		});
	}
}
