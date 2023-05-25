package com.astralbrands.flight.x3.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;

@Data
@JacksonXmlRootElement(localName = "GRP")
public class Group implements Serializable{
	@JacksonXmlProperty(isAttribute = true,localName = "ID")
	private String id;
	@JacksonXmlElementWrapper(useWrapping = false)
	@JacksonXmlProperty(localName = "FLD")
	private List<GroupField> fld;
}
