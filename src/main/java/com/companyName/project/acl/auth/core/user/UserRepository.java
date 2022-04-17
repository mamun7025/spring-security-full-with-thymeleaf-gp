package com.companyName.project.acl.auth.core.user;

import com.companyName.project.acl.auth.core.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    User getUserByUsername(String username);
    User findByUsername(String username);

}
