package linkedlist;

import java.util.*;

public class LinkedlistTest {

	public static void main(String[] args) {
		/** 
		 * public class LinkedList<E> 
		 * extends AbstractSequentialList<E>
		 * implements List<E>, Deque<E>, Cloneable, Serializable
		 */
		List<String> a = new LinkedList<String>();
        a.add("Amy");
        a.add("Carl");
        a.add("Erica");
        
        List<String> b = new LinkedList<String>();
        b.add("Bob");
        b.add("Doug");
        b.add("Frances");
        b.add("Gloria");
        
        /**
         * ������b�ϲ�������a
         */
        ListIterator<String> aIterator = a.listIterator(); /*�õ�����a�ĵ�����*/
        Iterator<String> bIterator = b.listIterator(); /*�õ�����b�ĵ�����*/
        
        while(bIterator.hasNext()){
        	if(aIterator.hasNext()){
        		aIterator.next();
        	}
        	/*
        	 * ListIterator�ӿ�ʵ��Iterator�ӿڣ�
        	 * ��������add�������ڵ�����λ��֮ǰ���һ��Ԫ��
        	 */
        	aIterator.add(bIterator.next());
        }
        
        System.out.println(a);  
        //�����[Amy, Bob, Carl, Doug, Erica, Frances, Gloria]
        //���Եó���������λ��ʵ��λ������Ԫ��֮��
        
        /**
         * ˫������
         */
        while(aIterator.hasPrevious()){
        	System.out.printf("%s ",aIterator.previous());  
        }
        System.out.println();
      //�����Gloria Frances Erica Doug Carl Bob Amy
        
        /**
         * ɾ������a
         */
        aIterator = a.listIterator();
        
        while(aIterator.hasNext()){
        	aIterator.next();
        	aIterator.remove();
        }
        System.out.println(a); 
        //�����[]
        
	}
}
