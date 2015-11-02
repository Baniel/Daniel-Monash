<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" StylesheetTheme="default" %>
<%@ Import Namespace="System.IO" %>


<asp:Content ID="SideBar" ContentPlaceHolderID="LeftSideBar" runat="server">




    <link href="css/MenuStyleSheet.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />


    <div class="menu">
        <asp:Menu StaticDisplayLevels="2" ID="Menu1" runat="server" DataSourceID="SiteMapDataSource1"
            StaticSubMenuIndent="25" ForeColor="Black" Width="100%" Height="450px" BackColor="WhiteSmoke">
            <StaticMenuItemStyle CssClass="MenuStaItm" />
            <DynamicHoverStyle CssClass="MenuDynHov" />
            <DynamicMenuItemStyle CssClass="MenuDynItm" />
            <StaticHoverStyle CssClass="MenuStaHov" />
        </asp:Menu>

    </div>


    <asp:Image ID="Image1" runat="server" SkinID="logo" />


</asp:Content>




<asp:Content ID="Content" ContentPlaceHolderID="Content" runat="server">

  

    <link href="css/defaultStyle.css" type="text/css" rel="stylesheet" />

    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>


  <center>

    <h1>Batman Arkham Origins</h1>
        <hr />
        <h2> Game Sale NOW !!!! PC,PS3,XBOX</h2>
   
    
 
 
         <asp:AdRotator ID="AdRotator1" runat="server" AdvertisementFile="~/advertisements.xml" />
  
        
        <hr />
       
         <asp:HyperLink runat="server" NavigateUrl="http://users.monash.edu.au/~sgrose/msh/disclaimer.htm">  
                 <h2>Standard Monash Course Disclaimer</h2><p />
        </asp:HyperLink>

        <h2>E-mail:<asp:HyperLink runat="server" NavigateUrl="http://yzha860@student.monash.edu">Author</asp:HyperLink></h2>

        <h2>E-mail:<asp:HyperLink runat="server" NavigateUrl="http://yzha860@student.monash.edu">Webmaster</asp:HyperLink></h2><p/><p/>
        
        <h2>Copyright 2015 Warner Bros. Entertainment, Inc </h2>                
       

      <asp:DataList ID="dlFiles" runat="server" Font-Names="Arial">



      </asp:DataList>  





        <asp:AdRotator ID="AdRotator2" runat="server" AdvertisementFile="~/adverstisements2.xml" />



</center>





</asp:Content>
