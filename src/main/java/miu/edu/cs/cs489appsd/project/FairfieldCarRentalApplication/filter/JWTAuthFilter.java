package miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.Impl.FFUserDetailsService;
import miu.edu.cs.cs489appsd.project.FairfieldCarRentalApplication.service.util.JWTMgmtUtilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {


    private JWTMgmtUtilityService jwtMgmtUtilityService;

    private FFUserDetailsService ffUserDetailsService;

    public JWTAuthFilter(JWTMgmtUtilityService jwtMgmtUtilityService,FFUserDetailsService ffUserDetailsService){
        this.jwtMgmtUtilityService = jwtMgmtUtilityService;
        this.ffUserDetailsService = ffUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws BadCredentialsException,ServletException, IOException {
        String authorizationHeader = request.getHeader("Authorization");
        // Here is just an example jwt token -
        // Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhbmEuYWRtaW4iLCJleHAiOjE2NTE0MzUwODEsImlhdCI6MTY1MTM5OTA4MX0.aPH-bBsaRETUip91m3y3_UTR_EwFFbIpkyOdKSTgM70KT0a7v0uAYh4NtnFqvwEgCN7kuR8MDO5VB3rktBAwpA
        String jwtToken = null;
        String username = null;
        System.out.println(request.getRequestURI());
        if(authorizationHeader == null && !request.getRequestURI().startsWith("/ffweb/api/v1/public/auth") && !request.getRequestURI().startsWith("/public") && !request.getRequestURI().startsWith("/images") && !request.getRequestURI().startsWith("/css") && !request.getRequestURI().startsWith("/js")&& !request.getRequestURI().startsWith("/login")){
//            throw new BadCredentialsException("Unauthorized Attempt");
            response.setStatus(401);
            response.getWriter().print("Unauthorized Attempt");
            return;
        }

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            jwtToken = authorizationHeader.substring(7);
            username = jwtMgmtUtilityService.extractUsername(jwtToken);
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = ffUserDetailsService.loadUserByUsername(username);
            if (jwtMgmtUtilityService.validateToken(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }

        filterChain.doFilter(request, response);
    }
}