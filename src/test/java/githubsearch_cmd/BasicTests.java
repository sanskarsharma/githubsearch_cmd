package githubsearch_cmd;

import static org.junit.Assert.*;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class BasicTests {

	@Test
	public void testQueryStringPreparation() {
		assertEquals(GitSearch.prepareQueryString("hello  world      kbye"), "hello+world+kbye");
	}
	
	@Test
	public void testGetResultList() {
		
		GitSearch gitsearch = new GitSearch(GitSearch.getDocument("minion+url"));		
		assertEquals(gitsearch.getResultList().size(), 6);
		
		gitsearch = new GitSearch(GitSearch.getDocument("apache"));		
		assertEquals(gitsearch.getResultList().size(), 10);
		
		gitsearch = new GitSearch(GitSearch.getDocument("wrgwgwgwfgf wfwgweq13414f3dfq3t"));		
		assertEquals(gitsearch.getResultList().size(), 0);
		
	}
	

}
