package cn.wltc.biz.weixin.service;

import cn.wltc.biz.weixin.manager.WeChatManager;
import cn.wltc.biz.weixin.util.HttpKit;
import cn.wltc.common.enums.WeChatConstant;

/***
 * 公众平台分组管理
 * @author xieyun
 *
 */
public class WeChatGroupService implements WeChatConstant{	 
	 
	 /***
	  * 创建分组
	  * @return 返回执行结果
	  */
	 public String createGroup(String createGroupName){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.post(CREATE_GROUP_URL.concat(accessToken),"{\"group\":{\"name\":\"" + createGroupName + "\"}}");
		 return reslut;
	 }
	
	 /***
	  * 获取所有分组
	  * @return 返回执行结果
	  */
	 public String getAllGroup(){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.get(GET_ALL_GROUP_URL.concat(accessToken),null);
		 return reslut;
	 }
	 
	 /***
	  * 查询用户所在分组
	  * @param openid 用户openid
	  * @return
	  */
	 public String getUserGroup(String openid){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.post(GET_USER_GROUP_URL.concat(accessToken),"{\"openid\":\""+openid+"\"}");
		 return reslut; 
	 }
	 
	 /***
	  * 修改分组名
	  * @return
	  */
	 public String modifyGroup(String groupId,String newGroupName){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.post(MODIFY_GROUP_URL.concat(accessToken),"{\"group\":{\"id\":"+groupId+",\"name\":"+newGroupName+"}}");
		 return reslut;
	 }
	 
	 /***
	  * 移动用户分组
	  * @return
	  */
	 public String modifyUserGroup(String openid,String toGroupid){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.post(MODIFY_USER_GROUP_URL.concat(accessToken),"{\"openid\":\""+openid+"\",\"to_groupid\":"+"+toGroupid+"+"}");
		 return reslut;
	 }
	 
	 /***
	  * 获取关注者列表(这里只是第一次获取，第二次需要传递next_openid)
	  * @return
	  */
	 public String getFollowersList(){
		 String accessToken = WeChatManager.getAccessToken();//获取accessToken
		 String reslut = HttpKit.get(GET_FOLLOWERS_LIST_URL.concat(accessToken),null);
		 return reslut;
	 }
}
