package com.zwl.rrms.display.back.view;

import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.constant.ViewRecord;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.view.panel.ListItemPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ViewListFrame extends BaseFrame {

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
					ViewListFrame window = new ViewListFrame();
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
	public ViewListFrame() {
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

		JLabel titleLabel = new JLabel("看房管理");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);

		JPanel bodyPanel = new JPanel();
		frame.getContentPane().add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

//		bodyPanel.add(new SubMenuPanel(frame), BorderLayout.NORTH);

		JPanel mainPanel = new JPanel();
		bodyPanel.add(mainPanel, BorderLayout.CENTER);
		mainPanel.setBorder(new EmptyBorder(20, 20, 0, 20));
		mainPanel.setLayout(new BorderLayout(0, 0));

		/**** main panel ****/

		JPanel searchPanel = new JPanel();
//		mainPanel.add(searchPanel, BorderLayout.NORTH);
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
		mainPanel.add(listPanel);
		listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));

		headPanel = new JPanel();
		listPanel.add(headPanel);
		headPanel.setLayout(new GridLayout(0, 6, 0, 0));

		JLabel nameLabel = new JLabel("小区名称");
		nameLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(nameLabel);

		JLabel roomerLabel = new JLabel("房主姓名");
		roomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(roomerLabel);

		JLabel stateLabel = new JLabel("房主确认状态");
		stateLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(stateLabel);

		JLabel delLabel = new JLabel("点击删除");
		delLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(delLabel);

		JLabel changeLabel = new JLabel("点击修改");
		changeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(changeLabel);

		JLabel detailLabel = new JLabel("查看详情");
		detailLabel.setFont(new Font("Dialog", Font.BOLD, 18));
		headPanel.add(detailLabel);

		List<ViewRecordEntity> views = ViewRecordController.listAll();
		renderList(views);


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
				List<ViewRecordEntity> views = ViewRecordController.listAllByPage(page);
				renderList(views);
			}
		});

		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (page.equals(maxPage)) return ;
				super.mouseClicked(e);
				page++;
				updateBtnState();
				List<ViewRecordEntity> views = ViewRecordController.listAllByPage(page);
				renderList(views);
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

	private void renderList(List<ViewRecordEntity> views) {
		listPanel.removeAll();
		listPanel.add(headPanel);

		for (ViewRecordEntity view: views) {
			listPanel.add(new ListItemPanel(view, frame));
		}

		for (int i = 0; i < Parameter.NUM_HOUSE_PER_PAGE - views.size(); i++) {
			listPanel.add(new JPanel());
		}

		listPanel.updateUI();
		listPanel.repaint();
	}


}
