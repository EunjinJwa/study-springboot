package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url) {
        System.out.println("set url = " + url);
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect : " + url);
    }

    public void call(String message) {
        System.out.println("call : " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void discnnect() {
        System.out.println("close " + url);
    }

    /**
     * property의 set이 끝나면 (의존관계 주입이 끝나면) 호출되는 메서드
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        connect();
        call("초기화 연결 메시지");
    }

    /**
     * Bean이 Closing 될 때 실행됨
     * @throws Exception
     */
    @Override
    public void destroy() throws Exception {
        discnnect();
    }
}
