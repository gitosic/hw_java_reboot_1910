package ru.itvitality.sbrf.cu.spring.rest;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itvitality.sbrf.cu.spring.service.MyService;

@Controller
public class PagesController {

    private final MyService myService;

    public PagesController(MyService myService) {
        this.myService = myService;
    }

    @GetMapping("/")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/public")
    public String publicPage() {
        return "public";
    }

    @GetMapping("/user")
    @Secured( "ROLE_USER" )
    public String userPage() {
        myService.onlyUser();
        return "user";
    }

    @GetMapping("/admin")
    public String adminPage() {
        myService.onlyUser();

        return "admin";
    }

    @GetMapping("/authenticated")
    public String authenticatedPage() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder
                .getContext().getAuthentication().getPrincipal();
        System.out.println(userDetails.getUsername());
        return "authenticated";
    }

    @GetMapping("/success")
    public String successPage() {
        return "success";
    }
}
