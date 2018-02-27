package fdu.ex4.copyconstructor;

/**
 * @author ilker
 *
 */
public class CopyConstructorExample {
	public static class MySimpleClass {
		private String lastName;
		public MySimpleClass(String _lastName) {
			lastName = _lastName;
		}
		public void setLastName(String lastName) { this.lastName = lastName;}
		public String getLastName() { return lastName;}
		public String toString() { return "lastName:" + lastName;}
	}
	
	public static class MyClass {
		private String name;
		private MySimpleClass mySimple;
		public MyClass(String _name, MySimpleClass _mySimple){
			this.name = _name;
			mySimple = _mySimple;
		}
		public MyClass(MyClass copyMe) {
//			this(copyMe.name, copyMe.mySimple);  // NOTE ilker BAD - "shallow copy" 
			this(copyMe.name, new MySimpleClass(copyMe.mySimple.getLastName())); // // NOTE ilker GOOD - "deep copy"
		}
		public String getName(){ return name; }
		public String prefix2name(String prefix){
			name = prefix + name;
			return name;
		}
		@Override
		public String toString() { return "name:" + name + "mySimple:" + mySimple; }
	}

	
	public static void main(String[] args) {
		MyClass myClass1 = new MyClass("John", new MySimpleClass("Doe"));
		MyClass myClass1b = myClass1;
		myClass1.prefix2name("MR");
		myClass1b.prefix2name("MRS");
		System.out.println("myClass1=" + myClass1);
		System.out.println("myClass1b=" + myClass1b);
		
		MyClass myClass2 = new MyClass(myClass1);
		myClass2.prefix2name("DR");
		System.out.println("myClass2=" + myClass2);
	}
}
