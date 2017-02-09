/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qcqiche.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.qcqiche.entity.QcQiche;
import com.thinkgem.jeesite.modules.qcqiche.dao.QcQicheDao;

/**
 * 汽车Service
 * @author 王晓强
 * @version 2016-07-15
 */
@Service
@Transactional(readOnly = true)
public class QcQicheService extends CrudService<QcQicheDao, QcQiche> {

	public QcQiche get(String id) {
		return super.get(id);
	}
	
	public List<QcQiche> findList(QcQiche qcQiche) {
		return super.findList(qcQiche);
	}
	
	public Page<QcQiche> findPage(Page<QcQiche> page, QcQiche qcQiche) {
		return super.findPage(page, qcQiche);
	}
	
	@Transactional(readOnly = false)
	public void save(QcQiche qcQiche) {
		super.save(qcQiche);
	}
	
	@Transactional(readOnly = false)
	public void delete(QcQiche qcQiche) {
		super.delete(qcQiche);
	}
	
}