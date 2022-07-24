package jinny.study.springboot.awswebservice.domain.posts;

import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        // given
        String title = "타이틀입니다.";
        String content = "본문입니다..";
        Posts post = Posts.builder().title(title).content(content).author("jinny@mail.com").build();

        postsRepository.save(post);

        // when
        List<Posts> posts = postsRepository.findAll();

        // then
        Assertions.assertThat(posts.size()).isEqualTo(1);
        Assertions.assertThat(posts.get(0).getTitle()).isEqualTo(title);
        Assertions.assertThat(posts.get(0).getContent()).isEqualTo(content);
    }

}