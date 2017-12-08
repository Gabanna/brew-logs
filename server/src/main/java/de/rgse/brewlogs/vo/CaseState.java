package de.rgse.brewlogs.vo;

public enum CaseState {

	AVAILABLE, ENABLED, ACTIVE, DISABLED, FAILED, SUSPENDED, COMPLETED, TERMINATED;
	
	public static CaseState parse(String caseState) {
		CaseState result = null;
		
		if(caseState != null) {
			result = CaseState.valueOf(caseState.toUpperCase());
		}
		
		return result;
	}
}
