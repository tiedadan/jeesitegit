/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.xiaoqc.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.cms.entity.Category;
import com.thinkgem.jeesite.modules.xiaoqc.entity.XiqoQc;
import com.thinkgem.jeesite.modules.xiaoqc.service.XiqoQcService;

/**
 * 汽车之家Controller
 * @author 王小强
 * @version 2016-07-12
 */
@Controller
@RequestMapping(value = "${adminPath}/xiaoqc/xiqoQc")
public class XiqoQcController extends BaseController {

	@Autowired
	private XiqoQcService xiqoQcService;
	
	@ModelAttribute
	public XiqoQc get(@RequestParam(required=false) String id) {
		XiqoQc entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = xiqoQcService.get(id);
		}
		if (entity == null){
			entity = new XiqoQc();
		}
		return entity;
	}
	
	@RequestMapping(value = {"list", ""})
	public String list(XiqoQc xiqoQc, HttpServletRequest request, HttpServletResponse response, Model model) {
//		Page<XiqoQc> page = xiqoQcService.findPage(new Page<XiqoQc>(request, response), xiqoQc); 
//		model.addAttribute("page", page);
		
		List<XiqoQc> list = Lists.newArrayList();
		List<XiqoQc> sourcelist = xiqoQcService.findList(new XiqoQc());
		XiqoQc.sortList(list, sourcelist, "1");
        model.addAttribute("list", list);
		return "modules/xiaoqc/xiqoQcList";
	}

//	@RequiresPermissions("xiaoqc:xiqoQc:view")
	@RequestMapping(value = "form")
	public String form(XiqoQc xiqoQc, Model model) {
		if (xiqoQc.getParent()==null||xiqoQc.getParent().getId()==null){
			xiqoQc.setParent(new XiqoQc("1"));
		}
		XiqoQc parent = xiqoQcService.get(xiqoQc.getParent().getId());
		xiqoQc.setParent(parent);
		model.addAttribute("xiqoQc", xiqoQc);
		return "modules/xiaoqc/xiqoQcForm";
	}
	@RequiresPermissions("xiaoqc:xiqoQc:edit")
	@RequestMapping(value = "save")
	public String save(XiqoQc xiqoQc, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, xiqoQc)){
			return form(xiqoQc, model);
		}
		xiqoQcService.save(xiqoQc);
		addMessage(redirectAttributes, "保存汽车之家成功");
		return "redirect:"+Global.getAdminPath()+"/xiaoqc/xiqoQc/?repage";
	}
	
	@RequestMapping(value = "delete")
	public String delete(XiqoQc xiqoQc, RedirectAttributes redirectAttributes) {
		xiqoQcService.delete(xiqoQc);
		addMessage(redirectAttributes, "删除汽车之家成功");
		return "redirect:"+Global.getAdminPath()+"/xiaoqc/xiqoQc/?repage";
	}
	@RequiresUser
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(String module, @RequestParam(required=false) String extId, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<XiqoQc> list = xiqoQcService.findList(new XiqoQc());
		System.out.println("???????????"+list.size());
		for (int i=0; i<list.size(); i++){
			XiqoQc e = list.get(i);
			System.out.println("???????????"+e.getName());
			System.out.println("???????????"+extId);
			if (extId == null || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParent()!=null?e.getParent().getId():0);
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}

}