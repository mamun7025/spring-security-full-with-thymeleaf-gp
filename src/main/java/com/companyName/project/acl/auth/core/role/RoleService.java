package com.companyName.project.acl.auth.core.role;

import com.companyName.project.acl.auth.core.role.Role;
import com.companyName.project.acl.auth.core.role.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService {


    private final RoleRepository repository;

    @Autowired
    public RoleService(RoleRepository repository){
        this.repository = repository;
    }


    public List<Role> getAll() {

        List<Role> result = repository.findAll();
        if(result.size() > 0) {
            return result;
        } else {
            return new ArrayList<>();
        }

    }

    public Page< Role > getAllPaginated(int pageNum, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
        return repository.findAll(pageable);

    }


    public Role findById(Long id) {
        Optional<Role> entity = repository.findById(id);
        return entity.orElse(null);
    }

    public Role getById(Long id) {
        return this.findById(id);
    }


    public void setAttributeForCreateUpdate(){
    }

    public Role createOrUpdate(Role entity) {

        this.setAttributeForCreateUpdate();

        if(entity.getId() == null) {
            entity = repository.save(entity);

        } else {
            Optional<Role> entityOptional = repository.findById(entity.getId());
            if(entityOptional.isPresent()) {
                entity = repository.save(entity);
            }
        }
        return entity;

    }


    public String deleteById(Long id) {

        Optional<Role> entity = repository.findById(id);
        if(entity.isPresent()) {
            repository.deleteById(id);
        } else {
            return "fail";
        }
        return "success";

    }



}
