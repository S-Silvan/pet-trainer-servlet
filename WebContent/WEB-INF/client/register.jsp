<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Client Register</title>

    <!--Stylesheet-->
    <link rel="stylesheet" href="css/style.css">
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
                    <div class="form-container shadow-md p-5 bg-white rounded bg-white mx-auto" style="max-width:450px">
                        <h3 class="text-center">Pet Trainer</h3>
                        <h5 class="text-center mb-3">Sign up</h5>
                        <p class="text-center">New client registration form</p>
                        <form action="<%=request.getContextPath() %>/cinsert-client" method="post">
                            <h5>User Details</h5>
                            <div class="form-group">
                                <label for="name">Name</label>
                                <input type="text" name="name" id="name" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                <label for="phone-number">Phone Number</label>
                                <input type="tel" name="phone-number" id="phone-number" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                <label for="email">Email</label>
                                <input type="email" name="email" id="email" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                <label for="address">Address</label>
                                <textarea name="address" id="address" class="form-control" rows="2"></textarea>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" name="password" id="password" class="form-control" required="true">
                            </div>

                            <h5>Pet Details</h5>
                            <div class="form-group">
                                <label for="pet-name">Pet Name</label>
                                <input type="text" name="pet-name" id="pet-name" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                <label for="pet-type">Pet Type</label>
                                <select name="pet-type" id="pet-type" class="form-select" required="true">
                                    <option value="cat">Cat</option>
                                    <option value="dog">Dogs</option>
                                </select>
                            </div>
                            <div class="form-group">
                                <label for="pet-breed">Pet Breed</label>
                                <input type="text" name="pet-breed" id="pet-breed" class="form-control" required="true">
                            </div>
                            <div class="form-group">
                                <label for="pet-dob">Pet Date of Birth</label>
                                <input type="date" name="pet-dob" id="pet-dob" class="form-control" required="true">
                            </div>

                            <div class="d-flex justify-content-between">
                                <a href="client/login.jsp" class="align-self-center"><p>Login</p></a>
                                <div class="form-group">
                                    <button class="btn btn-primary">Register</button>
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