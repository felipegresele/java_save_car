package com.saveCar.SaveCar.modules.carro;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import com.saveCar.SaveCar.infra.exceptions.CarNotFoundException;
import com.saveCar.SaveCar.infra.exceptions.InvalidCarDataException;
import com.saveCar.SaveCar.mapper.CarroMapper;
import com.saveCar.SaveCar.repository.CarroRepository;
import com.saveCar.SaveCar.service.CarroService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CarroServiceTest {

    @InjectMocks
    private CarroService carroService;

    @Mock
    private CarroRepository carroRepository;

    @Mock
    private CarroMapper carroMapper;

    @Test
    //Descrição do teste
    @DisplayName("Verifica excessão lançada ao buscar carro não existente")
    public void deveLancarExcessaoQuandoCarroNaoEncontrado() {
        when(carroRepository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(CarNotFoundException.class, () -> carroService.buscarPorId(99L));
    }

    @Test
    @DisplayName("Verifica excessão lançada ao tentar salvar novo carro com campos incorretos")
    public void deveLancarExcessaoQuandoDadosInvalidos() {
        CarroEntity carro = new CarroEntity();
        carro.setId(10L);
        when(carroRepository.findById(10L)).thenReturn(Optional.of(carro));

        UpdateCarroDTO dto = new UpdateCarroDTO();
        dto.setModelo(null);

        assertThrows(InvalidCarDataException.class, () -> carroService.editarCarro(10L, dto));
    }

    @Test
    @DisplayName("Verifica excessão lançada ao tentar salvar novo carro com ano maior ou igual 1960")
    public void deveLancarExcecaoQuandoAnoMenorQue1960() {
        CreateCarroDTO dto = new CreateCarroDTO("Honda", "Civic", 1950, true ,1L);

        assertThrows(InvalidCarDataException.class, () -> carroService.salvarCarro(dto));
    }


}

