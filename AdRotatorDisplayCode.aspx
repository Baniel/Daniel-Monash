<%@ Page Language="C#" Debug="true" %>

<%@ Import Namespace="System.IO" %>

<script language="C#" runat="server">

    void Page_Load()
    {
        //Home Page的源代码路径
        string filePath = Server.MapPath(".") + "/index.aspx";

        //XML文件的源代码路径
        string xmlfilePath = Server.MapPath(".") + "/advertisements.xml";

        FileName.Text = "Default.aspx";
        XMLFileName.Text = "advertisements.xml";

        FileInfo file = new FileInfo(filePath);
        FileInfo XMLFile = new FileInfo(xmlfilePath);

        Code.Text = ReadFile(filePath);
        XMLCode.Text = ReadFile(xmlfilePath);
    }


    private string ReadFile(string filePath)
    {
        string fileOutput = "";
        try
        {
            StreamReader FileReader = new StreamReader(filePath);

            while (FileReader.Peek() > -1)
            {

                fileOutput += FileReader.ReadLine().Replace("<", "&lt;").
                Replace(" ", "&nbsp;&nbsp;&nbsp;&nbsp;")
                                     + "<br />";
            }
            FileReader.Close();
        }
        catch (FileNotFoundException e)
        {
            fileOutput = e.Message;
        }
        return fileOutput;
    }

</script>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
    <link rel="stylesheet" href="css/DisplayCodeStyle.css" type="text/css">
</head>
<body>
    <form id="form1" runat="server">
  

        <h1 class="pageHeader">Source Code</h1>

        <h2> <asp:Label ID="FileName" CssClass="codeheader" runat="server" /></h2>
       

        <asp:Panel ID="pnlCode" CssClass="code" runat="server" Width="80%">
            <asp:Label ID="Code" runat="server" />
        </asp:Panel>



        <h2>
              <asp:Label ID="XMLFileName" CssClass="codeheader" runat="server" />
        </h2>
       
        <asp:Panel ID="XMLPanel" CssClass="code" runat="server" Width="80%">
            <asp:Label ID="XMLCode" runat="server" />
        </asp:Panel>


    </form>
</body>
</html>
