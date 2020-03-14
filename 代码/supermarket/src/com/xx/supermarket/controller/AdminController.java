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
import com.xx.supermarket.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2017年02月28日 22时35分11秒
 */


@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private AdminService adminService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	
	 /**
     * 登陆以后进入首页
     * @return
     * @throws IOException 
     */
	@RequestMapping("/index.json")
	@ResponseBody
    public String index(HttpServletRequest request, HttpServletResponse response, HttpSession session ,Admin admin) throws IOException{
	        //查询该用户是否存在
			 Map<String, Object> map = getMap();
			 map.put("adminName",admin.getAdminName());
	    	 Admin a1 =adminService.loadBySqlId("loadBySqlId", map);
    		JSONObject jsonObject = JsonUtil2.getJsonObject();
    	if(a1==null){
    		   jsonObject.put("result", 3); //该用户不存在
    	}else{
    		 //查询用户和密码是否 正确
	        map.put("passWord",admin.getPassWord());
	        Admin a =adminService.loadBySqlId("loadBySqlId", map);
		   if(a!=null){
				   jsonObject.put("result", 1); //登录成功
				   if (session!=null && !session.isNew()) {
					    session.invalidate();
					}
					session = request.getSession(true);		
		           session.setAttribute("adminName", a.getAdminName());
		           session.setAttribute("adminId", a.getId());
	        }else{
	        	jsonObject.put("result",2);//密码错误
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
	public String findByObj(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Admin> pagers = new Pager<Admin>();
		 Map<String, Object> map = getMap();
		 if(!isEmpty(admin.getAdminName())){
			 map.put("adminName",admin.getAdminName()); 
		 }
		pagers = adminService.findByMap(map);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", admin);
		return "admin/admin";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(admin.getAdminName())){
        	params.put("adminName", admin.getAdminName());
		}
        if(!isEmpty(admin.getPassWord())){
        	params.put("passWord", admin.getPassWord());
		}
        if(!isEmpty(admin.getPhone())){
        	params.put("phone", admin.getPhone());
		}
        if(!isEmpty(admin.getIsDelete())){
        	params.put("isDelete", admin.getIsDelete());
		}
		//分页查询
		Pager<Admin> pagers = adminService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", admin);
		return "admin/admin";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add() {
		return "admin/add";
	}

	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		admin.setIsDelete(0);
		adminService.insert(admin);
		return "redirect:/admin/findByObj.do";
	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Admin obj = adminService.load(id);
		model.addAttribute("obj",obj);
		return "admin/update";
	}
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		adminService.update(admin);
		return "redirect:/admin/findByObj.do";
	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Admin admin,Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
//		adminService.deleteById(id);
		
		admin.setIsDelete(1);
		//逻辑删除
		adminService.update(admin);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//adminService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Admin load = adminService.load(id);
		//load.setIsDelete(1);
		//adminService.update(load);
		return "redirect:/admin/findByObj.do";
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
	public String findByObjByEntity(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Admin> pagers = adminService.findByEntity(admin);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", admin);
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
	public String findByMapMap(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(admin.getAdminName())){
        	params.put("adminName", admin.getAdminName());
		}
        if(!isEmpty(admin.getPassWord())){
        	params.put("passWord", admin.getPassWord());
		}
        if(!isEmpty(admin.getPhone())){
        	params.put("phone", admin.getPhone());
		}
        if(!isEmpty(admin.getIsDelete())){
        	params.put("isDelete", admin.getIsDelete());
		}
		//分页查询
		Pager<Admin> pagers = adminService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", admin);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		adminService.insert(admin);
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
	public String exUpdateJson(Admin admin, Model model, HttpServletRequest request, HttpServletResponse response) {
		adminService.update(admin);
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
		adminService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//adminService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Admin load = adminService.load(id);
		//load.setIsDelete(1);
		//adminService.update(load);
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
