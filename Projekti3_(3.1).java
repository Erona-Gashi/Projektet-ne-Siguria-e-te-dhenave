

import java.util.ArrayList;
import java.util.Arrays;


public class SimplifiedSHA1 {
	
	 //Konstantet Ki per etapat 1,2,3 dhe 4 si vlera buleane
		public static boolean[] Ki(int i) {
			boolean[][] K = { { false, true, false, true, true, false, true, false }, // K1=5A(hex)
					{ true, true, true, false, false, true, true, true }, // K2=E7(hex)
					{ true, false, false, false, true, true, false, false }, // K3=8C(hex)
					{ true, false, true, true, true, true, false, true } }; // K4=BD(hex)
			return K[i];
		}

		//veprimi XOR ne mes te dy vargjeve buleane
		public static boolean[] XOR(boolean[] p, boolean[] q) {
			boolean[] pXORq = new boolean[p.length];
			for (int i = 0; i < p.length; i++) {
		        if(p[i]==q[i])
				   pXORq[i] = false;
		      	else
					pXORq[i] = true;
			}
			return pXORq;
		}
		
		//veprii AND mes dy vargjeve buleane
		public static boolean[] AND(boolean[] a, boolean[] b) {
			boolean[] aANDb = new boolean[a.length];
			for (int i = 0; i < a.length; i++) {
				aANDb[i] = a[i] && b[i];
			}
			return aANDb;
		}
		
		//veprimi negacion i nje vargu bulean
		public static boolean[] negacion(boolean[] a) {
			boolean[] neg = new boolean[a.length];
			for (int i = 0; i < neg.length; i++) {
				if (a[i]== false)
					neg[i] = true;
			}
			return neg;
		}
		
		//funksionet neper etapa
		public static boolean[] funksionet(boolean[] b, boolean[] c, int etapa) {
			boolean[] f = new boolean[b.length];
			if (etapa == 0) // funksioni per etapen 1
				f = AND(b, c);
			if (etapa == 1 || etapa == 3) // funksioni per etapen 2 dhe 4
				f = XOR(b, c);
			if (etapa == 2) // funksioni per etapen 3
				f = XOR(b, negacion(c));
			return f;
		}
		
		//konverton numrin heksadecimal ne binar fhe e kthen si vlere buleane
		public static boolean[] hexNeBinBool(int h) {
			int hex = h;
			if (hex < 16) {
				ArrayList<Integer> digits = new ArrayList<Integer>();
				boolean[][] hextobin = { { false, false, false, false, false, false, false, false },
						{ false, false, false, false, false, false, false, true },
						{ false, false, false, false, false, false, true, false },
						{ false, false, false, false, false, false, true, true },
						{ false, false, false, false, false, true, false, false },
						{ false, false, false, false, false, true, false, true },
						{ false, false, false, false, false, true, true, false },
						{ false, false, false, false, false, true, true, true },
						{ false, false, false, false, true, false, false, false },
						{ false, false, false, false, true, false, false, true },
						{ false, false, false, false, true, false, true, false },
						{ false, false, false, false, true, false, true, true },
						{ false, false, false, false, true, true, false, false },
						{ false, false, false, false, true, true, false, true },
						{ false, false, false, false, true, true, true, false },
						{ false, false, false, false, true, true, true, true } };
				if (hex == 0) {
					return hextobin[0];
				}
				while (hex > 0) {
					digits.add(hex % 16);
					hex = hex / 16;
				}
				boolean[] b = new boolean[digits.size() * 8];
				for (int i = 0; i < digits.size(); i++) {
					b[8 * i] = hextobin[digits.get((digits.size() - 1) - i)][0];
					b[8 * i + 1] = hextobin[digits.get((digits.size() - 1) - i)][1];
					b[8 * i + 2] = hextobin[digits.get((digits.size() - 1) - i)][2];
					b[8 * i + 3] = hextobin[digits.get((digits.size() - 1) - i)][3];
					b[8 * i + 4] = hextobin[digits.get((digits.size() - 1) - i)][4];
					b[8 * i + 5] = hextobin[digits.get((digits.size() - 1) - i)][5];
					b[8 * i + 6] = hextobin[digits.get((digits.size() - 1) - i)][6];
					b[8 * i + 7] = hextobin[digits.get((digits.size() - 1) - i)][7];
				}
				return b;
			} 
			else {
				ArrayList<Integer> digits = new ArrayList<Integer>();
				boolean[][] hextobin = { { false, false, false, false }, { false, false, false, true },
						{ false, false, true, false }, { false, false, true, true }, { false, true, false, false },
						{ false, true, false, true }, { false, true, true, false }, { false, true, true, true },
						{ true, false, false, false }, { true, false, false, true }, { true, false, true, false },
						{ true, false, true, true }, { true, true, false, false }, { true, true, false, true },
						{ true, true, true, false }, { true, true, true, true } };
				if (hex == 0) {
					return hextobin[0];
				}
				while (hex > 0) {
					digits.add(hex % 16);
					hex = hex / 16;
				}
				boolean[] b = new boolean[digits.size() * 4];
				for (int i = 0; i < digits.size(); i++) {
					b[4 * i] = hextobin[digits.get((digits.size() - 1) - i)][0];
					b[4 * i + 1] = hextobin[digits.get((digits.size() - 1) - i)][1];
					b[4 * i + 2] = hextobin[digits.get((digits.size() - 1) - i)][2];
					b[4 * i + 3] = hextobin[digits.get((digits.size() - 1) - i)][3];
				}
				return b;
			}
		}
		
		//konverton stringun ne varg bulean
		public static boolean[] stringNeBoolean(String a) {
			String s = a;
			boolean[][] sBool = new boolean[s.length()][8];
			for (int i = 0; i < s.length(); i++) {
				char c = s.charAt(i);
				sBool[i] = hexNeBinBool(c);
			}
			boolean[] res = new boolean[8 * s.length()];
			for (int i = 0; i < sBool.length; i++) {
				for (int j = 0; j < 8; j++) {
					res[8 * i + j] = sBool[i][j];
				}
			}

			return res;
		}
		
		//konverton numrin deciaml ne binar dhe e kthen si vlere buleane
		public static boolean[] decNeBinBool(int d) {
			boolean[] res = new boolean[16];
			int[] resInt = new int[16];
			int decimal = d;
			if (decimal == 0)
				return res;
			else
				for (int i = 15; i >= 0; i--) {
					resInt[i] = decimal % 2;
					decimal = decimal / 2;
					if (resInt[i] == 1) {
						res[i] = true;
					}
				}
			return res;
		}
		
		//Merr nje varg vlerash buleane dhe i konverton ne varg vlerash heksadecimal
		public static  int[] booleanNeHex(boolean[] b) {
			int[] hex = new int[8];
			boolean[] bool = b.clone();
			int value = 0;
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 4; j++) {
					if (bool[4 * i + j])
						value = (int) (value + Math.pow(2, 3 - j));
				}
				hex[i] = value;
				value = 0;
			}
			return hex;
		}

		//kthen numrin e plote si varg i vlerave buleane
		public static boolean[] intToBoolean(int[] a) {
			int[] h = a.clone();
			int v = 0;
			boolean[][] sBool = new boolean[h.length / 2][8];
			for (int i = 0; i < h.length / 2; i++) {
				v = h[2 * i] * 16 + h[2 * i + 1];
				sBool[i] = hexNeBinBool(v);
			}
			boolean[] res = new boolean[4 * h.length];
			for (int i = 0; i < sBool.length; i++) {
				for (int j = 0; j < 8; j++) {
					res[8 * i + j] = sBool[i][j];
				}
			}

			return res;
		}
		
		//mesazhi i bere padded si string
		public static boolean[] paddedString(String a) {
			boolean[] mesazhi = stringNeBoolean(a); // mesazhi origjinal i konvertuar ne numra binare
			int L = mesazhi.length; // gjatesia e mesazhit origjinal
			boolean[] bit16L = decNeBinBool(L); // gjatesia e mesazhit si vlere 16 bit qe duhet te shtohet ne fund te mesazhit te rregulluar
			int N = (L + 16) / 32 + 1; // N*32 eshte gjatesia e mesazhit te rregulluar
			mesazhi = Arrays.copyOf(mesazhi, N * 32);
			for (int i = mesazhi.length - 1; i > mesazhi.length - bit16L.length - 1; i--) {
				mesazhi[i] = bit16L[i - (mesazhi.length - bit16L.length - 1) - 1]; // shtimi i bit16L-se ne fund te mesazhit
			}
			mesazhi[L] = true;
			return mesazhi;
		}

	
		//mbledhja modulo 2^8 = 256
		public static boolean[] mod256(boolean[] a, boolean[] b) {
			int aa = 0;
			int bb = 0;
			for (int k = 0; k < 8; k++) {
				if (a[k]) {
					aa = aa + (int) (Math.pow(2, 7 - k));
				}
				if (b[k]) {
					bb = bb + (int) (Math.pow(2, 7 - k));
				}
			}
			int c = aa + bb;
			c = c % 256;

			boolean[] mbetja = new boolean[8];
			for (int i = 0; i < 8; i++) {
				if (c % 2 == 1) {
					mbetja[7 - i] = true;
				}
				c = c / 2;
			}

			return mbetja;
		}
		// ben rotacionin per n bit majtas
		public static boolean[] rotateBool(boolean[] vargu, int n) {
			boolean[] rotate = Arrays.copyOfRange(vargu, n, n + vargu.length);
			for (int i = 0; i < n; i++) {
				rotate[vargu.length - n + i] = vargu[i];
			}
			return rotate;
		}

		//logaritja e hash-it
		public static boolean[] hash(String s) {
		 boolean[][] IV = { { false, true, false, false, false, true, false, true }, // A=45(hex)
					{ true, false, true, false, true, true, true, true }, // B=AF(hex)
						{ true, false, true, false, true, true, false, false }, // C=AC(hex)
					{ true, true, true, true, true, true, true, false } }; //  D=FE(hex)
			boolean[] message = paddedString(s);// Mesazhi i rregulluar me gjatesi N*32
			int N = message.length / 32;
			boolean[][] initVector = IV.clone();// Vektori inicial qe ben xor me vleren e hash-it ne fund
			boolean[][] lastHash = IV.clone();// gjendja e fundit e hash-it
			boolean[][] hash = new boolean[lastHash.length][lastHash[0].length];// hash-i final
			boolean[][] messagei = new boolean[N][32];// mesazhi i ndare ne N xi nga 32 bit
			for (int i = 0; i < messagei.length; i++) {
				messagei[i] = Arrays.copyOfRange(message, 32 * i, 32 * i + 31);
			}
			boolean[][] W = new boolean[16][8]; // W qe na duhet per rounds
			// llogaritja e hash-it per tere tekstin
			for (int i = 0; i < N; i++) {
				// llogaritja e hash-it per nje etape
				for (int etapa = 0; etapa < 4; etapa++) {
					// llogaritja e vlerave te W
					for (int j = 0; j < W.length; j++) {
						if (j <= 3)
							W[j] = Arrays.copyOfRange(messagei[i], 8 * j, 8 * j + 8);
						else
							W[j] = rotateBool(XOR(W[j - 4], W[j - 2]), 2);
					}
					// llogaritja e hash-it per nje round
					for (int round = 0; round < 4; round++) {
						hash[0] = mod256(funksionet(lastHash[1], lastHash[2], etapa), lastHash[3]);
						hash[0] = mod256(hash[0], rotateBool(lastHash[0], 3));
						hash[0] = mod256(hash[0], W[4 * etapa + round]);
						hash[0] = mod256(hash[0], Ki(etapa));
						hash[1] = lastHash[0]; // vlera B e re
						hash[2] = rotateBool(lastHash[1], 7); // vlera C e re
						hash[3] = lastHash[2]; // vlera D e re
						lastHash = hash.clone();
					}
				}
				hash[0] = mod256(lastHash[0], initVector[0]); // pas perfundimit te etapave vlera e hash-it ben xor me initialVector
				hash[1] = mod256(lastHash[1], initVector[1]);
				hash[2] = mod256(lastHash[2], initVector[2]);
				hash[3] = mod256(lastHash[3], initVector[3]);
			}
			boolean[] res = new boolean[32];
			// paraqitja e hash-it ne nje rresht
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 8; j++)
					res[8 * i + j] = hash[i][j];
			}
			return res;
		}

		public static void main(String[] args) {
		 	String s = "Erona Benita";
		 	boolean[] hash = hash(s); // hash ne binar si varg i vlerave buleane 
			int[] hashHex = booleanNeHex(hash); //  hash ne hexadecimal
		 	System.out.println("Mesazhi i dhene: "+ s);
		 	System.out.print("Mesazhi pasi behet hash: ");
			for (int i = 0; i < hashHex.length; i++) 
			{
		 		System.out.print(Integer.toHexString(hashHex[i]).toUpperCase());
		 	}
		 }
}
