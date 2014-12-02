	<%@page import="mail.SendEmail" %>
	<!DOCTYPE html>
	<html>
	    <head>
	        
	        <title>Email</title>
	    </head>
	    <body>
	        
	        <div id="container">
	            <p class="heading">Send Email</p>
	            <form action="index.jsp" method="POST">
	                <div class="input">
	                    <label for="fullname">Name:</label>
	                    <input type="text" id="fullname" name="fullname" value="" />
	                    <div id="fullname_warn" ></div>
	                </div>
	 
	                <div class="input">
	                    <label for="email" >Email:</label>
	                    <input type="text" id="email" name="email" value="" />
	                    <span id="email_warn" ></span>
	                </div>
	 
	                <div class="input">
	                    <label for="message" >Message:</label>
	                    <textarea cols="10" rows="5" id="message" name="message"></textarea>
	                </div>
	 
	                <input type="submit" name="send" value="send" id="send" />
	            </form>
	 
	            <%
	        if (request.getParameter("fullname") != null) {
	            String fullname = request.getParameter("fullname");
	            String email = request.getParameter("email");
	            String message = request.getParameter("message");
	 
	            if (mail.SendEmail.run(email, fullname, message)) {
	            %>
	            <p class="goodtogo end">Mail Sent Successfully</p>
	            <%} else {%>
	            <p class="warning end">Error Sending Mail</p>
	            <%}
	        }
	            %>
	 
	        </div>
	        <script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
	        <script type="text/javascript" src="assets/js/jscript.js"></script>
	    </body>
	</html>