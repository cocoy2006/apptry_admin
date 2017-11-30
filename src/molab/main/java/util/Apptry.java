package molab.main.java.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletContext;

import molab.main.java.entity.T_Application;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import brut.androlib.ApkDecoder;

import com.android.sdklib.xml.AndroidManifestParser;
import com.android.sdklib.xml.ManifestData;
import com.google.gson.Gson;

public class Apptry {
	private static final String APPTRY_PROPERTIES = "/WEB-INF/classes/apptry.properties";
	private static final String DATABASE_HOST = "apptryDatabaseHost";
	private static final Logger LOG = Logger.getLogger(Apptry.class.getName());
	
	private static ServletContext servletContext;

	public static void setServletContext(ServletContext sc) {
		servletContext = sc;
	}

	public static ServletContext getServletContext() {
		return servletContext;
	}
	
	public static String getDatabaseHost() {
		return getProperty(Apptry.getServletContext().getResourceAsStream(APPTRY_PROPERTIES), DATABASE_HOST);
	}
	
	public static String getProperty(String file, String key) {
		try {
			Properties props = loadProperties(file);
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getProperty(InputStream is, String key) {
		try {
			Properties props = loadProperties(is);
			return props.getProperty(key);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Properties loadProperties(String file) {
		try {
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			return loadProperties(is);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Properties loadProperties(InputStream is) {
		Properties props = new Properties();
		try {
			props.load(is);
			return props;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String getUploadDirectory() {
		return servletContext.getRealPath("/upload/");
	}
	
	public static String getApkDirectory() {
		return getUploadDirectory().concat("/application/apk/");
	}
	
	public static String renameWithTimestamp(String originalName) {
		StringBuilder sb = new StringBuilder("");
		String name = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
		sb.append(name);
		String suffix = "";
		int indexOfPoint = originalName.lastIndexOf(".");
		if(indexOfPoint != -1) {
			suffix = originalName.substring(indexOfPoint, originalName.length());
		}
		sb.append(suffix);
		return sb.toString();
	}
	
	public static String getLocaleTimestamp(long time) {
		return new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss").format(new Date(time));
	}
	
	public static String getTimestamp(long time) {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(new Date(time));
	}
	
	public static String getTimestamp(Date date) {
		return new SimpleDateFormat("yyMMddHHmmssSSS").format(date);
	}
	
	/**
	 * 产生随机的N位数
	 */
	public static String randomInteger(int length) {
		Random random = new Random();
		String str = random.nextInt((int)Math.pow(10, length)) + "";
		if(str.length() < length) {
			int diff = length - str.length();
			while(diff-- > 0) {
				str = "0" + str;
			}
		}
		return str;
	}
	
	
	public static String randomOrderId() {
		return getTimestamp(new Date()).concat(randomInteger(Constants.DEFAULT_ORDER_RANDOM_LENGTH));
	}
	
	public static T_Application parseApplication(MultipartFile file) {
		T_Application application = null;
		try {
			String name = file.getOriginalFilename();
			String aliasName = renameWithTimestamp(name);
			if(aliasName.endsWith(".apk")) {
				String apkPath = getApkDirectory().concat(aliasName);
				File apk = new File(apkPath);
				file.transferTo(apk);
				String md5 = MD5Util.getFileMD5(apk);
				String url = Apptry.getDatabaseHost().concat("/application/load/").concat(md5);
				String result = ajaxGet(url);
				if("0".equals(result)) { // application does not exist
					application = new T_Application();
					application.setName(name);
					application.setSize(file.getSize());
					application.setAliasName(aliasName);
					// get more information from AndroidManifest.xml.
					ApkDecoder decoder = new ApkDecoder();
					String outDirPath = apkPath.substring(0, apkPath.length() - 4);
					File apkFile = new File(apkPath);
					decoder.setOutDir(new File(outDirPath));
			        decoder.setApkFile(apkFile);
			        decoder.decode();
			        
			        File amx = new File(outDirPath.concat("/AndroidManifest.xml"));
					ManifestData md = AndroidManifestParser.parse(new FileInputStream(amx));
					
					application.setOs(md.getMinSdkVersionString() != null ? md.getMinSdkVersionString() : "");
					application.setPackageName(md.getPackage() != null ? md.getPackage() : "");
					application.setStartActivity(md.getLauncherActivity() != null ? md.getLauncherActivity().getName() : "");
					application.setVersion(md.getVersionName());
					LOG.log(Level.INFO, application.getName() + "�������");
				} else { // application already exists
					application = new Gson().fromJson(result, T_Application.class);
					LOG.log(Level.INFO, application.getName() + "�Ѿ�����");
				}
			} else if(aliasName.endsWith(".ipa")) {}
			return application;
		} catch(Exception e) {
			return null;
		}
	}
	
	public static String ajaxGet(String url) {
		LOG.log(Level.INFO, "HttpClient GET Request:" + url);
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		try {
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				LOG.log(Level.SEVERE, "Method failed: " + getMethod.getStatusLine());
			}
			byte[] responseBody = getMethod.getResponseBody();
			String response = new String(responseBody);
			LOG.log(Level.INFO, "HttpClient GET Response:" + response);
			return response;
		} catch (HttpException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		} finally {
			getMethod.releaseConnection();
		}
		return null;
	}
	
	public static String ajaxPost(String url, NameValuePair[] params) {
		LOG.log(Level.INFO, "HttpClient POST Request:" + url);
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		postMethod.setRequestBody(params);
		try {
			int statusCode = httpClient.executeMethod(postMethod);
			if(statusCode != HttpStatus.SC_OK) {
				LOG.log(Level.SEVERE, "Method failed: " + postMethod.getStatusLine());
			}
			byte[] responseBody = postMethod.getResponseBody();
			String response = new String(responseBody);
			LOG.log(Level.INFO, "HttpClient POST Response:" + response);
			return response;
		} catch (HttpException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		} catch (IOException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		} finally {
			postMethod.releaseConnection();
		}
		return null;
	}
	
	public static String ajaxMultipart(String url, String[] filePaths, String[] serialNumbers) throws IOException {
		LOG.log(Level.INFO, "HttpClient Multipart Request:" + url);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
            HttpPost httppost = new HttpPost(url);
            MultipartEntity mEntity = new MultipartEntity(  
                    HttpMultipartMode.BROWSER_COMPATIBLE, null,  
                    Charset.forName("UTF-8"));
            for(String filePath : filePaths) {
            	mEntity.addPart("file", new FileBody(new File(filePath)));
            }
            for(String serialNumber : serialNumbers) {
            	mEntity.addPart("serialNumber", new StringBody(serialNumber));
            }

            httppost.setEntity(mEntity);

            System.out.println("executing request " + httppost.getRequestLine());
            CloseableHttpResponse response = httpclient.execute(httppost);
            try {
                System.out.println(response.getStatusLine());
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    System.out.println("Response content length: " + resEntity.getContentLength());
                    System.out.println(EntityUtils.toString(response.getEntity())); 
                }
                EntityUtils.consume(resEntity);
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
		return String.valueOf(Status.SUCCESS);
	}
	
}