package com.ProductApplication;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class ProductService {
    private static Connection conn = null;

    static {
        String url = "jdbc:mysql://localhost:3306/productjdbc";
        String username = "root";
        String password = "";
        try {
            conn= DriverManager.getConnection(url,username,password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public int insertData(Product product){
        int n = 0;
        String query = "INSERT INTO PRODUCT_INFO VALUE (?,?,?,?)";
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,product.getProductId());
            pstmt.setString(2,product.getProductName());
            pstmt.setDouble(3,product.getProductPrice());
            pstmt.setString(4,product.getProductType());
            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }
    public List<Product> displayAllProduct(){
        String query = "SELECT * FROM PRODUCT_INFO";
        List<Product> products = new ArrayList<>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()){
                int productId =rs.getInt(1);
                String productName = rs.getString(2);
                double productPrice = rs.getDouble(3);
                String productType = rs.getString(4);

                Product product = new Product(productId, productName, productPrice,productType);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public int deleteProductById(int productId){
        int n =0;
        String query = "DELETE FROM PRODUCT_INFO WHERE PRODUCTID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,productId);
            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }

    public Product searchProductById(int productId){
        Product product = null;
        String query = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTID = ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setInt(1,productId);
            ResultSet rs =pstmt.executeQuery();
            while (rs.next()){
                 product = new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getString(4));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return product;
    }

    public int updateProduct(Product product){
        int n =0;
        String query = "UPDATE PRODUCT_INFO SET PRODUCTNAME= ?, PRODUCTPRICE = ?, PRODUCTTYPE = ? WHERE PRODUCTID = ?" ;
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,product.getProductName());
            pstmt.setDouble(2,product.getProductPrice());
            pstmt.setString(3,product.getProductType());
            pstmt.setInt(4,product.getProductId());
            n = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return n;
    }
    public List<Product> filterProductByID(double productPrice){
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTPRICE > ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,productPrice);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getString(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public List<Product> filterProductByPriceLess(double productPrice){
        List<Product> productList = new ArrayList<>();
        String query = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTPRICE < ?";
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setDouble(1,productPrice);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getString(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }

    public List<Product> selectSpecificCategoryOfProduct(String productType){
        String query = "Select * From PRODUCT_INFO WHERE PRODUCTTYPE=?";
        List<Product> productCategory = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,productType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getString(4));
                productCategory.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productCategory;
    }
    public List<Product> showProductAccordingToPrice(String productType){
        String query = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTTYPE = ? ORDER BY PRODUCTPRICE ASC";
        List<Product> productlist = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,productType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2)
                        ,rs.getDouble(3),rs.getString(4));
                productlist.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productlist;
    }

    public List<Product> showProductAccordingToDec(String productType){
        String query = "SELECT * FROM PRODUCT_INFO WHERE PRODUCTTYPE = ? ORDER BY PRODUCTPRICE DESC";
        List<Product> productList = new ArrayList<>();
        try {
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1,productType);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                Product product = new Product(rs.getInt(1),rs.getString(2),
                        rs.getDouble(3),rs.getString(4));
                productList.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productList;
    }
}

