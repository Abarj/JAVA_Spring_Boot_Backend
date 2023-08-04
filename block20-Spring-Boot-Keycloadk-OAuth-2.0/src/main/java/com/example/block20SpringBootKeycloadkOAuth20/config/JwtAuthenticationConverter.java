/*
Spring Security por defecto busca los roles así -> ROLE_user_client_rol -> El ROLE_ del principio lo añade Spring por debajo
Nosotros le estamos pasando unicamente user_client_rol (sin el ROLE_) por lo que para Spring Security estos roles serán desconocidos
Necesitamos crear una clase que sea un converter que se encargue de buscar los roles de Keycloak (vienen dentro del token)
y enviárselos a Spring Security con el ROLE_ + nombre para que los conozca
 */

package com.example.block20SpringBootKeycloadkOAuth20.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class JwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {

    private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    @Value("${jwt.auth.converter.main-attribute}")
    private String mainAttribute;
    @Value("${jwt.auth.converter.resource-id}")
    private String resourceId;



    // Método converter
    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        Collection<GrantedAuthority> authorities = Stream
                .concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
                .toList();

        return new JwtAuthenticationToken(jwt, authorities, getMainName(jwt));
    }

    // Extraer roles
    private Collection<? extends GrantedAuthority> extractResourceRoles(Jwt jwt) {

        // Fragmentamos y mapeamos el jwt
        Map<String, Object> resourceAccess;
        Map<String, Object> resource;
        Collection<String> resourceRoles;

        if(jwt.getClaim("resource_access") == null) {
            return List.of();
        }

        resourceAccess = jwt.getClaim("resource_access");

        if(resourceAccess.get(resourceId) == null) {
            return List.of();
        }

        resource = (Map<String, Object>) resourceAccess.get(resourceId);

        if(resource.get("roles") == null) {
            return List.of();
        }

        resourceRoles = (Collection<String>) resource.get("roles");

        return resourceRoles.stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_".concat(role)))
                .toList();
    }

    private String getMainName(Jwt jwt) {
        // Se retorna el SUB en caso de que el preferred_username sea null
        String claimName = JwtClaimNames.SUB; // SUB -> id que identifica al claim (está en el token)

        if(mainAttribute != null) {
            claimName = mainAttribute;
        }

        return jwt.getClaim(claimName);
    }
}
