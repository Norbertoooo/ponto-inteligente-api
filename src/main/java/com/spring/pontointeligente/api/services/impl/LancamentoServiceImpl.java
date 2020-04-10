package com.spring.pontointeligente.api.services.impl;

import com.spring.pontointeligente.api.entities.Lancamento;
import com.spring.pontointeligente.api.repositories.FuncionarioRepository;
import com.spring.pontointeligente.api.repositories.LancamentoRepository;
import com.spring.pontointeligente.api.services.LancamentoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LancamentoServiceImpl implements LancamentoService {

    private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

    private LancamentoRepository lancamentoRepository;

    public LancamentoServiceImpl(LancamentoRepository lancamentoRepository) {
        this.lancamentoRepository = lancamentoRepository;
    }

    @Override
    public Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest) {
        log.info("Buscando lancamento pelo id {} e range de {}", funcionarioId, pageRequest);
        return lancamentoRepository.findByFuncionarioId(funcionarioId, pageRequest);
    }

    @Override
    public Optional<Lancamento> buscarPorId(Long id) {
        log.info("Buscando lancamento por id: {}", id);
        return lancamentoRepository.findById(id);
    }

    @Override
    public Lancamento persistir(Lancamento lancamento) {
        log.info("Salvando no banco de dados, lancamento: {}", lancamento);
        return lancamentoRepository.save(lancamento);
    }

    @Override
    public void remover(Long id) {
        log.info("Deletando lancamento por id: {}", id);
        lancamentoRepository.deleteById(id);
    }
}
