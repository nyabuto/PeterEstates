<%-- 
    Document   : index
    Created on : Oct 16, 2015, 2:40:37 AM
    Author     : Geofrey
--%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9"> <![endif]-->
<!--[if !IE]><!--> <html lang="en"> <!--<![endif]-->

<!-- BEGIN HEAD -->
<head>
     <meta charset="UTF-8" />
    <title>Real Estate Mgt</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport" />
	<meta content="" name="description" />
	<meta content="" name="author" />
        <link rel="shortcut icon" href="images/header.png">
     <link rel="stylesheet" href="assets/plugins/bootstrap/css/bootstrap.css" />
    <link rel="stylesheet" href="assets/css/login.css" />
    <link rel="stylesheet" href="assets/plugins/magic/magic.css" />
     <link rel="stylesheet" href="notify/animate.css">
</head>
<body >

   <!-- PAGE CONTENT --> 
    <div class="container">
    <div class="text-center">
        <!--<img src="assets/img/logo.png" id="logoimg" alt=" Logo" />-->
        <div style="font-weight:bolder; font-size: 50px;font-family: lucida calligraphy;"> Real Estate Management System. </div>
    </div>
      
        
    <div class="tab-content">
        <div id="login" class="tab-pane active">
            <form action="login" class="form-signin" style="width:800px;">
        <div class="panel panel-default">
             
                        <div class="panel-heading">
                           <p class="text-muted text-center btn-block btn btn-info btn-rect">
                    <font color="red">*</font> Enter username and Password.
                </p>
                        </div>
                        <div class="panel-body">
                       
               
                <input type="text" placeholder="Username" name="username" id="username" class="form-control" />
                <input type="password" placeholder="Password" name="password" id="password" class="form-control" />
               
           
                        </div>
                        <div class="panel-footer">
                       <button class="btn text-muted text-center btn-danger" type="submit">Log in</button>
                        </div>
            
                    </div>
         </form>
        </div>
        <div id="forgot" class="tab-pane">
            <form action="index.html" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Enter your valid e-mail</p>
                <input type="email"  required="required" placeholder="Your E-mail"  class="form-control" />
                <br />
                <button class="btn text-muted text-center btn-success" type="submit">Recover Password</button>
            </form>
        </div>
        <div id="signup" class="tab-pane">
            <form action="index.html" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect">Please Fill Details To Register</p>
                 <input type="text" placeholder="First Name" class="form-control" />
                 <input type="text" placeholder="Last Name" class="form-control" />
                <input type="text" placeholder="Username" class="form-control" />
                <input type="email" placeholder="Your E-mail" class="form-control" />
                <input type="password" placeholder="password" class="form-control" />
                <input type="password" placeholder="Re type password" class="form-control" />
                <button class="btn text-muted text-center btn-success" type="submit">Register</button>
            </form>
        </div>
    </div>
<!--    <div class="text-center">
        <ul class="list-inline">
            <li><a class="text-muted" href="#login" data-toggle="tab">Login</a></li>
            <li><a class="text-muted" href="#forgot" data-toggle="tab">Forgot Password</a></li>
            <li><a class="text-muted" href="#signup" data-toggle="tab">Signup</a></li>
        </ul>
    </div>-->


</div>

	  <!--END PAGE CONTENT -->     
	      
      <!-- PAGE LEVEL SCRIPTS -->
      <script src="assets/plugins/jquery-2.0.3.min.js"></script>
      <script src="assets/plugins/bootstrap/js/bootstrap.js"></script>
   <script src="assets/js/login.js"></script>
      <script src="notify/bootstrap-notify.min.js"></script>
      <!--END PAGE LEVEL SCRIPTS -->

       <%if(session.getAttribute("login")!=null){%>
      <script type="text/javascript">
         $.notify({message: '<%=session.getAttribute("login")%>'});  
           </script>
                <%} session.removeAttribute("login");%>
</body>
    <!-- END BODY -->
</html>
