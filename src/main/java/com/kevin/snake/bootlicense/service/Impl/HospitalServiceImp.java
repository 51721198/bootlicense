package com.kevin.snake.bootlicense.service.Impl;

import com.kevin.snake.bootlicense.dao.HospitalDao;
import com.kevin.snake.bootlicense.pojo.DatatableModel;
import com.kevin.snake.bootlicense.pojo.Hospital;
import com.kevin.snake.bootlicense.service.HospitalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class HospitalServiceImp implements HospitalService {

    public static final Logger LOGGER = LoggerFactory.getLogger(HospitalServiceImp.class);


    @Autowired
    private HospitalDao hospitaldao;

    @Override
//    @PreAuthorize("hasRole(admin)")
    public List<Hospital> showAllHospitals() {

        System.out.println("can heere");

        List<Hospital> list = hospitaldao.showAll();
        return list;
    }

    @Override
    public int addHospital(Hospital hospital) {
        int i = 0;
        try {
            i = hospitaldao.insertHospital(hospital);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public int deleteHospital(int hospitalnumber) {

        int i = 0;
        try {
            i = hospitaldao.deleteByPrimaryKey(hospitalnumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public String selectHospitalName(int hospitalNumber) {

        String hospitalName = null;

        if (hospitaldao.selectByPrimaryKey(hospitalNumber) == null) {
            return "无此医院信息";
        }

        hospitalName = hospitaldao.selectByPrimaryKey(hospitalNumber).getHospitalName();

        return hospitalName;
    }

    @Override
    public int updateHospital(Hospital hospital) {
        int i = 0;
        try {
            i = hospitaldao.updateByPrimaryKey(hospital);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public Hospital showOneHospital(int hospitalNumber) {

        return hospitaldao.selectByPrimaryKey(hospitalNumber);
    }

    @Override
//    @PreAuthorize("hasRole('ROLE_ADMIN')")    //权限验证,需要前端每次请求附带用户信息,否则不能做
//    @PreFilter("hasPermission()")
    public DatatableModel getHospitalByPage(Integer draw, Integer start, Integer length) {

        DatatableModel model = new DatatableModel();
        Integer recordsTotal = 0;
        Integer recordsFiltered = 0;
        try {
            model.setDraw(draw);
            List<Hospital> list = hospitaldao.selectAllHospitalsByPage(start, length);
            recordsTotal = hospitaldao.selectCountHospitals();
            recordsFiltered = recordsTotal;
            model.setData(list);
            model.setRecordsFiltered(recordsFiltered);
            model.setRecordsTotal(recordsTotal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return model;
    }
}
