package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分11秒
 */
public class Category implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Cname : cname
	 * 
	 * */
	private String cname;
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
	
	public String getCname() {
		return this.cname;
	}
	
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public Category() {
		
	}

	public Category(Integer id ,String cname ,Integer isDelete ){
	super();
	this.id=id;
	this.cname=cname;
	this.isDelete=isDelete;
	}
	
	@Override
	public String toString() {
		return "Category [id="+ id + ",cname="+ cname + ",isDelete="+ isDelete +  "]";
	}


}

