<%@ Page Language="C#" %>



<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">

        <asp:GridView ID="GridView1" runat="server" AutoGenerateColumns="False" CellPadding="4" DataKeyNames="CustomerID" DataSourceID="SqlDataSource1" ForeColor="#333333" GridLines="None">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:BoundField DataField="CustomerID" HeaderText="CustomerID" InsertVisible="False" ReadOnly="True" SortExpression="CustomerID" />
                <asp:BoundField DataField="Account" HeaderText="Account" SortExpression="Account" />
                <asp:BoundField DataField="Password" HeaderText="Password" SortExpression="Password" />
                <asp:BoundField DataField="Email" HeaderText="Email" SortExpression="Email" ItemStyle-Width="50" />
                <asp:BoundField DataField="Sex" HeaderText="Sex" SortExpression="Sex" ItemStyle-Width="100"/>
                <asp:BoundField DataField="City" HeaderText="City" SortExpression="City" />
                <asp:BoundField DataField="Game Machine Type" HeaderText="Game Machine Type" SortExpression="Game Machine Type" />
                <asp:BoundField DataField="Ocupation" HeaderText="Ocupation" SortExpression="Ocupation" />

                <asp:BoundField DataField="Birthday" HeaderText="Birthday" SortExpression="Birthday" />
                <asp:BoundField DataField="Age" HeaderText="Age" SortExpression="Age" />

            </Columns>
            <EditRowStyle BackColor="#2461BF" />
            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#EFF3FB" />
            <SelectedRowStyle BackColor="#D1DDF1" Font-Bold="True" ForeColor="#333333" />
            <SortedAscendingCellStyle BackColor="#F5F7FB" />
            <SortedAscendingHeaderStyle BackColor="#6D95E1" />
            <SortedDescendingCellStyle BackColor="#E9EBEF" />
            <SortedDescendingHeaderStyle BackColor="#4870BE" />
        </asp:GridView>

        <asp:AccessDataSource ID="SqlDataSource1" runat="server" DataFile="~/CustomerTable.mdb" SelectCommand="SELECT * FROM [CustomerTable] ORDER BY [CustomerID]">


        </asp:AccessDataSource>




    </form>
</body>
</html>
