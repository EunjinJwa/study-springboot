package jinny.study.springboot.awswebservice.web;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.junit.Assert.*;

public class ProfileControllerTest {

    @Test
    public void real_profile이_조회된다() {
        // given
        String expectedProfile = "real";
        MockEnvironment env = new MockEnvironment();
        env.addActiveProfile(expectedProfile);
        env.addActiveProfile("oauth");
        env.addActiveProfile("real-db");

        ProfileController controller = new ProfileController(env);

        // when
        String profile = controller.profile();

        // then
        Assertions.assertThat(profile).isEqualTo(expectedProfile);
    }
}