package com.postweb.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.postweb.domain.UserDTO;
import com.postweb.mapper.UserMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.mybatis.MybatisUtil;

public class UserServiceImpl implements UserService {
	
    private final SqlSessionFactory sqlSessionFactory = MybatisUtil.getSqlSessionFactory();

	@Override
	public void userSignUp(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userName = request.getParameter("user_name");
		String userNick = request.getParameter("user_nick");
		String userId = request.getParameter("user_id");
		String userPw = request.getParameter("user_pw");
		
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPw(userPw);
		dto.setUserName(userName);
		dto.setUserNick(userNick);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			UserMapper mapper = sql.getMapper(UserMapper.class);
			mapper.userSignUp(dto);
			System.out.println("유저가입정보: "+dto.toString());
			sql.commit();
			
			HttpSession session = request.getSession();
			session.setAttribute("userId", userId);
			
			response.sendRedirect(request.getContextPath() + "../post/postList.post");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 중 서버 error ");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
		
	}

}
