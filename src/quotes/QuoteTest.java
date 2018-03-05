package quotes;

import static org.junit.Assert.*;

import org.junit.*;

public class QuoteTest {
	AddXMLQuote adder;
	QuoteSaxParser parser;
	QuoteList q;
	
	@Before
	public void setUp(){
		adder = new AddXMLQuote();
	}
	@Test
	public void test_add_withKeyword() {
		boolean exceptionThrown = false;
		try {
			adder.addQuoteToXML("Tester Author", "testing quote", "TestAdd");
			parser = new QuoteSaxParser("src/quotes/quotes.xml");
			q = parser.getQuoteList();
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);
		assertEquals("Quote {author=\'Tester Author\', quoteText=\'testing quote, quoteKeyword=\'TestAdd\'}"
				, q.getQuote(q.getSize()-1).toString());
	}
	
	@Test
	public void test_search_withKeyword(){
		boolean exceptionThrown = false;
		QuoteList outputFromSearch = new QuoteList();
		try {
			adder.addQuoteToXML("Tester Author2", "second testing quote", "TestSearch");
			parser = new QuoteSaxParser("src/quotes/quotes.xml");
			q = parser.getQuoteList();
			outputFromSearch = q.search("TestSearch", 3);
		} catch (Exception e) {
			exceptionThrown = true;
		}
		assertFalse(exceptionThrown);
		assertEquals(1, outputFromSearch.getSize());
		assertEquals("Quote {author=\'Tester Author2\', quoteText=\'second testing quote, quoteKeyword=\'TestSearch\'}"
				, outputFromSearch.getQuote(outputFromSearch.getSize()-1).toString());
	}

}
