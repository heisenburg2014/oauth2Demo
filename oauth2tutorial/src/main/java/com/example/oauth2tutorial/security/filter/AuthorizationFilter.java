package com.example.oauth2tutorial.security.filter;

import com.example.oauth2tutorial.User;
import com.example.oauth2tutorial.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;
import java.util.*;

@Component
@Order(Ordered.LOWEST_PRECEDENCE)
public class AuthorizationFilter extends OncePerRequestFilter {

    private Set<String> allowedUsers = new HashSet<>(Arrays.asList("rrravirahul02@gmail.com"));

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        System.out.println("User principal - " + httpServletRequest.getUserPrincipal());
        Principal principal = httpServletRequest.getUserPrincipal();
        if(principal != null) {
            //Meaning this is a restricted endpoint, after oauth has happened
            OAuth2AuthenticationToken oAuth2AuthenticationToken = (OAuth2AuthenticationToken) httpServletRequest.getUserPrincipal();
            System.out.println("Token name - " + oAuth2AuthenticationToken.getPrincipal().getAttributes());
            Map<String, Object> attributes = oAuth2AuthenticationToken.getPrincipal().getAttributes();
            String email = (String) attributes.get("email");
            if (!allowedUsers.contains(email)) {
                httpServletResponse.sendError(403, "User Not found");
            }
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
