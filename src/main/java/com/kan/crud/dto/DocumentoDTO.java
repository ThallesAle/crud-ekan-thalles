package com.kan.crud.dto;


public class DocumentoDTO {

    private String tipoDocumento;
    private String descricao;

    public DocumentoDTO() {
    }

    public DocumentoDTO(String tipoDocumento, String descricao) {
        this.tipoDocumento = tipoDocumento;
        this.descricao = descricao;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

