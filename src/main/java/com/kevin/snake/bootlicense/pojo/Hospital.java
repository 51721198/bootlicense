package com.kevin.snake.bootlicense.pojo;

import org.springframework.stereotype.Component;

/**
 * @ClassName: Hospital
 * @Description: 医院信息
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:46:57
 */
@Component
public class Hospital {

    private Integer hospitalNumber;

    private String hospitalName;

    private String hospitalPhone;

    private String hospitalAddress;

    public Integer getHospitalNumber() {
        return hospitalNumber;
    }

    public void setHospitalNumber(Integer hospitalNumber) {
        this.hospitalNumber = hospitalNumber;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName == null ? null : hospitalName.trim();
    }

    public String getHospitalPhone() {
        return hospitalPhone;
    }

    public void setHospitalPhone(String hospitalPhone) {
        this.hospitalPhone = hospitalPhone == null ? null : hospitalPhone.trim();
    }

    public String getHospitalAddress() {
        return hospitalAddress;
    }

    public void setHospitalAddress(String hospitalAddress) {
        this.hospitalAddress = hospitalAddress == null ? null : hospitalAddress.trim();
    }

    @Override
    public String toString() {
        return "Hospital [hospitalNumber=" + hospitalNumber + ", hospitalName=" + hospitalName + ", hospitalPhone="
                + hospitalPhone + ", hospitalAddress=" + hospitalAddress + "]";
    }

}