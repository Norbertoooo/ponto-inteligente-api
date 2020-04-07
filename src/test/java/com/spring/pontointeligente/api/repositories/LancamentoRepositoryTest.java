package com.spring.pontointeligente.api.repositories;

import com.spring.pontointeligente.api.entities.Empresa;
import com.spring.pontointeligente.api.entities.Funcionario;
import com.spring.pontointeligente.api.entities.Lancamento;
import com.spring.pontointeligente.api.enums.PerfilEnum;
import com.spring.pontointeligente.api.enums.TipoEnum;
import com.spring.pontointeligente.api.utils.PasswordUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@SpringBootTest
public class LancamentoRepositoryTest {

    @Autowired
    private LancamentoRepository lancamentoRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private EmpresaRepository empresaRepository;

    private Long funcionarioId;
    private static final String EMAIL = "email@gmail.com";
    private static final String CPF = "01015002112";
    private static final String CNPJ = "908070605010";

    @BeforeEach
    public void setUp() throws Exception {
        Empresa empresa = this.empresaRepository.save(obterDadosEmpresa());

        Funcionario funcionario = this.funcionarioRepository.save(obterDadosFuncionario(empresa));
        this.funcionarioId = funcionario.getId();

        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
        this.lancamentoRepository.save(obterDadosLancamentos(funcionario));
    }
    @AfterEach
    public void tearDown() throws Exception {
        this.empresaRepository.deleteAll();
    }
    @Test
    public void testBuscarLancamentosPorFuncionarioId() {
        List<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId);

        assertEquals(2, lancamentos.size());
    }

    @Test
    public void testBuscarLancamentosPorFuncionarioIdPaginado() {
        Page<Lancamento> lancamentos = this.lancamentoRepository.findByFuncionarioId(funcionarioId, PageRequest.of(0,10));
        assertEquals(2, lancamentos.getTotalElements());
    }

    private Lancamento obterDadosLancamentos(Funcionario funcionario) {
        Lancamento lancameto = new Lancamento();
        lancameto.setData(new Date());
        lancameto.setTipo(TipoEnum.INICIO_ALMOCO);
        lancameto.setFuncionario(funcionario);
        return lancameto;
    }

    private Funcionario obterDadosFuncionario(Empresa empresa) throws NoSuchAlgorithmException {
        Funcionario funcionario = new Funcionario();
        funcionario.setNome("fulaninho");
        funcionario.setPerfil(PerfilEnum.ROLE_USUARIO);
        funcionario.setSenha(PasswordUtils.gerarBCrypt("123456"));
        funcionario.setCpf(CPF);
        funcionario.setEmail(EMAIL);
        funcionario.setEmpresa(empresa);
        return funcionario;
    }

    private Empresa obterDadosEmpresa() {
        Empresa empresa = new Empresa();
        empresa.setRazaoSocial("Empresa de exemplo");
        empresa.setCnpj(CNPJ);
        return empresa;
    }
}
