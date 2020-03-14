package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分11秒
 */
public class CategorySec implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Scname : scname
	 * 
	 * */
	private String scname;
	/** 
	 *  @Fields CategoryId : categoryId
	 * 
	 * */
	private Integer categoryId;
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
	
	public String getScname() {
		return this.scname;
	}
	
	public void setScname(String scname) {
		this.scname = scname;
	}
	
	public Integer getCategoryId() {
		return this.categoryId;
	}
	
	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	
    public CategorySec() {
		
	}

	public CategorySec(Integer id ,String scname ,Integer categoryId ,Integer isDelete ){
	super();
	this.id=id;
	this.scname=scname;
	this.categoryId=categoryId;
	this.isDelete=isDelete;
	}
	
	@Override
	public String toString() {
		return "CategorySec [id="+ id + ",scname="+ scname + ",categoryId="+ categoryId + ",isDelete="+ isDelete +  "]";
	}


}

