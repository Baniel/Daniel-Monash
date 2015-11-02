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

    <link href="css/FrequentlyAskedAuestionsStyle.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>




    <h1>Frequently Asked Questions</h1>

    <h2>Predator Paragon achievemet help</h2>
    <h4>this is the only hard achievment i need the rest i can do easy
if you want this achievemet to or just want to help
firend me and i guess who ever posts here
and then 8 of us can all help each other get it</h4>

    <h2>DIALOGOS BATMAN ARKHAM ORIGINS</h2>
    <h4>tengo problemas con respecto a dialogos del juego, lo instale pero los dialogos son voces de mexico y yo vivo en españa. quiero encontrar una solucion para que el juego tenga dialogos de España. gracias</h4>

    <h2>Bug Batman en caida continua</h2>
    <h4>Estaba en la sub mision de bird y entré a una habitacion que estaba con bug grafico, sali del juego volvi a entrar para ver si se arrreglaba, pero al final quedó peor, aparece batman cayendo incluso puedo planear y presionando tab puedo ver que me muevo por el mapa, pero no hay nadas, solo fondo negro y batman.</h4>
    
    <h2>enigma data packs getting boring...</h2>
    <h4>what can i do in the meantime? can i progress the story any other way than trying to light up all these damn lights? the one under the bridge with 6 lights is driving me nuts. 
i already went into enigmas building and it was locked so i left but all i see are data/informants.
is there ANYTHING else to do?</h4>

</asp:Content>
