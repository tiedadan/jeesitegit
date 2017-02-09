/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qcqiche.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.qcqiche.entity.QcQiche;
import com.thinkgem.jeesite.modules.qcqiche.service.QcQicheService;
import com.thinkgem.jeesite.modules.qctype.entity.QcType;
import com.thinkgem.jeesite.modules.qctype.service.QcTypeService;
import com.thinkgem.jeesite.modules.sys.entity.Office;
import com.thinkgem.jeesite.modules.sys.service.OfficeService;

/**
 * 汽车Controller
 * @author 王晓强
 * @version 2016-07-15
 */
@Controller
@RequestMapping(value = "${adminPath}/qcqiche/qcQiche")
public class QcQicheController extends BaseController {

	@Autowired
	private QcQicheService qcQicheService;
	@Autowired
	private QcTypeService qcTypeService;
	@Autowired
	private OfficeService officeService;
	
	@ModelAttribute
	public QcQiche get(@RequestParam(required=false) String id) {
		QcQiche entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = qcQicheService.get(id);
		}
		if (entity == null){
			entity = new QcQiche();
		}
		return entity;
	}
	
//	@RequiresPermissions("qcqiche:qcQiche:view")
	@RequestMapping(value = {"list", ""})
	public String list(QcQiche qcQiche, HttpServletRequest request, HttpServletResponse response, Model model) {
		if(qcQiche.getOffice() != null)
		{
			System.out.println("----------->>"+qcQiche.getOffice().getId());
			Office office = officeService.get(qcQiche.getOffice().getId());
			//qcQiche
			model.addAttribute("officeId", qcQiche.getOffice().getId());
		}
		Page<QcQiche> page = qcQicheService.findPage(new Page<QcQiche>(request, response), qcQiche); 
		
		List<QcType> QcTypes=qcTypeService.findList(new QcType());
		
		model.addAttribute("QcTypes", QcTypes);
		model.addAttribute("page", page);
		return "modules/qcqiche/qcQicheList";
	}

//	@RequiresPermissions("qcqiche:qcQiche:view")
	@RequestMapping(value = "form")
	public String form(QcQiche qcQiche, Model model) {
		List<QcType> QcTypes=qcTypeService.findList(new QcType());
		model.addAttribute("QcTypes", QcTypes);
		model.addAttribute("qcQiche", qcQiche);
		return "modules/qcqiche/qcQicheForm";
	}

//	@RequiresPermissions("qcqiche:qcQiche:edit")
	@RequestMapping(value = "save")
	public String save(QcQiche qcQiche, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qcQiche)){
			return form(qcQiche, model);
		}
		//System.out.println("++++++++++++++"+qcQiche.getOffice().getId());
		qcQicheService.save(qcQiche);
		addMessage(redirectAttributes, "保存汽车成功");
		return "redirect:"+Global.getAdminPath()+"/qcqiche/qcQiche/?repage";
	}
	
	@RequiresPermissions("qcqiche:qcQiche:edit")
	@RequestMapping(value = "delete")
	public String delete(QcQiche qcQiche, RedirectAttributes redirectAttributes) {
		qcQicheService.delete(qcQiche);
		addMessage(redirectAttributes, "删除汽车成功");
		return "redirect:"+Global.getAdminPath()+"/qcqiche/qcQiche/?repage";
	}
	@RequiresPermissions("qcqiche:qcQiche:view")
	@RequestMapping(value = {"index"})
	public String index(QcQiche qcQiche, Model model) {
//        model.addAttribute("list", officeService.findAll());
		return "modules/qcqiche/qcQicheIndex";
	}

}