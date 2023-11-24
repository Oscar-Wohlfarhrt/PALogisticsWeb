/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.EnderFire.PALogisticsWeb.Models;

import com.EnderFire.PALogisticsWeb.Utils.GenericEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
//falta la relacion con mercancia

/**
 *
 * @author Oscar2
 */
@Entity
public class Pedido implements Serializable, GenericEntity {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Nombre")
    private String name;

    private EstadosPedido state = EstadosPedido.NoDespachado;
    private String deliveryAddress = "Unknown";

    @ManyToOne(targetEntity = Cliente.class)
    private Cliente client;

    @ManyToOne
    @JoinColumn
    private Ubicacion location;

    @ManyToMany
    @JoinTable(joinColumns = @JoinColumn(name = "pedido_id"), inverseJoinColumns = @JoinColumn(name = "mercancia_id"))
    private List<Mercancia> mercancias = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public Ubicacion getLocation() {
        return location;
    }

    public EstadosPedido getState() {
        return state;
    }
    
    public Cliente getClient() {
        return client;
    }

    public void setClient(Cliente client) {
        this.client = client;
    }
    
    public String toDetailedString(){
        return String.format("<html>%s<br>Ubicacion: %s</html>",toString(),location!=null?location.toDetailedString():"No tiene ubicacion asignada");
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
        if (!(object instanceof Pedido)) {
            return false;
        }
        Pedido other = (Pedido) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return String.format("[%d] %s", id, name);
    }

}
