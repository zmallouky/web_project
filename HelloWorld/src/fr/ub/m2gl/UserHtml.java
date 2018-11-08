package fr.ub.m2gl;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/form")
public class UserHtml {

	public String form() {

		return "<html>"+"<head>"+"<script> "

				
				+ "function loadDoc() {\n"  
                + "var fname = document.getElementById(\"firstname\").value;"
                + "var lname = document.getElementById(\"lastname\").value;"
				+ "alert(fname);"
                +
				"  var xhttp = new XMLHttpRequest();\n" + 
				"  xhttp.onreadystatechange = function() {\n" + 
				"  };\n" + 
				"  xhttp.open(\"POST\", \"http://localhost:8080/HelloWorld/User/add\", true);\n" + 
				"  xhttp.setRequestHeader(\"Content-type\", \"application/x-www-form-urlencoded\");\n" + 
				"  xhttp.send(\"firstname=\"+fname+\"lastname=\"+lname);\n"  
				+"}"
				+ "</script>"+
		"</head>" + "<body>"
				+ "  First name:<br>\n" + "  <input type=\"text\" id=\"firstname\" value=\"\">\n" + "  <br>\n"
				+ "  Last name:<br>\n" + "  <input type=\"text\" id=\"lastname\" value=\"\">\n" + "  <br><br>\n"
				+ "  <input type=\"button\" onclick=\"loadDoc()\" value=\"Add User\">\n" + "</body>" + "</html>";

	}

	@GET
	@Produces("text/html")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public String getHelloWorld() {
		return form();
	}
}
