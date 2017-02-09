/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qctype.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.ActEntity;
import com.thinkgem.jeesite.modules.sys.entity.User;

/**
 * 汽车品牌Entity
 * @author 王晓强
 * @version 2016-07-15
 */
public class QcType extends ActEntity<QcType> {
	
	private static final long serialVersionUID = 1L;
	private User user;//单位信息
	private String typename;		// 品牌
	private String state;//状态
	private String userId;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public QcType() {
		super();
	}

	public QcType(String id){
		super(id);
	}

	@Length(min=0, max=255, message="品牌长度必须介于 0 和 255 之间")
	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}