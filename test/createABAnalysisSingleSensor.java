

public class createABAnalysisSingleSensor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String baseURL="ab  -n ";
		String folder="/test/analysisResults/";
		int iList[]={10,20,30,40,50,60,70,80,90,100};
		for(int p=100;p<=10000;p=p*10)
		{
		for(int i:iList)
		{
			String URL=baseURL+i+" -c "+i+" -g "+folder+"P"+p+"_C"+i+" ";
			URL=URL+"\""+"http://api.wiki-health.org:55555/healthbook/RunJob?service_id=19&input1_source=ecg604&output1_source=ab1&output2_source=bab2&output3_source=aaa&livejob=true&maxglobal="+p+""+"\"";
			System.out.println(URL);
			System.out.println("sleep 5");
		}
	}
	}

}
