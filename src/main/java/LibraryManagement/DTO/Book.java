package LibraryManagement.DTO;

public class Book {
	private int bid;
	private String bname;
	private double price;
	private int aid;

	public Book(int bid, String bname, double price, int aid) {
		this.bid = bid;
		this.bname = bname;
		this.price = price;
		this.aid = aid;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", bname=" + bname + ", price=" + price + ", aid=" + aid + "]";
	}

}
