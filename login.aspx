<%@ Page Language="C#" Debug="true" %>

<script language="C#" runat="server">

void Login_Authenticate(object sender, AuthenticateEventArgs e)
{
  DS1.SelectCommand = "SELECT * FROM CustomerTable WHERE Account = '" + Login.UserName +
    "' AND Password = '" + Login.Password + "'";

  DS1.Select(DataSourceSelectArguments.Empty);
}

private void CheckLogin(object sender, 
  SqlDataSourceStatusEventArgs e)
{
  if (e.AffectedRows > 0)
  {
    FormsAuthentication.RedirectFromLoginPage(Login.UserName, 
      false);
  }
  else
  {
    Login.FailureText="Invalid Login";
  }
}
</script>

<html>
  <head>
    <title>Forms Authentication</title>
    <link href="css/LoginStyle.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <asp:AccessDataSource ID="DS1" runat="server" 
      DataFile="~/CustomerTable.mdb" OnSelected="CheckLogin" SelectCommand="SELECT [Account], [Password] FROM [CustomerTable]" />

    <form runat="server">
        
        <center>
      <asp:Login ID="Login" runat="server" 
        OnAuthenticate="Login_Authenticate" CssClass="login"
        TitleText="<br />Please enter your details <br />
          below to login for this site.<br /><br />"
        UserNameLabelText="Account:" 
        UserNameRequiredErrorMessage="Account required<p />" 
        PasswordRequiredErrorMessage="Password required"
        Height = "250px" Width = "330px"
        LoginButtonText="Click to login" DisplayRememberMe="False" BackColor="#F7F6F3" BorderColor="#E6E2D8" BorderPadding="4" BorderStyle="Solid" BorderWidth="1px" Font-Names="Verdana" Font-Size="0.8em" ForeColor="#333333">
          <InstructionTextStyle Font-Italic="True" ForeColor="Black" />
        <LabelStyle CssClass="loginText" />
          <LoginButtonStyle BackColor="#FFFBFF" BorderColor="#CCCCCC" BorderStyle="Solid" BorderWidth="1px" Font-Names="Verdana" Font-Size="0.8em" ForeColor="#284775" />
          <TextBoxStyle Font-Size="0.8em" />
        <TitleTextStyle CssClass="loginText" BackColor="#5D7B9D" Font-Bold="True" Font-Size="0.9em" ForeColor="White" />
        <ValidatorTextStyle CssClass="loginValidator" />
      </asp:Login>
      <p />

      <asp:ValidationSummary id="vlSummary1" Font-Names="Arial" 
        Visible="true" CssClass="vldSummary"
        runat="server" ValidationGroup="Login" 
        HeaderText="Please correct the following errors:" />

        <br />
        <a href="DisplayCode.aspx?filename=login.aspx" target="_blank" >
            <asp:Image runat="server" ImageUrl="~/images/ButtonImages/codebuttonlogin.jpg"  />      </a>
        </center>
    </form>

  </body>
</html>
