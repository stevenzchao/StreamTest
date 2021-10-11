import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 注意: 1. Stream自己不会存储元素。 2. Stream 不会改变源对象。相,他们会返回一个持有结果的新Stream。 3. Stream
 * 操作是延迟执行得。这意味着他们会等到需要结果的时候才执行。
 * 
 * 流程:創->中間操作->終止(終止操作)
 */
public class StreamTest {
	// stream 方法:map filter sorted reduce
	// list 的例子 數字
	List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
	// list 的例子 字串
	List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

	// 创建Stream
	@Test
	public void test() {
		// 1. 可以通过Collecti系列集合提供的stream() 或 parallelStream()
		List<String> list = new ArrayList<>();
		Stream<String> stream1 = list.stream();

		// 2通过Arrays中的静态方法stream()获取数组流
		Employee[] emps = new Employee[10];
		Stream<Employee> stream2 = Arrays.stream(emps);

		// 3.通过Stream类中的态方法of()
		Stream<String> stream3 = Stream.of("aa", "bb", "cc");

		// 4.创建无限流
		// 迭代
		Stream<Integer> stream4 = Stream.iterate(0, (x) -> x + 2);
		stream4.limit(10).forEach((x) -> System.out.println(x));

		// 5.生成
		// 迭代
		Stream.generate(() -> Math.random()).limit(10).forEach(System.out::println);
	}

	@Test
	public void test1() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// 把 list 都成以平方 使用map
		List<Integer> squaresList = numbers.stream().map(i -> i * i).collect(Collectors.toList());
		System.out.println("乘平方  : " + squaresList);
	}

	@Test
	public void test2() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// list 大於3的才使用 使用filter
		List<Integer> filterSquaresList = numbers.stream().filter(i -> i > 3).map(i -> i * i)
				.collect(Collectors.toList());
		System.out.println("大於3才使用 並且乘平方  : " + filterSquaresList);
	}

	@Test
	public void test3() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// list 大於3才會乘平方 使用map & 問號判斷式
		List<Integer> conditionSquaresList = numbers.stream().map(i -> i <= 3 ? i : i * i).collect(Collectors.toList());
		System.out.println("大於3才會乘平方 並保留原本的值  : " + conditionSquaresList);
	}

	@Test
	public void test4() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// 箭頭右邊直接塞方法
		List<Integer> filterSquaresListss = numbers.stream().map(i -> {
			if (i > 3) {
				return i * i;
			}
			return i;
		}).collect(Collectors.toList());
		System.out.println("箭頭右邊直接塞方法  : " + filterSquaresListss);
	}

	@Test
	public void test5() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// list 排序 使用sorted
		List<Integer> sortedSquareList = numbers.stream().sorted().collect(Collectors.toList());
		System.out.println("排序  : " + sortedSquareList);
	}

	@Test
	public void test6() {
		System.out.println("原字串陣列 [\"abc\", \"\", \"bc\", \"efg\", \"abcd\", \"\", \"jkl\"]");
		// 字串的 list 使用 filter 過濾空字串並返回空字串總數
		long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
		System.out.println("返回空字串總數" + count);
	}

	@Test
	public void test7() {
		System.out.println("原字串陣列 [\"abc\", \"\", \"bc\", \"efg\", \"abcd\", \"\", \"jkl\"]");
		// 字串的 list 使用 filter 過濾空含有a的字串
		List<String> stringssss = strings.stream().filter(string -> string.contains("a")).collect(Collectors.toList());
		System.out.println("過濾空含有a的字串" + stringssss);
	}

	@Test
	public void test8() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// reduce 相加
		int x = numbers.stream().reduce(Integer::sum).get();
		int x1 = numbers.stream().reduce(0,(a,y)->a+y);
		System.out.println("reduce 相加" + x);
		System.out.println("reduce 相加" + x1);

	}

	@Test
	public void test9() {
		System.out.println("原數字陣列 [3, 2, 2, 3, 7, 3, 5]");
		// reduce 相減f
		int y = numbers.stream().reduce((a, b) -> a - b).get();
		System.out.println("reduce 相減" + y);
	}

	@Test
	public void test10() {
		List<String> slist = Arrays.asList("aaa", "bbb", "ccc", "ddd");
		slist.stream().map(i -> i + "s").forEach(System.out::println);
		System.out.println("=-================================");

		Stream<Stream<Character>> stream = slist.stream().map(StreamTest::filterC);
		stream.forEach(sm->sm.forEach(System.out::println));
		
		System.out.println("=-================================");
		
		Stream<Character> streamf = slist.stream().flatMap(StreamTest::filterC);
		streamf.forEach(System.out::println);

	}


	public static Stream<Character> filterC(String str){
		List<Character> sc = new ArrayList<Character>();
		for(Character c:str.toCharArray()) {
			sc.add(c);
		}
		return sc.stream();
	}

	/**
	 * 查找与匹配
		allMatch检查是否匹配所有元素
		anyMatch一检查是否至少匹配一个元素
		noneMatch检查是否没有匹配所有元素
		findFirst返回第一个元素
		findAny返回当前资中的任意元素
		count返回流中元素的总个数
		max-返回流中最大值
		min返回流中最小值
	 */
	// distinct, skip, MAX
	@Test
	public void test11() {
		int max = numbers.stream().max((e1,e2)->Integer.compare(e1, e2)).orElse(245);
		System.out.println(max);
	}



}
