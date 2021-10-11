
public class TestInterfaceSalary implements TestInterface<Employee> {

	@Override
	public boolean test(Employee t) {
		// TODO Auto-generated method stub
		return t.getSalary()>5000;
	}

}
