package com.camunda.auditTrail.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

@Service
public class AuthenticatedUserProvider {
    public OidcUser getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof OidcUser) {
            return (OidcUser) authentication.getPrincipal();
        }
        return null;
    }
    public String getName(){
        OidcUser user = getCurrentUser();
        return user != null ? user.getName() : "anonymous";
    }

    public String getEmail() {
        OidcUser user = getCurrentUser();
        return user != null ? user.getEmail() : "anonymous";
    }
}
