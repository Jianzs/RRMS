package com.zwl.rrms.display.common;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MsgFrame {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MsgFrame window = new MsgFrame("null");
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
	public MsgFrame(String content) {
		initialize(content);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String content) {
		frame = new JFrame();
		frame.setBounds(100, 100, 300, 200);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));


		new Thread(() -> {
			JLabel label = new JLabel("提示：".concat(content));
			label.setFont(new Font("Dialog", Font.BOLD, 24));
			frame.getContentPane().add(label, BorderLayout.CENTER);

			JButton closeBtn = new JButton("关闭");
			closeBtn.setFont(new Font("Dialog", Font.BOLD, 24));
			frame.getContentPane().add(closeBtn, BorderLayout.SOUTH);

			closeBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					frame.dispose();
				}
			});
		}).start();
	}

	public void display() {
		frame.setVisible(true);
	}
}
