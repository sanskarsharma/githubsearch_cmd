package githubsearch_cmd;

import java.io.IOException;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Enter search term(s)");
		Scanner x = new Scanner(System.in);
		String text = x.nextLine();
		
		String querystr = "";
		for (String str : text.trim().split(" ")) {
			querystr = querystr + str + "+";
		}
		querystr = querystr.substring(0, querystr.length()-1);

		System.out.println(querystr);

		Document doc = null;
		try {
			
			doc = Jsoup.connect("https://github.com/search?q="+querystr).get();
			
		} catch (IOException e) {

			e.printStackTrace();
		}
		
//		System.out.println(doc.html());
		
		Element element = doc.getElementsByClass("repo-list").first();
		
//		System.out.println(element.html());
		
		int i = 1;
		for (Element elem : element.getElementsByClass("repo-list-item")) {
			
//			System.out.println(elem.getElementsByClass("col-8"));
			
			Element e = elem.getElementsByClass("col-8").first();
			Element e1 = e.getElementsByTag("h3").first();
			String repo_sign = e1.getElementsByTag("a").first().text();
			
			System.out.println(i+ ". "+repo_sign);
			i++;

			
			
		}
		
	}

}
