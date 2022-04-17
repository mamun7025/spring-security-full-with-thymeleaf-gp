package com.companyName.project.modules.system.setup;

import com.companyName.project.acl.auth.core.role.Role;
import com.companyName.project.acl.auth.core.role.RoleRepository;
import com.companyName.project.acl.auth.core.user.User;
import com.companyName.project.acl.auth.core.user.UserRepository;
import com.companyName.project.acl.auth.core.user.UserService;
import com.companyName.project.acl.auth.core.userrole.UserRole;
import com.companyName.project.acl.auth.core.userrole.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class AppDefaultUserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    UserService userService;


    // Create Roles -----------------------------------------------
    public void createAppBasicRoles(){

        Role chkRoleExist = this.roleRepository.getRoleByAuthority("ROLE_SUPER_ADMIN");
        if(chkRoleExist == null){
            Role roleInst1 = new Role("ROLE_SUPER_ADMIN", "Super Admin");
            this.roleRepository.save(roleInst1);
        }

        chkRoleExist = this.roleRepository.getRoleByAuthority("ROLE_ADMIN");
        if(chkRoleExist == null){
            Role roleInst2 = new Role( "ROLE_ADMIN", "Admin");
            this.roleRepository.save(roleInst2);
        }

        chkRoleExist = this.roleRepository.getRoleByAuthority("ROLE_USER");
        if(chkRoleExist == null){
            Role roleInst3 = new Role("ROLE_USER", "User");
            this.roleRepository.save(roleInst3);
        }

        chkRoleExist = this.roleRepository.getRoleByAuthority("ROLE_MANAGER");
        if(chkRoleExist == null){
            Role roleInst4 = new Role("ROLE_MANAGER", "Manager");
            this.roleRepository.save(roleInst4);
        }

        chkRoleExist = this.roleRepository.getRoleByAuthority("ROLE_WORKER");
        if(chkRoleExist == null){
            Role roleInst5 = new Role("ROLE_WORKER", "Worker");
            this.roleRepository.save(roleInst5);
        }

    }


    // Create User -----------------------------------------------
    public void createAppBasicUsers(){

        // Super Admin User
        User userSuperAdmin = this.userRepository.findByUsername("mamun");
        if(userSuperAdmin == null){
            userSuperAdmin = new User();
            userSuperAdmin.setUsername("mamun");
            userSuperAdmin.setPassword(new BCryptPasswordEncoder().encode("1234567"));
            userSuperAdmin.setDisplayName("Al-Mamun");
            userSuperAdmin.setPhoneNumber("01779282181");
            this.userRepository.save(userSuperAdmin);
        }

        Role roleSuperAdmin = this.roleRepository.getRoleByAuthority("ROLE_SUPER_ADMIN");
        if(roleSuperAdmin != null){
            UserRole userRoleInst = this.userRoleRepository.getByUserAndRole(userSuperAdmin, roleSuperAdmin);
            if(userRoleInst == null){
                userRoleInst = new UserRole(userSuperAdmin, roleSuperAdmin, new Date(), "SYSTEM");
                this.userRoleRepository.save(userRoleInst);
            }
        }


        // Admin User
        User userAdmin = this.userRepository.findByUsername("admin");
        if(userAdmin == null){
            userAdmin = new User();
            userAdmin.setUsername("admin");
            userAdmin.setPassword(new BCryptPasswordEncoder().encode("1234567"));
            userAdmin.setDisplayName("Admin");
            userAdmin.setPhoneNumber("01779282183");
            this.userRepository.save(userAdmin);
        }

        Role roleAdmin = this.roleRepository.getRoleByAuthority("ROLE_ADMIN");
        if(roleAdmin != null){
            UserRole userRoleInst = this.userRoleRepository.getByUserAndRole(userAdmin, roleAdmin);
            if(userRoleInst == null){
                userRoleInst = new UserRole(userAdmin, roleAdmin, new Date(), "SYSTEM");
                this.userRoleRepository.save(userRoleInst);
            }
        }


        // General User
        User userGeneral = this.userRepository.findByUsername("user");
        if(userGeneral == null){
            userGeneral = new User();
            userGeneral.setUsername("user");
            userGeneral.setPassword(new BCryptPasswordEncoder().encode("1234567"));
            userGeneral.setDisplayName("User");
            userGeneral.setPhoneNumber("01779282184");
            this.userRepository.save(userGeneral);
        }

        Role roleGeneral = this.roleRepository.getRoleByAuthority("ROLE_USER");
        if(roleGeneral != null){
            UserRole userRoleInst = this.userRoleRepository.getByUserAndRole(userGeneral, roleGeneral);
            if(userRoleInst == null){
                userRoleInst = new UserRole(userGeneral, roleGeneral, new Date(), "SYSTEM");
                this.userRoleRepository.save(userRoleInst);
            }
        }


    }


    public void createDefaultUserAndRoles(){

        this.createAppBasicRoles();
        this.createAppBasicUsers();

    }



}
