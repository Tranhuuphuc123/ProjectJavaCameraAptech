/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.camera.session_beans;

import com.camera.entities.Categories;
import com.camera.entities.Users;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Tran Huu Phuc
 */
@Stateless
public class CategoriesFacade extends AbstractFacade<Categories> implements CategoriesFacadeLocal {

    @PersistenceContext(unitName = "CameraCantho24hApi-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CategoriesFacade() {
        super(Categories.class);
    }
    
    
    @Override
    public Categories findBySlug(String slug) {
        Query query = em.createNamedQuery("Categories.findBySlug", Categories.class);
        query.setParameter("slug", slug);
        try {
            return (Categories) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
    
}
