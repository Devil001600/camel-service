package com.devil16.demo.camelservice.dto;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employees {
	
	@Builder.Default
	private List<Employee> employees = List.of();
	
	@JsonCreator
	public Employees(Employee[] employees) {this.employees = Arrays.asList(employees);}
	
}
