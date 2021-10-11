import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;

import org.junit.Test;

public class TestLambda {

	@Test
	public void test() {
		Comparator<Integer> com = new Comparator<Integer>() {
			
			@Override
			public int compare(Integer o1, Integer o2) {
				return Integer.compare(o1, o2);
			}
		};	
		
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	@Test
	public void test2() {
		Comparator<Integer> com =(x,y)-> Integer.compare(x, y);
		TreeSet<Integer> ts = new TreeSet<>(com);
	}
	
	
	List<Employee> employees = Arrays.asList(
			new Employee("張3",18,9999),
			new Employee("李4",38,5555),
			new Employee("王5",50,6666),
			new Employee("趙6",16,7777),
			new Employee("陳7",8,8888)

			);
	
	@Test
	public void test3() {
		List<Employee> emps = filterEmployees(employees);
		emps.forEach(System.out::println);
		
		for(Employee r:emps) {
			System.out.println(r.getName());
		}
	}
	
	public List<Employee> filterEmployees(List<Employee> list){
		List<Employee> emps = new ArrayList<>();
		for(Employee e:list) {
			if(e.getAge()>=10) {
				emps.add(e);
			}
		}
		return emps;
	}
	
	@Test
	//優化方式 策略設計模式
	public void test4() {
		List<Employee> emps = filterEmployeeByInterface(employees, new TestInterfaceAge());
		
		for(Employee r:emps) {
			System.out.println(r.getName());
		}
	}
	
	public List<Employee> filterEmployeeByInterface(List<Employee> list, TestInterface<Employee> mp){
		
		List<Employee> emps = new ArrayList<Employee>();
		for(Employee e : list) {
			if(mp.test(e)) {
				emps.add(e);
			}
		}
		return emps;
		
	}
	
	@Test
	//優化方式 匿名內部類
	public void test5() {
		List<Employee> emps = filterEmployeeByInterface(employees, new TestInterface<Employee>() {
			
			@Override
			public boolean test(Employee t) {
				return t.getAge()<=8;
			}
		});
		
		for(Employee r:emps) {
			System.out.println(r.getName());
		}
	}
	
	@Test
	//優化方式 lambda
	public void test6() {
		List<Employee> emps = filterEmployeeByInterface(employees, (e)->e.getName().equals("張3"));
		
		emps.forEach(System.out::println);
		}
	
	
	@Test
	//優化方式 stream API
	public void test7() {
		employees.stream().filter((e)-> e.getAge()>30).limit(2).forEach(System.out::println);
	}
	
	@Test
	public void test8() {
		employees.stream().filter((e)-> e.getAge()>30).limit(2).map(Employee::getAge).forEach(System.out::println);
	}
	
	
	
	
}


