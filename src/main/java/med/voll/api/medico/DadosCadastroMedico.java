package med.voll.api.medico;

import med.voll.api.endereco.DadosEnderenco;

public record DadosCadastroMedico(String nome, String email, String crm, Especialidade especialidade, DadosEnderenco enderenco) {
}
