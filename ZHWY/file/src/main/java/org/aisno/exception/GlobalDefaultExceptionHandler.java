package org.aisno.exception;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 全局异常捕获处理
 * @author root
 *
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler {
	@ExceptionHandler(value = Exception.class)
	public void defaultErrorHandler(HttpServletRequest request,
			HttpServletResponse response, Exception e) {
		// 打印异常信息:
		e.printStackTrace();
		try {
			/*Writer w = new StringWriter();
		    e.printStackTrace(new PrintWriter(w));*/
			String exception = e.getMessage();
			exception = "{\"key\":\"\", \"filename\":\"\", \"msg\":\"请上传大小小于1MB图片！\", \"code\":-1}";
			response.getOutputStream().write(exception.getBytes());
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
