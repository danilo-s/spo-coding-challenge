package bean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class OptimizerResponse implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Map<String,String>> result;
	
	public OptimizerResponse() {
		
	}
	
	public OptimizerResponse(List<Map<String, String>> result) {
		super();
		this.result = result;
	}

	public List<Map<String,String>> getResult() {
		return result;
	}

	public void setResult(List<Map<String,String>> result) {
		this.result = result;
	}

}
