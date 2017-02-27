package treeset;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class TreeSetTest {

	public static void main(String[] args) {
		
		SortedSet<Item> parts = new TreeSet<Item>();
		parts.add(new Item("item1", 1));
		parts.add(new Item("item2", 2));
		parts.add(new Item("item3", 3));
		
		/**
		 * 比较器没有数据，只是比较方法的持有器。
		 * 这种对象称为函数对象，通常用内部内定义
		 */
		SortedSet<Item> sortedBydescription = new TreeSet<Item>(
				new Comparator<Item>() {

					@Override
					public int compare(Item itemOne, Item itemTwo) {
						String itemoneString = itemOne.getDescription();
						String itemtwoString = itemTwo.getDescription();
						
						return itemOne.compareTo(itemTwo);
					}
				});
		
		sortedBydescription.addAll(parts);
		System.out.print(sortedBydescription);
	}

}
