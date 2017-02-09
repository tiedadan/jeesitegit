/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qcqiche.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.modules.qctype.entity.QcType;
import com.thinkgem.jeesite.modules.sys.entity.Office;

/**
 * 汽车Entity
 * @author 王晓强
 * @version 2016-07-15
 */
public class QcQiche extends DataEntity<QcQiche> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String decription;		// decription
	private String price;		// price
	private Office office;	// 归属部门
//	private String qcTypeId;		// qc_type_id
	private QcType qcType;
	private String beginPrice;		// 开始 price
	private String endPrice;		// 结束 price
	public QcQiche() {
		super();
	}

	public Office getOffice() {
		return office;
	}

	public void setOffice(Office office) {
		this.office = office;
	}

	public QcType getQcType() {
		return qcType;
	}

	public void setQcType(QcType qcType) {
		this.qcType = qcType;
	}

	public QcQiche(String id){
		super(id);
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="decription长度必须介于 0 和 255 之间")
	public String getDecription() {
		return decription;
	}

	public void setDecription(String decription) {
		this.decription = decription;
	}
	
	@Length(min=1, max=255, message="price长度必须介于 1 和 255 之间")
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

//	public String getQcTypeId() {
//		return qcTypeId;
//	}
//
//	public void setQcTypeId(String qcTypeId) {
//		this.qcTypeId = qcTypeId;
//	}
	
	public String getBeginPrice() {
		return beginPrice;
	}

	public void setBeginPrice(String beginPrice) {
		this.beginPrice = beginPrice;
	}
	
	public String getEndPrice() {
		return endPrice;
	}

	public void setEndPrice(String endPrice) {
		this.endPrice = endPrice;
	}
		
}