package com.startcaft.ssm.po;

public class Application {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_application.id
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    private Integer id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_application.app_name
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    private String appName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_application.app_code
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    private String appCode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_application.id
     *
     * @return the value of tb_application.id
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_application.id
     *
     * @param id the value for tb_application.id
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_application.app_name
     *
     * @return the value of tb_application.app_name
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public String getAppName() {
        return appName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_application.app_name
     *
     * @param appName the value for tb_application.app_name
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public void setAppName(String appName) {
        this.appName = appName == null ? null : appName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_application.app_code
     *
     * @return the value of tb_application.app_code
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public String getAppCode() {
        return appCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_application.app_code
     *
     * @param appCode the value for tb_application.app_code
     *
     * @mbggenerated Thu Oct 08 17:27:14 CST 2015
     */
    public void setAppCode(String appCode) {
        this.appCode = appCode == null ? null : appCode.trim();
    }
}