package com.schoolManagement.controller;

import com.schoolManagement.model.UE;

public class Controller {

	public static void main(String[] args) {
		
		UE ue = new UE("1","5646","Maths et physique");
		
		System.out.println(ue.getId());
		System.out.println(ue.getCode());
		System.out.println(ue.getIntitule());
	}

}
