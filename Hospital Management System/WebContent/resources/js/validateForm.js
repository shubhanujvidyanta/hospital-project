$(document).ready(function(){
$("#registrationSubmit").click(function(){
	var firstName=$("#firstName").val();
	var lastName=$("#lastName").val();
	var email=$("#email").val();
	var phone=$("#phone").val();
	var password=$("#password").val();
	var confPassword=$("#confPassword").val();
	
	if (firstName=="" || firstName==null){
		$("#registrationFormError").text("Please fill first name.");
	}
	return false;
});
});