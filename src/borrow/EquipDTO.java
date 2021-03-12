package borrow;

public class EquipDTO {
	private String no;
	private String name;
	private String manuf;
	private String textu;
	private String year;
	
	public EquipDTO() {
		
	}
	
	public EquipDTO(String no, String name, String manuf, String textu, String year) {
		this.no = no;
		this.name = name;
		this.manuf = manuf;
		this.textu = textu;
		this.year = year;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getManuf() {
		return manuf;
	}

	public void setManuf(String manuf) {
		this.manuf = manuf;
	}

	public String getTextu() {
		return textu;
	}

	public void setTextu(String textu) {
		this.textu = textu;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}	
	
}
