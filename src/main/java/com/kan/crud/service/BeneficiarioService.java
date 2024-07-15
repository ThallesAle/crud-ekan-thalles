package com.kan.crud.service;

import com.kan.crud.dto.BeneficiarioDTO;
import com.kan.crud.dto.DocumentoDTO;
import com.kan.crud.entity.Beneficiario;
import com.kan.crud.entity.Documento;
import com.kan.crud.exceptions.BeneficiarioNaoEncontradoException;
import com.kan.crud.repository.BeneficiarioRepository;
import com.kan.crud.repository.DocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BeneficiarioService {

    @Autowired
    private BeneficiarioRepository beneficiarioRepository;

    @Autowired
    private DocumentoRepository documentoRepository;

    public Beneficiario salvarBeneficiario(BeneficiarioDTO beneficiarioDTO) {
        Beneficiario beneficiario = new Beneficiario();
        beneficiario.setNome(beneficiarioDTO.getNome());
        beneficiario.setTelefone(beneficiarioDTO.getTelefone());
        beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
        beneficiario.setDataInclusao(LocalDate.now());

        if (beneficiarioDTO.getDocumentos() != null) {
            for (DocumentoDTO documentoDTO : beneficiarioDTO.getDocumentos()) {
                Documento documento = new Documento();
                documento.setTipoDocumento(documentoDTO.getTipoDocumento().toUpperCase());
                documento.setDescricao(documentoDTO.getDescricao());
                documento.setDataInclusao(LocalDate.now());
                documento.setBeneficiario(beneficiario);
                beneficiario.getDocumentos().add(documento);
            }
        }

        return beneficiarioRepository.save(beneficiario);
    }

    public List<Beneficiario> listarBeneficiarios() {
        return beneficiarioRepository.findAll();
    }

    public List<DocumentoDTO> listarDocumentosPorBeneficiario(Long beneficiarioId) {
        List<DocumentoDTO> retornoDocumentos = new ArrayList<>();
        List<Documento> byBeneficiarioId = documentoRepository.findByBeneficiarioId(beneficiarioId);
        for (Documento doc: byBeneficiarioId) {
            DocumentoDTO documento = new DocumentoDTO();
            documento.setTipoDocumento(doc.getTipoDocumento());
            documento.setDescricao(doc.getDescricao());
            retornoDocumentos.add(documento);
        }
        return  retornoDocumentos;
    }

    public Beneficiario atualizarBeneficiario(Long beneficiarioId, BeneficiarioDTO beneficiarioDTO) {
        Optional<Beneficiario> beneficiarios = beneficiarioRepository.findById(beneficiarioId);
        if (beneficiarios.isPresent()) {
            Beneficiario beneficiario = beneficiarios.get();
            beneficiario.setNome(beneficiarioDTO.getNome());
            beneficiario.setTelefone(beneficiarioDTO.getTelefone());
            beneficiario.setDataNascimento(beneficiarioDTO.getDataNascimento());
            beneficiario.setDataAtualizacao(LocalDate.now());

            List<Documento> documentosExistentes = documentoRepository.findByBeneficiarioId(beneficiarioId);

            if (beneficiarioDTO.getDocumentos() != null) {
                for (DocumentoDTO documentoDTO : beneficiarioDTO.getDocumentos()) {

                    Optional<Documento> documentoOptional = documentosExistentes.stream()
                            .filter(doc -> doc.getTipoDocumento().equals(documentoDTO.getTipoDocumento()))
                            .findFirst();

                    if (documentoOptional.isPresent()) {
                        Documento documento = documentoOptional.get();
                        documento.setId(documentoOptional.get().getId());
                        documento.setDescricao(documentoDTO.getDescricao());
                        documento.setDataAtualizacao(LocalDate.now());
                    } else {
                        Documento novoDocumento = new Documento();
                        novoDocumento.setTipoDocumento(documentoDTO.getTipoDocumento());
                        novoDocumento.setDescricao(documentoDTO.getDescricao());
                        novoDocumento.setDataInclusao(LocalDate.now());
                        novoDocumento.setDataAtualizacao(LocalDate.now());
                        novoDocumento.setBeneficiario(beneficiario);
                        beneficiario.getDocumentos().add(novoDocumento);
                    }
                }
            }

            return beneficiarioRepository.save(beneficiario);
        } else {
            throw new BeneficiarioNaoEncontradoException("Beneficiário não encontrado");
        }
    }

    public void removerBeneficiario(Long beneficiarioId) {
        Optional<Beneficiario> beneficiarios = beneficiarioRepository.findById(beneficiarioId);
        if (beneficiarios.isPresent()) {
            Beneficiario beneficiario = beneficiarios.get();
            beneficiarioRepository.delete(beneficiario);
        } else {
            throw new BeneficiarioNaoEncontradoException("Beneficiário não encontrado com o ID: " + beneficiarioId);
        }
    }
}

