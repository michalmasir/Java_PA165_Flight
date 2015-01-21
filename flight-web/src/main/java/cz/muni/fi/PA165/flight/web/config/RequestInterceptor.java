package cz.muni.fi.PA165.flight.web.config;

import cz.muni.fi.PA165.flight.enums.UserRole;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * User: PC
 * Date: 14. 12. 2014
 * Time: 13:36
 */
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods",
                "GET, POST, PUT, DELETE, OPTIONS");

        //request.getPathInfo return null as we're not inside controller
        String requestPath = request.getRequestURI().substring(request.getContextPath().length());
        if (requestPath.startsWith("/rest")) {
            //authenticate the rest user by default. MVC users will be intercepted by security config at their urls
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(UserRole.ADMIN.getRoleString()));
            org.springframework.security.core.userdetails.User auth_user = new org.springframework.security.core.userdetails.User("rest", "rest", true, true, true, true, authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(auth_user.getUsername(), auth_user.getPassword(), auth_user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);

        }

        return true;
    }
}