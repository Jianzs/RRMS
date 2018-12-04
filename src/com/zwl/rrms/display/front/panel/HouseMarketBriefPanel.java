package com.zwl.rrms.display.front.panel;

import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.HouseDetailFrame;
import com.zwl.rrms.entity.HouseEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class HouseMarketBriefPanel extends JPanel {
	private JFrame frame;
	private HouseEntity house;

	public HouseMarketBriefPanel(HouseEntity house, JFrame frame) {
		this.frame = frame;
		this.house = house;

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

		JLabel lblNewLabel_1 = new JLabel(house.getNeighborhood());
		lblNewLabel_1.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblNewLabel_1);

		add(Box.createHorizontalGlue());

		JLabel lblNewLabel_2 = new JLabel(String.format("价格:%.2f", house.getRent()));
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD, 18));
		add(lblNewLabel_2);

		add(Box.createHorizontalGlue());

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
