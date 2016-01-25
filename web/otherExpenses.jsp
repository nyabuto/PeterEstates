<%-- 
    Document   : otherExpenses
    Created on : Oct 22, 2015, 10:24:58 PM
    Author     : Geofrey
--%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8" />
    <title>Other expenses</title>
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
            <form action="saveOtherExpenses" class="form-signin" id="saveRent">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect"> Rent.</p>
                <br>
                 <select name="expenseType" id="expenseType" class="form-control" required="true">
                    <option value="">Choose expense type</option>
                    <option value="1">Plot Expenses</option>
                    <option value="2">Room Expenses</option>
                </select>
                <br>
                 <select name="plot" id="plot" class="form-control" required="true">
                    <option value="">Choose plot</option>
                </select>
                <br>
                <div id="roomExpense">
                 <select name="house" id="house" class="form-control" required="true">
                    <option value="">Choose house</option>
                </select>
                <br>
                 <select name="room" id="room" class="form-control" required="true">
                    <option value="">Choose Room</option>
                </select>
                <br>
                </div>
                <input type="text" placeholder="" name="yearMonth" readonly="true" required="true" autocomplete="off" id="yearMonth" class="form-control" />
               <br>
               
               <textarea name="reason" id="reason" class="form-control" required placeholder="Reason/justification for the expenses"></textarea>
             
               <input type="text" placeholder="Amount to pay" name="amount" onkeypress="return numbers(event)" maxlength="5" required="true" autocomplete="off" id="amount" class="form-control" />
               <input type="hidden" id="prevdate" name="prevdate" value="">
               
               <br>
                <button class="btn text-muted text-center btn-success" type="submit" id="saveRoom">Update</button>
            </form>
        </div>
      
            
        </div>
        <!--END PAGE CONTENT -->
        <div id="right">
         <div class="chat-panel panel panel-success">
                            <div class="panel-heading">
                                <i class="icon-bell"></i>
                                Existing Expenses.
                            
                            </div>

                            <div class="panel-body">
                                <ul class="chat" id="expenses">
                                    <li class="clearfix">
                                    <div class="chat-body clearfix">
                                       No added expenses.
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
        <script src="notify/bootstrap-notify.min.js"></script>
     <script type="text/javascript">
         $(document).ready(function(){
          $('#yearMonth').datepicker({ 
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
  var yearmonth=$("#yearMonth").val();
    var roomid=$("#room").val();
    if(yearmonth!=="" && roomid!==""){
 loadCost();   
    }  
});

$("#yearMonth").change(function(){
    var prev=$("#prevdate").val();
    var yearmonth=$("#yearMonth").val();
    var roomid=$("#room").val();
    if(prev!==yearmonth){
    if(yearmonth!=="" && roomid!==""){
 loadCost();
    }
     loadNotification();
   $("#prevdate").val(yearmonth);  
 }
 
});
           });
  
  function loadNotification(){
      //           LOAD ROOMS in the DATABASE================================
 var yearMonth=$("#yearMonth").val();
$.ajax({
               url:"viewExpenses?yearMonth="+yearMonth,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#expenses").html(data); 
             } 
           });
  }
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
    <script type="text/javascript">
   $(document).ready(function(){
     $("#roomExpense").prop("hidden","true");
        $("#expenseType").change(function(){
       var expenseType=$("#expenseType").val();
       if(expenseType==="1"){
       $("#roomExpense").prop("hidden","true");
       $("#house").removeAttr("required");
       $("#room").removeAttr("required");
       
       }
       else if(expenseType==="2"){
        $("#roomExpense").removeAttr("hidden");
        $("#house").prop("required","true");
       $("#room").prop("required","true");
        
       }
       else{
           
       }
        });
   });     
        
        </script>
        
          <%if(session.getAttribute("saveExpenses")!=null){%>
      <script type="text/javascript">
         $.notify({message: '<%=session.getAttribute("saveExpenses")%>'});  
           </script>
                <%} session.removeAttribute("saveExpenses");%>
                
                
                
<!--         <script type="text/javascript">
                  $.notify({message: 'here is the message'});  
                    </script>-->
                    
</body>
</html>