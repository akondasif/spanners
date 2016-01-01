package org.dontpanic.spanners.springmvc.controllers;

import javax.validation.Valid;

import org.dontpanic.spanners.springmvc.forms.SignupForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Controller for the signup page. This page creates new user accounts.
 * Created by stevie on 29/12/15.
 */
@Controller
public class SignupController {

	public static final String VIEW_SUCCESS = "redirect:/";
    protected static final String[] DEFAULT_ROLES = {"ROLE_VIEWER", "ROLE_EDITOR"};

    /**
     * UserDetailsManager provided by Spring Security allows CRUD operations on user accounts
     */
    @Autowired
    private UserDetailsManager userDetailsManager;
	
	@RequestMapping(value = "signup")
	public SignupForm displayPage() {
		return new SignupForm();
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors) {
		if (errors.hasErrors()) {
			return null;
		}

        // Create the account
		UserDetails userDetails = new User(signupForm.getName(), signupForm.getPassword(), grantedAuthorities(DEFAULT_ROLES));
        userDetailsManager.createUser(userDetails);

		return VIEW_SUCCESS;
	}


    private static Collection<? extends GrantedAuthority> grantedAuthorities(String[] roleNames) {
        Collection<GrantedAuthority> gas = new ArrayList<>();
        for (String roleName : roleNames) {
            GrantedAuthority ga = new SimpleGrantedAuthority(roleName);
            gas.add(ga);
        }
        return gas;
    }

}