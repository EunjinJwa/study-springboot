package study.spring.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    EventRepository eventRepository;

    @Test
    public void createEvent() throws Exception {
        EventDto event = EventDto.builder()
                .name("Spring")
                .description("REST API Development with String")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 06, 22, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 06, 25, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2022, 07, 1, 10, 0))
                .endEventDateTime(LocalDateTime.of(2022, 8, 30, 10, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2")
                .build();

        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().is(201));
//                .andExpect(jsonPath("id").value(Matchers.not(100)))
//                .andExpect(jsonPath("free").value(Matchers.not(true)));
//                .andExpect(header().exists(HttpHeaders.LOCATION));
    }

    @Test
    public void createEvent_badRequest() throws Exception {
        Event event = Event.builder()
                .id(100)
                .name("Spring")
                .description("REST API Development with String")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 06, 22, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 06, 25, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2022, 07, 1, 10, 0))
                .endEventDateTime(LocalDateTime.of(2022, 8, 30, 10, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .location("강남역 D2")
                .free(true)
                .eventStatus(EventStatus.PUBLISHED)
                .build();

        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createEvent_badRequest_EmptyValue() throws Exception {
        EventDto event = EventDto.builder()
                .name("Spring")
                .description("REST API Development with String")
                .beginEnrollmentDateTime(LocalDateTime.of(2022, 9, 22, 10, 0))
                .closeEnrollmentDateTime(LocalDateTime.of(2022, 6, 25, 10, 0))
                .beginEventDateTime(LocalDateTime.of(2022, 7, 1, 10, 0))
                .endEventDateTime(LocalDateTime.of(2022, 8, 30, 10, 0))
                .basePrice(100)
                .maxPrice(200)
                .limitOfEnrollment(100)
                .build();

        mockMvc.perform(post("/api/events/")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaTypes.HAL_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

}
