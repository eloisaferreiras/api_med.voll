package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { // DDO é uma classe que representa um objeto de negócio e contém propriedades e métodos para acessar e manipular os dados associados a esse objeto.
        repository.save(new Medico(dados));

    }

    @GetMapping // metodo de leitura, apenas carregando dados do banco de dados
    public List<DadosListagemMedico> listar() { // o List representa uma coleção ordenada de elementos. fornece métodos para adicionar, remover, acessar e manipular os elementos da lista.(CRUD).
        return repository.findAll().stream().map(DadosListagemMedico::new).toList(); // criação da listagem e converão dos medicos.
    }

}
