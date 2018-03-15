package com.kevin.snake.bootlicense.pojo;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * @ClassName: LicenseDetail
 * @Description: 序列号信息
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:47:32
 */
@Component
public class LicenseDetail {
    private Integer serialNumberId;

    @NotBlank(message = "sourceNumber不能为空")
    private String sourceNumber;

    private Date createDate;

    @NotNull
    private Date expiredDate;

    @NotBlank(message = "encryptedNumber不能为空")
    private String encryptedNumber;

    @NotNull(message = "hospitalNumber不能为空")
    private Integer hospitalNumber;

    private Integer licenseState;

    /**
     * 给该序列号加密的RSAKey的ID
     **/
    @NotNull(message = "keyid不为空")
    private Integer keyId;
    /**
     * Hospital对象，用于连接查询
     */
    private Hospital hospital;
    /**
     * RSAKey对象，用于连接查询
     */
    private RSAKey rsaKey;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public RSAKey getRsaKey() {
        return rsaKey;
    }

    public void setRsaKey(RSAKey rsaKey) {
        this.rsaKey = rsaKey;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public Integer getLicenseState() {
        return licenseState;
    }

    public void setLicenseState(Integer licenseState) {
        this.licenseState = licenseState;
    }

    public Integer getSerialNumberId() {
        return serialNumberId;
    }

    public void setSerialNumberId(Integer serialNumberId) {
        this.serialNumberId = serialNumberId;
    }

    public String getSourceNumber() {
        return sourceNumber;
    }

    public void setSourceNumber(String sourceNumber) {
        this.sourceNumber = sourceNumber == null ? null : sourceNumber.trim();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getEncryptedNumber() {
        return encryptedNumber;
    }

    public void setEncryptedNumber(String encryptedNumber) {
        this.encryptedNumber = encryptedNumber == null ? null : encryptedNumber.trim();
    }


    public Integer getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(Integer hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    @Override
    public String toString() {
        return "LicenseDetail{" +
                "serialNumberId=" + serialNumberId +
                ", sourceNumber='" + sourceNumber + '\'' +
                ", createDate=" + createDate +
                ", expiredDate=" + expiredDate +
                ", encryptedNumber='" + encryptedNumber + '\'' +
                ", hospitalNumber=" + hospitalNumber +
                ", licenseState=" + licenseState +
                ", keyId=" + keyId +
                ", hospital=" + hospital +
                ", rsaKey=" + rsaKey +
                '}';
    }
}