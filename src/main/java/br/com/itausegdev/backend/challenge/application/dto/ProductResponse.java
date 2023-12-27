package br.com.itausegdev.backend.challenge.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class ProductResponse {

    private String id;
    private String name;
    private String category;
    private String baseValue;
    private String feeValue;

    @JsonProperty("id")
    public String getId() {
        return id;
    }
    @JsonProperty("nome")
    public String getName() {
        return name;
    }

    @JsonProperty("categoria")
    public String getCategory() {
        return category;
    }

    @JsonProperty("valor_base")
    public String getBaseValue() {
        return baseValue;
    }

    @JsonProperty("preco_tarifado")
    public String getFeeValue() {
        return feeValue;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBaseValue(String baseValue) {
        this.baseValue = baseValue;
    }

    public void setFeeValue(String feeValue) {
        this.feeValue = feeValue;
    }
}
