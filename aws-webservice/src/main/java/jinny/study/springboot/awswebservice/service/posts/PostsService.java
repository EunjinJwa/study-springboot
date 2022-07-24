package jinny.study.springboot.awswebservice.service.posts;

import jinny.study.springboot.awswebservice.domain.posts.Posts;
import jinny.study.springboot.awswebservice.domain.posts.PostsRepository;
import jinny.study.springboot.awswebservice.web.dto.PostsResponseDto;
import jinny.study.springboot.awswebservice.web.dto.PostsSaveRequestDto;
import jinny.study.springboot.awswebservice.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;
    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }


    public PostsResponseDto findById(Long id) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        System.out.println(posts.getTitle());
        return new PostsResponseDto(posts);

    }
}
