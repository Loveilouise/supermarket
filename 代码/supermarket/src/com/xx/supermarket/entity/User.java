package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年03月16日 14时55分22秒
 */
public class User implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields LoginName : loginName
	 * 
	 * */
	private String loginName;
	/** 
	 *  @Fields RealName : realName
	 * 
	 * */
	private String realName;
	/** 
	 *  @Fields Phone : phone
	 * 
	 * */
	private String phone;
	/** 
	 *  @Fields IsVip : isVip
	 * 
	 * */
	private Integer isVip;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;
	/** 
	 *  @Fields PassWord : passWord
	 * 
	 * */
	private String passWord;
	/** 
	 *  @Fields Sex : sex
	 * 
	 * */
	private Integer sex;
	/** 
	 *  @Fields Jf : jf
	 * 
	 * */
	private Integer jf;
	/** 
	 *  @Fields Address : address
	 * 
	 * */
	private String address;
	
	private int level;
	

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLoginName() {
		return this.loginName;
	}
	
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getRealName() {
		return this.realName;
	}
	
	public void setRealName(String realName) {
		this.realName = realName;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getIsVip() {
		return this.isVip;
	}
	
	public void setIsVip(Integer isVip) {
		this.isVip = isVip;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public Integer getSex() {
		return this.sex;
	}
	
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	
	public Integer getJf() {
		return this.jf;
	}
	
	public void setJf(Integer jf) {
		this.jf = jf;
	}
	
	public String getAddress() {
		return this.address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	
    public User() {
		
	}

	public User(Integer id ,String loginName ,String realName ,String phone ,Integer isVip ,Integer isDelete ,String passWord ,Integer sex ,Integer jf ,String address ){
	super();
	this.id=id;
	this.loginName=loginName;
	this.realName=realName;
	this.phone=phone;
	this.isVip=isVip;
	this.isDelete=isDelete;
	this.passWord=passWord;
	this.sex=sex;
	this.jf=jf;
	this.address=address;
	}
	
	@Override
	public String toString() {
		return "User [id="+ id + ",loginName="+ loginName + ",realName="+ realName + ",phone="+ phone + ",isVip="+ isVip + ",isDelete="+ isDelete + ",passWord="+ passWord + ",sex="+ sex + ",jf="+ jf + ",address="+ address +  "]";
	}


}

