package com.astralbrands.flight.x3.model;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Result implements Serializable{
	
	@JacksonXmlProperty(localName = "GRP")
	private Group grp;
}
