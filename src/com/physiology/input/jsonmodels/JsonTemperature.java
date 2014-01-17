/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.physiology.input.jsonmodels;

import java.util.List;

/**
 * 
 * @author Leon
 */
public class JsonTemperature {
	public String t_list;
	public double weight;
	public double work;
	public double rh;
	public double tair;
	public double vair;
	public double t_average;
	public int elapsed_seconds;
	public String t_tags = "head_core,head_muscle,head_fat,head_skin,"
			+ "trunk_core,trunk_muscle,trunk_fat,trunk_skin,"
			+ "arms_core,arms_muscle,arms_fat,arms_skin,"
			+ "hands_core,hands_muscle,hands_fat,hands_skin,"
			+ "legs_core,legs_muscle,legs_fat,legs_skin,"
			+ "feet_core,feet_muscle,feet_fat,feet_skin" + "central_blood";

	public String getT_list() {
		return t_list;
	}

	public void setT_list(String t_list) {
		this.t_list = t_list;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getWork() {
		return work;
	}

	public void setWork(double work) {
		this.work = work;
	}

	public double getRh() {
		return rh;
	}

	public void setRh(double rh) {
		this.rh = rh;
	}

	public double getTair() {
		return tair;
	}

	public void setTair(double tair) {
		this.tair = tair;
	}

	public double getVair() {
		return vair;
	}

	public void setVair(double vair) {
		this.vair = vair;
	}

	public int getElapsed_seconds() {
		return elapsed_seconds;
	}

	public void setElapsed_seconds(int elapsed_seconds) {
		this.elapsed_seconds = elapsed_seconds;
	}

	public String getT_tags() {
		return t_tags;
	}

	public void setT_tags(String t_tags) {
		this.t_tags = t_tags;
	}

	public double getT_average() {
		return t_average;
	}

	public void setT_average(double t_average) {
		this.t_average = t_average;
	}
}
