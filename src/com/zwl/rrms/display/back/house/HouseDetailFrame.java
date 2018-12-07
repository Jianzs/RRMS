package com.zwl.rrms.display.back.house;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.House;
import com.zwl.rrms.controller.*;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.house.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HouseDetailFrame extends BaseFrame {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseDetailFrame window = new HouseDetailFrame();
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
	public HouseDetailFrame() {
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


		/**** main panel  ****/
		HouseEntity house = Session.getInstance().getBackHouseDetail();
		ProvinceEntity province = ProvinceController.getById(house.getProvinceId());
		CityEntity city = CityController.getById(house.getCityId());
		CountyEntity county = CountyController.getById(house.getCountyId());
		UserEntity roomer = UserController.getUserById(house.getRoomerId());

		mainPanel.setLayout(new BorderLayout(0, 0));
		mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

		JPanel infoPanel = new JPanel();
		mainPanel.add(infoPanel);
//		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		infoPanel.setLayout(new GridLayout(10, 0, 10, 10));

		JLabel neiLabel = new JLabel("小区名称: ".concat(house.getNeighborhood()));
		neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(neiLabel);

		JLabel addressLabel = new JLabel("地      址: ".concat(province.getContent())
				.concat(city.getContent()).concat(county.getContent()).concat(house.getAddress()));
		addressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(addressLabel);
		
		JLabel typeLabel = new JLabel("类型: ".concat(type2str(house.getType())));
		typeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(typeLabel);
		
		JLabel maxCustomerLabel = new JLabel("最大组客数: ".concat(String.valueOf(house.getMaxCustomer())));
		maxCustomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(maxCustomerLabel);
		
		JLabel stateLabel = new JLabel("状      态: ".concat(state2str(house.getState())));
		stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(stateLabel);
		
		JLabel freetimeLabel = new JLabel("可申请看房时间: ".concat(freetime2str(house.getFreetime())));
		freetimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(freetimeLabel);
		
		JLabel serviceChargeLabel = new JLabel("手  续  费: ".concat(String.valueOf(house.getServiceCharge())));
		serviceChargeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(serviceChargeLabel);

		JLabel rentLabel = new JLabel("价      格: ".concat(String.valueOf(house.getRent())));
		rentLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(rentLabel);

		JLabel roomerNameLabel = new JLabel("房主姓名： ".concat(String.valueOf(roomer.getName())));
		roomerNameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(roomerNameLabel);

		JLabel roomerPhoneLabel = new JLabel("房主电话： ".concat(String.valueOf(roomer.getPhone())));
		roomerPhoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(roomerPhoneLabel);
	}

	private String freetime2str(Integer freetime) {
		switch (freetime) {
			case House.FreeTime.ANY_TIME: return "随时";
			case House.FreeTime.WEEK: return "周内";
			case House.FreeTime.WEEKEND: return "周末";
		}
		return "";
	}

	private String state2str(Integer state) {
		switch (state) {
			case House.State.NOT_RENT: return "未租用";
			case House.State.RENTED: return "已租用";
			case House.State.UNPAID: return "未付手续费";
			case House.State.DELETED: return "已删除";
		}
		return "null";
	}

	private String type2str(Integer type) {
		switch (type) {
			case House.Type.BUNGALOW: return "平房";
			case House.Type.BUILDING: return "楼房";
			case House.Type.TENEMENT: return "独立住宅";
		}
		return "null";
	}

}
