class Example{
    public static void main(String[] a){
        System.out.println(new Ex().computeEx(10));
    }
}

class Ex {
    public int returnsOne() {
    	return 1;
    }
    public int returnsZero() {
    	return 0;
    }
    public int computeEx(int num){
    	// Just returns number.
        return num ;
    }
}

class Second {
	public int secondFunction(int b) {
		b = 0;
		return b;
	}
}

class First {
	public int firstFunction(int a) {
		a = a + 1;
		a = 2;
		return a;
	}
}

