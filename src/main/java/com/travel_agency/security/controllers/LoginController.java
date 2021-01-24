package com.travel_agency.security.controllers;

import com.travel_agency.security.DTO.LoginDTO;
import com.travel_agency.security.DTO.UserDTO;
import com.travel_agency.security.configuaration.token.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

    JwtProvider jwtProvider;
    AuthenticationManager authenticationManager;

    @Autowired
    public LoginController(JwtProvider jwtProvider, AuthenticationManager authenticationManager) {
        this.jwtProvider = jwtProvider;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping(value = "/login")
    public String login(Model model) {
        model.addAttribute("user", new UserDTO());
        return "login";
    }

    @PostMapping(value = "/login")
    public String postLogin(@ModelAttribute("user") UserDTO userDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("user", userDTO);
        return "redirect:/";
    }

    @PostMapping(value = "/login", consumes = {MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public String getTokenForUser(@ModelAttribute("user") LoginDTO loginDTO, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDTO.getUserName(),
                        loginDTO.getPassword() )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtProvider.generateToken(authentication);
        redirectAttributes.addFlashAttribute("user", loginDTO);
        return "redirect:/";
    }


}
