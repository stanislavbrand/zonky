package cz.zonky.demo.model;



public class Photo extends AbstractEntity {

	private static final long serialVersionUID = 1L;

	private String name;
	private String url;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Override
	protected Object[] getBaseFields() {
		return new Object[]{name, url};
	}
}
