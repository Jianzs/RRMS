package com.zwl.rrms.display.front.house;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.Parameter;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.controller.ViewRecordController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.front.house.panel.HouseMarketBriefPanel;
import com.zwl.rrms.display.front.house.panel.HouseMarketSortPanel;
import com.zwl.rrms.display.front.panel.UserMenuPanel;
import com.zwl.rrms.entity.HouseEntity;
import com.zwl.rrms.entity.ViewRecordEntity;

import java.awt.*;

import javax.swing.*;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;

public class HouseMarketFrame extends BaseFrame {
    private JPanel itemPanel;
    private JPanel headPanel;
	private int maxPage;
	private JButton prevBtn;
	private JButton nextBtn;
	private Integer page = 1;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HouseMarketFrame window = new HouseMarketFrame();
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
	public HouseMarketFrame() {
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

		frame.getContentPane().add(new UserMenuPanel(frame), BorderLayout.WEST);

		JPanel mainPanel = new JPanel();
		frame.getContentPane().add(mainPanel, BorderLayout.CENTER);
		mainPanel.setLayout(new BorderLayout(0, 0));
		
		JPanel titlePanel = new JPanel();
		titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
		mainPanel.add(titlePanel, BorderLayout.NORTH);

		// title
		JLabel titleLabel = new JLabel("房源市场");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		mainPanel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		bodyPanel.add(new HouseMarketSortPanel(this), BorderLayout.NORTH);

		itemPanel = new JPanel();
		itemPanel.setLayout(new GridLayout(8, 1));
		bodyPanel.add(itemPanel, BorderLayout.CENTER);
//		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

        headPanel = new JPanel();
        headPanel.setLayout(new GridLayout(1, 4));
        headPanel.setBorder(BorderFactory.createEmptyBorder(10,20,5,15));
        itemPanel.add(headPanel);

        JLabel picLabel = new JLabel("图片");
        picLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headPanel.add(picLabel);

        JLabel neiLabel = new JLabel("小区名称");
        neiLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headPanel.add(neiLabel);

        JLabel rentLabel = new JLabel("租金");
        rentLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headPanel.add(rentLabel);

        JLabel detailLabel = new JLabel("查看详情");
        detailLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        headPanel.add(detailLabel);

		// render house list
		Objects.requireNonNull(HouseController.listHouseByPage(1)).forEach(house -> {
			JPanel panelTmp = new HouseMarketBriefPanel(house, this.frame);
			itemPanel.add(panelTmp);
//			itemPanel.add(Box.createVerticalGlue());
		});

		/*** page button  ****/
		maxPage = HouseController.countActiveAll() / Parameter.NUM_HOUSE_PER_PAGE + 1;
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
				List<HouseEntity> houses = HouseController.listHouseByPage(page);
				renderList(houses);
			}
		});

		nextBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (page.equals(maxPage)) return ;
				super.mouseClicked(e);
				page++;
				updateBtnState();
				List<HouseEntity> houses = HouseController.listHouseByPage(page);
				renderList(houses);
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

	private void renderList(List<HouseEntity> houses) {
		itemPanel.removeAll();
		itemPanel.add(headPanel);

		itemPanel.add(headPanel);
		houses.forEach((house) -> {
			JPanel panelTmp = new HouseMarketBriefPanel(house, this.frame);
			itemPanel.add(panelTmp);
//            itemPanel.add(Box.createVerticalGlue());
		});

		itemPanel.updateUI();
		itemPanel.repaint();
	}

    public void freshItem() {
        List<HouseEntity> houses = HouseController.listHouseByPage(1);
        houses.sort((h1, h2) -> {
            double res = h1.getRent() - h2.getRent();
            if (!Session.getInstance().getHouseMarketUpSort()) res *= -1;
            if (res > 0) {
                return 1;
            } else if (res < 0) {
                return -1;
            } else {
                return 0;
            }
        });

		renderList(houses);
    }
}