import java.util.Comparator;
import java.util.function.Consumer;

import org.junit.Test;

//左右遇到單一誇號省 左測推斷類型省
//lambda 表達式 需要函數式街口支持
//函數式接口: 若街口中只有一個抽象方法的街口，稱為函數式接口 可以使用 @functionalInterface 修飾(可以檢查是否是一個函數式接口)
public class TestLambda2 {
	
	@Test
	//無參數 無返回值 lambda  用法
	public void test1() {
		final int num = 0 ;

		Runnable r = new Runnable() {
			@Override
			public void run() {
				System.out.println("Hello" + num);				
			}
		};
		
		r.run();
		System.out.println("-----------------------");
		Runnable r1 = ()->System.out.println("lambda"+ num);
		r1.run();
	}
	
	@Test
	//有1參數 無返回值 lambda  用法
	public void test2() {
		Consumer<String> c = (x)->System.out.println(x);
		c.accept(":))");
	}
	
	@Test
	//若只有1參數 ()可省略不寫
	public void test3() {
		Consumer<String> c = x->System.out.println(x);
		c.accept(":)) 我沒括號");
	}
	
	@Test
	//若只有2參數 有返回值 且有多條語句 需用括號
	public void test4() {
		Comparator<Integer> com = (x,y)->{
			System.out.println("in comparator");
			return Integer.compare(x, y);
		};
		
		System.out.println(com.compare(1, 2));
	}
	
	@Test
	//若只有1條語句 大誇號&return 都可以省略
	public void test5() {
		Comparator<Integer> com = (x,y)->Integer.compare(x, y);
		
		System.out.println(com.compare(3, 1));
	}
	
	@Test
	//若只有1條語句 大誇號&return 都可以省略 (數據類型也可以省略)
	public void test6() {
		Comparator<Integer> com = ( Integer x, Integer y)->Integer.compare(x, y);
		
		System.out.println(com.compare(3, 1));
	}
	
	@Test
	public void test7() {
		Integer num =operation(23,(x)->x*x);
		System.out.println(num);
	}
	
	public Integer operation(Integer x,TestInterFace2 y) {
		return y.operate(x);
	}


}
