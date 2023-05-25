package com.astralbrands.flight.x3.model;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Component
public class TrackingResponse {

	private int ExceptionCount;
	private int SuccessCount;
	private int UnprocessedCount;
	
}
