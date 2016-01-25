<%-- 
    Document   : menu
    Created on : Oct 16, 2015, 6:02:22 AM
    Author     : Geofrey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

    </head>
    <body>
       <div>
                       <div class="media user-media well-small">
                <a class="user-link" href="#">
                    <img class="media-object img-thumbnail user-img" alt="User Picture" src="assets/img/user.PNG" />
             <!--Real Estate.-->
                </a>
                <br />
                <div class="media-body">
                    <h5 class="media-heading"> Gef</h5>
                    <ul class="list-unstyled user-info">
                        
                        <li>
                             <a class="btn btn-success btn-xs btn-circle" style="width: 10px;height: 12px;"></a> Online
                           
                        </li>
                       
                    </ul>
                </div>
                <br />
            </div>

            <ul id="menu" class="collapse">

                <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#component-nav">
                        <i class="icon-tasks"> </i> Management.    
	   
                        <span class="pull-right">
                          <i class="icon-angle-left"></i>
                        </span>
                       &nbsp; <span class="label label-default">3</span>&nbsp;
                    </a>
                    <ul class="collapse" id="component-nav">
                       
                        <li class=""><a href="register.jsp"><i class="icon-angle-right"></i> Add users. </a></li>
                         <li class=""><a href="RoomManagement.jsp"><i class="icon-angle-right"></i> Room Management </a></li>
                          <li class=""><a href="#"><i class="icon-angle-right"></i> Back up Data </a></li>
                         
                    </ul>
                </li>
                <li class="panel ">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle collapsed" data-target="#form-nav">
                        <i class="icon-pencil"></i> Rent Payment
	   
                        <span class="pull-right">
                            <i class="icon-angle-left"></i>
                        </span>
                          &nbsp; <span class="label label-success">2</span>&nbsp;
                    </a>
                    <ul class="collapse" id="form-nav">
                        <li class=""><a href="payRent.jsp"><i class="icon-angle-right"></i> Pay Rent </a></li>
                        <li class=""><a href="#"><i class="icon-angle-right"></i> Edit Rent </a></li>
                    </ul>
                </li>

                <li class="panel">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#pagesr-nav">
                        <i class="icon-table"></i> Expenses
	   
                        <span class="pull-right">
                            <i class="icon-angle-left"></i>
                        </span>
                          &nbsp; <span class="label label-info">2</span>&nbsp;
                    </a>
                    <ul class="collapse" id="pagesr-nav">
                        <li><a href="otherExpenses.jsp"><i class="icon-angle-right"></i> Add Expenses </a></li>
                        <li><a href="#"><i class="icon-angle-right"></i> Edit Expenses </a></li>
                    </ul>
                </li>
                <li class="panel">
                    <a href="#" data-parent="#menu" data-toggle="collapse" class="accordion-toggle" data-target="#chart-nav">
                        <i class="icon-bar-chart"></i> Reports
	   
                        <span class="pull-right">
                            <i class="icon-angle-left"></i>
                        </span>
                          &nbsp; <span class="label label-danger">5</span>&nbsp;
                    </a>
                    <ul class="collapse" id="chart-nav">



                        <li><a href="#"><i class="icon-angle-right"></i> Income reports </a></li>
                        <li><a href="#"><i class="icon-angle-right"></i> Total Expenses</a></li>
                        <li><a href="#"><i class="icon-angle-right"></i> Surplus/Deficit </a></li>
                        <li><a href="#"><i class="icon-angle-right"></i> Payments report </a></li>
                        <li><a href="#"><i class="icon-angle-right"></i> House rent </a></li>
                    </ul>
                </li>
                
                <li><a href="logout"><i class="icon-signin"></i> Log Out </a></li>

            </ul>

           
       </div>
        
    </body>
</html>
