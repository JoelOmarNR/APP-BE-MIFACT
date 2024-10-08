package app_be_mifac.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;
import java.util.stream.Collectors;

public final class TokenUtils {
    // tiempo de vida útil del token en segundos
    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS = 2_592_000L;
    // clave secreta para cifrar el token
    private final static String ACCESS_TOKEN_SECRET = "4qhq8LrEBfYcaRHxhdb9zURb2rf8e7Ud";

    public static String createToken(UserDetailsImpl userDetails) {
        long expirationTime = ACCESS_TOKEN_VALIDITY_SECONDS * 1_000; // en milisegundos
        Date expirationDate = new Date(System.currentTimeMillis() + expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("userId", userDetails.getId());
        extra.put("name", userDetails.getName());
        extra.put("role", userDetails.getRole());
        extra.put("authorities", AuthorityUtils.authorityListToSet(userDetails.getAuthorities()));

        // ej.: dasdasdasdggdfgkjdflkjgeklgjd.flksdjfjksdflksdfjlksdfj.klfjsdfksdjfklsdjlkf
        return Jwts.builder()
                .setSubject(userDetails.getUsername()) // sujeto
                .setExpiration(expirationDate) // fecha de exp.
                .addClaims(extra) // agregar información extra
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    /**
     * Método para obtener una autenticación
     * (UsernamePasswordAuthenticationToken)
     * a partir de un access token (String) ej.: dasdasdasdggdfgkjdflkjgeklgjd.flksdjfjksdflksdfjlksdfj.klfjsdfksdjfklsdjlkf
     */
    public static UsernamePasswordAuthenticationToken getAuthentication(String token) {
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            Object authoritiesObject = claims.get("authorities");

            List<String> authorities;
            if (authoritiesObject instanceof List) {
                authorities = (List<String>) authoritiesObject;
            } else {
                authorities = Collections.emptyList();
            }
            List<GrantedAuthority> grantedAuthorityList =
                    authorities
                            .stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
            return new UsernamePasswordAuthenticationToken(username, null, grantedAuthorityList);
        } catch (JwtException e) {
            return null;
        }
    }

}
