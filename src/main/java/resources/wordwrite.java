package resources;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.BreakType;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

public class wordwrite {

		
		
	private static final String callerClassName = new Exception().getStackTrace()[1].getClassName();
	private static final String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH_mm").format(Calendar.getInstance().getTime());

			
			//To DO write a method to read image file names from excel and call the method from function below to get Image names
			public void creatDoc(ArrayList<String> imgnames) throws IOException, InvalidFormatException {
				
				XWPFDocument doc = new XWPFDocument();
				XWPFParagraph p = doc.createParagraph();
		 
				XWPFRun r = p.createRun();
				
	  for (String image: imgnames)
	  {
			File img1 = new File("screenshots/"+image+".png");
			//File img2 = new File("screenshots/invalid_loginscreenshot.png");
	 
			BufferedImage bimg1 = ImageIO.read(img1);
			//int width1 = bimg1.getWidth();
			int height1 = bimg1.getHeight();
	 
			//BufferedImage bimg2 = ImageIO.read(img2);
			//int width2 = bimg2.getWidth();
			//int height2 = bimg2.getHeight();
	 
			String imgFile1 = img1.getName();
			//String imgFile2 = img2.getName();
	 
			int imgFormat1 = getImageFormat(imgFile1);
			//int imgFormat2 = getImageFormat(imgFile2);
	 
			r.setText(imgFile1);
			r.addBreak();
			r.addPicture(new FileInputStream(img1), imgFormat1, imgFile1, Units.toEMU(500), Units.toEMU(200));
			r.addBreak(BreakType.TEXT_WRAPPING);
	 
			//r.setText(imgFile2);
			//r.addBreak();
		//r.addPicture(new FileInputStream(img2), imgFormat2, imgFile2, Units.toEMU(500), Units.toEMU(200));
			
	  }
	  		String callerClassName = new Exception().getStackTrace()[1].getClassName();
	  		String timeStamp = new SimpleDateFormat("yyyyMMdd_HH_mm").format(Calendar.getInstance().getTime());

			FileOutputStream out = new FileOutputStream("test_evidence/"+callerClassName+"-"+timeStamp+".docx");
			doc.write(out);
			
			out.close();
			//doc.close();
			
		}
	 
		public static int getImageFormat(String imgFile) {
			int format;
			if (imgFile.endsWith(".emf"))
				format = XWPFDocument.PICTURE_TYPE_EMF;
			else if (imgFile.endsWith(".wmf"))
				format = XWPFDocument.PICTURE_TYPE_WMF;
			else if (imgFile.endsWith(".pict"))
				format = XWPFDocument.PICTURE_TYPE_PICT;
			else if (imgFile.endsWith(".jpeg") || imgFile.endsWith(".jpg"))
				format = XWPFDocument.PICTURE_TYPE_JPEG;
			else if (imgFile.endsWith(".png"))
				format = XWPFDocument.PICTURE_TYPE_PNG;
			else if (imgFile.endsWith(".dib"))
				format = XWPFDocument.PICTURE_TYPE_DIB;
			else if (imgFile.endsWith(".gif"))
				format = XWPFDocument.PICTURE_TYPE_GIF;
			else if (imgFile.endsWith(".tiff"))
				format = XWPFDocument.PICTURE_TYPE_TIFF;
			else if (imgFile.endsWith(".eps"))
				format = XWPFDocument.PICTURE_TYPE_EPS;
			else if (imgFile.endsWith(".bmp"))
				format = XWPFDocument.PICTURE_TYPE_BMP;
			else if (imgFile.endsWith(".wpg"))
				format = XWPFDocument.PICTURE_TYPE_WPG;
			else {
				return 0;
			}
			return format;
		}

}
