package com.hrm.services;

import java.util.List;

import com.hrm.models.JVM;

public interface IJVMService {
	String addJvm(JVM jvm);

	List<JVM> allJvm();

	JVM getJvm(Integer id);

	String updateJvm(JVM jvm, Integer id);

	String deleteJvm(Integer id);
}
