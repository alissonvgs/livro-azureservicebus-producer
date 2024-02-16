package com.desafio.softdesign.controller;

import com.desafio.softdesign.domain.user.AuthenticationDto;
import com.desafio.softdesign.domain.user.LoginResponseDto;
import com.desafio.softdesign.domain.user.RegisterDto;
import com.desafio.softdesign.domain.user.Usuario;
import com.desafio.softdesign.repository.UserRepository;
import com.desafio.softdesign.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping(value = "auth")
public class AuthenticationController {

    private AuthenticationManager authenticationManager;

    private UserRepository userRepository;

    private TokenService tokenService;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserRepository userRepository, TokenService tokenService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    private ResponseEntity login(@RequestBody AuthenticationDto authenticationDto) {
        var userNamePassword = new UsernamePasswordAuthenticationToken(authenticationDto.login(), authenticationDto.password());
        var auth = this.authenticationManager.authenticate(userNamePassword);
        var token = tokenService.generateToken((Usuario) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    private ResponseEntity register(@RequestBody RegisterDto registerDto) {
        if (this.userRepository.findByLogin(registerDto.login()) != null) {
            return ResponseEntity.badRequest().build();
        } else {
            String passwordCriptografada = new BCryptPasswordEncoder().encode(registerDto.password());
            Usuario novoUsuario = new Usuario(registerDto.login(), passwordCriptografada, registerDto.role());
            this.userRepository.save(novoUsuario);
        }
        return ResponseEntity.ok().build();
    }
}
