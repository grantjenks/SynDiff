class Factorial{
    public static void main(String[] a){
		System.out.println(new Fac().ComputeFac(10));
    }
}

class Fac {
    public int ComputeFac(int num){
        int num_cux ;
        int num_bux ;
		if (num < 1)
            num_cux = 1 ;
        else
            num_cux = num * (this.ComputeFac(num-1)) ;
        return num_cux ;
    }
}
