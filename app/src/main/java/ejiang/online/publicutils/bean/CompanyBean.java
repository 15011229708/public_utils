package ejiang.online.publicutils.bean;

import java.io.Serializable;

public class CompanyBean implements Serializable {
    private Integer companyId;
    private String companyName;
    private String companyLogo;
    private String companyAddress;
    private String companyBoss;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(String companyLogo) {
        this.companyLogo = companyLogo;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getCompanyBoss() {
        return companyBoss;
    }

    public void setCompanyBoss(String companyBoss) {
        this.companyBoss = companyBoss;
    }

}
