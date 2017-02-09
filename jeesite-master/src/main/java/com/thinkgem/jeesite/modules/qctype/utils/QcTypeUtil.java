package com.thinkgem.jeesite.modules.qctype.utils;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.thinkgem.jeesite.modules.qctype.entity.QcType;
import com.thinkgem.jeesite.modules.qctype.service.QcTypeService;

public class QcTypeUtil{
	@Autowired
	private QcTypeService qcTypeService;

	@SuppressWarnings("unused")
	private List<QcType> getQcTypeList() {
		return qcTypeService.findList(new QcType());
	}

}
