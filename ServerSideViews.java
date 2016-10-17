package com.uic.ids520;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServerSideViews extends JFrame {

	private JPanel contentPane;
	private JTextField textFieldSearch;
	private Connection conn = null;
	private PreparedStatement pst = null;
	private ResultSet rs = null;
	private JButton btnLoadData;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel lblSearchByCourses;
	private JTextField textField;
	private JButton btnLogOut;
	private static String userName="";
	private JFrame frameLogin;
	public void setFrameLogin(JFrame frameLogin) {
		this.frameLogin = frameLogin;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServerSideViews frame = new ServerSideViews();
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
	public ServerSideViews() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(600, 600, 777, 790);
		contentPane = new JPanel();
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBackground(new Color(204, 153, 153));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblServerSideViews = new JLabel("Server Side Views");
		lblServerSideViews.setFont(new Font("STSong", Font.PLAIN, 16));
		lblServerSideViews.setBackground(new Color(0, 0, 0));
		lblServerSideViews.setBounds(359, 19, 202, 16);
		contentPane.add(lblServerSideViews);
		
		textFieldSearch = new JTextField();
		textFieldSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				conn = DBConnect.DBConnect();
				
				try{
					String sql = "Select firstname,lastname,major,username,course1,course2,course3,course4,course5 from users where username =?";		
					pst = conn.prepareStatement(sql);
					pst.setString(1, textFieldSearch.getText());
					rs = pst.executeQuery();	
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}catch(Exception e1){
						e1.printStackTrace();
				}	
			}
		});
		textFieldSearch.setBounds(346, 97, 130, 26);
		contentPane.add(textFieldSearch);
		textFieldSearch.setColumns(10);
		
		JLabel lblSearch = new JLabel("Search by User");
		lblSearch.setBounds(227, 102, 98, 16);
		contentPane.add(lblSearch);
		
		btnLoadData = new JButton("Load User Data");
		btnLoadData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				conn = DBConnect.DBConnect();
				try{
					String sql = "Select firstname,lastname,major,username,course1,course2,course3,course4,course5 from users";		
					pst = conn.prepareStatement(sql);
					rs = pst.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}catch(Exception e1){
						e1.printStackTrace();
				}	
			}
		});
		btnLoadData.setBounds(327, 182, 139, 29);
		contentPane.add(btnLoadData);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(50, 250, 684, 473);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		lblSearchByCourses = new JLabel("Search by Courses");
		lblSearchByCourses.setBounds(210, 141, 124, 16);
		contentPane.add(lblSearchByCourses);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				conn = DBConnect.DBConnect();
				
				try{
					String sql1 = "Select firstname,lastname,major,username,course1,course2,course3,course4,course5 from users WHERE ? IN(course1,course2,course3,course4,course5)";		
					pst = conn.prepareStatement(sql1);
					pst.setString(1, textField.getText());
					rs = pst.executeQuery();	
					table.setModel(DbUtils.resultSetToTableModel(rs));	
				}catch(Exception e1){
						e1.printStackTrace();
				}
			}
		});
		textField.setBounds(346, 136, 130, 26);
		contentPane.add(textField);
		textField.setColumns(10);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				JOptionPane.showMessageDialog(null, "You are now logged out");
				frameLogin.setVisible(true);
			}
		});
		btnLogOut.setBounds(631, 66, 117, 29);
		contentPane.add(btnLogOut);
	}
}
