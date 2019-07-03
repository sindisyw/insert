/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "detail_asset")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DetailAsset.findAll", query = "SELECT d FROM DetailAsset d")
    , @NamedQuery(name = "DetailAsset.findById", query = "SELECT d FROM DetailAsset d WHERE d.id = :id")
    , @NamedQuery(name = "DetailAsset.findByBrand", query = "SELECT d FROM DetailAsset d WHERE d.brand = :brand")
    , @NamedQuery(name = "DetailAsset.findBySeri", query = "SELECT d FROM DetailAsset d WHERE d.seri = :seri")})
public class DetailAsset implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "spesification")
    private String spesification;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "brand")
    private String brand;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "SERI")
    private String seri;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAsset", fetch = FetchType.LAZY)
    private List<RepairRequest> repairRequestList;
    @JoinColumn(name = "asset", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Asset asset;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "detailAsset", fetch = FetchType.LAZY)
    private List<LoaningRequest> loaningRequestList;

    public DetailAsset() {
    }

    public DetailAsset(String id) {
        this.id = id;
    }

    public DetailAsset(String id, String spesification, String brand, String seri) {
        this.id = id;
        this.spesification = spesification;
        this.brand = brand;
        this.seri = seri;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpesification() {
        return spesification;
    }

    public void setSpesification(String spesification) {
        this.spesification = spesification;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getSeri() {
        return seri;
    }

    public void setSeri(String seri) {
        this.seri = seri;
    }

    @XmlTransient
    public List<RepairRequest> getRepairRequestList() {
        return repairRequestList;
    }

    public void setRepairRequestList(List<RepairRequest> repairRequestList) {
        this.repairRequestList = repairRequestList;
    }

    public Asset getAsset() {
        return asset;
    }

    public void setAsset(Asset asset) {
        this.asset = asset;
    }

    @XmlTransient
    public List<LoaningRequest> getLoaningRequestList() {
        return loaningRequestList;
    }

    public void setLoaningRequestList(List<LoaningRequest> loaningRequestList) {
        this.loaningRequestList = loaningRequestList;
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
        if (!(object instanceof DetailAsset)) {
            return false;
        }
        DetailAsset other = (DetailAsset) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.DetailAsset[ id=" + id + " ]";
    }
    
}
