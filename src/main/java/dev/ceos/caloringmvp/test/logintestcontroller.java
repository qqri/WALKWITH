package dev.ceos.caloringmvp.test;

//import com.sun.org.apache.xml.internal.security.algorithms.SignatureAlgorithm;

import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class logintestcontroller {

    public static void main(String[] args) throws UnsupportedEncodingException {
        List authList = new ArrayList();
        authList.add("manager");
        authList.add("admin");
        authList.add("user");


        String jwt = Jwts.builder()
                .setIssuer("Stormpath")
                .setSubject("msilverman")
                .claim("scope", authList)
                .claim("name", "Micah Silverman")
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(2, ChronoUnit.HOURS)))
                .signWith(SignatureAlgorithm.HS256,
                        "secret".getBytes("UTF-8"))
                .compact();

        System.out.println(jwt);

        Claims claims =
                Jwts.parser().setSigningKey("secret".getBytes("UTF-8")).parseClaimsJws(jwt).getBody();

        System.out.println(claims);
        System.out.println(claims.get("scope"));

    }
}