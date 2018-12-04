package com.zwl.rrms.display.back.contact;

import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.contact.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContactAddFrame extends BaseFrame {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactAddFrame window = new ContactAddFrame();
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
	public ContactAddFrame() {
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

        JLabel titleLabel = new JLabel("协议管理");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
        titlePanel.add(titleLabel);

        JPanel bodyPanel = new JPanel();
        frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
        bodyPanel.setLayout(new BorderLayout(0, 0));

        bodyPanel.add(new SubMenuPanel(frame), BorderLayout.NORTH);

        JPanel mainPanel = new JPanel();
        bodyPanel.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
        mainPanel.setLayout(new BorderLayout(0, 0));

        /**** main panel ****/

        JPanel infoPanel = new JPanel();
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JPanel startTimePanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) startTimePanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        infoPanel.add(startTimePanel);
        
        JLabel startTimeLabel = new JLabel("开始时间");
        startTimePanel.add(startTimeLabel);
        
        JComboBox startTimeYearBox = new JComboBox();
        startTimePanel.add(startTimeYearBox);
        
        JLabel lineLabel2 = new JLabel("-");
        startTimePanel.add(lineLabel2);
        
        JComboBox startTimeMonthBox = new JComboBox();
        startTimePanel.add(startTimeMonthBox);
        
        JPanel endTimePanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) endTimePanel.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        infoPanel.add(endTimePanel);
        
        JLabel endTimeLabel = new JLabel("结束时间");
        endTimePanel.add(endTimeLabel);
        
        JComboBox endTimeYearBox = new JComboBox();
        endTimePanel.add(endTimeYearBox);
        
        JLabel lineLable1 = new JLabel("-");
        endTimePanel.add(lineLable1);
        
        JComboBox endTimeMonthBox = new JComboBox();
        endTimePanel.add(endTimeMonthBox);
        
        JPanel statePanel = new JPanel();
        FlowLayout flowLayout_2 = (FlowLayout) statePanel.getLayout();
        flowLayout_2.setAlignment(FlowLayout.LEFT);
        infoPanel.add(statePanel);
        
        JLabel stateLabel = new JLabel("状态");
        statePanel.add(stateLabel);
        
        JComboBox stateBox = new JComboBox();
        statePanel.add(stateBox);
        
        JPanel roomerPanel = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) roomerPanel.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        infoPanel.add(roomerPanel);
        
        JLabel choseRoomerLabel = new JLabel("房主");
        roomerPanel.add(choseRoomerLabel);
        
        JButton choseRoomerBtn = new JButton("选择房主");
        roomerPanel.add(choseRoomerBtn);
        
        JPanel renterPanel = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) renterPanel.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        infoPanel.add(renterPanel);
        
        JLabel choseRenterLabel = new JLabel("租客");
        renterPanel.add(choseRenterLabel);
        
        JButton choseRenterBtn = new JButton("选择租客");
        renterPanel.add(choseRenterBtn);
        
        JPanel remarkPanel = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) remarkPanel.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        infoPanel.add(remarkPanel);
        
        JLabel remarkLabel = new JLabel("详细信息");
        remarkPanel.add(remarkLabel);
        
        JTextArea remarkText = new JTextArea();
        remarkPanel.add(remarkText);
        
        JPanel btnPanel = new JPanel();
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        
        JButton submitBtn = new JButton("提交");
        btnPanel.add(submitBtn);
	}

}
