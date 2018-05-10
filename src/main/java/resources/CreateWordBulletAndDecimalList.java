package resources;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.poi.xwpf.usermodel.*;

import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTAbstractNum;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTNumbering;

import java.math.BigInteger;

public class CreateWordBulletAndDecimalList {

 static String cTAbstractNumBulletXML = 
  "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"0\">"
+ "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
+ "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Wingdings\" w:hAnsi=\"Wingdings\" w:hint=\"default\"/></w:rPr></w:lvl>"
+ "<w:lvl w:ilvl=\"1\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"-\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Courier New\" w:hAnsi=\"Courier New\" w:cs=\"Courier New\" w:hint=\"default\"/></w:rPr></w:lvl>"
+ "<w:lvl w:ilvl=\"2\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"bullet\"/><w:lvlText w:val=\"\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr><w:rPr><w:rFonts w:ascii=\"Symbol\" w:hAnsi=\"Symbol\" w:hint=\"default\"/></w:rPr></w:lvl>"
+ "</w:abstractNum>";

 static String cTAbstractNumDecimalXML = 
  "<w:abstractNum xmlns:w=\"http://schemas.openxmlformats.org/wordprocessingml/2006/main\" w:abstractNumId=\"1\">"
+ "<w:multiLevelType w:val=\"hybridMultilevel\"/>"
+ "<w:lvl w:ilvl=\"0\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"720\" w:hanging=\"360\"/></w:pPr></w:lvl>"
+ "<w:lvl w:ilvl=\"1\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.%2\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"1440\" w:hanging=\"360\"/></w:pPr></w:lvl>"
+ "<w:lvl w:ilvl=\"2\" w:tentative=\"1\"><w:start w:val=\"1\"/><w:numFmt w:val=\"decimal\"/><w:lvlText w:val=\"%1.%2.%3\"/><w:lvlJc w:val=\"left\"/><w:pPr><w:ind w:left=\"2160\" w:hanging=\"360\"/></w:pPr></w:lvl>"
+ "</w:abstractNum>";

 public static void main(String[] args) throws Exception {

  XWPFDocument document = new XWPFDocument();

  CTNumbering cTNumbering = CTNumbering.Factory.parse(cTAbstractNumBulletXML);
  CTAbstractNum cTAbstractNum = cTNumbering.getAbstractNumArray(0);
  XWPFAbstractNum abstractNum1 = new XWPFAbstractNum(cTAbstractNum);


  cTNumbering = CTNumbering.Factory.parse(cTAbstractNumDecimalXML);
  cTAbstractNum = cTNumbering.getAbstractNumArray(0);
  XWPFAbstractNum abstractNum2 = new XWPFAbstractNum(cTAbstractNum);

  XWPFNumbering numbering = document.createNumbering();

  BigInteger abstractNumID1 = numbering.addAbstractNum(abstractNum1);
  BigInteger numID1 = numbering.addNum(abstractNumID1);

  BigInteger abstractNumID2 = numbering.addAbstractNum(abstractNum2);
  BigInteger numID2 = numbering.addNum(abstractNumID2);

  XWPFParagraph paragraph = document.createParagraph();
  XWPFRun run=paragraph.createRun();  
  run.setText("The lists:");
  paragraph = document.createParagraph();

  paragraph = document.createParagraph();
  run=paragraph.createRun();  
  run.setText("The bullet list:");

  for (int i = 0; i < 5; i++) {
   paragraph = document.createParagraph();
   paragraph.setNumID(numID1);
   run = paragraph.createRun();
   run.setText("List item " + (i+1)); 
   if (i < 4) paragraph.setSpacingAfter(0);
   if (i == 0) {
    for (int j = 0; j < 2; j++) {
     paragraph = document.createParagraph();
     paragraph.setNumID(numID1);
     paragraph.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(1));
     run = paragraph.createRun();
     run.setText("Sub list item " + (i+1) + " " + (char)(97+j));
     paragraph.setSpacingAfter(0);
    }
   }
   if (i == 1 || i == 3) {
    paragraph = document.createParagraph();
    paragraph.setNumID(numID1);
    paragraph.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(1));
    run = paragraph.createRun();
    run.setText("Sub list item " + (i+1) + " a");
    paragraph.setSpacingAfter(0); 
   }
  }

  paragraph = document.createParagraph();
  paragraph = document.createParagraph();
  run=paragraph.createRun();  
  run.setText("The decimal list:");

  for (int i = 0; i < 5; i++) {
   paragraph = document.createParagraph();
   paragraph.setNumID(numID2);
   run = paragraph.createRun();
   run.setText("List item " + (i+1)); 
   if (i < 4) paragraph.setSpacingAfter(0);
   if (i == 0) {
    for (int j = 0; j < 2; j++) {
     paragraph = document.createParagraph();
     paragraph.setNumID(numID2);
     paragraph.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(1));
     run = paragraph.createRun();
     run.setText("Sub list item " + (i+1) + " " + (char)(97+j));
     paragraph.setSpacingAfter(0);
    }
   }
   if (i == 1 || i == 3) {
    paragraph = document.createParagraph();
    paragraph.setNumID(numID2);
    paragraph.getCTP().getPPr().getNumPr().addNewIlvl().setVal(BigInteger.valueOf(1));
    run = paragraph.createRun();
    run.setText("Sub list item " + (i+1) + " a");
    paragraph.setSpacingAfter(0); 
   }
  }

  paragraph = document.createParagraph();
  paragraph = document.createParagraph();
  run=paragraph.createRun();  
  run.setText("Paragraph after the lists.");

  FileOutputStream out = new FileOutputStream("CreateWordBulletAndDecimalList.docx");    
  document.write(out);

  System.out.println("CreateWordBulletAndDecimalList written successully");
 }
}