package com.companyName.project.acl.auth.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogRepository extends JpaRepository<UserLog, Long> {
}
