
package com.camera.controllers.api;

import com.camera.entities.Categories;
import com.camera.dto.category.AllCategoryResDTO;
import com.camera.payload.request.categories.CreateCategoryReq;
import com.camera.payload.request.categories.UpdateCategoryReq;
import com.camera.payload.responses.DataResponse;
import com.camera.payload.responses.StandardResponse;
import com.camera.providers.UrlProvider;
import com.camera.session_beans.CategoriesFacadeLocal;
import com.camera.supports.FileSupport;
import com.camera.supports.SlugSupport;
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
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



//viết api (khởi tạo api bên java spring(giống như khởi tạo một api bên nodejs))
@RestController
@RequestMapping("" + UrlProvider.API_PREFIX + UrlProvider.Category.PREFIX)
public class ManageCategoryApiController {
    @Autowired
    private SlugSupport slugSupport;

    CategoriesFacadeLocal categoriesFacade = lookupCategoriesFacadeLocal();
    
    // 01 API trả về toàn bộ supplier trong db -> read -> (đỗ dữ liệu lên table trên giao diện)
    @GetMapping("" + UrlProvider.Category.ALL)
    public ResponseEntity<DataResponse<List<AllCategoryResDTO>>> allCategory(HttpServletRequest request){
        //tạo list
        List<Categories> allCategories = categoriesFacade.findAll();
        
        //list enities -> list dto
        List<AllCategoryResDTO> data = new ArrayList<>();
        for(Categories cate : allCategories){
              data.add(
                 AllCategoryResDTO.builder()
                     .cateID(cate.getCateID())
                     .cateName(cate.getCateName())
                     .description(cate.getDescription())
                     .thumbNail(FileSupport.perfectImg(request, "categories", cate.getThumbNail()))
                     .slug(cate.getSlug())
                   .build()
              );
        }
        
        //response Object
        DataResponse<List<AllCategoryResDTO>> res = new DataResponse<>();
        
         res.setSuccess(true);
         res.setCode(200);
         res.setMessage("Successfully get all Suppliers");
         res.setData(data);
         
         return ResponseEntity.ok(res);
    }



    //02 xay method (create) store Categories
    @PostMapping("" + UrlProvider.Category.STORE)
    public ResponseEntity<StandardResponse> store(
            @RequestPart(value = "image", required = true) MultipartFile image, // Upload image
            @RequestPart(value = "data", required = true) @Valid CreateCategoryReq req,
            BindingResult br,
            HttpSession session
    ) throws MethodArgumentNotValidException{
        //validation 
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        if (image == null || image.isEmpty()) {
            br.rejectValue("image", "error.image", "Image is required");
        }
        if (categoriesFacade.findBySlug(slugSupport.generateSlug(req.getCateName())) != null) {
            br.rejectValue("cateName", "error.cateName", "Slug is existing");
        }
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        
        //add data for table admin categories
        Categories cateq = new Categories();
        cateq.setCateName(req.getCateName());
        cateq.setDescription(req.getDescription());
        try {
           /**
            * Handle image
            */
           byte[] imageBytes = image.getBytes();
           String originFileName = image.getOriginalFilename();
           String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "categories", imageBytes, originFileName);
           cateq.setThumbNail(newImageFileName);
        } catch (IOException ex) {
            Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        cateq.setSlug(slugSupport.generateSlug(req.getCateName())); // Từ name -> slug, vd: (name)Camera Trong Nha -> (slug)camera-trong-nha
        
        categoriesFacade.create(cateq);
        
        StandardResponse res = StandardResponse.builder()
                .success(true)
                .code(200)
                .message("Successfully create new category")
                .build();
        
        return ResponseEntity.ok(res);
    }
   

    
    
    
    
    
    //03 method update
    @PostMapping("" + UrlProvider.Category.UPDATE)
    public ResponseEntity<StandardResponse> update(
            @RequestPart(value = "image", required = false) MultipartFile image, // Upload image
            @RequestPart(value = "data", required = true) @Valid UpdateCategoryReq req,
            BindingResult br,
            HttpSession session
    ) throws MethodArgumentNotValidException{
       //validation
        if(br.hasErrors()) throw new MethodArgumentNotValidException(null, br);
          
        // Check user not existing?
            Categories cateUpdate = categoriesFacade.find(req.getCateID());
        if (cateUpdate == null) {
            br.rejectValue("cateID", "error.cateID", "CateID is not found");
        }
        if (categoriesFacade.findBySlug(slugSupport.generateSlug(req.getCateName())) != null) {
            br.rejectValue("cateName", "error.cateName", "Slug is existing");
        }
        if(br.hasErrors()){
            throw new MethodArgumentNotValidException(null, br);
        }
        
        //add data for table admin categories
        if(image != null && image.isEmpty()){
            try {
                /**
                 * Handle image
                 */
                byte[] imageBytes = image.getBytes();
                String originFileName = image.getOriginalFilename();
                String newImageFileName = FileSupport.saveFile(session.getServletContext().getRealPath("/"), "categories", imageBytes, originFileName);
                cateUpdate.setThumbNail(newImageFileName);
            } catch (IOException ex) {
                Logger.getLogger(ManageUsersApiController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        cateUpdate.setCateName(req.getCateName());
        cateUpdate.setDescription(req.getDescription());
        cateUpdate.setSlug(slugSupport.generateSlug(req.getCateName())); // Từ name -> slug, vd: (name)Camera Trong Nha -> (slug)camera-trong-nha
        
        categoriesFacade.edit(cateUpdate);
        
        StandardResponse res = StandardResponse.builder()
                .success(true)
                .code(200)
                .message("Successfully update category")
                .build();
        
        return ResponseEntity.ok(res);
        
    }    
    

    
    
   //04 method delete
    @DeleteMapping(""+UrlProvider.Category.DELETE)
    public ResponseEntity<StandardResponse> delete(@RequestParam("ids") String ids){
        String[] idArr = ids.split(",");
        
        for(String id : idArr){
            try {
                Categories cateq = categoriesFacade.find(Integer.parseInt(id));
                if(cateq != null){
                    categoriesFacade.remove(cateq);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .success(true)
                        .code(200)
                        .message("Successfully delete Categories")
                        .build()
        );
    }
    
    
    
    
    
   //load facadelocal cua session beans
   private CategoriesFacadeLocal lookupCategoriesFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (CategoriesFacadeLocal) c.lookup("java:global/CameraCantho24hApi/CameraCantho24hApi-ejb/CategoriesFacade!com.camera.session_beans.CategoriesFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
    
    
}//end class ManageCategoryApiController


