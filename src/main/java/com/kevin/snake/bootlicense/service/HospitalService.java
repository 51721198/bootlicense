package com.kevin.snake.bootlicense.service;

import com.kevin.snake.bootlicense.pojo.DatatableModel;
import com.kevin.snake.bootlicense.pojo.Hospital;

import java.util.List;

public interface HospitalService {

    /**
     * @return
     * @param:
     * @return: List<Hospital>
     * @Title: showAllHospitals
     * @Description: 查
     */
    List<Hospital> showAllHospitals();

    String selectHospitalName(int hospitalNumber);

    Hospital showOneHospital(int hospitalNumber);


    /**
     * @param hospital,hospitalNumber
     * @return: int
     * @Description: 增, 删, 改
     */
    int addHospital(Hospital hospital);

    int deleteHospital(int hospitalNumber);

    int updateHospital(Hospital hospital);

    DatatableModel getHospitalByPage(Integer draw, Integer start, Integer length);
}
