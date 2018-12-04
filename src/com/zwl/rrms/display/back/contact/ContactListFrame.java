package com.zwl.rrms.display.back.contact;

import com.zwl.rrms.constant.Contact;
import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.controller.ContactController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.contact.panel.ListItemPanel;
import com.zwl.rrms.display.back.contact.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.UserEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContactListFrame extends BaseFrame {

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
					ContactListFrame window = new ContactListFrame();
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
	public ContactListFrame() {
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

		JLabel titleLabel = new JLabel("房产管理");
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

		JPanel searchPanel = new JPanel();
		mainPanel.add(searchPanel, BorderLayout.NORTH);
		searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

		searchField = new JTextField();
		searchPanel.add(searchField);
		searchField.setColumns(10);

		JComboBox searchBox = new JComboBox();
		searchPanel.add(searchBox);

		JButton searchBtn = new JButton("搜索");
		searchPanel.add(searchBtn);

		searchBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				ComboItem item = (ComboItem) searchBox.getSelectedItem();
				switch (item.getValue()) {
					case Contact.Search.NEIGHBORHOOD: searchByNeighborhood(searchField.getText()); break;
					case Contact.Search.RENTER_PHONE: searchByRenterPhone(searchField.getText()); break;
					case Contact.Search.ROOMER_PHONE: searchByRoomerPhone(searchField.getText()); break;
				}
			}
		});

		listPanel = new JPanel();
		mainPanel.add(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		headPanel = new JPanel();
		listPanel.add(headPanel);
		headPanel.setLayout(new GridLayout(0, 5, 0, 0));

		JLabel nameLabel = new JLabel("房主姓名");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("租客姓名");
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

		List<ContactEntity> contacts = ContactController.listAllByPage(page);
		renderList(contacts);

		/*** page button  ****/
		maxPage = ViewRecordController.countAll() / Parameter.NUM_HOUSE_PER_PAGE + 1;
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
				List<ContactEntity> contact = ContactController.listAllByPage(page);
				renderList(contact);
			}
		});

		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (page.equals(maxPage)) return;
				super.mouseClicked(e);
				page++;
				updateBtnState();
				List<ContactEntity> contacts = ContactController.listAllByPage(page);
				renderList(contacts);
			}
		});
	}

	private void searchByRoomerPhone(String text) {
		if (text.equals("")) return;
		List<ContactEntity> contacts = ContactController.listByRoomerPhone(text);
		renderList(contacts);
	}

	private void searchByRenterPhone(String text) {
		if (text.equals("")) return;
		List<ContactEntity> contacts = ContactController.listByRenterPhone(text);
		renderList(contacts);
	}

	private void searchByNeighborhood(String text) {
		if (text.equals("")) return;
		List<ContactEntity> contacts = ContactController.listFuzzyByNeighborhood(text);
		renderList(contacts);
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

	private void renderList(List<ContactEntity> contacts) {
		listPanel.removeAll();
		listPanel.add(headPanel);

		for (ContactEntity contact: contacts) {
			listPanel.add(new ListItemPanel(contact, frame));
		}

		for (int i = 0; i < Parameter.NUM_HOUSE_PER_PAGE - contacts.size(); i++) {
			listPanel.add(new JPanel());
		}

		listPanel.updateUI();
		listPanel.repaint();
	}

}
