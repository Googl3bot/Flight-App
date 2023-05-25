package com.astralbrands.flight.x3.model;

import java.util.ArrayList;

import com.fasterxml.jackson.annotation.JsonProperty;

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
public class SearchCriteriaResponse{
	
	@JsonProperty("success")
    private boolean success;
    @JsonProperty("Results") 
    private ArrayList<SearchCriteriaResult> results;
    @JsonProperty("RecordCount") 
    private int recordCount;
    @JsonProperty("DisplayCriteria") 
    private String displayCriteria;
}

