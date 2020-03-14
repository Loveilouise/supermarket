package com.xx.supermarket.entity;

import java.io.Serializable;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分12秒
 */
public class Orderitem implements Serializable {
    private static final long serialVersionUID = 3148176768559230877L;
    

	/** 
	 *  @Fields Id : id
	 * 
	 * */
	private Integer id;
	/** 
	 *  @Fields Count : count
	 * 
	 * */
	private Integer count;
	/** 
	 *  @Fields Subtotal : subtotal
	 * 
	 * */
	private java.lang.Double subtotal;
	/** 
	 *  @Fields OrderId : orderId
	 * 
	 * */
	private Integer orderId;
	/** 
	 *  @Fields ProductId : productId
	 * 
	 * */
	private Integer productId;
	
	private Integer userId;
	
	private Order order;
	
	private Product product;
	
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getCount() {
		return this.count;
	}
	
	public void setCount(Integer count) {
		this.count = count;
	}
	
	public java.lang.Double getSubtotal() {
		return this.subtotal;
	}
	
	public void setSubtotal(java.lang.Double subtotal) {
		this.subtotal = subtotal;
	}
	
	public Integer getOrderId() {
		return this.orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public Integer getProductId() {
		return this.productId;
	}
	
	public void setProductId(Integer productId) {
		this.productId = productId;
	}
	
	
    public Orderitem() {
		
	}

	public Orderitem(Integer id ,Integer count ,java.lang.Double subtotal ,Integer orderId ,Integer productId ){
	super();
	this.id=id;
	this.count=count;
	this.subtotal=subtotal;
	this.orderId=orderId;
	this.productId=productId;
	}
	
	@Override
	public String toString() {
		return "Orderitem [id="+ id + ",count="+ count + ",subtotal="+ subtotal + ",orderId="+ orderId + ",productId="+ productId +  "]";
	}


}

