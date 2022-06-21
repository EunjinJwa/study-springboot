package hello.core.order;

/**
 * Created by Eunjin on 2022-02-06.
 */
public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
