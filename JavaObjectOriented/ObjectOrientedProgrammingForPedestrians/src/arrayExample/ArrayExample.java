package arrayExample;
/*
 * This program is about array example
 */
public class ArrayExample {

	public static void main(String[] args) {

		int [] myArray = new int[20];//creating 20 of int type object
		int i;
		//fill it up with square number
		for(i=0;i<19;i++){
			myArray[i]=i*i;
			System.out.println("myArray["+i+"] contains"+
								myArray[i]);
		}
		System.out.println("Reverse order");
		//print it out reverse order
		for(i=19;i>=0;i--){
			System.out.println("myArray["+i+"] contains "+
								myArray[i]);
		}
		
		//adding up total
		int runningTotal = 0;
		
		System.out.println();
		for(i=0;i<19;i++){
			runningTotal = runningTotal+myArray[i];
			System.out.println("Runninf total is :"+runningTotal);
		}
		
		/*
		char[] alphaArray = new char[50];
		int j;
		for(j=0;j<49;j++){
			alphaArray[j]='a';
			System.out.println(alphaArray[j]);
		}*/
		
		
		
		
	}

}
