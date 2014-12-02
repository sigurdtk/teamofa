package no.uia.slit.web;
 
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;

 
@ManagedBean
@SessionScoped
public class MessageBean implements Serializable{
	private String messageInput;
 
	public String getmessageInput() {
		return messageInput;
	}
 
	public void setmessageInput(String messageInput) {
		this.messageInput = messageInput;
	}

    }