package model;

public class Country {

	private String code;
	private String name;
	private String continent;
	private String region;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContinent() {
		return continent;
	}
	public void setContinent(String continent) {
		this.continent = continent;
	}
	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}

	public String toString(){
		return "Name:"+name+" Code:"+code+" Continent:"+continent+" Region:"+region;
	}
}
