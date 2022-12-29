package jinny.study.springboot.springpractice.service;

import jinny.study.springboot.springpractice.exception.CheckThrowException;
import jinny.study.springboot.springpractice.exception.InvalidValueException;
import org.springframework.stereotype.Service;

@Service
public class ExceptionService {

    public void sumTest(int a, int b) {
        sum(a, b);
    }

    public int divTest(int a, int b) {
        try {
            return div(a, b);
        } catch (CheckThrowException e) {
            throw new RuntimeException(e);
        }
    }


    // runtime Exception
    private int sum(int a, int b) {
        if (a + b == 0) {
            throw new InvalidValueException("0 is not valid");
        }
        return a + b;
    }

    private int div(int a, int b) throws CheckThrowException {
        if (b == 0) {
            throw new CheckThrowException("div value cannot be 0.");
        }
        return a / b;
    }

}
