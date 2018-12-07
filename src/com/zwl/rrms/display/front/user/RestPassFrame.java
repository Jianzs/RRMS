package com.zwl.rrms.display.front.user;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.BoxLayout;

public class RestPassFrame {

	private JFrame frame;
	private JLabel oldPassLabel;
	private JTextField oldPassField;
	private JLabel newPassLabel;
	private JPasswordField newPassField;
	private JLabel repeatLabel;
	private JPasswordField repeatField;

	private JButton submitBtn;
	private JButton returnBtn;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RestPassFrame window = new RestPassFrame();
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
	public RestPassFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("重置密码");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		JPanel oldPassPanel = new JPanel();
		bodyPanel.add(oldPassPanel);
		
		oldPassLabel = new JLabel("旧密码");
		oldPassPanel.add(oldPassLabel);
		
		oldPassField = new JTextField();
		oldPassPanel.add(oldPassField);
		oldPassField.setColumns(16);
		
		JPanel newPassPanel = new JPanel();
		bodyPanel.add(newPassPanel);
		
		newPassLabel = new JLabel("新密码");
		newPassPanel.add(newPassLabel);
		
		newPassField = new JPasswordField();
		newPassPanel.add(newPassField);
		newPassField.setColumns(16);
		
		JPanel repeatPanel = new JPanel();
		bodyPanel.add(repeatPanel);
		
		repeatLabel = new JLabel("重复新密码");
		repeatPanel.add(repeatLabel);
		
		repeatField = new JPasswordField();
		repeatPanel.add(repeatField);
		repeatField.setColumns(16);
		
		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		submitBtn = new JButton("提交");
		btnPanel.add(submitBtn);
		
		returnBtn = new JButton("返回");
		btnPanel.add(returnBtn);
	}

}
