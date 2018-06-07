package githubsearch_cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class GitSearch {
	
	private Document document;

	private GitSearch(){
		
	}
	public GitSearch(Document doc){
		this.document = doc;
		
	}
		
	
	
	public static Document getDocument(String query){
					
		Document doc = null;
		try {
			doc = Jsoup.connect("https://github.com/search?q="+query).get();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return doc;		
		
	}
	
	public ArrayList<SearchResultModel> getResultList(){
		
		
		ArrayList<SearchResultModel> list = new ArrayList<SearchResultModel>();
		
		Element element = this.document.getElementsByClass("repo-list").first();
		if( element == null){
			return null;
		}
		Elements elements = element.getElementsByClass("repo-list-item");
		
		for (Element elem : elements) {
						
			Element e = elem.getElementsByClass("col-8").first();
			Element e1 = e.getElementsByTag("h3").first();
			Element repo_sign = e1.getElementsByTag("a").first();
			
			Element one_line = e.getElementsByTag("p").first();
			String text = one_line.text();
			
			Element time_cont = e.getElementsByClass("d-flex").first();
			Element relative_time = time_cont.getElementsByTag("relative-time").first();
			String time_str = relative_time.text();
			
			String arr[] = repo_sign.text().split("/");
			
			
			SearchResultModel model = new SearchResultModel();
			model.setRepo_name(arr[1]);
			model.setRepo_owner(arr[0]);
			model.setRepo_url("https://github.com"+repo_sign.attr("href"));
			model.setRepo_desc(text);
			model.setLast_updated(time_str);
			
			list.add(model);
			
		}
		
		return list;
		
	}
	
	
	
	public static String prepareQueryString(String query){
		
		StringTokenizer st = new StringTokenizer(query);
		StringBuilder sb = new StringBuilder("");
		while (st.hasMoreTokens()) {
			sb.append(st.nextToken() + "+");
		}
		
		query = sb.substring(0, sb.length()-1).toString();

		System.out.println(query);
		
		return query;
		
	}
	
	
}
