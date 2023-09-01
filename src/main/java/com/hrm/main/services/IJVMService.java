package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.JVM;

public interface IJVMService {
	String addJvm(JVM jvm);

	List<JVM> allJvm();

	JVM getJvm(Integer id);

	String updateJvm(JVM jvm, Integer id);

	String deleteJvm(Integer id);
}
