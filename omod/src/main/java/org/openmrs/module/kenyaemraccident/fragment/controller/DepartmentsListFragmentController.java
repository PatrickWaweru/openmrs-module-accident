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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.kenyaemraccident.api.AccidentService;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.annotation.*;
import org.openmrs.ui.framework.fragment.FragmentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.openmrs.api.context.Context;
import org.openmrs.module.kenyaui.*;
import org.openmrs.module.kenyaui.KenyaUiUtils;

/**
 *  * Controller for a fragment that shows all departments  
 */
public class DepartmentsListFragmentController {
	
	protected static final Log log = LogFactory.getLog(DepartmentsListFragmentController.class);
	
	// @Autowired
	// AccidentService accidentService;
	
	//	@Autowired
	//	KenyaUiUtils kenyaUi;
	
	// public void controller(FragmentModel model, @SpringBean("accidentService") AccidentService service)
	// public void controller(FragmentModel model, @SpringBean KenyaUiUtils kenyaUi, @SpringBean("accidentService") AccidentService service) {
	public void controller(FragmentModel model) {
		AccidentService accidentService = Context.getService(AccidentService.class);
		model.addAttribute("departments", accidentService.getAllDepartments());
	}
	
}
