package jinny.study.springboot.springpractice.factory;

import jinny.study.springboot.springpractice.common.builder.ADataBuilder;
import jinny.study.springboot.springpractice.common.builder.BDataBuilder;
import jinny.study.springboot.springpractice.common.builder.CDataBuilder;
import jinny.study.springboot.springpractice.common.builder.DataBuilder;
import org.springframework.stereotype.Component;

@Component
public class BuilderFactory {

    public static DataBuilder getBuilder(String type) {
        switch (type) {
            case "A":
                return new ADataBuilder();
            case "B":
                return new BDataBuilder();
            case "C":
                return new CDataBuilder();
            default:
                return null;
        }
    }

}
