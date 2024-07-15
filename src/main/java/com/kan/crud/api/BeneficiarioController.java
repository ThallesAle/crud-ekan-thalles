
package com.kan.crud.api;

import com.kan.crud.dto.BeneficiarioDTO;
import com.kan.crud.dto.DocumentoDTO;
import com.kan.crud.entity.Beneficiario;
import com.kan.crud.entity.Documento;
import com.kan.crud.exceptions.BeneficiarioNaoEncontradoException;
import com.kan.crud.service.BeneficiarioService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/beneficiario")
public class BeneficiarioController  {

    @Autowired
    private BeneficiarioService beneficiarioService;


    @PostMapping
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Beneficiário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Requisição inválida")
    })
    public ResponseEntity<Beneficiario> cadastrarBeneficiario(@RequestBody BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiarioSalvo = beneficiarioService.salvarBeneficiario(beneficiarioDTO);
        return new ResponseEntity<>(beneficiarioSalvo, HttpStatus.CREATED);
    }

    @GetMapping("/{beneficiarioId}/documentos")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Documentos listados com sucesso"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<List<DocumentoDTO>> listarDocumentosPorBeneficiario(@PathVariable Long beneficiarioId) {
        List<DocumentoDTO> documentos = beneficiarioService.listarDocumentosPorBeneficiario(beneficiarioId);
        return new ResponseEntity<>(documentos, HttpStatus.OK);
    }

    @PutMapping("/{beneficiarioId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Beneficiário atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<?> atualizarBeneficiario(@PathVariable Long beneficiarioId, @RequestBody BeneficiarioDTO beneficiarioDTO) {
        try {
            Beneficiario beneficiarioAtualizado = beneficiarioService.atualizarBeneficiario(beneficiarioId, beneficiarioDTO);
            return new ResponseEntity<>(beneficiarioAtualizado, HttpStatus.OK);
        } catch (BeneficiarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @DeleteMapping("/{beneficiarioId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Beneficiário removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Beneficiário não encontrado")
    })
    public ResponseEntity<?> removerBeneficiario(@PathVariable Long beneficiarioId) {
        try {
            beneficiarioService.removerBeneficiario(beneficiarioId);
            return ResponseEntity.noContent().build();
        } catch (BeneficiarioNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

}
