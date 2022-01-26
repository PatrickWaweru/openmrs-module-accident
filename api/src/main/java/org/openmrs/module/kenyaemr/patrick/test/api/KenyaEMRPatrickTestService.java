/**
 * This Source Code Form is subject to the terms of the Mozilla Public License,
 * v. 2.0. If a copy of the MPL was not distributed with this file, You can
 * obtain one at http://mozilla.org/MPL/2.0/. OpenMRS is also distributed under
 * the terms of the Healthcare Disclaimer located at http://openmrs.org/license.
 *
 * Copyright (C) OpenMRS Inc. OpenMRS is a registered trademark and the OpenMRS
 * graphic logo is a trademark of OpenMRS Inc.
 */
package org.openmrs.module.kenyaemr.patrick.test.api;

import java.util.List;

import org.openmrs.annotation.Authorized;
import org.openmrs.api.APIException;
import org.openmrs.api.OpenmrsService;
import org.openmrs.module.kenyaemr.patrick.test.KenyaEMRPatrickTestConfig;
import org.openmrs.module.kenyaemr.patrick.test.Department;
import org.openmrs.module.kenyaemr.patrick.test.Item;
import org.springframework.transaction.annotation.Transactional;

/**
 * The main service of this module, which is exposed for other modules. See
 * moduleApplicationContext.xml on how it is wired up.
 */
public interface KenyaEMRPatrickTestService extends OpenmrsService {
	
	/**
	 * Returns an item by uuid. It can be called by any authenticated user. It is fetched in read
	 * only transaction.
	 * 
	 * @param uuid
	 * @return
	 * @throws APIException
	 */
	@Authorized()
	@Transactional(readOnly = true)
	Item getItemByUuid(String uuid) throws APIException;
	
	/**
	 * Saves an item. Sets the owner to superuser, if it is not set. It can be called by users with
	 * this module's privilege. It is executed in a transaction.
	 * 
	 * @param item
	 * @return
	 * @throws APIException
	 */
	@Authorized(KenyaEMRPatrickTestConfig.MODULE_PRIVILEGE)
	@Transactional
	Item saveItem(Item item) throws APIException;
	
	/**
	 * Gets a list of departments.
	 * 
	 * @return the department list.
	 */
	@Transactional(readOnly = true)
	List<Department> getAllDepartments();
	
	/**
	 * Gets a department for a given id.
	 * 
	 * @param id the department id
	 * @return the department with the given id
	 */
	@Transactional(readOnly = true)
	Department getDepartment(Integer departmentId);
	
	/**
	 * Saves a new or existing department.
	 * 
	 * @param department the department to save.
	 * @return the saved department.
	 */
	Department saveDepartment(Department department);
	
	/**
	 * Deletes a department from the database.
	 * 
	 * @param department the department to delete.
	 */
	void purgeDepartment(Department department);
}
