package controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import bean.OptimizerResponse;
import service.WorkforceOptimizer;

@RestController
public class OptimizerController {
	
	private WorkforceOptimizer workforceOptimizer;
	
	public OptimizerController(WorkforceOptimizer workforceOptimizer) {
		this.workforceOptimizer=workforceOptimizer;
	}
	
    @RequestMapping(value="/optimize/{rooms}/{jrCleaningCapacity}/{srCleaningCapacity}", method = RequestMethod.GET)
    public @ResponseBody OptimizerResponse optimize(@PathVariable Integer[] rooms, @PathVariable Integer jrCleaningCapacity, @PathVariable Integer srCleaningCapacity) {
    	OptimizerResponse optimizerResponse = new OptimizerResponse(workforceOptimizer.optimize(rooms, jrCleaningCapacity, srCleaningCapacity));
    	return optimizerResponse;    	
    }

}
