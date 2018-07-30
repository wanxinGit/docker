package com.example.demo.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.VisitorRepository;
import com.example.demo.model.Visitor;

@RestController
public class VisitorController {
	
	@Autowired
	private VisitorRepository visitorDao;
	
	@RequestMapping("visit")
	public String visit(HttpServletRequest request) {
		//visitorDao
		
		String ip = request.getRemoteAddr();
		
		Visitor visitor = visitorDao.findByIp(ip);
		
		if(visitor!=null) {
			visitor.setTimes(visitor.getTimes()+1);
			visitor.setLastUpdateDate(new Date());
		} else {
			visitor = new Visitor();
			visitor.setIp(ip);
			visitor.setCreateDate(new Date());
			visitor.setLastUpdateDate(new Date());
			visitor.setTimes(1);
		}
		
		visitorDao.save(visitor);
		
		return "您的ip：" + visitor.getIp() + "；访问次数：" + visitor.getTimes();
	}
	
	
}
