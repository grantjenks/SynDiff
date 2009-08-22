class Example{
    public static void main(String[] a){
        System.out.println(new Ex().computeEx(10));
    }
}

class Ex {
    public int computeEx(int num){
    	// Just returns number.
        return num ;
    }
    public int returnsZero() {
    	return 0;
    }
    public int returnsOne() {
    	return 1;
    }
}

class First {
	public int firstFunction(int a) {
		// Returns 2
		a = 1;
		a = a + 1;
		return a;
	}
}

class Second {
	public int secondFunction(int b) {
		// Returns 0
		b = 0;
		return b;
	}
}
