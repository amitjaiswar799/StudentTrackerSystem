<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Student</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet">
<style>
.card{
margin :auto;
margin-top : 100px;
width : 450px;
height : 450px;
}
</style>
</head>
<body>
 
 <div class="card">
 <div class="card-body">
     <form action="studentController" >
     <!-- hidden -->
     <input type="hidden" name="command" value="UPDATE">
     <input type="hidden" name="studentId" value="${THE_STUDENT.id }">
     
     <div class="container">
          <h3 class="text-center">Add Student</h3>
          <h6 class="fw-light text-center">it's quick and easy</h6>
     </div>
     
     <hr>
     
    <div class="mb-3">
     <label class="form-label">Enter Your First Name</label>
     <input type="text" class="form-control" name="fn" value="${THE_STUDENT.firstName }">
    </div>

    <div class="mb-3">
     <label class="form-label">Enter Your Last Name</label>
     <input type="Text" class="form-control" name="ln" value="${THE_STUDENT.lastName }">
    </div>
    
     <div class="mb-3">
     <label class="form-label">Enter Your Email</label>
     <input type="email" class="form-control" name="email" value="${THE_STUDENT.email }">
    </div>
     
     <div class="btn1 text-center ">
    <button type="submit" class="btn btn-success fw-semibold my-3" style="width: 250px;">Save</button>
    </div>
       </form> 
 </div>
 </div>
</body>
</html>