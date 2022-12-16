package com.shop.controller;

import com.shop.controller.main.Attributes;
import com.shop.model.User;
import com.shop.model.enums.Role;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reg")
public class RegCont extends Attributes {
    @GetMapping
    String reg() {
        return "reg";
    }

    @PostMapping
    String userAdd(Model model, @RequestParam String username, @RequestParam String fio, @RequestParam String password, @RequestParam String password_repeat) {
        if (!password.equals(password_repeat)) {
            model.addAttribute("message", "Некорректный ввод паролей!");
            return "reg";
        }

        if (userRepo.findAll().isEmpty()) {
            userRepo.save(new User(username, fio, password, Role.ADMIN));
            return "redirect:/login";
        }

        if (userRepo.findByUsername(username) != null) {
            model.addAttribute("message", "Пользователь с такой почтой уже существует!");
            return "reg";
        }

        userRepo.save(new User(username, fio, password, Role.BUYER));

        return "redirect:/login";
    }
}
