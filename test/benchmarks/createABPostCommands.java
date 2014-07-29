package benchmarks;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createABPostCommands {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String baseURL = "ab  -n ";
		String folder = "/test/results/post/";
		String fileOutputFolder = "F:/Dropbox/PhD/Wiki Health/Yang Li/ECG/For Benchmarks/URLs/";
		for (int p = 10; p <= 100000; p = p * 10) {
			for (int i = 100; i <= 1000; i = i + 100) {
				String URL = baseURL + i + " -c " + i + " -g " + folder + "P"
						+ p + "_C" + i + " "
						+ " -T 'application/json; charset=utf-8' "
						+ " -p /test/inputs/" + p + ".json ";
				URL = URL+ "http://api.wiki-health.org:55555/healthbook/v1/health/title/ecg/datapoints-benchmarks?accesstoken=5b08af23d9f2490cb253fa5d221aa74e";
				System.out.println(URL);
				System.out.println("sleep 5");
			}
		}
	}

}
