package entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.*;


/**
 * The persistent class for the tbAccount database table.
 * 
 */
@Entity
@Table(name="tbAccount")
@NamedQuery(name="TbAccount.findAll", query="SELECT t FROM TbAccount t")
public class TbAccount implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="MaTK")
    private Long maTK;

    @Column(name="Email")
    private String email;

    @Column(name="HoTen")
    private String hoTen;

    @Column(name="NamSinh")
    private Integer namSinh;

    @Column(name="NgaySinh")
    private Integer ngaySinh;

    @Column(name="Phone")
    private String phone;
    
    @Column(name="PWord")
    private String PWord;

    @Column(name="Sao")
    private Float sao;

    @Column(name="ThangSinh")
    private Integer thangSinh;

    @Column(name="Tien")
    private Double tien;
    
    @Column(name="UName")
    private String UName;
    
    @Column(name="DateOfBirth")
    private Date dateOfBirth;
    
    @OneToMany(mappedBy = "account", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<TbPhienDauGia> phienDauGias;

    public List<TbPhienDauGia> getPhienDauGias() {
        return phienDauGias;
    }


    public void setPhienDauGias(List<TbPhienDauGia> phienDauGias) {
        this.phienDauGias = phienDauGias;
    }


    public TbAccount() {
        maTK = null;
        email = null;
        hoTen = null;
        namSinh = null;
        ngaySinh = null;
        phone = null;
        PWord = null;
        sao = null;
        thangSinh = null;
        tien = null;
        UName = null;
        phienDauGias= null;
    }


    public TbAccount(String uName,String pWord) {
        PWord = pWord;
        UName = uName;
    }




    public Long getMaTK() {
        return this.maTK;
    }

    public void setMaTK(Long maTK) {
        this.maTK = maTK;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHoTen() {
        return this.hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return this.namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public int getNgaySinh() {
        return this.ngaySinh;
    }

    public void setNgaySinh(int ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPWord() {
        return this.PWord;
    }

    public void setPWord(String PWord) {
        this.PWord = PWord;
    }

    public float getSao() {
        return this.sao;
    }

    public void setSao(float sao) {
        this.sao = sao;
    }

    public int getThangSinh() {
        return this.thangSinh;
    }

    public void setThangSinh(int thangSinh) {
        this.thangSinh = thangSinh;
    }

    public double getTien() {
        return this.tien;
    }

    public void setTien(double tien) {
        this.tien = tien;
    }

    public String getUName() {
        return this.UName;
    }

    public void setUName(String UName) {
        this.UName = UName;
    }
    
    public Date getDateOfBirth() {
        return this.dateOfBirth;
    }
    
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    
    public void add(TbPhienDauGia tempPhienDG)
    {
        if (phienDauGias == null)
            phienDauGias = new ArrayList<>();
        phienDauGias.add(tempPhienDG);
        tempPhienDG.setAccount(this);
    }


    @Override
    public String toString() {
        return "TbAccount [maTK=" + maTK + ", email=" + email + ", hoTen=" + hoTen + ", namSinh=" + namSinh
                + ", ngaySinh=" + ngaySinh + ", phone=" + phone + ", PWord=" + PWord + ", sao=" + sao + ", thangSinh="
                + thangSinh + ", tien=" + tien + ", UName=" + UName + "]";
    }

    

}