package com.zwl.rrms.display.back.contact;

import com.zwl.rrms.common.Session;
import com.zwl.rrms.constant.Contact;
import com.zwl.rrms.controller.ContactController;
import com.zwl.rrms.dao.ContactDao;
import com.zwl.rrms.display.back.common.MenuPanel;
import com.zwl.rrms.display.back.common.UserChoseFrame;
import com.zwl.rrms.display.back.contact.panel.SubMenuPanel;
import com.zwl.rrms.display.common.BaseFrame;
import com.zwl.rrms.display.common.ComboItem;
import com.zwl.rrms.entity.ContactEntity;
import com.zwl.rrms.entity.UserEntity;
import com.zwl.rrms.util.DateUtil;
import org.eclipse.swt.widgets.Combo;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ContactAddFrame extends BaseFrame {

//	private JFrame frame;

    private UserEntity roomer;
    private UserEntity renter;
    private JTextArea remarkText;
    private JComboBox startTimeYearBox;
    private JComboBox startTimeMonthBox;
    private JComboBox endTimeYearBox;
    private JComboBox endTimeMonthBox;
    private JTextField rentField;

    /**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContactAddFrame window = new ContactAddFrame();
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
	public ContactAddFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
	    Session.getInstance().setContact(true);
        frame = new JFrame();
        frame.setBounds(300, 200, 1024, 768);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout(0, 0));

        frame.getContentPane().add(new MenuPanel(frame), BorderLayout.WEST);

        JPanel titlePanel = new JPanel();
        frame.getContentPane().add(titlePanel, BorderLayout.NORTH);

        JLabel titleLabel = new JLabel("协议管理");
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

        /**** main panel ****/

        JPanel infoPanel = new JPanel();
        mainPanel.add(infoPanel, BorderLayout.CENTER);
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        
        JPanel startTimePanel = new JPanel();
        FlowLayout flowLayout = (FlowLayout) startTimePanel.getLayout();
        flowLayout.setAlignment(FlowLayout.LEFT);
        infoPanel.add(startTimePanel);
        
        JLabel startTimeLabel = new JLabel("开始时间: ");
        startTimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        startTimePanel.add(startTimeLabel);
        
        startTimeYearBox = new JComboBox();
        startTimeYearBox.setFont(new Font("Dialog", Font.BOLD, 18));
        startTimePanel.add(startTimeYearBox);

        for (int i = 2018; i >= 1900; i--) {
            startTimeYearBox.addItem(new ComboItem(String.valueOf(i), i));
        }

        JLabel lineLabel2 = new JLabel("-");
        lineLabel2.setFont(new Font("Dialog", Font.BOLD, 18));
        startTimePanel.add(lineLabel2);
        
        startTimeMonthBox = new JComboBox();
        startTimeMonthBox.setFont(new Font("Dialog", Font.BOLD, 18));
        startTimePanel.add(startTimeMonthBox);

        for (int i = 1; i <= 12; i++) {
            startTimeMonthBox.addItem(new ComboItem(String.valueOf(i), i));
        }

        JPanel endTimePanel = new JPanel();
        FlowLayout flowLayout_1 = (FlowLayout) endTimePanel.getLayout();
        flowLayout_1.setAlignment(FlowLayout.LEFT);
        infoPanel.add(endTimePanel);
        
        JLabel endTimeLabel = new JLabel("结束时间: ");
        endTimeLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        endTimePanel.add(endTimeLabel);
        
        endTimeYearBox = new JComboBox();
        endTimeYearBox.setFont(new Font("Dialog", Font.BOLD, 18));
        endTimePanel.add(endTimeYearBox);

        for (int i = 2018; i >= 1900; i--) {
            endTimeYearBox.addItem(new ComboItem(String.valueOf(i), i));
        }

        JLabel lineLable1 = new JLabel("-");
        lineLable1.setFont(new Font("Dialog", Font.BOLD, 18));
        endTimePanel.add(lineLable1);
        
        endTimeMonthBox = new JComboBox();
        endTimeMonthBox.setFont(new Font("Dialog", Font.BOLD, 18));
        endTimePanel.add(endTimeMonthBox);

        for (int i = 1; i <= 12; i++) {
            endTimeMonthBox.addItem(new ComboItem(String.valueOf(i), i));
        }

//        JPanel statePanel = new JPanel();
//        FlowLayout flowLayout_2 = (FlowLayout) statePanel.getLayout();
//        flowLayout_2.setAlignment(FlowLayout.LEFT);
//        infoPanel.add(statePanel);
//
//        JLabel stateLabel = new JLabel("状态");
//        statePanel.add(stateLabel);
//
//        JComboBox stateBox = new JComboBox();
//        statePanel.add(stateBox);
//
//        stateBox.add(new ComboItem("zhe"))

        JPanel roomerPanel = new JPanel();
        FlowLayout flowLayout_3 = (FlowLayout) roomerPanel.getLayout();
        flowLayout_3.setAlignment(FlowLayout.LEFT);
        infoPanel.add(roomerPanel);
        
        JLabel choseRoomerLabel = new JLabel("房      主: ");
        choseRoomerLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        roomerPanel.add(choseRoomerLabel);
        
        JButton choseRoomerBtn = new JButton("选择房主");
        choseRoomerBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        roomerPanel.add(choseRoomerBtn);

        choseRoomerBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setRole(Contact.Role.ROOMER);
                new UserChoseFrame().display();
            }
        });

        JPanel renterPanel = new JPanel();
        FlowLayout flowLayout_5 = (FlowLayout) renterPanel.getLayout();
        flowLayout_5.setAlignment(FlowLayout.LEFT);
        infoPanel.add(renterPanel);
        
        JLabel choseRenterLabel = new JLabel("租      客: ");
        choseRenterLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        renterPanel.add(choseRenterLabel);
        
        JButton choseRenterBtn = new JButton("选择租客");
        choseRenterBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        renterPanel.add(choseRenterBtn);

        choseRenterBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Session.getInstance().setRole(Contact.Role.RENTER);
                new UserChoseFrame().display();
            }
        });

        JPanel rentPanel = new JPanel();
        rentPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 5));
        infoPanel.add(rentPanel);

        JLabel rentLabel = new JLabel("租      金：");
        rentLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        rentPanel.add(rentLabel);

        rentField = new JTextField();
        rentField.setFont(new Font("Dialog", Font.BOLD, 18));
        rentPanel.add(rentField);

        JPanel remarkPanel = new JPanel();
        FlowLayout flowLayout_4 = (FlowLayout) remarkPanel.getLayout();
        flowLayout_4.setAlignment(FlowLayout.LEFT);
        infoPanel.add(remarkPanel);
        
        JLabel remarkLabel = new JLabel("详细信息: ");
        remarkLabel.setFont(new Font("Dialog", Font.BOLD, 18));
        remarkPanel.add(remarkLabel);
        
        remarkText = new JTextArea();
        remarkText.setFont(new Font("Dialog", Font.BOLD, 18));
        remarkPanel.add(remarkText);

        remarkText.setRows(4);
        remarkText.setColumns(20);

        JPanel btnPanel = new JPanel();
        mainPanel.add(btnPanel, BorderLayout.SOUTH);
        
        JButton submitBtn = new JButton("提交");
        submitBtn.setFont(new Font("Dialog", Font.BOLD, 18));
        btnPanel.add(submitBtn);

        submitBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                createNewContact();
            }
        });
	}

    private boolean createNewContact() {
        int roomerId = Session.getInstance().getAddContactRoomer().getId();
        int renterId = Session.getInstance().getAddContactRenter().getId();
        String detailText = remarkText.getText();
        Integer startTimeYear = ((ComboItem)startTimeYearBox.getSelectedItem()).getValue();
        Integer startTimeMonth = ((ComboItem)startTimeMonthBox.getSelectedItem()).getValue();
        Integer endTimeYear = ((ComboItem)endTimeYearBox.getSelectedItem()).getValue();
        Integer endTimeMonth = ((ComboItem)endTimeMonthBox.getSelectedItem()).getValue();
        Double rent = Double.valueOf(rentField.getText());
        ContactEntity.Builder builder = new ContactEntity.Builder();
        builder.setStartTime(DateUtil.getTime(startTimeYear, startTimeMonth, 1))
                .setEndTime(DateUtil.getTime(endTimeYear, endTimeMonth, 1))
                .setRemark(detailText)
                .setRenterId(renterId)
                .setRent(rent)
                .setRoomId(roomerId);
        return ContactController.create(builder.build());
    }

}
