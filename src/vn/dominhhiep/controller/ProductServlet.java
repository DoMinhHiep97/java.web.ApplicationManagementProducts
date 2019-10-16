package vn.dominhhiep.controller;

import vn.dominhhiep.model.Product;
import vn.dominhhiep.service.ProductService;
import vn.dominhhiep.service.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends javax.servlet.http.HttpServlet {

    private ProductService productService=new ProductServiceImpl();

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action=request.getParameter("action");
        if(action == null){
            action="";
        }
        switch (action){
            case "create":
                createProduct(request,response);
                break;
            case "edit":
                updateProduct(request,response);
                break;
            case "delete":
                deleteProduct(request,response);
                break;
            case "search":
                searchProduct(request,response);
            default:
                break;
        }
    }

    private void searchProduct(HttpServletRequest request, HttpServletResponse response ) {
        String name=request.getParameter("name");
        ArrayList<Product> arrayList= (ArrayList<Product>) this.productService.findAll();
        for (Product product : arrayList) {
            if (product.getName().equals(name)) {
                int id=product.getId();
                try {
                    response.sendRedirect("/products?action=view&&id="+id);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            this.productService.remove(id);
            try {
                response.sendRedirect("/products");
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        String name=request.getParameter("name");
        String information=request.getParameter("information");
        String producer=request.getParameter("producer");
        double price=Double.parseDouble(request.getParameter("price"));

        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product ==null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            product.setName(name);
            product.setInformation(information);
            product.setProducer(producer);
            product.setPrice(price);
            request.setAttribute("product",product);
            request.setAttribute("message","Product information was updated");
            dispatcher=request.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createProduct(HttpServletRequest request, HttpServletResponse response) {
        String name= request.getParameter("name");
        String information= request.getParameter("information");
        String producer=request.getParameter("producer");
        double price = Double.parseDouble(request.getParameter("price"));
        int id= (int)(Math.random()*10000);


        Product product = new Product(id,name,price,information,producer);
        this.productService.save(product);
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/create.jsp");
        request.setAttribute("message", "New product was created");
        try{
            dispatcher.forward(request,response);
        }catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String action=request.getParameter("action");
        if(action ==null){
            action="";
        }
        switch (action){
            case "create":
                showCreateForm(request,response);
                break;
            case "edit":
                showEditForm(request,response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                viewProduct(request,response);
                break;
            case "search":
                showSearchForm(request,response);
                break;
            default:
                listProducts(request,response);
                break;
        }
    }

    private void showSearchForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/search.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewProduct(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else{
            request.setAttribute("product",product);
            dispatcher =request.getRequestDispatcher("product/view.jsp");
        } try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDeleteForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product=this.productService.findById(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher=request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product",product);
            dispatcher=request.getRequestDispatcher("product/delete.jsp");
        }
        try {
            dispatcher.forward(request,response);
        } catch (ServletException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        int id=Integer.parseInt(request.getParameter("id"));
        Product product= this.productService.findById(id);
        RequestDispatcher dispatcher;
        if(product == null){
            dispatcher =request.getRequestDispatcher("error-404.jsp");
        }else {
            request.setAttribute("product", product);
            dispatcher=request.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher=request.getRequestDispatcher("product/create.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products= this.productService.findAll();
        request.setAttribute("products",products);

        RequestDispatcher dispatcher=request.getRequestDispatcher("product/list.jsp");
        try {
            dispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
