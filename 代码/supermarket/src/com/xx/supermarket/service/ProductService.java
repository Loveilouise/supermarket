package com.xx.supermarket.service;
import com.xx.supermarket.base.BaseService;
import com.xx.supermarket.entity.Product;
import java.util.*;

import com.xx.supermarket.entity.*;
import com.xx.supermarket.dao.*;
import com.xx.supermarket.service.*;
import com.xx.supermarket.utils.Pager;

/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date 2015年12月24日 下午1:46:33 - 2017年02月28日 22时35分12秒
 */
public interface ProductService extends BaseService<Product>{

	Pager<Product> findByMapSqlId(Map<String, Object> map);

}
