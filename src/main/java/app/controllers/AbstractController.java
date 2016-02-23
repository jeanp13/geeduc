package app.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import app.exception.AppException;

public class AbstractController {
	
	private ThreadLocal<List<String>> warningMessagesThread= new ThreadLocal<>();
	private ThreadLocal<List<String>> errorMessagesThread= new ThreadLocal<>();
	private ThreadLocal<List<String>> successMessagesThread= new ThreadLocal<>();
	
	@Autowired
	private ReloadableResourceBundleMessageSource messageSource;

	public String getMensagem(String chave, Object... parametros) {
		return messageSource.getMessage(chave, parametros, LocaleContextHolder.getLocale());
	}

	@ExceptionHandler(AppException.class)
	public ControllerResponse handleAppExceptions(AppException exception, WebRequest request, HttpServletResponse response) {
		System.out.println(exception.getMessage());
		addErrorMessage(exception.getMessage());
		//getErrorMessages().add(exception.getMessage());
		response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		return response(null);
	}

	protected ControllerResponse response(Object content) {
		
		ControllerResponse response = new ControllerResponse();
		
		response.setData(content);
		
		// Get runtime exceptions from local thread
		response.setErrorMessages(new ArrayList<>(getErrorMessages()));
		
		// Get warning messages normally sent by controllers
		response.setWarningMessages(new ArrayList<>(getWarningMessages()));
		
		// Get success messages sent by controller
		response.setSuccessMessages(new ArrayList<>(getSuccessMessages()));

		// After getting all messages clear them all to avoid duplicates
		this.clearMessages();

		//Get authenticated user from security context
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication != null) {
			UserInfo user = new UserInfo();
			user.setUsername(authentication.getName());
			// Get user authorities from security context
			for (GrantedAuthority authority : authentication.getAuthorities()) {
				user.addRole(authority.getAuthority());
			}
			response.setUser(user);
		}
		return response;
	}
	
	protected void clearMessages(){
		this.warningMessagesThread.set(null);
		this.errorMessagesThread.set(null);
		this.successMessagesThread.set(null);
	}
	
	protected void addSuccessMessage(String successMessage,Object... parametros){
		getSuccessMessages().add(getMensagem(successMessage, parametros));
	}
	
	protected void addWarningMessage(String warningMessage, Object... parametros){
		getWarningMessages().add(getMensagem(warningMessage, parametros));
	}
	
	protected void addErrorMessage(String errorMessage, Object... parametros){
		getErrorMessages().add(getMensagem(errorMessage, parametros));
	}
	
	protected List<String> getSuccessMessages() {
		if (this.successMessagesThread.get() == null) {
			this.successMessagesThread.set(new ArrayList<>());
		}
		return this.successMessagesThread.get();
	}
	
	protected List<String> getErrorMessages() {
		if (this.errorMessagesThread.get() == null) {
			this.errorMessagesThread.set(new ArrayList<>());
		}
		return this.errorMessagesThread.get();
	}
	
	protected List<String> getWarningMessages() {
		if (this.warningMessagesThread.get() == null) {
			this.warningMessagesThread.set(new ArrayList<>());
		}
		return this.warningMessagesThread.get();
	}

}
