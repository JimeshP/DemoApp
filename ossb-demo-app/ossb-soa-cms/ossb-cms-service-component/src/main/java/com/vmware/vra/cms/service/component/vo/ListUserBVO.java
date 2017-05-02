package com.vmware.vra.cms.service.component.vo;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="listuserresponse")
public class ListUserBVO extends StatusBVO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8699507711674128463L;
	private List<UserBVO> userList;

	/**
	 * @return the userList
	 */
	@XmlElementWrapper(name="users")
	@XmlElement(name="user")
	public List<UserBVO> getUserList() {
		return userList;
	}

	/**
	 * @param userList the userList to set
	 */
	public void setUserList(List<UserBVO> userList) {
		this.userList = userList;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ListUserBVO [userList=" + userList + "]";
	}

	
}
