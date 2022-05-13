/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.esprit.services;

/**
 *
 * @author Moez
 */



import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import edu.esprit.entities.Choix;
import edu.esprit.entities.Question;


public class ServicePdf {
    
    
    
    private void insertCell(PdfPTable table, String text, int align, int colspan, Font font){
   
  //create a new cell with the specified Text and Font
  PdfPCell cell = new PdfPCell(new Phrase(text.trim(), font));
  //set the cell alignment
  cell.setHorizontalAlignment(align);
  //set the cell column span in case you want to merge two or more cells
  cell.setColspan(colspan);
  //in case there is no text and you wan to create an empty row
  if(text.trim().equalsIgnoreCase("")){
   cell.setMinimumHeight(10f);
  }
  //add the call to the table
  table.addCell(cell);
   
 }
    
    
    
    public void liste_ProjetPDF() throws FileNotFoundException, DocumentException {

        QuestionService QS = new QuestionService();
        ChoixService CS = new ChoixService();
        
        String message = "Voici la liste des articles \n\n";

        String file_name = "C:\\Users\\Moez\\Desktop\\liste_Projet.pdf";
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(file_name));
        document.open();
        Paragraph para = new Paragraph(message);
        document.add(para);
         List<Question> questions = QS.afficherQuestion(29);
        PdfPTable table = new PdfPTable(3);

        
        PdfPCell cl1 = new PdfPCell(new Phrase("Question numéro"));
        table.addCell(cl1);
        PdfPCell cl2 = new PdfPCell(new Phrase("Enoncé"));
        table.addCell(cl2);
        PdfPCell cl3 = new PdfPCell(new Phrase("Réponses"));
        table.addCell(cl3);
        
        Font bf12 = new Font(FontFamily.TIMES_ROMAN, 12); 
         
        
        
        table.setHeaderRows(1);
        document.add(table);

        
        for (int i = 0; i < questions.size(); i++) {
            int nbr = i+1;
            List<Choix> choix = CS.afficherChoix(questions.get(i).getId());
            insertCell(table, ""+nbr , Element.ALIGN_RIGHT, 1, bf12);
            insertCell(table, questions.get(i).getContenu() , Element.ALIGN_LEFT, 1, bf12);
            insertCell(table, choix.get(0).getContenu(), Element.ALIGN_LEFT, 1, bf12);
            for (int j = 1; j < 3; j++) {
                insertCell(table, "" , Element.ALIGN_RIGHT, 1, bf12);
                insertCell(table, "" , Element.ALIGN_LEFT, 1, bf12);
                insertCell(table, choix.get(j).getContenu(), Element.ALIGN_LEFT, 1, bf12);
            }
            
            System.out.println("quuuuuu "+questions.get(i).getContenu());
            
            System.out.println("choixxx "+choix.get(0).getContenu());
            
            
            

            


                    

        }
        document.add(table);

        document.close();

    }
    
}
