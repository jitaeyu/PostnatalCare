package com.team.postnatalcare.Sanmo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;import org.apache.catalina.User;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.team.postnatalcare.Sanmo.PostnatalDTO;
import com.team.postnatalcare.Sanmo.SanmoMapper;
import com.team.postnatalcareMain.Mapper;
import com.team.postnatalcareMain.UserDTO;
import com.team.postnatalcare.Sanmo.DocDTO;
import com.team.postnatalcare.Sanmo.NurDTO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class SanmoController {
	
	private static final Logger logger = LoggerFactory.getLogger(SanmoController.class);
	
	
	@Autowired
	SqlSession SanmosqlSession;
	SanmoMapper dao;

	@RequestMapping(value = "/reservation")  // 산모개인정보 페이지로 가기
	public String reservation(HttpServletRequest request,Model mo) {
		String name = request.getParameter("name");

		dao = SanmosqlSession.getMapper(SanmoMapper.class);
		ArrayList<UserDTO> list = dao.test(name);
		ArrayList<DocDTO> dlist = dao.test1();
		ArrayList<NurDTO> nlist = dao.test2();
		mo.addAttribute("list", list);
		mo.addAttribute("dlist", dlist);
		mo.addAttribute("nlist", nlist);
		return "reservation"; 
	}
	
	@RequestMapping(value = "/reservationlist")  // 산모예약 페이지로 가기
	public String reservationlist(HttpServletRequest request) {
		String name = request.getParameter("name");
		String docname = request.getParameter("docname");
		String nurname = request.getParameter("nurname");
		String startday = request.getParameter("docname");
		String endday = request.getParameter("docname");
		String baby = request.getParameter("docname");
		String protect = request.getParameter("docname");
		dao = SanmosqlSession.getMapper(SanmoMapper.class);
		int emergencyphone = Integer.parseInt(request.getParameter("emergencyphone"));
		
		dao.insertsave(name,docname,nurname,startday,endday,baby,protect,emergencyphone);
		return "reservationlist";
	}
	
	@RequestMapping(value = "/Self-Checker")
	public String SelfChecker(HttpServletRequest request) {			
		return "Self-Checker";
	}
	
}
