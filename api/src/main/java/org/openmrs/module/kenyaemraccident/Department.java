package org.openmrs.module.kenyaemraccident;

import java.io.*;
import org.openmrs.*;
import javax.persistence.*;

//public class Department extends BaseOpenmrsObject implements Serializable
@Entity(name = "kenyaemraccident.Department")
@Table(name = "kenyaemraccident_department")
public class Department extends BaseOpenmrsData {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue
	private Integer departmentId;
	
	private String name;
	
	private String description;
	
	public Integer getDepartmentId() {
		return departmentId;
	}
	
	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}
	
	@Override
	public Integer getId() {
		return getDepartmentId();
	}
	
	@Override
	public void setId(Integer id) {
		setDepartmentId(id);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
