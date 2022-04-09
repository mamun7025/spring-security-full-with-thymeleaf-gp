package com.companyName.project.acl.auth.zlog;

import com.companyName.project.acl.auth.user.User;
import com.companyName.project.utils.BeanUtil;
import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.logging.Logger;
import static javax.transaction.Transactional.TxType.MANDATORY;

public class UserListener {

    Logger log = Logger.getLogger(UserListener.class.getName());

    @PrePersist
    public void prePersist(User entity) {
        // Persistence logic
        log.info("UserLog ...INSERT");
        perform(entity, "INSERT");
    }

    @PreUpdate
    public void preUpdate(User entity) {
        // Updation logic
        log.info("UserLog ...UPDATE");
        perform(entity, "UPDATE");
    }

    @PreRemove
    public void preRemove(User entity) {
        // Removal logic
        log.info("UserLog ...DELETE");
        perform(entity, "DELETE");
    }

    @Transactional(MANDATORY)
    void perform(User entity, String action) {
//        EntityManager entityManager = BeanUtil.getBean(EntityManager.class);
//        entityManager.persist(new UserLog(entity, action));
        UserLogRepository logRepository = (UserLogRepository) BeanUtil.getBean("userLogRepository");
        logRepository.save(new UserLog(entity, action));
    }

}
