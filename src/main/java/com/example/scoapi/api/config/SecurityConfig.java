package com.example.scoapi.api.config;

import com.example.scoapi.security.JwtAuthFilter;
import com.example.scoapi.security.JwtService;
import com.example.scoapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

// (Ainda não temos JWT)
// import com.example.scoapi.security.JwtAuthFilter;
// import com.example.scoapi.security.JwtService;
// import com.example.scoapi.service.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private JwtService jwtService;

    @Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/api/v1/adotantes/**").permitAll()
                .antMatchers("/api/v1/animais/**").permitAll()
                .antMatchers("/api/v1/ongs/**").permitAll()
                .antMatchers("/api/v1/perguntas/**").permitAll()
                .antMatchers("/api/v1/protocolos-vacina/**").permitAll()
                .antMatchers("/api/v1/questionarios/**").permitAll()
                .antMatchers("/api/v1/registros-vacina/**").permitAll()
                .antMatchers("/api/v1/respostas-questionario/**").permitAll()
                .antMatchers("/api/v1/solicitacoes-adocao/**").permitAll()
                .antMatchers("/api/v1/status-animal/**").permitAll()
                .antMatchers("/api/v1/status-solicitacao/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {

        web.ignoring().antMatchers(
                "/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/swagger-ui/**",
                "/webjars/**");
    }
}