package kr.ac.hansung.cse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LogController {
	// GET 메소드 처리 login 버튼이 눌렸을 때의 처리 display form
	// POST 메소드 처리 - 사용자가 입력한 정보와 DB 를 비교하여 검증 : spring 에서 자동으로 처리
	@RequestMapping("/login")
	public String login(@RequestParam(value="error", required=false)String error, 
			@RequestParam(value="logout", required=false)String logout, Model model){
		if(error != null){
			model.addAttribute("error", "invalid Username and Password");
		}
		
		if(logout != null){
			model.addAttribute("logout","You have been logged out successfully!");
		}
		return "login";
	}
	
	// Logout - spring 에게 logout 처리하는 기능(POST처리), logout된걸 알려주는 페이지(GET)
	@RequestMapping(value="/logout", method = RequestMethod.GET)
	public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
		// Spring 에 의해 처리되는 부분 - 누가 로그아웃을 하는가
	    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    if (auth != null){    
	        new SecurityContextLogoutHandler().logout(request, response, auth);
	    }
	    return "redirect:/login?logout";
	}
	
	
}
