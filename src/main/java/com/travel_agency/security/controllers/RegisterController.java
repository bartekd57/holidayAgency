package com.travel_agency.security.controllers;

import com.travel_agency.repository.RoleRepository;
import com.travel_agency.repository.UserRepository;
import com.travel_agency.security.DTO.UserDTO;
import com.travel_agency.security.service.RoleService;
import com.travel_agency.security.service.UserService;
import com.travel_agency.utils.FileUploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Objects;

@Controller
public class RegisterController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@ModelAttribute UserDTO userDTO, Model model, @RequestParam("image") MultipartFile multipartFile) throws IOException {

        if (userService.ifUserExistsByUsername(userDTO.getUserName())) {
            model.addAttribute("message", "Username already exists");
            return "errorPage";
        }
        if (userService.ifUserExistsByUsername(userDTO.getEmail())) {
            model.addAttribute("message", "Email already added");
            return "errorPage";
        }

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

//        HashSet<Role> roles = new HashSet<>();
//
//        signupDTO.getRoles().forEach(role -> {
//
//            switch (role) {
//                case "admin":
//                    Role adminRole = roleService.findRoleByName(UserRoleNameEnum.ROLE_ADMIN);
//                    roles.add(adminRole);
//                    break;
//                case "user":
//                    Role userRole = roleService.findRoleByName(UserRoleNameEnum.ROLE_USER);
//                    roles.add(userRole);
//                    break;
//            }
//        });
//
//        userDTO.setRoles(roles);

        model.addAttribute("userDTO", userDTO);

        String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));

        userDTO.setPhotos(fileName);

        String uploadDir = "user-photos/" + userDTO.getId();
        FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

        userService.saveUser(userDTO);

        return "redirect:/users";

    }


    @GetMapping(value = "/register")
    public String register(Model model) {

        model.addAttribute("user", new UserDTO());
        return "register";
    }




//    @PostMapping(value = "/register")
//    public String postRegister(@ModelAttribute("user") @Validated UserDTO userDTO, BindingResult bindingResult) {
//
//        if (bindingResult.hasErrors()) {
//            return "register";
//        }
//
//        if (userRepository.existsByUserName(userDTO.getUserName())) {
//            return "register";
//        }
//        if (userRepository.existsByEmail(userDTO.getEmail())) {
//            return "register";
//        }
//
//        User user = new User(userDTO.getFirstName(), userDTO.getLastName(), userDTO.getUserName(), userDTO.getEmail(),
//                passwordEncoder.encode(userDTO.getPassword()), passwordEncoder.encode(userDTO.getConfirmPassword()));
////
////        Set<Role> roles = new HashSet<>();
////
////        userDTO.getRoles().forEach(role -> {
////            switch (role.getName().name()) {
////                case "ROLE_ADMIN" :
////                    Role adminRole = roleRepository.findByName(UserRoleNameEnum.ROLE_ADMIN)
////                            .orElseThrow(() -> new RuntimeException("Admin role not found"));
////                    roles.add(adminRole);
////                    break;
////                case "ROLE_USER" :
////                    Role userRole = roleRepository.findByName(UserRoleNameEnum.ROLE_USER)
////                            .orElseThrow(() -> new RuntimeException("User role not found"));
////                    roles.add(userRole);
////                    break;
////                default:
////                    Role defaultRole = roleRepository.findByName(UserRoleNameEnum.ROLE_ADMIN)
////                            .orElseThrow(() -> new RuntimeException("User role not found"));
////                    roles.add(defaultRole);
////                    break;
////
////            }
////        });
////
////        user.setRoles(roles);
//        userRepository.save(user);
//        return "index";
//    }
}
