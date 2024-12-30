package com.postweb.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebFilter({"*.user", "*.post"})
public class SessionCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // 캐시 방지 헤더 추가
        httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        httpResponse.setHeader("Pragma", "no-cache");
        httpResponse.setDateHeader("Expires", 0);

        String uri = httpRequest.getRequestURI();
        String contextPath = httpRequest.getContextPath();
        String path = uri.substring(contextPath.length());

        // 로그인 페이지로 이동할 때 세션 초기화
        if (path.equals("/user/userLogin.user") || path.equals("/user/userSignUp.user")) {
            HttpSession session = httpRequest.getSession(false); // 세션이 있을 때만 가져옴
            if (session != null) {
                session.invalidate(); // 세션 초기화
                System.out.println("세션 초기화 완료");
            }
        } else {
            // 일반 요청에서 세션 확인
            HttpSession session = httpRequest.getSession();
            Long userNo = (Long)session.getAttribute("userNo");

            System.out.println("현재 로그인된 유저 No: " + userNo);
        }

        // 다음 필터 또는 서블릿으로 요청 전달
        chain.doFilter(request, response);
    }
}
