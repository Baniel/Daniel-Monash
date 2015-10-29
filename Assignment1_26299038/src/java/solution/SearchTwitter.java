/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;



import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author daniel
 */
public class SearchTwitter {
    public String search(String query) throws Exception
    {
    OAuthConsumer consumer = new DefaultOAuthConsumer("w4vtzyhz0BGlSZ9ETT7IPmF5a", "TJ8tiZMqzHrX7PqNyOQOlGThaR5pdkk6HR0eRV8gsZ9sqcrqfE");
    consumer.setTokenWithSecret ("3092694538-C1FTWmk0VMKLrHtq2JOrwSfVkRsxtngRVXCWhSD", "PCul2pz2GDMc9leOOljJJFRiSs0CcGPVZVc6YHiO5wBrD");
    //need to get the value for query variable from the user
    query  = query.replaceAll(" ", "%20");
    String URL = "https://api.twitter.com/1.1/search/tweets.json?q=" + query;

    System.out.println ( "--->" + consumer.sign(URL));
    String[][] pathParams = new String[][]{};
    String[][] queryParams = new String[][]{};
    RestConnection conn = new RestConnection(consumer.sign(URL), pathParams,queryParams);
    RestResponse response = conn.get();
    String strResponse = response.getDataAsString();
    return strResponse;
    }
}
