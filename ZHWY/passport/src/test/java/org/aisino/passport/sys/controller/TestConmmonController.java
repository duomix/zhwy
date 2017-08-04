package org.aisino.passport.sys.controller;


import org.aisino.App;
import org.aisino.passport.sys.service.IAosUserDutyService;
import org.junit.Assert;  
import org.junit.Before;  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.boot.test.SpringApplicationConfiguration;  
import org.springframework.http.MediaType;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
import org.springframework.test.context.web.WebAppConfiguration;  
import org.springframework.test.web.servlet.MockMvc;  
import org.springframework.test.web.servlet.MvcResult;  
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;  
import org.springframework.test.web.servlet.setup.MockMvcBuilders;  
import org.springframework.web.context.WebApplicationContext;  
import com.fasterxml.jackson.core.JsonProcessingException;  
 


/**
 * Controller 层测试列子
 * @author XZY
 * 2017-5-2-下午5:34:50
 */
@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = App.class)  
@WebAppConfiguration  
public class TestConmmonController {  
 
   MockMvc mvc;  
 
   @Autowired  
   WebApplicationContext webApplicationConnect;  
 
   String expectedJson;  
 
   @Before  
   public void setUp() throws JsonProcessingException {  
       mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();  
   }  
 
   /**
    * 测试版权
    * @throws Exception
    */
   //@Test  
   public void setcopyright() throws Exception {  
       String uri = "/setcopyright.do?copyright=版权";  
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
               .andReturn();  
       int status = mvcResult.getResponse().getStatus();  
       String content = mvcResult.getResponse().getContentAsString();  
       System.out.println(content);
       Assert.assertTrue("错误，正确的返回值为200", status == 200);  
   }  
   
   /**
    * 设置管理首页样式
    * @throws Exception
    */
   //@Test  
   public void setadminhomestyle() throws Exception {  
       String uri = "/setadminhomestyle.do?home_logo=home_logo.jpg&home_title=hometitle.jpg";  
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
               .andReturn();  
       int status = mvcResult.getResponse().getStatus();  
       String content = mvcResult.getResponse().getContentAsString();  
       System.out.println(content);
       Assert.assertTrue("错误，正确的返回值为200", status == 200);  
   }  
   
   
   /**
    * 登陆页面样式设置
    * @throws Exception
    */
   @Test  
   public void setloginstyle() throws Exception {  
       String uri = "/setloginstyle.do?login_style=str_login_style_1&pageimg_bak=pageimg_bak.jpg&pageimg_logo=pageimg_logo.jpg&pageimg_title=pageimg_title.jpg";  
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
               .andReturn();  
       int status = mvcResult.getResponse().getStatus();  
       String content = mvcResult.getResponse().getContentAsString();  
       System.out.println(content);
       Assert.assertTrue("错误，正确的返回值为200", status == 200);  
   }  
 
 
}  
