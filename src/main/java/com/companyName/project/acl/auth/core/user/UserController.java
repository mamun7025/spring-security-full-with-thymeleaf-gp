package com.companyName.project.acl.auth.core.user;

import com.companyName.project.utils.PaginatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    public Map<String, String> clientParams;

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @Secured("ROLE_ADMIN")
    @RequestMapping("/all")
    public String getAll(Model model) {

        List<User> list = service.getAll();
        model.addAttribute("users", list);
        return "acl/auth/user/index";

    }

    @Secured({ "ROLE_EDITOR", "ROLE_ADMIN" })
    @RequestMapping({"", "/index"})
    public String getAllPaginated(Model model, HttpServletRequest request, @RequestParam Map<String,String> clientParams) {

        SecurityContext securityContext = SecurityContextHolder.getContext();
        System.out.println(securityContext.getAuthentication().getName());
        System.out.println(securityContext.getAuthentication().getAuthorities());
        System.out.println(securityContext.getAuthentication().getPrincipal());
        System.out.println(securityContext.getAuthentication().getDetails());

        //Search Operation
        this.clientParams = clientParams;
        PaginatorService ps = new PaginatorService(request);
        Page<User> page = service.getAllPaginated(clientParams, ps.pageNum, ps.pageSize, ps.sortField, ps.sortDir);
        List< User > list = page.getContent();

        model.addAttribute("currentPage", ps.pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", ps.sortField);
        model.addAttribute("sortDir", ps.sortDir);
        model.addAttribute("reverseSortDir", ps.sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("objectList", list);

        return "acl/auth/user/index";
    }


    @RequestMapping(path = "/create")
    public String create(Model model)
    {
        model.addAttribute("user", new User());
        return "acl/auth/user/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@Valid User user, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
        if (result.hasErrors()) {
            return "acl/auth/user/create";
        }
        user = service.createOrUpdate(user);
        model.addAttribute("object", user);
        redirAttrs.addFlashAttribute("successMgs", "Successfully save transaction");

        // return "redirect:/user/index";
        return "redirect:/user/show/" + user.getId();
    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable long id)
    {
        User user = null;
        try {
            user = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute("errorMessage", "Entity not found");
        }
        model.addAttribute("object", user);
        return "acl/auth/user/show";
    }


    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable("id") Optional<Long> id) throws Exception
    {
        if (id.isPresent()) {
            User entity = service.getById(id.get());
            model.addAttribute("user", entity);
        } else {
            model.addAttribute("user", new User());
        }
        return "acl/auth/user/edit";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(User user, RedirectAttributes redirAttrs)
    {
        user = service.createOrUpdate(user);
        redirAttrs.addFlashAttribute("successMgs", "Successfully update transaction");
        // return "redirect:/user/index";
        return "redirect:/user/show/" + user.getId();
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirAttrs) throws Exception
    {
        service.deleteById(id);
        redirAttrs.addFlashAttribute("warningMgs", "Successfully delete transaction");
        return "redirect:/user/index";
    }



}
