package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.endereco.DadosEndereco;

public record DadosCadastroMedico(

        @NotBlank // verifica se o campo é nulo e vazio. ( utilizando o bean valitation)
        String nome,

        @NotBlank
        @Email // verificação para padrão de email)
        String email,

        @NotBlank
        String telefone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}") //expressao regular - vai ser atribuido 4 a 6 digitos
        String crm,

        @NotNull // Não é notblank pois o campo não é string. Notblank apenas para Strings!
        Especialidade especialidade,

        @NotNull
        @Valid
        DadosEndereco endereco) {


}