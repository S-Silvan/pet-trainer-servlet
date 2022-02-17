<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Add Trainer</title>

    <!--Stylesheet-->
    <link rel="stylesheet" href="../css/style.css">
    <!--Bootstrap-->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css"
    rel="stylesheet" 
    integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" 
    crossorigin="anonymous">
</head>
<body>
    
    <header class="bg-warning">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <nav class="navbar navbar-expand-lg navbar-dark bg-warning">
                        <div class="container-fluid">
                          <a class="navbar-brand" href="#">Pet Trainer</a>
                          <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="navbar-toggler-icon"></span>
                          </button>
                          <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                              <li class="nav-item">
                                <a class="nav-link" aria-current="page" href="<%=request.getContextPath() %>/ahome">Home</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath() %>/aappointments">Appointments</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath() %>/atrainers">Trainer</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link active" href="#">Add Trainer</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath() %>/alogout">Logout</a>
                              </li>
                            </ul>
                          </div>
                        </div>
                    </nav>
                </div>
            </div>
        </div>
    </header>

    <section id="form">
        <section id="form-section">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <div class="form-container shadow-md p-5 bg-white rounded bg-white mx-auto" style="max-width:450px">
                            <h4 class="text-center mb-4">Appointment Booking</h4>
                            <form action="<%=request.getContextPath() %>/ainsert-trainer" method="post">
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
	                            <div class="form-group">
	                                <label for="type">Type</label>
	                                <select name="type" id="type" class="form-select" required="true">
	                                    <option value="cat">Cat Trainer</option>
	                                    <option value="dog">Dogs Trainer</option>
	                                </select>
	                            </div>
	                            <div class="form-group mt-3">
	                            	<button class="btn btn-primary">Add</button>
	                            </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </section>

    <!--Script-->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" 
    integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" 
    crossorigin="anonymous"></script>
</body>
</html>