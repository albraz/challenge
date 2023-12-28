package br.com.itausegdev.backend.challenge.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;

public class ProductResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("nome")
    private String name;
    @JsonProperty("categoria")
    private String category;
    @JsonProperty("valor_base")
    private String baseValue;
    @JsonProperty("preco_tarifado")
    private String feeValue;

    public ProductResponse() {
    }

    public ProductResponse(String number, String s, String s1, double v, double v1) {
    }


    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getBaseValue() {
        return baseValue;
    }
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
