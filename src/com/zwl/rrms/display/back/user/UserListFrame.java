package com.zwl.rrms.display.back.user;

import com.zwl.rrms.constant.House;
import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.user.panel.ListItemPanel;
import com.zwl.rrms.display.back.user.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.UserEntity;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class UserListFrame extends BaseFrame {

//	private JFrame frame;
private JTextField searchField;
	private Integer page = 1;
	private Integer maxPage;
	private JPanel listPanel;
	private JPanel headPanel;
	private JButton nextBtn;
	private JButton prevBtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserListFrame window = new UserListFrame();
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
	public UserListFrame() {
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

		JLabel titleLabel = new JLabel("用户管理");
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


		/**** main panel  ****/

		JPanel searchPanel = new JPanel();
		mainPanel.add(searchPanel, BorderLayout.NORTH);
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

		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ComboItem item = (ComboItem) searchBox.getSelectedItem();
				switch (item.getValue()) {
					case User.Search.NAME: searchByName(searchField.getText()); break;
					case User.Search.PHONE: searchByPhone(searchField.getText()); break;
				}
			}
		});

		listPanel = new JPanel();
		mainPanel.add(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		headPanel = new JPanel();
		listPanel.add(headPanel);
		headPanel.setLayout(new GridLayout(0, 5, 0, 0));

		JLabel nameLabel = new JLabel("姓名");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("手机号");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(phoneLabel);

		JLabel delLabel = new JLabel("点击删除");
		delLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(delLabel);

		JLabel changeLabel = new JLabel("点击修改");
		changeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(changeLabel);

		JLabel detailLabel = new JLabel("查看详情");
		detailLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(detailLabel);

		List<UserEntity> users = UserController.listAllByPage(page);
		renderList(users);

		/*** page button  ****/
		maxPage = UserController.countAll() / Parameter.NUM_HOUSE_PER_PAGE + 1;
		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel, BorderLayout.SOUTH);

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

	private void searchByPhone(String text) {
		if (text.equals("")) return;
		List<UserEntity> users = UserController.listByPhone(text);
		renderList(users);
	}

	private void searchByName(String text) {
		if (text.equals("")) return;
		List<UserEntity> users = UserController.listFuzzyByName(text);
		renderList(users);
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
			listPanel.add(new ListItemPanel(user, frame));
		}

		for (int i = 0; i < Parameter.NUM_HOUSE_PER_PAGE - users.size(); i++) {
			listPanel.add(new JPanel());
		}

		listPanel.updateUI();
		listPanel.repaint();
	}

}
