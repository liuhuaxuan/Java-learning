package hashset;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class SetTest {

	public static void main(String[] args) {
		/**
		 * HashSet��Ԫ�ص�λ���Ǹ���hashcodeɢ�еģ�����������ġ�
		 */
	    Set<String> words = new HashSet<String>();
	    long totalTime = 0L;
	    
	    //���������ж��ļ�
	    Scanner in = new Scanner(System.in);
	    
	    while(in.hasNext()){
	    	String word = in.next();
	    	long callTime = System.currentTimeMillis();
	    	words.add(word);
	    	callTime = System.currentTimeMillis() - callTime;
	    	totalTime += callTime;
	    }
	    
	    Iterator<String> iterator = words.iterator();
	    for(int i = 1; i <= 20 && iterator.hasNext(); i++){
	    	System.out.println(iterator.next());
	    }
	    
	    System.out.println("......");
	    System.out.println(words.size() + " distinct words" + totalTime + " milliseconds");
	}
}
