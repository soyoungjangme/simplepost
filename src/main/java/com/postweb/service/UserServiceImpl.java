package com.postweb.service;

import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.postweb.constants.UrlPaths;
import com.postweb.domain.UserDTO;
import com.postweb.mapper.UserMapper;

import at.favre.lib.crypto.bcrypt.BCrypt;
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
		
		String hashedPassword = BCrypt.withDefaults().hashToString(12, userPw.toCharArray());
		
		UserDTO dto = new UserDTO();
		dto.setUserId(userId);
		dto.setUserPw(hashedPassword);
		dto.setUserName(userName);
		dto.setUserNick(userNick);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			UserMapper mapper = sql.getMapper(UserMapper.class);
			mapper.userSignUp(dto);
			//System.out.println("유저가입정보: "+dto.toString());
			sql.commit();
			
			//로그인하러가기
			response.sendRedirect(request.getContextPath() + "/user/userLogin.user");
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("회원가입 중 서버 error ");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
		
	}

	@Override
	public void checkLogin(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		String userPw = request.getParameter("user_pw");
		
//		UserDTO dto = new UserDTO();
//		dto.setUserId(userId);
		//dto.setUserPw(userPw);
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			UserMapper mapper = sql.getMapper(UserMapper.class);
			UserDTO userDTO = mapper.checkLogin(userId);
			
	        if (userDTO != null && BCrypt.verifyer().verify(userPw.toCharArray(), userDTO.getUserPw()).verified) {
				//session에 userNo저장
				HttpSession session = request.getSession();
				session.setAttribute("userNo", userDTO.getUserNo());
				
				response.sendRedirect(request.getContextPath() + "/post/postList.post");
			} else {
	            System.out.println("로그인 실패: 유효하지 않은 ID/PW"); // 디버깅용 로그
				response.sendRedirect(request.getContextPath() + "/user/userLogin.user");
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("로그인 중 서버 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
		}
	}

	@Override
	public UserDTO getUserInfo(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Long userNo = (Long)request.getSession().getAttribute("userNo");
		
		try(SqlSession sql = sqlSessionFactory.openSession()){
			UserMapper mapper = sql.getMapper(UserMapper.class);
			UserDTO user = mapper.getUserInfo(userNo);
			System.out.println("유저정보: " + user.toString());
			return user;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("로그인 정보 불러오는 중 서버 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			return null;
		}
	}

	@Override
	public int duplicatedId(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String userId = request.getParameter("userId");
		System.out.println("userId확인 " + userId);
		try(SqlSession sql = sqlSessionFactory.openSession()){
			UserMapper mapper = sql.getMapper(UserMapper.class);
			int checkCnt = mapper.duplicatedId(userId);
			System.out.println("id확인: " + checkCnt);
			
			return checkCnt;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ID중복확인 중 서버 error");
			response.sendRedirect(request.getContextPath() + "/errorPage.jsp");
			return -1;
		}
	}
	
	

}
