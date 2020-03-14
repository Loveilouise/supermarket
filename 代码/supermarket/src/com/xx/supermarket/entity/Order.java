package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分11秒
 */
public class Order implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;
	/** 
	 *  @Fields OrderTime : orderTime
	 * 
	 * */
	private java.util.Date orderTime;
	/** 
	 *  @Fields Status : status
	 * 
	 * */
	private Integer status;
	/** 
	 *  @Fields Total : total
	 * 
	 * */
	private java.lang.Double total;
	/** 
	 *  @Fields Uid : uid
	 * 
	 * */
	private String uid;
	/** 
	 *  @Fields UserId : userId
	 * 
	 * */
	private Integer userId;
	
	private User user;
	
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public java.util.Date getOrderTime() {
		return this.orderTime;
	}
	
	public void setOrderTime(java.util.Date orderTime) {
		this.orderTime = orderTime;
	}	
	
	public Integer getStatus() {
		return this.status;
	}
	
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public java.lang.Double getTotal() {
		return this.total;
	}
	
	public void setTotal(java.lang.Double total) {
		this.total = total;
	}
	
	public String getUid() {
		return this.uid;
	}
	
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	public Integer getUserId() {
		return this.userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
    public Order() {
		
	}

	public Order(Integer id ,Integer isDelete ,java.util.Date orderTime ,Integer status ,java.lang.Double total ,String uid ,Integer userId ){
	super();
	this.id=id;
	this.isDelete=isDelete;
	this.orderTime=orderTime;
	this.status=status;
	this.total=total;
	this.uid=uid;
	this.userId=userId;
	}
	
	@Override
	public String toString() {
		return "Order [id="+ id + ",isDelete="+ isDelete + ",orderTime="+ orderTime + ",status="+ status + ",total="+ total + ",uid="+ uid + ",userId="+ userId +  "]";
	}


}

