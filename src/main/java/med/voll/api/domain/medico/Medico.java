package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;


@Table(name = "medicos")
@Entity(name = "Medico")
@Getter //gera automaticamente os métodos getters para todos os atributos de uma classe,
@NoArgsConstructor // é um construtor vazio que não recebe parâmetros
@AllArgsConstructor //gera automaticamente um construtor com todos os argumentos para uma classe, evitando a necessidade de escrever manualmente o construtor.
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;

    @Enumerated(value = EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;
   // private Endereco endereco;

    private boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.especialidade = dados.especialidade();

        System.out.println("Endereco " + dados.endereco());

        this.endereco = new Endereco(dados.endereco());
    }

    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
        this.nome =dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.enderenco() != null) {
            this.endereco.atualizarInformacoes(dados.enderenco());
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}
