package ru.job4j.cars.servlet;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import ru.job4j.cars.model.Post;
import ru.job4j.cars.model.User;
import ru.job4j.cars.service.CarsService;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class AddPostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletContext servletContext = this.getServletConfig().getServletContext();
        File repository = (File) servletContext.getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(repository);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        try {
            int carBrandId = 0;
            int carModelId = 0;
            int bodyTypeId = 0;
            String bodyColor = "";
            int mileAge = 0;
            int ageYears = 0;
            String description = "";
            String photoFileName = "";

            List<FileItem> items = upload.parseRequest(req);
            for (FileItem item : items) {
                if (item.isFormField()) {
                    if ("carBrand".equals(item.getFieldName())) {
                        carBrandId = Integer.parseInt(item.getString());
                    }
                    if ("carModel".equals(item.getFieldName())) {
                        carModelId = Integer.parseInt(item.getString());
                    }
                    if ("bodyType".equals(item.getFieldName())) {
                        bodyTypeId = Integer.parseInt(item.getString());
                    }
                    if ("bodyColor".equals(item.getFieldName())) {
                        bodyColor = item.getString("utf-8");
                    }
                    if ("mileAge".equals(item.getFieldName())) {
                        mileAge = Integer.parseInt(item.getString());
                    }
                    if ("ageYears".equals(item.getFieldName())) {
                        ageYears = Integer.parseInt(item.getString());
                    }
                    if ("description".equals(item.getFieldName())) {
                        description = item.getString("utf-8");
                    }
                }
            }

            User user = (User) req.getSession().getAttribute("user");
            Post post = CarsService.instOf().addPost(carBrandId, carModelId, bodyTypeId, bodyColor,
                    mileAge, ageYears, description, user);

            File folder = new File("c:\\images\\");
            if (!folder.exists()) {
                folder.mkdir();
            }
            for (FileItem item : items) {
                if (!item.isFormField()) {
                    photoFileName = item.getName();
                    String[] photoFileNameSplit = photoFileName.split("\\.");
                    photoFileNameSplit[0] = String.valueOf(post.getId());
                    photoFileName = photoFileNameSplit[0] + "." + photoFileNameSplit[1];
                    CarsService.instOf().saveCarImage(post.getId(), photoFileName);
                    File file = new File(folder + File.separator + photoFileName);
                    try (FileOutputStream out = new FileOutputStream(file)) {
                        out.write(item.getInputStream().readAllBytes());
                    }
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        }

        resp.sendRedirect(req.getContextPath());
    }

}