package com.zwl.rrms.display.back.common;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.UserEntity;

import java.awt.*;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserChoseFrame extends BaseFrame {

//	private JFrame frame;
	private JTextField searchField;
	private Integer page = 1;
	private Integer maxPage;
	private JButton nextBtn;
	private JButton prevBtn;
	private JPanel headPanel;
	private JPanel listPanel;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserChoseFrame window = new UserChoseFrame();
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
	public UserChoseFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLabel = new JLabel("用户选择");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));
		
		
		JPanel searchPanel = new JPanel();
		bodyPanel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		searchField = new JTextField();
		searchField.setFont(new Font("Dialog", Font.BOLD, 18));
		searchPanel.add(searchField);
		searchField.setColumns(10);

		JComboBox searchBox = new JComboBox();
		searchBox.setFont(new Font("Dialog", Font.BOLD, 18));
		searchPanel.add(searchBox);

		JButton searchBtn = new JButton("搜索");
		searchBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		searchPanel.add(searchBtn);

		listPanel = new JPanel();
		bodyPanel.add(listPanel, BorderLayout.CENTER);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		headPanel = new JPanel();
		listPanel.add(headPanel);
		headPanel.setLayout(new GridLayout(0, 3, 0, 0));

		JLabel nameLabel = new JLabel("姓名");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("手机号");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(phoneLabel);

		JLabel choseLabel = new JLabel("选择");
		choseLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(choseLabel);

		List<UserEntity> users = UserController.listAllByPage(page);
		renderList(users);

		/*** page button  ****/
		maxPage = UserController.countAll() / Parameter.NUM_HOUSE_PER_PAGE + 1;
		JPanel btnPanel = new JPanel();
		bodyPanel.add(btnPanel, BorderLayout.SOUTH);

		prevBtn = new JButton("上一页");
		btnPanel.add(prevBtn);

		nextBtn = new JButton("下一页");
		btnPanel.add(nextBtn);

		updateBtnState();
		prevBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (page == 1) return;
				super.mouseClicked(e);
				page --;
				updateBtnState();
				List<UserEntity> users = UserController.listAllByPage(page);
				renderList(users);
			}
		});

		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (page.equals(maxPage)) return;
				super.mouseClicked(e);
				page++;
				updateBtnState();
				List<UserEntity> users = UserController.listAllByPage(page);
				renderList(users);
			}
		});
	}

	private void updateBtnState() {
		if (page.equals(1)) {
			prevBtn.setEnabled(false);
		} else {
			prevBtn.setEnabled(true);
		}

		if (page.equals(maxPage)) {
			nextBtn.setEnabled(false);
		} else {
			nextBtn.setEnabled(true);
		}
	}

	private void renderList(List<UserEntity> users) {
		listPanel.removeAll();
		listPanel.add(headPanel);

		for (UserEntity user: users) {
			listPanel.add(new UserListItemPanel(user, frame));
		}

		for (int i = 0; i < Parameter.NUM_HOUSE_PER_PAGE - users.size(); i++) {
			listPanel.add(new JPanel());
		}

		listPanel.updateUI();
		listPanel.repaint();
	}
}
