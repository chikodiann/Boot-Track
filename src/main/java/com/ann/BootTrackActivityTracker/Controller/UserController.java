package com.ann.BootTrackActivityTracker.Controller;

import com.ann.BootTrackActivityTracker.dto.LoginDTO;
import com.ann.BootTrackActivityTracker.dto.UserDTO;
import com.ann.BootTrackActivityTracker.implementation.UserServiceImpl;
import com.ann.BootTrackActivityTracker.entity.User;
import com.ann.BootTrackActivityTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("userDTO", new UserDTO());
        return mav;
    }

    @GetMapping("/signup")
    public ModelAndView getSignupForm() {
        ModelAndView mav = new ModelAndView("index");
        mav.addObject("userDTO", new UserDTO());
        return mav;
    }

    @PostMapping("/signup")
    public ModelAndView signup(@ModelAttribute("userDTO") UserDTO userDTO) {
        userService.registerUser(userDTO);
        return new ModelAndView("redirect:/login");
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute ("user") UserDTO userDTO){
        User existingUser = userService.getUserByUsername(userDTO.getUserName());
        if(existingUser == null){
            return "redirect:/registration";
        }
        userService.registerUser(userDTO);
        System.out.println("Ifenna");
        return "redirect:/login";
    }


    @GetMapping("/login")
    public ModelAndView getLoginForm() {
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("userDTO", new UserDTO());
        return mav;
    }
    @PostMapping("/login")
    public ModelAndView login(@ModelAttribute("userDTO") LoginDTO loginDTO) {
        ModelAndView model= new ModelAndView();
        UserDTO user = userService.loginUser(loginDTO);
       if( user == null){
          model.setViewName("redirect:/signup");
       }
        System.out.println(user.getUserName());
        model.setViewName("redirect:/dashboard");

       return model;
    }
//    @PostMapping("/login")
//    public String log2Dashborad(@ModelAttribute("userDTO") LoginDTO loginDTO, Model model){
//       UserDTO userDTO = userService.loginUser(loginDTO);
//       if(userDTO == null){
//           return "redirect:/signup";
//       }
//        System.out.println("Ifenna");
//       return "redirect:/activity_dashBoard";
//    }



    @GetMapping("/dashboard")
    public ModelAndView showDashboard() {
        List<User> users = userService.getAllUsers();
        ModelAndView mav = new ModelAndView("activity_dashBoard");
        mav.addObject("users", users);
        return mav;
    }

    @GetMapping("/users/{id}")
    public ModelAndView getUserDetail(@PathVariable Long id) {
        UserDTO userDTO = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("user-detail");
        mav.addObject("user", userDTO);
        return mav;
    }

    @GetMapping("/users/edit/{id}")
    public ModelAndView getEditUserForm(@PathVariable Long id) {
        UserDTO user = userService.getUserById(id);
        ModelAndView mav = new ModelAndView("edit-user");
        mav.addObject("userDTO", user);
        return mav;
    }



}

