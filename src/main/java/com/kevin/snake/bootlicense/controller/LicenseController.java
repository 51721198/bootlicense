package com.kevin.snake.bootlicense.controller;

import com.kevin.snake.bootlicense.aop.NeedCheck;
import com.kevin.snake.bootlicense.enums.ProcessResultEnum;
import com.kevin.snake.bootlicense.pojo.DatatableModel;
import com.kevin.snake.bootlicense.pojo.LicenseDetail;
import com.kevin.snake.bootlicense.pojo.ProcessResult;
import com.kevin.snake.bootlicense.pojo.RSAKey;
import com.kevin.snake.bootlicense.service.LicenseService;
import com.kevin.snake.bootlicense.util.ClassPathResourceURI;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



/**
 *
 * @ClassName: LicenseController
 * @Description: 序列号管理系统控制器
 * @author: Liu.Dun
 * @date: 2016年6月27日 下午8:40:05
 */
@RestController
@RequestMapping(value = "license")
@Slf4j
public class LicenseController {

    @Autowired
    private LicenseService licenseService;

    @Autowired
    private Environment env;

    @RequestMapping(value = "createcode", method = RequestMethod.GET)
    public ProcessResult sourceCode(@PathParam("hosnumber") String hosnumber, @PathParam("duedate") String duedate) throws Exception {
        Map<String, String> codeMap;
        ProcessResult<Map<String,String>> processResult = new ProcessResult();
        try {
            if (hosnumber != null && duedate != null) {
                codeMap = licenseService.createSourceCode(duedate, Integer.parseInt(hosnumber));
                processResult.setResult(ProcessResultEnum.SUCCESS,codeMap);
            }
        } catch (Exception e) {
            log.error("sourceCode exception:{}",hosnumber,duedate,e);
            throw e;
        }
        return processResult;
    }

    @RequestMapping(value = "encryptcode")
    public ProcessResult encryptCode(@PathParam("sourcecode") String sourcecode) {
        ProcessResult<Map<RSAKey,String> > processResult = new ProcessResult<>();
        try {
            RSAKey rsakey = licenseService.getLatestRSAKey();
            String encryptcode = licenseService.createEncryptCode(sourcecode, rsakey.getPublicKey());
            Map<RSAKey,String> encryptResult = new HashMap<>();
            encryptResult.put(rsakey,encryptcode);
            processResult.setResult(ProcessResultEnum.SUCCESS,encryptResult);
        } catch (Exception e) {
            log.error("encryptCode exception,sourcecode:{}",sourcecode,e);
        }
        return processResult;
    }

    /**
     * @param:
     * @return: 所有序列号+序列号对应医院名称
     * @Title: showAllCodes
     * @Description: 获取所有序列号，并且获取序列号对应的医院名称
     */
    @NeedCheck("Hello world API")  //有这个注解的方法必须进行AOP拦截
    @RequestMapping(value = "showallcodes")
    public ProcessResult showAllCodes() {
        ProcessResult<List<LicenseDetail>> processResult = new ProcessResult<>();
        log.info("controller方法执行！拦截这个方法!!!!!!!!!!!!!");

        try {
            List<LicenseDetail> list = licenseService.listAllCodes();
            processResult.setResult(ProcessResultEnum.SUCCESS,list);
        } catch (Exception e) {
            log.error("showAllCodes exception:{}",e);
        }
        return processResult;
    }

    @NeedCheck("")
    @RequestMapping(value = "showallcodesByPage", method = RequestMethod.POST)
    public DatatableModel showAllCodesByPage(HttpServletRequest request) {
        Integer draw = 1;
        Integer length = 0;
        Integer start = 0;
        DatatableModel result = null;
        try {
            if (request != null) {
                draw = (Integer.parseInt(request.getParameter("draw")));
                length = Integer.parseInt(request.getParameter("length"));
                start = Integer.parseInt(request.getParameter("start"));
                result = licenseService.getLicenseByPage(draw, start, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @param:
     * @return: ProcessResult
     * @Title: deleteCode
     * @Description: 删除序列号，如果序列号未过期则禁止删除
     */
    @RequestMapping(value = "deletecode")
    public ProcessResult deleteCode(@PathParam("serialNumberId") String serialNumberId) {
        ProcessResult<String> processResult = new ProcessResult<>();

        try {
            LicenseDetail licensedetail = licenseService.listOneCode(Integer.parseInt(serialNumberId));

            int lastdays = licenseService.countEndDate(licensedetail.getExpiredDate());
            if (lastdays >= 0) {
                processResult.setResult(ProcessResultEnum.DEL_FAIL,"序列号并未过期,删除失败");
            } else {
                int i = licenseService.deleteCode(Integer.parseInt(serialNumberId));
                if (i == 1) {
                    processResult.setResult(ProcessResultEnum.SUCCESS);
                } else {
                    processResult.setResult(ProcessResultEnum.DEL_FAIL);
                }
            }
        } catch (Exception e) {
            log.error("deletecode serialNumberId:{} exception:{}",serialNumberId,e);
        }
        return processResult;
    }

    /**
     * @param serialNumberId
     * @return
     * @throws IOException
     * @param:
     * @return: ProcessResult
     * @Title: useLicense
     * @Description: 使用序列号
     */
    @RequestMapping(value = "uselicense")
    public void useLicense(@PathParam("serialNumberId") String serialNumberId, HttpServletResponse response) {
        ProcessResult processResult = new ProcessResult();
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        boolean creatsucess = false;
        String nameofzip = "license.zip";
        creatsucess = licenseService.createZIPFile(Integer.parseInt(serialNumberId));
        if (!creatsucess) {
            log.error("生成ZIP文件失败!");
            return;
        }

        try {
            FileInputStream inputStream1 = new FileInputStream(path + nameofzip);
            response.addHeader("Content-disposition", "attachment;filename=license.zip");
            response.setContentType("zip/plain");
            IOUtils.copy(inputStream1, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("exception:{}", e);
        }

        try {
            int i = licenseService.modifyLicenseState(Integer.parseInt(serialNumberId));
            if (i == 1) {
                processResult.setResult(ProcessResultEnum.SUCCESS);
            } else {
                processResult.setResult(ProcessResultEnum.UPD_FAIL);
            }
        } catch (Exception e) {
            log.error("useLicense exception,serialNumberId:{}",serialNumberId,e);
        }
    }

    @RequestMapping(value = "uselicense/{serialNumberId}")
    public void useLicenseAngu(@PathVariable("serialNumberId") String serialNumberId, HttpServletResponse response) {
        ProcessResult processResult = new ProcessResult();
        String path = ClassPathResourceURI.getResourceURI("/").getPath();
        boolean creatsucess = false;
        String nameofzip = "license.zip";
        creatsucess = licenseService.createZIPFile(Integer.parseInt(serialNumberId));
        if (!creatsucess) {
            log.error("生成ZIP文件失败!");
            return;
        }

        try {
            FileInputStream inputStream1 = new FileInputStream(path + nameofzip);
            response.addHeader("Content-disposition", "attachment;filename=license.zip");
            response.setContentType("zip/plain");
            IOUtils.copy(inputStream1, response.getOutputStream());
            response.flushBuffer();
        } catch (Exception e) {
            log.error("exception{}", e);
        }

        try {
            int i = licenseService.modifyLicenseState(Integer.parseInt(serialNumberId));
            if (i == 1) {
                processResult.setResult(ProcessResultEnum.SUCCESS);
            } else {
                processResult.setResult(ProcessResultEnum.UPD_FAIL);
            }
        } catch (Exception e) {
            log.error("useLicense exception,serialNumberId:{}",serialNumberId,e);
        }
    }

    /**
     * @param licensedetail
     * @return
     * @param:
     * @return: ModelAndView
     * @Title: saveCode
     * @Description: 保存序列号对象, 该对象包含原始序列号, 加密序列号等信息,
     * 后端非空判断采用javax.validation,防止前端js非空判断失效
     */
    @RequestMapping(value = "savecode", method = RequestMethod.POST)
    public ProcessResult saveCode(@Valid  LicenseDetail licensedetail, BindingResult bindingResult) {
        ProcessResult<String> processResult = new ProcessResult<>();

        /**
         * 假如入参是application/json格式,则只能采用@RequestBody进行入参绑定,这种情况下@valid校验无法正常工作
         * 假如入参是application/x-www-form-urlencoded,则这种使用@RequestBody绑定会抛异常,去掉该注解后@valid正常
         */
        if (bindingResult.hasFieldErrors()) {
            log.error("序列号参数绑定异常:" + bindingResult.getFieldError().getDefaultMessage());
            processResult.setResult(ProcessResultEnum.INS_FAIL,bindingResult.getFieldError().getDefaultMessage());
            return processResult;
        }

        try {
            int i = licenseService.saveCode(licensedetail);
            if (i != 1) {
                log.warn("序列号保存失败" + licensedetail.toString());
            }
        } catch (Exception e) {
            log.warn("序列号保存失败" + licensedetail.toString());
            processResult.setResult(ProcessResultEnum.INS_FAIL);
            return processResult;
        }
        processResult.setResult(ProcessResultEnum.SUCCESS);
        return processResult;
    }

    @RequestMapping(value = "createkeypair")
    public ProcessResult createKeyPair() {
        ProcessResult processResult = new ProcessResult();
        int i = 0;
        RSAKey rsaKey = new RSAKey();
        i = licenseService.createKeyPair(rsaKey);
        if (i == 1) {
            processResult.setResult(ProcessResultEnum.SUCCESS);
        } else {
            processResult.setResult(ProcessResultEnum.INS_FAIL);
        }
        return null;
    }
}
