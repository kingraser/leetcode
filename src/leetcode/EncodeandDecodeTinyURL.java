package leetcode;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class EncodeandDecodeTinyURL {

  /*
  TinyURL is a URL shortening service where you enter a URL such as https://leetcode.com/problems/design-tinyurl and it returns a short URL such as http://tinyurl.com/4e9iAk.
  Design the encode and decode methods for the TinyURL service. 
  There is no restriction on how your encode/decode algorithm should work. 
  You just need to ensure that a URL can be encoded to a tiny URL and the tiny URL can be decoded to the original URL.
  */

  @Test
  public void test() {
    Coder coder = new Coder();
    String url = "hello world";
    assertEquals(url, coder.decode(coder.encode(url)));
  }

  public class Coder {
    List<String> urlList = new ArrayList<>();

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
      urlList.add(longUrl);
      return Integer.toString(urlList.size() - 1);
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
      return urlList.get(Integer.parseInt(shortUrl));
    }
  }
}
