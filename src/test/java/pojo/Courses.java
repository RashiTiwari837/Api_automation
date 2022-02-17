package pojo;

import java.util.List;

public class Courses {
private List<webAutomation> WebAutomation;
private List<api> Api;
private List <mobile> Mobile;

public List<webAutomation> getWebAutomation() {
	return WebAutomation;
}
public void setWebAutomation(List<webAutomation> WebAutomation) {
	this.WebAutomation =  WebAutomation;
}
public List<api> getapi() {
	return Api;
}
public void setApi(List<api> api) {
	this.Api =  api;
}
public List<mobile> getMobile() {
	return Mobile;
}
public void setMobile(List<mobile> Mobile) {
	this.Mobile = Mobile;
}

}
