
public class TestInterfaceAge implements TestInterface<Employee> {

	@Override
	public boolean test(Employee t) {
		// TODO Auto-generated method stub
		return t.getAge()>35;
	}

}
