<%@ Page Language="C#" Debug="true" %>

<%@ Import Namespace="System.IO" %>

<script language="C#" runat="server">

    void Page_Load()
    {
        //About Us的源代码路径
        string aboutUsFilePath = Server.MapPath(".") + "/AboutUs.aspx";

        //History 文件的源代码路径
        string historyFilePath = Server.MapPath(".") + "/History.aspx";

        //Frequently Asked Questions 文件的源代码路径
        string FAQFilePath = Server.MapPath(".") + "/FrequentlyAskedQuestions.aspx";

        AboutUsFileName.Text = "AboutUs.aspx";
        HistoryFileName.Text = "History.aspx";
        FAQFileName.Text = "FrequentlyAskedQuestions.aspx";

        FileInfo aboutUsFile = new FileInfo(aboutUsFilePath);
        FileInfo historyFile = new FileInfo(historyFilePath);
        FileInfo FAQFile = new FileInfo(FAQFilePath);

        AboutUsCode.Text = ReadFile(aboutUsFilePath);
        HistoryCode.Text = ReadFile(historyFilePath);
        FAQCode.Text = ReadFile(FAQFilePath);

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
    <link rel="stylesheet" href="../css/DisplayCodeStyle.css" type="text/css">
</head>
<body>
    <form id="form1" runat="server">
  

        <h1 class="pageHeader">Source Code</h1>



        <h2> <asp:Label ID="AboutUsFileName" CssClass="codeheader" runat="server" /></h2> 
        <asp:Panel ID="pnlCode" CssClass="code" runat="server" Width="80%">
            <asp:Label ID="AboutUsCode" runat="server" />
        </asp:Panel>



        <h2>
              <asp:Label ID="HistoryFileName" CssClass="codeheader" runat="server" />
        </h2>
        <asp:Panel ID="HistoryLPanel" CssClass="code" runat="server" Width="80%">
            <asp:Label ID="HistoryCode" runat="server" />
        </asp:Panel>


        <h2><asp:Label ID="FAQFileName" CssClass="codeheader" runat="server" /></h2>
        <asp:Panel ID="FAQFilePanel" CssClass="code" runat="server" Width="80%">
            <asp:Label ID="FAQCode" runat="server" />
        </asp:Panel>


    </form>
</body>
</html>

