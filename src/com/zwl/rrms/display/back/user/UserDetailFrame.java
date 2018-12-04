package com.zwl.rrms.display.back.user;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.User;
import com.zwl.rrms.controller.CityController;
import com.zwl.rrms.controller.CountyController;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ProvinceController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.user.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.CityEntity;
import com.zwl.rrms.entity.CountyEntity;
import com.zwl.rrms.entity.ProvinceEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.DateUtil;
import org.eclipse.swt.SWT;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class UserDetailFrame extends BaseFrame {

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserDetailFrame window = new UserDetailFrame();
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
	public UserDetailFrame() {
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

		JPanel infoPanel = new JPanel();
		mainPanel.add(infoPanel, BorderLayout.CENTER);
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));

		UserEntity user = Session.getInstance().getBackUserDetail();
		ProvinceEntity province = ProvinceController.getById(user.getProvinceId());
		CityEntity city = CityController.getById(user.getCityId());
		CountyEntity county = CountyController.getById(user.getCountyId());
		Integer houseCount = HouseController.countByUser(user);

		JLabel nameLabel = new JLabel("姓名: ".concat(user.getName()));
        nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(nameLabel);

		JLabel phoneLabel = new JLabel("手机号".concat(user.getPhone()));
        phoneLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(phoneLabel);

		JLabel addressLabel = new JLabel("地址".concat(province.getContent())
				.concat(city.getContent()).concat(county.getContent()).concat(user.getAddress()));
        addressLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(addressLabel);

		JLabel birthLabel = new JLabel("出生年月".concat(DateUtil.getYear(user.getBirthday()) + "-" +
				DateUtil.getMonth(user.getBirthday())));
        birthLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(birthLabel);

		JLabel houseLabel = new JLabel("房产数".concat(String.valueOf(houseCount)));
        houseLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(houseLabel);

		JLabel stateLabel = new JLabel("状态".concat(state2str(user.getState())));
        stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(stateLabel);

		JLabel genderLabel = new JLabel("性别".concat(gender2str(user.getGender())));
        genderLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		infoPanel.add(genderLabel);

		JPanel btnPanel = new JPanel();
		mainPanel.add(btnPanel, BorderLayout.SOUTH);

		JButton houseBtn = new JButton("查看该用户相关房产信息");
        houseBtn.setFont(new Font("Dialog", Font.BOLD, 18));
		btnPanel.add(houseBtn);
	}

	private String gender2str(Integer gender) {
		return gender.equals(User.Gender.MALE) ? "男" : "女";
	}

	private String state2str(Integer state) {
		switch (state) {
			case User.State.NORMAL: return "正常";
			case User.State.DELETED: return "已被删除";
		}
		return "null";
	}

}
