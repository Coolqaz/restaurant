package com.restraurant.action;

import java.util.UUID;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.restraurant.entity.User;
import com.restraurant.service.UserService;
import com.restraurant.util.DateUtil;
import com.restraurant.util.UuidUtil;

/**
 * �û�����
 * @author dell
 *
 */
public class UserAction extends ActionSupport{

	private UserService userService;
	private User user; //����û�ʱ��Ҫ�Ķ���
	
	public String showUserMessage(){
		user = null;
		ActionContext at  = ActionContext.getContext();
		User u = (User) at.getSession().get("user");
		if(u != null){
			user = userService.showUser(u.getUserId());
		}
		
		return SUCCESS;
	}
	
	/**
	 * ���ӻ�Ա
	 * @return String
	 */
	public String addUser(){
		//��֤����
		//��֤����
		//�����ݷ�װ��user  Start
		String userLoginName = "";
		String userNickName = "";
		String userPassword = "";
		String userPhone = "";
		String userAddress = "";
		user.setUserId(UuidUtil.getUUID());//32λuuid
		user.setUserLoginName(userLoginName);
		user.setUserNickName(userNickName);
		user.setUserPassword(userPassword);
		user.setUserPhone(userPhone);
		user.setUserAddress(userAddress);
		user.setUserCreateTime(DateUtil.getStringDate());
		//�����ݷ�װ��user End
		userService.addUser(user);
		return SUCCESS;
	}

	/**
	 * ��Ա��½
	 * @return String
	 */
	public String userlogin(){
		ActionContext context = ActionContext.getContext();
		String loginResult = INPUT;//�ɹ�����SUCCESS��ʧ�ܷ���INPUT
		String userLoginName = "admin";
		String userPassword = "admin";
		user = userService.userLogin(userLoginName,userPassword);
		//����û�Ϊ�գ������û����������������û����ڣ�ע�뵽session
		if(user != null){
			if(context.getSession().get("user") != null) context.getSession().remove("user");//ɾ��֮ǰsession
			context.getSession().put("user", user);
			loginResult = SUCCESS;
		}
		return loginResult;
	}
/* 
 	===============================Getter Setter Constructor=====================================
*/
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}

	
}
