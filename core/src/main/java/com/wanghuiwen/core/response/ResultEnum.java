package com.wanghuiwen.core.response;

public enum ResultEnum implements IResultEnum {
    SUCCESS_MESSAGE(200),
    UPLOADED(200),
    LOGIN_SUCCESS(20000),
    LOGIN_FAIL(400001),
    MODULE_EXISTS(400002),
    LOGIN_OUT_SUCCESS(400003),
    UPLOADED_FAIL(400004),
    UPLOADED_MAX(400005),
    REDIS_CONNECTION_FAIL(400006),
    DATE_ENTRY_ERROR(400007),
    PARAMS_LACK(400008),
    CONNECT_EXCEPTION(400009),
    DUPLICATE_KEY(400010),
    INTERNAL_SERVER_ERROR(400011),
    NO_RELATED_USER(400012),
    FORBIDDEN(400013),
    ROLE_HAS_USER(400014),
    UNAUTHORIZED(400015),
    NO_COUNTRY(400016),
    PARAMS_VALIDATED_ERROR(400017),
    ;
    private final int code;

    ResultEnum(int code) {
        this.code=code;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getMessageCode() {
        return String.valueOf(this.code);
    }


}
