package wallet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Check2 {

	public static void main(String[] args) {
		BufferedReader br=new BufferedReader(new
				InputStreamReader(System.in));  
		StringTokenizer st=null; 
		//while (st == null || !st.hasMoreElements()) 
		//{ 
			try
			{ 
				st = new StringTokenizer(br.readLine()); 
			} 
			catch (IOException e) 
			{ 
				e.printStackTrace(); 
			} 
		//} 
		System.out.println( st.nextToken()); 

	}

}
