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

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private DomainProductService domainProductService;

    @Test
    public void shouldReturnProductList() throws Exception {
        ProductRequest productRequest = new ProductRequest();
        productRequest.setId("1");

        List<ProductResponse> productResponseList = new ArrayList<>();
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId("1");
        productResponse.setName("Seguro de Vida Individual");
        productResponseList.add(productResponse);

        when(domainProductService.getProducts()).thenReturn(productResponseList);

        mockMvc.perform(get("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value("1"))
                .andExpect(jsonPath("$[0].nome").value("Seguro de Vida Individual"));
    }
}
