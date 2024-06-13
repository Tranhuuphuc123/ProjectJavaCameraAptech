/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.camera.session_beans;

import com.camera.entities.ImportDetails;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tran Huu Phuc
 */
@Local
public interface ImportDetailsFacadeLocal {

    void create(ImportDetails importDetails);

    void edit(ImportDetails importDetails);

    void remove(ImportDetails importDetails);

    ImportDetails find(Object id);

    List<ImportDetails> findAll();

    List<ImportDetails> findRange(int[] range);

    int count();
    
}
