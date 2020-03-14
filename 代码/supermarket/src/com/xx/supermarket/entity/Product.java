package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分12秒
 */
public class Product implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Image : image
	 * 
	 * */
	private String image;
	/** 
	 *  @Fields IsDelete : isDelete
	 * 
	 * */
	private Integer isDelete;
	/** 
	 *  @Fields Name : name
	 * 
	 * */
	private String name;
	/** 
	 *  @Fields Price : price
	 * 
	 * */
	private java.lang.Double price;
	/** 
	 *  @Fields Remark : remark
	 * 
	 * */
	private String remark;
	/** 
	 *  @Fields CategorySecId : categorySecId
	 * 
	 * */
	private Integer categorySecId;
	
	
	private Integer cid;
	
	private Integer count;
	/** 
	 *  @Fields Subtotal : subtotal
	 * 
	 * */
	private double subtotal;
	

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getImage() {
		return this.image;
	}
	
	public void setImage(String image) {
		this.image = image;
	}
	
	public Integer getIsDelete() {
		return this.isDelete;
	}
	
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public java.lang.Double getPrice() {
		return this.price;
	}
	
	public void setPrice(java.lang.Double price) {
		this.price = price;
	}
	
	public String getRemark() {
		return this.remark;
	}
	
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public Integer getCategorySecId() {
		return this.categorySecId;
	}
	
	public void setCategorySecId(Integer categorySecId) {
		this.categorySecId = categorySecId;
	}
	
	
    public Product() {
		
	}

	public Product(Integer id ,String image ,Integer isDelete ,String name ,java.lang.Double price ,String remark ,Integer categorySecId ){
	super();
	this.id=id;
	this.image=image;
	this.isDelete=isDelete;
	this.name=name;
	this.price=price;
	this.remark=remark;
	this.categorySecId=categorySecId;
	}
	
	@Override
	public String toString() {
		return "Product [id="+ id + ",image="+ image + ",isDelete="+ isDelete + ",name="+ name + ",price="+ price + ",remark="+ remark + ",categorySecId="+ categorySecId +  "]";
	}


}

