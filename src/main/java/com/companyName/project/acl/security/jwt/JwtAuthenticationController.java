package com.companyName.project.acl.security.jwt;


import com.companyName.project.acl.security.jwt.config.JwtTokenUtil;
import com.companyName.project.acl.security.jwt.dto.JwtRequest;
import com.companyName.project.acl.security.jwt.dto.JwtResponse;
import com.companyName.project.acl.springuser.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Objects;


@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    private final HashMap<String, String> responseError = new HashMap<>();


    @RequestMapping(value = "/api/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
            throws Exception {

        boolean valid = authenticate( authenticationRequest.getUsername(), authenticationRequest.getPassword() );

        if(valid){
            UserDetails userDetails = this.userDetailsService.loadUserByUsername( authenticationRequest.getUsername() );
            String token = this.jwtTokenUtil.generateToken(userDetails);
            return ResponseEntity.ok(new JwtResponse(token));
        } else {
            return ResponseEntity.ok(this.responseError);
        }


    }

    private boolean authenticate(String username, String password) throws Exception {

        Objects.requireNonNull(username);
        Objects.requireNonNull(password);

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username, password) ) ;
            return true;
        } catch (DisabledException e) {
            this.responseError.put("ERROR_TYPE", "USER_DISABLED");
            this.responseError.put("ERROR_MSG", "Enter user is not enable");
            return false;
        } catch (BadCredentialsException e) {
            this.responseError.put("ERROR_TYPE", "INVALID_CREDENTIALS");
            this.responseError.put("ERROR_MSG", "Please enter valid username and password");
            return false;
        } catch (Exception e){
            throw new Exception("UNKNOWN_EXCEPTION", e);
        }

    }



}
