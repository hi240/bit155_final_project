package kr.or.bit3004.user;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Autowired
	private UserServiceImpl service;
	
	@Autowired
	private MailServiceImpl mailService;
	
	//로그인 폼
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signIn() {
		return "user/signIn";
	}
	
//	//이거 안먹음 
//	@RequestMapping("/login/oauth2/code/naver")
//	public String oauth2Callback(Model model, @RequestParam String state, HttpSession session) {
//		
//		
//		System.out.println("여기는 naver callback");
//		OAuth2AccessToken oauthToken;
//		oauthToken = customOAuth2Service.getAccessToken();
//		System.out.println(oauthToken.getScopes());
//		
//		return "user/signIn";
//	}
	
	
	// 가입 폼
	@RequestMapping(value="/signup", method=RequestMethod.GET)
	public String signUp() {
		return "user/signUp";
	}
	

	//가입 처리 
	@RequestMapping(value="/signup", method=RequestMethod.POST)
	public String signUp(User user) {
		System.out.println("controller");
		System.out.println(user);
		
		service.insertUser(user);
		return "redirect:signin";
	}
	
	
	
	// 비번 수정 요청 폼
	@RequestMapping(value="/forgotpwd", method=RequestMethod.GET)
	public String resetPassword() {
		return "user/forgotPwd";

	}
	
	// 비번 수정 처리
	// 이메일로 비밀번호 수정 페이지 링크를 메일로 보내기 
	//		>	링크를 클릭하면 비밀번호 수정 페이지 
	//		>	해당 페이지에서 새 비밀번호를 입력하면 DB에 반영 
	
	//회원 수정 폼
	@RequestMapping(value="/edituser", method=RequestMethod.GET)
	public String editUserInfo() {	
		return "user/editUser";
	}
	
	//회원 수정 처리
	@RequestMapping(value="/edituser", method=RequestMethod.POST)
	public String editUserInfo(User user, HttpServletRequest request, HttpSession session) {
		System.out.println("===controller===");
		System.out.println(user);
		
		service.updateUser(user, session);
		return "redirect:edituser";
	}
	
	//회원 삭제 : ROLE_MEMBER 테이블에서 먼저 지우면 TRIGGER로 USER 테이블 데이터가 지워진다
	@RequestMapping(value="/deleteuser", method=RequestMethod.GET)
	public String deleteUser(String id) {
		service.deleteUser(id);
		return "redirect:signin";
	}

	
}