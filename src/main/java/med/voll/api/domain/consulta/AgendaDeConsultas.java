package med.voll.api.domain.consulta;


import jakarta.validation.Valid;
import med.voll.api.domain.ValidacaoException;
import med.voll.api.domain.consulta.validacoes.DadosDetalhamentoConsulta;
import med.voll.api.domain.consulta.validacoes.ValidadorAgendamentoDeConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class AgendaDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private List<ValidadorAgendamentoDeConsulta> validadores;


    public DadosDetalhamentoConsulta agendar(DadosAgendamentoConsulta dados) {
        if (!pacienteRepository.existsById(dados.idPaciente())) {
            ; //metodo verificador de conteudo de banco de dados(boolean)
        throw new ValidacaoException("ID do paciente informado não existe!"); {

        if (dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico())) {
            throw new ValidacaoException("ID do medico informado não existe!");
        }

        validadores.forEach(v -> v.validar(dados)); // design pattern > 'strategy'. Utilizando do SOLID. 'S' - (Principio da responsabilidade unica) 'O' (Principio aberto-fechado) 'D' (principio da inversão de dependencia)


        var paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        var medico = escolherMedico(dados);
        var consulta = new Consulta(null, medico, paciente, dados.data());
        consultaRepository.save(consulta);

        return  new DadosDetalhamentoConsulta(consulta);
    }
}


    private Medico escolherMedico(DadosAgendamentoConsulta dados) {
        if (dados.idMedico() != null) {
            return medicoRepository.getReferenceById(dados.idMedico());
        }

        if(dados.especialidade() == null) {
            throw new ValidacaoException("Especialidade é obrigatoria quando médico não for escolhido");
        }

        return medicoRepository.escolherMedicoAleatorioLivreNaData(dados.especialidade(), dados.data());

    }
  }
