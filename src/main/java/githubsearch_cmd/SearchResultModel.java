package githubsearch_cmd;

public class SearchResultModel {
	
	private String repo_name;
	private String repo_owner;
	private String repo_desc;
	private String repo_url;
	private String last_updated;
	
	public void print(){
		if(this.repo_name != null){
			System.out.println("Repo name : " +this.repo_name);
		}
		if(this.repo_owner != null){
			System.out.println("Repo owner : " +this.repo_owner);
		}
		if(this.repo_desc != null){
			System.out.println("Repo description : " +this.repo_desc);
		}
		if(this.repo_url != null){
			System.out.println("Repo url : " +this.repo_url);
		}
		if(this.last_updated != null){
			System.out.println("Repo last updated : " +this.last_updated);
		}
	}
	
	
	
	public String getRepo_name() {
		return repo_name;
	}
	public void setRepo_name(String repo_name) {
		this.repo_name = repo_name;
	}
	public String getRepo_owner() {
		return repo_owner;
	}
	public void setRepo_owner(String repo_owner) {
		this.repo_owner = repo_owner;
	}
	public String getRepo_desc() {
		return repo_desc;
	}
	public void setRepo_desc(String repo_desc) {
		this.repo_desc = repo_desc;
	}
	public String getRepo_url() {
		return repo_url;
	}
	public void setRepo_url(String repo_url) {
		this.repo_url = repo_url;
	}
	public String getLast_updated() {
		return last_updated;
	}
	public void setLast_updated(String last_updated) {
		this.last_updated = last_updated;
	}
	
	

}
