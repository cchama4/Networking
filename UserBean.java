package com.uic.ids520;

import java.io.Serializable;
import java.sql.ResultSet;

public class UserBean implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstNaame() {
		return firstNaame;
	}
	public void setFirstNaame(String firstNaame) {
		this.firstNaame = firstNaame;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getCourse0() {
		return course0;
	}
	public void setCourse0(String course0) {
		this.course0 = course0;
	}
	public String getCourse1() {
		return course1;
	}
	public void setCourse1(String course1) {
		this.course1 = course1;
	}
	public String getCourse2() {
		return course2;
	}
	public void setCourse2(String course2) {
		this.course2 = course2;
	}
	public String getCourse3() {
		return course3;
	}
	public void setCourse3(String course3) {
		this.course3 = course3;
	}
	public String getCourse4() {
		return course4;
	}
	public void setCourse4(String course4) {
		this.course4 = course4;
	}
private String userName;
private String password;
private String firstNaame;
private String lastName;
private String major;
private String course0;
private String course1;
private String course2;
private String course3;
private String course4;
private int loginFlag;
private int count;
private Boolean flag=true;
private boolean duplicateFlag=false;
public boolean getDuplicateFlag() {
	return duplicateFlag;
}
public void setDuplicateFlag(boolean b) {
	this.duplicateFlag = b;
}
public Boolean getFlag() {
	return flag;
}
public void setFlag(Boolean flag) {
	this.flag = flag;
}
private String courseSearch;
public String getCourseSearch() {
	return courseSearch;
}
public void setCourseSearch(String courseSearch) {
	this.courseSearch = courseSearch;
}
private String pageName;
public String getPageName() {
	return pageName;
}
public void setPageName(String pageName) {
	this.pageName = pageName;
}
public int getCount() {
	return count;
}
public void setCount(int count) {
	this.count = count;
}
public int getLoginFlag() {
	return loginFlag;
}
public void setLoginFlag(int loginFlag) {
	this.loginFlag = loginFlag;
}
}
