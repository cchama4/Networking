package com.uic.ids520;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;

public class CourseRegistration extends JFrame {

	private JPanel contentPane;
	public JComboBox comboBox = new JComboBox();
	private ArrayList<JCheckBox> checkBoxes=new ArrayList<JCheckBox>();
	private Connection conn;
	private String[] courses;
	private static String userName="";
	private PreparedStatement pst = null;
	private JFrame frameLogin;
	private static UserBean userBean;
	private static Socket clientSocket;
	public void setFrameLogin(JFrame frameLogin) {
		this.frameLogin = frameLogin;
	}

	public void setUserName(String username) {
		this.userName = username;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CourseRegistration frame = new CourseRegistration(userBean,clientSocket);
					if(!userName.equals("")){
					frame.setVisible(true);
					}
					else{
						JOptionPane.showMessageDialog(null, "Please Login!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public CourseRegistration(UserBean userBean, Socket clientSocket) {
		this.clientSocket=clientSocket;
		this.userBean=userBean;
		int count = 0;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 200, 650, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(204, 255, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JList list = new JList();
		list.setBounds(184, 202, 99, -42);
		contentPane.add(list);
		
		JLabel lblChooseMajor = new JLabel("Choose your major");
		lblChooseMajor.setBounds(211, 19, 131, 16);
		contentPane.add(lblChooseMajor);
		
		
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("ACC 101 - Introduction to Accounting");	
		chckbxNewCheckBox.setBounds(27, 75, 289, 23);
		contentPane.add(chckbxNewCheckBox);
		checkBoxes.add(chckbxNewCheckBox);
		
		JCheckBox chckbxFin_1 = new JCheckBox("FIN 301 - Advanced Finance");
		chckbxFin_1.setEnabled(false);
		chckbxFin_1.setBounds(27, 328, 248, 23);
		contentPane.add(chckbxFin_1);
		checkBoxes.add(chckbxFin_1);
		
		JCheckBox chckbxAcc = new JCheckBox("ACC 201 - Intermediate Accounting");
		chckbxAcc.setBounds(27, 124, 328, 23);
		contentPane.add(chckbxAcc);
		checkBoxes.add(chckbxAcc);
		
		JCheckBox chckbxAcc_1 = new JCheckBox("ACC 301 - Advanced Accounting");
		chckbxAcc_1.setBounds(27, 179, 248, 23);
		contentPane.add(chckbxAcc_1);
		checkBoxes.add(chckbxAcc_1);
		
		JCheckBox chckbxFinIntroduction = new JCheckBox("FIN 101- Introduction to Finance");
		chckbxFinIntroduction.setBounds(27, 226, 248, 23);
		contentPane.add(chckbxFinIntroduction);
		checkBoxes.add(chckbxFinIntroduction);
		
		JCheckBox chckbxFin = new JCheckBox("FIN - 201 - Intermediate Finance");
		chckbxFin.setBounds(27, 276, 248, 23);
		contentPane.add(chckbxFin);
		checkBoxes.add(chckbxFin);
		
		JCheckBox chckbxBus_1 = new JCheckBox("BUS 401 - Business Strategy");
		chckbxBus_1.setBounds(374, 276, 270, 23);
		contentPane.add(chckbxBus_1);
		checkBoxes.add(chckbxBus_1);
		
		JCheckBox chckbxBus = new JCheckBox("BUS 101 - Introduction to Business");
		chckbxBus.setBounds(374, 226, 257, 23);
		contentPane.add(chckbxBus);
		checkBoxes.add(chckbxBus);
		
		JCheckBox chckbxMkt = new JCheckBox("MKT 101 - Introduction to Marketing");
		chckbxMkt.setBounds(374, 75, 270, 23);
		contentPane.add(chckbxMkt);
		checkBoxes.add(chckbxMkt);
		
		JCheckBox chckbxAdvancedMarketing = new JCheckBox("MKT 301 - Advanced Marketing");
		chckbxAdvancedMarketing.setBounds(374, 179, 257, 23);
		contentPane.add(chckbxAdvancedMarketing);
		checkBoxes.add(chckbxAdvancedMarketing);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("MKT 201 - Intermediate Marketing");
		chckbxNewCheckBox_1.setBounds(374, 124, 257, 23);
		contentPane.add(chckbxNewCheckBox_1);
		checkBoxes.add(chckbxNewCheckBox_1);

		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String x = comboBox.getSelectedItem().toString();
				if(x == "Accounting" ){
					chckbxNewCheckBox.setEnabled(true);
					chckbxAcc.setEnabled(true);
					chckbxAcc_1.setEnabled(true);
					chckbxMkt.setEnabled(true);
					chckbxFinIntroduction.setEnabled(true);
					chckbxBus.setEnabled(true);
					chckbxBus_1.setEnabled(true);
					
					chckbxFin.setEnabled(false);
					chckbxFin_1.setEnabled(false);
					chckbxAdvancedMarketing.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
				}
				if(x == "Finance" ){
					chckbxNewCheckBox.setEnabled(true);
					chckbxFin.setEnabled(true);
					chckbxFin_1.setEnabled(true);
					chckbxMkt.setEnabled(true);
					chckbxFinIntroduction.setEnabled(true);
					chckbxBus.setEnabled(true);
					chckbxBus_1.setEnabled(true);
					
					chckbxAdvancedMarketing.setEnabled(false);
					chckbxNewCheckBox_1.setEnabled(false);
					chckbxAcc.setEnabled(false);
					chckbxAcc_1.setEnabled(false);
				}
				if(x == "Marketing" ){
					chckbxNewCheckBox.setEnabled(true);
					chckbxAdvancedMarketing.setEnabled(true);
					chckbxNewCheckBox_1.setEnabled(true);
					chckbxMkt.setEnabled(true);
					chckbxFinIntroduction.setEnabled(true);
					chckbxBus.setEnabled(true);
					chckbxBus_1.setEnabled(true);
					
					chckbxAcc.setEnabled(false);
					chckbxAcc_1.setEnabled(false);
					chckbxFin.setEnabled(false);
					chckbxFin_1.setEnabled(false);
				}
			}
		});
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Accounting", "Marketing", "Finance"}));
		comboBox.setBounds(341, 15, 130, 27);
		contentPane.add(comboBox);		
		
		JButton btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ObjectOutputStream oos;
				try {
					userBean.setPageName("LogOut");
					oos = new ObjectOutputStream(clientSocket.getOutputStream());
					oos.writeObject(userBean);
					oos.flush();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				setVisible(false);
				JOptionPane.showMessageDialog(null, "You are now logged out");
				frameLogin.setVisible(true);
			}
		});
		btnLogOut.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		btnLogOut.setBounds(527, 14, 117, 29);
		contentPane.add(btnLogOut);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=0;
				courses=new String[5];
				for(JCheckBox checkBox : checkBoxes){
					if(checkBox.isSelected()){
						if(i<5)
						courses[i]=checkBox.getText();
						i++;
					}
				}
				if(i==0){
					JOptionPane.showMessageDialog(null, "You have not selected any course");		
				}
				else if(i>=6)
				{
					JOptionPane.showMessageDialog(null, "You can't select more than 5 courses");
				}
				else
				{
					    System.out.println(userName);
						userBean.setCourse0(courses[0]);
						userBean.setCourse1(courses[1]);
						userBean.setCourse2(courses[2]);
						userBean.setCourse3(courses[3]);
						userBean.setCourse4(courses[4]);
						userBean.setLoginFlag(0);
						userBean.setPageName("CourseRegistration");
						userBean.setCount(i);
						setVisible(false);
						try {
							ObjectOutputStream oos=new ObjectOutputStream(clientSocket.getOutputStream());
							oos.writeObject(userBean);
							oos.flush();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						JOptionPane.showMessageDialog(null, "Courses registered successfully");
						frameLogin.setVisible(true);
					}	
				}
		});
		btnRegister.setBounds(455, 327, 117, 29);
		contentPane.add(btnRegister);
		
	}
}
