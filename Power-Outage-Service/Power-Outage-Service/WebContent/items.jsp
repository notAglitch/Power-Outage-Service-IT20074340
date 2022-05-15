<%@ page import="model.Outage" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Outage Management</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="Components/jquery-3.6.0.js"></script>
<script src="Components/items.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Power Outage Management  </h1>
<form id="formItem" name="formItem">
 Customer ID: 
 <input id="cusID" name="cusID" type="text" 
 class="form-control form-control-sm">
 <br>Customer Name: 
 <input id="cusName" name="cusNam" type="text" 
 class="form-control form-control-sm">
 <br> OutAge Area: 
 <input id="outArea" name="outArea" type="text" 
 class="form-control form-control-sm">
 <br> Outage Date: 
 <input id="outDate" name="outDate" type="text" 
 class="form-control form-control-sm">
 <br> Outage Time: 
 <input id="outTime" name="outTime" type="text" 
 class="form-control form-control-sm">
 <br> Outage Description: 
 <input id="outDescription" name="outDescription" type="text" 
 class="form-control form-control-sm">
 <br>
 
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 <input type="hidden" id="hidItemIDSave" 
 name="hidItemIDSave" value="">
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divItemsGrid">
 <%
       Outage itemObj =new Outage(); 
	 out.print(itemObj.readOutages()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>
