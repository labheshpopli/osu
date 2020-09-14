package osu.edu.Model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRawValue;

public class CommonResponse {
	
	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected String status;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected String message;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	@JsonRawValue
	protected String errors;

	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected Object data;
	
	public CommonResponse() {
		this.status = null;
		this.message = null;
		this.errors=null;
		this.data=null;
	}

	public CommonResponse(String status, String message) {
		this.status = status;
		this.message = message;
	}
	 
	public String getErrors() {
		return this.errors;
	}

	public void setErrors(String errors) {
		this.errors=errors;
	}
	
	public Object getData() {
		return this.data;
	}
	
	public void setData(Object data) {
		this.data = data;
	}

	public String getstatus() {
		return this.status;
	}
	
	public void setstatus (String status) {
		this.status = status;
	}

	public String getmessage() {
		return this.message;
	}
	
	public void setmessage (String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "CommonResponse{" +
				"status='" + status + '\'' +
				", message='" + message + '\'' +
				'}';
	}
}
