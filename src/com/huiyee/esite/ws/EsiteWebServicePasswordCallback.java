package com.huiyee.esite.ws;

import java.io.IOException;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.UnsupportedCallbackException;
import org.apache.ws.security.WSPasswordCallback;


public class EsiteWebServicePasswordCallback implements CallbackHandler {

	private String name;
	private String password;
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
    	WSPasswordCallback pc = (WSPasswordCallback) callbacks[0];
    	pc.setIdentifier(name);
        pc.setPassword(password);
    }
}
