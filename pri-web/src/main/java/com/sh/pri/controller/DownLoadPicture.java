package com.sh.pri.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequestMapping("/down")
@Controller
public class DownLoadPicture {

	private static Log log = LogFactory.getLog(DownLoadPicture.class);
	// 地址
	private static final String URL = "http://desk.zol.com.cn/";
	private static final String URL1 = "http://desk.zol.com.cn";
	// private static final String URL = "http://www.58pic.com/";
	// 获取img标签正则
	// private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	private static final String IMGURL_REG = "<img.*src=(.*?)[^>]*?>";
	// 获取a标签的正则
	// <a href="/bizhi/7068_87674_2.html" target="_blank"></a>
	private static final String A_REG = "<a.*href=(.*?)[^>]*?>";
	// 获取src路径的正则
	// img标签的src正则表达式
	// src="http://desk.fd.zol-img.com.cn/t_s208x130c5/g5/M00/01/0E/ChMkJ1bKwbqICS9oAAzy-ziI3pAAALGcwOyNbEADPMT693.jpg"
	 private static final String IMGSRC_REG = "(src=\"|SRC=\")(.*?)(jpg\")";
	 //不需要的src正则   .src不需要
	 private static final String NOIMGSRC_REG = "^.*/(.src).*$";
//	private static final String IMGSRC_REG = "(src|SRC)=\".*?\"";// 匹配的是以src="开头的 直到下一个"结尾的内容
	// a标签的href正则表达式 href="/bizhi/7068_87674_2.html"
	// private static final String AHREF_REG = "(href|HREF)=\".*?\"";s
	//.表示除\n之外的任意字符   *表示匹配0-无穷   +表示匹配1-无穷   ？是懒惰模式匹配到第一个符合的就不再往后了   不加？的话就是匹配到最后一个符合的才结束
	private static final String AHREF_REG = "(href|HREF)=\".*?(html\")";
	//href="/down/1366x768_89402.html"  高清图片下载地址    a标签
//	private static final String DOWNLOAD_HREF = "(href|HREF)=\"(/down/1366x768).*?(html\")";
//	private static final String DOWNLOAD_HREF = "(href|HREF)=\"/(down|showpic)/1366x768.*?(html\")";
	private static final String DOWNLOAD_HREF = "^.*/(down|showpic)/1366x768.*$";
	
	public static void main(String[] args) {

		try {
			DownLoadPicture cm = new DownLoadPicture();
			// 获得html文本内容
			String HTML = cm.getHtml(URL);
			// 获取图片标签
//			List<String> imgUrl = cm.getImageUrl(HTML);
			// 获取图片src地址
//			List<String> imgSrc = cm.getImageSrc(imgUrl);
			// 获取a标签
			List<String> aUrl = cm.getAUrl(HTML);
			// 获取a标签的href地址
			List<String> aHref = cm.getAHref(aUrl);
			// 真正要下载的图片地址集合(子页面)
			List<String> finalList = new ArrayList<String>();
			List<String> list = new ArrayList<String>();
			for (String href : aHref) {
				// 进入a标签的src路径 ，拿到子页面的html
				String html2 = cm.getHtml(href);
//				//匹配到子页面所有的a标签
//				List<String> aUrl2 = cm.getAUrl(html2);
//				//匹配子页面所有的a标签中的href属性
//				List<String> sonHref = cm.getAHref(aUrl2);
//				//匹配出包含图片下载地址的a标签
//				List<String> downLoadHref1 = cm.getDownLoadHref(sonHref);
				//匹配到子页面的所有img标签
				List<String> imageUrlList = cm.getImageUrl(html2);
				//匹配子页面中需要的src路径    加点的是不需要的
//				src="http://desk.fd.zol-img.com.cn/t_s960x600c5/g5/M00/0A/09/ChMkJ1mNg0SIUaV9ABQtXrf7TeoAAflnAKHKuwAFC12141.jpg"
//				.src="http://desk.fd.zol-img.com.cn/t_s208x130c5/g5/M00/0A/0D/ChMkJldhFs2IKIYEAAcw6-AfNhgAASojwOGpw4ABzED139.jpg"
				List<String> imageSrcList = cm.getImageSrc(imageUrlList);
				//创建最终的下载地址集合
				finalList = cm.removeImageSrc(imageSrcList);
				for (Iterator<String> it = finalList.iterator(); it.hasNext() ; ) {
					String url = it.next();
					if (!url.startsWith("http")){
						it.remove();
					}
				}
				//只下载960x600分辨率的
				for (int i = 0; i < finalList.size(); i++) {
					if (finalList.get(i).indexOf("960x600") != -1){
						list.add(finalList.get(i).replace("960x600","1366x768"));
					}
				}
//				List<String> downLoadHrefList1 = new ArrayList<String>();
				// 获取子页面的img标签
//				List<String> imgUrl1 = cm.getImageUrl(html2);
				// 获取子页面的img标签的src属性
//				List<String> imgSrc1 = cm.getImageSrc(imgUrl1);
				//获取子页面的a标签
//				cm.getAUrl(html2);
				// 截取图片标签list中的第一个值，因为后面的都是小图，不需要；
//				String url = imgSrc1.get(0);
				// 把第一个url放到list集合中，遍历这个集合进行下载
//				srcList.add(url);
			}
			log.info("最终的图片下载地址集合是:============"+list.toString());
			if (list != null && list.size() > 0) {
				//根据集合中的地址  下载图片
				cm.Download(list);
			}
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
		// 把所有的img标签都放到集合中
		while (matcher.find()) {
			listimgurl.add(matcher.group());
		}
//		log.info("图片地址list集合  :" + listimgurl);
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
//		log.info("图片的下载地址集合 : " + listImageSrc);
		return listImageSrc;
	}
	
	//去除不需要的src
	private List<String> removeImageSrc(List<String> imageSrcList){
		//.src="http://desk.fd.zol-img.com.cn/t_s208x130c5/g5/M00/0A/0D/ChMkJldhFs2IKIYEAAcw6-AfNhgAASojwOGpw4ABzED139.jpg"
		Iterator<String> it = imageSrcList.iterator();
		while(it.hasNext()){
		    String x = it.next();
		    Matcher matcher = Pattern.compile(NOIMGSRC_REG).matcher(x);
		    while (matcher.find()) {
		    	imageSrcList.remove(x);
			}
		}
//		log.info("图片的下载地址集合 : " + imageSrcList);
		return imageSrcList;
	}

	// 获取所有的a标签
	private List<String> getAUrl(String html) {
		Matcher matcher = Pattern.compile(A_REG).matcher(html);// 正则匹配出所有的a标签
		List<String> listAurl = new ArrayList<String>();
		// 把所有的a标签都放到集合中
		while (matcher.find()) {
			listAurl.add(matcher.group());
		}
//		log.info("图片地址list集合  :" + listAurl);
		return listAurl;
	}

	// 获取a标签的href地址
	private List<String> getAHref(List<String> aUrl) {
		List<String> listAHref = new ArrayList<String>();
		// http://desk.zol.com.cn/ + href="/bizhi/7217_89405_2.html"
		for (String href : aUrl) {
			String href1 = href.replace(" ", "");
			Matcher matcher = Pattern.compile(AHREF_REG).matcher(href1);// 正则匹配出有用的a标签的href
			while (matcher.find()) {// 如果能匹配到就 对匹配到的字符串进行截取 取出我们需要的图片地址
				if ( !matcher.group().substring(6,matcher.group().length() - 1).startsWith("http")){
					listAHref.add(URL1
							+ matcher.group().substring(6,
							matcher.group().length() - 1));
				}
			}
		}
//		log.info("图片的跳转地址集合 : " + listAHref);
		return listAHref;
	}

	//获取a标签 下的下载地址
	private List<String> getDownLoadHref(List<String> downLoad){
		List<String> listAHref = new ArrayList<String>();
		// http://desk.zol.com.cn/ + href="/bizhi/7217_89405_2.html"
		for (String href : downLoad) {
			String href1 = href.replace(" ", "");
			Matcher matcher = Pattern.compile(DOWNLOAD_HREF).matcher(href1);// 正则匹配出有用的a标签的href
			while (matcher.find()) {// 如果能匹配到就 对匹配到的字符串进行截取 取出我们需要的图片地址
				listAHref.add(matcher.group().substring(7,
								matcher.group().length()));
			}
		}
		//去除重复的url
		List<String> removeDuplicate = DownLoadPicture.removeDuplicate(listAHref);
//		log.info("图片的下载地址集合 : " + listAHref);
		return removeDuplicate;
	}
	
	//通过HashSet踢除重复元素
	public static List<String> removeDuplicate(List<String> list) {   
		HashSet<String> h = new HashSet<String>(list);   
		list.clear();   
		list.addAll(h);   
		return list;   
		}   
	
	// 下载图片
	private void Download(List<String> listImgSrc) {
		try {
			// 抓取图片开始时间
			Date begindate = new Date();
			OutputStream os = null;// 定义一个输出流
			Long count = 0L;
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
				count ++;
			}
			if (os != null) {
				os.close();
			}
			Date endDate = new Date();
			double sumTime = endDate.getTime() - begindate.getTime();
			System.out.println("下载完成。。。。。。。。。。。。。。。。共下载"+count+"张图片");
			System.out.println(">>>>>>>>>>>>>>>>>>>>>抓取所以资源共耗时  :" + sumTime
					/ 1000 + "秒");
		} catch (Exception e) {
			System.out.println("下载失败");
			log.info("下载失败:exception :" + e.getMessage());
			e.printStackTrace();
		}
	}
}