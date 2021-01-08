package ru.javalab.hateoasservice;

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
import ru.javalab.models.Troupe;
import ru.javalab.services.TroupeService;

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
public class TropeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TroupeService troupeService;

    @BeforeEach
    public void setUp() {
        when(troupeService.publish(1L)).thenReturn(publishedTroupe());
    }

    @Test
    public void troupePublishTest() throws Exception {
        mockMvc.perform(put("/troupes/1/publish")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(publishedTroupe().getName()))
                .andExpect(jsonPath("$.description").value(publishedTroupe().getDescription()))
                .andExpect(jsonPath("$.state").value(publishedTroupe().getState()))
                .andDo(document("publish_course", responseFields(
                        fieldWithPath("name").description("Troupe name"),
                        fieldWithPath("description").description("Review"),
                        fieldWithPath("state").description("Troupe state")
                )));
    }

    private Troupe publishedTroupe() {
        return Troupe.builder()
                .id(1L)
                .description("Best comedy troupe")
                .state("Published")
                .name("Bolshoy theater")
                .build();
    }
}
