<%@ Page Language="C#" AutoEventWireup="True" %>
<%@ Import Namespace="System.Data" %>
<%@ Import Namespace="System.Windows.Forms" %>
<%@ Import Namespace="System" %>
<%@ Import Namespace="System.Data" %>
<%@ Import Namespace="System.Data.OleDb" %>
<%@ Import Namespace="System.Drawing" %>

    

<html>
<head>

    <script runat="server">
        void DayRender(Object sender, DayRenderEventArgs e)
        {
            DateTime today = DateTime.Today;
            String todayStr = today.ToString("dd/MM/yyyy");
            String todayStr2 = "10/10/2015"; 

            dsEvent.SelectCommand = "SELECT * FROM CalenderEvent WHERE EventDate = #" + todayStr2 + "#";
            String sqlCommand = dsEvent.SelectCommand;

            test.Text = sqlCommand;

            DataView dv = (DataView)dsEvent.Select(DataSourceSelectArguments.Empty);

            if (dv.Count == 0)
            {
                Event.Text = "No Events Scheduled for Today up";
            }
            else
            {

                if (e.Day.IsToday)
                {
                    e.Cell.BackColor = System.Drawing.Color.Green;
                }
            }


        }

        void Page_Load(Object sender, EventArgs e)
        {
            Calendar1.DayRender += new DayRenderEventHandler(this.DayRender);

            string strConnection = "Provider = Microsoft.jet.OLEDB.4.0; Data Source = " +
                            Server.MapPath("CustomerTable.mdb") + ";";
            string strSQL = "";
            string strResultsHolder = "";
            DateTime today = DateTime.Today;

            String todayStr = today.ToString("dd/MM/yyyy");

             String todayStr2 = "10/10/2015"; 
            strSQL =  "SELECT * FROM CalenderEvent WHERE EventDate = #" + todayStr2 + "#";

            OleDbConnection objConnection = new OleDbConnection(strConnection);
            OleDbCommand objCommand = new OleDbCommand(strSQL, objConnection);
            OleDbDataReader objDataReader = null;
            objConnection.Open();
            objDataReader = objCommand.ExecuteReader();

            Boolean find = false;



            while (objDataReader.Read() == true)

            {

                strResultsHolder += "<h2>" + objDataReader[2].ToString() + "</h2>";
                strResultsHolder += "<h2>" + objDataReader[3].ToString() + "</h2>";
                find = true;
            }


            objDataReader.Close();
            objConnection.Close();


            if (find == false)
            {
                strResultsHolder = "No Event Scheduled for Today down!!!";
            }

            Event.Text = strResultsHolder;

        }


    </script>



</head>

<body>

    <form runat="server">





        <asp:Calendar ID="Calendar1"
            runat="server" BackColor="#FFFFCC" BorderColor="#FFCC66" BorderWidth="1px" DayNameFormat="Shortest" Font-Names="Verdana" Font-Size="8pt" ForeColor="#663399" Height="200px" ShowGridLines="True" Width="220px">

            <DayHeaderStyle BackColor="#FFCC66" Font-Bold="True" Height="1px" />
            <NextPrevStyle Font-Size="9pt" ForeColor="#FFFFCC" />
            <OtherMonthDayStyle ForeColor="#CC9966" />
            <SelectedDayStyle BackColor="#CCCCFF" Font-Bold="True" />
            <SelectorStyle BackColor="#FFCC66" />
            <TitleStyle BackColor="#990000" Font-Bold="True" Font-Size="9pt" ForeColor="#FFFFCC" />


        </asp:Calendar>
        <asp:AccessDataSource ID="dsEvent" runat="server" DataFile="~/CustomerTable.mdb"
            SelectCommand="SELECT * FROM [CalenderEvent]">
           
        </asp:AccessDataSource>

        <h2>
            <asp:Label runat="server" ID="Event" />
        </h2>
        

        <br />
        <asp:Label runat="server" ID="Result" />

        <br />
        <asp:Label runat="server" ID="test" />




    </form>

</body>
</html>

