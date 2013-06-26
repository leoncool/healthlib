package rss;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class createFeedsText {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		File folder = new File("G:/seo/");
		File[] files = folder.listFiles();
		FileWriter writer = new FileWriter(new File("G:/rssList.txt"));
		BufferedWriter bw = new BufferedWriter(writer);
		int counter=0;
		for (File file : files) {
			System.out.println(counter+","+file.getName());
			counter++;
			bw.write("http://www.123-cloud.com/rss/" + file.getName());
			bw.newLine();
		}
		bw.flush();
		bw.close();
		writer.close();
	}
}
