package com.companyName.project.acl.auth.userrole;

import com.companyName.project.acl.auth.role.Role;
import com.companyName.project.acl.auth.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    List<UserRole> getAllByUser(User user);
    UserRole getByUserAndRole(User user, Role role);

}
