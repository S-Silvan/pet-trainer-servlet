<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Home</title>

    <!--Stylesheet-->
    <link rel="stylesheet" href="WEB-INF/css/style.css">
    <!--Bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
    rel="stylesheet" 
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" 
    crossorigin="anonymous">
</head>
<body>
    
    <section>
        <div class="container-fluid">
            <img src="WEB-INF/img/banner.jpg" alt="Pet Trainer" style="height: 100vh;">
            <div class="position-absolute bg-white p-4 rounded" style="top:10vh;right:6vw">
                <h1 class="text-warning">Online Pet Trainer Booking</h1>
                <p>Book appointment online</p>
                <ul>
                    <li>Create Account</li>
                    <a href="register.jsp"><button class="btn btn-primary">Register</button></a>
                    <li>Login</li>
                    <a href="client/login.jsp"><button class="btn btn-primary mr-3">Login</button></a>
                </ul>
                <button class="btn btn"></button>
            </div>
        </div>
    </section>

    <!--Script-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" 
    crossorigin="anonymous"></script>
</body>
</html>