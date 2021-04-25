package tinkoff.qa.models;

import java.util.List;
import java.util.Objects;

public class Pet {
	private List<String> photoUrls;
	private String name;
	private int id;
	private Category category;
	private List<TagsItem> tags;
	private String status;

	public void setPhotoUrls(List<String> photoUrls){
		this.photoUrls = photoUrls;
	}

	public List<String> getPhotoUrls(){
		return photoUrls;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getName(){
		return name;
	}

	public void setId(int id){
		this.id = (int)(id);
	}

	public int getId(){
		return id;
	}

	public void setCategory(Category category){
		this.category = category;
	}

	public Category getCategory(){
		return category;
	}

	public void setTags(List<TagsItem> tags){
		this.tags = tags;
	}

	public List<TagsItem> getTags(){
		return tags;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Pet)) return false;
		Pet pet = (Pet) o;
		return id == pet.id && Objects.equals(photoUrls, pet.photoUrls) && Objects.equals(name, pet.name) && Objects.equals(category, pet.category) && Objects.equals(tags, pet.tags) && Objects.equals(status, pet.status);
	}

	@Override
	public int hashCode() {
		return Objects.hash(photoUrls, name, id, category, tags, status);
	}
}