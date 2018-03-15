package com.kevin.snake.bootlicense.enums;

/**
 * Created by tangqiuyuan on 2017/4/8.
 */
public enum SysMsgEnum {
    //系统异常code和开放平台保持一致
    SUCCESS(0, "成功", "request_success"),
    FAIL(1, "系统错误", "request_error"),
    PARAM_ERR(2, "参数异常", "param_error"),
    SIGNATURE_ERR(3, "签名验证失败", "signature_error"),
    HTTP_REQUEST_METHOD_ERROR(7, "http请求方式错误", "request_method_error"),
    DATABASE_OPERATION_ERROR(8, "数据库操作失败", "sql_error"),
    BIND_EXCEPTION(10, "socket连接异常", "bind_error"),

    //电子发票业务错误码
    SEND_REQUEST_ERROR(1301, "服务商接口调用失败", "web_socket_error"),
    UNSUPPORTED_ENCODING(1302, "计算数据大小出错", "computer_data_error"),
    MD5_ERROR(1303, "md5加密出错", "md5_error"),
    CONSUMER_INVOICE_ERROR(1304, "该扫码人没有开过此发票，无法查看发票信息", "consumer_query_error"),
    VENDOR_NO_OPEN_INVOICE(1305, "该商家没有开通电子发票业务", "vendor_open_invoice_error"),
    CHECKOUT_PRICE_TAX_AMOUNT(1306, "发票开取金额校验失败", "verify_price_error"),
    VENDOR_COMMIT_ERROR(1307, "不存在此条商家提交发票开具记录", "vendor_commit_error"),
    CONSUMER_COMMIT_ERROR(1308, "消费者提交发票请求失败，请核对参数", "consumer_commit_error"),
    CONSUMER_COMMIT_AGAIN_SUCCESS(1309, "发票已开具成功，查看邮箱或者选择发票邮件补发", "consumer_commit_again_error"),
    CONSUMER_COMMIT_AGAIN_ERROR(1310, "该张发票已经开具失败", "consumer_commit_again_error"),
    CONSUMER_COMMIT_BILLING(1311, "该张发票正在开具", "consumer_commit_billing"),
    CONSUMER_INFO_UPDATE_ERROR(1312, "消费者邮箱和电话更改失败", "consumer_info_update_error"),
    JSON_CONVERT_ERROR(1313, "数据json格式转换失败，请校验", "json_convert_error"),
    VENDOR_URLENCODE_ERROR(1314, "url编码错误", "vendor_urlencode_error"),
    CONSUMER_INVOICE_EXISTED(1315, "该张发票已经存在", "consumer_invoice_existed"),
    QRCODE_PARAM_ERROR(1316, "二维码接口参数校验错误", "qrcode_param_error"),
    QRCODE_CHECKER_ERROR(1317, "checker校验错误,长度不超过8字节", "qrcode_checker_error"),
    QRCODE_DRAWER_ERROR(1318, "drawer校验错误,长度不超过8字节", "qrcode_drawer_error"),
    QRCODE_PRICE_ERROR(1319, "金额不能为空或者金额数目校验错误", "qrcode_price_error"),
    REMOTE_THRIFT_EXCEPTION(1320, "远程thrift调用失败", "remote_thrift_exception");
    private int code;
    private String message;
    private String errorType;


    SysMsgEnum(int code, String message, String errorType) {
        this.code = code;
        this.errorType = errorType;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError_type() {
        return errorType;
    }

    public void setError_type(String error_type) {
        this.errorType = error_type;
    }
}
