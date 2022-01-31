/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemraccident.fragment.controller;

import org.openmrs.module.kenyaemraccident.Department;
import org.openmrs.module.kenyaemraccident.api.AccidentService;
//import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.openmrs.web.WebConstants;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.*;
import org.openmrs.ui.framework.annotation.*;
import org.openmrs.ui.framework.page.*;
import org.openmrs.ui.framework.page.PageModel;

/**
 *  * Controller for a fragment that shows all departments  
 */
public class NewDepartmentFragmentController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	AccidentService departmentService;
	
	// public void controller(FragmentModel model, @SpringBean("accidentService") AccidentService service)
	// public void controller(FragmentModel model) {
	// 	// AccidentService accidentService = Context.getService(AccidentService.class);
	// 	// model.addAttribute("departments", accidentService.getAllDepartments());
	// 	////model.addAttribute("department", new Department());
	// 	Department exists = department != null ? department : null;
	// 	model.addAttribute("department", newDepartment(exists));
	// }
	
	//public void controller(@FragmentParam(value = "id", required = false) Department department, @RequestParam(value = "returnUrl") String returnUrl, PageModel model) 
	public void controller(FragmentModel model) {
		// Department exists = department != null ? department : null;
		// model.addAttribute("department", newDepartment(exists));
		model.addAttribute("department", new Department());
	}
	
	//public SimpleObject saveDepartment(@MethodParam("newDepartment") @BindParams Department department, UiUtils ui) {
	// public String saveDepartment(WebRequest request, HttpSession httpSession, ModelMap model,
	//         @RequestParam(required = false, value = "action") String action,
	//         @ModelAttribute("department") Department department, BindingResult errors) {
	public SimpleObject saveDepartment(@BindParams Department department, HttpSession httpSession) {
		//ui.validate(form, form, null);
		if (department != null) {
			AccidentService accidentService = Context.getService(AccidentService.class);
			Department savedDepartment = accidentService.saveDepartment(department);
			log.info("Successfully created department : " + savedDepartment.getName() + " and "
			        + savedDepartment.getDescription());
			// Department savedDepartment2 = departmentService.saveDepartment(department);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "department.saved");
			// log.error("Successfully created department2 : " + savedDepartment2.getName() + " and " + savedDepartment2.getDescription());
			return SimpleObject.create("id", department.getId());
		} else {
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "Failed to create department");
			log.error("Failed to create department");
			return (new SimpleObject());
		}
		//return SimpleObject.create("id", department.getId());
		//return "redirect:kenyaemraccident.page";
	}
	
	public Department newDepartment(@RequestParam(value = "id", required = false) Department department) {
		if (department != null) {
			return (department);
		} else {
			return (new Department());
		}
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String submitDepartment(WebRequest request, HttpSession httpSession, ModelMap model,
	        @RequestParam(required = false, value = "action") String action,
	        @ModelAttribute("department") Department department, BindingResult errors) {
		
		MessageSourceService mss = Context.getMessageSourceService();
		AccidentService departmentService = Context.getService(AccidentService.class);
		if (!Context.isAuthenticated()) {
			errors.reject("department.auth.required");
		} else if (mss.getMessage("department.purgeDepartment").equals(action)) {
			try {
				departmentService.purgeDepartment(department);
				httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "department.delete.success");
				return "redirect:departmentList.list";
			}
			catch (Exception ex) {
				httpSession.setAttribute(WebConstants.OPENMRS_ERROR_ATTR, "department.delete.failure");
				log.error("Failed to delete department", ex);
				return "redirect:departmentForm.form?departmentId=" + request.getParameter("departmentId");
			}
		} else {
			departmentService.saveDepartment(department);
			//accidentService.saveDepartment(department);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "department.saved");
		}
		return "redirect:departmentList.list";
	}
	
}
