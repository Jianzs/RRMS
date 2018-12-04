package com.zwl.rrms.display.back.contact;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.Contact;
import com.zwl.rrms.controller.ContactController;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.house.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.DateUtil;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContactDetailFrame extends BaseFrame {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactDetailFrame window = new ContactDetailFrame();
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
	public ContactDetailFrame() {
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

		frame.getContentPane().add(new MenuPanel(frame), BorderLayout.WEST);

		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("房产管理");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);

		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		bodyPanel.add(new SubMenuPanel(frame), BorderLayout.NORTH);

		JPanel mainPanel = new JPanel();
		bodyPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));

		/**** main panel ****/

		// TODO A Big Bug
		ContactEntity contact = ContactController.getById(1);
//		ContactEntity contact = Session.getInstance().getBackContactDetail();
		HouseEntity house = HouseController.getHouseById(contact.getRoomId());
		UserEntity roomer = UserController.getUserById(house.getRoomerId());
		UserEntity renter = UserController.getUserById(contact.getRenterId());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel startTimePanel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) startTimePanel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		mainPanel.add(startTimePanel);

		JLabel startTimeLabel = new JLabel("开始时间".concat(String.valueOf(DateUtil.getYear(contact.getStartTime())))
									.concat("-").concat(String.valueOf(DateUtil.getMonth(contact.getStartTime()))));
		startTimePanel.add(startTimeLabel);

		JPanel endTimePanel = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) endTimePanel.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		mainPanel.add(endTimePanel);

		JLabel endTimeLabel = new JLabel("结束时间".concat(String.valueOf(DateUtil.getYear(contact.getStartTime())))
				.concat("-").concat(String.valueOf(DateUtil.getMonth(contact.getStartTime()))));
		endTimePanel.add(endTimeLabel);

		JPanel statePanel = new JPanel();
		FlowLayout flowLayout_2 = (FlowLayout) statePanel.getLayout();
		flowLayout_2.setAlignment(FlowLayout.LEFT);
		mainPanel.add(statePanel);

		JLabel stateLabel = new JLabel("状态".concat(state2str(contact.getState())));
		statePanel.add(stateLabel);

		JPanel roomerPanel = new JPanel();
		FlowLayout flowLayout_3 = (FlowLayout) roomerPanel.getLayout();
		flowLayout_3.setAlignment(FlowLayout.LEFT);
		mainPanel.add(roomerPanel);

		JLabel roomerLabel = new JLabel("房主姓名".concat(roomer.getName()));
		roomerPanel.add(roomerLabel);

		JPanel renterPanel = new JPanel();
		FlowLayout flowLayout_5 = (FlowLayout) renterPanel.getLayout();
		flowLayout_5.setAlignment(FlowLayout.LEFT);
		mainPanel.add(renterPanel);

		JLabel renterLabel = new JLabel("租客姓名".concat(renter.getName()));
		renterPanel.add(renterLabel);

		JPanel remarkPanel = new JPanel();
		FlowLayout flowLayout_4 = (FlowLayout) remarkPanel.getLayout();
		flowLayout_4.setAlignment(FlowLayout.LEFT);
		mainPanel.add(remarkPanel);

		JLabel remarkLabel = new JLabel("详细信息");
		remarkPanel.add(remarkLabel);

		JTextArea remarkText = new JTextArea();
		remarkText.setText(contact.getRemark());
		remarkLabel.setEnabled(false);
		remarkPanel.add(remarkText);
	}

	private String state2str(Integer state) {
		switch (state) {
			case Contact.State.NORMAL: return "正常";
			case Contact.State.DELETED: return "已删除";
		}
		return  "";
	}

}
