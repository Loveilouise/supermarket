package com.xx.supermarket.dao.impl;
/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分12秒
 */
import org.springframework.stereotype.Repository;
import com.xx.supermarket.entity.Product;
import org.springframework.stereotype.Repository;
import com.xx.supermarket.base.BaseDaoImpl;
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

@Repository
public class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDao{

	@Override
	public Pager<Product> findByMapSqlId(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return findByMap("findByMapSqlId",map);
	}
	
	
}
