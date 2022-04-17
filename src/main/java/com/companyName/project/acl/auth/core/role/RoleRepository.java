package com.companyName.project.acl.auth.core.role;

import com.companyName.project.acl.auth.core.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role getRoleByAuthority(String authority);

}
