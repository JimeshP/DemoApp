/**
 * 
 */
package com.vmware.vra.cms.service.component.util;


/**
 * @author RamS
 *
 */
public class UserUtil {
	


/*	public static List<UserBVO> createUserBVOList(List<User> userList){
		
		List<UserBVO> userBVOList=new ArrayList<UserBVO>();
		for(User user:userList){
			userBVOList.add(createUserBVO(user));
		}
		return userBVOList;
	}
	*/
/*	public static UserBVO createUserBVO(User user){

		UserBVO userBVO=null;
		if(user!=null){
			userBVO=new UserBVO();
			userBVO.setUserId(user.getUserId());
			userBVO.setUserName(user.getUserName());
			userBVO.setPassword(user.getPassword());
			userBVO.setCustomerId(user.getCustomer().getCustomerId());
		}
		return userBVO;
	}
	*/
/*	public static User createUser(UserBVO userBVO, Customer customer) throws Exception {
		User user=new User();
		user.setUserId(userBVO.getUserId());
		if(userBVO.getUserName()!=null){
		user.setUserName(userBVO.getUserName());
		}else{
			throw new Exception();
		}
		user.setPassword(userBVO.getPassword());
		user.setCustomer(customer);
		return user;
	}*/
	
}
