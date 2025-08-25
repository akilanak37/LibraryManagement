package LibraryManagement.DTO;

public class Author {
	private int aid;
	private String name;
	private String address;

	public Author(int aid, String name, String address) {
		this.aid = aid;
		this.name = name;
		this.address = address;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Author [aid=" + aid + ", name=" + name + ", address=" + address + "]";
	}

}
