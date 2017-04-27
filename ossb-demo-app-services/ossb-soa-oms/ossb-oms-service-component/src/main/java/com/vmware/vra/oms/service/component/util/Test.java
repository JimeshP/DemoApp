package com.vmware.vra.oms.service.component.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import com.vmware.vra.oms.service.component.vo.OrderHeaderBVO;
import com.vmware.vra.oms.service.component.vo.OrderLineBVO;

public class Test {

	
	
	public static void main(String[] args) {
		/*ListUserBVO listuseBVO = new ListUserBVO();
		listuseBVO.setErrorCode("404");
		listuseBVO.setErrorDesc("Page not found");
		listuseBVO.setStatus("Failure");
		List<UserBVO> userList = new ArrayList<UserBVO>();
		listuseBVO.setUserList(userList);
		
		UserBVO usrBVO= new UserBVO();
		usrBVO.setCustomerId(123);
		usrBVO.setPassword("test");
		usrBVO.setUserId(123);
		usrBVO.setUserName("ram");
		userList.add(usrBVO);
		
		usrBVO= new UserBVO();
		usrBVO.setCustomerId(1232);
		usrBVO.setPassword("test");
		usrBVO.setUserId(123);
		usrBVO.setUserName("ramKumar Pandey");
		userList.add(usrBVO);
		
		  try {

				JAXBContext jaxbContext = JAXBContext.newInstance(ListUserBVO.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(listuseBVO, System.out);

			      } catch (JAXBException e) {
				e.printStackTrace();
			      }

		*/
		
		/*OrderHeaderBVO ohb = new OrderHeaderBVO();
		ohb.setCustomerId(6);
		ohb.setStatusId(1);
		List<OrderLineBVO> orderLineBVOList = new ArrayList<OrderLineBVO>();
		ohb.setOrderLineBVOList(orderLineBVOList);
		
		  try {

				JAXBContext jaxbContext = JAXBContext.newInstance(OrderHeaderBVO.class);
				Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

				// output pretty printed
				jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

				jaxbMarshaller.marshal(ohb, System.out);

			      } catch (JAXBException e) {
				e.printStackTrace();
			      }*/

		
		
		try {
			
			 Connection connect = null;
			   Statement statement = null;
			   PreparedStatement preparedStatement = null;
			   ResultSet resultSet = null;
			
			Class.forName("com.mysql.jdbc.Driver");
		      // Setup the connection with the DB
		      connect = DriverManager.getConnection("jdbc:mysql://localhost:3307/vrademoapp", "root", "root@123");
		      										
		      // Statements allow to issue SQL queries to the database
		      statement = connect.createStatement();
		      // Result set get the result of the SQL query
		      resultSet = statement
		          .executeQuery("select count(*) from app_user");
		      
		      System.out.println("result is "  + resultSet);
		      
		     // writeResultSet(resultSet);		
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
