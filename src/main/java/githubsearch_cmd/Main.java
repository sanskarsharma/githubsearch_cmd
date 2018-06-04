package githubsearch_cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ArrayList<SearchResultModel> list = null;
		SearchResultModel model = null;
		
		String input = "n";
		Scanner x = new Scanner(System.in);

		while(true){
			
			if( list != null){
			System.out.println();
			System.out.println("Enter 1-10 for details, 'n' for a new search, and 'q' to quit program ");
			input = x.next();
			}
			
			if(input.equals("n")){
				x.nextLine();
				System.out.println("Enter search term(s)");
				String query = x.nextLine();
				list = getAsList(prepareQueryString(query));
				
			}else if (input.equals("q")){
				
				break;
				
			}else if (Character.isDigit(input.charAt(0))){
				
				int index = 0;
				try{
					index = Integer.parseInt(input);
				} catch (Exception e) {
					System.out.println("Invalid Input");
					continue;
				}
				
				if(index > 0 && index <= list.size()){
					model = list.get(index-1);
				}
				System.out.println(model.getRepo_name());
				System.out.println(model.getRepo_owner());

			}
			
			
		}
		
		
		
		
	}
	
	private static ArrayList<SearchResultModel> getAsList(String query){
				
		Document doc = null;
		try {
			
			doc = Jsoup.connect("https://github.com/search?q="+query).get();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
		
		ArrayList<SearchResultModel> list = new ArrayList<SearchResultModel>();
		
		Element element = doc.getElementsByClass("repo-list").first();
		
		int i = 1;
		for (Element elem : element.getElementsByClass("repo-list-item")) {
						
			Element e = elem.getElementsByClass("col-8").first();
			Element e1 = e.getElementsByTag("h3").first();
			Element repo_sign = e1.getElementsByTag("a").first();
			
			String arr[] = repo_sign.text().split("/");
			
			
			SearchResultModel model = new SearchResultModel();
			model.setRepo_name(arr[1]);
			model.setRepo_owner(arr[0]);
			
			list.add(model);
			
			System.out.println(i+ ". "+ model.getRepo_name());
			i++;

			
		}
		
		
		
		

		return list;
	}
	
	private static String prepareQueryString(String query){
		
		StringBuilder sb = new StringBuilder("");
		for (String str : query.trim().split(" ")) {
			sb.append(str.trim() + "+");
		}
		
		query = sb.substring(0, sb.length()-1).toString();

		System.out.println(query);
		
		return query;
		
	}
	
	

}
