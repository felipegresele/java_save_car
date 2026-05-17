package com.saveCar.SaveCar.controller;

import com.saveCar.SaveCar.dto.carro.CreateCarroDTO;
import com.saveCar.SaveCar.entity.CarroEntity;
import com.saveCar.SaveCar.dto.carro.UpdateCarroDTO;
import com.saveCar.SaveCar.dto.carro.ResponseCarroDTO;
import com.saveCar.SaveCar.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/carros")
@Tag(name = "Carros", description = "Recurso responsavel pelo gerenciamento de carros")
public class CarroController {

    private CarroService service;

    public CarroController(CarroService service) {
        this.service = service;
    }

    @Operation(
            summary = "Buscar todos carros",
            description = "Metódo responsavel por retornar a lista de todos carros salvos",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Busca feito com sucesso!")
    @GetMapping
    public ResponseEntity<Page<ResponseCarroDTO>> getAll(Pageable pageable) {
        //Qual a pagina = page
        //Qual o tamanho de cada pagina = size
        //Qual a ordem e pelo o que vai ordenar = order

        //Dessa forma o back controla
        //pageable = PageRequest.of(0,10, Sort.by("ano").descending());

        //Se não passar isso quem controla a ordenação é o front
        return ResponseEntity.ok(service.buscarTodos(pageable));
    }

    @Operation(
            summary = "Buscar carro por ID",
            description = "Metódo responsavel por buscar um carro pelo ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "200", description = "Busca feito com sucesso!")
    @GetMapping("/{id}")
    public ResponseEntity<ResponseCarroDTO> buscarPorId(@PathVariable Long id){
        ResponseCarroDTO carro = service.buscarPorId(id);
        return ResponseEntity.ok().body(carro);
    }

    @Operation(
            summary = "Adicionar carro",
            description = "Metódo responsavel por salvar carro",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Carro salvo com sucesso!")
    @PostMapping
    public ResponseEntity<ResponseCarroDTO> save(@RequestBody CreateCarroDTO dto) {
        ResponseCarroDTO carro = service.salvarCarro(dto);
            return ResponseEntity.status(201).body(carro);
    }

    @Operation(
            summary = "Atualizar carro",
            description = "Metódo responsavel por atualizar carro por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "201", description = "Carro atualizado com sucesso!")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseCarroDTO> update(@RequestBody UpdateCarroDTO dto,
                                              @PathVariable Long id) {
        ResponseCarroDTO carroAtualizado = service.editarCarro(id, dto);
        return ResponseEntity.status(200).body(carroAtualizado);
    }

    @Operation(
            summary = "Excluir carro",
            description = "Metódo responsavel por excluir carro por ID",
            security = @SecurityRequirement(name = "bearerAuth"))
    @ApiResponse(responseCode = "204", description = "Carro excluido com sucesso!")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        service.excluirCarro(id);
        return ResponseEntity.ok("Carro excluido com sucesso, ID: " + id);
    }

}
