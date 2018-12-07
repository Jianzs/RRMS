package com.zwl.rrms.display.front.house;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.front.house.panel.HouseMarketBriefPanel;
import com.zwl.rrms.display.front.house.panel.HouseMarketSortPanel;
import com.zwl.rrms.display.front.house.panel.MyHouseBriefPanel;
import com.zwl.rrms.display.front.panel.UserMenuPanel;
import com.zwl.rrms.entity.HouseEntity;

public class MyHouseFrame extends BaseFrame {
    private JPanel itemPanel;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyHouseFrame window = new MyHouseFrame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MyHouseFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		frame.getContentPane().add(new UserMenuPanel(frame), BorderLayout.WEST);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		// title
		JLabel titleLabel = new JLabel("我的房屋");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		mainPanel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		bodyPanel.add(new HouseMarketSortPanel(this), BorderLayout.NORTH);

		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(8, 1));
		bodyPanel.add(itemPanel, BorderLayout.CENTER);
//		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		// render house list
		HouseController.listHouseByUserAndPage(Session.getInstance().getUser().getId(), 1).forEach(house -> {
			JPanel panelTmp = new MyHouseBriefPanel(house, this.frame);
			itemPanel.add(panelTmp);
			itemPanel.add(Box.createVerticalGlue());
		});

		JPanel btnPanel = new JPanel();
		bodyPanel.add(btnPanel, BorderLayout.SOUTH);

		JButton createBtn = new JButton("提交房屋");
		createBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(createBtn);

		createBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.enterFrame(frame, new HouseSubmitFrame().getFrame());
			}
		});
	}

    public void freshItem() {
        itemPanel.removeAll();
        List<HouseEntity> houses = HouseController.listHouseByUserAndPage(Session.getInstance().getUser().getId(), 1);
        houses.sort((h1, h2) -> {
            double res = h1.getRent() - h2.getRent();
            if (!Session.getInstance().getHouseMarketUpSort()) res *= -1;
            if (res > 0) {
                return 1;
            } else if (res < 0) {
                return -1;
            } else {
                return 0;
            }
        });

        houses.forEach(house -> {
            JPanel panelTmp = new MyHouseBriefPanel(house, this.frame);
            itemPanel.add(panelTmp);
            itemPanel.add(Box.createVerticalGlue());
        });

        itemPanel.updateUI();
        itemPanel.repaint();
    }
}
