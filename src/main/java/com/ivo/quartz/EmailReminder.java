package com.ivo.quartz;

import com.ivo.dao.abnormalRecord.IAbnormalDao;
import com.ivo.model.abnormalRecord.Abnormal;
import com.ivo.model.equipment.DepOfClass;
import com.ivo.model.equipment.Equipment;
import com.ivo.model.equipment.EquipmentGroup;
import com.ivo.service.ICheckService;
import com.ivo.service.IEquipmentManageService;
import com.ivo.service.MailService;
import com.ivo.util.HttpRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备异常到期和delay邮件提醒
 * @author jian wang
 * @date 2018/01/17
 */
public class EmailReminder {
    protected Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private MailService mailService;
    @Resource
    private IAbnormalDao abnormalDao;
    @Resource
    private ICheckService checkService;
    @Resource
    private IEquipmentManageService equipmentManageService;

    public IEquipmentManageService getEquipmentManageService() {
        return equipmentManageService;
    }

    public void setEquipmentManageService(IEquipmentManageService equipmentManageService) {
        this.equipmentManageService = equipmentManageService;
    }

    public ICheckService getCheckService() {
        return checkService;
    }

    public void setCheckService(ICheckService checkService) {
        this.checkService = checkService;
    }

    public MailService getMailService() {
        return mailService;
    }

    public void setMailService(MailService mailService) {
        this.mailService = mailService;
    }

    public IAbnormalDao getAbnormalDao() {
        return abnormalDao;
    }

    public void setAbnormalDao(IAbnormalDao abnormalDao) {
        this.abnormalDao = abnormalDao;
    }

    public void execute() {
        execute1();
        execute2();
    }

    /**设备异常即将到期邮件提醒工程师，创建者，并抄送课长，提前一天**/
    public void execute1() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + 1);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        List<Abnormal> abnormalList = abnormalDao.getAbnormalExpected(dateString);

        /**循环遍历abnormalList,发送邮件提醒**/
        for(Abnormal abnormal : abnormalList) {
            String engineer = null;
            String creater = null;
            String engineerMail = null;
            String createrMail = null;
            List<String> mailList = new ArrayList<String>();

            /**
             * engineer数据格式 -- "C1607908 王建"
             * **/
            engineer = abnormal.getEngineer();
            if(engineer!=null && engineer.length()>8) {
                engineer =  engineer.substring(0,8);
            } else {
                continue;
            }
            creater = abnormal.getCreater();
            engineerMail = getEmployeeMailAddress(engineer);
            createrMail = getEmployeeMailAddress(creater);



            if(engineerMail != null) {
                mailList.add(engineerMail);
            }
            if (createrMail != null) {
                mailList.add(createrMail);
            }

            String[] mails = mailList.toArray(new String[mailList.size()]);

            StringBuffer mailStr = new StringBuffer();
            mailStr.append("<html lang=\"en\">");
            mailStr.append("<head><meta charset=\"UTF-8\"></head>");
            mailStr.append("<body>");
            mailStr.append("<h4>Dear All,</h4>");
            mailStr.append("<div style=\"margin-left:30px;\">");
            mailStr.append("<p>异常设备的预计完成时间即将到期，设备还处于异常状态，请注意需要及时处理。");
            mailStr.append("<p>异常状况：</p>");
            mailStr.append("<div><table style='font-size:12px;text-align:center;' width=\"2000\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
            mailStr.append("<tr>");
            mailStr.append("<th style='width:8%;'>日期</th>");
            mailStr.append("<th style='width:8%;'>课</th>");
            mailStr.append("<th style='width:6%;'>系统</th>");
            mailStr.append("<th style='width:6%;'>设备编号</th>");
            mailStr.append("<th style='width:15%;'>异常状况</th>");
            mailStr.append("<th style='width:6%;'>创建人</th>");
            mailStr.append("<th style='width:6%;'>工程师</th>");
            mailStr.append("<th style='width:15%;'>解决方案</th>");
            mailStr.append("<th style='width:8%;'>预计完成时间</th>");
            mailStr.append("<th style='width:8%;'>实际完成时间</th>");
            mailStr.append("<th style='width:6%;'>是否完成</th>");
            mailStr.append("<th style='width:8%;'>备注</th>");
            mailStr.append("</tr>");
            mailStr.append("<tr>");
            mailStr.append("<td>" + abnormal.getDates() + "</td>");
            mailStr.append("<td>" + abnormal.getDeptClassName() + "</td>");
            mailStr.append("<td>" + abnormal.getEquipmentGroupName() + "</td>");
            mailStr.append("<td>" + abnormal.getEquipmentName() + "</td>");
            mailStr.append("<td>" + abnormal.getSipecification() + "</td>");
            mailStr.append("<td>" + abnormal.getCreater() + "</td>");

            mailStr.append("<td>" + abnormal.getEngineer() + "</td>");
            mailStr.append("<td>" + abnormal.getSolutions() + "</td>");
            mailStr.append("<td>" + abnormal.getExpectedTime() + "</td>");
            mailStr.append("<td>" + abnormal.getActualTime() + "</td>");
            mailStr.append("<td>" + ( ("0").equals(abnormal.getIfCompleted()) ? "否":"是") + "</td>");
            mailStr.append("<td>" + abnormal.getMemo() + "</td>");

            mailStr.append("</tr>");

            if(mails.length>0){
                mailService.sendHtmlMails("EPRM@ivo.com.cn", mails,"常务设备妥善率管理系统:设备异常即将到期提醒", mailStr.toString());
            }
        }

    }

    /**设备异常delay一周邮件提醒工程师，创建者，并抄送经理，delay 一周**/
    public void execute2() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - 7);
        Date date = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = sdf.format(date);
        List<Abnormal> abnormalList = abnormalDao.getAbnormalExpected(dateString);

        /**循环遍历abnormalList,发送邮件提醒**/
        for(Abnormal abnormal : abnormalList) {
            String engineer = null;
            String creater = null;
            String engineerMail = null;
            String createrMail = null;
            List<String> mailList = new ArrayList<String>();

            /**
             * engineer数据格式 -- "C1607908 王建"
             * **/
            engineer = abnormal.getEngineer();
            if(engineer!=null && engineer.length()>8) {
                engineer =  engineer.substring(0,8);
            } else {
                continue;
            }
            creater = abnormal.getCreater();
            engineerMail = getEmployeeMailAddress(engineer);
            createrMail = getEmployeeMailAddress(creater);
            if(engineerMail != null) {
                mailList.add(engineerMail);
            }
            if (createrMail != null) {
                mailList.add(createrMail);
            }

            String[] mails = mailList.toArray(new String[mailList.size()]);

            StringBuffer mailStr = new StringBuffer();
            mailStr.append("<html lang=\"en\">");
            mailStr.append("<head><meta charset=\"UTF-8\"></head>");
            mailStr.append("<body>");
            mailStr.append("<h4>Dear All,</h4>");
            mailStr.append("<div style=\"margin-left:30px;\">");
            mailStr.append("<p>异常设备的预计完成时间已经delay一周，设备还处于异常状态，请注意需要及时处理。");
            mailStr.append("<p>异常状况：</p>");
            mailStr.append("<div><table style='font-size:12px;text-align:center;' width=\"2000\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
            mailStr.append("<tr>");
            mailStr.append("<th style='width:8%;'>日期</th>");
            mailStr.append("<th style='width:8%;'>课</th>");
            mailStr.append("<th style='width:6%;'>系统</th>");
            mailStr.append("<th style='width:6%;'>设备编号</th>");
            mailStr.append("<th style='width:15%;'>异常状况</th>");
            mailStr.append("<th style='width:6%;'>创建人</th>");
            mailStr.append("<th style='width:6%;'>工程师</th>");
            mailStr.append("<th style='width:15%;'>解决方案</th>");
            mailStr.append("<th style='width:8%;'>预计完成时间</th>");
            mailStr.append("<th style='width:8%;'>实际完成时间</th>");
            mailStr.append("<th style='width:6%;'>是否完成</th>");
            mailStr.append("<th style='width:8%;'>备注</th>");
            mailStr.append("</tr>");
            mailStr.append("<tr>");
            mailStr.append("<td>" + abnormal.getDates() + "</td>");
            mailStr.append("<td>" + abnormal.getDeptClassName() + "</td>");
            mailStr.append("<td>" + abnormal.getEquipmentGroupName() + "</td>");
            mailStr.append("<td>" + abnormal.getEquipmentName() + "</td>");
            mailStr.append("<td>" + abnormal.getSipecification() + "</td>");
            mailStr.append("<td>" + abnormal.getCreater() + "</td>");

            mailStr.append("<td>" + abnormal.getEngineer() + "</td>");
            mailStr.append("<td>" + abnormal.getSolutions() + "</td>");
            mailStr.append("<td>" + abnormal.getExpectedTime() + "</td>");
            mailStr.append("<td>" + abnormal.getActualTime() + "</td>");
            mailStr.append("<td>" + ( ("0").equals(abnormal.getIfCompleted()) ? "否":"是") + "</td>");
            mailStr.append("<td>" + abnormal.getMemo() + "</td>");

            mailStr.append("</tr>");

            if(mails.length>0){
                mailService.sendHtmlMails("EPRM@ivo.com.cn", mails,"常务设备妥善率管理系统:设备异常delay提醒", mailStr.toString());
            }
        }

    }

    /**根据工号，从org系统中获取员工邮件地址邮件**/
    private static String getEmployeeMailAddress(String employeeID) {
        String param = "eid="+employeeID;
        String em = HttpRequest.sendPost("http://10.20.2.10:8080/org/org/getEmployee", param);
        ObjectMapper mapper = new ObjectMapper();
        HashMap map = null;
        try{
            map = mapper.readValue(em,HashMap.class);
        } catch (IOException e){
            e.printStackTrace();
        }
        String mailAdress = (String) map.get("email");
        if(mailAdress!=null && !("").equals(mailAdress)){
            return mailAdress;
        } else {
            return null;
        }
    }
}