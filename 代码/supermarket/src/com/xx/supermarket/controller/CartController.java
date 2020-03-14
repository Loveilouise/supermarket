package com.xx.supermarket.controller;
import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.axis2.databinding.types.soapencoding.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.xx.supermarket.base.BaseController;
import com.xx.supermarket.utils.JsonUtil2;
import com.xx.supermarket.utils.Pager;
import net.sf.json.JSONObject;
import java.util.*;
import com.xx.supermarket.entity.*;
import com.xx.supermarket.dao.*;
import com.xx.supermarket.dto.Cart;
import com.xx.supermarket.dto.CartItem;
import com.xx.supermarket.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2017年02月28日 22时35分11秒
 */


@Controller
@RequestMapping("/cart")
public class CartController extends BaseController {
	
	// 接收pid
	private Integer pid;
	// 接收数量count
	private Integer count;
	
	@Autowired
	private ProductService productService;


	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	
	public Integer getPid() {
		return pid;
	}

	public Integer getCount() {
		return count;
	}

	// 将购物项添加到购物车:执行的方法
	@RequestMapping("/addCart.do")
	public String addCart(Product product,Model model,HttpSession session,Integer count,Integer pid) {
		product.setId(pid);
		// 封装一个CartItem对象.
		CartItem cartItem = new CartItem();
		// 设置数量:
		cartItem.setCount(count);
		// 根据pid进行查询商品:
		 Map<String, Object> map = getMap();
		 map.put("id",product.getId());
		 Product p =  productService.loadBySqlId("loadBySqlId", map);
		// 设置商品:
		cartItem.setProduct(p);
		// 将购物项添加到购物车.
		// 购物车应该存在session中.
		Cart cart = getCart(session);
		cart.addCart(cartItem);

		return "cart/cart";
	}

	// 清空购物车的执行的方法:
	@RequestMapping("/clearCart.do")
	public String clearCart(HttpSession session){
		// 获得购物车对象.
		Cart cart = getCart(session);
		// 调用购物车中清空方法.
		cart.clearCart();
		return "cart/cart";
	}
	
	// 从购物车中移除购物项的方法:
	@RequestMapping("/removeCart.do")
	public String removeCart(HttpSession session,Integer pid){
		// 获得购物车对象
		Cart cart = getCart(session);
		// 调用购物车中移除的方法:
		cart.removeCart(pid);
		// 返回页面:
		return "cart/cart";
	}
	
	// 我的购物车:执行的方法
	@RequestMapping("/myCart.do")
	public String myCart(HttpServletRequest request, HttpServletResponse response, HttpSession session,Model model){
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			model.addAttribute("message","亲!您还没有购物!");
			return "cart/cart";
		}else{
		   return "cart/cart";
		}
	}
	
	/**
	 * 获得购物车的方法:从session中获得购物车.
	 * @return
	 */
	@RequestMapping("/getCart.do")
	private Cart getCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
	
}
