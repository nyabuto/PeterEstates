<%-- 
    Document   : RoomManagement
    Created on : Oct 19, 2015, 8:06:23 PM
    Author     : Geofrey
--%>
<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8" />
    <title>Manage Rooms</title>
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
        
              <div class="tab-content">
        <div id="AddNewPlot" class="tab-pane active">
            <form action="savePlot" class="form-signin" id="savePlot">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect"> Add New Plot .</p>
                <br>
                <input type="text" placeholder="Enter plot name" class="form-control" autocomplete="off" id="newplot" name="newplot" list="allPlots" required="true" />
                <datalist id="allPlots">
                </datalist>
               <br>
                <button class="btn text-muted text-center btn-danger" type="submit" id="savePlot">Add Plot</button>
            </form>
        </div>
        <div id="AddNewHouse" class="tab-pane">
            <form action="saveHouse" class="form-signin" id="saveHouse">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Add New House .</p>
               <br>
                <select name="plot2" id="plot2" class="form-control" required="true">
                    <option value="">Choose plot</option>
                </select>
               <input type="text"  required="true" id="housename" name="housename" autocomplete="off" list="allHouses" placeholder="House name here"  class="form-control" />
               <datalist id="allHouses">
                </datalist>
                <br />
                <button class="btn text-muted text-center btn-success" type="submit" id="saveHouse">Add house</button>
            </form>
        </div>
        <div id="AddNewRoom" class="tab-pane">
            <form action="saveRoom" class="form-signin" id="saveRoom">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Add New Room .</p>
                <br>
                 <select name="plot3" id="plot3" class="form-control" required="true">
                    <option value="">Choose plot</option>
                </select>
                <br>
                 <select name="house3" id="house3" class="form-control" required="true">
                    <option value="">Choose house</option>
                </select>
                
                <input type="text" placeholder="Enter room name" name="room" required="true" autocomplete="off" id="room" list="allRooms" class="form-control" />
                <datalist id="allRooms">
                </datalist>
                
                <br>
                <button class="btn text-muted text-center btn-success" type="submit" id="saveRoom">Add Room</button>
            </form>
        </div>
            
                  
                  <!--EDIT ROOM MANAGEMENT========================================-->
                  
                  <div id="EditPlot" class="tab-pane">
            <form action="savePlot" class="form-signin" id="">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect"> Edit Plot .</p>
                <br>
              <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Plot Name</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="loadedEditPlots">
                                        <tr><td colspan="4">Loading data ...</td></tr>
                                    </tbody>
                                </table>
                                </div>
                  </div>
            </form>
        </div>
        <div id="EditHouse" class="tab-pane">
            <form action="saveHouse" class="form-signin" id="">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Edit House .</p>
                
                <br>
              <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables2">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Plot Name</th>
                                            <th>House Name</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="loadedEditHouses">
                                        <tr><td colspan="5">Loading data ...</td></tr>
                                    </tbody>
                                </table>
                                </div>
                  </div>
            
            </form>
        </div>
        <div id="EditRoom" class="tab-pane">
         <form action="saveRoom" class="form-signin" id="">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Edit Rooms details .</p>
                
                <br>
              <div class="panel-body">
                            <div class="table-responsive">
                                <table class="table table-striped table-bordered table-hover" id="dataTables3">
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>Plot Name</th>
                                            <th>House Name</th>
                                            <th>Room Name</th>
                                            <th>Edit</th>
                                            <th>Delete</th>
                                            
                                        </tr>
                                    </thead>
                                    <tbody id="loadedEditRooms">
                                        <tr><td colspan="6">Loading data ...</td></tr>
                                    </tbody>
                                </table>
                                </div>
                  </div>
            
            </form>
        </div>
                  
                  <!--SET RENT HERE=========================================-->
                  
                   <div id="SetRent" class="tab-pane">
            <form action="saveRoom" class="form-signin" id="saveRent">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Set Rent.</p>
                <br>
                 <select name="plot4" id="plot4" class="form-control" required="true">
                    <option value="">Choose plot</option>
                </select>
                <br>
                 <select name="house4" id="house4" class="form-control" required="true">
                    <option value="">Choose house</option>
                </select>
                <br>
                 <div class="form-group input-group">
                 <span class="input-group-addon">Ksh. </span>
                <input type="text" placeholder="Enter rent" name="rent" required="true" autocomplete="off" id="rent" class="form-control" />
                 <span class="input-group-addon">.00</span>
                 </div>
                <datalist id="allRooms">
                </datalist>
                
                <br>
                <button class="btn text-muted text-center btn-success" type="submit" id="saveRoom">Update</button>
            </form>
        </div>
                  
    </div>
    <div class="text-center">
        <ul class="list-inline">
            <li><a class="text-muted" href="#AddNewPlot" data-toggle="tab">Add Plots</a></li>
            <li><a class="text-muted" href="#AddNewHouse" data-toggle="tab">Add Houses</a></li>
            <li><a class="text-muted" href="#AddNewRoom" data-toggle="tab">Add Rooms</a></li>
            
            <li><a class="text-muted" href="#EditPlot" data-toggle="tab">Edit Plots</a></li>
            <li><a class="text-muted" href="#EditHouse" data-toggle="tab">Edit Houses</a></li>
            <li><a class="text-muted" href="#EditRoom" data-toggle="tab">Edit Rooms</a></li>
            
            <li><a class="text-muted" href="#SetRent" data-toggle="tab">Set Rent</a></li>
        </ul>
    </div>
            
            
        </div>
        <!--END PAGE CONTENT -->
    </div>
        
      <!--modals-->
    
       <div class="modal fade" id="editPlotModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      </div>
      
      <div class="modal fade" id="editHouseModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      </div>
      <div class="modal fade" id="editRoomModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
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
     <script>
         $(document).ready(function () {
           loadEditPlots();
           loadEditHouses();
           loadEditRooms();
         });
    </script>
     <script type="text/javascript">
         $(document).ready(function(){
            loadPlots();
//            loadHouses();
            listPlots();
            listHouses();
            listRooms();
         $("#savePlot").submit(function(){
      var plot=$("#newplot").val();
       $.ajax({
               url:"savePlot?plotname="+plot,
               type:"post",
               dataType:"html",
               success:function(data){
               $.notify({message: data });  
               $("#newplot").val("");
               loadPlots();
               listPlots();
             } 
           });
        
        return false;
         });
         
       $("#saveHouse").submit(function(){
      var plot=$("#plot2").val();
      var housename=$("#housename").val();
       $.ajax({
               url:"saveHouse?plotid="+plot+"&&housename="+housename,
               type:"post",
               dataType:"html",
               success:function(data){
               $.notify({message: data });  
               $("#housename").val("");
               listHouses();
               loadHouses();
             } 
           });
        
        return false;
         });
       
         $("#saveRoom").submit(function(){
      var houseid=$("#house3").val();
      var room=$("#room").val();
       $.ajax({
               url:"saveRoom?houseid="+houseid+"&&room="+room,
               type:"post",
               dataType:"html",
               success:function(data){
               $.notify({message: data });  
               $("#room").val("");
               listRooms();
             } 
           });
        
        return false;
         });
         
          $("#saveRent").submit(function(){
      var houseid=$("#house4").val();
       var rent=$("#rent").val();
       $.ajax({
               url:"saveRent?rent="+rent+"&&houseid="+houseid,
               type:"post",
               dataType:"html",
               success:function(data){
               $.notify({message: '<font color="green">Rent updated successfully.</font>' });  
               
             } 
           });
        
        return false;
         });
         
         
         $("#plot3").change(function(){
          loadHouses();   
           });
           $("#plot4").change(function(){
          loadHouses2();   
           });
           $("#house4").change(function(){
          loadRent();   
           });
            });
            
            function loadRent(){
                var houseid=$("#house4").val();
           $.ajax({
               url:"loadRent?houseid="+houseid,
               type:"post",
               dataType:"html",
               success:function(data){
//                   alert("data : "+data);
            $("#rent").val(data);
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
            
               $("#plot2").html(data);
               $("#plot3").html(data);
               $("#plot4").html(data);
             } 
           });   
}          
function loadHouses(){
                  var plotid=$("#plot3").val();
         $.ajax({
               url:"loadHouses?plotid="+plotid,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#house3").html(data); 
             } 
           });      
            }
            
            function loadHouses2(){
                  var plotid=$("#plot4").val();
         $.ajax({
               url:"loadHouses?plotid="+plotid,
               type:"post",
               dataType:"html",
               success:function(data){
               $("#house4").html(data); 
             } 
           });      
            }
            
            
function listPlots(){
    //        LOAD ALL PLOTS IN THE DATABASE==============================
 $.ajax({
               url:"listPlot",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#allPlots").html(data); 
             } 
           });
}
function listRooms(){
 //           LOAD ROOMS in the DATABASE================================
$.ajax({
               url:"listRooms",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#allRooms").html(data); 
             } 
           });
     }
function listHouses(){
      //           LOAD HOUSES in the database==================================

$.ajax({
               url:"listHouses",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#allHouses").html(data); 
             } 
           });
  }
  
  function loadEditPlots(){
  $.ajax({
               url:"loadEditPlots",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#loadedEditPlots").html(data);
               $('#dataTables').dataTable();
             } 
           });    
  }
  
    function loadEditHouses(){
  $.ajax({
               url:"loadEditHouses",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#loadedEditHouses").html(data);
               $('#dataTables2').dataTable();
             } 
           });    
  }
    function loadEditRooms(){
  $.ajax({
               url:"loadEditRooms",
               type:"post",
               dataType:"html",
               success:function(data){
               $("#loadedEditRooms").html(data);
               $('#dataTables3').dataTable();
             } 
           });    
  }
  
  function editor(pos){
  var id=$("#val_"+pos).val();  
      $.ajax({
               url:"editName?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
//$('#myModal').modal('toggle');
//$('#myModal').modal('show');
//$('#myModal').modal('hide');
$('#editPlotModal').html(data);
$('#editPlotModal').modal({backdrop: 'static',keyboard: false});
$('#editPlotModal').modal('show');
             } 
           }); 
    
    return false;
  }
  
    function editor2(pos){
  var id=$("#valhs_"+pos).val();  
      $.ajax({
               url:"editHouseData?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
$('#editHouseModal').html(data);
$('#editHouseModal').modal({backdrop: 'static',keyboard: false});
$('#editHouseModal').modal('show');
             } 
           }); 
    
    return false;
  }
  function editor3(pos){
  var id=$("#valrm_"+pos).val();  
      $.ajax({
               url:"editRoomData?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
//                   alert(data);
$('#editRoomModal').html(data);
$('#editRoomModal').modal({backdrop: 'static',keyboard: false});
$('#editRoomModal').modal('show');
             } 
           }); 
    
    return false;
  }
  
  
  function savePlotUpdates(){
      var id,name;
    id=$("#editplotid").val();
    name=$("#editplotname").val();
    $('#editPlotModal').modal('hide');
//    alert("id is : "+id+"    name is : "+name);
    $.ajax({
               url:"saveEditedPlot?id="+id+"&&name="+name,
               type:"post",
               dataType:"html",
               success:function(data){
              loadEditPlots();
              $.notify({message: data}); 
             } 
           }); 
    
  }
  
    function saveHouseUpdates(){
//        alert("called");
      var id,name,plot_id;
    id=$("#editplotid2").val();
    plot_id=$("#editablePlot").val();
    name=$("#edithousename").val();

    $('#editHouseModal').modal('hide');
//    alert("id is : "+id+" plotis : "+plot_id+"    name is : "+name);
    $.ajax({
               url:"saveEditedHouse?id="+id+"&&name="+name+"&&plot_id="+plot_id,
               type:"post",
               dataType:"html",
               success:function(data){
              loadEditHouses();
              $.notify({message: data}); 
             } 
           }); 
    
  }
  
      function saveRoomUpdates(){
//        alert("called");
      var id,name,house_id;
    id=$("#editroomid").val();
    house_id=$("#editableHouse").val();
    name=$("#editroomname").val();
    $('#editRoomModal').modal('hide');
//    alert("id is : "+id+" plotis : "+plot_id+"    name is : "+name);
    $.ajax({
               url:"saveEditedRoom?id="+id+"&&name="+name+"&&house_id="+house_id,
               type:"post",
               dataType:"html",
               success:function(data){
              loadEditRooms();
              $.notify({message: data}); 
             } 
           }); 
    
  }
  
//  delete plots
  function deletor(pos){
      var id=$("#val_"+pos).val();  
      $.ajax({
               url:"deletePlot?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
              
        $.notify({message: data});  
              loadEditPlots();
              loadPlots();
              listPlots(); 
               
             } 
           });
     
     return false;
  }
//  delete houses========================================
    function deletor2(pos){
      var id=$("#valhs_"+pos).val();  
      $.ajax({
               url:"deleteHouse?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
              
        $.notify({message: data});  
              loadEditHouses();
              loadHouses();
              loadHouses2();
              listHouses(); 
               
             } 
           });
     
     return false;
  }
  
//  delete rooms-------------------------------
  function deletor3(pos){
      var id=$("#valrm_"+pos).val();  
      $.ajax({
               url:"deleteRoom?id="+id,
               type:"post",
               dataType:"html",
               success:function(data){
              
        $.notify({message: data});  
              loadEditRooms();
              loadRooms();
              listRooms(); 
               
             } 
           });
     
     return false;
  }
  
         </script>
</body>
</html>