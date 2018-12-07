package com.zwl.rrms.display.front.house;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.controller.ContactController;
import com.zwl.rrms.controller.HouseController;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.front.house.panel.HouseMarketBriefPanel;
import com.zwl.rrms.display.front.house.panel.HouseMarketSortPanel;
import com.zwl.rrms.display.front.house.panel.RentHouseBriefPanel;
import com.zwl.rrms.display.front.panel.UserMenuPanel;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.HouseEntity;

public class RentHouseFrame extends BaseFrame {
    private JPanel itemPanel;

//	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RentHouseFrame window = new RentHouseFrame();
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
	public RentHouseFrame() {
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
		JLabel titleLabel = new JLabel("租房记录");
		titleLabel.setFont(new Font("Dialog", Font.BOLD, 32));
		titlePanel.add(titleLabel);
		
		JPanel bodyPanel = new JPanel();
		mainPanel.add(bodyPanel, BorderLayout.CENTER);
		bodyPanel.setLayout(new BorderLayout(0, 0));

		bodyPanel.add(new HouseMarketSortPanel(this), BorderLayout.NORTH);

		itemPanel = new JPanel();
		itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.Y_AXIS));
		bodyPanel.add(itemPanel, BorderLayout.CENTER);
//		panel_5.setLayout(new BoxLayout(panel_5, BoxLayout.Y_AXIS));

		// render house list
		ContactController.listContactByPage(1).forEach(contact -> {
			JPanel panelTmp = new RentHouseBriefPanel(contact, this.frame);
			itemPanel.add(panelTmp);
			itemPanel.add(Box.createVerticalGlue());
		});
	}

    public void freshItem() {
        itemPanel.removeAll();
        List<ContactEntity> contacts = ContactController.listContactByPage(1);
        contacts.sort((h1, h2) -> {
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

        contacts.forEach(contact -> {
            JPanel panelTmp = new RentHouseBriefPanel(contact, this.frame);
            itemPanel.add(panelTmp);
            itemPanel.add(Box.createVerticalGlue());
        });
        itemPanel.updateUI();
        itemPanel.repaint();
    }
}
