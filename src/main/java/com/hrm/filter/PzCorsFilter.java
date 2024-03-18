//package com.hrm.filter;
//
//import java.io.IOException;
//
//import org.springframework.core.Ordered;
//import org.springframework.core.annotation.Order;
//import jakarta.servlet.Filter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.ServletRequest;
//import jakarta.servlet.ServletResponse;
//import jakarta.servlet.annotation.WebFilter;
//import jakarta.servlet.http.HttpServletResponse;
//
//@WebFilter
//@Order(Ordered.HIGHEST_PRECEDENCE)
//public class PzCorsFilter implements Filter {
//
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		System.out.println("doFilter");
//		HttpServletResponse res = ((HttpServletResponse) response);
//		res.addHeader("Access-Control-Allow-Origin", "*");
//		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
//		res.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, HEAD");
//		res.addHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept");
//		res.addHeader("Access-Control-Max-Age", "1728000");
//
////		chain.doFilter(request, response);
//
//	}
//
//}
