import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

// 方法引用:若Lambda体中的内容有方法已经实现了,我们可以使用 “方法引用“
// (可以理解为方法引用是Lambda 表达式的另外一种表现形式
// 主要有三种语法格式:
// 对象::实例方法名
//  类::静态方法名
//  类::实例方法名

// 注意:
// 1. Lambda体中调用方法的参数列表与返回值类型,要与函数式接口中抽象方法的函数列表和返回值类型保持一致!
// 2. 若Lambda 参数列表中的第一参数是实例方法的调用者,而第二个参数是实例方法的参数时,可以使用ClassName::method

// 二、构造器引用:
// 格式:
// ClassName : : new
// 注意:需要调用的构造器的参数列表要与函数式接口中抽象方法的参数列表保持一致

// 三、数组引用:
// Type[]: :new;

public class TestMethodRed {
	
	@Test
	// 对象::实例方法名
	public void test1() {
		Consumer<String> con = (x)->System.out.println(x);
		
		PrintStream ps = System.out;
		Consumer<String> con1 = ps::println;
		
		con.accept("Steve");
		con1.accept("Steve2");
	}
	
	@Test
	public void test2() {
		Employee employee = new Employee(); 
		Supplier<Integer> s = employee::getAge;
		System.out.println(s.get());
	}
	
	@Test
	//  类::实例方法名
	public void test3() {
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		Comparator<Integer> com1 = Integer::compare;
		System.out.println(com.compare(1, 1));
		System.out.println(com1.compare(2, 1));
	}
	
	@Test
	//类::静态方法名
	public void test4() {
		BiPredicate<String, String> bp =(x,y)->x.equals(y);
		BiPredicate<String, String> bp1 = String::equals;
		System.out.println(bp.test("1","一")); 
		System.out.println(bp1.test("1","1")); 
	}
	
	//构造器引用
	@Test
	public void test5() {
	Supplier<Employee> sup = () -> new Employee();
	//构造器引用方式
	Supplier<Employee> sup2 = Employee::new;
	Employee emp = sup2.get();
	System.out.println(emp);
	}
	
	//构造器引用
	@Test
	public void test6() {
		Function<Integer,Employee> fun = (x)-> new Employee(x);
		Function<Integer,Employee> fun2 = Employee::new;
		
		BiFunction<Integer,Integer,Employee> fun3 = Employee::new;
	
	}
	
	//构造器引用
	@Test
	public void test7() {
		Function<Integer,String[]> fun = (x)-> new String[x];
		Function<Integer,String[]> fun2 = String[]::new ;
		String[] x =fun2.apply(10);
		
		System.out.println(fun.apply(2).length);

		System.out.println(x.length);
		


	}


}
