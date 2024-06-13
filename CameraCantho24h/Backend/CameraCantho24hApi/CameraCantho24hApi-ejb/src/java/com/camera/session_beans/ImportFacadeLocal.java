/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.camera.session_beans;

import com.camera.entities.Import;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Tran Huu Phuc
 */
@Local
public interface ImportFacadeLocal {

    void create(Import imports);

    void edit(Import imports);

    void remove(Import imports);

    Import find(Object id);

    List<Import> findAll();

    List<Import> findRange(int[] range);

    int count();
    
}
