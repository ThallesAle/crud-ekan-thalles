package com.kan.crud.dto;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BeneficiarioDTO {

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private List<DocumentoDTO> documentos = new ArrayList<>();

    public BeneficiarioDTO() {
    }

    public BeneficiarioDTO(String nome, String telefone, LocalDate dataNascimento, List<DocumentoDTO> documentos) {
        this.nome = nome;
        this.telefone = telefone;
        this.dataNascimento = dataNascimento;
        this.documentos = documentos;
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public List<DocumentoDTO> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<DocumentoDTO> documentos) {
        this.documentos = documentos;
    }
}
