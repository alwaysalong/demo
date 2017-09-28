package com.sh.pri.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/down")
@Controller
public class Main {

	private static Log log = LogFactory.getLog(Main.class);
	// 地址
	private static final String URL = "http://desk.zol.com.cn/";
	// 获取img标签正则
	// private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取a标签的正则
	// <a href="/bizhi/7068_87674_2.html" target="_blank"></a>
	private static final String A_REG = "<a.*href=(.*?)[^>]*?>";
	// 获取src路径的正则
	// img标签的src正则表达式
	// src="http://desk.fd.zol-img.com.cn/t_s208x130c5/g5/M00/01/0E/ChMkJ1bKwbqICS9oAAzy-ziI3pAAALGcwOyNbEADPMT693.jpg"
	// private static final String IMGSRC_REG = "(src=\"|SRC=\")(.*?)(jpg\")";
	private static final String IMGSRC_REG = "(src|SRC)=\".*?\"";// 匹配的是以src="开头的 直到下一个"结尾的内容
	// a标签的href正则表达式 href="/bizhi/7068_87674_2.html"
	private static final String AHREF_REG = "(href|HREF)=\".*?\"";

	public static void main(String[] args) {

		try {
			Main cm = new Main();
			// 获得html文本内容
			String HTML = cm.getHtml(URL);
			// 获取图片标签
			List<String> imgUrl = cm.getImageUrl(HTML);
			// 获取图片src地址
			List<String> imgSrc = cm.getImageSrc(imgUrl);
			// 下载图片
			cm.Download(imgSrc);
		} catch (Exception e) {
			System.out.println("发生错误");
			log.info("发生错误:exception :" + e.getMessage());
			e.printStackTrace();
		}

	}

	// 获取HTML内容
	private String getHtml(String url) throws Exception {
		URL url1 = new URL(url);
		URLConnection connection = url1.openConnection();
		InputStream in = connection.getInputStream();
		InputStreamReader isr = new InputStreamReader(in);// 字节流 一次读取一个字符
		BufferedReader br = new BufferedReader(isr);// 读取一个文本行
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = br.readLine()) != null) {// 每次读取一行 如果不为空
												// 就追加到stringbuffer后面
												// 每保存一行进行一次手动换行操作
			sb.append(line, 0, line.length());
			sb.append('\n');
		}
		br.close();
		isr.close();
		in.close();
		return sb.toString();
	}

	// 获取ImageUrl地址
	private List<String> getImageUrl(String html) {
		Matcher matcher = Pattern.compile(IMGURL_REG).matcher(html);// 正则匹配出所有的img标签
		List<String> listimgurl = new ArrayList<String>();
		while (matcher.find()) {
			listimgurl.add(matcher.group());
		}
		log.info("图片地址list集合  :" + listimgurl);
		return listimgurl;
	}

	// 获取ImageSrc地址
	private List<String> getImageSrc(List<String> listimageurl) {
		List<String> listImageSrc = new ArrayList<String>();
		// src="http://desk.fd.zol-img.com.cn/t_s208x130c5/g5/M00/01/0E/ChMkJ1bKwbqICS9oAAzy-ziI3pAAALGcwOyNbEADPMT693.jpg"
		for (String image : listimageurl) {
			String image1 = image.replace(" ", "");
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image1);// 正则匹配出有用的图片src
			while (matcher.find()) {// 如果能匹配到就 对匹配到的字符串进行截取 取出我们需要的图片地址
				listImageSrc.add(matcher.group().substring(5,
						matcher.group().length() - 1));
			}
		}
		log.info("图片的下载地址集合 : " + listImageSrc);
		return listImageSrc;
	}

	// 下载图片
	private void Download(List<String> listImgSrc) {
		try {
			// 抓取图片开始时间
			Date begindate = new Date();
			OutputStream os = null;// 定义一个输出流
			for (String url : listImgSrc) {
				// 判断src里的地址不是以图片格式结尾的话 就跳过
				if (!(url.toLowerCase().endsWith(".jpg") || url.toLowerCase()
						.endsWith(".bmp"))) {
					continue;
				}
				// 单张图片开始下载时间
				Date begindate2 = new Date();
				// 取得最后一个/后面的字符串 作为图片名
				String imageName = url.substring(url.lastIndexOf("/") + 1,
						url.length());
				URL uri = null;
				if (url.indexOf("http:") != -1) {
					uri = new URL(url);
				} else {
					uri = new URL("http:" + url);
				}
				InputStream in = uri.openStream();// 打开流
				// 创建一个文件夹
				File f = new File("d:\\webimg\\");
				f.mkdirs();// mkdirs可以创建多级目录
				os = new FileOutputStream("d:\\webimg\\" + imageName);// 创建一个指定名称的输出流对象
				int bytesRead = 0;
				byte[] buffer = new byte[8192];
				while ((bytesRead = in.read(buffer, 0, 8192)) != -1) {
					os.write(buffer, 0, bytesRead);
				}
				Date overdate = new Date();
				double time = overdate.getTime() - begindate2.getTime();
				System.out.println("------>单张下载耗时：" + time / 1000 + "秒");
			}
			os.close();
			Date endDate = new Date();
			double sumTime = endDate.getTime() - begindate.getTime();
			System.out.println("完成。。。。。。。。。。。。。。。。");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>抓取所以资源共耗时  :" + sumTime
					/ 1000 + "秒");
		} catch (Exception e) {
			System.out.println("下载失败");
			log.info("下载失败:exception :" + e.getMessage());
			e.printStackTrace();
		}
	}
}