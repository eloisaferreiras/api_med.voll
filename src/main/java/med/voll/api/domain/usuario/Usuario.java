package med.voll.api.domain.usuario;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuario")
@Entity(name = "Usuario")
@Getter //gera automaticamente os métodos getters para todos os atributos de uma classe,
@NoArgsConstructor // é um construtor vazio que não recebe parâmetros
@AllArgsConstructor
//gera automaticamente um construtor com todos os argumentos para uma classe, evitando a necessidade de escrever manualmente o construtor.
@EqualsAndHashCode(of = "id")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

}
