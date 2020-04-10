package com.spring.pontointeligente.api.services.impl;

import com.spring.pontointeligente.api.entities.Funcionario;
import com.spring.pontointeligente.api.repositories.FuncionarioRepository;
import com.spring.pontointeligente.api.services.FuncionarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

    private FuncionarioRepository funcionarioRepository;

    public FuncionarioServiceImpl(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    @Override
    public Funcionario persistir(Funcionario funcionario) {
        log.info("Persistindo no banco de dados funcionario {}", funcionario);
        return funcionarioRepository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorCpf(String cpf) {
        log.info("Procurando funcionario pelo CPF {}", cpf);
        return Optional.ofNullable(funcionarioRepository.findByCpf(cpf));
    }

    @Override
    public Optional<Funcionario> buscarPorEmail(String email) {
        log.info("Procurando funcionario pelo email {}", email);
        return Optional.ofNullable(funcionarioRepository.findByEmail(email));
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        log.info("Procurando funcionario pelo id: {}", id);
        return funcionarioRepository.findById(id);
    }
}
