/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qctype.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.qctype.entity.QcType;

/**
 * 汽车品牌DAO接口
 * @author 王晓强
 * @version 2016-07-15
 */
@MyBatisDao
public interface QcTypeDao extends CrudDao<QcType> {
	
}