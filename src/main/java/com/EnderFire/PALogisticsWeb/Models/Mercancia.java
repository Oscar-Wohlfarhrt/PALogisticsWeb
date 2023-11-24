/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EnderFire.PALogisticsWeb.Models;

import com.EnderFire.PALogisticsWeb.Utils.GenericEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author Oscar2
 */
@Entity
public class Mercancia implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private Float value;
    
    @ManyToMany(targetEntity = Proveedor.class)
    private List<Proveedor> supplier = new ArrayList<>();
    
    /*@ManyToOne(fetch = FetchType.LAZY,targetEntity = OrdenCompra.class) //esta relacion deberia ser n a n
    private List<OrdenCompra> order = new ArrayList<>();*/
    
    @ManyToMany(mappedBy = "mercancias")
    private List<Pedido> pedidos = new ArrayList<>();
    
    @ManyToMany(mappedBy = "mercancias")
    private List<Almacen> almacenes = new ArrayList<>();
            
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mercancia)) {
            return false;
        }
        Mercancia other = (Mercancia) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s (p/u: $%.2f)", id, description,value);
    }
    
}
