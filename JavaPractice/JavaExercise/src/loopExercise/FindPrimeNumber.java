package loopExercise;

public class FindPrimeNumber {

	//nested for loop example
	public static void main(String[] args) {
		 
		int i , j;
		for(i =2 ; i<100; i++)
		{
			for(j = 2; j <=(i/j); j++)
			{
				if((i%j) == 0) 
					break; //if factor found, not prime
				/*if (j > (i/j))
					System.out.println("is prime"+i);*/
			}
			if (j > (i/j))
			System.out.println(i+ " is prime number.");
		}
		

	}

}
