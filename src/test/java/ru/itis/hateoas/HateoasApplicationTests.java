package ru.itis.hateoas;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.itis.hateoas.models.Desk;
import ru.itis.hateoas.models.Place;
import ru.itis.hateoas.services.ReserveService;


import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
class HateoasApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReserveService reserveService;

    @BeforeEach
    public void setUp() {
        when(reserveService.reserve(1L, 1L)).thenReturn(reservedDesk());
    }

    private Desk reservedDesk() {
        return Desk.builder()
                .place(Place.builder()
                        .name("Beer House")
                        .isFull(false)
                        .build())
                .isReserved(true)
                .build();
    }

    @Test
    public void reserveDeskTest() throws Exception {
        mockMvc.perform(put("/places/1/desks/1/reserve")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.isReserved").value(reservedDesk().getIsReserved()))
                .andDo(document("reserve_desk", responseFields(
                        fieldWithPath("isReserved").description("Забронирован?")
                )));
    }

}
