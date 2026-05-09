package com.saveCar.SaveCar.service;

import com.saveCar.SaveCar.dto.usuario.ResponseUsuarioDTO;
import com.saveCar.SaveCar.entity.UsuarioEntity;
import com.saveCar.SaveCar.mapper.UsuarioMapper;
import com.saveCar.SaveCar.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioMapper usuarioMapper) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioMapper = usuarioMapper;
    }

    public List<ResponseUsuarioDTO> getAll() {
        List<UsuarioEntity> lista = usuarioRepository.findAll();
        return usuarioMapper.toDTOList(lista);
    }


}
