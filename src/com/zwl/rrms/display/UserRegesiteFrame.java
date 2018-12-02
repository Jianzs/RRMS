package com.zwl.rrms.display;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.BoxLayout;

public class UserRegesiteFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegesiteFrame window = new UserRegesiteFrame();
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
	public UserRegesiteFrame() {
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
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("用户注册");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		bodyPanel.add(namePanel);
		
		JLabel lblNewLabel = new JLabel("New label");
		namePanel.add(lblNewLabel);
		
		JPanel phonePanel = new JPanel();
		bodyPanel.add(phonePanel);
		
		JPanel passPanel = new JPanel();
		bodyPanel.add(passPanel);
		
		JPanel genderPanel = new JPanel();
		bodyPanel.add(genderPanel);
		
		JPanel addressPanel = new JPanel();
		bodyPanel.add(addressPanel);
		
		JPanel birthPanel = new JPanel();
		bodyPanel.add(birthPanel);
		
		JPanel panel = new JPanel();
		bodyPanel.add(panel);
		
		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		JButton submitBtn = new JButton("注册");
		btnPanel.add(submitBtn);
		
		JButton returnBtn = new JButton("返回");
		btnPanel.add(returnBtn);
	}

}
