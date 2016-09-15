package br.uefs.control;

public class Debuger {
	
	private static boolean print = true;
	
	public static void sysPrint(String x) {
			if(print)
			System.out.println( x );
	}
	public static  void disablePrint()
	{
		print = false;
	}
	public static  void enablePrint()
	{
		print = true;
	}
}
