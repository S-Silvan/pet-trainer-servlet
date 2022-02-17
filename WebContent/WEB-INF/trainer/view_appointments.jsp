<%@page import="java.util.List" %>
<%@page import="model.Appointment" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Appointments</title>

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
                                <a class="nav-link" href="<%=request.getContextPath() %>/thome">Home</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link active" aria-current="page" href="#">Appointments</a>
                              </li>
                              <li class="nav-item">
                                <a class="nav-link" href="<%=request.getContextPath() %>/tlogout">Logout</a>
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
            			<table class="table table-bordered mt-5">
            				<thead class="table-dark">
            					<tr>
            						<td>Date</td>
            						<td>Slot</td>
            						<td>Status</td>
            						<td>Client</td>
            						<td>Contact</td>
            						<td>Email</td>
            						<td>Address</td>
            						<td>Pet</td>
            						<td>Type</td>
            						<td>Breed</td>
            						<td>Actions</td>
            					</tr>
            				</thead>
            				<tbody>
								<% if(request.getAttribute("appointmentBookings")!=null){
									List<Appointment> bookingList=(List<Appointment>) request.getAttribute("appointmentBookings");
									for(Appointment booking:bookingList){ %>
            					<tr>
            						<td><%=booking.getDate() %></td>
            						<td><%=booking.getSlot() %></td>
            						<td><%=booking.getStatus() %></td>
            						<td><%=booking.getClient().getName() %></td>
            						<td><%=booking.getClient().getPhoneNumber() %></td>
            						<td><%=booking.getClient().getEmail() %></td>
            						<td><%=booking.getClient().getAddress() %></td>
            						<td><%=booking.getPet().getName() %></td>
            						<td><%=booking.getPet().getType() %></td>
            						<td><%=booking.getPet().getBreed() %></td>
            						<td>
            							<%if(!booking.getStatus().equalsIgnoreCase("completed") && !booking.getStatus().equalsIgnoreCase("cancelled")){ %>
            							<form action="<%=request.getContextPath() %>/tupdate-appointment-status" method="post">
            								<input type="hidden" name="ap-id" value="<%=booking.getId() %>">
            								<button class="btn btn-danger">Complete</button>
            							</form>
            							<%} %>
            						</td>
            					</tr>
								<%  	}
									}%>
            				</tbody>
            			</table>
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