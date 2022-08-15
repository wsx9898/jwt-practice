package tw.com.ispan.cma.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PathController {
	//這個是測thymeleaf的
//	@RequestMapping(path = {"/"})
//	public String handlerMethod1() {
//		return "/index";
//	}

//	//跟上面不能共存，這個是測JSP的
	@RequestMapping(path = {"/"})
	public String handlerMethod11() {
		return "th_index";
	}

	@RequestMapping("/registerFormHTML.view")
	public String handlerMethod6(){ return "th_registerForm"; }

	@RequestMapping(path = "loginKevin.view")
	public String handlerMethod21() {
		return "th_loginKevin";
	}

	@RequestMapping(path = "interview.view")
	public String handlerMethod30() {
		return "th_interview";
	}
//Thymeleaf每次都要去SpringMVCJavaConfig額外標註


}
