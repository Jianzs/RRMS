package com.zwl.rrms.display.front.house;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.controller.CityController;
import com.zwl.rrms.controller.CountyController;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ProvinceController;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.common.FrameChange;
import com.zwl.rrms.display.common.MsgFrame;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ProvinceEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HouseSubmitFrame extends BaseFrame {

//	private JFrame frame;
	private JTextField neiField;
	private JComboBox provinceBox;
	private JComboBox cityBox;
	private JComboBox countyBox;
	private JTextField addressField;
	private JTextField priceField;
	private JSpinner maxCustomerSpinner;
	private JComboBox typeBox;
	private JComboBox freetimeBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseSubmitFrame window = new HouseSubmitFrame();
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
	public HouseSubmitFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel);
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel titlePanel = new JPanel();
		mainPanel.add(titlePanel);

		JLabel titleLabel = new JLabel("添加房屋");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(namePanel);

		JLabel nameLabel = new JLabel("小 区 名 称：");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		namePanel.add(nameLabel);

		neiField = new JTextField();
		neiField.setFont(new Font("Dialog", Font.BOLD, 18));
		namePanel.add(neiField);
		neiField.setColumns(16);

		JPanel addressPanel = new JPanel();
		mainPanel.add(addressPanel);
		addressPanel.setLayout(new GridLayout(2, 2, 0, 0));

		JPanel briefPanel = new JPanel();
		addressPanel.add(briefPanel);
		briefPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));

		JLabel addressLabel = new JLabel("地      址：");
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		briefPanel.add(addressLabel);

		provinceBox = new JComboBox();
		provinceBox.setFont(new Font("Dialog", Font.BOLD, 18));
		briefPanel.add(provinceBox);

		cityBox = new JComboBox();
		cityBox.setFont(new Font("Dialog", Font.BOLD, 18));
		briefPanel.add(cityBox);

		countyBox = new JComboBox();
		countyBox.setFont(new Font("Dialog", Font.BOLD, 18));
		briefPanel.add(countyBox);

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
		briefPanel.add(addressField);
		addressField.setFont(new Font("Dialog", Font.BOLD, 18));
		addressField.setColumns(20);

		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(typePanel);

		JLabel typeLabel = new JLabel("房 屋 类 型: ");
		typeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		typePanel.add(typeLabel);

		typeBox = new JComboBox();
		typeBox.setFont(new Font("Dialog", Font.BOLD, 18));
		typeBox.addItem(new ComboItem("平房", House.Type.BUNGALOW));
		typeBox.addItem(new ComboItem("楼房", House.Type.BUILDING));
		typeBox.addItem(new ComboItem("独立式住宅", House.Type.TENEMENT));
		typePanel.add(typeBox);

		JPanel maxCustomerPanel = new JPanel();
		maxCustomerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(maxCustomerPanel);

		JLabel maxLabel = new JLabel("最大租客数： ");
		maxLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		maxCustomerPanel.add(maxLabel);

		maxCustomerSpinner = new JSpinner();
		maxCustomerSpinner.setFont(new Font("Dialog", Font.BOLD, 18));
		maxCustomerSpinner.setValue(1);
		((JSpinner.DefaultEditor)maxCustomerSpinner.getEditor()).getTextField().setColumns(4);
		maxCustomerPanel.add(maxCustomerSpinner);

		maxCustomerSpinner.addChangeListener(e ->
				maxCustomerSpinner.setValue(Math.max(1, (Integer) maxCustomerSpinner.getValue())));

		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(pricePanel);

		JLabel priceLabel = new JLabel("房 屋 价 格：");
		priceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		priceLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		pricePanel.add(priceLabel);

		priceField = new JTextField();
		priceField.setColumns(16);
		priceField.setFont(new Font("Dialog", Font.BOLD, 18));
		pricePanel.add(priceField);

		JPanel freetimePanel = new JPanel();
		freetimePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(freetimePanel);

		JLabel freetimeLabel = new JLabel("可申请看房时间：");
		freetimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		freetimeLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		freetimePanel.add(freetimeLabel);

		freetimeBox = new JComboBox();
		freetimeBox.addItem(new ComboItem("周内", House.FreeTime.WEEK));
		freetimeBox.addItem(new ComboItem("周末", House.FreeTime.WEEKEND));
		freetimeBox.addItem(new ComboItem("任意", House.FreeTime.ANY_TIME));
		freetimeBox.setFont(new Font("Dialog", Font.BOLD, 18));
		freetimePanel.add(freetimeBox);

		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel);

		JButton submitBtn = new JButton("提交");
		submitBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(submitBtn);

		submitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				boolean succ = createHouse();
				if (succ) {
					new MsgFrame("创建成功").display();
				} else {
					new MsgFrame("创建失败").display();
				}
			}
		});

		JButton returnBtn = new JButton("返回");
		returnBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(returnBtn);

		returnBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				FrameChange.returnFrame(frame);
			}
		});
	}


	private boolean createHouse() {
		String neighborhood = neiField.getText();
		Integer provinceId = ((ComboItem)provinceBox.getSelectedItem()).getValue();
		Integer cityId = ((ComboItem)cityBox.getSelectedItem()).getValue();
		Integer countyId = ((ComboItem)countyBox.getSelectedItem()).getValue();
		String address = addressField.getText();
		Integer roomerId = Session.getInstance().getUser().getId();
		Double price = Double.valueOf(priceField.getText());

		HouseEntity.Builder builder = new HouseEntity.Builder();
		HouseEntity house = builder.setNeighborhood(neighborhood)
				.setProvinceId(provinceId)
				.setCityId(cityId)
				.setFreetime(((ComboItem) freetimeBox.getSelectedItem()).getValue())
				.setMaxCustomer((Integer) maxCustomerSpinner.getValue())
				.setType(((ComboItem)typeBox.getSelectedItem()).getValue())
				.setCountyId(countyId)
				.setAddress(address)
				.setRoomerId(roomerId)
				.setRent(price)
				.build();
		return HouseController.create(house);
	}


	private void addCountyItem(ComboItem item) {
		countyBox.removeAllItems();
		if (item.getValue().equals(-1)) return ;

		java.util.List<CountyEntity> countys = CountyController.listByCid(item.getValue());
		countys.forEach(county -> {
			countyBox.addItem(new ComboItem(county.getContent(), county.getId()));
		});
	}

	private void addCityItem(ComboItem item) {
		cityBox.removeAllItems();
		if (item.getValue().equals(-1)) return ;

		java.util.List<CityEntity> citys = CityController.listByPid(item.getValue());
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
