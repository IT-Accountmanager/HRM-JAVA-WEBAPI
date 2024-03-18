package com.hrm.utils;

import java.nio.file.Paths;

import org.springframework.stereotype.Component;

@Component
public class CommonUtils {

	public String getBasePath() {
		String basePath = Paths.get("").toAbsolutePath().toString();
		return basePath;
	}
}
