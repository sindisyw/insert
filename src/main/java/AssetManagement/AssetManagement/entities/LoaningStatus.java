/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author HP
 */
@Entity
@Table(name = "loaning_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LoaningStatus.findAll", query = "SELECT l FROM LoaningStatus l")
    , @NamedQuery(name = "LoaningStatus.findById", query = "SELECT l FROM LoaningStatus l WHERE l.id = :id")
    , @NamedQuery(name = "LoaningStatus.findByStatusDate", query = "SELECT l FROM LoaningStatus l WHERE l.statusDate = :statusDate")})
public class LoaningStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "status_date")
    @Temporal(TemporalType.DATE)
    private Date statusDate;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 65535)
    @Column(name = "status_note")
    private String statusNote;
    @JoinColumn(name = "loaning_request", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private LoaningRequest loaningRequest;
    @JoinColumn(name = "status", referencedColumnName = "id")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    public LoaningStatus() {
    }

    public LoaningStatus(String id) {
        this.id = id;
    }

    public LoaningStatus(String id, Date statusDate, String statusNote) {
        this.id = id;
        this.statusDate = statusDate;
        this.statusNote = statusNote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getStatusNote() {
        return statusNote;
    }

    public void setStatusNote(String statusNote) {
        this.statusNote = statusNote;
    }

    public LoaningRequest getLoaningRequest() {
        return loaningRequest;
    }

    public void setLoaningRequest(LoaningRequest loaningRequest) {
        this.loaningRequest = loaningRequest;
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
        if (!(object instanceof LoaningStatus)) {
            return false;
        }
        LoaningStatus other = (LoaningStatus) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.LoaningStatus[ id=" + id + " ]";
    }
    
}
