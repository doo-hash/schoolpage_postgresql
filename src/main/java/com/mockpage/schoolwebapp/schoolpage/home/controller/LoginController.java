package com.mockpage.schoolwebapp.schoolpage.home.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mockpage.schoolwebapp.schoolpage.home.model.Admin;
import com.mockpage.schoolwebapp.schoolpage.home.model.Admindto;
import com.mockpage.schoolwebapp.schoolpage.home.model.GuestUser;
import com.mockpage.schoolwebapp.schoolpage.home.model.Parent;
import com.mockpage.schoolwebapp.schoolpage.home.model.Parentdto;
import com.mockpage.schoolwebapp.schoolpage.home.model.SchoolUser;
import com.mockpage.schoolwebapp.schoolpage.home.model.Student;
import com.mockpage.schoolwebapp.schoolpage.home.model.Teacher;
import com.mockpage.schoolwebapp.schoolpage.home.model.Teacherdto;
import com.mockpage.schoolwebapp.schoolpage.home.model.Userdto;
import com.mockpage.schoolwebapp.schoolpage.home.service.IAdminService;
import com.mockpage.schoolwebapp.schoolpage.home.service.IGuestUserService;
import com.mockpage.schoolwebapp.schoolpage.home.service.IParentService;
import com.mockpage.schoolwebapp.schoolpage.home.service.ISchoolUserService;
import com.mockpage.schoolwebapp.schoolpage.home.service.IStudentService;
import com.mockpage.schoolwebapp.schoolpage.home.service.ITeacherService;

@Controller
@RequestMapping("/home")
public class LoginController{

	@Autowired
	private ISchoolUserService userservice;
	
	@Autowired
	private IAdminService adminservice;
	
	@Autowired
	private ITeacherService teacherservice;
	
	@Autowired
	private IParentService parentservice;
	
	@Autowired
	private IGuestUserService guestuserservice;

	@Autowired
	private IStudentService studentservice;
	
	@ModelAttribute("admin")
	public Admindto getadmindto() {
		Admindto admin = new Admindto();
		return admin;
	}
	
	@ModelAttribute("parent")
	public Parentdto getparentdto() {
		Parentdto parent = new Parentdto();
		return parent;
	}
	
	@ModelAttribute("teacher")
	public Teacherdto getteacherdto() {
		Teacherdto teacher = new Teacherdto();
		return teacher;
	}
	
	@ModelAttribute("guestuser")
	public Userdto getuserdto() {
		Userdto user = new Userdto();
		return user;
	}
	
	@GetMapping("/login")
	public String adminLogin() {
		return "login";
	}
	
	@PostMapping("/login/auth_user")
	public String validLogin(Model model,
			@Valid @RequestParam String username, 
			@Valid @RequestParam String password) {
		
		User user = (User) userservice.loadUserByUsername(username);
		Collection<GrantedAuthority> authorities = user.getAuthorities();
		
		for (GrantedAuthority grantedAuthority : authorities) {
			if(grantedAuthority.getAuthority().equals("ADMIN")) {
				return "redirect:/home/admin/portal";
			}
			else if(grantedAuthority.getAuthority().equals("PARENT")) {
				return "redirect:/home/parent/portal";
			}
			else if(grantedAuthority.getAuthority().equals("TEACHER")) {
				return "redirect:/home/teacher/portal";
			}
			else if(grantedAuthority.getAuthority().equals("USER")) {
				return "redirect:/home/user/portal";
			}
		}
		return "redirect:/home";
	}
	
	@GetMapping("/admin/portal")
	public String adminPortal(Authentication authentication,Model model) {
		String userid = authentication.getName();
		
		SchoolUser admin = userservice.findUserByUserId(userid); 
		SchoolUser adminbyemail = userservice.findUserByEmail(userid);
		
		List<Teacher> teachers = teacherservice.findAll();
		List<Student> students = studentservice.findAll();
		int count = 0;
		if(admin != null) {
			count = teacherservice.usercount();
			model.addAttribute("studentcount", studentservice.usercount());
			model.addAttribute("teachercount",count);
			model.addAttribute("teachers",teachers);
			model.addAttribute("students",students);
			model.addAttribute("admin",admin);
		}
		if(adminbyemail != null) {
			model.addAttribute("studentcount", studentservice.usercount());
			model.addAttribute("teachercount",count);
			model.addAttribute("teachers",teachers);
			model.addAttribute("students",students);
			model.addAttribute("admin",adminbyemail);
		}
		return "admin_portal";
	}
	
	@GetMapping("/parent/portal")
	public String parentPortal(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser parent = userservice.findUserByUserId(userid); 
		SchoolUser parentbyemail = userservice.findUserByEmail(userid);
		if(parent != null) {
			model.addAttribute("parent",parent);
		}
		if(parentbyemail != null) {
			model.addAttribute("parent",parentbyemail);
		}
		return "parent_portal";
	}

	@GetMapping("/teacher/portal")
	public String teacherPortal(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser teacher = userservice.findUserByUserId(userid); 
		SchoolUser teacherbyemail = userservice.findUserByEmail(userid);
		List<Student> students= studentservice.findAll();
		int count = studentservice.usercount();
		if(teacher != null) {
			model.addAttribute("students",students);
			model.addAttribute("studentcount",count);
			model.addAttribute("teacher",teacher);
		}
		if(teacherbyemail != null) {
			model.addAttribute("students",students);
			model.addAttribute("studentcount",count);
			model.addAttribute("teacher",teacherbyemail);
		}
		return "teacher_portal";
	}
	
	@GetMapping("/user/portal")
	public String userPortal(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		if(user != null) {
			model.addAttribute("user",user);
		}
		if(userbyemail != null) {
			model.addAttribute("user",userbyemail);
		}
		return "userportal";
	}
	
	@GetMapping("/{userrole}/notifications")
	public String usernotification(@PathVariable String userrole,Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		if(user != null) {
			model.addAttribute("user",user);
		}
		if(userbyemail != null) {
			model.addAttribute("user",userbyemail);
		}
		model.addAttribute("userrole",userrole);
		return "notifications";
	}
	
	@GetMapping("/{userrole}/settings")
	public String usersettings(@PathVariable String userrole,Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		if(user != null) {
			model.addAttribute("user",user);
		}
		if(userbyemail != null) {
			model.addAttribute("user",userbyemail);
		}
		model.addAttribute("userrole",userrole);
		return "settings";
	}
	
	@GetMapping("/admin/delete")
	public String adminDelete(Authentication authentication,HttpServletRequest req,HttpSession session) throws ServletException {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Admin admin = adminservice.findByAdminId(userid);
		Admin adminbyemail = adminservice.findByEmail(userid);
		
		if(user != null) {
			session.invalidate();
			req.logout();
			user.setDelete(true);
			user.setInactive(true);
//			userservice.deleteUser(userid);
			if(admin != null) {
				admin.setInactive(true);
				admin.setDelete(true);
				adminservice.save(admin);
//				adminservice.deleteAdmin(userid);
			}
		}
		if(userbyemail != null) {
			session.invalidate();
			req.logout();
			userbyemail.setDelete(true);
			userbyemail.setInactive(true);
//			userservice.deleteUser(userid);
			if(adminbyemail != null) {
				adminbyemail.setDelete(true);
				adminbyemail.setInactive(true);
				adminservice.save(adminbyemail);
//				adminservice.deleteAdmin(userid);
			}
		}
		return "redirect:/home/register?delete";
	}
	
	@GetMapping("/parent/delete")
	public String parentDelete(Authentication authentication,HttpServletRequest req,HttpSession session) throws ServletException {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Parent parent = parentservice.findByParentId(userid);
		Parent parentbyemail = parentservice.findByEmail(userid);
		
		if(user != null) {
			session.invalidate();
			req.logout();
			user.setDelete(true);
			user.setInactive(true);
//			userservice.deleteUser(userid);
			if(parent != null) {
				parent.setDelete(true);
				parent.setInactive(true);
				parentservice.save(parent);
//				parentservice.deleteParent(userid);
			}
		}
		if(userbyemail != null) {
			session.invalidate();
			req.logout();
			userbyemail.setDelete(true);
			userbyemail.setInactive(true);
//			userservice.deleteUser(userid);
			if(parentbyemail != null) {
				parentbyemail.setDelete(true);
				parentbyemail.setInactive(true);
				parentservice.save(parentbyemail);
//				parentservice.deleteParent(userid);
			}
		}
		return "redirect:/home/register?delete";
	}
	
	@GetMapping("/teacher/delete")
	public String teacherDelete(Authentication authentication,HttpServletRequest req,HttpSession session) throws ServletException {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Teacher teacher = teacherservice.findByTeacherId(userid);
		Teacher teacherbyemail = teacherservice.findByEmail(userid);
		
		if(user != null) {
			session.invalidate();
			req.logout();
			user.setDelete(true);
			user.setInactive(true);
//			userservice.deleteUser(userid);
			if(teacher != null) {
				teacher.setDelete(true);
				teacher.setInactive(true);
				teacherservice.save(teacher);
//				teacherservice.deleteTeacher(userid);
			}
		}
		if(userbyemail != null) {
			session.invalidate();
			req.logout();	
			userbyemail.setDelete(true);//soft delete
			userbyemail.setInactive(true);
//			userservice.deleteUser(userid);(hard delete)
			if(teacherbyemail != null) {
				teacherbyemail.setDelete(true);
				teacherbyemail.setInactive(true);
				teacherservice.save(teacherbyemail);
//				teacherservice.deleteTeacher(userid);
			}
		}
		return "redirect:/home/register?delete";
	}
	
	@GetMapping("/user/delete")
	public String userDelete(Authentication authentication,HttpServletRequest req,HttpSession session) throws ServletException {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		GuestUser guestuser = guestuserservice.findByUserId(userid);
		GuestUser guestuserbyemail = guestuserservice.findByEmail(userid);
		
		if(user != null) {
			session.invalidate();
			req.logout();
			user.setDelete(true);
			user.setInactive(true);
//			userservice.deleteUser(userid);
			if(guestuser != null) {
				guestuser.setDelete(true);
				guestuser.setInactive(true);
				guestuserservice.save(guestuser);
//				guestuserservice.deleteGuestUser(userid);
			}
		}
		if(userbyemail != null) {
			authentication.setAuthenticated(false);		
			session.invalidate();
			req.logout();
			userbyemail.setDelete(true);
			userbyemail.setInactive(true);
//			userservice.deleteUser(userid);
			if(guestuserbyemail != null) {
				guestuserbyemail.setDelete(true);
				guestuserbyemail.setInactive(true);
				guestuserservice.save(guestuserbyemail);
//				guestuserservice.deleteGuestUser(userid);
			}
		}
		return "redirect:/home/register?delete";
	}
	
	@GetMapping("/admin/edit")
	public String adminUpdate(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Admin admin = adminservice.findByAdminId(userid);
		Admin adminbyemail = adminservice.findByEmail(userid);
			
		if(user != null) {
			if(admin == null) {
				model.addAttribute("admin",new Admindto(user.getFirstname(),
						user.getLastname(),user.getPhonenumber(),
				user.getEmail(),user.getUserid(),
				user.getDesignation(),null,null));
			}else {
			model.addAttribute("admin",new Admindto(user.getFirstname(),
					user.getLastname(),user.getPhonenumber(),
					user.getEmail(),user.getUserid(),
					user.getDesignation(),admin.getEducation(),
					admin.getWork_experience()));
			}
		}
		if(userbyemail != null) {
			if(adminbyemail == null) {
				model.addAttribute("admin",new Admindto(userbyemail.getFirstname(),
						userbyemail.getLastname(),userbyemail.getPhonenumber(),
					userbyemail.getEmail(),
					userbyemail.getUserid(),
					userbyemail.getDesignation(),null,null));
			}
			else {
			model.addAttribute("admin",new Admindto(userbyemail.getFirstname(),
					userbyemail.getLastname(),
					userbyemail.getPhonenumber(),
					userbyemail.getEmail(),userbyemail.getUserid(),
					userbyemail.getDesignation(),adminbyemail.getEducation(),
					adminbyemail.getWork_experience()));
			}
		}
		return "adminedit";
	}
	
	@PostMapping("/admin/update")
	public String updateAdmin(
			@Valid @ModelAttribute("admin") Admindto adminupdate,
			BindingResult result,
			Model model) {
		
		String userid = adminupdate.getAdminId();
		SchoolUser user = userservice.findUserByUserId(userid); 
		
		String email = adminupdate.getEmail(); 
		String phone = adminupdate.getPhonenumber(); 
		boolean isemail = false;  
		boolean isphone = false;
		
		List<SchoolUser> allusers = userservice.findAllUsers(); 
		List<SchoolUser> remainingusers = new ArrayList<>();
		
		for (SchoolUser userobj : allusers) { 
			if(!user.toString().equals(userobj.toString())) {
				remainingusers.add(userobj);
			} 
		} 
		for(SchoolUser userobj : remainingusers) {
			if(email.equals(userobj.getEmail().toString())) { 
				isemail = true; 
			} 
			if(phone.equals(userobj.getPhonenumber().toString())) { 
				isphone = true; 
			} 
		} 
		if(!result.hasErrors()) { 
			if(remainingusers  != null) {
				if(isemail || isphone) { 
					if(isemail) {model.addAttribute("emailmsg","already exists!!");} 
					if(isphone) {model.addAttribute("phonemsg","already exists!!");} 
					if(isemail && isphone) {
			  model.addAttribute("emailmsg","already exists!!");
			  model.addAttribute("phoneemsg","already exists!!");}
					return "adminedit"; 
					}
			}
			Admin admin = new Admin(adminupdate.getFirstName(),
					adminupdate.getLastName(),
					adminupdate.getPhonenumber(),
					adminupdate.getEmail(),
					adminupdate.getAdminId(),
					user.getPassword(),user.getRoles().toString(),
					adminupdate.getDesignation(),adminupdate.getEducation(),
					adminupdate.getWork_experience());
			System.out.println(admin);
			adminservice.update(admin);
			return "redirect:/home/admin/edit?success";	
		}
		return "adminedit";
	}

	@GetMapping("/parent/edit")
	public String parentUpdate(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Parent parent = parentservice.findByParentId(userid);
		Parent parentbyemail = parentservice.findByEmail(userid);

		if(user != null) {
			if(parent == null) {
				model.addAttribute("parent",new Parentdto(user.getFirstname(),
						user.getLastname(),user.getPhonenumber(),
				user.getEmail(),user.getUserid(),null,null));
			}else {
				model.addAttribute("parent",new Parentdto(user.getFirstname(),
						user.getLastname(),user.getPhonenumber(),
				user.getEmail(),user.getUserid(),parent.getStudentName(),
				parent.getStudentid()));
			}
		}
		if(userbyemail != null) {
			if(parentbyemail == null) {
				model.addAttribute("parent",new Parentdto(userbyemail.getFirstname(),
						userbyemail.getLastname(),userbyemail.getPhonenumber(),
					userbyemail.getEmail(),
					userbyemail.getUserid(),
					null,
					null));
			}
			else {
				model.addAttribute("parent",new Parentdto(userbyemail.getFirstname(),
						userbyemail.getLastname(),userbyemail.getPhonenumber(),
					userbyemail.getEmail(),
					userbyemail.getUserid(),
					parentbyemail.getStudentName(),
					parentbyemail.getStudentid()));	
			}
		}
		return "parentedit";
	}
	
	@PostMapping("/parent/update")
	public String updateParent(
			@Valid @ModelAttribute("parent") Parentdto parentupdate,
			BindingResult result,
			Model model) {
		
		String userid = parentupdate.getParentId();
		SchoolUser user = userservice.findUserByUserId(userid); 
		Student student = studentservice.findByStudentId(parentupdate.getStudentid());
		String email = parentupdate.getEmail(); 
		String phone = parentupdate.getPhonenumber();
		
		boolean isemail = false;  
		boolean isphone = false;
		boolean isstudentid = studentservice.existsByStudentId(parentupdate.getStudentid());
		List<SchoolUser> allusers = userservice.findAllUsers(); 
		List<SchoolUser> remainingusers = new ArrayList<>();
	
		for (SchoolUser userobj : allusers) { 
			if(!user.toString().equals(userobj.toString())) {
				remainingusers.add(userobj);
			} 
		} 
		for(SchoolUser userobj : remainingusers) {
			if(email.equals(userobj.getEmail().toString())) { 
				isemail = true; 
			} 
			if(phone.equals(userobj.getPhonenumber().toString())) { 
				isphone = true; 
			}
		} 
		if(!result.hasErrors()) { 
			if(remainingusers  != null) {
				if(isemail || isphone || !isstudentid) { 
					if(isemail) {model.addAttribute("emailmsg","already exists!!");} 
					if(isphone) {model.addAttribute("phonemsg","already exists!!");}
					if(!isstudentid) {model.addAttribute("idmsg","This id does not exist!!");}
					if(isemail && isphone) {
			  model.addAttribute("emailmsg","already exists!!");
			  model.addAttribute("phoneemsg","already exists!!");}
					if(isemail && !isstudentid) {
						  model.addAttribute("emailmsg","already exists!!");
						  model.addAttribute("idmsg","This id does not exist!!");}
					if(!isstudentid && isphone) {
						  model.addAttribute("idmsg","This id does not exist!!");
						  model.addAttribute("phoneemsg","already exists!!");}
					if(isemail && isphone && !isstudentid) {
						  model.addAttribute("idmsg","This id does not exist!!");
						  model.addAttribute("emailmsg","already exists!!");
						  model.addAttribute("phoneemsg","already exists!!");}
					return "parentedit"; 
					}
			}
			Parent parent = new Parent(parentupdate.getFirstName(),
					parentupdate.getLastName(),
					parentupdate.getPhonenumber(),
					parentupdate.getEmail(),
					parentupdate.getParentId(),
					parentupdate.getStudentName(),
					parentupdate.getStudentid(),
					user.getPassword(),user.getRoles().toString());
			String name = parent.getStudentName();
			if(name.contains(" ")) {
				String[] f= name.split(" ");
				String fname = f[0];
				String lname = f[1];
				System.out.println(fname);
				System.out.println(lname);
				if(student != null) {
					student.setFirstName(fname);
					student.setLastName(lname);
					studentservice.update(student);
				}
			}
				student.setFirstName(name);
				studentservice.update(student);
			parentservice.update(parent);
			return "redirect:/home/parent/edit?success";	
		}
		return "parentedit";
	}
	
	@GetMapping("/teacher/edit")
	public String teacherUpdate(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		Teacher teacher = teacherservice.findByTeacherId(userid);
		Teacher teacherbyemail = teacherservice.findByEmail(userid);
			
		if(user != null) {
			if(teacher == null) {
				model.addAttribute("teacher",new Teacherdto(user.getFirstname(),
						user.getLastname(),user.getPhonenumber(),
				user.getEmail(),user.getUserid(),
				user.getDesignation(),null,null));
			}else {
			model.addAttribute("teacher",new Teacherdto(user.getFirstname(),
					user.getLastname(),user.getPhonenumber(),
					user.getEmail(),user.getUserid(),
					user.getDesignation(),teacher.getEducation(),
					teacher.getWork_experience()));
			}
		}
		if(userbyemail != null) {
			if(teacherbyemail == null) {
				model.addAttribute("teacher",new Teacherdto(userbyemail.getFirstname(),
						userbyemail.getLastname(),userbyemail.getPhonenumber(),
					userbyemail.getEmail(),
					userbyemail.getUserid(),
					userbyemail.getDesignation(),null,null));
			}
			else {
			model.addAttribute("teacher",new Teacherdto(userbyemail.getFirstname(),
					userbyemail.getLastname(),
					userbyemail.getPhonenumber(),
					userbyemail.getEmail(),userbyemail.getUserid(),
					userbyemail.getDesignation(),teacherbyemail.getEducation(),
					teacherbyemail.getWork_experience()));
			}
		}
		return "teacheredit";
	}
	
	@PostMapping("/teacher/update")
	public String updateteacher(
			@Valid @ModelAttribute("teacher") Teacherdto teacherupdate,
			BindingResult result,
			Model model) {
		
		String userid = teacherupdate.getTeacherId();
		SchoolUser user = userservice.findUserByUserId(userid); 
		
		String email = teacherupdate.getEmail(); 
		String phone = teacherupdate.getPhonenumber(); 
		boolean isemail = false;  
		boolean isphone = false;
		
		List<SchoolUser> allusers = userservice.findAllUsers(); 
		List<SchoolUser> remainingusers = new ArrayList<>();
		
		for (SchoolUser userobj : allusers) { 
			if(!user.toString().equals(userobj.toString())) {
				remainingusers.add(userobj);
			} 
		} 
		for(SchoolUser userobj : remainingusers) {
			if(email.equals(userobj.getEmail().toString())) { 
				isemail = true; 
			} 
			if(phone.equals(userobj.getPhonenumber().toString())) { 
				isphone = true; 
			} 
		} 
		if(!result.hasErrors()) { 
			if(remainingusers  != null) {
				if(isemail || isphone) { 
					if(isemail) {model.addAttribute("emailmsg","already exists!!");} 
					if(isphone) {model.addAttribute("phonemsg","already exists!!");} 
					if(isemail && isphone) {
			  model.addAttribute("emailmsg","already exists!!");
			  model.addAttribute("phoneemsg","already exists!!");}
					return "teacheredit"; 
					}
			}
			Teacher teacher = new Teacher(teacherupdate.getFirstName(),
					teacherupdate.getLastName(),
					teacherupdate.getPhonenumber(),
					teacherupdate.getEmail(),
					teacherupdate.getTeacherId(),
					user.getPassword(),user.getRoles().toString(),
					teacherupdate.getDesignation(),teacherupdate.getEducation(),
					teacherupdate.getWork_experience());
			teacherservice.update(teacher);
			return "redirect:/home/teacher/edit?success";	
		}
		return "teacheredit";
	}
	
	@GetMapping("/user/edit")
	public String userUpdate(Authentication authentication,Model model) {
		String userid = authentication.getName();
		SchoolUser user = userservice.findUserByUserId(userid); 
		SchoolUser userbyemail = userservice.findUserByEmail(userid);
		GuestUser guestuser = guestuserservice.findByUserId(userid);
		GuestUser guestuserbyemail = guestuserservice.findByEmail(userid);
			
		if(user != null) {
			if(guestuser == null) {
				model.addAttribute("guestuser",new Userdto(user.getFirstname(),
						user.getLastname(),user.getPhonenumber(),
				user.getEmail(),user.getUserid(),
				user.getDesignation(),null,null));
			}else {
			model.addAttribute("guestuser",new Userdto(user.getFirstname(),
					user.getLastname(),user.getPhonenumber(),
					user.getEmail(),user.getUserid(),
					user.getDesignation(),guestuser.getEducation(),
					guestuser.getDescription()));
			}
		}
		if(userbyemail != null) {
			if(guestuserbyemail == null) {
				model.addAttribute("guestuser",new Userdto(userbyemail.getFirstname(),
						userbyemail.getLastname(),userbyemail.getPhonenumber(),
					userbyemail.getEmail(),
					userbyemail.getUserid(),
					userbyemail.getDesignation(),null,null));
			}
			else {
			model.addAttribute("admin",new Userdto(userbyemail.getFirstname(),
					userbyemail.getLastname(),
					userbyemail.getPhonenumber(),
					userbyemail.getEmail(),userbyemail.getUserid(),
					userbyemail.getDesignation(),guestuserbyemail.getEducation(),
					guestuserbyemail.getDescription()));
			}
		}
		return "useredit";
	}
	
	@PostMapping("/user/update")
	public String updateUser(
			@Valid @ModelAttribute("guestuser") Userdto guestuserupdate,
			BindingResult result,
			Model model) {
		
		String userid = guestuserupdate.getUserId();
		SchoolUser user = userservice.findUserByUserId(userid); 
		
		String email = guestuserupdate.getEmail(); 
		String phone = guestuserupdate.getPhonenumber(); 
		boolean isemail = false;  
		boolean isphone = false;
		
		List<SchoolUser> allusers = userservice.findAllUsers(); 
		List<SchoolUser> remainingusers = new ArrayList<>();
		
		for (SchoolUser userobj : allusers) { 
			if(!user.toString().equals(userobj.toString())) {
				remainingusers.add(userobj);
			} 
		} 
		for(SchoolUser userobj : remainingusers) {
			if(email.equals(userobj.getEmail().toString())) { 
				isemail = true; 
			} 
			if(phone.equals(userobj.getPhonenumber().toString())) { 
				isphone = true; 
			} 
		} 
		if(!result.hasErrors()) { 
			if(remainingusers  != null) {
				System.out.println(isemail);
				System.out.println(isphone);
				if(isemail || isphone) { 
					if(isemail) {model.addAttribute("emailmsg","already exists!!");} 
					if(isphone) {model.addAttribute("phonemsg","already exists!!");} 
					if(isemail && isphone) {
			  model.addAttribute("emailmsg","already exists!!");
			  model.addAttribute("phoneemsg","already exists!!");}
					return "useredit"; 
					}
			}
			GuestUser guestuser = new GuestUser(guestuserupdate.getFirstName(),
					guestuserupdate.getLastName(),
					guestuserupdate.getPhonenumber(),
					guestuserupdate.getEmail(),
					guestuserupdate.getUserId(),
					user.getPassword(),user.getRoles().toString(),
					guestuserupdate.getProfession(),guestuserupdate.getEducation(),
					guestuserupdate.getDescription());
	
			guestuserservice.update(guestuser);
			return "redirect:/home/user/edit?success";	
		}
		return "useredit";
	}
	
}