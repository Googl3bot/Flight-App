package com.astralbrands.flight.x3.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailAttachment {
	private String fileName;
	private String attachmentData;
	private String fileType;
	private String mimetype;

}
