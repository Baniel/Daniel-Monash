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

    <link href="css/HistoryStyle.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>




    <h1>History</h1>
    <h4>Founded: April 4, 1923

WARNER BROS. ENTERTAINMENT, a fully integrated, broad-based entertainment company, is a global leader in all forms of entertainment and their related businesses across all current and emerging media and platforms. The fully integrated, broad-based company stands at the forefront of every aspect of the entertainment industry from feature film, television and home entertainment production and worldwide distribution to DVD and Blu-ray, digital distribution, animation, comic books, product and brand licensing and broadcasting.

In addition to its long-standing position as the industry’s preeminent creator and distributor of feature films, television programs, animation, video and DVD, Warner Bros. Studios has also become one of the foremost authorities on utilizing licensing and merchandising to grow and reinforce its brands, on pioneering new forms of distribution, and on marshaling its vast creative and business resources to build world-renowned entertainment franchises that become appreciating assets in its unrivaled library.</h4>

    <h4>
        One of the most respected, diversified and successful motion picture and television studios in the world, Warner Bros. Studios began when the brothers Warner (Albert, Sam, Harry and Jack L.) incorporated their fledgling movie company on April 4, 1923. In 1927, the release of the world’s first “talkie,” (synchronized-sound feature film), The Jazz Singer, set a character and tone of innovation and influence that would become synonymous with the name Warner Bros. And—as Al Jolson foretold in this milestone movie—“you ain’t heard nothin’ yet!”

Since those early days, Warner Bros. Studios has amassed an impressive legacy based on world-class quality entertainment and technological foresight and created a diversified entertainment company with an unparalleled depth and breadth. Its unmatched consistency and success is built on a foundation of stable management throughout its history (especially by entertainment industry standards), long-term creative relationships with many of the world’s leading talent, and an unwavering dedication to excellence.
    </h4>

    <asp:Image ID="historyPicture" runat="server" ImageUrl="https://upload.wikimedia.org/wikipedia/commons/thumb/0/04/Warner_studios_office_building_burbank.jpg/250px-Warner_studios_office_building_burbank.jpg" />
  

</asp:Content>
