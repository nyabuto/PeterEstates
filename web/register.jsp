<!DOCTYPE html>
<html lang="en"> 
<head>
    <meta charset="UTF-8" />
    <title>Register user</title>
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
             
      <div id="signup" class="tab-pane" style="width:800px;">
            <form action="register" class="form-signin">
                <p class="text-muted text-center btn-block btn btn-primary btn-rect"><font color="red">*</font> Please enter all details to register.</p>
                <input type="text" placeholder="First Name" class="form-control" name="fname" id="fname" required maxlength="20" />
                 <input type="text" placeholder="Last Name" class="form-control" name="lname" id="lname" required maxlength="20" />
                <input type="text" placeholder="Username" class="form-control" name="username" id="username" required maxlength="20" />
                <!--<input type="email" placeholder="Your E-mail" class="form-control" />-->
                <input type="password" placeholder="password" oninput="checkPasswords()" class="form-control" name="password" id="password" required maxlength="20" />
                <input type="password" placeholder="Re type password" oninput="checkPasswords()" class="form-control" name="password2" id="password2" required maxlength="20" />
                <button class="btn text-muted text-center btn-success" type="submit">Register</button>
            </form>
        </div>
        </div>
        <!--END PAGE CONTENT -->
    </div>
 <!-- FOOTER -->
    <div id="footer">
        <p>&copy;  Real Estate Management System</p>
    </div>
    <!--END FOOTER -->

    <!-- GLOBAL SCRIPTS -->
    <script src="assets/plugins/jquery-2.0.3.min.js"></script>
     <script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
      <script src="assets/js/login.js"></script>
      <script src="notify/bootstrap-notify.min.js"></script>
    <script type="text/javascript">
            function checkPasswords() {
                var password = document.getElementById('password');
                var conf_password = document.getElementById('password2');

                if (password.value != conf_password.value) {
                    conf_password.setCustomValidity('Passwords do not match');
                } else {
                    conf_password.setCustomValidity('');
                }
            }      
        </script> 
        
        <% if(session.getAttribute("userAdded")!=null){%>
      <script type="text/javascript">
         $.notify({
	// options
	message: '<%=session.getAttribute("userAdded")%>' 
},{
	// settings
//	type: 'danger',
//        center:true
});  
           </script>
            
            <% }session.removeAttribute("userAdded");%>
</body>
</html>