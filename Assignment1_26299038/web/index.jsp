<%-- 
    Document   : Search
    Created on : 2015-3-19, 16:58:53
    Author     : Daniel
--%>

<%@page import="Client.NewJerseyClient"%>
<%@page import="java.util.Locale.Category"%>
<%@page import="javax.jdo.Query"%>
<%@page import="java.net.URL"%>
<%@page import="solution.SearchYouTube"%>
<%@page import="solution.SearchGoogle"%>
<%@page import="solution.SearchTwitter"%>
<%@page import="org.codehaus.jettison.json.JSONObject"%>
<%@page import="org.codehaus.jettison.json.JSONArray"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Family  Movie Search Engine</title>
        <style type="text/css">

          
        </style>
    </head>
    <body>
        
    <center>
        <h3>Family Movie Recommender</h3>
    </center>
   
        
    
        <form action="" method="get" align="center">              
               <center>
                    Movie:
                    <input type="text" name="query" placeholder="Enter your keyword" style="width:449px">
               </center>

        </form> 
    
        <%
    //get the keyword from the request using getParameter method

        if (request.getParameter("query") != null && !request.getParameter("query").isEmpty() && !request.getParameter("query").trim().isEmpty() ) 
        {
        
           
            
            
            
            String query = request.getParameter("query");
            
            
            
            
            
            
            SearchTwitter searchTwitter = new SearchTwitter();
            SearchGoogle searchGoogle = new SearchGoogle();
            SearchYouTube searchYouTube = new SearchYouTube();

        // GooGle API
            // call your search method
            String twitterJson = searchTwitter.search(query);
            String googleJson = searchGoogle.search(query);

            //Identify the movie weather is existed.
            if (googleJson.contains("item") == false) {
                out.print("<br></br>");
                out.print("<br></br>");
                out.print("<center>");
                out.print("<img src='images/sorry.jpg'>");
                out.print("Oh my god !!!,we don't have this movie");
                out.print("<br></br>");
                out.print("Please trust us,we will fix it");
                out.print("</center>");
            } else {
                JSONObject googleResult = new JSONObject(googleJson);
                JSONArray googleStatuses = googleResult.getJSONArray("items");
                JSONObject jasonObject = googleStatuses.getJSONObject(0);
                JSONObject pagemap = jasonObject.getJSONObject("pagemap");

                if (pagemap.toString().contains("movie") == false) {
                    out.print("<br></br>");
                    out.print("<center>");
                    out.print("<img src='images/sorry.jpg'>");
                    out.print("Oh my god !!!,we don't have this movie");
                    out.print("<br></br>");
                    out.print("Please trust us,we will fix it");
                    out.print("</center>");
                } else {
                    JSONArray movie = pagemap.getJSONArray("movie");

                    JSONObject movieObject = movie.getJSONObject(0);

                    String contentrating = movieObject.getString("contentrating");

                    if (contentrating.contains("PG") == true || contentrating.contains("G") == true || contentrating.contains("M") == true) {

        //Google Search
                    

        //Get title
                        out.println("<div id='topArea'>");
                        String title = jasonObject.getString("title");
                        out.println("<br></br>");
                        out.println("&nbsp;");
                        out.println("&nbsp;");
                        out.println(title.replace("- IMDb", "  "));
                    
                        //Get img
                   %>
                   
                   
                   <div id="header" sytle="border:1px solid;padding:20px">
                   <div id="image" sytle="align=left;hspace=10px">
                   
                   <%
                        String image = movieObject.getString("image");
                        out.println("<img src = '" + image + "width = '300' height = '200' align='left' hspace='10px'>");
                    
                    %>
                   </div>
                   
                        
                        
                   <div id="description" style="font-size:10px">     
                        <%

                        //Get Descreption
                        out.println("<table class='detail'>");
                        String description = movieObject.getString("description");
                        out.println("<h2>Details</h2>");
                        out.println("<p>");
                        
         
                        out.println("Storyline:  ");
                        
                        
                        out.println(description.replaceAll("\\.\\.\\.", "."));

                        out.println("<br></br>");
                        JSONArray moviereview = pagemap.getJSONArray("moviereview");
                        JSONObject moviereviewObject = moviereview.getJSONObject(0);
                        String summary = moviereviewObject.getString("summary");
                        out.println(summary.replaceAll("\\.\\.\\.", "."));
                        
                        out.println("</div>"); // topArea

                        //Get Director
                        out.println("<br></br>");
                        String director = moviereviewObject.getString("directed_by");
                        out.println("Director: ");
                        out.println(director);

                        //Get Stars
                        out.println("Stars: ");
                        JSONArray person = pagemap.getJSONArray("person");
                        for (int i = 0; i < person.length(); i++) {
                            JSONObject personObject = person.getJSONObject(i);
                            String name = personObject.getString("name");
                            out.println(name + ",");

                        }
                        out.println("<br></br>");


                        JSONObject twitterResult = new JSONObject(twitterJson);
                        JSONArray twitterStatuses = twitterResult.getJSONArray("statuses");
                    

                        //Get Release Date:
                        
                        out.println("Release Date: ");
                        String datepublished = movieObject.getString("datepublished");
                        out.println(datepublished);

                       
                        

                        //filter rating movie
                        out.println("<br></br>");
                        String rating = movieObject.getString("contentrating");
                        out.print("Rating: ");
                        out.print(rating);
                        out.print("</table>");
                        out.print("</p>");

                        %>
                   </div>
                        
                   </div>
                   
                   
                        <%
                          out.println("<hr />");
                        %>
                        
                        <div id="mainbody">
                        <div id="video" style="float:right;margin: 10px;padding: 10px" >
                        <%
                        //Get official trailer   
                        out.print(searchYouTube.searchVideo(query));
                        out.println("</iframe>");
                        %>
                        </div>
                        
                        
                        
                        <div id="review" style="font-size: 10px; ">
                        <%
 
                       // User Review
                        NewJerseyClient client = new NewJerseyClient();

                        String number = client.getAvg(query);

                        if (number.equals("null")) {
                            out.println("<h1> Sorry,the database of family Movie System don't have ReviewUser about this movie</h1>");
                        } else {
                       
                            out.println("<table class='review'>");
                            out.println("<h2>User Reviews<h2>");

                            //draw star
                            
                            String starAvg = client.getAvg(query);
                            float star = Float.parseFloat(starAvg);
   
                            //star 0
                            if (star == 0)
                            {
                                 out.println("<image src='images/empty start small.JPG'>"
                                            + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //star 0.1~0.9
                            if (star > 0 && star <1.0)
                            {
                                 out.println( "<image src='images/half start small.JPG'>"
                                            + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //Star 1
                            if (star == 1)
                            {
                                   out.println("<image src='images/Gold Start small.JPG'>"
                                            + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //Star 1.1~1.9
                            if (star > 1.0 && star < 2.0)
                            {
                                 out.println("<image src='images/Gold Start small.JPG'>"
                                     + "<image src='images/half start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //Star 2.0
                            if (star == 2.0)
                            {
                                 out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //Star 2.1~2.9
                            if(star >2.0 && star <3.0)
                            {
                                  out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/half start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            //Star 3.0
                            if (star == 3.0) {
                                out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                      + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }

                            // star 3.1~3.9
                            if (star > 3.0 && star < 4.0)
                            {
                                out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                     +   "<image src='images/half start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                        
                                );
                            }
                            //star 4.0
                            if (star == 4.0 ) {
                                out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                            
                            //star 4.1~4.9
                            if (star > 4.0 && star < 5.0)
                            {
                                 out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                             +   "<image src='images/half start small.JPG'>"
                                        + "<image src='images/empty start small.JPG'>"
                                );
                            }
                              
                            //star 5.0
                            if (star == 5.0)
                            {
                                 out.println("<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                        + "<image src='images/Gold Start small.JPG'>"
                                );
                            }
                            

                            %>
                            
                            <div id="detail">
                            <%
                            
                      //User Review Detail
                            out.println("Rating: "
                                    + client.getAvg(query)
                                    + "/5 from"
                                    + client.countUser(query)
                                    + " users  "
                                   
                                    + client.getDate(query)
                                    + " | by user : "
                                    + client.getUser(query)
                                    + " ("
                                    + client.getLocation(query)
                                    + ") "
                                    + "<br></br>"
                                    + "Comment :"
                                    + client.getComment(query)
                            );
                        }

                        out.println("</table>");
                        
                        %>
                        </div>
                        </div>
                        </div>
                        <%
                            out.println("<hr />");
                        %>  
                          
                        <div id="bottom">
                        <div id="twitter" style="font-size: 15px;clear:both">
                        <%
                        
                        /*
                         Get Twitter Comment
                         */
                        out.println("<h3>Twitter</h3>");
                        //Twitter API
                        out.println("<ol>");
                        String textStr = " ";
                        for (int i = 0; i < 5; i++) {
                            JSONObject twitterObject = twitterStatuses.getJSONObject(i);
                            String text = twitterObject.getString("text");
                            out.println("<li>");
                            
                            textStr = text.replaceAll("[\\.]+", ".");
                            textStr = textStr.replaceAll(" \\.\\.\\.", " ");
                           
                            out.println(textStr);

                            out.println("</li>");
                            out.print("<br></br>");
                        }
                        out.println("</ol>");
                        
                        
                        %>
                        
                        </div>
                        
                        </div>
                        <%

                    } else {
                        out.print("<center>");
                        out.print("<br></br>");
                        out.print("<img src='images/sorry2.jpg'  width='500' height='600'>");
                        out.print("Sorry,Please enter the movies within the famil rating only");
                        out.print("</center>");

                    }

                }

            }

        }
    %>
</center>   
</body>
</html>