package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoRepository repository;

    @PostMapping
    @Transactional // usada para garantir que, se ocorrer algum erro durante a execução do método, todas as alterações feitas no banco de dados durante a transação serão revertidas.
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados) { // DDO é uma classe que representa um objeto de negócio e contém propriedades e métodos para acessar e manipular os dados associados a esse objeto.
        repository.save(new Medico(dados));

    }

    @GetMapping // metodo de leitura, apenas carrega os dados do banco de dados
    public ResponseEntity<Page<DadosListagemMedico>> listar( @PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        // o List representa uma coleção ordenada de elementos. fornece métodos para adicionar, remover, acessar e manipular os elementos da lista.(CRUD).
        return  ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var medico = repository.getReferenceById(dados.id());
        medico.atualizarInformacoes(dados);

        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico))

    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();

    }


}
