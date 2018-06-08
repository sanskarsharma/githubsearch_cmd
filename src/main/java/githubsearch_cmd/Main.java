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
		
		Scanner x = new Scanner(System.in);
		String input = "n";

		System.out.println("Welcome, This is a command line program for showing github search results. Please press <enter> to continue");

		while(true){
			
			if( list != null && list.size()>0){
			System.out.println();
			System.out.println("Enter 1-"+list.size()+" for details, 'n' for a new search, and 'q' to quit program ");
			input = x.next();
			}
			
			if(input.equals("n")){
				x.nextLine();  
				System.out.println("Enter search term(s)");
				String query = GitSearch.prepareQueryString(x.nextLine());
				System.out.println("getting results ... ");
				Document doc = GitSearch.getDocument(query);
				if(doc == null){
					System.out.println("Could not connect to the internet, Please check your internet connection and try again");
					continue;
				}
				GitSearch gitsearch = new GitSearch(doc);
				list = gitsearch.getResultList();
				
				if(list.size()>0){
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
					list.get(index-1).print();					
				}else{
					System.out.println("Invalid serial number, Please enter number from 1 to "+list.size());
				}
				

			}else{
				System.out.println("Invalid choice, please try again");
			}
			
			
		}
			
			
	}
	
	private static void showResults(ArrayList<SearchResultModel> list){
		
		System.out.println("Search results : ")
		int i = 0;
		while(i<list.size()){
			SearchResultModel model = list.get(i);
			System.out.println((i+1)+ ". "+model.getRepo_name() + " (by "+model.getRepo_owner()+")");
			i++;
		}
		
		
	}

}
