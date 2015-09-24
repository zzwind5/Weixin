/**
 * 微信公众平台开发模式(JAVA) SDK
 * (c) 2012-2013 ____′↘夏悸 <wmails@126.cn>, MIT Licensed
 * http://www.jeasyuicn.com/wechat
 */
package cn.wltc.biz.weixin.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import cn.wltc.biz.weixin.util.HttpKit;
import cn.wltc.biz.weixin.util.SHA1;
import cn.wltc.common.enums.WeChatConstant;

@Repository("OauthWeChatService_")
public class OauthWeChatService implements WeChatConstant {

	@Value("${wechat.appid}")  private String wechatAppid;
	@Value("${wechat.appsecret}")  private String wechatAppsecret;
	@Value("${wechat.paySignKey}") private String wechatPaySignKey;
	@Value("${wechat.redirect_uri}") private String wechatRedirectURL;
	@Value("${wechat.partnerKey}") private String wechatPartnerKey;
	@Value("${wechat.partnerId}") private String wechatPartnerId;
	@Value("${wechat.notify_url}") private String wechatNotifyURL;

    /**
     * 请求code
     * @return
     */
    public String getCode() {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wechatAppid);
        params.put("response_type", "code");
        params.put("redirect_uri",wechatRedirectURL);
        params.put("scope", "snsapi_base"); // snsapi_base（不弹出授权页面，只能拿到用户openid）snsapi_userinfo
        // （弹出授权页面，这个可以通过 openid 拿到昵称、性别、所在地）
        params.put("state", "wx#wechat_redirect");
        String para = createSign(params, false);
        return CODE_URI + "?" + para;
    }

    /**
     * 通过code 换取 access_token
     * @param code
     * @return
     */
    public String getToken(String code) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wechatAppid);
        params.put("secret", wechatAppsecret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        return HttpKit.get(TOKEN_URI, params);
    }

    /**
     * 刷新 access_token
     * @param refreshToken
     * @return
     */
    public String getRefreshToken(String refreshToken) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("appid", wechatAppid);
        params.put("grant_type", "refresh_token");
        params.put("refresh_token", refreshToken);
        return HttpKit.get(REFRESH_TOKEN_URI, params);
    }

    /**
     * 拉取用户信息
     * @param accessToken
     * @param openid
     * @return
     */
    public String getUserInfo(String accessToken, String openid) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("access_token", accessToken);
        params.put("openid", openid);
        return HttpKit.get(USER_INFO_URI, params);
    }
    /**
     * 参与 paySign 签名的字段包括：appid、timestamp、noncestr、package 以及 appkey。
     * 这里 signType 并不参与签名微信的Package参数
     * @param params
     * @return
     */
    public  String getPackage(Map<String, String> params) {
        // 公共参数
        params.put("bank_type", "WX");
        params.put("attach", "yongle");
        params.put("partner", wechatPartnerId);
        params.put("notify_url", wechatNotifyURL);
        params.put("input_charset", "UTF-8");
        return packageSign(params, wechatPartnerKey);
    }

    /**
     * 构造签名
     * @param params
     * @param encode
     * @return
     */
    public static String createSign(Map<String, String> params, boolean encode) {
        Set<String> keysSet = params.keySet();
        Object[] keys = keysSet.toArray();
        Arrays.sort(keys);
        StringBuffer temp = new StringBuffer();
        boolean first = true;
        for (Object key : keys) {
            if (first) {
                first = false;
            } else {
                temp.append("&");
            }
            temp.append(key).append("=");
            Object value = params.get(key);
            String valueString = "";
            if (null != value) {
                valueString = value.toString();
            }
            if (encode) {
                try {
                    temp.append(URLEncoder.encode(valueString, "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            } else {
                temp.append(valueString);
            }
        }
        return temp.toString();
    }

    /**
     * 构造package, 这是我见到的最草蛋的加密，尼玛文档还有错
     * @param params
     * @param paternerKey
     * @return
     */
    private static String packageSign(Map<String, String> params,String paternerKey) {
        String string1 = createSign(params, false);
        String stringSignTemp = string1 + "&key=" + paternerKey;
        String signValue = DigestUtils.md5Hex(stringSignTemp).toUpperCase();
        String string2 = createSign(params, true);
        return string2 + "&sign=" + signValue;
    }

    /**
     * 支付签名
     * @param timestamp
     * @param noncestr
     * @param packages
     * @return
     */
    public String paySign(String timestamp, String noncestr,String packages) {
        Map<String, String> paras = new HashMap<String, String>();
        paras.put("appid", wechatAppid);
        paras.put("timestamp", timestamp);
        paras.put("noncestr", noncestr);
        paras.put("package", packages);
        paras.put("appkey", wechatPaySignKey);
        // appid、timestamp、noncestr、package 以及 appkey。
        String string1 = createSign(paras, false);
        String paySign = SHA1.encode(string1);
        return paySign;
    }

	public String getWechatAppid() {
		return wechatAppid;
	}

	public void setWechatAppid(String wechatAppid) {
		this.wechatAppid = wechatAppid;
	}

	public String getWechatAppsecret() {
		return wechatAppsecret;
	}

	public void setWechatAppsecret(String wechatAppsecret) {
		this.wechatAppsecret = wechatAppsecret;
	}

	public String getWechatPaySignKey() {
		return wechatPaySignKey;
	}

	public void setWechatPaySignKey(String wechatPaySignKey) {
		this.wechatPaySignKey = wechatPaySignKey;
	}

	public String getWechatRedirectURL() {
		return wechatRedirectURL;
	}

	public void setWechatRedirectURL(String wechatRedirectURL) {
		this.wechatRedirectURL = wechatRedirectURL;
	}

	public String getWechatPartnerKey() {
		return wechatPartnerKey;
	}

	public void setWechatPartnerKey(String wechatPartnerKey) {
		this.wechatPartnerKey = wechatPartnerKey;
	}

	public String getWechatPartnerId() {
		return wechatPartnerId;
	}

	public void setWechatPartnerId(String wechatPartnerId) {
		this.wechatPartnerId = wechatPartnerId;
	}

	public String getWechatNotifyURL() {
		return wechatNotifyURL;
	}

	public void setWechatNotifyURL(String wechatNotifyURL) {
		this.wechatNotifyURL = wechatNotifyURL;
	}
}
