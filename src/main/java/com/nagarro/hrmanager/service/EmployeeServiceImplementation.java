package com.nagarro.hrmanager.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nagarro.hrmanager.constant.Constant;
import com.nagarro.hrmanager.dao.EmployeeRepository;
import com.nagarro.hrmanager.entity.Employee;

@Service
public class EmployeeServiceImplementation implements EmployeeService {
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	
	public Constant constants;
	
	public RestTemplate restTemplate =new RestTemplate();
	
	@Override
	public List<Employee> allEmployee() {
		
		List<Employee> employee;
		String url = Constant.RESTURL + Constant.EMPLOYEESLIST;
		ResponseEntity<List<Employee>> response = restTemplate.exchange(url, HttpMethod.GET, null,
		new ParameterizedTypeReference<List<Employee>>() {
		});
		employee = response.getBody();
		return employee;
		//return this.employeeRepository.findAll();
	}
	
	
	
	@Override
	public void saveEmployee(Employee employee) {
		String url = Constant.RESTURL + Constant.SAVEEMPLOYEE;
		restTemplate.postForObject(url, employee, Employee.class);
		//this.employeeRepository.save(employee);
	}
	
	
//	@Override
//	public void updateEmployee(Employee employee) {
//		String url = Constant.RESTURL + Constant.UPDATEEMPLOYEE;
//		restTemplate.put(url, employee);
//		//this.employeeRepository.save(employee);
//	}
	
	@Override
	public void deleteEmployee(Employee employee) {
		this.employeeRepository.delete(employee);
	}
	
	@Override
	public Employee getEmployeeById(long id) {
		Optional<Employee> employee = this.employeeRepository.findById(id);
		Employee emp = null;
		if(employee.isPresent()) {
			emp = employee.get();
		}	
		return emp;
	}
	
	@Override
	public Employee deleteEmployeeById(long id) {
		Optional<Employee> employeeDelete = this.employeeRepository.findById(id);
		Employee employee = null;
		if(employeeDelete.isPresent()) {
			employee = employeeDelete.get();
			employeeRepository.delete(employee);
		}
		return employee;
	}
	
	@Override
	public List<Employee> listEmployee() {
        return employeeRepository.findAll(Sort.by("employeeCode").ascending());
    }
	
	
	
}
