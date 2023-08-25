package com.example.block26SpringBootAWSLambda.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityFilter implements Filter {

    // Filtro del token que se envía en la cabecera de la petición http
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest; // Para acceder a los parámetro de la petición
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse; // Para acceder a la respuesta de la petición

        String token = httpServletRequest.getHeader("token");

        if(token == null || token.isEmpty() || !token.equalsIgnoreCase("ABC123")) { // En producción sustituir por JWT
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode objectNode = objectMapper.createObjectNode();

            objectNode.put("Error", "El token es inválido");

            httpServletResponse.setStatus(401); // 401 -> No autorización
            httpServletResponse.setContentType("application/json");
            httpServletResponse.getOutputStream().write(objectMapper.writeValueAsBytes(objectNode));
            httpServletResponse.getOutputStream().flush();
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
