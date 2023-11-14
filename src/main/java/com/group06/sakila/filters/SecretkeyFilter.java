package com.group06.sakila.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class SecretkeyFilter extends OncePerRequestFilter {
    @Value("${jwt.secretKey}")
    private String secretKey;

    @Value("${jwt.expiration}")
    private Integer expiration;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain) throws IOException {
        try {
            String timestamp = extractTimestamp(request);
            String url = extractUrlApi(request);
            String providedToken = extractToken(request);
            String hashedToken = hashToken(url, timestamp);

            // Assuming you have currentTime and clientTime defined somewhere in your class
            Date currentTime = new Date();

            Date clientTime = parseClientTime(timestamp);

            if (currentTime.getTime() - clientTime.getTime() <= expiration
                    && providedToken.equals(hashedToken)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(
                                null,
                                null,
                                null
                        );
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
            filterChain.doFilter(request, response); //enable bypass

        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
        }
    }

    private String extractToken(HttpServletRequest request)
    {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
    private String extractTimestamp(HttpServletRequest request)
    {
        return request.getHeader("Timestamp");
    }

    private String extractUrlApi(HttpServletRequest request)
    {
        return request.getHeader("UrlApi");
    }
    private Date parseClientTime(String timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");

        try {
            return dateFormat.parse(timestamp);
        } catch (ParseException e) {
            throw new IllegalArgumentException("Invalid timestamp format", e);
        }
    }
    public String hashToken(String url,String timeStamp){
        // hash token's payload + secret key
        return DigestUtils.sha256Hex(url + timeStamp + secretKey);
    }
}