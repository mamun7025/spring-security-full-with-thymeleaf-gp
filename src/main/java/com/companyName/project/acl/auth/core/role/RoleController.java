package com.companyName.project.acl.auth.core.role;

import com.companyName.project.utils.PaginatorService;
import com.companyName.project.utils.SysMsgStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@SuppressWarnings("OptionalUsedAsFieldOrParameterType")
@Controller
@RequestMapping("/role")
public class RoleController {


    private RoleService service;
    @Autowired
    public void setInjectedBean(RoleService service) {
        this.service = service;
    }


    @RequestMapping("/all")
    public String getAll(Model model) {

        List<Role> list = service.getAll();
        model.addAttribute("objectList", list);
        return "acl/auth/role/index";

    }

    @Secured({"ROLE_EDITOR", "ROLE_ADMIN"})
    @RequestMapping("/index")
    public String getAllPaginated(HttpServletRequest request, Model model) {

        PaginatorService ps = new PaginatorService(request);
        Page<Role> page = service.getAllPaginated(ps.pageNum, ps.pageSize, ps.sortField, ps.sortDir);
        List< Role > list = page.getContent();

        model.addAttribute("currentPage", ps.pageNum);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());

        model.addAttribute("sortField", ps.sortField);
        model.addAttribute("sortDir", ps.sortDir);
        model.addAttribute("reverseSortDir", ps.sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("objectList", list);

        return "acl/auth/role/index";

    }


    @RequestMapping(path = "/create")
    public String create(Model model) {

        model.addAttribute("object", new Role());
        return "acl/auth/role/create";

    }


    @RequestMapping(path = "/save", method = RequestMethod.POST)
    public String save(@Valid Role postObjInst, BindingResult result, Model model, RedirectAttributes redirAttrs)
    {
        if (result.hasErrors()) {
            return "acl/auth/role/create";
        }
        postObjInst = service.createOrUpdate(postObjInst);
        model.addAttribute("object", postObjInst);
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey1, SysMsgStr.msgDesc1);

        return "redirect:/role/show/" + postObjInst.getId();

    }

    @GetMapping(value = "/show/{id}")
    public String show(Model model, @PathVariable long id) {

        Role object = null;
        try {
            object = service.findById(id);
        } catch (Exception ex) {
            model.addAttribute(SysMsgStr.msgKey3, SysMsgStr.msgDesc3);
        }
        model.addAttribute("object", object);
        return "acl/auth/role/show";

    }


    @RequestMapping(path = {"/edit", "/edit/{id}"})
    public String edit(Model model, @PathVariable("id") Optional<Long> id ) {

        if (id.isPresent()) {
            Role entity = service.getById(id.get());
            model.addAttribute("object", entity);
        } else {
            model.addAttribute("object", new Role());
        }
        return "acl/auth/role/edit";

    }

    @RequestMapping(path = "/update", method = RequestMethod.POST)
    public String update(Role postObjInst, RedirectAttributes redirAttrs) {

        postObjInst = service.createOrUpdate(postObjInst);
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey1, SysMsgStr.msgDesc1u);
        return "redirect:/role/show/" + postObjInst.getId();

    }


    @RequestMapping(path = "/delete/{id}")
    public String deleteById(@PathVariable("id") Long id, RedirectAttributes redirAttrs) throws Exception {

        String status = this.service.deleteById(id);
        if(status.equals("fails")) throw new Exception("Fail to delete item");
        redirAttrs.addFlashAttribute(SysMsgStr.msgKey2, SysMsgStr.msgDesc2);
        return "redirect:/role/index";

    }



}
