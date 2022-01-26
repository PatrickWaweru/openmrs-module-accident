/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemrAccident.api.impl;

import java.util.List;

import org.openmrs.api.APIException;
import org.openmrs.api.UserService;
import org.openmrs.api.impl.BaseOpenmrsService;
import org.openmrs.module.kenyaemrAccident.Department;
import org.openmrs.module.kenyaemrAccident.Item;
import org.openmrs.module.kenyaemrAccident.api.KenyaEMRPatrickTestService;
import org.openmrs.module.kenyaemrAccident.api.dao.KenyaEMRPatrickTestDao;

public class KenyaEMRPatrickTestServiceImpl extends BaseOpenmrsService implements KenyaEMRPatrickTestService {
	
	KenyaEMRPatrickTestDao dao;
	
	UserService userService;
	
	/**
	 * Injected in moduleApplicationContext.xml
	 */
	public void setDao(KenyaEMRPatrickTestDao dao) {
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
	
	// private DepartmentDAO dao;
	// /**
	//  * @param dao the dao to set
	//  */
	// public void setDao(DepartmentDAO dao) {
	//     this.dao = dao;
	// }
	// /**
	//  * @return the dao
	//  */
	// public DepartmentDAO getDao() {
	//     return dao;
	// }
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
		return dao.saveDepartment(department);
	}
	
	/**
	 * @see org.openmrs.module.department.api.DepartmentService#purgeDepartment(org.openmrs.module.department.Department)
	 */
	@Override
	public void purgeDepartment(Department department) {
		dao.purgeDepartment(department);
	}
}
