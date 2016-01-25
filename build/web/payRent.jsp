<%-- 
    Document   : payRent
    Created on : Oct 21, 2015, 11:28:36 AM
    Author     : Geofrey
--%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8" />
    <title>Pay Rent</title>
    <link rel="shortcut icon" href="images/header.png">
    <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/main.css" />
    <link rel="stylesheet" href="assets/css/theme.css" />
    <link rel="stylesheet" href="assets/css/MoneAdmin.css" />
    <link rel="stylesheet" href="assets/plugins/Font-Awesome/css/font-awesome.css" />
    <link href="assets/css/layout2.css" rel="stylesheet" />
    <link href="assets/plugins/flot/examples/examples.css" rel="stylesheet" />
    <link rel="stylesheet" href="assets/plugins/timeline/timeline.css" />
    <link rel="stylesheet" href="assets/css/login.css" />
    <link rel="stylesheet" href="assets/plugins/magic/magic.css" />
     <link rel="stylesheet" href="notify/animate.css">
      <link type="text/css" rel="stylesheet" href="datepicker/css/bootstrap-datepicker.css">
       <link rel="stylesheet" href="notify/animate.css">
</head>
<body class="padTop53 " >

    <!-- MAIN WRAPPER -->
    <div id="wrap" >
         <!-- HEADER SECTION -->
        <div id="top">

            <nav class="navbar navbar-inverse navbar-fixed-top " style="padding-top: 10px;">
                <a data-original-title="Show/Hide Menu" data-placement="bottom" data-tooltip="tooltip" class="accordion-toggle btn btn-primary btn-sm visible-xs" data-toggle="collapse" href="#menu" id="menu-toggle">
                    <i class="icon-align-justify"></i>
                </a>
                <header class="navbar-header">

                    <a href="index.jsp" class="navbar-brand">
                        Real Estate MGT.
                        </a>
                </header>
               
            </nav>

        </div>

        <!-- MENU SECTION -->
       <div id="left" >
           <%@include file="menu/menu.jsp"%>
        </div>
        <!--END MENU SECTION -->
        <!--PAGE CONTENT -->
        <div id="content">
         
                   <div id="SetRent" class="tab-pane">
            <form action="saveMonthlyRent" class="form-signin" id="saveRent">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect"> Rent.</p>
                <br>
                 <select name="plot" id="plot" class="form-control" required="true">
                    <option value="">Choose plot</option>
                </select>
                <br>
                 <select name="house" id="house" class="form-control" required="true">
                    <option value="">Choose house</option>
                </select>
                <br>
                 <select name="room" id="room" class="form-control" required="true">
                    <option value="">Choose Room</option>
                </select>
                
                <br>
                <input type="text" placeholder="" name="rentMonth" readonly="true" required="true" autocomplete="off" id="rentMonth" class="form-control" />
               <br>
               <!--<table><tr><td style="padding-right: 100px;">Expected amount : <b id="expected"></b></td><td style="padding-right: 100px;" >Amount paid : <b id="paid"></b></td><td style="padding-right: 100px;">Amount remaining : <b id="unpaid"></b></td></tr></table>--> 
               <input type="text" placeholder="Amount to pay" name="rent" onkeypress="return numbers(event)" maxlength="5" required="true" autocomplete="off" id="rent" class="form-control" />
               <input type="hidden" id="balance" name="balance" value="0">
                <datalist id="allRooms">
                </datalist>
                
                <br>
                <button class="btn text-muted text-center btn-success" type="submit" id="saveRoom">Update</button>
            </form>
        </div>
      
            
        </div>
        <!--END PAGE CONTENT -->
        
        
            <!--END PAGE CONTENT -->
        <div id="right">
         <div class="chat-panel panel panel-success">
                            <div class="panel-heading">
                                <i class="icon-bell"></i>
                              Made payments : 
                            
                            </div>

                            <div class="panel-body">
                                <ul class="chat" id="expenses">
                                    <li class="clearfix">
                                    <div class="chat-body clearfix">
                                      <table><tr><td>Expected : </td><td id="expected"></td></tr></table> 
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                    <div class="chat-body clearfix">
                                      <table><tr><td>Paid : </td><td id="paid"></td></tr></table> 
                                        </div>
                                    </li>
                                    <li class="clearfix">
                                    <div class="chat-body clearfix">
                                      <table><tr><td>Balance : </td><td id="unpaid"></td></tr></table> 
                                        </div>
                                    </li>
                                    
                                </ul>
                            </div>

                            <div class="panel-footer">
                                <div class="input-group">
                                 
                                </div>
                            </div>

                        </div>
        </div>
            
    </div>
 <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  Real Estate Management System</p>
    </div>
    <!--END FOOTER -->

    <!-- GLOBAL SCRIPTS -->
    <script src="assets/plugins/jquery-2.0.3.min.js"></script>
     <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script src="assets/plugins/modernizr-2.6.2-respond-1.1.0.min.js"></script>
     <script src="assets/js/login.js"></script>
      <script src="notify/bootstrap-notify.min.js"></script>
      <script src="datepicker/js/bootstrap-datepicker.js"></script>
       
     <script type="text/javascript">
         $(document).ready(function(){
          $('#rentMonth').datepicker({ 
    format: "yyyy-mm",
    viewMode: "months", 
    minViewMode: "months"
});  
loadPlots();
$("#plot").change(function(){
 loadHouses();   
});

$("#house").change(function(){
 loadRooms();   
});

$("#room").change(function(){
  var yearmonth=$("#rentMonth").val();
    var roomid=$("#room").val();
    if(yearmonth!=="" && roomid!==""){
 loadCost();   
    }  
});

$("#rentMonth").change(function(){
    var yearmonth=$("#rentMonth").val();
    var roomid=$("#room").val();
    if(yearmonth!=="" && roomid!==""){
 loadCost();   
    }
});
           });
  
  function loadRooms(){
 //           LOAD ROOMS in the DATABASE================================
 var houseid=$("#house").val();
$.ajax({
               url:"loadRooms?houseid="+houseid,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#room").html(data); 
             } 
           });
     }
  function loadHouses(){
                  var plotid=$("#plot").val();
         $.ajax({
               url:"loadHouses?plotid="+plotid,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#house").html(data); 
             } 
           });      
            }
  function loadPlots(){
            //        LOAD ALL PLOTS IN THE DATABASE to add houses==============================
 $.ajax({
               url:"loadPlots",
               type:"post",
               dataType:"html",
               success:function(data){
             $("#plot").html(data);
             } 
           });   
}
function loadCost(){
    var yearMonth=$("#rentMonth").val();
    var roomid=$("#room").val();
//    alert("year month : "+yearMonth+" room id : "+roomid);
 $.ajax({
               url:"loadHouseRent?roomid="+roomid+"&&yearmonth="+yearMonth,
               type:"post",
               dataType:"html",
               success:function(data){
            var totalRent=0,paid=0,unpaid=0;
            var alldata=data.split("-");
            totalRent=alldata[0];
            paid=alldata[1];
            unpaid=alldata[2];
            $("#expected").html(totalRent);
            $("#paid").html(paid);
            $("#unpaid").html(unpaid);
            
            $("#balance").val(unpaid);
             } 
           });   
}
         </script>
         <script type="text/javascript" language="en">
   function numbers(evt){
var charCode=(evt.which) ? evt.which : event.keyCode
if(charCode > 31 && (charCode < 48 || charCode>57))
return false;
return true;
}
//-->
</script>

<script type="text/javascript">
    $(document).ready(function(){
    
        $("#rent").keyup(function(){
            
            var rent=$("#rent").val();
            if(rent===""){rent=0;}
            var balance=$("#balance").val();
            if(balance===""){balance=0;}
            
            if(parseInt(rent)>parseInt(balance)){
           
           $("#saveRoom").html("Error");
           $("#saveRoom").removeClass("btn-success");
           $("#saveRoom").addClass("btn-danger");
           $("#saveRoom").prop("disabled",true);
            }
            else{
             $("#saveRoom").html("Update");
           $("#saveRoom").removeClass("btn-danger");
           $("#saveRoom").addClass("btn-success");
           $("#saveRoom").prop("disabled",false);    
            }
        });
    });
    </script>
    
     <%if(session.getAttribute("savedRent")!=null){%>
      <script type="text/javascript">
         $.notify({message: '<%=session.getAttribute("savedRent")%>'});  
           </script>
                <%} session.removeAttribute("savedRent");%>
                
                
</body>
</html>