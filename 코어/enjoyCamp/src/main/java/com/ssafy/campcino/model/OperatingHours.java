package com.ssafy.campcino.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OperatingHours {
	private boolean o_weekday;
    private boolean o_weekend;
    private boolean o_spring;
    private boolean o_summer;
    private boolean o_fall;
    private boolean o_winter;
}
