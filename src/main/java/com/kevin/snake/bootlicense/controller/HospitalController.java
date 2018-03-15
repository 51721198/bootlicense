package com.kevin.snake.bootlicense.controller;

import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import com.kevin.snake.bootlicense.pojo.DatatableModel;
import com.kevin.snake.bootlicense.pojo.Hospital;
import com.kevin.snake.bootlicense.pojo.LicenseDetail;
import com.kevin.snake.bootlicense.pojo.ProcessResult;
import com.kevin.snake.bootlicense.service.HospitalService;
import com.kevin.snake.bootlicense.service.LicenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @author Liu.Dun
 * @医院信息系统控制器
 */
@RestController
@RequestMapping(value = "hospital")
public class HospitalController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HospitalController.class);
    @Autowired
    private HospitalService hospitalservice;

    @Autowired
    private LicenseService licenseservice;


    @RequestMapping(value = "showhospital")
    public ProcessResult showAllHospital() {
        ProcessResult<List<Hospital>> processResult = new ProcessResult<>();
        LOGGER.info("show all hospitals");
        try {
            List<Hospital> list = hospitalservice.showAllHospitals();
            processResult.setResult(ProcessResultEnum.SUCCESS,list);
        } catch (Exception e) {
            LOGGER.error("获取医院信息失败:{}" + e);
        }
        return processResult;
    }

    @RequestMapping(value = "showHospitalByPage", method = RequestMethod.POST)
    public DatatableModel showAllHospitalByPage(HttpServletRequest request) {
        Integer draw = 1;
        Integer length = 0;
        Integer start = 0;
        DatatableModel result = null;
        LOGGER.info("show all hospitals");
        try {
            if (request != null) {
                draw = (Integer.parseInt(request.getParameter("draw")));
                length = Integer.parseInt(request.getParameter("length"));
                start = Integer.parseInt(request.getParameter("start"));
                result = hospitalservice.getHospitalByPage(draw, start, length);
            }
        } catch (Exception e) {
            LOGGER.error("查询异常:,request:{}",request, e);
        }
        return result;
    }

    /**
     * @return
     * @由医院编号获取医院信息
     */
    @RequestMapping(value = "showone")
    public ProcessResult selectOneHospital(@PathParam("hospitalNumber") String hospitalNumber) {
        ProcessResult<Hospital> processResult = new ProcessResult<>();
        try {
            if (StringUtils.hasText(hospitalNumber)) {
                Hospital hospital = hospitalservice.showOneHospital(Integer.parseInt(hospitalNumber));
                processResult.setResultobject(hospital);
                processResult.setResult(ProcessResultEnum.SUCCESS,hospital);
            } else {
                processResult.setResult(ProcessResultEnum.QUE_FAIL);
            }
        } catch (Exception e) {
            LOGGER.error("selectOneHospital exception,hospitalNumber:{}",hospitalNumber,e);
        }
        return processResult;
    }

    /**
     * @return: void
     * @Title: deleteHospital
     * @Description: 删除医院，有关联序列号信息的医院禁止删除
     */
    @RequestMapping(value = "deletehospital")
    public ProcessResult deleteHospital(@PathParam("hospitalNumber") String hospitalNumber) {
        ProcessResult<String> processResult = new ProcessResult<>();
        try {
            List<LicenseDetail> list = licenseservice.selectByhospitalNumber(Integer.parseInt(hospitalNumber));
            if (list.isEmpty()) {
                int i = hospitalservice.deleteHospital(Integer.parseInt(hospitalNumber));
                //返回值为1说明删除操作执行成功
                if (i == 1) {
                    processResult.setResult(ProcessResultEnum.SUCCESS);
                } else {
                    processResult.setResult(ProcessResultEnum.DEL_FAIL);
                }
            } else {
                processResult.setResult(ProcessResultEnum.DEL_FAIL,"有关联序列号信息,删除失败");
            }
        } catch (Exception e) {
            LOGGER.error("deleteHospital exception,hospitalNumber:{}:",hospitalNumber,e);
        }
        return processResult;
    }

    /**
     * @param hospital
     * @return
     * @param:
     * @return: String
     * @Title: addHospital
     * @Description: 根据hospitalNumber的值是否存在判断是添加还是修改
     */
    @RequestMapping(value = "addhospital")
    public ModelAndView addHospital(@Valid Hospital hospital, BindingResult result) {

        ProcessResult<?> processResult = new ProcessResult<>();
        /**
         * 后台非空判断,假如输入的医院编号和名称为空,则返回原页面
         */
        if (result.hasErrors()) {
            ModelAndView mv = new ModelAndView("redirect:/bounceController/toaddhospital");
            return mv;
        }

        try {
            if (hospital.getHospitalNumber() == null) {
                int i = hospitalservice.addHospital(hospital);
                if (i == 1) {
                    processResult.setResult(ProcessResultEnum.SUCCESS);
                } else {
                    processResult.setResult(ProcessResultEnum.INS_FAIL);
                }
            } else {
                int i = hospitalservice.updateHospital(hospital);
                if (i == 1) {
                    processResult.setResult(ProcessResultEnum.SUCCESS);
                } else {
                    processResult.setResult(ProcessResultEnum.UPD_FAIL);
                }
            }
        } catch (Exception e) {
            LOGGER.error("addHospital exception:hospital:{}",hospital,e);
        }
        return new ModelAndView("redirect:/bounceController/toshowallhospital");
    }

}
