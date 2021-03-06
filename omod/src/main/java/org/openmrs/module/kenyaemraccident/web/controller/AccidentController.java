/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemraccident.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.kenyaemraccident.Department;
import org.openmrs.module.kenyaemraccident.api.AccidentService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.WebRequest;

/**
 * This class configured as controller using annotation and mapped with the URL of
 * 'module/${rootArtifactid}/${rootArtifactid}Link.form'.
 */
@Controller("${rootrootArtifactid}.AccidentController")
//@RequestMapping(value = "module/${rootArtifactid}/${rootArtifactid}.form")
//@RequestMapping(value = "module/kenyaemraccident/kenyaemraccident.form")
@RequestMapping(value = "module/kenyaemraccident/")
public class AccidentController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;
	
	@Autowired
	AccidentService accidentService;
	
	/** Success form view name */
	//private final String VIEW = "/module/${rootArtifactid}/${rootArtifactid}";
	private final String FORM_VIEW = "/module/kenyaemraccident/kenyaemraccident";
	
	private final String SUCCESS_VIEW = "/module/kenyaemraccident/departmentlist";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	// @RequestMapping(method = RequestMethod.GET)
	// public String onGet() {
	// 	model.addAttribute("person", new Person());
	// 	return VIEW;
	// }
	@RequestMapping(value = "/kenyaemraccident.form", method = RequestMethod.GET)
	public String onGet(Model model) {
		model.addAttribute("department", new Department());
		return FORM_VIEW;
	}
	
	@RequestMapping(value = "/departmentList.list", method = RequestMethod.GET)
	public String onSuccessSave(Model model) {
		//model.addAttribute("department", new Department());
		//List<User> users = userService.getAllUsers();
		List<Department> departments = accidentService.getAllDepartments();
		model.addAttribute("departments", departments);
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		//return users;
		return SUCCESS_VIEW;
	}
	
	/**
	 * All the parameters are optional based on the necessity
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	// @RequestMapping(method = RequestMethod.POST)
	// public String onPost(HttpSession httpSession, @ModelAttribute("anyRequestObject") Object anyRequestObject,
	//         BindingResult errors) {
	
	// 	if (errors.hasErrors()) {
	// 		// return error view
	// 	}
	
	// 	return VIEW;
	// }
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just defined
	 * by the return type of this method
	 */
	@ModelAttribute("users")
	protected List<User> getUsers() throws Exception {
		List<User> users = userService.getAllUsers();
		
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		return users;
	}
	
	/**
	 * This class returns the form backing object. This can be a string, a boolean, or a normal java
	 * pojo. The bean name defined in the ModelAttribute annotation and the type can be just defined
	 * by the return type of this method
	 */
	@ModelAttribute("departments")
	protected List<Department> getDepartments() throws Exception {
		List<Department> departments = accidentService.getAllDepartments();
		// this object will be made available to the jsp page under the variable name
		// that is defined in the @ModuleAttribute tag
		return departments;
	}
	
	/**
	 * To allow posting data
	 * 
	 * @param request
	 * @param httpSession
	 * @param model
	 * @param action
	 * @param department
	 * @param errors
	 * @return
	 */
	////@RequestMapping(value = "/module/kenyaemraccident/kenyaemraccident.form", method = RequestMethod.POST)
	////@RequestMapping(method = RequestMethod.POST)
	@RequestMapping(value = "/kenyaemraccident.form", method = RequestMethod.POST)
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
			accidentService.saveDepartment(department);
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "department.saved");
		}
		return "redirect:departmentList.list";
	}
	
}
