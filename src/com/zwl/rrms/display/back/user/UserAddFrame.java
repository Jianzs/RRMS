package com.zwl.rrms.display.back.user;

import com.zwl.rrms.constant.User;
import com.zwl.rrms.controller.CityController;
import com.zwl.rrms.controller.CountyController;
import com.zwl.rrms.controller.ProvinceController;
import com.zwl.rrms.controller.UserController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.user.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.ProvinceEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.DateUtil;

import java.awt.*;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.border.EmptyBorder;

public class UserAddFrame extends BaseFrame {

//	private JFrame frame;

	private JTextField addressField;

	private JComboBox provinceBox;
	private JComboBox cityBox;
	private JComboBox countyBox;
	private JTextField phoneField;
	private JTextField nameField;
	private JTextField passField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserAddFrame window = new UserAddFrame();
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
	public UserAddFrame() {
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
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		bodyPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(namePanel);

		JLabel nameLabel = new JLabel("真实姓名：");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		namePanel.add(nameLabel);

		nameField = new JTextField();
		nameField.setFont(new Font("Dialog", Font.BOLD, 18));
		namePanel.add(nameField);
		nameField.setColumns(16);

		JPanel addressPanel = new JPanel();
		mainPanel.add(addressPanel);
		addressPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		
		JLabel addressLabel = new JLabel("地      址：");
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		addressPanel.add(addressLabel);

		provinceBox = new JComboBox();
		provinceBox.setFont(new Font("Dialog", Font.BOLD, 18));
		addressPanel.add(provinceBox);

		cityBox = new JComboBox();
		cityBox.setFont(new Font("Dialog", Font.BOLD, 18));
		addressPanel.add(cityBox);

		countyBox = new JComboBox();
		countyBox.setFont(new Font("Dialog", Font.BOLD, 18));
		addressPanel.add(countyBox);

		addProvinceItem();

		provinceBox.addItemListener(e -> {
			ComboItem item = (ComboItem) e.getItem();
			System.out.println(item.getKey());
			addCityItem(item);
		});

		cityBox.addItemListener(e -> {
			ComboItem item = (ComboItem) e.getItem();
			addCountyItem(item);
		});

		addressField = new JTextField();
		addressField.setFont(new Font("Dialog", Font.BOLD, 18));
		addressPanel.add(addressField);
		addressField.setColumns(20);
		
		JPanel phonePanel = new JPanel();
		phonePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(phonePanel);

		JLabel phoneLabel = new JLabel("手  机  号：");
		phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		phonePanel.add(phoneLabel);

		phoneField = new JTextField();
		phoneField.setFont(new Font("Dialog", Font.BOLD, 18));
		phonePanel.add(phoneField);
		phoneField.setColumns(16);

		JPanel birthPanel = new JPanel();
		birthPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(birthPanel);

		JLabel birthLabel = new JLabel("出生年月：");
		birthLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		birthLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		birthPanel.add(birthLabel);

		JComboBox yearBox = new JComboBox();
		yearBox.setFont(new Font("Dialog", Font.BOLD, 18));
		birthPanel.add(yearBox);

		for (int i = 2018; i >= 1900; i--) {
			yearBox.addItem(new ComboItem(String.valueOf(i), i));
		}

		JComboBox monthBox = new JComboBox();
		monthBox.setFont(new Font("Dialog", Font.BOLD, 18));
		birthPanel.add(monthBox);

		for (int i = 1; i <= 12; i++) {
			monthBox.addItem(new ComboItem(String.valueOf(i), i));
		}

		JPanel genderPanel = new JPanel();
		genderPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(genderPanel);

		JLabel genderLabel = new JLabel("性      别：");
		genderLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(genderLabel);

		JRadioButton maleBtn = new JRadioButton("男", true);
		maleBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(maleBtn);

		JRadioButton femaleBtn = new JRadioButton("女");
		femaleBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		genderPanel.add(femaleBtn);

		ButtonGroup group = new ButtonGroup();
		group.add(maleBtn);
		group.add(femaleBtn);

		JPanel passPanel = new JPanel();
		passPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(passPanel);

		JLabel passLabel = new JLabel("密      码：");
		passLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		passPanel.add(passLabel);
		
		passField = new JTextField();
		passField.setFont(new Font("Dialog", Font.BOLD, 18));
		passPanel.add(passField);
		passField.setColumns(16);
		
		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel);
		
		JButton submitBtn = new JButton("提交");
		submitBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(submitBtn);

		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				String phone = phoneField.getText();
				String pass = passField.getText();
				String name = nameField.getText();
				String address = addressField.getText();
				Integer gender = femaleBtn.isSelected() ? User.Gender.FEMALE : User.Gender.MALE;
				Integer provinceId = ((ComboItem) provinceBox.getSelectedItem()).getValue();
				Integer cityId = ((ComboItem) cityBox.getSelectedItem()).getValue();
				Integer countyId = ((ComboItem) countyBox.getSelectedItem()).getValue();
				Integer year = ((ComboItem) yearBox.getSelectedItem()).getValue();
				Integer month = ((ComboItem) monthBox.getSelectedItem()).getValue();

				UserEntity.Builder builder = new UserEntity.Builder();
				builder.setName(name)
						.setPhone(phone)
						.setPassword(pass)
						.setProvinceId(provinceId)
						.setCityId(cityId)
						.setCountyId(countyId)
						.setAddress(address)
						.setGender(gender)
						.setBirthday(DateUtil.getTime(year, month, 1));
				if (UserController.register(builder.build())) {
					new MsgFrame("添加成功。").display();
				} else {
					new MsgFrame("添加失败。").display();
				}
			}
		});
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
