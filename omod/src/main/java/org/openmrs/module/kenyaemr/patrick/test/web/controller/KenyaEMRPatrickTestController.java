/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemr.patrick.test.web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.User;
import org.openmrs.api.UserService;
import org.openmrs.api.context.Context;
import org.openmrs.messagesource.MessageSourceService;
import org.openmrs.module.kenyaemr.patrick.test.Department;
import org.openmrs.module.kenyaemr.patrick.test.api.KenyaEMRPatrickTestService;
import org.openmrs.web.WebConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
@Controller("${rootrootArtifactid}.KenyaEMRPatrickTestController")
//@RequestMapping(value = "module/${rootArtifactid}/${rootArtifactid}.form")
@RequestMapping(value = "module/kenyaemr.patrick.test/kenyaemr.patrick.test.form")
public class KenyaEMRPatrickTestController {
	
	/** Logger for this class and subclasses */
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	UserService userService;
	
	/** Success form view name */
	//private final String VIEW = "/module/${rootArtifactid}/${rootArtifactid}";
	private final String VIEW = "/module/kenyaemr.patrick.test/kenyaemr.patrick.test";
	
	/**
	 * Initially called after the getUsers method to get the landing form name
	 * 
	 * @return String form view name
	 */
	@RequestMapping(method = RequestMethod.GET)
	public String onGet() {
		return VIEW;
	}
	
	/**
	 * All the parameters are optional based on the necessity
	 * 
	 * @param httpSession
	 * @param anyRequestObject
	 * @param errors
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public String onPost(HttpSession httpSession, @ModelAttribute("anyRequestObject") Object anyRequestObject,
	        BindingResult errors) {
		
		if (errors.hasErrors()) {
			// return error view
		}
		
		return VIEW;
	}
	
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
	
	@RequestMapping(value = "/module/kenyaemr.patrick.test/kenyaemr.patrick.test.form", method = RequestMethod.POST)
	public String submitDepartment(WebRequest request, HttpSession httpSession, ModelMap model,
	        @RequestParam(required = false, value = "action") String action,
	        @ModelAttribute("department") Department department, BindingResult errors) {
		
		MessageSourceService mss = Context.getMessageSourceService();
		KenyaEMRPatrickTestService departmentService = Context.getService(KenyaEMRPatrickTestService.class);
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
			httpSession.setAttribute(WebConstants.OPENMRS_MSG_ATTR, "department.saved");
		}
		return "redirect:departmentList.list";
	}
	
}
