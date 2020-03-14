package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分11秒
 */
public class Admin implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields AdminName : adminName
	 * 
	 * */
	private String adminName;
	/** 
	 *  @Fields PassWord : passWord
	 * 
	 * */
	private String passWord;
	/** 
	 *  @Fields Phone : phone
	 * 
	 * */
	private String phone;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getAdminName() {
		return this.adminName;
	}
	
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	
	public String getPassWord() {
		return this.passWord;
	}
	
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public String getPhone() {
		return this.phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public Admin() {
		
	}

	public Admin(Integer id ,String adminName ,String passWord ,String phone ,Integer isDelete ){
	super();
	this.id=id;
	this.adminName=adminName;
	this.passWord=passWord;
	this.phone=phone;
	this.isDelete=isDelete;
	}
	
	@Override
	public String toString() {
		return "Admin [id="+ id + ",adminName="+ adminName + ",passWord="+ passWord + ",phone="+ phone + ",isDelete="+ isDelete +  "]";
	}


}

