import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> numbers = Arrays.asList(3, 2, 2, 3, 7, 3, 5);
		// 获取对应的平方数
		
		List<Integer> squaresList = numbers.stream().map( i -> i*i).collect(Collectors.toList());
	 	System.out.println("乘平方  : " + squaresList);
	 	
		List<Integer> filterSquaresList = numbers.stream().filter(i->i>3).map( i -> i*i).collect(Collectors.toList());
	 	System.out.println("大於3才使用 並且乘平方  : " + filterSquaresList);
	 	
		List<Integer> conditionSquaresList = numbers.stream().map( i -> i < 3 ? i:i*i).collect(Collectors.toList());
	 	System.out.println("大於3才會乘平方 並保留原本的值  : " + conditionSquaresList);
	 	
		List<Integer> sortedSquareList = numbers.stream().sorted().collect(Collectors.toList());
	 	System.out.println("排序  : " + sortedSquareList);
	 	
	 	List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd","", "jkl");
	 // 获取空字符串的数量
	 	long count = strings.parallelStream().filter(string -> string.isEmpty()).count();
	 	System.out.println(count);
	 	
	 	List<String> stringssss = strings.stream().filter(string -> string.contains("a")).collect(Collectors.toList());
	 	System.out.println(stringssss);
	 	
	 	
		List<Integer> filterSquaresListss = numbers.stream().map( i ->{ 
			if(i>3) {
				return i*i;
			}
			return i;
			}).collect(Collectors.toList());
	 	System.out.println("大於3才使用 並且乘平方  : " + filterSquaresListss);
	 	
	 	
	 	int x = numbers.stream().reduce((a,b) -> a + b).get();
	 	System.out.println(x);

	
	}

}
