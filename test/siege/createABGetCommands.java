package siege;

public class createABGetCommands {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String baseURL="ab  -n ";
		String folder="/test/results/";
		for(int p=10;p<=100000;p=p*10)
		{
		for(int i=100;i<=1000;i=i+100)
		{
			String URL=baseURL+i+" -c "+i+" -g "+folder+"P"+p+"_C"+i+" ";
			URL=URL+"\""+"http://api.wiki-health.org:55555/healthbook/v1/health/title/ecg/datapoints?accesstoken=5b08af23d9f2490cb253fa5d221aa74e&max="+p+"&start=1406224391"+"\"";
			System.out.println(URL);
			System.out.println("sleep 5");
		}
	}
	}

}
