
public class FindPrimeNumber {

	public static void main(String[] args) {
		 
		int number = 0;
		int total = 0;
		for(number =2; number <101; number=number+1 ){
			if((number%2==0) || (number%3==0)
			 ||(number%3==0) || (number%7==0)
				&& (
				   (number!= 2)
				&& (number!= 3)
				&& (number!= 5)
				&& (number!=7)))
				{
					System.out.println(number+"its not  prime number");
				}
			else{
				System.out.println(number+"is prime");
			}
			
		}

	}

}
