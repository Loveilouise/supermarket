package com.xx.supermarket.controller;
import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
@RequestMapping("/order")
public class OrderController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ProductService  productService;
	
	@Autowired
	private UserService userService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	@Autowired
	private OrderitemService orderitemService;


	// 生成订单的执行的方法:
	@RequestMapping(value = "/saveOrder.do")	
	public String saveOrder(HttpSession session,Model model,Order order,Product product) {
		// 调用Service完成数据库插入的操作:
		// Order order = new Order();
		// 设置订单的总金额:订单的总金额应该是购物车中总金额:
		// 购物车在session中,从session总获得购物车信息.
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			model.addAttribute("message","亲!您还没有购物!");
			return "order/msg";
		}
		 Calendar calendar = Calendar.getInstance();
		 //订单号
        order.setUid("1000000"+calendar.getTime().getTime());
		order.setTotal(cart.getTotal());
		// 设置订单的状态
		order.setStatus(0); // 0:未付款.
		// 设置订单时间
		order.setOrderTime(new Date());
		// 设置订单关联的客户:
		User existUser = (User) session.getAttribute("user");
		if (existUser == null) {
			model.addAttribute("message","亲!您还没有登录!");
			return "order/msg";
		}
		order.setUserId(existUser.getId());
		order.setIsDelete(0);
		orderService.insert(order);
		List<Orderitem> orderItemList1=new ArrayList<>();
		// 设置订单项集合:
		for (CartItem cartItem : cart.getCartItems()) {
			// 订单项的信息从购物项获得的.
			Orderitem orderItem = new Orderitem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProductId(cartItem.getProduct().getId());
			orderItem.setOrderId(order.getId());
			orderitemService.insert(orderItem);
//			order.getOrderItems().add(orderItem);
			orderItemList1.add(orderItem);
		}
		List<Product> orderItemList=new ArrayList<>();
		for (Orderitem orderitem : orderItemList1) {
			Map<String, Object> map = getMap();
			map.put("id",orderitem.getProductId());
			Product p =productService.loadBySqlId("loadBySqlId", map); 	
			p.setCount(orderitem.getCount());
			p.setSubtotal(orderitem.getSubtotal());
			orderItemList.add(p);
		}
		// 清空购物车:
		cart.clearCart();
		// 页面需要回显订单信息:
		// 使用模型驱动了 所有可以不使用值栈保存了
		// ActionContext.getContext().getValueStack().set("order", order);
		model.addAttribute("user", existUser);
		model.addAttribute("order", order);
		model.addAttribute("orderItemList", orderItemList);
		return "order/order1";
	}
	
    	// 点击付款生成订单的执行的方法:
	   @RequestMapping(value = "/saveOrder1.do")	
		public String saveOrder1(HttpSession session,Model model,Order order) {
			 Map<String, Object> map = getMap();
			 map.put("id",order.getId());
			 Order o =orderService.loadBySqlId("loadBySqlId", map); 
		    model.addAttribute("order", o);
		    map.put("orderId",order.getId());
		    List<Orderitem> orderItemList1=orderitemService.list(map);
		    List<Product> orderItemList=new ArrayList<>();
			for (Orderitem orderitem : orderItemList1) {
				Map<String, Object> map1 = getMap();
				map1.put("id",orderitem.getProductId());
				Product p =productService.loadBySqlId("loadBySqlId", map1); 	
				p.setCount(orderitem.getCount());
				p.setSubtotal(orderitem.getSubtotal());
				orderItemList.add(p);
			}
	     	 model.addAttribute("orderItemList", orderItemList);
			return "order/order1";
		}
	
	
	// 查询我的订单:
	@RequestMapping(value = "/findByUserId.do")	
	public String findByUserId(HttpSession session,Model model) {
		 Map<String, Object> map = getMap();
		// 获得用户的id.
		User existUser = (User) session.getAttribute("user");
		// 获得用户的id
		Integer userId = existUser.getId();
		 map.put("userId",userId);
		 Order order =new Order();
		 order.setUserId(userId);
		// 根据用户的id查询订单:
		Pager<Order> pagers = orderService.findByEntity(order);
		Orderitem orderitem=new Orderitem();
		orderitem.setUserId(userId);
		Pager<Orderitem> orderItemList=orderitemService.findByEntity(orderitem);
		for (Orderitem orderitem1 : orderItemList.getDatas()) {
			Map<String, Object> map1 = getMap();
			map1.put("id",orderitem1.getProductId());
			Product p =productService.loadBySqlId("loadBySqlId", map1); 
			orderitem1.setProduct(p);
		}
		// 将PageBean数据带到页面上.
		//ActionContext.getContext().getValueStack().set("myOrder", myOrder);
		 model.addAttribute("pagers", pagers);
		 model.addAttribute("orderItemList", orderItemList);
		return "order/orderList";
	}

	// 修改订单的状态:付款
	@RequestMapping(value = "/payOrder.do")
	public String payOrder(HttpSession session,Model model,Order order,Integer type){
		Map<String, Object> map = getMap();
		 map.put("id",order.getId());
	     Order currOrder =orderService.loadBySqlId("loadBySqlId", map);
		currOrder.setStatus(1);
		User u = (User) session.getAttribute("user");
		//积分购物券付款 10个积分是1元这样的计算规则
		if(type==1){
			//有积分的情况
			 if(u.getJf()!=0){
				 //积分折算金额小于商品金额
				 if((currOrder.getTotal())-u.getJf()/10>0){
					u.setJf((int)((currOrder.getTotal())-u.getJf()/10)/10);
					userService.update(u);
				 }else{
					 //积分折算金额大于商品金额
					 u.setJf((int)(u.getJf()-currOrder.getTotal()*10));
					 userService.update(u);
				 }
			 }else{
				//无积分的情况
				 u.setJf((int)(currOrder.getTotal()/10+u.getJf()));
				 userService.update(u);
			 }
		}else{//现金付款
			u.setJf((int)(currOrder.getTotal()/10+u.getJf()));
			userService.update(u);
		}
		if(currOrder.getTotal()>=100){
			u.setLevel(1);//黄金会员
		}else if(currOrder.getTotal()>=200){
			u.setLevel(2);//白金会员
		}else if(currOrder.getTotal()>=800){
			u.setLevel(3);//钻石会员
		}
		userService.update(u);
		orderService.update(currOrder);
		return "redirect:/order/findByUserId.do";
	}
	
	// 修改订单的状态:取消订单
	@RequestMapping(value = "/cancelOrder.do")
	public String cancelOrder(HttpSession session,Model model,Order order){
		Map<String, Object> map = getMap();
		 map.put("id",order.getId());
	     Order currOrder =orderService.loadBySqlId("loadBySqlId", map);
		currOrder.setStatus(3);
		orderService.update(currOrder);
		return "redirect:/order/findByUserId.do";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Order> pagers = orderService.findByEntity(order);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", order);
		return "order/order";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(order.getIsDelete())){
        	params.put("isDelete", order.getIsDelete());
		}
        if(!isEmpty(order.getOrderTime())){
        	params.put("orderTime", order.getOrderTime());
		}
        if(!isEmpty(order.getStatus())){
        	params.put("status", order.getStatus());
		}
        if(!isEmpty(order.getTotal())){
        	params.put("total", order.getTotal());
		}
        if(!isEmpty(order.getUid())){
        	params.put("uid", order.getUid());
		}
        if(!isEmpty(order.getUserId())){
        	params.put("userId", order.getUserId());
		}
		//分页查询
		Pager<Order> pagers = orderService.findByMap(params);
		for (Order order1 : pagers.getDatas()) {
			Map<String, Object> map = getMap();
			map.put("id",order1.getUserId());
			User u=userService.loadBySqlId("loadBySqlId", map); 	
			order1.setUser(u);
		}
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("order", order);
		return "order/order";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "order/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderService.insert(order);
		return "redirect:/order/findByMap.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Order obj = orderService.load(id);
		Map<String, Object> map = getMap();
		map.put("id",obj.getUserId());
		User u=userService.loadBySqlId("loadBySqlId", map); 	
		obj.setUser(u);
		model.addAttribute("order",obj);
		return "order/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderService.update(order);
		return "redirect:/order/findByMap.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Order order,Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> map = getMap();
		map.put("orderId",order.getId());
	   List<Orderitem> orderitemList=orderitemService.list(map);
	   for (Orderitem orderitem : orderitemList) {
		   orderitemService.deleteById(orderitem.getId());//删除中间表
    	}
		order.setIsDelete(1);
		//逻辑删除
		orderService.update(order);
		return "redirect:/order/findByMap.do";
	}
	
	/**
	 *  修改订单的状态:取消订单
	 * @return
	 */
	@RequestMapping(value = "/cancelOrder1.do")
	public String cancelOrder1(Order order){
		order.setStatus(3);
		orderService.update(order);
		return "redirect:/order/findByMap.do";
	}

	/**
	 *  修改订单的状态:已经审核
	 * @return
	 */
	@RequestMapping(value = "/confirm.do")
	public String confirm(Order order){
		order.setStatus(2);
		orderService.update(order);
		return "redirect:/order/findByMap.do";
	}
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	/**
	 * 分页查询 返回list json(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String findByObjByEntity(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Order> pagers = orderService.findByEntity(order);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", order);
		return jsonObject.toString();
	}
	
	  
	/**
	 * 分页查询 返回list json(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String findByMapMap(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(order.getIsDelete())){
        	params.put("isDelete", order.getIsDelete());
		}
        if(!isEmpty(order.getOrderTime())){
        	params.put("orderTime", order.getOrderTime());
		}
        if(!isEmpty(order.getStatus())){
        	params.put("status", order.getStatus());
		}
        if(!isEmpty(order.getTotal())){
        	params.put("total", order.getTotal());
		}
        if(!isEmpty(order.getUid())){
        	params.put("uid", order.getUid());
		}
        if(!isEmpty(order.getUserId())){
        	params.put("userId", order.getUserId());
		}
		//分页查询
		Pager<Order> pagers = orderService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", order);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderService.insert(order);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "添加成功");
		return jsonObject.toString();
	}
	

	/**
	 * ajax 修改
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exUpdateJson(Order order, Model model, HttpServletRequest request, HttpServletResponse response) {
		orderService.update(order);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "修改成功");
		return jsonObject.toString();
	}

	/**
	 * ajax 删除
	 * @return
	 */
	@RequestMapping(value = "/delete.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exDeleteJson(Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
		orderService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//orderService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Order load = orderService.load(id);
		//load.setIsDelete(1);
		//orderService.update(load);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("message", "删除成功");
		return jsonObject.toString();
	}
	/**
	 * 单文件上传
	 * @param file
	 * @param request
	 * @param model
	 * @return
	 */
    @RequestMapping(value = "/saveFile")  
    public String saveFile(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request, Model model) {  
  
        System.out.println("开始");  
        String path = request.getSession().getServletContext().getRealPath("/upload");  
        String fileName = file.getOriginalFilename();  
        System.out.println(path);  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //保存  
        try {  
            file.transferTo(targetFile);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return "";  
    }  
	
	
	/**
	 * springMvc多文件上传
	 * @param files
	 * @param id
	 * @return
	 */
    @RequestMapping(value = "/saveFiles")
    public String saveFiles(@RequestParam("file") CommonsMultipartFile[] files,Integer id,HttpServletRequest request){
		for(int i = 0;i<files.length;i++){
	      	System.out.println("fileName---------->" + files[i].getOriginalFilename());
		  if(!files[i].isEmpty()){
            int pre = (int) System.currentTimeMillis();
	     	try {
			//拿到输出流，同时重命名上传的文件
			 String filePath = request.getRealPath("/upload");
			 File f=new File(filePath);
			 if(!f.exists()){
				f.mkdirs();
			 }
		     String fileNmae=new Date().getTime() + files[i].getOriginalFilename();
		     File file=new File(filePath+"/"+pre + files[i].getOriginalFilename());
			  if(!file.exists()){
				  file.createNewFile();
			 }
			  files[i].transferTo(file);
		     } catch (Exception e) {
				e.printStackTrace();
				System.out.println("上传出错");
			 }
		  }
		}
	  return "";
	}
 // --------------------------------------- 华丽分割线 ------------------------------
	
	
}
