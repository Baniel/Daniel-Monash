<%@ Page Language="C#" AutoEventWireup="true" CodeFile="Test.aspx.cs" Inherits="Test" %>


<script>

    void gvCustomer_OnRowDataBound(object sender, GridViewRowEventArgs e)
    {
  if (e.Row.RowType == DataControlRowType.DataRow)
    {
        AccessDataSource dataSource = 
          (AccessDataSource)e.Row.FindControl("dsOrders");
        if (null != dataSource)
        {
            dataSource.SelectCommand = "SELECT * FROM Orders " +
              " WHERE CustID = " +
              gvCustomers.DataKeys[e.Row.RowIndex].Value;
        }
        System.Data.DataView dv =
         (System.Data.DataView)dataSource. 
         Select(DataSourceSelectArguments.Empty); 
        if (dv.Count == 0) 
        { 
            Label lbl = new Label(); 
            lbl.Text = "No records found for this customer"; 
            e.Row.Cells[1].Controls.Add(lbl); 
        }
        else
        {
            GridView gridControl = 
              (GridView)e.Row.FindControl("gvOrders");
            if (null != gridControl)
            {
                gridControl.DataSource = dataSource;
                gridControl.DataBind();
            }
        }
    }  
    }







</script>
<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <title></title>
</head>
<body>
    <form id="form1" runat="server">
        <div>
        </div>
    </form>
</body>
</html>
