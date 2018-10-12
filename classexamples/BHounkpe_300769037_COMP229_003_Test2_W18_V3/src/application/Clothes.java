package application;

public class Clothes {
	
	String category;
	String color;
	Integer clothesSize;
	String material;
	Integer Id;
	Clothes(){
		
	}
	Clothes(Integer id,String category,String color,Integer size,String material){
		this.category=category;
		this.color=color;
		this.clothesSize=size;
		this.material=material;
		this.Id=id;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getClothesSize() {
		return clothesSize;
	}
	public void setClothesSize(Integer clothesSize) {
		this.clothesSize = clothesSize;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	
}
