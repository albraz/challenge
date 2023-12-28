package br.com.itausegdev.backend.challenge.domain.utils;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static br.com.itausegdev.backend.challenge.domain.utils.Message.PRODUCT_NOT_FOUND;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MessageTest {

    @Test
    void shouldReturnMessage() {
        assertEquals("Nenhum produto encontrado", PRODUCT_NOT_FOUND);
        assertEquals("Produto não parametrizado", Message.PRODUCT_NOT_PARAMETRIZED);
        assertEquals("Erro interno do servidor", Message.INTERNAL_SERVER_ERROR);
        assertEquals("Criando produto", Message.CREATE_PRODUCT);
        assertEquals("Atualizando produto", Message.UPDATE_PRODUCT);
        assertEquals("Recuperando produtos", Message.GET_PRODUCTS);
        assertEquals("Recuperando produto por id", Message.GET_PRODUCT_BY_ID);
    }
}
