package com.example.desafioNeurotech.security;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JwtAuthFilter extends OncePerRequestFilter{
    
    
    private final UserDetailsService userDetailsService;

    public JwtAuthFilter(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
            throws ServletException, IOException {//método chamado automaticamente a cada requisição
        
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer")) {//verifica se o header está nulo ou não possui Bearer authentication
            filterChain.doFilter(request, response);
            return;//caso seja uma rota livre, ele encerra e permite o acesso, encerrando a execução neste ponto
        }

        String token = authHeader.substring(7);//extrai o token, removendo o "Bearer " do começo
        String username = JwtUtil.extractUsername(token);//extrai o username a partir do token

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {//se o usuário foi extraído com sucesso e não há autenticação ativa
            UserDetails userdetails = userDetailsService.loadUserByUsername(username);//busca usuário do banco
            if (JwtUtil.validateToken(token)) {//verifica token
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userdetails, null, userdetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(authToken);//se token for válido, registra autenticação
            }
            filterChain.doFilter(request, response);
        }
    }

}
