package org.aisino.passport.sys.controller;

import org.aisino.passport.sys.service.IAosUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制
 *
 */
@Controller
public class HomeController {
		
	@Autowired
	private IAosUserService aosUserService;
	
	
	/**
	 * 跳转到index页面
	 * @return
	 */
	@RequestMapping({"/index.do"})
	public String index(Model model){
		return "index";
	}
	
	
}
