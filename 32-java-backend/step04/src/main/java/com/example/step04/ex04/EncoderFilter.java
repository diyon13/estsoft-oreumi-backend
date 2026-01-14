package com.example.step04.ex04;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter("/*")
public class EncoderFilter implements Filter{
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
        request.setCharacterEncoding("UTF-8");

        // 매개 변수 request를 HttpServletRequest 자료형으로 변환
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;

        // 클라이언트가 요청한 URI
        String requestURI = httpServletRequest.getRequestURI();

        // 컨텍스트 경로(context path)
        String contextPath = httpServletRequest.getContextPath();

        System.out.println("RequestURI: " + requestURI);
        System.out.println("contextPath: " + contextPath);

        // 요청 필터에서 서블릿에서 요청 처리를 하기 전의 시간을 구함
        long start = System.currentTimeMillis();

        // 필터 체인의 다음 필터를 실행
        chain.doFilter(request, response);

        /*
         * 응답 필터(response filter)
         */

        // 응답 필터에서 서블릿에서 요청 처리를 끝낸 다음의 시각을 구함
        long end = System.currentTimeMillis();

        // 서블릿의 실행 시간을 구해 출력
        System.out.println("Runtime: " + (end - start) + "ms");

    }
}
