package com.zwl.rrms.display.front.viewrecord;

import java.awt.*;

import javax.swing.*;
import java.util.List;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.front.house.panel.HouseMarketBriefPanel;
import com.zwl.rrms.display.front.viewrecord.panel.HouseStateAndSortPanel;
import com.zwl.rrms.display.front.panel.UserMenuPanel;
import com.zwl.rrms.display.front.viewrecord.panel.ViewRecordBriefPanel;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

public class ViewHouseFrame extends BaseFrame {
	private JPanel itemPanel;
	private JPanel headPanel;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewHouseFrame window = new ViewHouseFrame();
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
	public ViewHouseFrame() {
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
		JLabel titleLabel = new JLabel("看房记录");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		mainPanel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		bodyPanel.add(new HouseStateAndSortPanel(this), BorderLayout.NORTH);

		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(8, 0));
//		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		bodyPanel.add(itemPanel, BorderLayout.CENTER);
//		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		headPanel = new JPanel();
		headPanel.setLayout(new GridLayout(1, 5));
		headPanel.setBorder(BorderFactory.createEmptyBorder(20,10, 0, 10));
		itemPanel.add(headPanel);

		JLabel neiLabel = new JLabel("小区名称");
		neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(neiLabel);

		JLabel planPanel = new JLabel("计划时间");
		planPanel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(planPanel);

		JLabel adminStateLabel = new JLabel("业务员状态");
		adminStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(adminStateLabel);

		JLabel roomerStateLabel = new JLabel("房主状态");
		roomerStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(roomerStateLabel);

		JLabel detailLabel = new JLabel("查看详情");
		detailLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(detailLabel);

		// render house list
        for (ViewRecordEntity view: ViewRecordController.listRecordByPage(1)) {
            JPanel panelTmp = new ViewRecordBriefPanel(view, this.frame);
            itemPanel.add(panelTmp);
//            itemPanel.add(Box.createVerticalGlue());
        }
	}

	public void fresh(Integer roomer, Integer admin) {
		System.out.println(roomer);
		System.out.println(admin);
		itemPanel.removeAll();
		List<ViewRecordEntity> views = ViewRecordController.listByState(roomer, admin);

		itemPanel.add(headPanel);
		for (ViewRecordEntity view: views) {
			JPanel panelTmp = new ViewRecordBriefPanel(view, this.frame);
			itemPanel.add(panelTmp);
//			itemPanel.add(Box.createVerticalGlue());
		}

		itemPanel.updateUI();
		itemPanel.repaint();
	}
}
