package org.aisino.apiauth.exception;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aisino.apiauth.common.MsgMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
		
		private  final static   Logger  log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
	
		@ExceptionHandler(Exception.class)
		public    Map<String,Object>  defaultExceptionHandler(HttpServletRequest request,Exception  e){
				log.error("RequestUrl:   {}    exception:{}",request.getRequestURI(),e.getMessage(),e);
				return   MsgMap.getFailMsg(e.getMessage());
		}
	
}
