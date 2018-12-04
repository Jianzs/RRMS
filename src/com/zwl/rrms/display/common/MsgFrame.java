package com.zwl.rrms.display.common;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

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
		frame.setBounds(400, 300, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("提示：".concat(content));
		label.setFont(new Font("Dialog", Font.BOLD, 24));
		frame.getContentPane().add(label, BorderLayout.CENTER);
	}

	public void display() {
		frame.setVisible(true);
	}
}
