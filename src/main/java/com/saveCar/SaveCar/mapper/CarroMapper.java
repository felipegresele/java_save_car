package com.saveCar.SaveCar.mapper;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import com.saveCar.SaveCar.entity.UsuarioEntity;
import com.saveCar.SaveCar.infra.exceptions.InvalidCarDataException;
import com.saveCar.SaveCar.repository.UsuarioRepository;
import org.apache.coyote.Response;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarroMapper {

    private final UsuarioRepository usuarioRepository;

    public CarroMapper(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public CarroEntity toCreate(CreateCarroDTO dto) {

        UsuarioEntity usuarioEncontrado = usuarioRepository.findById(dto.usuarioID())
                .orElseThrow(() -> new InvalidCarDataException("Não existe usuário com este ID"));

        return CarroEntity.builder()
                .marca(dto.marca())
                .modelo(dto.modelo())
                .ano(dto.ano())
                .novo(dto.novo())
                .usuario(usuarioEncontrado)
                .build();
    }

    public CarroEntity update(CarroEntity carroCadastrado, UpdateCarroDTO carroDTO) {

        UsuarioEntity usuarioEncontrado = usuarioRepository.findById(carroDTO.getUsuarioID())
                .orElseThrow(() -> new InvalidCarDataException("Não existe usuário com este ID"));

        carroCadastrado.setMarca(carroDTO.getMarca());
        carroCadastrado.setModelo(carroDTO.getModelo());
        carroCadastrado.setAno(carroDTO.getAno());
        carroCadastrado.setNovo(carroDTO.getNovo());
        carroCadastrado.setUsuario(usuarioEncontrado);

        return carroCadastrado;
    }

    //Metodo responsavel por retornar dados pelo DTO
    //Pode ser usado para retornar dados nas APIs
    public ResponseCarroDTO toDTO(CarroEntity carro) {
        //Passando atributos direto no construtor e ja retornando
        return new ResponseCarroDTO(
                carro.getId(),
                carro.getMarca(),
                carro.getModelo(),
                carro.getAno(),
                carro.isNovo(),
                carro.getUsuario().getId()
        );
    }

    public List<ResponseCarroDTO> toDTOs(List<CarroEntity> carros) {
        return carros.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
