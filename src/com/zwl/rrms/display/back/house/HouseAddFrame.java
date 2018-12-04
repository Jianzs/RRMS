package com.zwl.rrms.display.back.house;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.controller.*;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.common.UserChoseFrame;
import com.zwl.rrms.display.back.house.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.display.common.MsgFrame;
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

public class HouseAddFrame extends BaseFrame {

//	private JFrame frame;

	private HouseEntity house;

	private JTextField addressField;

	private JComboBox provinceBox;
	private JComboBox cityBox;
	private JComboBox countyBox;
	private JTextField neiField;
	private JTextField priceField;
	private JComboBox typeBox;
	private JSpinner maxCustomerSpinner;
	private JButton submitBtn;
	private JComboBox stateBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseAddFrame window = new HouseAddFrame();
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
	public HouseAddFrame() {
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

		JLabel titleLabel = new JLabel("房屋管理");
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

		/***** main panel   *****/

		JPanel namePanel = new JPanel();
		namePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(namePanel);

		JLabel nameLabel = new JLabel("小区名称：");
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

		JLabel addressLabel = new JLabel("地址：");
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

		JPanel roomerPanel = new JPanel();
		roomerPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(roomerPanel);

		JLabel roomerLabel = new JLabel("房主：");
		roomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		roomerPanel.add(roomerLabel);

		JButton choseBtn = new JButton("选择房主");
		choseBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		roomerPanel.add(choseBtn);

		choseBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				new UserChoseFrame().display();
			}
		});

		JPanel typePanel = new JPanel();
		typePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(typePanel);

		JLabel typeLabel = new JLabel("房屋类型");
		typeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		typePanel.add(typeLabel);

		typeBox = new JComboBox();
		typeBox.setFont(new Font("Dialog", Font.BOLD, 18));
		typeBox.addItem(new ComboItem("平房", House.Type.BUNGALOW));
		typeBox.addItem(new ComboItem("楼房", House.Type.BUILDING));
		typeBox.addItem(new ComboItem("独立式住宅", House.Type.TENEMENT));
		typePanel.add(typeBox);

		JPanel statePanel = new JPanel();
		statePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(statePanel);

		JLabel stateLabel = new JLabel("状态");
		stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		statePanel.add(stateLabel);

		stateBox = new JComboBox();
		stateBox.setFont(new Font("Dialog", Font.BOLD, 18));
		stateBox.addItem(new ComboItem("未付手续费", House.State.UNPAID));
		stateBox.addItem(new ComboItem("未租赁", House.State.NOT_RENT));
		stateBox.addItem(new ComboItem("已租赁", House.State.RENTED));
		stateBox.addItem(new ComboItem("已删除", House.State.DELETED));
		statePanel.add(stateBox);


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

		JPanel pricePanel = new JPanel();
		pricePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		mainPanel.add(pricePanel);

		JLabel priceLabel = new JLabel("房屋价格：");
		priceLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		priceLabel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
		pricePanel.add(priceLabel);

		priceField = new JTextField();
		priceField.setColumns(16);
		priceField.setFont(new Font("Dialog", Font.BOLD, 18));
		pricePanel.add(priceField);

		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel);

		submitBtn = new JButton("提交");
		submitBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(submitBtn);



		if (!renderChange()) {
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
		}
	}

	private boolean renderChange() {
		boolean isModify = Session.getInstance().isModify();
		Session.getInstance().setModify(false);
		if (!isModify) return false;
		else {
			ProvinceEntity province;
			CityEntity city;
			CountyEntity county;
			house = Session.getInstance().getBackHouseDetail();
			province = ProvinceController.getById(house.getProvinceId());
			city = CityController.getById(house.getCityId());
			county = CountyController.getById(house.getCountyId());

			Session.getInstance().setSelectedUser(UserController.getUserById(house.getRoomerId()));

			neiField.setText(house.getNeighborhood());
			provinceBox.setSelectedIndex(province.getId() - 1);
			cityBox.setSelectedIndex(city.getId() - 1);
			countyBox.setSelectedIndex(county.getId() - 1);
			addressField.setText(house.getAddress());
			priceField.setText(String.valueOf(house.getRent()));
			typeBox.setSelectedIndex(house.getType() - 1);
			maxCustomerSpinner.setValue(house.getMaxCustomer());

			submitBtn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					super.mouseClicked(e);
					boolean succ = updateHouse();
					if (succ) {
						new MsgFrame("修改成功").display();
					} else {
						new MsgFrame("修改失败").display();
					}
				}
			});

			return  true;
		}
	}

	private boolean updateHouse() {
		HouseEntity en = makeHouse();
		en.setId(house.getId());
		return HouseController.update(en);
	}

	private boolean createHouse() {

		return HouseController.create(makeHouse());
	}

	private HouseEntity makeHouse() {
		String neighborhood = neiField.getText();
		Integer provinceId = ((ComboItem)provinceBox.getSelectedItem()).getValue();
		Integer cityId = ((ComboItem)cityBox.getSelectedItem()).getValue();
		Integer countyId = ((ComboItem)countyBox.getSelectedItem()).getValue();
		String address = addressField.getText();
		Integer roomerId = Session.getInstance().getSelectedUser().getId();
		Double price = Double.valueOf(priceField.getText());

		HouseEntity.Builder builder = new HouseEntity.Builder();
		return builder.setNeighborhood(neighborhood)
				.setProvinceId(provinceId)
				.setCityId(cityId)
				.setMaxCustomer((Integer) maxCustomerSpinner.getValue())
				.setType(((ComboItem)typeBox.getSelectedItem()).getValue())
				.setCountyId(countyId)
				.setAddress(address)
				.setState(((ComboItem) stateBox.getSelectedItem()).getValue())
				.setRoomerId(roomerId)
				.setRent(price)
				.build();
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
