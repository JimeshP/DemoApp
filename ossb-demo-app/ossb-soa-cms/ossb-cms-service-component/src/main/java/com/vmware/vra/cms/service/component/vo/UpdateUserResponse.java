/**
 * 
 */
package com.vmware.vra.cms.service.component.vo;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author RamS
 *
 */
@XmlRootElement(name="updateuserresponse")
public class UpdateUserResponse extends StatusBVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7994854459795436687L;
	private UserBVO userBVO;
	
	@XmlElement(name="user")
	public UserBVO getUserBVO() {
		return userBVO;
	}
	/**
	 * @param userBVO the userBVO to set
	 */
	public void setUserBVO(UserBVO userBVO) {
		this.userBVO = userBVO;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "UpdateUserResponse [userBVO=" + userBVO + "]";
	}
}
