
package com.camera.controllers.api;


import com.camera.dto.suppliers.AllSuppliersResDTO;
import com.camera.entities.Suppliers;
import com.camera.payload.requests.suppliers.CreateSupplierReq;
import com.camera.payload.requests.suppliers.UpdateSuppliersReq;
import com.camera.payload.responses.DataResponse;
import com.camera.payload.responses.StandardResponse;
import com.camera.providers.UrlProvider;
import com.camera.session_beans.SuppliersFacadeLocal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.validation.Valid;
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
import org.springframework.web.bind.annotation.RestController;


//viết api (khởi tạo api bên java spring(giống như khởi tạo một api bên nodejs))
@RestController
@RequestMapping("" + UrlProvider.API_PREFIX + UrlProvider.Suppliers.PREFIX)
public class ManageSuppliersApiControllers {

    SuppliersFacadeLocal suppliersFacade = lookupSuppliersFacadeLocal();
    
    
   
    // 01 API trả về toàn bộ supplier trong db -> read -> (đỗ dữ liệu lên table trên giao diện)
    @GetMapping("" + UrlProvider.Suppliers.ALL)
    public ResponseEntity<DataResponse<List<AllSuppliersResDTO>>> allSuppliers() {
        //
        List<Suppliers> allSuppliers = suppliersFacade.findAll();
        
        //list enities -> list dto
        List<AllSuppliersResDTO> data= new ArrayList<>();
        for(Suppliers sup : allSuppliers ){
            data.add(
               AllSuppliersResDTO.builder()
                    .supplierID(sup.getSupplierID())
                    .supplierName(sup.getSupplierName())
                    .phone(sup.getPhone())
                    .email(sup.getEmail())
                    .description(sup.getDescription())
                 .build()
            );
        }
       
        
        //response Object
        DataResponse<List<AllSuppliersResDTO>> res = new DataResponse<>();
        
        res.setSuccess(true);
        res.setCode(200);
        res.setMessage("Successfully get all Suppliers");
        res.setData(data);
        
        return ResponseEntity.ok(res);
    } 
    
    
    
   //02 xay method (create) store suppliers
    @PostMapping("" + UrlProvider.Suppliers.STORE)
    public ResponseEntity<StandardResponse> store(@Valid @RequestBody CreateSupplierReq req, BindingResult br) throws MethodArgumentNotValidException {
        // Validation
        if (br.hasErrors()) {
            throw new MethodArgumentNotValidException(null, br);
        }
        // Add
        Suppliers newSupplier = new Suppliers();
        newSupplier.setSupplierName(req.getSupplierName());
        newSupplier.setPhone(req.getPhone());
        newSupplier.setEmail(req.getEmail());
        newSupplier.setDescription(req.getDescription());
        
        suppliersFacade.create(newSupplier);
        
        StandardResponse res = StandardResponse.builder()
                .success(true)
                .code(200)
                .message("Successfully create new supplier")
                .build();
        
        return ResponseEntity.ok(res);
    }
    
    
    
  
    
    //03 method update
    @PutMapping("" + UrlProvider.Suppliers.UPDATE)
    public ResponseEntity<StandardResponse> update(@Valid @RequestBody UpdateSuppliersReq reqBody, BindingResult br)
                          throws MethodArgumentNotValidException 
    {
       if(br.hasErrors()) throw new MethodArgumentNotValidException(null, br);
          
        Suppliers suppliers = suppliersFacade.find(reqBody.getSupplierID());

        if(suppliers == null){
            // rejectValue: tu choi gia tri supplierID (add new error to binding result)
            // throw error -> advice xu ly 
            br.rejectValue("supplierID", "error.supplierID", "That supplierID is not exist ");
            throw new MethodArgumentNotValidException(null, br);
        }
        
        
        //lấy dữ liệu của dto chuyền qua entity rồi update trong db
        suppliers.setSupplierName(reqBody.getSupplierName());
        suppliers.setPhone(reqBody.getPhone());
        suppliers.setEmail(reqBody.getEmail());
        suppliers.setDescription(reqBody.getDescription());
        
        suppliersFacade.edit(suppliers);
        
        
         return ResponseEntity.ok(
                StandardResponse.builder()
                        .success(true)
                        .code(200)
                        .message("Successfully update supplier")
                        .build()
        );
        
    }      
    
    
    
    //04 method delete
    @DeleteMapping(""+UrlProvider.Suppliers.DELETE)
    public ResponseEntity<StandardResponse> delete(@RequestParam("ids") String ids){
        String[] idArr = ids.split(",");
        
        for(String id : idArr){
            try {
                Suppliers supplier = suppliersFacade.find(Integer.parseInt(id));
                if(supplier != null){
                    suppliersFacade.remove(supplier);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return ResponseEntity.ok(
                StandardResponse.builder()
                        .success(true)
                        .code(200)
                        .message("Successfully delete supplier")
                        .build()
        );
    }
    
    
    
    //load facadelocal cua session beans
    private SuppliersFacadeLocal lookupSuppliersFacadeLocal() {
        try {
            Context c = new InitialContext();
            return (SuppliersFacadeLocal) c.lookup("java:global/CameraCantho24hApi/CameraCantho24hApi-ejb/SuppliersFacade!com.camera.session_beans.SuppliersFacadeLocal");
        } catch (NamingException ne) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
            throw new RuntimeException(ne);
        }
    }
}
