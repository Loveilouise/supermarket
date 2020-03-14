package com.xx.supermarket.dao;
/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分12秒
 */
import com.xx.supermarket.base.BaseDao;
import com.xx.supermarket.utils.Pager;
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


public interface ProductDao extends BaseDao<Product>{

	Pager<Product> findByMapSqlId(Map<String, Object> map);
}
