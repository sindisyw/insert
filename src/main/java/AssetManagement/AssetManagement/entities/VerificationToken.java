/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AssetManagement.AssetManagement.entities;

import java.io.Serializable;
import java.security.Timestamp;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ACER
 */
@Entity
@Table(name = "verification_tokens")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "VerificationToken.findAll", query = "SELECT v FROM VerificationToken v")
    , @NamedQuery(name = "VerificationToken.findById", query = "SELECT v FROM VerificationToken v WHERE v.id = :id")
    , @NamedQuery(name = "VerificationToken.findByToken", query = "SELECT v FROM VerificationToken v WHERE v.token = :token")
    , @NamedQuery(name = "VerificationToken.findByExpiryDate", query = "SELECT v FROM VerificationToken v WHERE v.expiryDate = :expiryDate")})
public class VerificationToken implements Serializable {

    private static final int EXPIRATION = 60 * 24;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 225)
    @Column(name = "token")
    private String token;
    @Basic(optional = false)
    @NotNull
    @Column(name = "expiry_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiryDate;
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Account accountId;

    public VerificationToken() {
    }

    public VerificationToken(Integer id) {
        this.id = id;
    }

    public VerificationToken(Integer id, String token, Date expiryDate) {
        this.id = id;
        this.token = token;
        this.expiryDate = expiryDate;
    }
    
    private Date calculateExpiryDate(int expiryTimeInMinutes) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new java.sql.Timestamp(cal.getTime().getTime()));
        cal.add(Calendar.MINUTE, expiryTimeInMinutes);
        
        return new Date(cal.getTime().getTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
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
        if (!(object instanceof VerificationToken)) {
            return false;
        }
        VerificationToken other = (VerificationToken) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AssetManagement.AssetManagement.entities.VerificationToken[ id=" + id + " ]";
    }
    
}
