package org.aisino.passport.sys.controller;


import org.aisino.App;
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
public class TestAosRoleController {  
 
   MockMvc mvc;  
 
   @Autowired  
   WebApplicationContext webApplicationConnect;  
 
 
   String expectedJson;  
 
   @Before  
   public void setUp() throws JsonProcessingException {  
       mvc = MockMvcBuilders.webAppContextSetup(webApplicationConnect).build();  
   }  
 
   /**
    * 查询当前人员职务角色资源并选中角色资源
    * @throws Exception
    */
   @Test  
   public void queryrolerestree() throws Exception {  
       String uri = "/aosrole/add.do?resid=-2&rolename=新增角色2&key=00d343bd9bc443b8u8y6078ba4f37963";  
       MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).accept(MediaType.APPLICATION_JSON))  
               .andReturn();  
       int status = mvcResult.getResponse().getStatus();  
       String content = mvcResult.getResponse().getContentAsString();  
       System.out.println(content);
       Assert.assertTrue("错误，正确的返回值为200", status == 200);  
   }  
 
 
}  
