<%@ Page Language="C#" Debug="true" %>

<script language="C#" runat="server">
  void Page_Load()
  {
    lblUser.Text = User.Identity.Name; 
   
  }
  
  void Logout_Click(object sender, EventArgs e) 
  { 
    FormsAuthentication.SignOut(); 
    Server.Transfer("../index.aspx"); 
  }
</script>
<html>
<head>
    <title>ASP.NET Login Control</title>
</head>
  <body>
  <form runat="server">
    <div class="heading3">
    
        <h2>     
             Hi  , <asp:label id="lblUser" runat="server"/>,You will log out .

        </h2>
     
   
    <asp:button text="Logout" OnClick="Logout_Click" 
    runat="server"/>
    </div>
  </form>
  </body>
</html>