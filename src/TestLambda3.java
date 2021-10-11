import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 
 * Consumer<T> : 消費型接口 void accept(T t);
 * 
 * Supplier<T> ： 供給行接口 T get();
 * 
 * Function<T,R> : 函數行接口 R : apply<T,R>
 * 
 * Predicate<T> : 斷言行接口 boolean test(T t)
 *
 */
public class TestLambda3 {

	@Test
	public void test1() {
		happy(1000, (x) -> System.out.println("我花了 " + x + " 元"));

	}

	public void happy(Integer m, Consumer<Integer> c) {
		c.accept(m);
	}

	@Test
	public void test2() {
		List<Integer> numlist = happy2(1000, () -> (int) (Math.random() * 1000));
		numlist.forEach(System.out::println);

	}

	public List<Integer> happy2(Integer m, Supplier<Integer> s) {
		List<Integer> test = new ArrayList<Integer>();
		for (int i = 0; i < m; i++) {
			Integer n = s.get();
			test.add(n);
		}
		return test;
	}

	@Test
	public void test3() {
		String s = happy3(-2, (x) -> {
			if (x > 0) {
				return "餘額為正";
			}
			return "no money!!!!";
		});
		System.out.println(s);

	}

	public String happy3(Integer m, Function<Integer, String> f) {

		return f.apply(m);
	}
	
	@Test
	public void test4() {
		boolean s = happy4(5,x->x>4);
		System.out.println(s);

	}

	public boolean happy4(Integer m, Predicate<Integer> p) {

		return p.test(m);
	}

}
