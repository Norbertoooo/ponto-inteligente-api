package com.spring.pontointeligente.api.services;

import com.spring.pontointeligente.api.entities.Empresa;

import java.util.Optional;

public interface EmpresaService {
    /**
     * Retorna uma empresa dado um cnpj
     * @param cnpj
     * @return Optional<empresa></>
     */
    Optional<Empresa> buscarPorCnpj(String cnpj);

    /**
     * Cadastra uma nova empresa no banco de dados
     * @param empresa
     * @return Empresa
     */
    Empresa persistir(Empresa empresa);
}
