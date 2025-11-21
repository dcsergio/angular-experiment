package it.sdc.restserver.controller;

import com.itextpdf.forms.PdfAcroForm;
import com.itextpdf.forms.fields.PdfFormField;
import com.itextpdf.forms.fields.TextFormFieldBuilder;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.properties.TextAlignment;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.OutputStream;


@RestController
@RequestMapping(value = "/modules")
@CrossOrigin(origins = "http://localhost:4200")
public class ModulesController {

    @GetMapping("/generate-pdf")
    public void generatePdf(@RequestParam String nome,
                            @RequestParam String cognome,
                            @RequestParam String lavoro,
                            HttpServletResponse response) throws IOException {

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=modulo.pdf");

        try (OutputStream out = response.getOutputStream()) {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc);

            // Logo (caricato da resources)
            Image logo = new Image(ImageDataFactory.create("src/main/resources/logo.png"));
            logo.setWidth(100);
            logo.setHeight(100);
            logo.setFixedPosition(120 , 780);
            document.add(logo);

            // Titolo
            PdfFont boldFont = PdfFontFactory.createFont(com.itextpdf.io.font.constants.StandardFonts.HELVETICA_BOLD);
            Paragraph title = new Paragraph("Modulo Informazioni")
                    .setFont(boldFont)
                    .setFontSize(18)
                    .setTextAlignment(TextAlignment.CENTER)
                    .setMarginTop(80);
            document.add(title);

            // Linea separatrice
            document.add(new Paragraph("________________________________________")
                    .setTextAlignment(TextAlignment.CENTER)
                    .setFontColor(ColorConstants.GRAY));

            // Creazione form
            PdfAcroForm form = PdfAcroForm.getAcroForm(pdfDoc, true);

            // Campi modulo (posizionati con coordinate assolute)
            PdfFormField nomeField = new TextFormFieldBuilder(pdfDoc, "nome")
                    .setWidgetRectangle(new Rectangle(150, 650, 250, 20))
                    .createText()
                    .setValue(nome);

            PdfFormField cognomeField = new TextFormFieldBuilder(pdfDoc, "cognome")
                    .setWidgetRectangle(new Rectangle(150, 600, 250, 20))
                    .createText()
                    .setValue(cognome);

            PdfFormField lavoroField = new TextFormFieldBuilder(pdfDoc, "lavoro")
                    .setWidgetRectangle(new Rectangle(150, 550, 250, 20))
                    .createText()
                    .setValue(lavoro);

            // Aggiungi i campi al form
            form.addField(nomeField);
            form.addField(cognomeField);
            form.addField(lavoroField);


            form.flattenFields();

            document.close();
        }
    }


}
