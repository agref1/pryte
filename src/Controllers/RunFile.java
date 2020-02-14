package Controllers;

import sun.misc.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;


@MultipartConfig(maxFileSize = 16177215)
public class RunFile extends RequestHandler {
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        InputStream inputStream = null; // input stream of the upload file
        Part filePart = null;
        String naam = request.getParameter("naam");
        String FILE_TO = "C:\\Users\\jefuy\\Documents\\pryteapp\\uploadedfiles\\";

        try {
            System.out.println("enter try block RunFIle Runfile" + naam);
            filePart = request.getPart("file");
            FILE_TO = FILE_TO + "\\" + filePart.getName() + filePart.getSubmittedFileName();
            System.out.println(filePart.getContentType() + "  " + filePart.getName());
            if (filePart != null) {
                System.out.println("first if block RunFIle Runfile");

                System.out.println("secund if block RunFIle Runfile");
                // get extension
                String extension = filePart.getContentType();
                String[] parts = extension.split("/");
                //extension = parts[parts.length - 1];
                System.out.println("extention gotten " + filePart.getName() + filePart.getSubmittedFileName());
                // obtains input stream of the upload file
                inputStream = filePart.getInputStream();
                File file = new File(FILE_TO);
                copyInputStreamToFile(inputStream, file);

                System.out.println("normaal is de file gesaved hier"+ file.getAbsolutePath());

            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        }
        return "test.jsp";
    }

    // InputStream -> File
    private static void copyInputStreamToFile(InputStream inputStream, File file)
            throws IOException {

        try (FileOutputStream outputStream = new FileOutputStream(file)) {

            int read;
            byte[] bytes = new byte[1024];

            while ((read = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }

            // commons-io
            //IOUtils.copy(inputStream, outputStream);

        }

    }
}
