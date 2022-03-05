package hello.core.singleton;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SingletoneService {

    private static final SingletoneService instance = new SingletoneService();

    public static SingletoneService getInstance() {
        return instance;
    }

    // 외부에서 SingletoneService의 직접 생성을 막기 위해 생성자를 private로 만들어둔다.
    private SingletoneService() {
    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }

}

