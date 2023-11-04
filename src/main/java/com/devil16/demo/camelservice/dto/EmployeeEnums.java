package com.devil16.demo.camelservice.dto;

/**
 * EmployeeDtoEnums class -
 * 
 * place holder for the EmployeeDto Enums
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2023-10-31
 */
public class EmployeeEnums {
	
	/**
	 * employee type
	 */
	public enum EmployeeType {
		
		Contract2Hire,
		Probabtion,
		Contractor,
		Permanent,
		Novalue;
		
	}
	
	/**
	 * employee designation
	 */
	public enum EmployeeDesignation {
		
		ApplicationDeveloper,
		SeniorApplicationDeveloper,
		ProjectLead,
		TeamLead,
		LeadManager,
		SpecialistManager,
		IndividualContributor,
		ChiefTechnicalOfficer,
		ChiefFinancialOfficer,
		ChiefExecutiveOffice,
		Novalue;
		
	}
	
	/**
	 * employee team
	 */
	public enum EmployeeTeam {
		
		Order,
		Processing,
		Billing,
		Clearing,
		Settlement,
		Novalue;
		
	}
	
	/**
	 * employee line of business
	 */
	public enum EmployeeLob {
		
		Trading,
		Custody,
		Management,
		Advisory,
		Novalue;
		
	}
	
	/**
	 * employee status
	 */
	public enum EmployeeStatus {
		
		Active,
		Terminated,
		Exited,
		Novalue;
		
	}
}
