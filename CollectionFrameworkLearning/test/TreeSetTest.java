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
		 * �Ƚ���û�����ݣ�ֻ�ǱȽϷ����ĳ�������
		 * ���ֶ����Ϊ��������ͨ�����ڲ��ڶ���
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
