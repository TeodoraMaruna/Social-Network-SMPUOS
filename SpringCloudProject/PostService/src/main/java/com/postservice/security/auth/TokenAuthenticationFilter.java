package com.postservice.security.auth;

import com.postservice.security.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Component
public class TokenAuthenticationFilter  extends OncePerRequestFilter {

    @Autowired
    private final TokenUtils tokenUtils;

    public TokenAuthenticationFilter(TokenUtils tokenUtils) {
        this.tokenUtils = tokenUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        final String authToken = tokenUtils.getToken(httpServletRequest);
        final String username = tokenUtils.getUsernameFromToken(authToken);


        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            if (tokenUtils.validateToken(authToken)) {
                String role = this.tokenUtils.getRoleFromToken(authToken);
                GrantedAuthority grantedAuthority = new GrantedAuthority() {
                    @Override
                    public String getAuthority() {
                        return role;
                    }
                };
                Collection<GrantedAuthority> collection = new ArrayList<>();
                collection.add(grantedAuthority);

                final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(null, null, collection);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}