package com.xx.supermarket.controller;
import java.io.File;
import java.io.IOException;

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
import com.xx.supermarket.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2017年03月16日 14时55分22秒
 */


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private UserService userService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	

	/**
	 * 注册
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/register.json")
	@ResponseBody
	public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user)
			throws IOException {
		// 查询该用户是否存在
		Map<String, Object> map = getMap();
		map.put("loginName", user.getLoginName());
		User u1 = userService.loadBySqlId("loadBySqlId", map);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		if (u1 != null) {
			jsonObject.put("result", 2); // 该用户存在
		} else {
			user.setIsDelete(0);
			user.setIsVip(1);
			user.setJf(0);
			user.setLevel(0);
			userService.insert(user);
			jsonObject.put("result", 1); // 注册成功
		}
		return jsonObject.toString();
	}

	/**
	 * 登陆以后进入首页
	 * 
	 * @return
	 * @throws IOException
	 */
	@RequestMapping("/login.json")
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, HttpSession session, User user)
			throws IOException {
		// 查询该用户是否存在
		Map<String, Object> map = getMap();
		map.put("loginName", user.getLoginName());
		User u1 = userService.loadBySqlId("loadBySqlId", map);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		if (u1 == null) {
			jsonObject.put("result", 3); // 该用户不存在
		} else {
			// 查询用户和密码是否 正确
			map.put("passWord", user.getPassWord());
			User u = userService.loadBySqlId("loadBySqlId", map);
			if (u != null) {
				jsonObject.put("result", 1); // 登录成功
				if (session != null && !session.isNew()) {
					session.invalidate();
				}
				session = request.getSession(true);
				session.setAttribute("userId", u.getId());
				session.setAttribute("loginName", u.getLoginName());
				session.setAttribute("user", u);
			} else {
				jsonObject.put("result", 2);// 密码错误
			}
		}
		return jsonObject.toString();
	}
	
	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = new Pager<User>();
		 Map<String, Object> map = getMap();
		 if(!isEmpty(user.getLoginName())){
			 map.put("loginName",user.getLoginName()); 
		 }
		pagers = userService.findByMap(map);
		for (User user1 : pagers.getDatas()) {
			if(user1.getJf()>=200){
				user1.setLevel(2);//白金会员
				userService.update(user1);
			}else if(user1.getJf()>=500){
				user1.setLevel(3);//钻石会员
				userService.update(user1);
			}
		}
		pagers = userService.findByMap(map);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getLoginName())){
        	params.put("loginName", user.getLoginName());
		}
        if(!isEmpty(user.getRealName())){
        	params.put("realName", user.getRealName());
		}
        if(!isEmpty(user.getPhone())){
        	params.put("phone", user.getPhone());
		}
        if(!isEmpty(user.getIsVip())){
        	params.put("isVip", user.getIsVip());
		}
        if(!isEmpty(user.getIsDelete())){
        	params.put("isDelete", user.getIsDelete());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getSex())){
        	params.put("sex", user.getSex());
		}
        if(!isEmpty(user.getJf())){
        	params.put("jf", user.getJf());
		}
        if(!isEmpty(user.getAddress())){
        	params.put("address", user.getAddress());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", user);
		return "user/user";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "user/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
		return "redirect:/user/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		User obj = userService.load(id);
		model.addAttribute("obj",obj);
		return "user/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
		return "redirect:/user/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(User user,Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
//		userService.deleteById(id);
		
		user.setIsDelete(1);
		//逻辑删除
		userService.update(user);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//userService.deleteBySqId("deleteBySql", params);
		//状态删除
		//User load = userService.load(id);
		//load.setIsDelete(1);
		//userService.update(load);
		return "redirect:/user/findByObj.do";
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
	public String findByObjByEntity(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<User> pagers = userService.findByEntity(user);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
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
	public String findByMapMap(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(user.getLoginName())){
        	params.put("loginName", user.getLoginName());
		}
        if(!isEmpty(user.getRealName())){
        	params.put("realName", user.getRealName());
		}
        if(!isEmpty(user.getPhone())){
        	params.put("phone", user.getPhone());
		}
        if(!isEmpty(user.getIsVip())){
        	params.put("isVip", user.getIsVip());
		}
        if(!isEmpty(user.getIsDelete())){
        	params.put("isDelete", user.getIsDelete());
		}
        if(!isEmpty(user.getPassWord())){
        	params.put("passWord", user.getPassWord());
		}
        if(!isEmpty(user.getSex())){
        	params.put("sex", user.getSex());
		}
        if(!isEmpty(user.getJf())){
        	params.put("jf", user.getJf());
		}
        if(!isEmpty(user.getAddress())){
        	params.put("address", user.getAddress());
		}
		//分页查询
		Pager<User> pagers = userService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", user);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.insert(user);
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
	public String exUpdateJson(User user, Model model, HttpServletRequest request, HttpServletResponse response) {
		userService.update(user);
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
		userService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//userService.deleteBySqId("deleteBySql", params);
		//状态删除
		//User load = userService.load(id);
		//load.setIsDelete(1);
		//userService.update(load);
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
