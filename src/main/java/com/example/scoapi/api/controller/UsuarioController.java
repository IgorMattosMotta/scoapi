package com.example.scoapi.api.controller;

import com.example.scoapi.api.dto.CredenciaisDTO;
import com.example.scoapi.api.dto.TokenDTO;
import com.example.scoapi.exception.RegraNegocioException;
import com.example.scoapi.exception.SenhaInvalidaException;
import com.example.scoapi.model.entity.Usuario;
import com.example.scoapi.security.JwtService;
import com.example.scoapi.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/usuarios")
@RequiredArgsConstructor
@CrossOrigin
public class UsuarioController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<TokenDTO> autenticar(@RequestBody CredenciaisDTO credenciais) {
        try {
            Usuario usuario = Usuario.builder()
                    .login(credenciais.getLogin())
                    .senha(credenciais.getSenha())
                    .build();

            UserDetails userDetails = usuarioService.autenticar(usuario);

            String token = jwtService.gerarToken(usuario);

            return ResponseEntity.ok(new TokenDTO(userDetails.getUsername(), token));

        } catch (UsernameNotFoundException | SenhaInvalidaException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage());
        }
    }

    @PostMapping("/recuperar-senha")
    public ResponseEntity recuperarSenha(@RequestParam("email") String email) {
        try {
            usuarioService.recuperarSenha(email);
            return ResponseEntity.ok("Instruções de recuperação de senha enviadas para " + email);
        } catch (RegraNegocioException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
