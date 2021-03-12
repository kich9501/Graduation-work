package borrow;

public class PlayerDTO {
	private String no;
	private String name;
	private String debut;
	private String backnum;
	private String height;
	private String weight;
	private String tota;
	private String position;
	private String team;
	
	public PlayerDTO() {

	}
	
	public PlayerDTO(String no, String name, String backnum, String debut, String height, String weight, String tota, String position, String team) {
		this.no = no;
		this.name = name;
		this.backnum = backnum;
		this.debut = debut;
		this.height = height;
		this.weight = weight;
		this.tota = tota;
		this.position = position;
		this.team = team;
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
	
	public String getBacknum() {
		return backnum;
	}

	public void setBacknum(String backnum) {
		this.backnum = backnum;
	}

	public String getDebut() {
		return debut;
	}

	public void setDebut(String debut) {
		this.debut = debut;
	}

	public String getHeight() {
		return height;
	}

	public void setHeight(String height) {
		this.height = height;
	}

	public String getWeight() {
		return weight;
	}

	public void setWeight(String weight) {
		this.weight = weight;
	}

	public String getTota() {
		return tota;
	}

	public void setTota(String tota) {
		this.tota = tota;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}	
	
}
