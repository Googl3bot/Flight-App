package com.astralbrands.flight.x3.model;

import java.util.List;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailData {

	private String subject;
	private String emailBody;
	private List<EmailAttachment> attachments;
}
