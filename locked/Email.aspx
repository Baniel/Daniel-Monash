<%@ Import Namespace="System.Net.Mail" %>
<%@ Import Namespace="System.Net" %>
<%@ Page Language="C#" Debug="true" %>

<script Language="c#" runat="server">

    public void SendEmail(object sender, EventArgs e)
    {
        MailMessage newMsg = new MailMessage();

        foreach (GridViewRow gvRow in gvCustomers.Rows)
        {
            CheckBox cb = (CheckBox)gvRow.FindControl("chkEmail");

            if (cb != null && cb.Checked)
            {

                newMsg.To.Add(new MailAddress(gvRow.Cells[1].Text,gvRow.Cells[0].Text));
            }
        }

        newMsg.From = new MailAddress("danielPark139@gmail.com","Daniel");
        newMsg.Subject = txtSubject.Text;
        newMsg.Body = txtMsg.Text;

        try
        {
            SmtpClient smtp = new SmtpClient();
       
            smtp.Host = "smtp.monash.edu.au";
            smtp.Send(newMsg);
            lblMail.Text = "Mail Successfully Sent";
        }
        catch (Exception exc)
        {
            lblMail.Text = exc.Message;
        }
    }
</script>

<html>
  <head>
    <title>Send Email</title>
    <link rel="stylesheet" href="../css/EmailStyle.css" type="text/css" />
  </head>
<body>

  <body>

  <form id="Form1" method="post" runat="server" >

     
    <asp:label id="lblMail" CssClass="error" runat="server" />
    <p />

    <asp:GridView ID="gvCustomers" runat="server" 
      DataSourceID="SqlDataSource1"
      AutoGenerateColumns="False" 
      RowStyle-CssClass="customerRow" 
      AlternatingRowStyle-CssClass="customerAlternate" 
      HeaderStyle-CssClass="customerHeader" 
      CssClass="customerGrid" CellPadding="5" DataKeyNames="CustomerID">
<AlternatingRowStyle CssClass="customerAlternate"></AlternatingRowStyle>
      <Columns>
        <asp:BoundField DataField="Account" HeaderText="Account" SortExpression="Account" />
        <asp:BoundField DataField="Email" HeaderText="Email" SortExpression="Email" />
          <asp:TemplateField HeaderText="Select"> 
          <ItemTemplate>
            <asp:CheckBox runat="server" id="chkEmail" /> 
          </ItemTemplate> 
        </asp:TemplateField>
       

      </Columns>

<HeaderStyle CssClass="customerHeader"></HeaderStyle>

<RowStyle CssClass="customerRow"></RowStyle>
    </asp:GridView>
     

      <asp:AccessDataSource ID="SqlDataSource1" runat="server" DataFile="~/CustomerTable.mdb" SelectCommand="SELECT * FROM [CustomerTable] ORDER BY [CustomerID]" ></asp:AccessDataSource>
    <br /><br />

         
    <table class="emailTable">
     
    <tr>
      <td class="emailHeader" width="15%">From</td>
      <td class="emailRow">Daniel</td>
    </tr>
    <tr>
      <td class="emailHeader" width="15%">Subject</td>
      <td class="emailRow">
        <asp:TextBox ID="txtSubject" Width="350" runat="server" />
      </td>
    </tr>
    <tr>
      <td class="emailHeader">Message</td>
      <td class="emailRow">
        <asp:TextBox runat="server" ID="txtMsg" 
          TextMode="MultiLine" Columns="55" Rows="15" />
      </td>
    </tr>
   
    </table>'

    <br />
    
    <asp:Button id="SendMail" runat="server" OnClick="SendEmail" Text="Send Email"/>

          <br /><br />

          <asp:HyperLink runat="server" Font-Size="X-Large" Font-Italic="true" Text="Back to the Main Page" NavigateUrl="~/index.aspx" />

    <br /><br />	 

      <a href="DisplayCode.aspx?filename=Email.aspx" >
          <asp:Image runat="server" ImageUrl="~/images/ButtonImages/codebuttonEmail.jpg" />
      </a>

   

  </form>
</body>
</html>