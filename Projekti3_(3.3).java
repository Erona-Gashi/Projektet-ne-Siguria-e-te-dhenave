

public class SSHA1c {

	public static void main(String[] args) {
		String mesazhi = SSHA1b.randomWord(12);
	    boolean[] hash =  SimplifiedSHA1.hash(mesazhi);
	    int[] hashHex = SimplifiedSHA1.booleanNeHex(hash);
	    String hash1="";
		for(int i = 0;i<hashHex.length;i++) { //kthen vleren hex te hash-it ne string
		   hash1=hash1+ SSHA1b.hexNeChar(hashHex[i]);
		 
		}
		System.out.println("Mesazhi M: " +  mesazhi + " Hash: " + hash1.toUpperCase());
		 
		 if(SSHA1b.hashedRandomWord(12, hash1, 100) == null) {
	            System.out.println("Nuk u gjind mesazhi M' i ndryshem nga mesazhi M ");
	        } else{
	            if(SSHA1b.hashedRandomWord(16, hash1, 100) == mesazhi) {
	             System.out.println("Mesazhi i gjetur eshte i njejte me mesazhin e pare");}

	            System.out.println("U gjind M': " + SSHA1b.hashedRandomWord(16, hash1, 100) + "i ndryshem nga M");
	           }

	}

}
