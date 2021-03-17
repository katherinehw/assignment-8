package herman;

/**
 * 
 * @author KatherineHerman-Williams
 *
 *         creates book class with attribute
 */

public class Book {
	private int sku; // stock keeping unit
	private String title;
	private double price;
	private int quant;

	/**
	 * @param sku   stock keeping unit
	 * @param title title of book
	 * @param price price of book
	 * @param quant quantity of this book
	 */

	public Book(String title, double price, int quant, int sku) {
		this.sku = sku;
		this.title = title;
		this.price = price;
		this.quant = quant;
	}

	public String listString() {
		return ("Stock Keeping Unit: " + sku + "\nTitle: " + title + "\nPrice: " + price + "\nQuantity: " + quant);
	}

	public void setSku(int sku) {
		this.sku = sku;
	}

	public int getSku() {
		return sku;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getPrice() {
		return price;
	}

	public void setQuant(int quant) {
		this.quant = quant;
	}

	public int getQuant() {
		return quant;
	}

	public String store() {
		return (this.title + "\t" + this.price + "\t" + this.quant + "\t" + this.sku);
	}

}
