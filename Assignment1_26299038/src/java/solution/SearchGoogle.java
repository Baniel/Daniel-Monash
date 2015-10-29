/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solution;

import org.netbeans.saas.RestConnection;
import org.netbeans.saas.RestResponse;

/**
 *
 * @author daniel
 */
public class SearchGoogle 

{
    public String search(String query) throws Exception
    {
    String API_key = "AIzaSyBoVGChzXf-Ekia5Kfc-pL-wRpaSuzFyBc";
    String SEARCH_ID_cx = "009674152720278514225:hogrraozmr4";
    query  = query.replaceAll(" ", "%20");
    String[][] pathParams = new String[][]{};
    String[][] queryParams = new String[][]{};
    //need to get the value for query variable from the user
    RestConnection conn = new RestConnection("https://www.googleapis.com/customsearch/v1?key=" + API_key + "&cx=" + SEARCH_ID_cx + "&q=" + query + "&num=5", pathParams, queryParams);
    RestResponse response = conn.get();
    String strResponse = response.getDataAsString();
    
   
   
    return strResponse;
    
    }
}
