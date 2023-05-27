

import java.util.concurrent.ThreadLocalRandom;

public class SSHA1b {
	
	 //kthen numrin heksadecimal si varg te karaktereve
	 public static char hexNeChar(int hexValue) {
			if (hexValue >= 0 && hexValue <= 9)
				return (char)(hexValue + '0');
			else
				return (char)(hexValue - 10 + 'A');
			}
	//Kthen nje fjale randome 
	public static String randomWord(int a)
    {
         String s = "";
         for (int j = 0; j < a; j++) {
            s += (char)(ThreadLocalRandom.current().nextInt(65, 91));// 65=A - 91=Z
         }
      return s;
    }
     
	 //merr fjalet randome deri te kufiri count dhe i bene hash 
	 public static String hashedRandomWord(int b, String hashedMessage, int count)
	    { String a = randomWord(b);
	   
	    boolean[] hash =  SimplifiedSHA1.hash(a);
	    int[] hashHex = SimplifiedSHA1.booleanNeHex(hash); 
		String hash1="";
		for(int i = 0;i<hashHex.length;i++) { //kthen vleren hex te hash-it ne string
		   hash1=hash1+hexNeChar(hashHex[i]);
		}

	       System.out.println("M': " + a + " Hash: " + hash1.toUpperCase());
	        if(hash1 == hashedMessage)
	            return a;
	        else if (count >= 0)
	            return hashedRandomWord(b, hashedMessage, count - 1);
	           
	        return null;

	    }
	

	 
	 public static void main(String[] args) {
		 
		 final String HM = "4BAFE69C";
	        if(hashedRandomWord(12, HM, 200) == null) {
	            System.out.println("M' nuk u gjind");
	        } else
	            System.out.println("M' eshte: " + hashedRandomWord(16, HM, 100) );
	    }





}