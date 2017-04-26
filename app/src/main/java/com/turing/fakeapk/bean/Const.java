/**
 * MobileResultConst.java	@ turingkuang @ 2015年9月22日
 */
package com.turing.fakeapk.bean;

/**
 * @author turingkuang
 */
public class Const {

	/* [0~100) 常规信息 */
    /**
     * 成功
     */
    public static int common_ok = 0;
    /**
     * 程序出错
     */
    public static int common_error = 1;
    /**
     * 参数异常
     */
    public static int common_param_error = 2;
    /**
     * 没有找到资源
     */
    public static int common_resource_not_found = 3;
    /**
     * 数据不存在
     */
    public static int common_data_not_exists = 4;
    /**
     * 警告信息
     */
    public static int common_warn = 5;
    /**
     * 未授权
     */
    public static int common_unauthorized = 6;
    /**
     * 平台异常
     */
    public static int common_platfrom_error = 7;
    /**
     * 数据库操作未知错误
     */
    public static int common_unknown = 99;

	/* [100~200) 请求提示信息 */
    /**
     * 验证请求成功
     */
    public static int oauth_request_success = 100;
    /**
     * 时间戳过期，时间戳1天内有效
     */
    public static int oauth_request_overdue = 101;
    /**
     * 签名错误，算法或者token错误
     */
    public static int oauth_request_illegal = 102;
    /**
     * 签名失效，签名已验证过
     */
    public static int oauth_request_invalidSign = 103;

	/* [200~300) 用户提示信息 */
    /**
     * 用户登录过期
     */
    public static int phone_user_accessTokenOverdue = 201;
    /**
     * 此手机已经注册
     */
    public static int phone_user_mobile_exist = 202;
    /**
     * 手机号码不能为空
     */
    public static int phone_user_mobile_null = 203;
    /**
     * 验证码类型不能为空
     */
    public static int phone_user_codeType_null = 204;
    /**
     * 密码不能为空
     */
    public static int phone_user_password_null = 205;
    /**
     * 验证码超时
     */
    public static int phone_user_authCode_timeout = 206;
    /**
     * 验证码错误
     */
    public static int phone_user_authCode_error = 207;
    /**
     * 验证码不能为空
     */
    public static int phone_user_authCode_null = 208;
    /**
     * 此手机号码未注册
     */
    public static int phone_user_mobile_notExist = 209;
    /**
     * 密码错误
     */
    public static int phone_user_password_error = 210;
    /**
     * 密码不能为空
     */
    public static int phone_user_password_notnull = 211;
    /**
     * 真实姓名不能为空
     */
    public static int phone_user_realName_notnull = 212;
    /**
     * 身份证不能为空
     */
    public static int phone_user_identityId_notnull = 213;
    /**
     * 法人不能为空
     */
    public static int phone_user_legalPerson_notnull = 214;
    /**
     * 公司名称不能为空
     */
    public static int phone_user_companyName_notnull = 215;
    /**
     * 用户不存在
     */
    public static int phone_user_notExist = 216;
    /**
     * 用户被禁用
     */
    public static int phone_user_disabled = 217;
    /**
     * 用户余额不足
     */
    public static int phone_user_moneyLack = 218;
    /**
     * 请先实名认证
     */
    public static int phone_user_no_realNameAuth = 219;
    /**
     * 请先设置交易密码
     */
    public static int phone_user_noset_tradePwd = 220;
    /**
     * 交易密码错误
     */
    public static int phone_user_tradePwd_error = 221;
    /**
     * 已经实名认证
     */
    public static int phone_user_realNameAuth_exist = 222;
    /**
     * 已经设置过交易密码
     */
    public static int phone_user_tradePwd_exist = 223;
    /**
     * 验证码类型错误
     */
    public static int phone_user_codeType_error = 224;
    /**
     * 用户类型不能为空
     */
    public static int phone_user_userType_null = 225;
    /**
     * 用户类型错误
     */
    public static int phone_user_userType_error = 226;
    /**
     * 邀请码错误
     */
    public static int phone_user_invite_error = 227;
    /**
     * 用户不能登陆
     */
    public static int phone_user_not_login = 228;
    /**
     * 用户或密码错误
     */
    public static int phone_user_namrOrpwd_error = 229;
    /**
     * 用户类型不支持手机端登录
     */
    public static int phone_usertype_not_support_login = 230;
    /**
     * 用户类型不支持该操作
     */
    public static int phone_usertype_not_support_oper = 231;

	/* [300~400) 投资信息 */
    /**
     * 投资金额必须大于起投金额
     */
    public static int phone_invest_moneyError = 301;
    /**
     * 投资失败
     */
    public static int phone_invest_error = 302;
    /**
     * 项目状态不是融资中，不能投资
     */
    public static int phone_invest_stateError = 303;
    /**
     * 项目不存在
     */
    public static int phone_invest_project_not_exist = 304;
    /**
     * 投资不存在
     */
    public static int phone_invest_not_exist = 305;
    /**
     * 投资金额大于可投金额
     */
    public static int phone_invest_moneyMaxError = 306;
    /**
     * 募集已经结束
     */
    public static int phone_invest_bidclose = 307;
    /**
     * 投资金额必须是增量金额的整数倍
     */
    public static int phone_invest_increment = 308;

	/* [500~600) 银行卡信息 */
    /**
     * 开户人名称不能为空
     */
    public static int phone_bank_name_notnull = 501;
    /**
     * 银行卡号不能为空
     */
    public static int phone_bank_bankCard_notnull = 502;
    /**
     * 开户银行不能为空
     */
    public static int phone_bank_whichBank_notnull = 503;
    /**
     * 银行卡信息错误
     */
    public static int phone_bank_bankInfo_error = 504;
    /**
     * 绑定银行卡失败
     */
    public static int phone_bank_band_fail = 505;
    /**
     * 金额错误
     */
    public static int phone_bank_money_error = 506;
    /**
     * 没有绑定银行卡
     */
    public static int phone_bank_no_band = 507;
    /**
     * 已经绑定银行卡
     */
    public static int phone_bank_already_band = 508;
    /**
     * 身份证不能为空
     */
    public static int phone_bank_idCard_notnull = 509;
    /**
     * 银行卡信息不存在
     */
    public static int phone_bank_notExist = 510;
    /**
     * 绑卡短信验证失败
     */
    public static int phone_bank_band_verifyerror = 511;
    /**
     * 获取充值短信失败
     */
    public static int phone_bank_recharge_code_error = 512;
    /**
     * 绑卡记录失败
     */
    public static int phone_bank_record_error = 513;
    /**
     * 充值短信验证失败
     */
    public static int phone_bank_recharge_verify_error = 514;
    /**
     * 充值用户金额更新失败
     */
    public static int phone_bank_recharge_user_error = 515;
    /**
     * 银行提现失败
     */
    public static int phone_bank_withdraw_fail = 516;
    /**
     * 获取绑卡短信失败
     */
    public static int phone_bank_band_get_error = 517;
    /**
     * 线下操作类型不能为空
     */
    public static int bank_offline_opertype_notnull = 518;
    /**
     * 线下第三方操作流水不能为空
     */
    public static int bank_offline_thirdSerial_notnull = 519;
    /**
     * 线下充值失败
     */
    public static int bank_offline_recharge_fail = 520;
    /**
     * 线下提现失败
     */
    public static int bank_offline_withdraw_fail = 521;
    /**
     * 线下放款失败
     */
    public static int bank_offline_loan_fail = 522;
    /**
     * 第三方绑卡验证失败
     */
    public static int bank_third_fail = 523;
    /**
     * 提现金额必须大于提现手续费
     */
    public static int phone_bank_withdraw_fee = 524;
    /**
     * 银行卡不支持
     */
    public static int phone_bank_support_error = 525;
    /**
     * 银行卡解绑失败
     */
    public static int phone_bank_unbind_fail = 526;
    /**
     * 线下操作银行转账截图不能为空
     */
    public static int bank_offline_bankTransferImage_notnull = 527;

	/* [1000~2000) 活动信息 */
    /**
     * 新人标投资失败
     */
    public static int act_coupon_is_fail = 1000; // 投注册标失败
    /**
     * 活动无效
     */
    public static int act_not_valid = 1001;//
    /**
     * 卷已使用
     */
    public static int act_coupon_is_used = 1002;
    /**
     * 无效卷
     */
    public static int act_coupon_not_valid = 1003;
    /**
     * 投资金额必须大于使用金额
     */
    public static int act_coupon_valid_money = 1004;
    /**
     * 券不存在
     */
    public static int act_coupon_not_exist = 1005;

}
