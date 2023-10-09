package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public Page<DadosListagemMedico> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByActiveTrue(paginacao).map(DadosListagemMedico::new);
        // o List representa uma coleção ordenada de elementos. fornece métodos para adicionar, remover, acessar e manipular os elementos da lista.(CRUD).
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

    }

    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

    }


}
