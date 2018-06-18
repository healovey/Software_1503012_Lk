package intent;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.net.URLEncoder;

import org.json.JSONArray;  
import org.json.JSONObject; 

public class HttpGet {
	public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;          
            
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.setRequestProperty("Charsert", "UTF-8");
            connection.setRequestProperty("Content-Type", "application/json");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }
	
	public static String gbk2utf(String str) throws Throwable {
		return new String(str.getBytes("GBK"), "UTF-8");
	}
	
	public static String utf2gbk(String str) throws Throwable {
		return new String(str.getBytes("UTF-8"), "GBK");
	}
	
	public static String sendData(String sentence) throws Throwable {
		return sendGet("https://westus.api.cognitive.microsoft.com/luis/v2.0/apps/7aa38472-13b5-4e0c-91e9-ddc7c0d68879",
						"subscription-key=1aafcba3e3264b3aa3dd811e4af0876b&verbose=true&timezoneOffset=0&q=" + URLEncoder.encode(sentence,"UTF-8"));
	}

	public static IntentGet getIntent(String strs, IntentDefaultSet set) throws Throwable {
		IntentGet result = new IntentGet(set);
		
		result.origin = strs;
		String json = sendData( strs );
		System.out.println(json);
		JSONObject jsonObject = new JSONObject(json);
		JSONObject topScoringIntent = jsonObject.getJSONObject("topScoringIntent");
		result.intent = topScoringIntent.get("intent").toString();
		result.score = Float.parseFloat(topScoringIntent.get("score").toString());
		JSONArray entities = jsonObject.getJSONArray("entities");
		if (entities.length() > 0) {
			result.name = entities.getJSONObject(0).get("entity").toString();
		}else {
			result.name = "";
		}
		return result;
	}
	public static void main(String[] args) throws Throwable {
		String[] exenames = {"mmm Word","360"};
		IntentDefaultSet intentDefaultSet = new IntentDefaultSet(exenames);
		
		IntentGet result = getIntent("打开QQ", intentDefaultSet);
		result.out();
	}
}
