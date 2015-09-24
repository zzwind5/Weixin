package cn.wltc.biz.system.service;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import cn.wltc.biz.system.dao.SysLogDao;
import cn.wltc.biz.system.model.SysLog;
import cn.wltc.common.service.BaseService;
import cn.wltc.framework.util.network.IPRequest;
import cn.wltc.security.UserContextHelper;

@Service("SysLogService_")
public class SysLogService extends BaseService<SysLog, java.lang.Integer> {

    public static final String  LOGIN ="0";
    public static final String  ADD ="1";
    public static final String  MODIFY ="2";
    public static final String  DELETE ="3";
    public static final String  LOGOUT ="4";
    public static final String  EXPORT ="5";
    
    @Autowired
    private SysLogDao logDao;

    /**
     * 操作日志管理
     * <p>
     * 记录用户和系统的操作日志，这个方法只用于未登陆时 比如登出，登陆失败
     * </p>
     * @param command 以下命令(待补充)
     * <ul>
     * <li>0:登录</li>
     * <li>1:添加</li>
     * <li>2:修改</li>
     * <li>3:删除</li>
     * <li>4:注销</li>
     * </ul>
     * @param remark 一个比较详细的描述，比如：<strong>增加用户,用户名：zhangsan</strong>
     * @param loginUsername 登陆用户名 
     */
    public  void log(String command, String remark,String loginUsername) {
        
        SysLog czrzb = new SysLog();
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();
        String remoteIp = IPRequest.getIpAddress(request);
        czrzb.setIpdz(remoteIp);
        czrzb.setYhm(loginUsername);
        czrzb.setCzlx(command);
        czrzb.setCznr(remark);
        czrzb.setCzsj(new Date());
        //czrzb.setMac(GetMacAddress.getMacAddress(remoteIp));//mac 不太准确 不同网段，路由器上网 不准确，先不插入 耗时
        czrzb.setMac(null);
        czrzb.setJlcjsj(new Date());
        czrzb.setJlcjyh(loginUsername);
        czrzb.setJlxgsj(new Date());
        czrzb.setJlxgyh(loginUsername);
        logDao.saveSelective(czrzb);
    }
    
    /**
     * 操作日志管理
     * <p>
     * 记录用户和系统的操作日志。
     * </p>
     * @param command 以下命令(待补充)
     * <ul>
     * <li>0:登录</li>
     * <li>1:添加</li>
     * <li>2:修改</li>
     * <li>3:删除</li>
     * <li>4:注销</li>
     * </ul>
     * @param remark 一个比较详细的描述，比如：<strong>增加用户,用户名：zhangsan</strong>
     */    
    public  void log(String command, String remark) {
        String username = "";
        Subject currentUser = SecurityUtils.getSubject();
        if (currentUser == null){ // may be not login uer or batch task;
            username = "system";//??
        } else {
            username = UserContextHelper.getCurrentYhm();
        }        
        log(command,remark,username);
    }
    
    @Override
    protected SysLogDao getEntityDao() {
        return this.logDao;
    }
}
