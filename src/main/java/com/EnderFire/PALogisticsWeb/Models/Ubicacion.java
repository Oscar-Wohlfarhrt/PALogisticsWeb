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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Oscar2
 */
@Entity
public class Ubicacion implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private Float latitude; //revisar el tipo de dato. Por la naturaleza de la coordenada hay que especificar si es norte o sur y expresarse en grados sexagesimales con sus minutos y segundos respectivos
    private Float longitude;//revisar el tipo de dato
   
    @OneToMany(mappedBy = "location")
    private List<Pedido> pedidos =  new ArrayList<>();
    
    

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
        if (!(object instanceof Ubicacion)) {
            return false;
        }
        Ubicacion other = (Ubicacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }
    
    public String toDetailedString(){
        return String.format("[%d] %s Lat: %.7f, Long: %.7f", id, name,latitude,longitude);
    }
}
