package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.DadosAgendamentoConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;

public class ValidadorMedicoAtivo {

    private MedicoRepository repository;

    public void validar(DadosAgendamentoConsulta dados){
      // escolha de medico opcional
        if (dados.idMedico() == null) {
            return; // para sair fora do metodo
        }
        
        var medicoEstaAtivo = repository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com medico excluido"); 
        }
    }
}
