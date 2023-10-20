package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.consultaController.DadosAgendamentoConsulta;
import med.voll.api.controller.DadosDetalhamentoConsulta;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("consultas")
//@SecurityRequirement(name = "bearer-key")
public class ConsultaController {


    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados) {
        System.out.println(dados);
        // var dto = agenda.agendar(dados);
        //return ResponseEntity.ok(dto);
        return ResponseEntity.ok(new DadosDetalhamentoConsulta(null, null,null,null));

    }
}

//    @DeleteMapping
//    @Transactional
//    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
//        agenda.cancelar(dados);
//        return ResponseEntity.noContent().build();
//    }
//}