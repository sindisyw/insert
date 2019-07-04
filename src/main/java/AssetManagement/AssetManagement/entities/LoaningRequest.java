/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "loaning_request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaningRequest.findAll", query = "SELECT l FROM LoaningRequest l")
    , @NamedQuery(name = "LoaningRequest.findById", query = "SELECT l FROM LoaningRequest l WHERE l.id = :id")
    , @NamedQuery(name = "LoaningRequest.findByLoaningDate", query = "SELECT l FROM LoaningRequest l WHERE l.loaningDate = :loaningDate")
    , @NamedQuery(name = "LoaningRequest.findByReturnDate", query = "SELECT l FROM LoaningRequest l WHERE l.returnDate = :returnDate")
    , @NamedQuery(name = "LoaningRequest.findByLoaningTotal", query = "SELECT l FROM LoaningRequest l WHERE l.loaningTotal = :loaningTotal")
    , @NamedQuery(name = "LoaningRequest.findByNote", query = "SELECT l FROM LoaningRequest l WHERE l.note = :note")
    , @NamedQuery(name = "LoaningRequest.findByQuantity", query = "SELECT l FROM LoaningRequest l WHERE l.quantity = :quantity")})
public class LoaningRequest implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "loaning_date")
    @Temporal(TemporalType.DATE)
    private Date loaningDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "return_date")
    @Temporal(TemporalType.DATE)
    private Date returnDate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "loaning_total")
    private int loaningTotal;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "note")
    private String note;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private int quantity;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "loaningRequest", fetch = FetchType.LAZY)
    private List<LoaningStatus> loaningStatusList;
    @JoinColumn(name = "employee", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Employee employee;
    @JoinColumn(name = "detail_asset", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private DetailAsset detailAsset;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Status status;

    public LoaningRequest() {
    }

    public LoaningRequest(String id) {
        this.id = id;
    }

    public LoaningRequest(String id, Date loaningDate, Date returnDate, int loaningTotal, String note, int quantity) {
        this.id = id;
        this.loaningDate = loaningDate;
        this.returnDate = returnDate;
        this.loaningTotal = loaningTotal;
        this.note = note;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLoaningDate() {
        return loaningDate;
    }

    public void setLoaningDate(Date loaningDate) {
        this.loaningDate = loaningDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public int getLoaningTotal() {
        return loaningTotal;
    }

    public void setLoaningTotal(int loaningTotal) {
        this.loaningTotal = loaningTotal;
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
    public List<LoaningStatus> getLoaningStatusList() {
        return loaningStatusList;
    }

    public void setLoaningStatusList(List<LoaningStatus> loaningStatusList) {
        this.loaningStatusList = loaningStatusList;
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
        if (!(object instanceof LoaningRequest)) {
            return false;
        }
        LoaningRequest other = (LoaningRequest) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.LoaningRequest[ id=" + id + " ]";
    }
    
}
