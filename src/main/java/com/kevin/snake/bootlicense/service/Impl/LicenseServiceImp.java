package com.kevin.snake.bootlicense.service.Impl;

import com.kevin.snake.bootlicense.dao.HospitalDao;
import com.kevin.snake.bootlicense.dao.LicenseDao;
import com.kevin.snake.bootlicense.dao.RSAKeyDao;
import com.kevin.snake.bootlicense.pojo.DatatableModel;
import com.kevin.snake.bootlicense.pojo.LicenseDetail;
import com.kevin.snake.bootlicense.pojo.RSAKey;
import com.kevin.snake.bootlicense.service.LicenseService;
import com.kevin.snake.bootlicense.util.ByteArrayToObj;
import com.kevin.snake.bootlicense.util.ObjToByteArray;
import com.kevin.snake.bootlicense.util.ObjToFile;
import com.kevin.snake.bootlicense.util.StringToFile;
import com.kevin.snake.bootlicense.util.TimeDiff;
import com.kevin.snake.bootlicense.util.ZIPFiles;
import com.kevin.snake.bootlicense.util.rsa.RSACreateSourceCode;
import com.kevin.snake.bootlicense.util.rsa.RSAKeyPair;
import com.kevin.snake.bootlicense.util.rsa.RSAdoEncrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.security.Key;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class LicenseServiceImp implements LicenseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(LicenseServiceImp.class);
    @Resource
    private LicenseDao licensedao;

    @Resource
    private HospitalDao hospitaldao;

    @Resource
    private RSAKeyDao rsakeydao;

    @Override
    /**
     *
     * @Title: createSourceCode
     * @Description: 生成原始序列号
     * @param duedate
     * @return sourceCode
     * @see com.vico.license.service.LicenseService#createSourceCode(String)
     */
    public Map<String, String> createSourceCode(String duedate, int hosnumber) throws Exception {
        String sourceCode = "";
        String hospitalName = "";
        hospitalName = hospitaldao.selectByPrimaryKey(hosnumber).getHospitalName();

        sourceCode = RSACreateSourceCode.createSourceCode(duedate, hospitalName); // RSA风格的原始序列号

        RSAKey rsakey = getLatestRSAKey();
        String encryptcode = createEncryptCode(sourceCode, rsakey.getPublicKey());

        Map<String, String> codeMap = new HashMap<>();
        codeMap.put("sourceCode", sourceCode);
        codeMap.put("encryptcode", encryptcode);
        codeMap.put("rsaKey", rsakey.getKeyId() + "");

        // sourceCode = MD5CreateSourceCode.createSourceCode(duedate);
        // //MD5风格的原始序列号
        return codeMap;
    }

    /**
     * @param code
     * @return
     * @Title: createEncryptCode
     * @Description:采用加密算法对原始序列后进行加密
     */
    @Override
    public String createEncryptCode(String code, byte[] publickey) {

        String encryptcode = "";
        try {
            encryptcode = RSAdoEncrypt.encrypt(code, publickey); // RSA算法加密

        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
        }
        return encryptcode;
    }

    /**
     * @param licensedetail
     * @Title: saveCode
     * @Description: 存储序列号，存储前计算序列号的有效日期和到期标识
     */
    @Override
    public int saveCode(LicenseDetail licensedetail) {
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        licensedetail.setCreateDate(currentDate);
        licensedetail.setLicenseState(0);
        int i = 0;
        try {
            i = licensedao.insertLicenseDetail(licensedetail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public List<LicenseDetail> listAllCodes() {

        List<LicenseDetail> list = licensedao.selectAllLicenses();
        return list;
    }

    @Override
    public int deleteCode(int codeID) {
        int i = 0;
        try {
            i = licensedao.deleteByPrimaryKey(codeID);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public LicenseDetail listOneCode(int serialNumberId) {

        LicenseDetail licensedetail = licensedao.selectByPrimaryKey(serialNumberId);
        return licensedetail;
    }

    @Override
    public List<LicenseDetail> selectByhospitalNumber(int hospitalNumber) {

        List<LicenseDetail> list = licensedao.selectLicenseByHospitalNumber(hospitalNumber);
        return list;
    }

    /**
     * @Title: taskChangeData
     * @Description:springtask定时任务，每24小时计算一次序列号的到期标识和有效天数，写入数据库
     */
    @Override
    public void taskChangeData() {

        List<LicenseDetail> list = licensedao.selectAllLicenses();
        for (LicenseDetail ldetail : list) {
            licensedao.updateByPrimaryKey(ldetail);
        }
    }

    @Override
    public int modifyLicenseState(int serialNumberId) {

        LicenseDetail licensedetail = licensedao.selectByPrimaryKey(serialNumberId);
        licensedetail.setLicenseState(1);
        int i = 0;
        try {
            i = licensedao.updateByPrimaryKey(licensedetail);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public int countEndDate(java.sql.Date expiredDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return TimeDiff.countDay(sdf.format(expiredDate));
    }

    @Override
    public int createKeyPair(RSAKey rsaKey) {
        int i = 0;
        try {
            Map<String, Key> map = RSAKeyPair.generateKeyPair();
            Key privatekey = map.get("privateKey");
            Key publicKey = map.get("publicKey");
            byte[] pri = ObjToByteArray.ObjectToByte(privatekey);
            byte[] pub = ObjToByteArray.ObjectToByte(publicKey);
            rsaKey.setPrivateKey(pri);
            rsaKey.setPublicKey(pub);
        } catch (Exception e) {
            LOGGER.error("exception:{}", e);
        }
        try {
            i = rsakeydao.insertRSAKeyPair(rsaKey);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return i;
    }

    @Override
    public RSAKey getLatestRSAKey() {
        return rsakeydao.selectKeyByID();
    }

    @Override
    public boolean createZIPFile(int serialNumberId) {
        boolean zipres = false;
        boolean strtofile = false;
        boolean objtofile = false;
        boolean flag = false;

        LicenseDetail lDetail = licensedao.selectCodeAndRSAByPrimaryKey(serialNumberId);

        strtofile = StringToFile.string2File(lDetail.getEncryptedNumber());

        objtofile = ObjToFile.object2File(ByteArrayToObj.ByteToObject(lDetail.getRsaKey().getPrivateKey()));

        if (strtofile && objtofile) {
            zipres = ZIPFiles.compatFiles();
            flag = zipres;
        }
        return flag;
    }

    @Override
    public DatatableModel getLicenseByPage(Integer draw, Integer start, Integer length) {
        // TODO Auto-generated method stub

        DatatableModel model = new DatatableModel();
        Integer recordsTotal = 0;
        Integer recordsFiltered = 0;
        try {
            model.setDraw(draw);
            List<LicenseDetail> list = new ArrayList<>();
            list = licensedao.selectAllLicensesByPage(start, length);
            recordsTotal = licensedao.selectCountLicenses();
            recordsFiltered = recordsTotal;

            model.setData(list);
            model.setRecordsFiltered(recordsFiltered);
            model.setRecordsTotal(recordsTotal);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return model;
    }
}
