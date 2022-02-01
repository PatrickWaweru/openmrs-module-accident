package org.openmrs.module.kenyaemraccident.page.controller;

//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.api.db.hibernate.DbSessionFactory;
//import org.openmrs.module.afyastat.api.service.InfoService;
//import org.openmrs.module.afyastat.model.AfyaStatQueueData;
//import org.openmrs.module.afyastat.model.ErrorInfo;
//import org.openmrs.module.afyastat.model.ErrorMessagesInfo;
import org.openmrs.module.kenyaemraccident.Department;
import org.openmrs.module.kenyaemraccident.api.AccidentService;
import org.openmrs.module.kenyaemraccident.fragment.controller.DepartmentsListFragmentController;
import org.openmrs.module.kenyaui.KenyaUiUtils;
import org.openmrs.module.kenyaui.annotation.AppPage;
import org.openmrs.ui.framework.SimpleObject;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.annotation.SpringBean;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.PageRequest;
import org.openmrs.util.PrivilegeConstants;
import org.openmrs.module.appframework.domain.AppDescriptor;
import org.openmrs.module.appframework.service.AppFrameworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.openmrs.module.kenyaui.annotation.*;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.openmrs.ui.framework.page.*;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//@AppPage("kenyaemr.accident.home")
@Controller
@AppPage("kenyaemr.accident.home")
public class KenyaemraccidentPageController {
	
	protected static final Log log = LogFactory.getLog(KenyaemraccidentPageController.class);
	
	public void controller(UiUtils ui, PageModel model, PageContext pageContext) {
		//model.addAttribute("returnUrl", url);
		AccidentService accidentService = Context.getService(AccidentService.class);
		AppDescriptor app = Context.getService(AppFrameworkService.class).getApp("kenyaemr.accident.home");
		pageContext.getRequest().getRequest().setAttribute("kenyaui.current-app", app);
		pageContext.getModel().addAttribute("currentApp", app);
		System.err.println("controller was called");
		log.info("controller was called");
	}
	
	//	@Autowired
	//	KenyaUiUtils kenyaUi2;
	
	//public void get(@SpringBean KenyaUiUtils kenyaUi, UiUtils ui, PageModel model, PageRequest pageRequest) {
	public void get(UiUtils ui, PageModel model, PageRequest pageRequest, PageContext pageContext) {
		//public void get(@SpringBean KenyaUiUtils kenyaUi, UiUtils ui, PageModel model) {
		AccidentService accidentService = Context.getService(AccidentService.class);
		AppDescriptor app = Context.getService(AppFrameworkService.class).getApp("kenyaemr.accident.home");
		pageContext.getRequest().getRequest().setAttribute("kenyaui.current-app", app);
		pageContext.getModel().addAttribute("currentApp", app);
		System.err.println("Get was called: Home Page controller");
		log.info("Get was called: Home Page controller");
		//		try {
		//			AppDescriptor currentApp = kenyaUi2.getCurrentApp(pageRequest);
		//			log.info("main page controller: " + currentApp);
		//			System.err.println("main page controller: " + currentApp);
		//		}
		//		catch (Exception e) {
		//			log.info("main page controller Error: " + e.getMessage());
		//			System.err.println("main page controller Error: " + e.getMessage());
		//		}
	}
	
}
