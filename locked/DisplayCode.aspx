<%@ Page Language="C#" runat="server" Debug="true" %>

<%@ Import Namespace="System.IO" %>
<script language="C#" runat="server">
    void Page_Load()
    {
        string filePath = Server.MapPath(Request.QueryString["filename"]);
        FileName.Text = Request.QueryString["filename"];
        FileInfo file = new FileInfo(filePath);


        Code.Text = ReadFile(filePath);

    }

    private string ReadFile(string filepath)
    {
        string fileOutput = "";
        try
        {
            StreamReader FileReader = new StreamReader(filepath);

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

<html>
<head>
    <title>code</title>
    <link rel="stylesheet" href="../css/DisplayCodeStyle.css" type="text/css">
</head>
<body>
    <h1 class="pageHeader">Source Code</h1>
    <h2>
        <asp:Label ID="FileName"
            CssClass="codeheader" runat="server" />
    </h2>
    <asp:Panel ID="pnlCode" CssClass="code"
        runat="server" Width="100%">
        <asp:Label ID="Code" runat="server" />
    </asp:Panel>
</body>
</html>
