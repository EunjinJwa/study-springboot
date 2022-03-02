package jinny.study.hellospringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("/hello")
    public String hello(Model model) {
        model.addAttribute("data", "Kassy!!");
        return "hello";     // return : viewName. viewResolver가 기본적으로 resources:templates/{viewName}.html 이렇게 찾음.
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(required = true, value = "name") String name,
                           Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }

    /**
     * [ API ]
     * @ResponseBody
     * HTTP의 body에 문자 내용을 직접 반환.
     * viewResolver 대신에 HttpMessageConverter 가 동작한다.
     * 기본 문자처리 : StringHttpMessageConverter
     * 기본 객체처리 : MappingJackson2HttpMessageConverter (jackson : 객체를 json으로 변환해주는 라이브러리)
     */
    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }



}
