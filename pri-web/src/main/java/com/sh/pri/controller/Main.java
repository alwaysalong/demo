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

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/down")
@Controller
public class Main {

	// 地址
	private static final String URL = "http://www.ule.com/";
	// 获取img标签正则
	// private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取src路径的正则
	// http://img06.tooopen.com/images/20170321/tooopen_sl_202673156116.jpg
	// http://img03.tooopen.com/thumbnails/20130702/x_08383297.jpg
	private static final String IMGSRC_REG = "(//pic)(.*?)(jpg)";
//	private static final String IMGSRC_REG = "(http://img)(.*?)(jpg)";

	// private static final String IMGSRC_REG = "(src|SRC)=(\"|\')(.*?)(\"|\')";

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
		return listimgurl;
	}

	// 获取ImageSrc地址
	private List<String> getImageSrc(List<String> listimageurl) {
		List<String> listImageSrc = new ArrayList<String>();
		for (String image : listimageurl) {
			Matcher matcher = Pattern.compile(IMGSRC_REG).matcher(image);// 正则匹配出有用的图片src
			while (matcher.find()) {// 如果能匹配到就 对匹配到的字符串进行截取 取出我们需要的图片地址
				listImageSrc.add(matcher.group().substring(0,
						matcher.group().length()));
			}
		}
		return listImageSrc;
	}

	// 下载图片
	private void Download(List<String> listImgSrc) {
		try {
			// 开始时间
			Date begindate = new Date();
			for (String url : listImgSrc) {
				// 开始时间
				Date begindate2 = new Date();
				// 取得最后一个/后面的字符串 作为图片名
				String imageName = url.substring(url.lastIndexOf("/") + 1,
						url.length());
				URL uri = new URL("http:"+url);
				InputStream in = uri.openStream();// 打开流
				OutputStream os = null;// 定义一个输出流
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
				double time = overdate.getTime() - begindate.getTime();
				System.out.println("总耗时：" + time / 1000 + "s");
				os.close();
			}
			System.out.println("完成。。。。。。。。。。。。。。。。");
		} catch (Exception e) {
			System.out.println("下载失败");
		}
	}
}