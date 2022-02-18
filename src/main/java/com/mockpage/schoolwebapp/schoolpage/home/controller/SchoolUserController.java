package com.mockpage.schoolwebapp.schoolpage.home.controller;
  
  import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import
  org.springframework.validation.BindingResult;
import
  org.springframework.web.bind.annotation.GetMapping;
import
  org.springframework.web.bind.annotation.ModelAttribute;
import
  org.springframework.web.bind.annotation.PostMapping;
import
  org.springframework.web.bind.annotation.RequestMapping;
import
  org.springframework.web.bind.annotation.RequestParam;

import
  com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.repository.RoleRepository;
import com.mockpage.schoolwebapp.schoolpage.home.service.IStudentService;
import
  com.mockpage.schoolwebapp.schoolpage.home.service.SchoolUserServiceImpl;
import com.mockpage.schoolwebapp.schoolpage.home.validator.CaptchaValidator;
  
  @Controller
  @RequestMapping("/home") 
  public class SchoolUserController {
  
	  protected Logger logger = LoggerFactory.getLogger(this.getClass());
  
	  @Autowired 
	  private SchoolUserServiceImpl userservice;
  
	  @Autowired
	  private RoleRepository roleRepo;
	  
	  @Autowired 
	  private CaptchaValidator captchavalidator;
  
	  @GetMapping("/register") 
	  public String registerForm(Model model) {
		  model.addAttribute("schooluser", new SchoolUser());
		  model.addAttribute("userroles",roleRepo.findAll());
		  return "userform"; 
	  }
  
	  @PostMapping("/register/save") 
	  public String userCheck(
			  @RequestParam("g-recaptcha-response") String captcha,
			  @Valid @ModelAttribute("schooluser") SchoolUser user, 
			  BindingResult result, 
			  Model model) {
		  model.addAttribute("userroles",roleRepo.findAll());
		  
		  boolean isuserid = userservice.existsByUserId(user.getUserid());
		  boolean isphone = userservice.existsByPhoneNumber(user.getPhonenumber());
		  boolean isemail = userservice.existsByEmail(user.getEmail());
		  boolean isfname = userservice.existsByFirstName(user.getFirstname());
		  if(!result.hasErrors()) {
			  if(captchavalidator.isValidate(captcha)) {  
				 
					  if(isuserid || isphone || isemail || isfname) { 
					  if(isuserid) { model.addAttribute("useridmsg","already exists!!");} 
					  if(isfname) { model.addAttribute("fnamemsg","already exists!!");}
					  if(isphone) { model.addAttribute("phonemsg","already exists!!");} 
					  if(isemail) { model.addAttribute("emailmsg","already exists!!");}  
					  if(isuserid && isphone) { model.addAttribute("useridmsg","already exists!!");
						  model.addAttribute("phonemsg","already exists!!");} 
					  if(isuserid && isemail) { model.addAttribute("useridmsg","already exists!!");
						  model.addAttribute("emailmsg","already exists!!");} 
					  if(isemail && isphone) { model.addAttribute("emailmsg","already exists!!");
						  model.addAttribute("phonemsg","already exists!!"); } 
					  if(isuserid && isfname) { model.addAttribute("useridmsg","already exists!!");
					  model.addAttribute("fnamemsg","already exists!!"); } 
					  if(isfname && isphone) { model.addAttribute("fnamemsg","already exists!!");
					  model.addAttribute("phonemsg","already exists!!"); } 
					  if(isemail && isfname) { model.addAttribute("emailmsg","already exists!!");
					  model.addAttribute("fnamemsg","already exists!!"); } 
					  if(isuserid && isemail && isphone) {  model.addAttribute("useridmsg","already exists!!");
						  model.addAttribute("emailmsg","already exists!!"); model.addAttribute("phonemsg","already exists!!"); } 
					  if(isuserid && isemail && isfname) {  model.addAttribute("useridmsg","already exists!!");
					  model.addAttribute("emailmsg","already exists!!"); model.addAttribute("fnamemsg","already exists!!"); }
					  if(isuserid && isfname && isphone) {  model.addAttribute("useridmsg","already exists!!");
					  model.addAttribute("fnamemsg","already exists!!"); model.addAttribute("phonemsg","already exists!!"); }
					  if(isfname && isemail && isphone) {  model.addAttribute("fnamemsg","already exists!!");
					  model.addAttribute("emailmsg","already exists!!"); model.addAttribute("phonemsg","already exists!!"); }
					  if(isuserid && isemail && isphone && isfname) {  model.addAttribute("useridmsg","already exists!!");
					  model.addAttribute("emailmsg","already exists!!"); model.addAttribute("phonemsg","already exists!!"); 
					  model.addAttribute("fnamemsg","already exists!!"); }
					  return "userform";
				  }
				  userservice.saveSchoolUser(user);
				  return "redirect:/home/register?success";
 			  }
			  model.addAttribute("captchaerr","Captcha not validated.");
		  }
			model.addAttribute("captchaerr","Captcha not validated.");
			return "userform";
	  } 
  }