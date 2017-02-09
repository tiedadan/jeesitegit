/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xiaoqc.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.xiaoqc.entity.XiqoQc;
import com.thinkgem.jeesite.modules.xiaoqc.dao.XiqoQcDao;

/**
 * 汽车之家Service
 * @author 王小强
 * @version 2016-07-12
 */
@Service
@Transactional(readOnly = true)
public class XiqoQcService extends CrudService<XiqoQcDao, XiqoQc> {

	public XiqoQc get(String id) {
		return super.get(id);
	}
	
	public List<XiqoQc> findList(XiqoQc xiqoQc) {
		return super.findList(xiqoQc);
	}
	
	public Page<XiqoQc> findPage(Page<XiqoQc> page, XiqoQc xiqoQc) {
		return super.findPage(page, xiqoQc);
	}
	
	@Transactional(readOnly = false)
	public void save(XiqoQc xiqoQc) {
		super.save(xiqoQc);
	}
	
	@Transactional(readOnly = false)
	public void delete(XiqoQc xiqoQc) {
		super.delete(xiqoQc);
	}
	
}