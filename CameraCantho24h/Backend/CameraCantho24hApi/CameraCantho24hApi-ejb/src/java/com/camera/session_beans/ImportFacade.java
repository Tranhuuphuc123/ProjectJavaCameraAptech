/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camera.session_beans;

import com.camera.entities.Import;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Tran Huu Phuc
 */
@Stateless
public class ImportFacade extends AbstractFacade<Import> implements ImportFacadeLocal {

    @PersistenceContext(unitName = "CameraCantho24hApi-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ImportFacade() {
        super(Import.class);
    }
    
}
