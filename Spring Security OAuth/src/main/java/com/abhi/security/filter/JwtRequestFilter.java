package com.abhi.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.abhi.security.service.MyUserDetails;
import com.abhi.security.service.MyUserDetailsService;
import com.abhi.security.util.JwtUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private MyUserDetailsService userService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//examine the incoming request for jwt token.
		String authrozationHeader  = request.getHeader("Authorization");
		String userName=null;
		String jwt=null;
		
		if(null!=authrozationHeader && authrozationHeader.startsWith("Bearer")) {
			jwt=authrozationHeader.substring(7);
			userName = JwtUtil.getUsernameFromToken(jwt);
		}
		
		if(null!=userName && SecurityContextHolder.getContext().getAuthentication() == null) {
			MyUserDetails userDetails =  userService.loadUserByUsername(userName);
			
			if(JwtUtil.validateToken(jwt, userDetails)){
				UsernamePasswordAuthenticationToken authToken = 
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				
				authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			
				SecurityContextHolder.getContext().setAuthentication(authToken);
			}
		
		}
		
		filterChain.doFilter(request, response);
	}

}
