package com.spring.pontointeligente.api.controllers;

import com.spring.pontointeligente.api.dtos.EmpresaDto;
import com.spring.pontointeligente.api.entities.Empresa;
import com.spring.pontointeligente.api.response.Response;
import com.spring.pontointeligente.api.services.EmpresaService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/empresas")
@CrossOrigin(origins = "*")
public class EmpresaController {

    private static final Logger log = LoggerFactory.getLogger(EmpresaController.class);
    
    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping(value = "/cnpj/{cnpj}")
    public ResponseEntity<Response<EmpresaDto>> buscarPorCnpj(@PathVariable("cnpj") String cnpj) {
        log.info("Buscando Empresa por cnpj: {}", cnpj);
        Response<EmpresaDto> response = new Response<>();
        Optional<Empresa> empresa = empresaService.buscarPorCnpj(cnpj);
        if(! empresa.isPresent()) {
            log.info("Empresa não encontrada para o cnpj: {}", cnpj);
            response.getErrors().add("Empresa não encontrada para o cnpj: " + cnpj );
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.converterEmpresaDto(empresa.get()));
        return ResponseEntity.ok(response);
    }

    /**
     * Cria um dto de uma empresa
     * @param empresa
     * @return empresaDto
     */
    private EmpresaDto converterEmpresaDto(Empresa empresa) {
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(empresa.getId());
        empresaDto.setRazaoSocial(empresa.getRazaoSocial());
        empresaDto.setCnpj(empresa.getCnpj());
        return empresaDto;
    }

}
