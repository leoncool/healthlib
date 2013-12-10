package com.humanmodel;

import java.util.HashMap;

import dk.ange.octave.OctaveEngine;
import dk.ange.octave.OctaveEngineFactory;
import dk.ange.octave.type.OctaveDouble;
import dk.ange.octave.type.matrix.DoubleMatrix;
public class HumanTemperature {

	/**
	 * @param args
	 */
	// C(25) heat capacitance of compartment N. Kcal/C

	static final double DefaultWeight = 74.1; // % 74.1 kg
	static final double DefaultS = 1.89; // %square meters

	public static HashMap<String, Object> CalculateTemperature(
			HashMap<String, Object> inputs) {
		double weight = DefaultWeight;
		double WORK = 0;
		double RH = 0;
		double TAIR = 0;
		double VAIR = 0;
		int runSeconds = 0;
		int max_time = runSeconds;
		double TOTAL_EnergyKcal = 0;

		// if (inputs.get("T") == null) {
		// return null;
		// }
		//
		// if (inputs.get("weight") != null) {
		// weight = (Double) inputs.get("weight");
		// }
		//
		// if (inputs.get("WORK") == null) {
		// return null;
		// } else {
		// WORK = (Double) inputs.get("WORK");
		// }
		//
		// if (inputs.get("RH") == null) {
		// return null;
		// } else {
		// RH = (Double) inputs.get("RH");
		// }
		//
		// if (inputs.get("RH") == null) {
		// return null;
		// } else {
		// RH = (Double) inputs.get("RH");
		// }
		//
		// if (inputs.get("TAIR") == null) {
		// return null;
		// } else {
		// TAIR = (Double) inputs.get("TAIR");
		// }
		// if (inputs.get("VAIR") == null) {
		// return null;
		// } else {
		// VAIR = (Double) inputs.get("VAIR");
		// }
		// if (inputs.get("runSeconds") == null) {
		// return null;
		// } else {
		// runSeconds = (Integer) inputs.get("runSeconds");
		// }
		String modelPath = "addpath(\"E:/IC_Dropbox/Dropbox/PhD/Matlab/human model\")";
		String modelPath2 = "addpath(\"/usr/local/apache-tomcat-7.0.29/octave\")";
		OctaveEngine octave = new OctaveEngineFactory().getScriptEngine();
		octave.eval(modelPath);
		octave.eval(modelPath2);
		// OctaveDouble matA = new OctaveDouble(new double[]{1, 2, 3, 4, 5, 6,
		// 7, 8, 9, 10, 11, 12, 13, 14, 15}, 3, 5);

		double[] T = { 36.96, 35.07, 34.81, 34.58, 36.89, 36.28, 34.53, 33.62,
				35.53, 34.12, 33.59, 33.25, 35.41, 35.38, 35.30, 35.22, 35.81,
				35.30, 35.31, 34.10, 35.14, 35.03, 35.11, 35.04, 36.71 };
		StringBuilder builder = new StringBuilder();
		for (double i : T) {
			if (builder.length() != 0) {
				builder.append(",");
			} else {
				builder.append('[');
			}
			builder.append(i);
		}
		builder.append(']');
		String inputT = "T=" + builder.toString();
		octave.eval(inputT);
		octave.eval("[x,y]=CalculateTemperature(T,80,80,0.3,20,0.1,10);");
		// octave.eval("T=[36.96,35.07,34.81,34.58,36.89,36.28,34.53,33.62,35.53,34.12,33.59,33.25,35.41,35.38,35.30,35.22,35.81,35.30,35.31,34.10,35.14,35.03,35.11,35.04,36.71];");
		DoubleMatrix varX = (DoubleMatrix) octave.get("x");
		// System.out.println("Result: " + varX.get(1) + " " + varX.get(2) + " "
		// + varX.get(3));
//		System.out.println("Result: " + varX.get(1));
		double[] Tnew = new double[26];
		
		for (int i = 1; i <= 25; i++) {
			Tnew[i] = varX.get(1,i);
		}
		octave.close();
		HashMap<String, Object> results = new HashMap<String, Object>();
		results.put("T", Tnew);
		return results;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculateTemperature(null);
	}

}
