/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xiaoqc.entity;

import java.util.List;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.persistence.TreeEntity;
import com.thinkgem.jeesite.modules.cms.entity.Category;

/**
 * 汽车之家Entity
 * @author 王小强
 * @version 2016-07-12
 */
public class XiqoQc extends TreeEntity<XiqoQc> {
	
	private static final long serialVersionUID = 1L;
	private String name;		// name
	private String description;		// description
	
	public XiqoQc() {
		super();
	}
	

	public XiqoQc(String id){
		this();
		this.id = id;
	}

	@Length(min=0, max=255, message="name长度必须介于 0 和 255 之间")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Length(min=0, max=255, message="description长度必须介于 0 和 255 之间")
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public XiqoQc getParent() {
		// TODO 自动生成的方法存根
		return parent;
	}

	@Override
	public void setParent(XiqoQc parent) {
		this.parent = parent;
		
	}
	public static void sortList(List<XiqoQc> list, List<XiqoQc> sourcelist, String parentId){
		for (int i=0; i<sourcelist.size(); i++){
			XiqoQc e = sourcelist.get(i);
			if (e.getParent()!=null && e.getParent().getId()!=null
					&& e.getParent().getId().equals(parentId)){
				list.add(e);
				// 判断是否还有子节点, 有则继续获取子节点
				for (int j=0; j<sourcelist.size(); j++){
					XiqoQc child = sourcelist.get(j);
					if (child.getParent()!=null && child.getParent().getId()!=null
							&& child.getParent().getId().equals(e.getId())){
						sortList(list, sourcelist, e.getId());
						break;
					}
				}
			}
		}
	}
	
}