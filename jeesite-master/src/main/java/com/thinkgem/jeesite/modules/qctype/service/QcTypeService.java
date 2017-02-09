/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qctype.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.act.service.ActTaskService;
import com.thinkgem.jeesite.modules.act.utils.ActUtils;
import com.thinkgem.jeesite.modules.qctype.entity.QcType;
import com.thinkgem.jeesite.modules.qctype.dao.QcTypeDao;

/**
 * 汽车品牌Service
 * @author 王晓强
 * @version 2016-07-15
 */
@Service
@Transactional(readOnly = true)
public class QcTypeService extends CrudService<QcTypeDao, QcType> {

	@Autowired
	private ActTaskService actTaskService;
	public QcType get(String id) {
		return super.get(id);
	}
	
	public List<QcType> findList(QcType qcType) {
		return super.findList(qcType);
	}
	
	public Page<QcType> findPage(Page<QcType> page, QcType qcType) {
		return super.findPage(page, qcType);
	}
	@Transactional(readOnly = false)
	public void approveSave(QcType qcType) {
		
		// 设置意见
		qcType.getAct().setComment(("yes".equals(qcType.getAct().getFlag())?"[同意] ":"[驳回] ")+qcType.getAct().getComment());
		qcType.setRemarks((!StringUtils.isBlank(qcType.getRemarks())?qcType.getRemarks()+";":"") + qcType.getAct().getComment());
		qcType.preUpdate();
		
		// 提交流程任务
		Map<String, Object> vars = Maps.newHashMap();
		vars.put("pass", "yes".equals(qcType.getAct().getFlag())? "1" : "0");
		qcType.setState("yes".equals(qcType.getAct().getFlag())? "2" : "1");
		dao.update(qcType);
		actTaskService.complete(qcType.getAct().getTaskId(), qcType.getAct().getProcInsId(), qcType.getAct().getComment(), vars);
		
	}
	@Transactional(readOnly = false)
	public void save(QcType qcType) {
		boolean isSave = false;
		if ("save".equals(qcType.getAct().getFlag())) {
			//仅仅是保存
			isSave = true;
		}
		// 申请发起
		if (StringUtils.isBlank(qcType.getId())){
			qcType.preInsert();
			dao.insert(qcType);
		// 启动流程
		actTaskService.startProcess(ActUtils.PD_QC_TYPE[0], ActUtils.PD_QC_TYPE[1], qcType.getId(), "汽车品牌信息添加");
			
		}
		super.save(qcType);
	}
	
	@Transactional(readOnly = false)
	public void delete(QcType qcType) {
		super.delete(qcType);
	}
	
}