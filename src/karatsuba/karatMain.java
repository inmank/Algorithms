package karatsuba;

public class karatMain {

	public static void main(String[] args) {
		long x = Long.parseLong(args[0]);
		long y = Long.parseLong(args[1]);
		System.out.println(new karatMain().mutlipy(x, y));
	}
	
	private long mutlipy(long x, long y) {
 		if (x <= 10 || y <= 10) {
			return x*y;
		} else {
			int size1 = 1 + (int)Math.floor(Math.log10(x));
			int size2 = 1 + (int)Math.floor(Math.log10(y));
			long size = Math.max(size1, size2);
			size = (long) Math.ceil(size/2);
			
			long a = (long) (x/Math.pow(10, size));
			long b = (long) (x%Math.pow(10, size));
			long c = (long) (y/Math.pow(10, size));
			long d = (long) (y%Math.pow(10, size));

			long z0 = mutlipy(a, c);
			long z1 = mutlipy(b, d);
			long temp = mutlipy((a+b),(c+d));
			long z2 = temp - z1 - z0;

			return (long) (z0*Math.pow(10, (2*size)) + z2*Math.pow(10, size) + z1);
		}	
	}
}
