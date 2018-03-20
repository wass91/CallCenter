package com.almundo.callcenter.enums;

public enum EmployeeTypeEnum {

	TYPE_OPERATOR("operator"),
	TYPE_SUPERVISOR("supervisor"),
	TYPE_DIRECTOR("director");
	
	private String name;
	
	EmployeeTypeEnum(String name){
		this.name = name;
	}
	
	public String getName() {
        return name;
    }
    
	public String toLowerCase(){
		return toString().toLowerCase();
	}
	
	public String toUpperCase(){
		return toString().toUpperCase();
	}
}
