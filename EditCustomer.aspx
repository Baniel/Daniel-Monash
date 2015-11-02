<%@ Page Language="C#" Debug="true" ValidateRequest="true" %>

<script runat="server">

    void refreshGridUpdate(object sender, DetailsViewUpdatedEventArgs e)
    {
        gvCustomers.DataBind();
    }




</script>





<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>

    <form id="form1" runat="server">
        <asp:GridView ID="gvCustomers" runat="server" AutoGenerateColumns="False" DataKeyNames="CustomerID"
            DataSourceID="SqlDataSource1" CellPadding="4" ForeColor="#333333" GridLines="None" AutoGenerateSelectButton="True">
            <AlternatingRowStyle BackColor="White" />
            <Columns>
                <asp:BoundField DataField="CustomerID" HeaderText="CustomerID" InsertVisible="False" ReadOnly="True" SortExpression="CustomerID" />
                <asp:BoundField DataField="Account" HeaderText="Account" SortExpression="Account" />
                <asp:BoundField DataField="Password" HeaderText="Password" SortExpression="Password" />
                <asp:BoundField DataField="Email" HeaderText="Email" SortExpression="Email" />
                <asp:BoundField DataField="Sex" HeaderText="Sex" SortExpression="Sex" />
                <asp:BoundField DataField="City" HeaderText="City" SortExpression="City" />
                <asp:BoundField DataField="Game_Machine_Type" HeaderText="Game_Machine_Type" SortExpression="Game_Machine_Type" />
                <asp:BoundField DataField="Ocupation" HeaderText="Ocupation" SortExpression="Ocupation" />
                <asp:BoundField DataField="Birthday" HeaderText="Birthday" SortExpression="Birthday" />
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




        <asp:AccessDataSource ID="SqlDataSource1" runat="server" DataFile="~/CustomerTable.mdb" SelectCommand="SELECT [CustomerID], [Account], [Password], [Email], [Sex], [City], [Game Machine Type] AS Game_Machine_Type, [Ocupation], [Birthday] FROM [CustomerTable] ORDER BY [CustomerID]"></asp:AccessDataSource>



        <%--用户信息编辑区--%>
        <br />
        <asp:DetailsView ID="DetailsView1" runat="server" Height="50px" Width="125px" AutoGenerateRows="False" DataKeyNames="CustomerID"
            DataSourceID="SqlDataSource2" CellPadding="4" ForeColor="#333333" GridLines="None" OnItemUpdated="refreshGridUpdate" AutoGenerateEditButton="True">

            <AlternatingRowStyle BackColor="White" />
            <CommandRowStyle BackColor="#D1DDF1" Font-Bold="True" />
            <EditRowStyle BackColor="#2461BF" />
            <FieldHeaderStyle BackColor="#DEE8F5" Font-Bold="True" />
            <Fields>

                <asp:BoundField DataField="CustomerID" HeaderText="CustomerID" InsertVisible="False" ReadOnly="True" SortExpression="CustomerID" />
                <asp:BoundField DataField="Account" HeaderText="Account" SortExpression="Account" />
                <asp:BoundField DataField="Password" HeaderText="Password" SortExpression="Password" />
                <asp:BoundField DataField="City" HeaderText="City" SortExpression="City" ReadOnly="True" />
                <asp:BoundField DataField="Email" HeaderText="Email" SortExpression="Email" />
                <asp:BoundField DataField="Sex" HeaderText="Sex" SortExpression="Sex" ReadOnly="True" />
                <asp:BoundField DataField="Game_Machine_Type" HeaderText="Game_Machine_Type" SortExpression="Game_Machine_Type" ReadOnly="True" />
                <asp:BoundField DataField="Ocupation" HeaderText="Ocupation" SortExpression="Ocupation" ReadOnly="True" />
                <asp:BoundField DataField="Birthday" HeaderText="Birthday" SortExpression="Birthday" ReadOnly="True" />



            </Fields>
            <FooterStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <HeaderStyle BackColor="#507CD1" Font-Bold="True" ForeColor="White" />
            <PagerStyle BackColor="#2461BF" ForeColor="White" HorizontalAlign="Center" />
            <RowStyle BackColor="#EFF3FB" />
        </asp:DetailsView>


        <asp:AccessDataSource ID="SqlDataSource2" runat="server" DataFile="~/CustomerTable.mdb"
            DeleteCommand="DELETE FROM [CustomerTable] WHERE [CustomerID] = ?"
            InsertCommand="INSERT INTO [CustomerTable] ([CustomerID], [Account], [Password], [City], [Email], [Sex], [Game Machine Type], [Ocupation], [Birthday]) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)"
            SelectCommand="SELECT [CustomerID], [Account], [Password], [City], [Email], [Sex], [Game Machine Type] AS Game_Machine_Type, [Ocupation], [Birthday] FROM [CustomerTable] WHERE ([CustomerID] = ?) ORDER BY [CustomerID]"
            UpdateCommand="UPDATE [CustomerTable] SET [Account] = ?, [Password] = ?,  [Email] = ? WHERE [CustomerID] = ?">
            <DeleteParameters>
                <asp:Parameter Name="CustomerID" Type="Int32" />
            </DeleteParameters>
            <InsertParameters>
                <asp:Parameter Name="CustomerID" Type="Int32" />
                <asp:Parameter Name="Account" Type="String" />
                <asp:Parameter Name="Password" Type="String" />
                <asp:Parameter Name="City" Type="String" />
                <asp:Parameter Name="Email" Type="String" />
                <asp:Parameter Name="Sex" Type="String" />
                <asp:Parameter Name="Game_Machine_Type" Type="String" />
                <asp:Parameter Name="Ocupation" Type="String" />
                <asp:Parameter Name="Birthday" Type="String" />
            </InsertParameters>

            <SelectParameters>
                <asp:ControlParameter ControlID="gvCustomers" Name="CustomerID" PropertyName="SelectedValue" Type="Int16" />
            </SelectParameters>
            <UpdateParameters>
                <asp:Parameter Name="Account" Type="String" />
                <asp:Parameter Name="Password" Type="String" />
                <asp:Parameter Name="Email" Type="String" />

            </UpdateParameters>
        </asp:AccessDataSource>



    </form>
</body>
</html>
