package com.flash.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@ControllerAdvice
public class ErrorController {

	@RequestMapping(value = "/error/404")
	public String error404(HttpServletRequest request) {

		String origialUri = (String) request.getAttribute("javax.servlet.forward.request_uri");
		System.out.println("Requested URL not found : " + request.getMethod() + " " + origialUri);

		return "error/404";
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException(HttpServletRequest request, Exception ex) {
		System.out.println("Error page exception : " + getRequestURL(request));
		ex.printStackTrace();

		ModelAndView result = new ModelAndView("/error/500");

		String stackTrace = getHTMLStackTrace(ExceptionUtils.getStackFrames(ex));

		result.addObject("requestUrl", getRequestURL(request));
		result.addObject("message", ex.getMessage());
		result.addObject("stackTrace", stackTrace);

		if (ex.getCause() != null) {
			result.addObject("rootcause", ExceptionUtils.getRootCause(ex));

			String roottrace = getHTMLStackTrace(ExceptionUtils.getRootCauseStackTrace(ex));
			result.addObject("roottrace", roottrace);
		}

		return result;
	}

	private String getHTMLStackTrace(String[] stack) {
//		String stackTrace = ExceptionUtils.getStackTrace(ex);
//
//		stackTrace = stackTrace.replaceAll("[\\r\\f\\n]+", "<br/>");
//		stackTrace = stackTrace.replaceAll("\\t", " &nbsp; &nbsp; &nbsp;");
//
//		return stackTrace;

		StringBuffer result = new StringBuffer();
		for (String frame : stack) {
			if (frame.contains("perscholas")) {
				result.append(" &nbsp; &nbsp; &nbsp;" + frame.trim().substring(3) + "<br>\n");
			} else if (frame.contains("Caused by:")) {
				result.append("Caused By:<br>");
			}
		}

		return result.toString();
	}

	private String getRequestURL(HttpServletRequest request) {
		String result = request.getRequestURL().toString();
		if (request.getQueryString() != null) {
			result = result + "?" + request.getQueryString();
		}

		return result;
	}

}
