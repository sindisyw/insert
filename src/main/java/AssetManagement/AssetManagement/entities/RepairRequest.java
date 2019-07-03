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
@Table(name = "repair_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RepairRequest.findAll", query = "SELECT r FROM RepairRequest r")
    , @NamedQuery(name = "RepairRequest.findById", query = "SELECT r FROM RepairRequest r WHERE r.id = :id")
    , @NamedQuery(name = "RepairRequest.findByNote", query = "SELECT r FROM RepairRequest r WHERE r.note = :note")
    , @NamedQuery(name = "RepairRequest.findByQuantity", query = "SELECT r FROM RepairRequest r WHERE r.quantity = :quantity")})
public class RepairRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "repairRequest", fetch = FetchType.LAZY)
    private List<RepairStatus> repairStatusList;
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @JoinColumn(name = "detail_asset", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetailAsset detailAsset;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    public RepairRequest() {
    }

    public RepairRequest(String id) {
        this.id = id;
    }

    public RepairRequest(String id, String note, int quantity) {
        this.id = id;
        this.note = note;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @XmlTransient
    public List<RepairStatus> getRepairStatusList() {
        return repairStatusList;
    }

    public void setRepairStatusList(List<RepairStatus> repairStatusList) {
        this.repairStatusList = repairStatusList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public DetailAsset getDetailAsset() {
        return detailAsset;
    }

    public void setDetailAsset(DetailAsset detailAsset) {
        this.detailAsset = detailAsset;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
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
        if (!(object instanceof RepairRequest)) {
            return false;
        }
        RepairRequest other = (RepairRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.RepairRequest[ id=" + id + " ]";
    }
    
}
