
package com.camera.controllers.api;

import com.camera.controllers.web.AdminPagesController;
import com.camera.dto.category.AllCategoryResDTO;
import com.camera.dto.products.AllProductsResDTO;
import com.camera.entities.Categories;
import com.camera.entities.Products;
import com.camera.payload.request.products.CreateProductsReq;
import com.camera.payload.request.products.UpdateProductsReq;
import com.camera.payload.responses.DataResponse;
import com.camera.payload.responses.StandardResponse;
import com.camera.providers.UrlProvider;
import com.camera.session_beans.CategoriesFacadeLocal;
import com.camera.session_beans.ProductsFacadeLocal;
import com.camera.supports.FileSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("" + UrlProvider.API_PREFIX + UrlProvider.Products.PREFIX)
public class ManageProductsController {

    ProductsFacadeLocal productsFacade1 = lookupProductsFacadeLocal();

    CategoriesFacadeLocal categoriesFacade1 = lookupCategoriesFacadeLocal();

    ProductsFacadeLocal productsFacade = lookupProductsFacadeLocal();
    CategoriesFacadeLocal categoriesFacade = lookupCategoriesFacadeLocal();
      
    // API trả về toàn bộ Products trong db
    //01 đỗ dữ liệu từ sql lên table user manager trên giao diện  admin
    @GetMapping("" + UrlProvider.Products.ALL)
    public ResponseEntity<DataResponse<List<AllProductsResDTO>>> allProducts(HttpServletRequest request) {
        // Get all users from database by ejb
        List<Products> allProducts = productsFacade.findAll();
        
        // List Entities -> List DTO
        List<AllProductsResDTO> data = new ArrayList<>();
        for (Products products : allProducts) {
            data.add(
                AllProductsResDTO.builder()
                        .productID(products.getProductID())
                        .category(
                                    AllCategoryResDTO.builder()
                                            .cateID(products.getCateID().getCateID())
                                            .cateName(products.getCateID().getCateName())
                                            .description(products.getCateID().getDescription())
                                            .thumbNail(products.getCateID().getThumbNail())
                                            .slug(products.getCateID().getSlug())
                                .build()
                        )
                        .productName(products.getProductName())
                        .description(products.getDescription())
                        .unitPrice(products.getUnitPrice())
                        .avatar(products.getAvatar())
                        .quantity(products.getQuantity())
                        .discount(products.getDiscount())
                        .warranty(products.getWarranty())
                .build()
            );
        }
        
        // Response Object
        DataResponse<List<AllProductsResDTO>> res = new DataResponse<>();
        
        res.setSuccess(true);
        res.setCode(200);
        res.setMessage("Successfully get all Products");
        res.setData(data);
        
        return ResponseEntity.ok(res);
    }

    
    
    
    
    
    //02 store Products
    @PostMapping("" + UrlProvider.Products.STORE)
    public ResponseEntity<StandardResponse> store(
            @RequestPart(value = "image", required = true) MultipartFile image, // Upload image
            @RequestPart(value = "data", required = true) @Valid CreateProductsReq req,
            BindingResult br,
            HttpSession session
    ) throws MethodArgumentNotValidException, IOException {
        //validation
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        if (image == null || image.isEmpty()) {
            br.rejectValue("avatar", "error.avatar", "Image is required");
        }
       
        //xử lý categoryID -> load vào
        Categories categories =  categoriesFacade.find(req.getCateID());
        if(categories  == null){
           br.rejectValue("cateID", "error.cateID", "The product category ID does not exist.");
        }
        
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        
        //Add
        Products nProducts = new Products();
        nProducts.setProductName(req.getProductName());
        nProducts.setDescription(req.getDescription());
        nProducts.setUnitPrice(req.getUnitPrice());
        try {
            /**
             * Handle image
             */
            byte[] imageBytes = image.getBytes();
            String originFileName = image.getOriginalFilename();
            String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "products", imageBytes, originFileName);
            nProducts.setAvatar(newImageFileName);
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        nProducts.setQuantity(req.getQuantity());
        nProducts.setDiscount(req.getDiscount());
        nProducts.setWarranty(req.getWarranty());
        nProducts.setCateID(categories);
        
//        System.out.println("Products: " + nProducts.toString());   
        productsFacade.create(nProducts);
        
        StandardResponse res = StandardResponse.builder()
             .success(true)
             .code(200)
             .message("Successfully create new Products")
             .build();
        
        return ResponseEntity.ok(res);
    }
    
    
    
    //03 - chuc nang update
//    @PostMapping(""+UrlProvider.Products.UPDATE)
//    public ResponseEntity<StandardResponse> updateProduct(
//            @RequestPart(value = "image", required = false) MultipartFile image, 
//            @RequestPart(value = "data", required = false) @Valid UpdateProductsReq req, 
//            HttpSession session, 
//            BindingResult br
//    ) 
//            throws MethodArgumentNotValidException 
//    {
//        
//        Categories categories =  categoriesFacade.find(req.getCateID());
//
//        if (br.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, br);
//        }
//
//        if (categories == null) {
//            br.rejectValue("cateID", "error.cateID", "The product category ID does not exist.");
//            throw new MethodArgumentNotValidException(null, br);
//        }
//
//        Products nProducts = productsFacade1.find(req.getProductID());
//
//        if (nProducts == null) {
//            br.rejectValue("productID", "error.productID", "The product ID does not exist.");
//            throw new MethodArgumentNotValidException(null, br);
//        }
//
//        if (br.hasErrors()) {
//            throw new MethodArgumentNotValidException(null, br);
//        }
//        
//        nProducts.setProductName(req.getProductName());
//        nProducts.setDescription(req.getDescription());
//        nProducts.setUnitPrice(req.getUnitPrice());
//        try {
//            /**
//             * Handle image
//             */
//            byte[] imageBytes = image.getBytes();
//            String originFileName = image.getOriginalFilename();
//            String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "products", imageBytes, originFileName);
//            nProducts.setAvatar(newImageFileName);
//        } catch (IOException ex) {
//            Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        nProducts.setQuantity(req.getQuantity());
//        nProducts.setDiscount(req.getDiscount());
//        nProducts.setWarranty(req.getWarranty());
//        nProducts.setCateID(req.getCateID());
//         
//        productsFacade.create(nProducts);
//        
//        StandardResponse res = StandardResponse.builder()
//             .success(true)
//             .code(200)
//             .message("Successfully update new Products")
//             .build();
//        
//
//        productsFacade.edit(nProducts);
//
//        return ResponseEntity.ok(StandardResponse.builder().success(true).code(200).message("Successfully edit product").build());
//
//    }
//    
    
    
    
    
   // 04 - delete
    @DeleteMapping("" + UrlProvider.Products.DELETE)
    public ResponseEntity<?> deleteProduct(@RequestParam("ids") String ids, HttpSession session) {
        String[] idsPC = ids.split(",");

        for (String id : idsPC) {
            int idInt;
            try {
                idInt = Integer.parseInt(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                continue;
            }
            Products product = productsFacade.find(idInt);
            if (product != null) {
                try {
                    FileSupport.deleteFile(session.getServletContext().getRealPath("/"), "products", product.getAvatar());
                } catch (IOException ex) {
                    Logger.getLogger(AdminPagesController.class.getName()).log(Level.SEVERE, null, ex);
                }
                productsFacade.remove(product);
            }
        }
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .success(true)
                        .code(200)
                        .message("Successfully delete product")
                        .build()
        );
    }
    
    
    
    
    
    
    
    
    
    
   //insert localfacade cua session beans : (phim tat: lt + insert > call enterprise bean)
   
    private CategoriesFacadeLocal lookupCategoriesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CategoriesFacadeLocal) c.lookup("java:global/CameraCantho24hApi/CameraCantho24hApi-ejb/CategoriesFacade!com.camera.session_beans.CategoriesFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

    private ProductsFacadeLocal lookupProductsFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (ProductsFacadeLocal) c.lookup("java:global/CameraCantho24hApi/CameraCantho24hApi-ejb/ProductsFacade!com.camera.session_beans.ProductsFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }

 
}// end class Products Api Cn
