package com.wywk.myautotest.constant;

/**
 * @ClassName: GlobalConstants
 * @Author: zsj
 * @Since: 2020/7/31 17:34
 * @Version: V1.0
 * @Description:
 */
public class GlobalConstants {

    public static final int RESP_SUCCESS_CODE = 1;

    public static final int RESP_FAIL_CODE = 0;

    public static final String RESP_SUCCESS_MSG = "操作成功";

    public static final String RESP_FAIL_MSG = "操作失败";

    public static final String RESP_FAIL_RESEARCH_MSG = "未查询到记录";

    public static final String RESP_CODE_KEY = "code";

    public static final String RESP_MSG_KEY = "msg";

    public static final String RESP_DATA_KEY = "data";

    public static final String RESP_TOKEN_KEY = "token";

    public GlobalConstants() {
    }

    public static int getRespFailCode() {
        return RESP_FAIL_CODE;
    }

    public static String getRespFailMsg() {
        return RESP_FAIL_MSG;
    }

    public static int getRespSuccessCode() {
        return RESP_SUCCESS_CODE;
    }

    public static String getRespSuccessMsg() {
        return RESP_SUCCESS_MSG;
    }

    public static String getRespFailResearchMsg() {
        return RESP_FAIL_RESEARCH_MSG;
    }

    public static String getRespCodeKey() {
        return RESP_CODE_KEY;
    }

    public static String getRespMsgKey() {
        return RESP_MSG_KEY;
    }

    public static String getRespDataKey() {
        return RESP_DATA_KEY;
    }

    public static String getRespTokenKey() {
        return RESP_TOKEN_KEY;
    }
}
