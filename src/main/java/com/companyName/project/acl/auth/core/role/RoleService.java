package com.companyName.project.acl.auth.core.role;

import com.companyName.project.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public Page< Role > getAllPaginated(Map<String, String> clientParams, int pageNum, int pageSize, String sortField, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize, sort);
//        return repository.findAll(pageable);

        return repository.findAll((Specification<Role>) (root, cq, cb) -> {
            Predicate p = cb.conjunction();

            if(!clientParams.isEmpty()){

                if(clientParams.containsKey("authority")){
                    if (!Utils.isEmpty(clientParams.get("authority"))) {
                        p = cb.and(p, cb.like(root.get("authority"), "%" + clientParams.get("authority") + "%"));
                    }
                }
                if(clientParams.containsKey("description")){
                    if (!Utils.isEmpty(clientParams.get("description"))) {
                        p = cb.and(p, cb.like(root.get("description"), "%" + clientParams.get("description") + "%"));
                    }
                }

                return p;
            }
            return null;
        }, pageable);

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
