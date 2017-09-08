package cn.qhjys.mall.vo;

public class AdVo {
	private String link;
	private String image;
	
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public AdVo(String link, String image) {
		super();
		this.link = link;
		this.image = image;
	}
	public AdVo() {
		super();
	}
	
	
}
