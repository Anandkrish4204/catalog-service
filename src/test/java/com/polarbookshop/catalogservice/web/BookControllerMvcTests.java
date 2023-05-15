package com.polarbookshop.catalogservice.web;

import com.polarbookshop.catalogservice.config.PolarProperties;
import com.polarbookshop.catalogservice.domain.BookNotFoundException;
import com.polarbookshop.catalogservice.domain.BookService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@WebMvcTest(HomeController.class)
class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;
    @MockBean
    private PolarProperties polarProperties;

    @Test
    void testMvc() throws Exception {
        BDDMockito.given(bookService.viewBookDetails("123456"))
                .willThrow(BookNotFoundException.class);

        mockMvc.perform(MockMvcRequestBuilders.get("/books/123456"))
                .andExpect(MockMvcResultMatchers.status()
                        .isNotFound());
    }
}
