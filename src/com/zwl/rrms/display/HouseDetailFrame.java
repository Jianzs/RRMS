package com.zwl.rrms.display;

import com.zwl.rrms.controller.*;
import com.zwl.rrms.entity.*;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.reflect.InvocationTargetException;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class HouseDetailFrame extends BaseFrame {
	private UserEntity roomer;
	private HouseEntity house;
	private ProvinceEntity province;
	private CityEntity city;
	private CountyEntity county;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HouseDetailFrame window = new HouseDetailFrame();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public HouseDetailFrame(HouseEntity house, Class<? extends JPanel> btnClass) {
		this.house = house;
		this.roomer = UserController.getUserById(house.getRoomerId());
		this.province = ProvinceController.getById(house.getProvinceId());
		this.city = CityController.getById(house.getCityId());
		this.county = CountyController.getById(house.getCountyId());

		try {
			initialize(btnClass);
		} catch (IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Initialize the contents of the frame.
	 * @param btnClass
	 */
	private void initialize(Class<? extends JPanel> btnClass) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
		frame = new JFrame();
		
		JPanel picPanel = new JPanel();
		frame.getContentPane().add(picPanel, BorderLayout.WEST);
		picPanel.setPreferredSize(new Dimension(400, 400));
		picPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 10, 10));

		JLabel picLabel = new JLabel("图片");
		picLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		picPanel.add(picLabel);

		JPanel roomerPanel = new JPanel();
		frame.getContentPane().add(roomerPanel, BorderLayout.SOUTH);
		roomerPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));
		roomerPanel.setLayout(new BorderLayout(0, 0));
		JPanel roomerInfoPanel = new JPanel();
		roomerInfoPanel.setBorder(BorderFactory.createEmptyBorder(10,20,10,20));
		roomerPanel.add(roomerInfoPanel, BorderLayout.NORTH);
		roomerInfoPanel.setLayout(new BoxLayout(roomerInfoPanel, BoxLayout.Y_AXIS));
		JLabel roomerNameLabel = new JLabel("房主姓名： ".concat(roomer.getName()));
		roomerNameLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		roomerInfoPanel.add(roomerNameLabel);
		roomerInfoPanel.add(Box.createVerticalGlue());
		JLabel roomerPhoneLabel = new JLabel("房主手机号： ".concat(roomer.getPhone()));
		roomerPhoneLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		roomerPhoneLabel.setHorizontalAlignment(SwingConstants.CENTER);
		roomerInfoPanel.add(roomerPhoneLabel);


		JPanel btnPanel = btnClass.getConstructor(JFrame.class).newInstance(frame);
//		JPanel btnPanel = new JPanel();
		roomerPanel.add(btnPanel, BorderLayout.SOUTH);
//		btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
//		btnPanel.setBorder(BorderFactory.createEmptyBorder(10,20,20,10));
//		btnPanel.add(Box.createHorizontalGlue());
//		JButton applySeeBtn = new JButton("申请看房");
//		applySeeBtn.setFont(new Font("Dialog", Font.BOLD, 20));
//		btnPanel.add(applySeeBtn);
//		btnPanel.add(Box.createHorizontalGlue());
//		JButton returnBtn = new JButton("返回");
//		returnBtn.setFont(new Font("Dialog", Font.BOLD, 20));
//		btnPanel.add(returnBtn);
//		btnPanel.add(Box.createHorizontalGlue());
		
		JPanel infoPanel = new JPanel();
		frame.getContentPane().add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		
		Component verticalGlue_1 = Box.createVerticalGlue();
		infoPanel.add(verticalGlue_1);
		
		JLabel houseNameLable = new JLabel("小区名称： ".concat(house.getNeighborhood()));
		houseNameLable.setFont(new Font("Dialog", Font.BOLD, 20));
		infoPanel.add(houseNameLable);
		infoPanel.add(Box.createVerticalGlue());
		JLabel houseAreaLabel = new JLabel("所属区域： ".concat(province.getContent()).concat(" ")
				.concat(city.getContent()).concat(" ").concat(county.getContent()));
		houseAreaLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		infoPanel.add(houseAreaLabel);
		infoPanel.add(Box.createVerticalGlue());
		JLabel houseAddressLabel = new JLabel("详细地址： ".concat(house.getAddress()));
		houseAddressLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		infoPanel.add(houseAddressLabel);
		infoPanel.add(Box.createVerticalGlue());
		JLabel housePriceLabel = new JLabel("房屋价格： ".concat(String.valueOf(house.getRent())));
		housePriceLabel.setFont(new Font("Dialog", Font.BOLD, 20));
		infoPanel.add(housePriceLabel);
		Component verticalGlue = Box.createVerticalStrut(100);
		infoPanel.add(verticalGlue);
		
		JPanel titlePanel = new JPanel();
		frame.getContentPane().add(titlePanel, BorderLayout.NORTH);
		
		JLabel titleLable = new JLabel("房屋详情");
		titleLable.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLable);
		frame.setBounds(300, 200, 1024, 768);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
