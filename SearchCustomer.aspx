<%@ Page Language="C#" %>

<%@ Import Namespace="System.Data" %>
<%@ Import Namespace="System.Data.OleDb" %>
<%@ Import Namespace="System.Drawing" %>

<script language="C#" runat="server">

    private void RetrieveCustomer(object sender, EventArgs e)
    {
        string strConnection = "Provider = Microsoft.Jet.OLEDB.4.0; Data Source = " +
                        Server.MapPath("CustomerTable.mdb") + ";";
        string strSQL = "";
        string strResultsHolder = "";

        if (Request.Form["Account"] != "")
        {
            strSQL = "SELECT * FROM CustomerTable WHERE Account LIKE '%" + Account.Text.Trim(' ') + "%'";
        }
        else if (Request.Form["CustomerID"] != "")
        {
            strSQL = "SELECT * FROM CustomerTable WHERE Account LIKE '%" + CustomerID.Text.Trim(' ') + "%'";
        }

        else
        {
            strSQL = "SELECT * FROM CustomerTable";
        }

        OleDbConnection objConnection = new OleDbConnection(strConnection);
        OleDbCommand objCommand = new OleDbCommand(strSQL, objConnection);
        OleDbDataReader objDataReader = null;
        objConnection.Open();
        objDataReader = objCommand.ExecuteReader();

        Boolean find = false;


        strResultsHolder += "<br/>";
        strResultsHolder += "<table border='2'>";
        strResultsHolder += "<tr>";
        strResultsHolder += "<td>Customer ID</td>";
        strResultsHolder += "<td>Account </td>";
        strResultsHolder += "<td>Password </td>";
        strResultsHolder += "<td> Email </ td >";
        strResultsHolder += "<td> Sex </td>";
        strResultsHolder += "<td> City </td>";
        strResultsHolder += "</tr>"; 



        while (objDataReader.Read() == true)

        {
            strResultsHolder += "<tr>";
            strResultsHolder += "<td>" +  objDataReader[0].ToString() + " " + "</td> ";
            strResultsHolder += "<td>" +  objDataReader[1].ToString() + "</td>";
            strResultsHolder += "<td>" + objDataReader[2].ToString() + "</td>";
            strResultsHolder += "<td>" + objDataReader[3].ToString() + "</td>";
            strResultsHolder += "<td>" + objDataReader[4].ToString() +"</td>";
            strResultsHolder += "<td>" + objDataReader[5].ToString() + "</td>";
            strResultsHolder += "</tr>";
            find = true;
        }
        strResultsHolder += "</table>";



        objDataReader.Close();
        objConnection.Close();


        if (find == false)
        {
            strResultsHolder ="Sorry,We can't find this Customer!!!";
        }

        CustomerList.Text = strResultsHolder;
    }


</script>




<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">


        <h1>Please enter part,or all,of the Customer ID</h1>
        <asp:TextBox ID="CustomerID" runat="server" Width="200"></asp:TextBox>
        <asp:Button ID="CustomerIDButton" runat="server" OnClick="RetrieveCustomer" Text="Click to find Customer by ID"
            Style="font: 8pt verdana; background-color: lightgreen; border-color: black; width: 600" />



        <h1>Please enter part,or all ,of the Account  </h1>
        <asp:TextBox ID="Account" runat="server" Width="200"> </asp:TextBox>
        <asp:Button ID="AccountButton" runat="server" OnClick="RetrieveCustomer" Text="Click to find customer by Name"
            Style="font: 8pt verdana; background-color: lightgreen; border-color: black; width: 600" />
        <br />










        <asp:Label ID="CustomerList" runat="server" ForeColor="#ff0066" Font-Bold="true" Font-Size="14"/>


    </form>
</body>
</html>
