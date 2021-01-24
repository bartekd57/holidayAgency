package com.travel_agency.controller;

import com.travel_agency.security.DTO.UserDTO;
import com.travel_agency.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserController {

    int count;

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


//    @ModelAttribute("user") LoginDTO loginDTO,

//    @GetMapping("/")
//    public String index(Model model) {
////
////        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
////        String username = loggedInUser.getName();
////
////        model.addAttribute("dateTime", LocalDateTime.now());
////        model.addAttribute("visitCount", ++count);
////        model.addAttribute("userName", username);
//        return "index2";
//    }

    @GetMapping("/users")
    public String users(Model model) {
        List<UserDTO> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @PostMapping("/editUser")
    public String editUser(UserDTO userDTO) {
        userService.saveUser(userDTO);
        return "redirect:/users";
    }
    @GetMapping("/editUser")
    public String getEditedUser(Model model, @RequestParam Long id) {
        UserDTO userDTO = userService.getUserById(id);
        model.addAttribute("user", userDTO);
        return "editUser";
    }


}
