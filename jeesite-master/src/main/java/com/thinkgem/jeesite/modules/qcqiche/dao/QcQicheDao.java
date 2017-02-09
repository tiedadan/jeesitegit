/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qcqiche.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qcqiche.entity.QcQiche;

/**
 * 汽车DAO接口
 * @author 王晓强
 * @version 2016-07-15
 */
@MyBatisDao
public interface QcQicheDao extends CrudDao<QcQiche> {
	
}