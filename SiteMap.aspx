<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" StylesheetTheme="default" %>


<asp:Content ID="SideBar" ContentPlaceHolderID="LeftSideBar" runat="server">




    <link href="css/MenuStyleSheet.css" type="text/css" rel="stylesheet" />
    


    <div class="menu">
        <asp:Menu StaticDisplayLevels="3" ID="Menu1" runat="server" DataSourceID="SiteMapDataSource1"
            StaticSubMenuIndent="25" ForeColor="Black" Width="100%" Height="300px" >
           <StaticMenuItemStyle CssClass="MenuStaItm" />
            <DynamicHoverStyle CssClass="MenuDynHov" />
            <DynamicMenuItemStyle CssClass="MenuDynItm" />
            <StaticHoverStyle CssClass="MenuStaHov" />
        </asp:Menu>
    </div>

    <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />


    <asp:Image ID="Image1" runat="server" SkinID="logo" />

</asp:Content>

<asp:Content ID="Content" ContentPlaceHolderID="Content" runat="server">


    <link href="css/SiteMapStyle.css" type="text/css" rel="stylesheet" />
      <asp:SiteMapPath ID="SiteMapPath1" runat="server">
    <PathSeparatorTemplate> 
      <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png"  Width="60px" Height="30px"/>
    </PathSeparatorTemplate>
  </asp:SiteMapPath>  
   
        
        <div class="SiteMapContent">
        <h1>Site Map</h1>
        <asp:SiteMapDataSource ID="SiteMapDataSource2" runat="server" />

        <asp:TreeView ID="TreeView1" runat="server" DataSourceID="SiteMapDataSource2" ShowLines="True"  CssClass="treeView" />

        </div>

          <br />
           <a href="DisplayCode.aspx?filename=SiteMap.aspx" target="_blank">
               <asp:Image ID="Site_Map" runat="server" ImageUrl="~/images/ButtonImages/codebuttonSiteMap.jpg" />

           </a>



</asp:Content>
