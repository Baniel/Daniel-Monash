<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" StylesheetTheme="default" %>


<asp:Content ID="SideBar" ContentPlaceHolderID="LeftSideBar" runat="server">






    <link href="../css/MenuStyleSheet.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />


    <div class="menu">
        <asp:Menu StaticDisplayLevels="2" ID="Menu1" runat="server" DataSourceID="SiteMapDataSource1"
            StaticSubMenuIndent="25" ForeColor="Black" Width="100%" Height="300px" BackColor="WhiteSmoke">
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
    <link href="css/AboutUs.css" type="text/css" rel="stylesheet" />

    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>





    <center>
        <h1>About Us</h1>
      

        <h1>Warner Bros</h1>
        <h4>Warner Bros. Entertainment Inc. (commonly called Warner Bros., Warners, or simply WB) is an American entertainment company that produces film,
             television and music entertainment. As one of the major film studios, it is a subsidiary of Time Warner, with its headquarters in Burbank,
             California. Warner Bros. has several subsidiary companies, including Warner Bros. Pictures, Warner Bros. Interactive Entertainment,
             Warner Bros. Television, Warner Bros. Animation, Warner Home Video, New Line Cinema, Castle Rock Entertainment, DC Entertainment, 
            and the former The WB Television Network and Kids' WB. Warner Bros. owns half of The CW Television Network.</h4>

           <h4>
        Batman: Arkham Origins features an expanded Gotham City and introduces an original
         prequel storyline occurring several years before the events of Batman: Arkham Asylum
         and Batman: Arkham City. Taking place before the rise of Gotham City’s most dangerous
         villains and assassins, the game showcases a young, raw, unrefined Batman as he faces
         a defining moment in his early career as a crime fighter that sets his path to becoming
         the Dark Knight. As the story unfolds, witness identities being formed and key relationships being forged.
         </h4>


        <asp:Image ID="gameSale" runat="server" ImageUrl="https://batmanarkhamorigins.com/sites/default/files/_0007_usa.png" />


        <a href="DisplayCodeAboutUs.aspx" target="_blank">

            <asp:Image ID="About_Us" runat="server" ImageUrl="~/images/ButtonImages/codebuttonAboutus.jpg" />


        </a>

    </center>





</asp:Content>
