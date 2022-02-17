<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Login</title>

    <!--Stylesheet-->
    <link rel="stylesheet" href="../css/style.css">
    <!--Bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
    rel="stylesheet" 
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" 
    crossorigin="anonymous">
</head>
<body class="bg-warning">
    
    <section id="form-section">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <div class="form-container shadow-md p-5 bg-white rounded bg-white mt-5 mx-auto" style="max-width:400px">
                        <h3 class="text-center">Pet Trainer</h3>
                        <h5 class="text-center mb-3">Sign in</h5>
                        <p class="text-center">Using Pet trainer account credentials</p>
                        <form action="<%=request.getContextPath() %>/cauthenticate" method="post">
                            <div class="form-group">
                                <label for="user-id">User Id</label>
                                <input type="text" name="user-id" id="user-id" class="form-control" required="true" autocomplete="on">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" class="form-control" required="true">
                            </div>
                            <div class="d-flex justify-content-between mt-2">
                                <a href="register.jsp" class="align-self-center"><p>Create Account</p></a>
                                <div class="form-group">
                                    <button class="btn btn-primary">Login</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </section>

    <!--Script-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" 
    crossorigin="anonymous"></script>
</body>
</html>