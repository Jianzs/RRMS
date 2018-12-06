package com.zwl.rrms.display.back.view;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;
import com.zwl.rrms.util.DateUtil;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.Font;
import java.awt.Component;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.EmptyBorder;

public class ViewDetailFrame extends BaseFrame {

//    private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ViewDetailFrame window = new ViewDetailFrame();
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
	public ViewDetailFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		ViewRecordEntity viewRecord = Session.getInstance().getBackViewRecordDetail();
		HouseEntity house = HouseController.getHouseById(viewRecord.getRoomId());
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.X_AXIS));

		JPanel basePanel = new JPanel();
		bodyPanel.add(basePanel);
		basePanel.setBorder(new EmptyBorder(20, 20, 30, 20));
		basePanel.setLayout(new BoxLayout(basePanel, BoxLayout.Y_AXIS));

		JLabel neiLabel = new JLabel("小区名称：".concat(house.getNeighborhood()));
		neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		basePanel.add(neiLabel);

		Component verticalGlue = Box.createVerticalGlue();
		basePanel.add(verticalGlue);

		JLabel planTimeLabel = new JLabel("计划看房时间：".concat(plantime2str(viewRecord.getPlanTime())));
		planTimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		basePanel.add(planTimeLabel);

		Component verticalGlue_1 = Box.createVerticalGlue();
		basePanel.add(verticalGlue_1);

		JLabel roomerAckLabel = new JLabel("房主确认状态：".concat(state2str(viewRecord.getRoomerAck())));
		roomerAckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		basePanel.add(roomerAckLabel);

		Component verticalGlue_2 = Box.createVerticalGlue();
		basePanel.add(verticalGlue_2);

		JLabel adminAckLabel = new JLabel("管理员确认状态：".concat(state2str(viewRecord.getAdminAck())));
		adminAckLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		basePanel.add(adminAckLabel);

		Component verticalGlue_3 = Box.createVerticalGlue();
		basePanel.add(verticalGlue_3);

		JLabel viewStateLabel = new JLabel("看房状态：".concat(viewState2str(viewRecord.getState())));
		viewStateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		basePanel.add(viewStateLabel);

		Component verticalGlue_4 = Box.createVerticalGlue();
		basePanel.add(verticalGlue_4);

		bodyPanel.add(Box.createHorizontalGlue());

		JPanel descPanel = new JPanel();
		descPanel.setBorder(new EmptyBorder(20, 100, 30, 20));
		bodyPanel.add(descPanel);
		descPanel.setLayout(new BorderLayout(0, 0));

		JLabel descLabel = new JLabel("备注：".concat(viewRecord.getDescription()));
		descLabel.setVerticalAlignment(SwingConstants.TOP);
		descPanel.add(descLabel);
		descLabel.setFont(new Font("Dialog", Font.BOLD, 18));

		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		titlePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel label = new JLabel("看房详情");
		label.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(label);

		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);

        JButton returnBtn = new JButton("返回");
        returnBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        btnPanel.add(returnBtn);

        returnBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                FrameChange.returnFrame(frame);
            }
        });

        JButton ackBtn = new JButton("同意");
        ackBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        btnPanel.add(ackBtn);

        ackBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ViewRecordController.adminAck(viewRecord);
            }
        });

        JButton nakBtn = new JButton("拒绝");
        nakBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        btnPanel.add(nakBtn);

        nakBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                ViewRecordController.adminNak(viewRecord);
            }
        });
	}

	private String plantime2str(Integer planTime) {
		switch (planTime){
			case House.FreeTime.WEEK: return "周内";
			case House.FreeTime.WEEKEND: return "周末";
			case House.FreeTime.ANY_TIME: return "任意";
		}
		return "null";
	}

	private String viewState2str(Integer state) {
		switch (state) {
			case ViewRecord.State.CLOSED:
				return "已取消";
			case ViewRecord.State.NEW:
				return "正常";
		}
		return "null;";
	}

	private String state2str(Integer ack) {
		switch (ack) {
			case ViewRecord.ACK.NO_RESPONSE:
				return "未回复";
			case ViewRecord.ACK.ACK:
				return "已同意";
			case ViewRecord.ACK.NAK:
				return "已拒绝";
		}
		return "null";
	}

}
