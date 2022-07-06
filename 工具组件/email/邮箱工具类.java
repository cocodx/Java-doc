package cn.richinfo.examination.util;

import org.springframework.util.CollectionUtils;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendMail {

	// 收件人邮箱地址
	private String to;
	// 发件人邮箱地址
	private String from;
	// SMTP服务器地址
	private String smtpServer;
	// 登录SMTP服务器的用户名
	private String username;
	// 登录SMTP服务器的密码
	private String password;
	// 邮件主题
	private String subject;
	// 邮件正文
	private String content;
	// 记录所有附件文件的集合
	List<String> attachments = new ArrayList<String>();

	// 无参数的构造器
	public SendMail() {
	}

	public SendMail(String to, String from, String smtpServer, String username,
                    String password, String subject, String content) {
		this.to = to;
		this.from = from;
		this.smtpServer = smtpServer;
		this.username = username;
		this.password = password;
		this.subject = subject;
		this.content = content;
	}

	// to属性的setter方法
	public void setTo(String to) {
		this.to = to;
	}

	// from属性的setter方法
	public void setFrom(String from) {
		this.from = from;
	}

	// smtpServer属性的setter方法
	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	// username属性的setter方法
	public void setUsername(String username) {
		this.username = username;
	}

	// password属性的setter方法
	public void setPassword(String password) {
		this.password = password;
	}

	// subject属性的setter方法
	public void setSubject(String subject) {
		this.subject = subject;
	}

	// content属性的setter方法
	public void setContent(String content) {
		this.content = content;
	}

	public String getTo() {
		return to;
	}

	public String getFrom() {
		return from;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getSubject() {
		return subject;
	}

	public String getContent() {
		return content;
	}

	// 把邮件主题转换为中文
	public String transferChinese(String strText) {
		try {
			strText = MimeUtility.encodeText(new String(strText.getBytes(),
					"UTF-8"), "UTF-8", "B");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strText;
	}

	// 增加附件，将附件文件名添加的List集合中
	public void attachfile(String fname) {
		attachments.add(fname);
	}

	public static void main(String[] args) {
		SendMail sendMail = new SendMail();
		sendMail.setSmtpServer("192.168.8.174");
		sendMail.setSubject("测试发送邮件");
		sendMail.setFrom("admin2@csair2.com");
		sendMail.setTo("admin2@csair2.com");
		sendMail.setUsername("admin2@csair2.com");
		sendMail.setSubject("云邮箱服务sxfdsfs");
		sendMail.setContent("sdfadfadfasfdsaf");
		sendMail.setPassword("1q2w3e");
		System.out.println(sendMail.sendSystem());
	}

	// 发送邮件
	public boolean send() {
		// 创建邮件Session所需的Properties对象
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		// 创建Session对象
		Session session = Session.getDefaultInstance(props
		// 以匿名内部类的形式创建登录服务器的认证对象
				, new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			// 构造MimeMessage并设置相关属性值
			MimeMessage msg = new MimeMessage(session);
			// 设置发件人
			msg.setFrom(new InternetAddress(from));
			// 设置收件人
			InternetAddress[] addresses = { new InternetAddress(to) };
			msg.setRecipients(Message.RecipientType.TO, addresses);
			// 设置邮件主题
			subject = transferChinese(subject);
			msg.setSubject(subject);
			// 构造Multipart
			Multipart mp = new MimeMultipart();
			// 向Multipart添加正文
			MimeBodyPart mbpContent = new MimeBodyPart();
			mbpContent.setContent(content, "text/html;charset=utf-8");//html样式的
			//mbpContent.setText(content);纯文本格式的
			// 将BodyPart添加到MultiPart中
			mp.addBodyPart(mbpContent);
			// 向Multipart添加附件
			// 遍历附件列表，将所有文件添加到邮件消息里
			for (String efile : attachments) {
				MimeBodyPart mbpFile = new MimeBodyPart();
				// 以文件名创建FileDataSource对象
				FileDataSource fds = new FileDataSource(efile);
				// 处理附件
				mbpFile.setDataHandler(new DataHandler(fds));
				mbpFile.setFileName(transferChinese(fds.getName()));
				// 将BodyPart添加到MultiPart中
				mp.addBodyPart(mbpFile);
			}
			// 清空附件列表
			attachments.clear();
			// 向Multipart添加MimeMessage
			msg.setContent(mp);
			// 设置发送日期
			msg.setSentDate(new Date());
			// 发送邮件
			Transport.send(msg);
		} catch (MessagingException mex) {
			mex.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean sendSystem() {
		// 创建邮件Session所需的Properties对象
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);
		props.put("mail.smtp.auth", "true");
		// 创建Session对象
		Session session = Session.getDefaultInstance(props
				// 以匿名内部类的形式创建登录服务器的认证对象
				, new Authenticator() {
					@Override
					public PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(username, password);
					}
				});
		try {
			// 构造MimeMessage并设置相关属性值
			MimeMessage message = new MimeMessage(session);
			// 设置发件人
			message.setFrom(new InternetAddress(from));
			// 设置收件人
			InternetAddress[] addresses = { new InternetAddress(to) };
			message.setRecipients(Message.RecipientType.TO, addresses);
			// 设置邮件主题
			subject = transferChinese(subject);
			message.setSubject(subject);
			// 设置发送日期
			message.setSentDate(new Date());

			//给消息对象设置内容
			//新建一个存放信件内容的BodyPart对象
			BodyPart mdp=new MimeBodyPart();
			//给BodyPart对象设置内容和格式/编码方式
			mdp.setContent(content, "text/html;charset=utf-8");
			//用于组合文本和图片，"related"型的MimeMultipart对象
			//新建一个MimeMultipart对象用来存放BodyPart对象(事实上可以存放多个)
			Multipart mm=new MimeMultipart("related");
			//将BodyPart加入到MimeMultipart对象中(可以加入多个BodyPart)
			mm.addBodyPart(mdp);

			// 向Multipart添加附件
			// 遍历附件列表，将所有文件添加到邮件消息里
			if (!CollectionUtils.isEmpty(attachments)){
				for (String efile : attachments) {
					MimeBodyPart mbpFile = new MimeBodyPart();
					// 以文件名创建FileDataSource对象
					FileDataSource fds = new FileDataSource(efile);
					// 处理附件
					mbpFile.setDataHandler(new DataHandler(fds));
					mbpFile.setFileName(transferChinese(fds.getName()));
					// 将BodyPart添加到MultiPart中
					mm.addBodyPart(mbpFile);
				}
				// 清空附件列表
				attachments.clear();
			}
			//添加图片
//			mm.addBodyPart(createImageMimeBodyPart("logo_login.png"));
			//把mm作为消息对象的内容
			message.setContent(mm);
			message.saveChanges();
			// 发送邮件
			Transport.send(message);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static MimeBodyPart createImageMimeBodyPart(String imageName) throws MessagingException, UnsupportedEncodingException {
		FileDataSource fds=new FileDataSource(new File("/home/richmail/webconf/email_image/"+imageName));
		MimeBodyPart mbp=new MimeBodyPart();
		DataHandler dh=new DataHandler(fds);
		mbp.setDataHandler(dh);
		//设置对应的资源文件的唯一标识符，即 MIME 协议对于邮件的结构组织格式中的 Content-ID 头字段；
		mbp.setHeader("Content-ID", imageName);
		mbp.setFileName(MimeUtility.encodeText(fds.getName()));
		return mbp;
	}

	@Override
	public String toString() {
		return "SendMail{" +
				"to='" + to + '\'' +
				", from='" + from + '\'' +
				", smtpServer='" + smtpServer + '\'' +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", subject='" + subject + '\'' +
				", content='" + content + '\'' +
				", attachments=" + attachments +
				'}';
	}
}
