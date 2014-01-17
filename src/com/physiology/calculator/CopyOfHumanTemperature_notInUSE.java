package com.physiology.calculator;
/*package com.humanmodel;

import java.util.HashMap;

public class HumanTemperature {

	*//**
	 * @param args
	 *//*
	// C(25) heat capacitance of compartment N. Kcal/C
	static final double[] C = { 2.22, 0.33, 0.22, 0.24, 9.82, 16.15, 4.25,
			1.21, 1.41, 3.04, 0.58, 0.43, 0.14, 0.06, 0.09, 0.17, 4.24, 9.17,
			1.43, 1.08, 0.23, 0.06, 0.13, 0.22, 2.25 };

	// QB(24) Basal Metabolic heat production in N. Kcal/h
	static final double[] QB = { 12.84, 0.10, 0.11, 0.08, 45.38, 5.00, 2.13,
			0.40, 0.70, 0.95, 0.17, 0.13, 0.08, 0.20, 0.03, 0.05, 2.23, 2.86,
			0.43, 0.32, 0.13, 0.02, 0.04, 0.07 };

	// EB(24) Basal Evaporative heat loss from N. Kcal/h
	static final double[] EB = { 0., 0., 0., 0.63, 9.00, 0., 0., 3.25, 0., 0.,
			0., 1.20, 0., 0., 0., 0.45, 0., 0., 0., 2.85, 0., 0., 0., 0.62 };
	// BFB(24) Basal effective blood flow to N. L/h?
	static final double[] BFB = { 45.00, 0.12, 0.13, 1.44, 210.00, 6.00, 2.56,
			2.10, 0.84, 1.14, 0.20, 0.50, 0.10, 0.24, 0.04, 2.00, 2.69, 3.43,
			0.52, 2.85, 0.16, 0.02, 0.05, 3.00 };
	// TC(24) Thermal conductance between N and N+1. Kcal/h/C
	static final double[] TC = { 1.38, 11.4, 13.8, 0., 1.37, 4.75, 19.80, 0.,
			1.20, 8.90, 26.20, 0., 5.50, 9.65, 9.90, 0., 9., 12.4, 64., 0.,
			14.0, 17.7, 14.1, 0. };

	// S(6) surface area of Segment I (HEAD,TRUNK,ARMS..)
	static final double[] S = { 0.1326, 0.6804, 0.2536, 0.0946, 0.5966, 0.1299 };
	// HR(6) Radiant Heat Transfer Coeff. for Segment I. Kcal/m^2/h/C
	static final double[] HR = { 5.5, 4.5, 4.3, 3.0, 4.0, 4.0 };
	// HC(6). Convective and conductive heat transfer coeff. for Segment I.
	// //%Kcal/m^2/h/C
	static final double[] HC = { 2.75, 2.15, 3.0, 3.35, 2.75, 3.0 };
	// P(10) is not sure.Vapor pressure table from 5-50C. mmHg
	static final double[] P = { 6.8, 9.0, 13.5, 17.5, 23.8, 31.5, 42.0, 55.5,
			73.9, 92.3 };

	// //%TSET(25). SET POINT for receptors in compartment N C
	static final double[] TSET = { 36.96, 35.07, 34.81, 34.58, 36.89, 36.28,
			34.53, 33.62, 35.53, 34.12, 33.59, 33.25, 35.41, 35.38, 35.30,
			35.22, 35.81, 35.30, 35.31, 34.10, 35.14, 35.03, 35.11, 35.04,
			36.71 };
	// //%SKINR(6).Fraction of all skin receptors in Segment I. N.D.
	static final double[] SKINR = { 0.0695, 0.4935, 0.0686, 0.1845, 0.1505,
			0.0334 };
	// //%SKINS(6). Fraction of sweating command applicable to skin of Segment
	// I.
	static final double[] SKINS = { 0.081, 0.481, 0.154, 0.031, 0.218, 0.035 };
	// //%SKINV(6).Fraction of vasodilatation command applicable to skin of
	// Segment I
	static final double[] SKINV = { 0.132, 0.322, 0.095, 0.121, 0.230, 0.1 };
	// //%SKINC(6).Fraction of vasoconstriction command applicable to skin of
	// Segment I
	static final double[] SKINC = { 0.05, 0.15, 0.05, 0.35, 0.05, 0.35 };
	// //%WARMS integrated output from skin warm receptors. C
	static final double[] WORKM = { 0., 0.30, 0.08, 0.01, 0.60, 0.01 };
	// //%CHILM fraction of total shivering occuring in muscle of segment I
	static final double[] CHILM = { 0.02, 0.85, 0.05, 0.00, 0.07, 0.00 };

	static final double CSW = 320.; // //%Sweating from head core. Kcal/h/C
	static final double SSW = 29.0;// //%Sweating from skin. Kcal/h/c
	static final double PSW = 0.;// % Sweating from skin and head core.
									// Kcal/h/C^2
	static final double BULL = 10.0; // %Factor determing temperature
										// sensitivity of sweat
	// gland response. /C
	static final double CDIL = 117.0;// %vasodilatation from head core. l/h/C
	static final double SDIL = 7.5; // %Vasodilation from skin. l/h/C
	static final double PDIL = 0.;// %Vasodilatation from skin and head core.
									// l/h/C^2
	static final double CCHIL = 0.0; // %Shivering from head core. Kcal/h/C
	static final double SCHIL = 0.0; // %Shivering from skin. /C
	static final double PCHIL = 21.0; // %Shiversing from skin and head core.
										// /C^2
	static final double CCON = 5.0; // %vasoconstriction from head core
	static final double SCON = 5.0;// %vasoconstriction from skin
	static final double PCON = 0.0;// %vasoconstriction from skin and head core.
									// /C^2
	static final double DefaultWeight = 74.1; // % 74.1 kg
	static final double DefaultS = 1.89; // %square meters

	double SA = sum(S); // % total area of body

	public static double sum(double[] S) {
		double total = 0;
		for (double s : S) {
			total = total + s;
		}
		return total;
	}

	public static double[] CalculateTemperature(HashMap<String, Object> inputs) {
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
		double[] F = new double[25];
		double TIME = 0;

		for (int N = 0; N < 25; N++) {
			System.out.println(N);
			F[N] = 0;
		}
		double V = VAIR;
		double WORKI;
		if ((WORK - 74.4) <= 0)
			WORKI = 0.0;
		else
			WORKI = (WORK - 74.4) * 0.78;
		
		for (int I = 0; I < 25; I++) {
//			System.out.println(I);
			   H[I]=(HR[I]+HC[I]*(V/0.1)^0.5)*S[I];
		}
		return null;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CalculateTemperature(null);
	}

}
*/