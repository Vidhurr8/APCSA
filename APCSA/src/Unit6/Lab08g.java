package Unit6;

import static java.lang.System.*;

public class Lab08g
{
	public static void main ( String[] args )
	{
		LoopStats test = new LoopStats(1, 5);
		out.print(test);

		test = new LoopStats(2, 8);
		out.print(test);

		test = new LoopStats(5, 15);
		out.print(test);
	}
}