package com.devil16.demo.camelservice.dto;

import com.devil16.demo.camelservice.dto.EmployeeEnums.EmployeeDesignation;
import com.devil16.demo.camelservice.dto.EmployeeEnums.EmployeeLob;
import com.devil16.demo.camelservice.dto.EmployeeEnums.EmployeeStatus;
import com.devil16.demo.camelservice.dto.EmployeeEnums.EmployeeTeam;
import com.devil16.demo.camelservice.dto.EmployeeEnums.EmployeeType;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.apache.commons.lang3.StringUtils;

/**
 * EmployeeDto class -
 * 
 * this is the Employee DTO (Data Transfer Object) class
 * 
 * @author Debanshu P
 * @version 1.0
 * @since 2023-10-31
 * 
 */

/*
 * DTO objects exposed to the REST Client;
 * instead of exposing the Entity objects
 */

/* we are using the Lombok (https://projectlombok.org/) framework to generate the getters, setters and other boiler-plate code
 * 
 * the following Lombok annotations are being used
 * 
 * @Data	- generates boilerplate code; getters, setters, toString(), all-args Constructors
 * @Builder - allows easier object creation, without having to call upon individual constructors
 * @AllArgsConstructor - generates all-args Constructor 
 * @NoArgsConstructor - generates no-args Constructor
 * 
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public final class Employee {
	
	/**
	 * 8-byte String uniquely identifies each employee  
	 */
	@Builder.Default
	private String commitId = StringUtils.EMPTY;
	
	/**
	 * first name of the employee
	 */
	@Builder.Default
	private String firstName = StringUtils.EMPTY;
	
	/**
	 * last name of the emplyee
	 */
	@Builder.Default
	private String lastName = StringUtils.EMPTY;
	
	/**
	 * type of employment with the firm
	 */
	@Builder.Default
	private EmployeeType type = EmployeeType.Novalue;
	
	/**
	 * billing link for cost-to-company for the employee 
	 */
	@Builder.Default
	private String costBillLink = StringUtils.EMPTY;
	
	/**
	 * settlement link for cost-to-company for the employee 
	 */
	@Builder.Default
	private String costSettleLink = StringUtils.EMPTY;
	
	/**
	 * designation of the employee
	 */
	@Builder.Default
	private EmployeeDesignation designation = EmployeeDesignation.Novalue;
	
	/**
	 * team assigned to the employee
	 */
	@Builder.Default
	private EmployeeTeam team = EmployeeTeam.Novalue;
	
	/**
	 * the line-of-business the employee works for
	 */
	@Builder.Default
	private EmployeeLob lineOfBusiness = EmployeeLob.Novalue;
	
	/**
	 * firm assigned email-Id for the employee
	 */
	@Builder.Default
	private String emailId = StringUtils.EMPTY;
	
	/**
	 * firm assigned phone for the employee
	 */
	@Builder.Default
	private String phone = StringUtils.EMPTY;
	
	/**
	 * status of the employee
	 */
	@Builder.Default
	private EmployeeStatus status = EmployeeStatus.Novalue;
	
	/**
	 * joining date of the employee
	 */
	@Builder.Default
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//private LocalDate dateOfJoining = LocalDate.of(1970,01,01);
	private String dateOfJoining = StringUtils.EMPTY;
	
	/**
	 * last working-day of the employee
	 */
	@Builder.Default
	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	//private LocalDate lastWorkingDate = LocalDate.of(1970,01,01);
	private String lastWorkingDate = StringUtils.EMPTY;
	
}