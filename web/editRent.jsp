<%-- 
    Document   : editRent
    Created on : Oct 31, 2015, 8:12:13 AM
    Author     : Geofrey
--%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8" />
    <title>Edit Rent</title>
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
      <!-- PAGE LEVEL STYLES -->
    <link href="assets/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <link type="text/css" rel="stylesheet" href="datepicker/css/bootstrap-datepicker.css">
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
        
           <!--EDIT Rent MANAGEMENT========================================-->
      
               
        <div id="EditHouse" class="tab-pane active">
            <form action="saveHouse" class="form-signin" id="">
                
                 <br>
                  <p class="text-muted text-center btn-block btn btn-primary btn-rect">Edit House .</p>
                
                <br>
      <table style="width : 600px;"><tr><td><b>Choose Date  <font color="red">*</font>:</b></td><td>       <input type="text" placeholder="" name="yearMonth" readonly="true" required="true" autocomplete="off" id="yearMonth" class="form-control" />
             </td></tr></table> 
                  <br> 
               
              <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Plot Name</th>
                                            <th>House Name</th>
                                            <th>Room Name</th>
                                            <th>Year-Month</th>
                                            <th>Amount paid</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="rent">
                                        <tr><td colspan="8" style="text-align: center;">No data to display.</td></tr>
                                    </tbody>
                                </table>
                                </div>
                  </div>
            
            </form>
        </div>
      
        <!--END PAGE CONTENT -->
    </div>
        
      <!--modals-->
    
      <div class="modal fade" id="editRentModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      </div>
      <!--end modals-->
        
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
          <!-- PAGE LEVEL SCRIPTS -->
    <script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
    <script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
      <script src="datepicker/js/bootstrap-datepicker.js"></script>
     <script type="text/javascript">
         $(document).ready(function(){
             
              $('#yearMonth').datepicker({ 
    format: "yyyy-mm",
    viewMode: "months", 
    minViewMode: "months"
});

       $("#yearMonth").change(function(){
         
          loadRent();   
           });
            });
            
            function loadRent(){
                var yearMonth=$("#yearMonth").val();
           $.ajax({
               url:"datatableRent?yearMonth="+yearMonth,
               type:"post",
               dataType:"html",
               success:function(data){
                
            $("#rent").html(data);
            $('#dataTables').dataTable();
             } 
           });      
            }         
 
  function editor(pos){
  var id=$("#val_"+pos).val();  
      $.ajax({
               url:"editRent?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
$('#editRentModal').html(data);
$('#editRentModal').modal({backdrop: 'static',keyboard: false});
$('#editRentModal').modal('show');
             } 
           }); 
    
    return false;
  }
  
//  delete plots
  function deletor(pos){
      var id=$("#val_"+pos).val();  
      $.ajax({
               url:"deleteRent?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
              
        $.notify({message: data});  
              loadRent();
             } 
           });
     
     return false;
  }
  
         </script>
</body>
</html>