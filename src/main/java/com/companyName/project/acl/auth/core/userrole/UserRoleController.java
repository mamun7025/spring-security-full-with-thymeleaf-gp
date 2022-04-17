package com.companyName.project.acl.auth.core.userrole;

import com.companyName.project.acl.auth.core.role.RoleService;
import com.companyName.project.acl.auth.core.user.UserService;
import com.companyName.project.utils.PaginatorService;
import com.companyName.project.utils.SysMsgStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/userrole")
public class UserRoleController {

    private final UserRoleService service;
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public UserRoleController(
            UserRoleService service,
            UserService userService,
            RoleService roleService) {
        this.service = service;
        this.userService = userService;
        this.roleService = roleService;
    }




    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @RequestMapping("/index")
    public String getAllPaginated(HttpServletRequest request, Model model) {

        PaginatorService ps = new PaginatorService(request);
        Page<UserRole> page = service.getAllPaginated(ps.pageNum, ps.pageSize, ps.sortField, ps.sortDir);
        List< UserRole > list = page.getContent();

        model.addAttribute("currentPage", ps.pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", ps.sortField);
        model.addAttribute("sortDir", ps.sortDir);
        model.addAttribute("reverseSortDir", ps.sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("objectList", list);

        return "acl/auth/userrole/index";
    }


    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable long id) {
        UserRole object = null;
        try {
            object = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute(SysMsgStr.msgKey3, SysMsgStr.msgDesc3);
        }
        model.addAttribute("object", object);
        return "acl/auth/userrole/show";
    }

    @RequestMapping(path = "/create")
    public String create(Model model) {
        model.addAttribute("allUser", this.userService.getAll());
        model.addAttribute("allRoles", this.roleService.getAll());
        model.addAttribute("object", new UserRole());
        return "acl/auth/userrole/create";
    }

    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@Valid UserRole postObjInst, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
//        if (result.hasErrors()) {
//            return "view/auth/userrole/create";
//        }
        System.out.println(postObjInst);
        postObjInst = service.createOrUpdate(postObjInst);
        model.addAttribute("object", postObjInst);
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey1, SysMsgStr.msgDesc1);

        return "redirect:/userrole/show/" + postObjInst.getId();
    }

    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable("id") Optional<Long> id) throws Exception
    {
        if (id.isPresent()) {
            model.addAttribute("allUser", this.userService.getAll());
            model.addAttribute("allRoles", this.roleService.getAll());
            UserRole entity = service.getById(id.get());
            model.addAttribute("object", entity);
        } else {
            model.addAttribute("object", new UserRole());
        }
        return "acl/auth/userrole/edit";
    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(UserRole postObjInst, RedirectAttributes redirAttrs)
    {
        postObjInst = service.createOrUpdate(postObjInst);
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey1, SysMsgStr.msgDesc1u);
        return "redirect:/userrole/show/" + postObjInst.getId();
    }

    @RequestMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirAttrs) throws Exception
    {
        service.deleteById(id);
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey2, SysMsgStr.msgDesc2);
        return "redirect:/userrole/index";
    }



}
