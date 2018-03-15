package com.kevin.snake.bootlicense.dao;

import com.kevin.snake.bootlicense.pojo.LicenseDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName: LicenseDao
 * @Description: 序列号信息Dao层
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:46:25
 */
@Component
public interface LicenseDao {
    int deleteByPrimaryKey(Integer serialNumberId);

    int insertLicenseDetail(LicenseDetail licensedetail);

    LicenseDetail selectByPrimaryKey(Integer serialNumberId);

    int updateByPrimaryKey(LicenseDetail licensedetail);

    List<LicenseDetail> selectAllLicenses();

    List<LicenseDetail> selectLicenseByHospitalNumber(int hospitalNumber);

    LicenseDetail selectCodeAndRSAByPrimaryKey(Integer serialNumberId);

    List<LicenseDetail> selectAllLicensesByPage(@Param("start") Integer start,
                                                @Param("length") Integer length);

    Integer selectCountLicenses();
}
