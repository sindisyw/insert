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
@Table(name = "status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Status.findAll", query = "SELECT s FROM Status s")
    , @NamedQuery(name = "Status.findById", query = "SELECT s FROM Status s WHERE s.id = :id")
    , @NamedQuery(name = "Status.findByName", query = "SELECT s FROM Status s WHERE s.name = :name")
    , @NamedQuery(name = "Status.findByIsDelete", query = "SELECT s FROM Status s WHERE s.isDelete = :isDelete")})
public class Status implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "is_delete")
    private String isDelete;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status", fetch = FetchType.LAZY)
    private List<RepairStatus> repairStatusList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "status", fetch = FetchType.LAZY)
    private List<LoaningStatus> loaningStatusList;
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<RepairRequest> repairRequestList;
    @OneToMany(mappedBy = "status", fetch = FetchType.LAZY)
    private List<LoaningRequest> loaningRequestList;

    public Status() {
    }

    public Status(String id) {
        this.id = id;
    }

    public Status(String id, String name, String isDelete) {
        this.id = id;
        this.name = name;
        this.isDelete = isDelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(String isDelete) {
        this.isDelete = isDelete;
    }

    @XmlTransient
    public List<RepairStatus> getRepairStatusList() {
        return repairStatusList;
    }

    public void setRepairStatusList(List<RepairStatus> repairStatusList) {
        this.repairStatusList = repairStatusList;
    }

    @XmlTransient
    public List<LoaningStatus> getLoaningStatusList() {
        return loaningStatusList;
    }

    public void setLoaningStatusList(List<LoaningStatus> loaningStatusList) {
        this.loaningStatusList = loaningStatusList;
    }

    @XmlTransient
    public List<RepairRequest> getRepairRequestList() {
        return repairRequestList;
    }

    public void setRepairRequestList(List<RepairRequest> repairRequestList) {
        this.repairRequestList = repairRequestList;
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
        if (!(object instanceof Status)) {
            return false;
        }
        Status other = (Status) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.Status[ id=" + id + " ]";
    }
    
}
