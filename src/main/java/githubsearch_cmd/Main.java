package githubsearch_cmd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.StringTokenizer;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		ArrayList<SearchResultModel> list = null;
		SearchResultModel model = null;
		
		String input = "n";
		Scanner x = new Scanner(System.in);
		
		System.out.println("Welcome, This is a command line program for showing github search results. Please press <enter> to continue");

		while(true){
			
			if( list != null){
			System.out.println();
			System.out.println("Enter 1-"+list.size()+" for details, 'n' for a new search, and 'q' to quit program ");
			input = x.next();
			}
			
			if(input.equals("n")){
				x.nextLine();  
				System.out.println("Enter search term(s)");
				String query = prepareQueryString(x.nextLine());
				
				Document doc = null;
				try {
					
					doc = Jsoup.connect("https://github.com/search?q="+query).get();
					
				} catch (IOException e) {
					
					System.out.println("Could not connect to the internet, Please check your internet connection and try again");
					//e.printStackTrace();
					continue;
				}
				
				list = getAsList(doc);
				if( list!=null && list.size()>0){
					showResults(list);
				}
				else{
					System.out.println("Your search returned zero results, press <enter> for new search");
				}
				
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
					System.out.println(model.getRepo_name());
					System.out.println(model.getRepo_owner());
					System.out.println(model.getRepo_url());
					System.out.println(model.getRepo_desc());
					
				}else{
					System.out.println("Invalid serial number, Please enter number from 1 to "+list.size());
				}
				

			}else{
				System.out.println("Invalid choice, please try again");
			}
			
			
		}
		
		
		
		
	}
	
	private static void showResults(ArrayList<SearchResultModel> list){
		
		int i = 0;
		while(i<list.size()){
			System.out.println((i+1)+ ". "+list.get(i).repo_name);
			i++;
		}
		
		
	}
	
	private static ArrayList<SearchResultModel> getAsList(Document doc){
				
				
		ArrayList<SearchResultModel> list = new ArrayList<SearchResultModel>();
		
		Element element = doc.getElementsByClass("repo-list").first();
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
			
			String arr[] = repo_sign.text().split("/");
			
			
			SearchResultModel model = new SearchResultModel();
			model.setRepo_name(arr[1]);
			model.setRepo_owner(arr[0]);
			model.setRepo_url("https://github.com"+repo_sign.attr("href"));
			model.setRepo_desc(text);
			
			list.add(model);
			
		}
		
		return list;
		
	}
	
	private static String prepareQueryString(String query){
		
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
