/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemraccident.api.impl;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.kenyaemraccident.Department;
import org.openmrs.module.kenyaemraccident.Item;
import org.openmrs.module.kenyaemraccident.api.AccidentService;
import org.openmrs.module.kenyaemraccident.api.dao.AccidentDao;

public class AccidentServiceImpl extends BaseOpenmrsService implements AccidentService {
	
	AccidentDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(AccidentDao dao) {
		this.dao = dao;
	}
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Override
	public Item getItemByUuid(String uuid) throws APIException {
		return dao.getItemByUuid(uuid);
	}
	
	@Override
	public Item saveItem(Item item) throws APIException {
		if (item.getOwner() == null) {
			item.setOwner(userService.getUser(1));
		}
		
		return dao.saveItem(item);
	}
	
	/**
	 * @see org.openmrs.module.department.api.DepartmentService#getAllDepartments()
	 */
	@Override
	public List<Department> getAllDepartments() {
		return dao.getAllDepartments();
	}
	
	/**
	 * @see org.openmrs.module.department.api.DepartmentService#getDepartment(java.lang.Integer)
	 */
	@Override
	public Department getDepartment(Integer departmentId) {
		return dao.getDepartment(departmentId);
	}
	
	/**
	 * @see org.openmrs.module.department.api.DepartmentService#saveDepartment(org.openmrs.module.department.Department)
	 */
	@Override
	public Department saveDepartment(Department department) {
		// if (department.getOwner() == null) {
		// 	department.setOwner(userService.getUser(1));
		// }
		
		return dao.saveDepartment(department);
	}
	
	/**
	 * @see org.openmrs.module.department.api.DepartmentService#purgeDepartment(org.openmrs.module.department.Department)
	 */
	@Override
	public void purgeDepartment(Department department) {
		dao.purgeDepartment(department);
	}
	
	@Override
	public Department getDepartmentByUuid(String uuid) throws APIException {
		return dao.getDepartmentByUuid(uuid);
	}
}
