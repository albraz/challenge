package br.com.itausegdev.backend.challenge.application.controller;

import br.com.itausegdev.backend.challenge.application.dto.ProductRequest;
import br.com.itausegdev.backend.challenge.application.dto.ProductResponse;
import br.com.itausegdev.backend.challenge.domain.service.DomainProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    private static final String PRODUCT_ID = "1";
    private static final String BASE_VALUE =  "100.00";
    private static final String FEE_VALUE = "103.20";
    private static final String PRODUCT_NAME = "Seguro de Vida Individual";
    private static final String PRODUCT_CATEGORY = "VIDA";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DomainProductService domainProductService;

    @Test
    public void shouldReturnProductList() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setId(PRODUCT_ID);

        List<ProductResponse> productResponseList = new ArrayList<>();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(PRODUCT_ID);
        productResponse.setName(PRODUCT_NAME);
        productResponseList.add(productResponse);

        when(domainProductService.getProducts()).thenReturn(productResponseList);

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(PRODUCT_ID))
                .andExpect(jsonPath("$[0].nome").value(PRODUCT_NAME));
    }

    @Test
    public void shouldCreateProduct() throws Exception {
        ProductRequest request = new ProductRequest();
        request.setId(PRODUCT_ID);
        request.setName(PRODUCT_NAME);
        request.setCategory(PRODUCT_CATEGORY);
        request.setBaseValue(new BigDecimal(BASE_VALUE));

        ProductResponse response = new ProductResponse();
        response.setId(PRODUCT_ID);
        response.setName(PRODUCT_NAME);
        response.setCategory(PRODUCT_CATEGORY);
        response.setBaseValue(BASE_VALUE);
        response.setFeeValue(FEE_VALUE);

        when(domainProductService.createProduct(any())).thenReturn(response);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(PRODUCT_ID))
                .andExpect(jsonPath("$.nome").value(PRODUCT_NAME))
                .andExpect(jsonPath("$.categoria").value(PRODUCT_CATEGORY))
                .andExpect(jsonPath("$.valor_base").value(BASE_VALUE));
    }
}
