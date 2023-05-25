package com.astralbrands.flight.x3.model;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import lombok.Data;

@Data
public class GroupField implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JacksonXmlProperty(isAttribute = true,localName = "NAME")
	private String name;
	@JacksonXmlProperty(isAttribute = true,localName = "TYPE")
	private String type;
	@JacksonXmlText
	private String value;
	
	
}
