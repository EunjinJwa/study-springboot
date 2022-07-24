package jinny.study.springboot.awswebservice.web;

import jinny.study.springboot.awswebservice.domain.posts.Posts;
import jinny.study.springboot.awswebservice.domain.posts.PostsRepository;
import jinny.study.springboot.awswebservice.web.dto.PostsSaveRequestDto;
import jinny.study.springboot.awswebservice.web.dto.PostsUpdateRequestDto;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록() {
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
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> allPosts = postsRepository.findAll();
        Assertions.assertThat(allPosts.size()).isEqualTo(1);
        Assertions.assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
    }

    @Test
    public void posts_수정() {
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
        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        // when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        // then
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody()).isGreaterThan(0L);

        Posts findPosts = postsRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("not found posts"));
        Assertions.assertThat(findPosts.getTitle()).isEqualTo(expectedTitle);
        Assertions.assertThat(findPosts.getContent()).isEqualTo(expectedContent);

    }

}