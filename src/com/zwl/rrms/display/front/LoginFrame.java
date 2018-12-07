package com.zwl.rrms.display.front;

import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.front.house.HouseMarketFrame;
import com.zwl.rrms.display.front.user.UserRegisterFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends BaseFrame {

//	private JFrame frame;
	private JLabel lblrrms;
	private JLabel label;
	private JTextField textField;
	private JLabel label_1;
	private JPasswordField passwordField;
	private JPanel titlePanel;
	private JPanel bodyPanel;
	private JPanel btnPanel;
	private JPanel phonePanel;
	private JPanel passPanel;
	private JPanel msgPanel;
	private JLabel msgLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame window = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.getContentPane().setFont(new Font("Dialog", Font.PLAIN, 18));
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		titlePanel = new JPanel();
		titlePanel.setBorder(new EmptyBorder(30, 0, 60, 0));
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		lblrrms = new JLabel("欢迎使用RRMS");
		titlePanel.add(lblrrms);
		lblrrms.setFont(new Font("Dialog", Font.BOLD, 24));
		
		bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));
		
		phonePanel = new JPanel();
		phonePanel.setBorder(new EmptyBorder(10, 0, 0, 0));
		bodyPanel.add(phonePanel);
		
		label = new JLabel("手 机 号");
		phonePanel.add(label);
		label.setFont(new Font("Dialog", Font.BOLD, 18));
		
		textField = new JTextField();
		phonePanel.add(textField);
		textField.setFont(new Font("Dialog", Font.PLAIN, 18));
		textField.setColumns(16);
		
		passPanel = new JPanel();
		passPanel.setBorder(new EmptyBorder(0, 0, 0, 0));
		bodyPanel.add(passPanel);
		
		label_1 = new JLabel("密    码");
		passPanel.add(label_1);
		label_1.setFont(new Font("Dialog", Font.BOLD, 18));

		passwordField = new JPasswordField();
		passwordField.setColumns(16);
		passPanel.add(passwordField);
		passwordField.setFont(new Font("Dialog", Font.PLAIN, 18));
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				super.keyTyped(e);
				if (e.getKeyChar() == '\n') {
					login();
				}
			}
		});


		msgPanel = new JPanel();
		bodyPanel.add(msgPanel);
		msgLabel = new JLabel();
		msgLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		msgPanel.add(msgLabel);

		btnPanel = new JPanel();
		btnPanel.setBorder(new EmptyBorder(30, 0, 40, 0));
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);
		
		JButton button = new JButton("登录");
		btnPanel.add(button);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login();
			}
		});
		button.setFont(new Font("Dialog", Font.BOLD, 18));

		JButton registerBtn = new JButton("注册");
		registerBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(registerBtn);

		registerBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.enterFrame(frame, new UserRegisterFrame().getFrame());
			}
		});

		JButton adminBtn = new JButton("业务员系统");
		adminBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(adminBtn);

		adminBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.enterFrame(frame, new com.zwl.rrms.display.back.LoginFrame().getFrame());
			}
		});
	}


	protected void login() {
		String phone = textField.getText();
		String password = String.valueOf(passwordField.getPassword());
		if (password.equals("") || phone == null || phone.equals("")) {
			msgLabel.setText("用户名或密码不能为空。");
			return;
		}
		if (UserController.login(phone, password)) {
			FrameChange.enterFrame(this.frame, new HouseMarketFrame().getFrame());
//			this.frame.dispose();
//			HouseMarketFrame window = new HouseMarketFrame();
//			window.display();
		} else {
			msgLabel.setText("用户名或密码错误。");
		}
	}
}
