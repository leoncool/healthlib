/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Leon
 */
public class JsonUtil {

    private static String HOST = "localhost:8080";
    private static String HOSTIP = "192.168.1.8";

    public String readJsonStrFromHttpRequest(HttpServletRequest request) throws IOException {
//        System.out.println("Content-Encoding: \n" + request.getHeader("Content-Encoding"));
//        System.out.println("getContentType: \n" + request.getContentType());
//        System.out.println("getContentLength: \n" + request.getContentLength());

//    	request.setCharacterEncoding("UTF-8");
    	
    	 BufferedReader reader = new BufferedReader(
    		       new InputStreamReader(request.getInputStream(),"UTF-8"));
    	
//    	BufferedReader reader = request.getReader();
        
        StringBuilder sb = new StringBuilder();
        String line = reader.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = reader.readLine();
        }
        reader.close();
        String data = sb.toString();
//       System.out.println("input stream: \n" + data);
        return data;
    }

    public static String ServletPath(HttpServletRequest req) {
        return req.getRequestURI();
    }

    public static String contextPath(HttpServletRequest req) {
//        System.out.println(req.getHeader("host").startsWith(HOST));
        System.out.println("getRequestURI"+req.getRequestURI());
        System.out.println("getContextPath"+req.getContextPath());
        System.out.println("getServletPath:"+req.getServletPath());
//        if ((req.getHeader("host").startsWith(HOST) || req.getHeader("host").startsWith(HOSTIP))) {
//            String cleanContext = req.getPathInfo().substring(1, req.getPathInfo().length());
//            if (cleanContext.contains("/")) {
//                return cleanContext.substring(cleanContext.indexOf("/") + 1, cleanContext.length());
//            } else {
//                return "";
//            }
//        } else {
//            return req.getPathInfo().substring(1, req.getPathInfo().length());
//        }
        return "";
    }
}
