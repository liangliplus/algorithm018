package indi.liangli.week01;

import java.util.Deque;
import java.util.LinkedList;

/**
 *
 * 用 add first 或 add last 这套新的 API 改写 Deque 的代码
 * @author liangli
 * @Date: 2020/10/22 6:43
 */
public class DequeApi {

    public static void main(String[] args) {
        Deque<String> deque = new LinkedList<>();
        deque.addFirst("a");
        deque.addFirst("b");
        deque.addFirst("c");

        System.out.println(deque);

        String str = deque.peekFirst();
        System.out.println(str);
        System.out.println(deque);

        while (deque.size() > 0) {
            System.out.println(deque.removeFirst());
        }
        System.out.println(deque);

    }

}
