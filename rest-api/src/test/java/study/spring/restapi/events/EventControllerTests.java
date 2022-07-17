package study.spring.restapi.events;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.BinaryDecoder;
import org.hamcrest.Matchers;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

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

    @Test
    @DisplayName("30개의 이벤트를 10개씩 두번째 페이지 조회하기")
    public void queryEvents() throws Exception {
        // Given
        IntStream.range(0, 30).forEach(this::generateEvent);

        // When
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/events")
                        .param("page", "1")
                        .param("size", "10")
                        .param("sort", "name,DESC")
                )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @GetMapping
    public ResponseEntity queryEvents(Pageable pageable) {
        return ResponseEntity.ok(this.eventRepository.findAll(pageable));
    }

    private void generateEvent(int index) {
        Event event = Event.builder()
                .name("event " + index)
                .description("test event")
                .build();
        this.eventRepository.save(event);
    }


}
