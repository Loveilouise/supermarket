/**
 * @ClassName:  
 * @Description: 
 * @author administrator
 * @date - 2017年02月28日 22时35分12秒
 */
package com.xx.supermarket.service.impl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.xx.supermarket.base.*;
import com.xx.supermarket.service.UserService;
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

@Service
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService{
	 
	@Autowired
	private UserDao userDao;
	@Override
	public BaseDao<User> getBaseDao() {
		return userDao;
	}

}
