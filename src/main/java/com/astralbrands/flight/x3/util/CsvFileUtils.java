package com.astralbrands.flight.x3.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.multipart.MultipartFile;

import com.astralbrands.flight.x3.model.Order;

public class CsvFileUtils {

	
	public static List<Order> convertCsvToProducts(MultipartFile file) {

		try {
			String data = new BufferedReader(new InputStreamReader(file.getInputStream())).lines().parallel()
					.collect(Collectors.joining("\n"));
			
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
}
