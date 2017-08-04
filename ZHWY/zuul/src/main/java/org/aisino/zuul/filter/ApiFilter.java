package org.aisino.zuul.filter;

import javax.servlet.http.HttpServletRequest;
import org.aisino.utils.common.JsonUtil;
import org.aisino.zuul.common.XConstants;
import org.aisino.zuul.service.ApiTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

/**
 * API过滤器，用于验证token
 * @author hexing
 */
@Component
public class ApiFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(ApiFilter.class);
	
	@Autowired
	ApiTokenService  service;

	@Override
	public String filterType() {
		return "pre";
	}

	@Override
	public int filterOrder() {
		return 0;
	}
	
	/**
	 * 通过请求路径判断是否需要使用此过滤器
	 */
	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		String  reqUrl = ctx.getRequest().getRequestURI();
		
		/**
		 * 记录访问资源信息
		 */
		try{
			HttpServletRequest request = ctx.getRequest();
			String interface_req_parm = JsonUtil.map2json(request.getParameterMap());
			service.recordBehavior(reqUrl, interface_req_parm);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		
		if(reqUrl.startsWith("/api/")){
			log.info("请求路径：{}，校验token!",reqUrl);
			//暂定文件访问不做验证
			if(reqUrl.startsWith("/api/file/")){
				log.info("请求路径：{}，不验证校验token!",reqUrl);
				return false;
			}
			return true;
		}else{
			log.info("请求路径：{}，不验证校验token!",reqUrl);
			return false;
		}
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		if("true".equals(XConstants.TOKEN_SWITCH)){
			//token优先从header内取，取不到才从parameter中取
			String  token = request.getHeader("api_token");
			if(StringUtils.isEmpty(token)){
				token = request.getParameter("api_token");
			}
			//token验证未通过
			if(!service.checkToken(token, request.getRequestURI())){
				//token为空或者token验证失效
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(401);
				try {
					ctx.getResponse().getWriter().write("{\"code\":\"1\",\"msg\":\"Invalid request!\"}");
					ctx.getResponse().getWriter().close();
				}catch (Exception e){
					log.error("",e);
				}
			}
		}
		
		if("true".equals(XConstants.APPUSERLOGIN_SWITCH)){
			/**
			 * 未登陆
			 */
			String sessionid = request.getHeader("sessionid");
			if(StringUtils.isEmpty(sessionid)){
				sessionid = request.getParameter("sessionid");
			}
			System.out.println("sessionid:" + sessionid);
			if(!service.checkUserLogin(sessionid)){
				ctx.setSendZuulResponse(false);
				ctx.setResponseStatusCode(200);
				try {
					ctx.getResponse().getWriter().write("{\"code\":\"-9999\",\"msg\":\"您已下线!\"}");
					ctx.getResponse().getWriter().close();
				}catch (Exception e){
					log.error("",e);
				}
			}
		}
		
		
        return null;
	}
}
