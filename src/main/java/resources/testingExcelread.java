package resources;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class testingExcelread {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CreateEvidence wd =new CreateEvidence();
		 try {
			ReadExcel eat = new ReadExcel("./Testcase-TransparenSee.xlsx");
			
			ArrayList<String[]> test= new ArrayList<String[]>();
			test = eat.getTestcases("Sheet1", "ID", 5, "id:36580");
			wd.creatDoc(test);
			
			for (String[] steps:test)
				System.out.println(Arrays.toString(steps));
//			ArrayList<String> imgnames= new ArrayList<String>();
//			int counter= 0;
//			for (String[] step: test)
//			{	if (step[2].equalsIgnoreCase("yes"))
//				{
//				counter += 1;
//				imgnames.add("Test evidence_"+counter);
//				System.out.println(imgnames.toString());
//				//imgnames will have list of expected values that need screenshots
//				}
//			}
//			
//			
//			for (String image : imgnames) {
//				String screenshotName=image; 
//				File img1 = new File("Screenshot/" + screenshotName + ".png");
//				// File img2 = new File("screenshots/invalid_loginscreenshot.png");
//
//				if(img1.exists()){
//			        System.out.println("image found");
//			    }else
//			    {
//			    	System.out.println("image not found: "+img1.getName());
//			    }
//			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}        
	        
	}

}
