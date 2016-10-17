package com.uic.ids520;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Database extends Thread{
	private Connection conn=null;
	private PreparedStatement pst=null;
	private ResultSet rs=null;
	private static UserBean ub;
	Socket clientSocket;
public Database(Socket clientSocket) throws IOException, SQLException{
	this.clientSocket=clientSocket;
}
@Override
public void run() {
	try {
    	conn = DBConnect.DBConnect();
    	while(true){
    	ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
    	UserBean ob= (UserBean) ois.readObject();
    	ub=ob;
		System.out.println("Database"+ob.getUserName());
		if(ob.getPageName().equals("login")){
		String sql = "Select * from users where username =? and password=?";
		pst = conn.prepareStatement(sql);
		pst.setString(1, ob.getUserName());
		pst.setString(2, ob.getPassword());
		rs = pst.executeQuery();
		System.out.println("after");
		if(!rs.next()){
			ob.setFlag(false);
			ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(ob);
			oos.flush();
		}
		else if(!(ob.getUserName().equals("admin"))){
				conn = DBConnect.DBConnect();
				String sql2 = "update users SET LoginFlag=? where username=?;";
				pst = conn.prepareStatement(sql2);
				pst.setLong(1, 1);
				pst.setString(2, ob.getUserName());
				pst.executeUpdate();
				ob.setMajor(rs.getString("major"));
				ob.setFirstNaame(rs.getString("firstname"));
				ob.setLastName(rs.getString("lastname"));
				ob.setLoginFlag(rs.getInt("LoginFlag"));
				ob.setCourse0(rs.getString("course1"));
				ob.setCourse1(rs.getString("course2"));
				ob.setCourse2(rs.getString("course3"));
				ob.setCourse3(rs.getString("course4"));
				ob.setCourse4(rs.getString("course5"));
				ob.setFlag(true);
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				oos.writeObject(ob);
				oos.flush();
		}
		} else if(ob.getPageName().equals("UserRegistration")){
			conn = DBConnect.DBConnect();
			String sql = " insert into users (firstname, lastname, major, username, password)"
					+ " values (?, ?, ?, ?, ?)";
				pst = conn.prepareStatement(sql);
				pst.setString(1, ob.getFirstNaame());
				pst.setString(2, ob.getLastName());
				pst.setString(3, ob.getMajor());
				pst.setString(4, ob.getUserName());
				pst.setString(5, ob.getPassword());
				pst.executeUpdate();
				ob.setDuplicateFlag(true);
				ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
				oos.writeObject(ob);
				oos.flush();
		}
		else if(ob.getPageName().equals("CourseRegistration")){
			conn = DBConnect.DBConnect();
			System.out.println(ob.getUserName());
			String sql = " update users SET course1=?, course2=?, course3=?, course4=?, course5=?, CourseCount=?, LoginFlag=? where username=?;";
				pst = conn.prepareStatement(sql);
				pst.setString(1, ob.getCourse0());
				pst.setString(2, ob.getCourse1());
				pst.setString(3, ob.getCourse2());
				pst.setString(4, ob.getCourse3());
				pst.setString(5, ob.getCourse4());
				pst.setLong(6, ob.getCount());
				pst.setLong(7, ob.getLoginFlag());
				pst.setString(8, ob.getUserName());
				pst.executeUpdate();
		}
		else if(ob.getPageName().equals("LogOut")){
			conn = DBConnect.DBConnect();
			String sql2 = "update users SET LoginFlag=? where username=?;";
			pst = conn.prepareStatement(sql2);
			pst.setLong(1, 0);
			pst.setString(2, ob.getUserName());
			pst.executeUpdate();
		}
		else{
			JOptionPane.showMessageDialog(null, "DB Invalid user name or password");			
		}
    }}catch (MySQLIntegrityConstraintViolationException e) {
		// TODO Auto-generated catch block
    	ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(clientSocket.getOutputStream());
			oos.writeObject(ub);
			oos.flush();
			Thread db=new Database(clientSocket);
			db.start();
		} catch (IOException | SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		e.printStackTrace();
	}
catch(Exception e){
	e.printStackTrace();
}
}
}
