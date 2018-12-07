package com.zwl.rrms.display.front.user;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.controller.CityController;
import com.zwl.rrms.controller.CountyController;
import com.zwl.rrms.controller.ProvinceController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.ProvinceEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.DateUtil;

import java.awt.EventQueue;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class UserInfoFrame extends BaseFrame {
	private final Integer CHANGE_STATE = 1;
	private final Integer SUBMIT_STATE = 2;


	//	private JFrame frame;
	private JTextField nameField;
	private JTextField phoneField;
	private JPasswordField passField;
	private JTextField addressField;

	private JRadioButton maleBtn;
	private JRadioButton femaleBtn;

	private JComboBox provinceBox;
	private JComboBox cityBox;
	private JComboBox countyBox;

	private JComboBox yearBox;
	private JComboBox monthBox;

	private Integer btnState = CHANGE_STATE;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInfoFrame window = new UserInfoFrame();
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
	public UserInfoFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("个人中心");
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));

		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

		JLabel titleLabel = new JLabel("个人中心");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);

		JPanel bodyPanel = new JPanel();
		bodyPanel.setBorder(new EmptyBorder(0, 100, 0, 0));
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BoxLayout(bodyPanel, BoxLayout.Y_AXIS));

		JPanel namePanel = new JPanel();
		bodyPanel.add(namePanel);
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel nameLabel = new JLabel("真实姓名：");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		namePanel.add(nameLabel);

		nameField = new JTextField();
		namePanel.add(nameField);
		nameField.setColumns(16);

		JPanel phonePanel = new JPanel();
		bodyPanel.add(phonePanel);
		phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel phoneLabel = new JLabel("手机号：");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		phonePanel.add(phoneLabel);

		phoneField = new JTextField();
		phonePanel.add(phoneField);
		phoneField.setColumns(16);

		JPanel passPanel = new JPanel();
		bodyPanel.add(passPanel);
		passPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel passLabel = new JLabel("密码：");
		passLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		passPanel.add(passLabel);

		passField = new JPasswordField();
		passField.setColumns(16);
		passPanel.add(passField);

		JPanel genderPanel = new JPanel();
		bodyPanel.add(genderPanel);
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel genderLabel = new JLabel("性别：");
		genderLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(genderLabel);

		maleBtn = new JRadioButton("男", true);
		maleBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(maleBtn);

		femaleBtn = new JRadioButton("女");
		femaleBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(femaleBtn);

		ButtonGroup group = new ButtonGroup();
		group.add(maleBtn);
		group.add(femaleBtn);


		JPanel addressPanel = new JPanel();
		bodyPanel.add(addressPanel);
		addressPanel.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel briefPanel = new JPanel();
		addressPanel.add(briefPanel);
		briefPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel addressLabel = new JLabel("地址：");
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		briefPanel.add(addressLabel);

		provinceBox = new JComboBox();
		briefPanel.add(provinceBox);

		cityBox = new JComboBox();
		briefPanel.add(cityBox);

		countyBox = new JComboBox();
		briefPanel.add(countyBox);

		addProvinceItem();

		provinceBox.addItemListener(e -> {
			ComboItem item = (ComboItem) e.getItem();
			addCityItem(item);
		});

		cityBox.addItemListener(e -> {
			ComboItem item = (ComboItem) e.getItem();
			addCountyItem(item);
		});

		JPanel detailPanel = new JPanel();
		detailPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		addressPanel.add(detailPanel);

		addressField = new JTextField();
		detailPanel.add(addressField);
		addressField.setColumns(20);

		JPanel birthPanel = new JPanel();
		bodyPanel.add(birthPanel);
		birthPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel birthLabel = new JLabel("出生年月：");
		birthLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		birthPanel.add(birthLabel);

		yearBox = new JComboBox();
		birthPanel.add(yearBox);

		for (int i = 2018; i >= 1900; i--) {
			yearBox.addItem(new ComboItem(String.valueOf(i), i));
		}

		monthBox = new JComboBox();
		birthPanel.add(monthBox);

		for (int i = 1; i <= 12; i++) {
			monthBox.addItem(new ComboItem(String.valueOf(i), i));
		}

		JPanel btnPanel = new JPanel();
		frame.getContentPane().add(btnPanel, BorderLayout.SOUTH);

		JButton changeBtn = new JButton("修改");
		changeBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(changeBtn);

		JButton returnBtn = new JButton("返回");
		returnBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(returnBtn);

		changeBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (btnState.equals(SUBMIT_STATE)) {
					String phone = phoneField.getText();
					String pass = String.valueOf(passField.getPassword());
					if (pass.equals(""))
						new MsgFrame("密码不能为空").display();
					String name = phoneField.getText();
					String address = addressField.getText();
					Integer gender = femaleBtn.isSelected() ? User.Gender.FEMALE : User.Gender.MALE;
					Integer provinceId = ((ComboItem) provinceBox.getSelectedItem()).getValue();
					Integer cityId = ((ComboItem) cityBox.getSelectedItem()).getValue();
					Integer countyId = ((ComboItem) countyBox.getSelectedItem()).getValue();
					Integer year = ((ComboItem) yearBox.getSelectedItem()).getValue();
					Integer month = ((ComboItem) monthBox.getSelectedItem()).getValue();

					UserEntity.Builder builder = new UserEntity.Builder();
					builder.setId(Session.getInstance().getUser().getId())
							.setName(name)
							.setPhone(phone)
							.setPassword(pass)
							.setProvinceId(provinceId)
							.setCityId(cityId)
							.setCountyId(countyId)
							.setAddress(address)
							.setGender(gender)
							.setBirthday(DateUtil.getTime(year, month, 1));
					if (UserController.update(builder.build())) {
						new MsgFrame("修改成功。").display();
					} else {
						new MsgFrame("修改失败，请重新尝试。").display();
					}
				} else {
					btnState = SUBMIT_STATE;
					changeBtn.setText("提交");

					nameField.setEnabled(true);
//					phoneField.setEnabled(true);
					passField.setEnabled(true);
					passField.setText("");
					addressField.setEnabled(true);

					maleBtn.setEnabled(true);
					femaleBtn.setEnabled(true);

					provinceBox.setEnabled(true);
					cityBox.setEnabled(true);
					countyBox.setEnabled(true);

					yearBox.setEnabled(true);
					monthBox.setEnabled(true);
				}
			}
		});

		returnBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.returnFrame(frame);
			}
		});

		UserEntity user = Session.getInstance().getUser();
		int provinceIndex = 0;
		for (int i = 0; i < provinceBox.getItemCount(); i++) {
			if (((ComboItem)provinceBox.getItemAt(i)).getValue().equals(user.getProvinceId())) {
				provinceIndex = i;
				break;
			}
		}

		provinceBox.setSelectedIndex(provinceIndex);
//		addCityItem((ComboItem) provinceBox.getItemAt(provinceIndex));

		int cityIndex = 0;
		for (int i = 0; i < cityBox.getItemCount(); i++) {
			if (((ComboItem)cityBox.getItemAt(i)).getValue().equals(user.getCityId())) {
				cityIndex = i;
				break;
			}
		}

		if (cityBox.getItemCount() != 0)
			cityBox.setSelectedIndex(cityIndex);
		addCountyItem((ComboItem) cityBox.getItemAt(cityIndex));

		int countyIndex = 0;
		for (int i = 0; i < countyBox.getItemCount(); i++) {
			if (((ComboItem)countyBox.getItemAt(i)).getValue().equals(user.getCountyId())) {
				countyIndex = i;
				break;
			}
		}

		countyBox.setSelectedIndex(countyIndex);

		int yearIndex = 0;
		for (int i = 0; i < yearBox.getItemCount(); i++) {
			if (((ComboItem)yearBox.getItemAt(i)).getValue().equals(DateUtil.getYear(user.getBirthday()))) {
				yearIndex = i;
				break;
			}
		}

		int monthIndex = 0;
		for (int i = 0; i < monthBox.getItemCount(); i++) {
			if (((ComboItem)monthBox.getItemAt(i)).getValue().equals(DateUtil.getMonth(user.getBirthday()))) {
				monthIndex = i;
				break;
			}
		}

		nameField.setText(user.getName());
		phoneField.setText(user.getPhone());
		passField.setText(user.getPassword());
		addressField.setText(user.getAddress());
		maleBtn.setSelected(user.getGender().equals(User.Gender.MALE));
		femaleBtn.setSelected(user.getGender().equals(User.Gender.FEMALE));
		yearBox.setSelectedIndex(yearIndex);
		monthBox.setSelectedIndex(monthIndex);

		nameField.setEnabled(false);
		phoneField.setEnabled(false);
		passField.setEnabled(false);
		addressField.setEnabled(false);
		maleBtn.setEnabled(false);
		femaleBtn.setEnabled(false);
		provinceBox.setEnabled(false);
		cityBox.setEnabled(false);
		countyBox.setEnabled(false);
		yearBox.setEnabled(false);
		monthBox.setEnabled(false);
	}

	private void addCountyItem(ComboItem item) {
		countyBox.removeAllItems();
		if (item.getValue().equals(-1)) return ;

		List<CountyEntity> countys = CountyController.listByCid(item.getValue());
		countys.forEach(county -> {
			countyBox.addItem(new ComboItem(county.getContent(), county.getId()));
		});
	}

	private void addCityItem(ComboItem item) {
		cityBox.removeAllItems();
		if (item.getValue().equals(-1)) return ;

		List<CityEntity> citys = CityController.listByPid(item.getValue());
		citys.forEach(city -> {
			cityBox.addItem(new ComboItem(city.getContent(), city.getId()));
		});

		if (citys.size() > 0)
			addCountyItem(new ComboItem("null", citys.get(0).getId()));
		else
			addCountyItem(new ComboItem("null", -1));
	}

	private void addProvinceItem() {
		List<ProvinceEntity> provinces = ProvinceController.listAll();
		provinces.forEach((province) -> {
			provinceBox.addItem(new ComboItem(province.getContent(), province.getId()));
		});
		if (provinces.size() > 0)
			addCityItem(new ComboItem("null", provinces.get(0).getId()));
		else
			addCityItem(new ComboItem("null", -1));
	}
}
