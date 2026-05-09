package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.dto.auth.LoginRequestDTO;
import com.saveCar.SaveCar.dto.auth.RegisterRequestDTO;
import com.saveCar.SaveCar.dto.auth.TokenResponseDTO;
import com.saveCar.SaveCar.entity.RolesEntity;
import com.saveCar.SaveCar.entity.UsuarioEntity;
import com.saveCar.SaveCar.enums.RoleType;
import com.saveCar.SaveCar.infra.exceptions.BadRequestException;
import com.saveCar.SaveCar.infra.security.TokenProvider;
import com.saveCar.SaveCar.mapper.UsuarioMapper;
import com.saveCar.SaveCar.repository.RoleRepository;
import com.saveCar.SaveCar.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenProvider tokenProvider;
    private final UsuarioMapper usuarioMapper;
    @Value("${jwt.expiration}")
    private long expirationTime;

    public AuthenticationService(UsuarioRepository usuarioRepository,
                                 RoleRepository roleRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 TokenProvider tokenProvider,
                                 UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.usuarioMapper = usuarioMapper;
    }

    public void register(RegisterRequestDTO dto) {
        UsuarioEntity usuarioEncontrado = usuarioRepository.findByEmail(dto.getEmail()).orElse(null);

        if (usuarioEncontrado != null) {
            throw new BadRequestException(("Usuário ja cadastrado com este email!"));
        }

        RolesEntity role = roleRepository.findByName(RoleType.OPERADOR.name())
                .orElseGet(() -> roleRepository.save(RolesEntity.builder()
                .name(RoleType.OPERADOR.name())
                .build()));

        UsuarioEntity usuario = usuarioMapper.toEntity(dto);
        usuario.setUserPassword(passwordEncoder.encode(dto.getPassword()));

        usuario.setRoles(Set.of(role));
        usuarioRepository.save(usuario);
    }

    public TokenResponseDTO login(LoginRequestDTO dto) {
        try {
            //É isso que precisa para fazer a autenticação
            Authentication auth = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            String token = tokenProvider.gerarToken(auth);

            return new TokenResponseDTO(token, expirationTime);
            //Fluxo do Spring por baixo dos panos
            //authentication provider -> userDetailsService -> passwordEnconder.matches() -> autenticado

            //caso não encontre o usuário (etapa 2) ou a senha criptografada != senha, devolve o catch
        } catch(BadCredentialsException e) {
            throw new BadRequestException(("Credencias inválidas"));
        } catch (Exception e) {
            throw e;
        }
    }

}
