package org.aisino.zuul.service;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name="aos-api-auth")
public interface ApiUserService {
	@RequestMapping("/apiuser/{account}")
	public  Map<String,Object> queryUser(@PathVariable("account") String account);
}
