package com.ProductApplication;
import java.util.List;
import java.util.Scanner;

public class MainApp {
    private static ProductService service = new ProductService();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("SELECT THE OPTION ");
        System.out.println("1.Insert the data :");
        System.out.println("---------------------------");
        System.out.println("2.Display all product :");
        System.out.println("---------------------------");
        System.out.println("3.Delete Product By ID :");
        System.out.println("----------------------------");
        System.out.println("4.Search Product by id :");
        System.out.println("--------------------------");
        System.out.println("5.Update the Product :");
        System.out.println("----------------------------");
        System.out.println("6.Filter product by price :");
        System.out.println("---------------------------");
        System.out.println("7.Filter the product according to less price :");
        System.out.println("-------------------------------");
        System.out.println("8.Search product according to category :");
        System.out.println("--------------------------------");
        System.out.println("9.Show the product category and its lesser price :");
        System.out.println("-----------------------------------");
        System.out.println("10.Show the product category in descending order :");
        System.out.println("--------------------------------------------");


        int ch = sc.nextInt();
        switch (ch){
            case 1:
                insertData();
                break;
            case 2:
                getAllProduct();
                break;
            case 3:
                deleteByID();
                break;
            case 4:
                searchByID();
                break;
            case 5:
                updateProduct();
                break;
            case 6:
                filterProductByPrice();
                break;
            case 7:
                filterProductByPirceLesser();
                break;
            case 8:
                filterProductCategoryByType();
                break;
            case 9:
                filterProductInAscendingOrder();
                break;
            case 10:
                filterProductAccordingDecendingOrder();
                break;
            default:
                System.exit(0);

        }
        main(args);
    }
    public static void insertData(){
        System.out.println("Enter the product id :");
        int id = sc.nextInt();
        System.out.println("Enter the Product Name :");
        String productName = sc.next();
        System.out.println("Enter the Product Price :");
        double productPrice = sc.nextDouble();
        System.out.println("Enter the Product type :");
        String productType = sc.next();
        Product product = new Product(id, productName,productPrice,productType);
        int n = service.insertData(product);
        System.out.println(n+ " product added successfully ||");
        System.out.println("___________________________________");
    }
    public static void getAllProduct(){
        List<Product> productList = service.displayAllProduct();
        for(Product p : productList){
            System.out.println(p.getProductId()+"\t\t\t\t\t"+p.getProductName()+
                    "\t\t\t\t\t"+p.getProductPrice()+"\t\t\t\t\t"+p.getProductType());
        }
        System.out.println("----------------------------------------------");
    }
    public static void deleteByID(){
        System.out.println("Enter the id :");
        int productID =sc.nextInt();
        int n = service.deleteProductById(productID);
        System.out.println(n+ " Product Deleted Successfully !!");
        System.out.println("_____________________________________");
    }
    public static void searchByID(){
        System.out.println("Enter the Product id :");
        int productId = sc.nextInt();
        Product p = service.searchProductById(productId);
        System.out.println(p.getProductId()+"\t\t\t\t\t"+p.getProductPrice()+"\t\t\t\t\t"+p.getProductName()+
                "\t\t\t\t\t"+p.getProductType());
    }

    public static void updateProduct(){
        System.out.println("Enter the product name for update :");
        String productName = sc.next();
        System.out.println("Enter the product price for update :");
        double productPrice = sc.nextDouble();
        System.out.println("Enter the product type for update :");
        String productType = sc.next();
        System.out.println("Enter the product id ");
        int productID = sc.nextInt();

        Product p =new Product(productID, productName, productPrice,productType);
        int n = service.updateProduct(p);
        System.out.println(n+ " data updated successfully !!");
    }

    public static void filterProductByPrice(){
        System.out.println("Enter the product price to filter :");
        double price = sc.nextDouble();
        List<Product> productList = service.filterProductByID(price);
        if (productList.isEmpty()){
            System.out.println("No Product found with price greater than "+price);
        }
        else {
            System.out.println("All product which is greater than "+ price + " : " );
            for (Product product : productList){
                System.out.println(product.getProductId()+"\t\t\t\t\t"+product.getProductPrice()+
                        "\t\t\t\t\t"+product.getProductName()+"\t\t\t\t\t"+product.getProductType());
            }
        }
    }
    public static void filterProductByPirceLesser(){
        System.out.println("Enter the price to filter according to less amount :");
        double price = sc.nextDouble();
        List<Product> productList  = service.filterProductByPriceLess(price);
        if (productList.isEmpty()){
            System.out.println("No Product found with the price is less than enter price : "+price);

        }
        else{
            System.out.println("ALL Product which is cheaper than : " +price);
            for(Product product : productList){
                System.out.println(product.getProductId()+"\t\t\t\t\t"+product.getProductPrice()+"\t\t\t\t\t"
                        +product.getProductName()+"\t\t\t\t\t"+product.getProductType());
            }
        }
    }
    public static void filterProductCategoryByType(){
        System.out.println("Enter the product type for filter : ");
        String productType = sc.next();
        List<Product> productList = service.selectSpecificCategoryOfProduct(productType);
        if(productList.isEmpty()){
            System.out.println("No Product is present for search according to type : "+ productType);
        }
        else{
            System.out.println("ALL product which is according to the category "+productType);
            for(Product product : productList){
                System.out.println(product.getProductId()+"\t\t\t\t\t"+product.getProductName()+
                        "\t\t\t\t"+product.getProductType()+"\t\t\t\t"+product.getProductPrice());
            }
        }
    }

    public static void filterProductInAscendingOrder(){
        System.out.println("Enter the Product type to show Price in ascending order :");
        String productType = sc.next();
        List<Product> productList = service.showProductAccordingToPrice(productType);
        if (productList.isEmpty()){
            System.out.println("All product belong to same price "+productType);
        }
        else{
            System.out.println("Product that are according to pricc : "+productType);
            for (Product product : productList){
                System.out.println(product.getProductId()+"\t\t\t\t\t"+product.getProductName()+"\t\t\t\t\t"+
                        product.getProductType()+"\t\t\t\t\t"+product.getProductPrice());
            }
        }
    }

    public static  void filterProductAccordingDecendingOrder(){
        System.out.println("Enter the product for show price in Decsending order :");
        String productType = sc.next();
        List<Product> productList = service.showProductAccordingToDec(productType);
        if(productList.isEmpty()){
            System.out.println("All product belong to same pricc or product mismatch "+productType);
        }
        else {
            System.out.println("Product that are according to price +"+productType);
            for(Product product : productList){
                System.out.println(product.getProductId()+"\t\t\t\t"+product.getProductName()+
                        "\t\t\t\t"+product.getProductType()+"\t\t\t\t"+product.getProductPrice());
            }
        }
    }

}

