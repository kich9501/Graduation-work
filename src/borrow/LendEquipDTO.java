package borrow;

public class LendEquipDTO {
	private String no;
	private String bno;
	private String mno;
	private long cdate;
	private int days;
	private String name;
	
	public LendEquipDTO() {
		
	}
	
	public LendEquipDTO(String no, String bno, String mno, long cdate, int days, String name) {
		this.no = no;
		this.bno = bno;
		this.mno = mno;
		this.cdate = cdate;
		this.days = days;
		this.name = name;

	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getMno() {
		return mno;
	}

	public void setMno(String mno) {
		this.mno = mno;
	}

	public long getCdate() {
		return cdate;
	}

	public void setCdate(long cdate) {
		this.cdate = cdate;
	}

	public int getDays() {
		return days;
	}

	public void setDays(int days) {
		this.days = days;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
