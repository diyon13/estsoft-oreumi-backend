package com.example.step05.ex07;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncoderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CALL: EncoderFilter#init()");
    }

    @Override
    public void destroy() {
        System.out.println("CALL: EncoderFilter#destroy()");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        System.out.println("CALL: EncoderFilter#doFilter()");

        /*
         * 요청 필터(request filter)
         */

        // 클라이언트에서 전송한 데이터의 문자 인코딩 설정
        request.setCharacterEncoding("UTF-8");

        // 매개 변수 request를 HttpServletRequest 자료형으로 변환
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 클라이언트가 요청한 URI
        String requestURI = httpServletRequest.getRequestURI();

        // 컨텍스트 경로(context path)
        String contextPath = httpServletRequest.getContextPath();

        // 서블릿 경로(servlet path)
        String servletPath = httpServletRequest.getServletPath();

        // 요청 경로(path info)
        String pathInfo = httpServletRequest.getPathInfo();

        // 요청 방식(method)
        String method = httpServletRequest.getMethod();

        System.out.println("Request URI: " + requestURI);
        System.out.println("Context Path: " + contextPath);
        System.out.println("Servlet Path: " + servletPath);
        System.out.println("Path Info: " + pathInfo);
        System.out.println("Request Method: " + method);

        // 요청 필터에서 서블릿에서 요청 처리를 하기 전의 시각을 구함
        long start = System.currentTimeMillis();

        //--------------------------------------------------------------------
        // 필터 체인의 다음 필터를 실행
        chain.doFilter(request, response);

        /*
         * 응답 필터(response filter)
         */

        // 응답 필터에서 서블릿에서 요청 처리를 끝낸 다음의 시각을 구함
        long end = System.currentTimeMillis();

        // 서블릿의 실행 시간을 구해 출력
        System.out.printf("Running time: %dms\n", end - start);
    }
}