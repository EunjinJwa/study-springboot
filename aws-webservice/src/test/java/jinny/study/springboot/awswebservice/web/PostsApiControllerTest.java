package jinny.study.springboot.awswebservice.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jinny.study.springboot.awswebservice.domain.posts.Posts;
import jinny.study.springboot.awswebservice.domain.posts.PostsRepository;
import jinny.study.springboot.awswebservice.web.dto.PostsSaveRequestDto;
import jinny.study.springboot.awswebservice.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setp() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    @WithMockUser(roles="USER") // USER 권한을 가진 유저가 요청하는 것과 동일한 효과를 가짐
    public void posts_등록() throws Exception {
        // given
        String title = "test title";
        String content = "test content..!!";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("jinny@mail.com")
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // when
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        List<Posts> allPosts = postsRepository.findAll();
        Assertions.assertThat(allPosts.size()).isEqualTo(1);
        Assertions.assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    @WithMockUser(roles="USER")
    public void posts_수정() throws Exception {
        // given
        Posts posts = Posts.builder()
                .title("title _ 1")
                .content("content _ 1")
                .author("jinny@mail.com")
                .build();

        Posts saved = postsRepository.save(posts);
        Long postId = saved.getId();
        String expectedTitle = "title _ 2";
        String expectedContent = "title _ 2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();
        String url = "http://localhost:" + port + "/api/v1/posts/" + postId;

        // when
        mockMvc.perform(put(url)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(new ObjectMapper().writeValueAsString(requestDto)))
                .andExpect(status().isOk());

        // then
        Posts findPosts = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found posts"));
        Assertions.assertThat(findPosts.getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(findPosts.getContent()).isEqualTo(expectedContent);

    }

}