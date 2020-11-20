package com.decade.mall.ums.security.filter;

import com.decade.mall.ums.security.service.MyUserDetailsService;
import com.decade.mall.ums.security.util.JwtTokenUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class JwtAuthorizationTokenFilter extends OncePerRequestFilter {

    private final MyUserDetailsService myUserDetailsService;

    @Autowired
    public JwtAuthorizationTokenFilter(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = "Authorization";
        String header = request.getHeader(tokenHeader);
        if (header != null) {
            if (JwtTokenUtil.verify(header) && SecurityContextHolder.getContext().getAuthentication() == null) {
                Claims claims = JwtTokenUtil.getClaims(header);
                String username = claims.get("username", String.class);
                log.debug(username + " jwt校验通过");
                try {
                    UserDetails userDetails = myUserDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } catch (Exception ignored) {

                }
            }
        }
        filterChain.doFilter(request, response);
    }

}
