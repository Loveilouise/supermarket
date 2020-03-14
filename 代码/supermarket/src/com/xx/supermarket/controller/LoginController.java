package com.xx.supermarket.controller;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import com.xx.supermarket.base.BaseController;
import com.xx.supermarket.entity.Category;
import com.xx.supermarket.entity.CategorySec;
import com.xx.supermarket.entity.Product;
import com.xx.supermarket.service.CategoryService;
import com.xx.supermarket.service.ProductService;
import com.xx.supermarket.utils.Pager;

import net.sf.json.JSONObject;
import java.util.*;

@Controller
@RequestMapping("/login")
public class LoginController extends BaseController {
	
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private ProductService productService;
	
	
	    //注册页面(只有前台有注册)
	    @RequestMapping(value = "/register.do")
		public String register(Model model) {
			return "login/uRegister";
		}
		
		//登陆页面
		@RequestMapping(value = "/login.do")
		public String login(Integer role) {
			if(role==1){
				return "login/uLogin";
			}else{
				return "login/mLogin";
			}
		}
		
		 /**
		 * 跳转到忘记密码页面
		 * @return
		 */
	   @RequestMapping(value = "/forgetPassWord.do")
		public String forgetPassWord(){
			return "login/forgetPassWord";
			
			}
		
		
	   //退出
		@RequestMapping(value = "/tuichu.do")
		public String tuichu(Integer role, HttpServletRequest request) {
			if(role==1){
				HttpSession  session = request.getSession();
				session.invalidate();
				return "login/uLogin";
			}else{
				HttpSession  session = request.getSession();
				session.invalidate();
				return "login/mLogin";
			}
		}
		
		
		//登录成功进入首页
		@RequestMapping(value = "/index.do")
		public String index(Integer role, Model model,HttpSession session,Product product){
			if(role==1){
				List<Category> categoryList = categoryService.listAll();
				model.addAttribute("categoryList", categoryList);
				//通过map查询
				Map<String,Object> params = new HashMap<String,Object>();
		        if(!isEmpty(product.getName())){
		        	params.put("name", product.getName());
				}
				//分页查询
				Pager<Product> pagers = productService.findByMap(params);
				model.addAttribute("pagers", pagers);
				return "login/uIndex";
			}else{
				return "login/mIndex";
			}
		}
	
	
}
