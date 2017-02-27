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
         * 将集合b合并到集合a
         */
        ListIterator<String> aIterator = a.listIterator(); /*得到集合a的迭代器*/
        Iterator<String> bIterator = b.listIterator(); /*得到集合b的迭代器*/
        
        while(bIterator.hasNext()){
        	if(aIterator.hasNext()){
        		aIterator.next();
        	}
        	/*
        	 * ListIterator接口实现Iterator接口，
        	 * 并增添了add方法，在迭代器位置之前添加一个元素
        	 */
        	aIterator.add(bIterator.next());
        }
        
        System.out.println(a);  
        //结果：[Amy, Bob, Carl, Doug, Erica, Frances, Gloria]
        //可以得出迭代器的位置实际位于两个元素之间
        
        /**
         * 双向链表
         */
        while(aIterator.hasPrevious()){
        	System.out.printf("%s ",aIterator.previous());  
        }
        System.out.println();
      //结果：Gloria Frances Erica Doug Carl Bob Amy
        
        /**
         * 删除集合a
         */
        aIterator = a.listIterator();
        
        while(aIterator.hasNext()){
        	aIterator.next();
        	aIterator.remove();
        }
        System.out.println(a); 
        //结果：[]
        
	}
}
