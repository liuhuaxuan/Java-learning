package treeset;


public class Item implements Comparable<Item> {

	private String description;
	private int partNumber = 0;
	
	/**
	 * Construct an item 
	 * @param description them item's description
	 * @param partNumber them item's part number
	 */
	public Item(String description,int partNumber){
		this.setDescription(description);
		this.partNumber = partNumber;
	}
	@Override
	public int compareTo(Item other) {
		return Integer.compare(partNumber, other.partNumber);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
