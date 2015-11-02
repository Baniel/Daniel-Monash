<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" StylesheetTheme="default" %>


<asp:Content ID="SideBar" ContentPlaceHolderID="LeftSideBar" runat="server">




    <link href="../css/MenuStyleSheet.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />


    <div class="menu">
        <asp:Menu StaticDisplayLevels="2" ID="Menu1" runat="server" DataSourceID="SiteMapDataSource1"
            StaticSubMenuIndent="25" ForeColor="Black" Width="100%" Height="300px">
            <StaticMenuItemStyle CssClass="MenuStaItm" />
            <DynamicHoverStyle CssClass="MenuDynHov" />
            <DynamicMenuItemStyle CssClass="MenuDynItm" />
            <StaticHoverStyle CssClass="MenuStaHov" />
        </asp:Menu>
         <asp:HyperLink runat="server" NavigateUrl="~/locked/Logout.aspx"  ForeColor="WhiteSmoke">
        <h2>Log Out</h2>
        </asp:HyperLink>
    </div>

    <asp:Image ID="Image1" runat="server" SkinID="logo" />

</asp:Content>

<asp:Content ID="Content" ContentPlaceHolderID="Content" runat="server">

    <link href="css/DocumentationStyle.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>

    <center>

           <h1>Documentation</h1>
           <hr />
           <div class="documentation">

           <h4>Author Name : YANDONG ZHANG</h4>
           <h4>Student ID : 26299038</h4>
           <h4>FIT5032 Internet applications development</h4>
           <h4>Monash University,The Caufield School of Information Technology</h4>
           <h4>Assignment1</h4>
           <h4>Date of Submission : 10 / Sep / 2015</h4>
           <h4>Tutor: Julian</h4>
            <asp:HyperLink ID="EmailLink" runat="server" NavigateUrl="http://yzha860@student.monash.edu">
                 <h4>Email : yzha860@student.monash.edu</h4>
            </asp:HyperLink>
          
           <asp:HyperLink ID="AssignmentSpecification" runat="server" NavigateUrl="http://moodle.vle.monash.edu/mod/page/view.php?id=2639809">
               <h4>Assignment Specification</h4>
           </asp:HyperLink>  

           </div>
          
        <table>
           <tr> 
               <td>
                   <a href="DisplayCode.aspx?filename=../css/DocumentationStyle.css" target="_blank">
                       <asp:Image ID="CSS" runat="server" ImageUrl="~/images/ButtonImages/codebuttonCSS.jpg" />
                   </a>
               </td>
               <td>
                   <a href="DisplayCode.aspx?filename=../App_Themes/default/default.skin" target="_blank">
                       <asp:Image ID="SKIN" runat="server" ImageUrl="~/images/ButtonImages/codebuttonSkin.jpg" />
                   </a>
               </td>

           </tr>
        </table>

    </center>





</asp:Content>
