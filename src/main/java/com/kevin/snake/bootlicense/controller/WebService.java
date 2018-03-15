package com.kevin.snake.bootlicense.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kevin on 2017/4/3.
 */

@RestController
@RequestMapping("webServiceTest")
public class WebService {

    public static final Logger LOGGER = LoggerFactory.getLogger(WebService.class);

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public String test(@RequestParam("uname") String username) {
        System.out.println("call ws");
        return null;
//        JaxWsDynamicClientFactory dcf = JaxWsDynamicClientFactory.newInstance();
//
////        Client client = dcf.createClient("http://localhost:8888/webService/test?wsdl");
////        Object[] objects = new Object[0];
////        try {
////            objects = client.invoke("testWebService",username);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
//
//        Client client = dcf.createClient("http://fptest.ele-cloud.com:8091/dzfp/service/eInvWS?wsdl");
//        Object[] objects = new Object[10];
//        String interfaceCode = "";
//        String aa = "";
//
//
//        String globle = "<?xml version='1.0' encoding='utf-8' ?>" +
//                "<interface xmlns='' xmlns:xsi='http://www.w3.org/2001/XMLSchema-instance' xsi:schemaLocation='http://www.chinatax.gov.cn/tirip/dataspec/interfaces.xsd' version='DZFP1.0' >" +
//                "<globalInfo>" +
//                "<terminalCode>0</terminalCode>" +
//                "<appId>ZZS_PT_DZFP</appId>" +
//                "<version>2.0</version>" +
//                "<interfaceCode>" + interfaceCode + "</interfaceCode>" +
//                "<userName>144TVCCN</userName>" +
//                "<passWord>0232747270yP0awQXxq+793biCwxmTIw==</passWord>" +
//                "<taxpayerId>150001203909248032</taxpayerId>" +
//                "<authorizationCode>1509248032</authorizationCode>" +
//                "<requestCode>144TVCCN</requestCode>" +
//                "<requestTime>2017-04-05 13:30:42 968</requestTime>" +
//                "<responseCode>144</responseCode>" +
//                "<dataExchangeId>144TVCCN20170405000000001</dataExchangeId>" +
//                "</globalInfo>" +
//                "<returnStateInfo>" +
//                "<returnCode />" +
//                "<returnMessage />" +
//                "</returnStateInfo>" +
//                "<Data>" +
//                "<dataDescription>" +
//                " <zipCode>0</zipCode>" +
//                "<encryptCode>2</encryptCode>" +
//                "<codeType>CA</codeType>" +
//                "</dataDescription>" +
//                "<content>" + aa +
//                "</content>" +
//                "</Data>" +
//                "</interface>";
//
//        try {
//            objects = client.invoke("eiInterface", globle);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        System.out.println(objects[0]);
//
//        return JSON.toJSONString(objects);

    }


}
