/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.qctype.web;

import java.util.Date;

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
import com.thinkgem.jeesite.modules.qctype.entity.QcType;
import com.thinkgem.jeesite.modules.qctype.service.QcTypeService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 汽车品牌Controller
 * 
 * @author 王晓强
 * @version 2016-07-15
 */
@Controller
@RequestMapping(value = "${adminPath}/qctype/qcType")
public class QcTypeController extends BaseController {

	@Autowired
	private QcTypeService qcTypeService;

	@ModelAttribute
	public QcType get(@RequestParam(required = false) String id) {
		QcType entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = qcTypeService.get(id);
		}
		if (entity == null) {
			entity = new QcType();
		}
		return entity;
	}

	// @RequiresPermissions("qctype:qcType:view")
	@RequestMapping(value = { "list", "" })
	public String list(QcType qcType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		Page<QcType> page = qcTypeService.findPage(new Page<QcType>(request,
				response), qcType);
		model.addAttribute("page", page);
		return "modules/qctype/qcTypeList";
	}

	// @RequiresPermissions("qctype:qcType:view")
	@RequestMapping(value = "form")
	public String form(QcType qcType, Model model) {
		if (StringUtils.isBlank(qcType.getId())) {
			qcType.setCreateDate(new Date());
			qcType.setUser(UserUtils.getUser());
		} else {
			qcType.setUser(UserUtils.get(qcType.getUserId()));
		}
		String view = "qcTypeForm";
		// 查看审批申请单
		if (StringUtils.isNotBlank(qcType.getProcInsId())) {// .getAct().getProcInsId())){

			// 环节编号
			String taskDefKey = qcType.getAct().getTaskDefKey();
			// 修改环节
			if ("modify".equals(taskDefKey)) {
				view = "qcTypeForm";
			}
			// 审核环节
			else if ("approve".equals(taskDefKey)) {
				view = "qcTypeFormApprove";
			}

			// 查看工单
			if (qcType.getAct().isFinishTask()) {
				view = "qcTypeFormDetail";
			}
		}
		model.addAttribute("qcType", qcType);
		// return "modules/qctype/qcTypeForm";
		return "modules/qctype/" + view;
	}

	@RequiresPermissions("qctype:qcType:edit")
	@RequestMapping(value = "save")
	public String save(QcType qcType, Model model,
			RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, qcType)) {
			return form(qcType, model);
		}
		// if (StringUtils.isNotBlank(qcType.getState())) {
		// qcType.setState("0");
		// }
		if (StringUtils.isBlank(qcType.getState())) {
			qcType.setState("0");
		}
		qcTypeService.save(qcType);
		if ("save".equals(qcType.getAct().getFlag())) {
			addMessage(redirectAttributes, "保存汽车品牌信息成功");
		} else if ("yes".equals(qcType.getAct().getFlag())) {
			addMessage(redirectAttributes, "提交汽车品牌信息成功");
		}

		return "redirect:" + Global.getAdminPath() + "/qctype/qcType/?repage";
	}

	@RequiresPermissions("qctype:qcType:edit")
	@RequestMapping(value = "saveApprove")
	public String saveApprove(QcType qcType, Model model) {
		if (StringUtils.isBlank(qcType.getAct().getFlag())
				|| StringUtils.isBlank(qcType.getAct().getComment())) {
			addMessage(model, "请填写审核意见。");
			return form(qcType, model);
		}
		qcTypeService.approveSave(qcType);
		return "redirect:" + adminPath + "/act/task/todo/";
	}

	
	@RequiresPermissions("qctype:qcType:edit")
	@RequestMapping(value = "delete")
	public String delete(QcType qcType, RedirectAttributes redirectAttributes) {
		qcTypeService.delete(qcType);
		addMessage(redirectAttributes, "删除汽车品牌成功");
		return "redirect:" + Global.getAdminPath() + "/qctype/qcType/?repage";
	}

	@RequiresPermissions("qctype:qcType:view")
	@RequestMapping(value = "exportWord")
	public String exportWord(QcType qcType, HttpServletRequest request,
			HttpServletResponse response, Model model) {
		String filename = "C:\\Users\\xiaoqiang\\Desktop\\img\\title.docx";
		model.addAttribute("filename", filename);
		// response.reset();
		// response.setContentType("application/x-msword");
		// response.addHeader("Content-isposition","attachment;filename=" +
		// "C:\\x.doc");
		return "modules/qctype/iframe";
	}

}