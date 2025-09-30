package com.example.spring_boot_start.service.token;

import com.example.spring_boot_start.DTO.AccountDto;
import com.example.spring_boot_start.helper.JwtToken;
import com.example.spring_boot_start.service.AccountService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.time.Duration;
import java.util.Date;
import java.util.stream.Collectors;

@Component
public class TokenHandler {

    private String secretKey;
    private Duration time;
    private JwtBuilder jwtBuilder;
    private JwtParser jwtParser;

    @Autowired
    private AccountService accountService;

    public TokenHandler(JwtToken jwtToken) {
        secretKey = jwtToken.getSecret();
        time = jwtToken.getTime();
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        jwtBuilder = Jwts.builder().signWith(key);
        jwtParser = Jwts.parserBuilder().setSigningKey(key).build();
    }

    public String createToken(AccountDto accountDto){
        Date issueDate = new Date();
        Date expireDate = Date.from(issueDate.toInstant().plus(time));
        jwtBuilder.setSubject(accountDto.getUserName());
        jwtBuilder.setIssuedAt(issueDate);
        jwtBuilder.setExpiration(expireDate);
        jwtBuilder.claim("address",accountDto.getAddress());
        jwtBuilder.claim("roles",accountDto.getRoles()
                .stream().map(roleDto -> roleDto.getRoleName()).collect(Collectors.toList()));
        return jwtBuilder.compact();
    }

    public AccountDto validateToken(String token){
        if(jwtParser.isSigned(token)){
            Claims claims = jwtParser.parseClaimsJws(token).getBody();
            String username = claims.getSubject();
            Date issuedDate = claims.getIssuedAt();
            Date expirationDate = claims.getExpiration();
            AccountDto accountDto = accountService.getByUserName(username);
            Boolean isValidToken = issuedDate.before(expirationDate) && expirationDate.after(new Date());
            if(!isValidToken){
                return null;
            }
            return accountDto;
        }
        return null;
    }
}
