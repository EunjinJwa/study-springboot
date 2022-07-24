package jinny.study.springboot.awswebservice.web.dto;

import jinny.study.springboot.awswebservice.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostsUpdateRequestDto {

    private String title;
    private String content;
}
