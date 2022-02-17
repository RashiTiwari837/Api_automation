package pojo;

public class getcourse {
	//shortcut to generate getter setter alt+shift+S
private String instructor;
private String url;
private String services;
private String expertise;
private Courses Courses;
private String linkedIn;
public String getInstructor() {
	return instructor;
}
public void setInstructor(String instructor) {
	this.instructor = instructor;
}
public String getUrl() {
	return url;
}
public void setUrl(String url) {
	this.url = url;
}
public String getServices() {
	return services;
}
public void setServices(String services) {
	this.services = services;
}
public String getExpertise() {
	return expertise;
}
public void setExpertise(String expertise) {
	this.expertise = expertise;
}
public pojo.Courses getCourses() {
	return Courses;
}
public void setCourses( pojo.Courses cour) {
	Courses = cour;
}
public String getLinkedIn() {
	return linkedIn;
}
public void setLinkedin(String linkedin) {
	this.linkedIn = linkedin;
}


//public String getinstructor(String instructor) {
//	return instructor;	
//}
//public String setinstructor(String instructor) {
//	this.instructor=instructor;	
//
}
