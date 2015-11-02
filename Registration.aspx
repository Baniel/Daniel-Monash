<%@ Page Language="C#" MasterPageFile="~/MasterPage.master" StylesheetTheme="default" Title="Registration" %>

<%@ Import Namespace="System.Drawing" %>

<script language="C#" runat="server">



    //Customer Validator
    protected void ValidateDate(Object source, ServerValidateEventArgs args)
    {
        args.IsValid = false;
        DateTime date;
        DateTime startDate = DateTime.Parse("01/09/2015");
        DateTime endDate = DateTime.Parse("01/10/2015");

        feedback.ForeColor = System.Drawing.Color.Maroon;

        try
        {
            date = DateTime.Parse(DateTextBox.Text);
        }
        catch (Exception ex)
        {
            feedback.Text = "Date is invalid.<br />" + "Enter a valid date,for example: 09/09/2015";
            return;
        }

        args.IsValid = true;


    }

    //提交按钮
    private void Submit_click(Object sender, EventArgs e)
    {
        if (!Page.IsValid)
        {
            return;
        }


        feedback.Text = "Account: " + accountTextBox.Text + "<br/>"
                       + "Password: " + passwordTextBox.Text + "<br/>"
                       + "Email: " + EmailTextBox.Text + "<br/>"
                       + "Age: " + AgeTextBox.Text + "<br/>"
                       + "City:" + DorpDownList1.Text + "<br/>"
                       + "Game Machine Type: " + checkboxlist1.Text + "<br/>"
                       + "Ocupation : " + listBox.Text + "<br/>"
                       + "Birthday:" + DateTextBox.Text + "<br/>" +
                       "<a href='https://batmanarkhamorigins.com/#about'>Batman Media</a>";




        CustomerDS.Insert();






    }



</script>


<asp:Content ID="SideBar" ContentPlaceHolderID="LeftSideBar" runat="server">

    <link href="css/MenuStyleSheet.css" type="text/css" rel="stylesheet" />
    <asp:SiteMapDataSource ID="SiteMapDataSource1" runat="server" />


    <div class="menu">
        <asp:Menu StaticDisplayLevels="2" ID="Menu1" runat="server" DataSourceID="SiteMapDataSource1"
            StaticSubMenuIndent="25" ForeColor="Black" Width="100%" Height="300px">
            <StaticMenuItemStyle CssClass="MenuStaItm" />
            <DynamicHoverStyle CssClass="MenuDynHov" />
            <DynamicMenuItemStyle CssClass="MenuDynItm" />
            <StaticHoverStyle CssClass="MenuStaHov" />
        </asp:Menu>
    </div>

    <asp:Image ID="Image1" runat="server" SkinID="logo" />

</asp:Content>

<asp:Content ID="Content" ContentPlaceHolderID="Content" runat="server">


    <asp:AccessDataSource ID="CustomerDS" runat="server" DataFile="~/CustomerTable.mdb"
        DeleteCommand="DELETE FROM [CustomerTable] WHERE [CustomerID] = ?"
        InsertCommand="INSERT INTO [CustomerTable] ([Account], [Password], [Email], [Sex], [City], [Game Machine Type], [Ocupation], [Birthday], [Age]) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        SelectCommand="SELECT * FROM [CustomerTable] ORDER BY [CustomerID]"
        UpdateCommand="UPDATE [CustomerTable] SET [Account] = ?, [Password] = ?, [Email] = ?, [Sex] = ?, [City] = ?, [Game Machine Type] = ?, [Ocupation] = ?, [Birthday] = ?, [Age] = ? WHERE [CustomerID] = ?">

        <DeleteParameters>
            <asp:Parameter Name="CustomerID" Type="Int32" />
        </DeleteParameters>

        <InsertParameters>

            <asp:ControlParameter Name="Account" ControlID="accountTextBox" Type="String" />
            <asp:ControlParameter Name="Password" ControlID="passwordTextBox" Type="String" />
            <asp:ControlParameter Name="Email" ControlID="EmailTextBox" Type="String" />
            <asp:ControlParameter Name="Sex" ControlID="radioButtonList" PropertyName="SelectedValue" Type="String" />
            <asp:ControlParameter Name="City" ControlID="DorpDownList1" PropertyName="SelectedValue" Type="String" />
            <asp:ControlParameter Name="Game Machine Type" ControlID="checkboxlist1" PropertyName="SelectedValue" Type="String" />
            <asp:ControlParameter Name="Ocupation" ControlID="listBox" PropertyName="SelectedValue" Type="String" />
            <asp:ControlParameter Name="Birthday" ControlID="DateTextBox" Type="String" />
            <asp:ControlParameter Name="Age" ControlID="AgeTextBox" Type="Int32" />

        </InsertParameters>

        <UpdateParameters>
            <asp:Parameter Name="Account" Type="String" />
            <asp:Parameter Name="Password" Type="String" />
            <asp:Parameter Name="Email" Type="String" />
            <asp:Parameter Name="Sex" Type="String" />
            <asp:Parameter Name="City" Type="String" />
            <asp:Parameter Name="Game_Machine_Type" Type="String" />
            <asp:Parameter Name="Ocupation" Type="String" />
            <asp:Parameter Name="Birthday" Type="String" />
            <asp:Parameter Name="Age" Type="Int32" />
            <asp:Parameter Name="CustomerID" Type="Int32" />
        </UpdateParameters>


    </asp:AccessDataSource>




    <asp:SiteMapPath ID="SiteMapPath1" runat="server">
        <PathSeparatorTemplate>
            <asp:Image ID="emerald" runat="server" ImageUrl="~/images/icon.png" Width="60px" Height="30px" />
        </PathSeparatorTemplate>
    </asp:SiteMapPath>

    <link href="css/Registration.css" type="text/css" rel="stylesheet" />
    <asp:Panel ID="Panel" runat="server" CssClass="pnaelStyle" ToolTip="Please enter your Information,thanks Y^_^Y">



        <h3>Registration you can early to get Game!!! </h3>

        <center>
            <div class="layout">
                 <table>

                
             
                <%--账号--%>
                <tr>
                    <td class="text">Account: </td>
                    <td><asp:TextBox ID="accountTextBox" runat="server" CssClass="textBox"></asp:TextBox>
                     <asp:RequiredFieldValidator id="accountValidator" runat="server"  ErrorMessage= "Please enter a account!!!" ControlToValidate="accountTextBox" CssClass="Validator" />
                 </td>
                </tr>

                <%--密码--%>
                <tr>
                    <td class="text">Password: </td>
                    <td><asp:TextBox ID="passwordTextBox" runat="server" CssClass="textBox" TextMode="Password"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="passwordValidator" runat="server" ErrorMessage="Please enter a password!!!" ControlToValidate="passwordTextBox"/>

                    </td>
                </tr>

                <%--确认密码--%>
                <tr>
                    <td class="text">Confirm Password: </td>
                    <td><asp:TextBox ID="confirmPasswordTextBox" runat="server" CssClass="textBox" TextMode="Password"></asp:TextBox>
                    <asp:RequiredFieldValidator ID="confirmPasswordValidator" runat="server" ErrorMessage="Please enter a confirm password!!!" ControlToValidate="confirmPasswordTextBox" />
                    <asp:CompareValidator ID="passwordCompareValidator" runat="server" ControlToValidate="passwordTextBox" ControlToCompare="confirmPasswordTextBox" ErrorMessage="Please enter the same value for the Password!!!" />                   
                     </td>
                </tr>

               <%-- 邮件--%>
                <tr>
                    <td class="text">Email: </td>
                    <td><asp:TextBox ID="EmailTextBox" runat="server" CssClass="textBox"></asp:TextBox>
                        <asp:RequiredFieldValidator ID="EmailValidator" runat="server" ErrorMessage="Please enter a Email!!!" ControlToValidate="EmailTextBox" />
                        <asp:RegularExpressionValidator ID="EmailRegularExpressionValidator" runat="server" ControlToValidate="EmailTextBox" ValidationExpression=".*@.*\..*" ErrorMessage="Invalid Email!!!" Display="Dynamic" />
                    </td>
                </tr>

               <%-- 年龄--%>
                <tr>
                    <td class="text">Age: </td>
                    <td><asp:TextBox ID="AgeTextBox" runat="server" CssClass="textBox" TextMode="Number"></asp:TextBox>
                    <asp:RequiredFieldValidator  ID="AgeValidator" runat="server" ErrorMessage="Please enter a Age!!!" ControlToValidate="AgeTextBox" />
                    <asp:RangeValidator ID="AgeRangeValidator" runat="server" ErrorMessage="Please enter between 5 - 100 " MinimumValue="5" MaximumValue="100" Type="Integer" ControlToValidate="AgeTextBox"/>
                    </td>
                </tr>

                <%--性别--%>
                <tr>
                    <td class="text">Sex: </td>
                    <td>
                        <asp:RadioButtonList ID="radioButtonList" runat="server">
                        <asp:ListItem Selected="true">Male</asp:ListItem>
                        <asp:ListItem>Female</asp:ListItem>
                        </asp:RadioButtonList>
                      
                     
                    </td>
                </tr>


                
               <%-- 城市--%>
                <tr>
                    <td class="text">City: </td>
                    <td><asp:DropDownList ID="DorpDownList1" runat="server">
                        <asp:ListItem Selected="True" Value="Sydney">Sydney</asp:ListItem>
                        <asp:ListItem Value="Melbourne">Melbourne</asp:ListItem>
                        <asp:ListItem Value="Brisbane">Brisbane</asp:ListItem>
                        <asp:ListItem Value="Perth">Perth</asp:ListItem>
                        <asp:ListItem Value="Hobart">Hobart</asp:ListItem>
                        <asp:ListItem Value="Adeladide">Adeladide</asp:ListItem>
                        </asp:DropDownList> </td>
                    <br />
                </tr>


               <%-- 主机类型--%>
                <tr>
                    <td class="text">Game Machine Type: </td>
                    <td> 
                        <asp:CheckBoxList ID="checkboxlist1"  runat="server">
                            <asp:ListItem Selected="True" Value="XBOX">XBOX</asp:ListItem>
                            <asp:ListItem Value="PlayStation">PlayStation</asp:ListItem>
                            <asp:ListItem Value="PC">PC</asp:ListItem>

                        </asp:CheckBoxList>

                    </td>

                </tr>

                <%--职业--%>
                <tr>
                    <td class="text">Ocupation: </td>
                    <td><asp:ListBox ID="listBox" Rows="3" Width="100px" SelectionMode="Single" runat="server">
                        <asp:ListItem Selected="True" Value="Student">Student</asp:ListItem>
                        <asp:ListItem Value="Teacher">Teacher</asp:ListItem>
                        <asp:ListItem Value="Others">Others</asp:ListItem>
                        </asp:ListBox> </td>
                </tr>

                <%--日期--%>
                <tr>
                    <td class="text">Birthday : </td>
                    <td>
                        <asp:TextBox ID="DateTextBox" runat="server" CssClass="textBox" placeHolder="Please Input the Format like 25/12/2015" Width="500" ></asp:TextBox>
                        <asp:RequiredFieldValidator ID="DateValidator" runat="server" ErrorMessage="Please enter a Date" ControlToValidate="DateTextBox"/>
                        <asp:CustomValidator ID="CustomerValidator" runat="server" ControlToValidate="DateTextBox" OnServerValidate="ValidateDate" />
                    </td>
                    

                </tr>
                <tr>
                    <td></td>
                    <td>We will send a gift on your birthday.</td>
                        
               </tr>

                <%--按钮--%>
                <tr>
                    <td>
                      
                    </td>
                    <td><asp:Button ID="SubmitButton" runat="server" Text="Sumbit" OnClick="Submit_click" CssClass="textBox"/>   </td>
                </tr>

                <tr>
                    <td></td>

                    <td class="formResult"> 
                    
                        <asp:Label ID="feedback" runat="server">
                         
                        </asp:Label>

                    </td>
                    <td width="50%"></td>                   
                </tr>


                    <tr>
                        <td>
                                    
                <a href="DisplayCode.aspx?filename=DisplayCustomerDatabase.aspx" target="_blank">
                        <asp:Image ID="codebuttonAddRecords" runat="server" ImageUrl="~/images/ButtonImages/codebuttonAddRecords.jpg" />
                        </a>



                        </td>
                        <td> 
                              <asp:HyperLink runat="server" Text="Click here to see all the Customers" NavigateUrl="~/DisplayCustomerDatabase.aspx" Font-Size="X-Large" Target="_blank">
                               </asp:HyperLink>
                        </td>
                    </tr>
               

                     <tr>
                         <td>
                              <a href="DisplayCode.aspx?filename=EditCustomer.aspx" target="_blank">
                    <asp:Image ID="Display_Records" runat="server" ImageUrl="~/images/ButtonImages/codebuttondisplayrecords.jpg" />
                </a> 
                         </td>
                         <td>
                             <asp:HyperLink runat="server"  Text="Clieck here to Edit Customers" NavigateUrl="~/EditCustomer.aspx" Font-Size="X-Large" Target="_blank"/>
                         </td>

                     </tr>

                     <tr>
                         <td>
                             <a href="DisplayCode.aspx?filename=SearchCustomer.aspx" target="_blank">
                                 <asp:Image ID="Search_Records" runat="server"  ImageUrl="~/images/ButtonImages/codebuttonsearchrecords.jpg" />
                             </a>
                         </td>

                         <td>
                             <asp:HyperLink runat="server" Text="Click here to search Customers" NavigateUrl="~/SearchCustomer.aspx" Font-Size="X-Large" Target="_blank" />

                         </td>
                     </tr>
               
                

            </table>


            </div>             

        </center>




    </asp:Panel>

</asp:Content>

