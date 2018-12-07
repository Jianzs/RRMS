package com.zwl.rrms.display.front.viewrecord;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

public class ApplySeeFrame extends BaseFrame {

//	private JFrame frame;
	private JComboBox freetimeBox;
	private JTextArea contentText;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApplySeeFrame window = new ApplySeeFrame();
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
	public ApplySeeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(400, 300, 500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());

		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("申请看房");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);

		JPanel contentPanel = new JPanel();
		contentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		bodyPanel.add(contentPanel);

		JLabel contentLabel = new JLabel("备注信息：");
		contentLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPanel.add(contentLabel);

		contentText = new JTextArea();
		contentText.setColumns(20);
		contentText.setRows(3);
		contentText.setFont(new Font("Dialog", Font.BOLD, 18));
		contentPanel.add(contentText);

		JPanel freetimePanel = new JPanel();
		freetimePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		bodyPanel.add(freetimePanel);

		JLabel freetimeLabel = new JLabel("可申请看房时间：");
		freetimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		freetimeLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		freetimePanel.add(freetimeLabel);

		freetimeBox = new JComboBox();
		freetimeBox.addItem(new ComboItem("周内", House.FreeTime.WEEK));
		freetimeBox.addItem(new ComboItem("周末", House.FreeTime.WEEKEND));
		freetimeBox.addItem(new ComboItem("任意", House.FreeTime.ANY_TIME));
		freetimeBox.setFont(new Font("Dialog", Font.BOLD, 18));
		freetimePanel.add(freetimeBox);

		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);

		JButton submitBtn = new JButton("提交");
		submitBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(submitBtn);

		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (submitApplySee()) {
					new MsgFrame("提交成功").display();
				}else  {
					new MsgFrame("提交失败").display();
				}
			}
		});

		JButton returnBtn = new JButton("提交");
		returnBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(returnBtn);

		returnBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.returnFrame(frame);
			}
		});
	}

	private boolean submitApplySee() {
		HouseEntity house = Session.getInstance().getHouse();
		UserEntity renter = Session.getInstance().getUser();
		UserEntity roomer = UserController.getUserById(house.getRoomerId());

		ViewRecordEntity.Builder builder = new ViewRecordEntity.Builder();
		builder.setRenterId(renter.getId())
				.setRoomId(house.getId())
				.setPlanTime(((ComboItem) freetimeBox.getSelectedItem()).getValue())
				.setDescription(contentText.getText());
		return ViewRecordController.create(builder.build());
	}

}
