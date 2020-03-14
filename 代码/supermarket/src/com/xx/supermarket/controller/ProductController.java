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
import com.xx.supermarket.service.*;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2017年02月28日 22时35分12秒
 */


@Controller
@RequestMapping("/product")
public class ProductController extends BaseController {
	
	/**
	 * 依赖注入 start dao/service/===
	 */
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private CategorySecService categorySecService;
	
	// --------------------------------------- 华丽分割线 ------------------------------
	/**
	 * 根据商品的ID进行查询商品:执行方法
	 */
	@RequestMapping("/findByPid.do")
	public String findByPid(Model model, HttpSession session,Product product) {
		List<Category> categoryList = categoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		List<CategorySec> categorySecList = categorySecService.listAll();
		model.addAttribute("categorySecList", categorySecList);
		 Map<String, Object> map = getMap();
		 map.put("id",product.getId());
		Product p =  productService.loadBySqlId("loadBySqlId", map);
		model.addAttribute("product", p);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("categorySecList", categorySecList);
		return "product/product1";
	}

	/**
	 * 根据分类的id查询商品:
	 */
	@RequestMapping("/findByCid.do")
	public String findByCid(Model model, HttpSession session,Product product) {
		Pager<Product> pagers = productService.findByEntity(product);// 根据一级分类查询商品,带分页查询
		model.addAttribute("pagers", pagers);
		List<Category> categoryList = categoryService.listAll();
		model.addAttribute("categoryList", categoryList);
		List<CategorySec> categorySecList = categorySecService.listAll();
		model.addAttribute("categorySecList", categorySecList);
		session.setAttribute("categoryList", categoryList);
		session.setAttribute("categorySecList", categorySecList);
		return "product/productList";
	}

	/**
	 * 根据二级分类id查询商品
	 */
	@RequestMapping("/findByScid.do")
	public String findByScid(Model model, HttpSession session,Product product,Integer scid) {
		product.setCategorySecId(scid);
		// 根据二级分类查询商品
		 Map<String, Object> map = getMap();
		 map.put("categorySecId",product.getCategorySecId());
		Pager<Product> pagers = productService.findByMap(map);
		model.addAttribute("pagers", pagers);
		model.addAttribute("scid", product.getCategorySecId());
		return "product/productList";
	}

	/**
	 * 分页查询 返回list对象(通过对象)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByObj.do")
	public String findByObj(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Product> pagers = productService.findByEntity(product);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", product);
		return "product/product";
	}
	
	
	/**
	 * 分页查询 返回list对象(通过Map)
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/findByMap.do")
	public String findByMap(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(product.getImage())){
        	params.put("image", product.getImage());
		}
        if(!isEmpty(product.getIsDelete())){
        	params.put("isDelete", product.getIsDelete());
		}
        if(!isEmpty(product.getName())){
        	params.put("name", product.getName());
		}
        if(!isEmpty(product.getPrice())){
        	params.put("price", product.getPrice());
		}
        if(!isEmpty(product.getRemark())){
        	params.put("remark", product.getRemark());
		}
        if(!isEmpty(product.getCategorySecId())){
        	params.put("categorySecId", product.getCategorySecId());
		}
		//分页查询
		Pager<Product> pagers = productService.findByMap(params);
		model.addAttribute("pagers", pagers);
		//存储查询条件
		model.addAttribute("obj", product);
		return "product/product";
	}
	
	
	/**
	 * 跳至添加页面
	 * @return
	 */
	@RequestMapping(value = "/add.do")
	public String add(Model model) {
		List<CategorySec> categorySecList = categorySecService.listAll();
		model.addAttribute("categorySecList", categorySecList);
		return "product/add";
	}

	
	
	/**
	 * 添加执行
	 * @return
	 */
	@RequestMapping(value = "/exAdd.do")
	public String exAdd(@RequestParam(value = "file", required = false) MultipartFile file, Product product,HttpServletRequest request, Model model) {
		    System.out.println("开始");  
	        String path = request.getSession().getServletContext().getRealPath("/")+"upload/";  
	        String fileName = file.getOriginalFilename();  
	        System.out.println(path);  
	        File targetFile = new File("D:/my/upload", fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }  
	        product.setImage("\\upload\\"+fileName);
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        product.setIsDelete(0);
		productService.insert(product);
		return "redirect:/product/findByMap.do";
	}
	
	
	
	
	
	/**
	 * 添加执行
	 * @return
	 */
//	@RequestMapping(value = "/exAdd.do")
//	public String exAdd(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
//		productService.insert(product);
//		return "redirect:/product/findByObj.do";
//	}
	
	
	/**
	 * 跳至修改页面
	 * @return
	 */
	@RequestMapping(value = "/update.do")
	public String update(Integer id,Model model) {
		Product obj = productService.load(id);
		List<CategorySec> categorySecList = categorySecService.listAll();
		model.addAttribute("categorySecList", categorySecList);
		model.addAttribute("obj",obj);
		return "product/update";
	}
	
	
	/**
	 * 添加修改
	 * @return
	 */
	@RequestMapping(value = "/exUpdate.do")
	public String exUpdate(@RequestParam(value = "file", required = false) MultipartFile file, Product product,HttpServletRequest request, Model model) {
		 System.out.println("开始");  
	        String path = request.getSession().getServletContext().getRealPath("/")+"upload/";  
	        String fileName = file.getOriginalFilename();  
	        System.out.println(path);  
	        File targetFile = new File("D:/my/upload", fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        }
	        if(fileName!=null){
	        	product.setImage("\\upload\\"+fileName);
	        }
	        //保存  
	        try {  
	            file.transferTo(targetFile);  
	        } catch (Exception e) {  
	            e.printStackTrace();  
	        }  
	        productService.update(product);
	       return "redirect:/product/findByMap.do";
	}
	
	
	
	/**
	 * 添加修改
	 * @return
	 */
//	@RequestMapping(value = "/exUpdate.do")
//	public String exUpdate(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
//		productService.update(product);
//		return "redirect:/product/findByObj.do";
//	}
	
	/**
	 * 删除通过主键
	 * @return
	 */
	@RequestMapping(value = "/delete.do")
	public String delete(Product product,Integer id, Model model, HttpServletRequest request, HttpServletResponse response) {
		//真正删除
//		productService.deleteById(id);
		
		product.setIsDelete(1);
		//逻辑删除
		productService.update(product);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//productService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Product load = productService.load(id);
		//load.setIsDelete(1);
		//productService.update(load);
		return "redirect:/product/findByMap.do";
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
	public String findByObjByEntity(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		//分页查询
		Pager<Product> pagers = productService.findByEntity(product);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", product);
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
	public String findByMapMap(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		//通过map查询
		Map<String,Object> params = new HashMap<String,Object>();
        if(!isEmpty(product.getImage())){
        	params.put("image", product.getImage());
		}
        if(!isEmpty(product.getIsDelete())){
        	params.put("isDelete", product.getIsDelete());
		}
        if(!isEmpty(product.getName())){
        	params.put("name", product.getName());
		}
        if(!isEmpty(product.getPrice())){
        	params.put("price", product.getPrice());
		}
        if(!isEmpty(product.getRemark())){
        	params.put("remark", product.getRemark());
		}
        if(!isEmpty(product.getCategorySecId())){
        	params.put("categorySecId", product.getCategorySecId());
		}
		//分页查询
		Pager<Product> pagers = productService.findByMap(params);
		JSONObject jsonObject = JsonUtil2.getJsonObject();
		jsonObject.put("pagers", pagers);
		jsonObject.put("obj", product);
		return jsonObject.toString();
	}
	
	
	/**
	 * ajax 添加
	 * @param 
	 * @return
	 */
	@RequestMapping(value = "/exAdd.json", produces=MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@ResponseBody
	public String exAddJson(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		productService.insert(product);
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
	public String exUpdateJson(Product product, Model model, HttpServletRequest request, HttpServletResponse response) {
		productService.update(product);
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
		productService.deleteById(id);
		//通过参数删除
        //Map<String,Object> params = new HashMap<String,Object>();
		//params.put("id", id);
		//productService.deleteBySqId("deleteBySql", params);
		//状态删除
		//Product load = productService.load(id);
		//load.setIsDelete(1);
		//productService.update(load);
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
