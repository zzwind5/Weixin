
var _imgCdn = "";
var _hotDomain = "/";

var $ = function (id) { return document.getElementById(id); };
var _g = function (url) { location.href = url; };
var _e = { _add: function (o, e, f) { o.addEventListener ? o.addEventListener(e, f, false) : o.attachEvent("on" + e, f); }, _del: function (o, e, f) { o.removeEventListener ? o.removeEventListener(e, f, false) : o.detachEvent("on" + e, f); } };

var dataForShare = {
    weixin_icon: _imgCdn + "images/other_weixin_msg.png",
    weixin_tl_icon: _imgCdn + "images/other_weixin_tl.png",
    weixin_url: "http://www.happylinli.com/m/hot.aspx",
    qq_icon: _imgCdn + "images/other_qq.png",
    weibo_icon: _imgCdn + "images/other_weibo.png",
    url: "http://www.happylinli.com/m/hot.aspx",
    title: "邀好友一起来投票... 快速发起搞笑测试，找乐子就上街米互动(客户端10月底上线)",
    description: "邀好友一起来投票... 快速发起搞笑测试，找乐子就上“街米互动”,客户端版本10月底上线，分享有礼，大奖不断",
    sms: "邀好友一起来投票... 快速发起搞笑测试，找乐子就上街米互动(客户端10月底上线)",
    appId: "wxc0883d8d9f9e321b",
    callback: function () { _$(_api._shareCount, "info_id=0&info_type=other"); }
};

//隐藏微信标题栏
(function () { _e._add(document, "WeixinJSBridgeReady", function onBridgeReady() { WeixinJSBridge.call('hideToolbar'); WeixinJSBridge.call('showOptionMenu'); }); })();

var _link = {
    _appIntro: "http://www.happylinli.com/m/hot.aspx",
    _appDownload: "/download?from=detail",
    _downloadGuide: "/download_guide",
    _followMp: "",
    _hot: "/m/hot.aspx",
    _post: "/m/index.aspx",
    _login: "/m/login.aspx",
    _loginWithWeixin: "/go_wechat_oauth",
    _loginWithQq: "/go_qq_oauth",
    _loginWithWeibo: "/go_weibo_oauth",
    _loginWithWeixinMp: "/login_mp.html",
    _loginBackDefault: "/m/index.aspx",
    _alipay: "/alipay_submit",
    _showMap: "/show_map",
    _checkOrder: "/pay_check_pay_order"
};
var _api = {
    _getQr: "/get/api:qr",
    _shareCount: "/post/api:7",
    _report: "/post/api:10",
    _review: "/post/api:11",
    _reviewList: "/post/api:12",
    _vote: "/post/api:13",
    _joinParty: "/m/ajax.aspx?type=255",
    _partyJoinList: "/post/api:19",
    _joinRecruit: "/post/api:40",
    _recruitJoinList: "/post/api:41",
    _sign: "/post/api:14",
    _signatureList: "/post/api:15",
    _answer: "/post/api:16",
    _answerList: "/post/api:17",
    _login: "/m/ajax.aspx?type=2",
    _getCode: "/post/api:4",
    _checkCode: "/post/api:3",
    _reset: "/post/api:21",
    _reset_from_phone: "/post/api:5",
    _register: "/post/api:1",
    _loginWithMp: "/post/api:6",
    _hotList: "/post/api:22",
    _TimelineList: "/post/api:23",
    _getQr2: "/get/api:qr2",
    _loginWithQr: "/post/api:24",
    _feedback: "/post/api:35",
    _like: "/post/api:36",
    _likeList: "/get/api:37",
    _checkInviteCode: "/get/api:45",
    _checkOrder: "/get/api:46"

};
var _ = {
    _trim: function (text) {
        return text.replace(/(^\s*)|(\s*$)/g, "");
    },
    _len: function (text) {
        return text.replace(/[^\x00-\xff]/g, "aa").length;
    },
    _encode: function (text) {
        return escape(encodeURIComponent(text));
    },
    _htmlencode: function (text) {
        return text.replace(/\'/g, "&#39;")
			.replace(/\"/g, "&quot;")
			.replace(/</g, "&lt;")
			.replace(/>/g, "&gt;")
			.replace(/ /g, "&nbsp;")
			.replace(/\n\r/g, "<br>")
			.replace(/\r\n/g, "<br>")
			.replace(/\n/g, "<br>");
    },
    _zero: function (n) {
        return n < 0 ? 0 : n;
    },
    _isArray: function (o) {
        return Object.prototype.toString.call(o) === '[object Array]';
    },
    _client: function () {
        return { w: document.documentElement.scrollWidth, h: document.documentElement.scrollHeight, bw: document.documentElement.clientWidth, bh: document.documentElement.clientHeight };
    },
    _scroll: function () {
        return { x: document.documentElement.scrollLeft + document.body.scrollLeft, y: document.documentElement.scrollTop + document.body.scrollTop };
    },
    _show: function (id) {
        $(id).style.display = "block";
    },
    _hide: function (id) {
        $(id).style.display = "none";
    },
    _isHide: function (id) {
        return $(id).style.display != "block";
    },
    _opacity: function (id, p) {
        var _s = $(id).style;
        if ("opacity" in _s) {
            _s.opacity = p;
        } else if ("MozOpacity" in _s) {
            _s.MozOpacity = p;
        } else if ("filter" in _s) {
            _s.filter = "alpha(opacity=" + p * 100 + ")";
        }
    },
    _center: function (id) {
        with ($(id)) {
            style.top = _._zero(_._client().bh - offsetHeight) / 2 + "px";
            style.left = _._zero(_._client().bw - offsetWidth) / 2 + "px";
        }
    },
    _evCancel: function (event) { var _ev = window.event || event; _ev.cancelBubble = true; },
    _checkCss: function (css) {
        var _div = document.createElement("div");
        if (css in _div.style) return true;
        css = css.replace(/^[a-z]/, function (val) { return val.toUpperCase(); });
        var _vendors = ["O", "Moz", "Webkit"];
        for (var i = 0, len = _vendors.length; i < len; i++) {
            if (_vendors[i] + css in _div.style) { return true; break; }
        }
        return false;
    }
};

var _URICode = {
    _Str2Hex: function (text) {
        var _c = "";
        var _n;
        var _s = "0123456789ABCDEF";
        var _digS = "";
        for (var i = 0, _len = text.length; i < _len; i++) {
            _c = text.charAt(i);
            _n = _s.indexOf(_c);
            _digS += _URICode._Dec2Dig(eval(_n));
        }
        return _digS;
    },
    _Dec2Dig: function (n1) {
        var _s = "";
        var _n2 = 0;
        for (var i = 0; i < 4; i++) {
            _n2 = Math.pow(2, 3 - i);
            if (n1 >= _n2) {
                _s += "1";
                n1 = n1 - _n2;
            } else {
                _s += "0";
            }
        }
        return _s;
    },
    _Dig2Dec: function (s) {
        var _retV = 0;
        if (s.length == 4) {
            for (var i = 0; i < 4; i++) {
                _retV += eval(s.charAt(i)) * Math.pow(2, 3 - i);
            }
            return _retV;
        }
        return -1;
    },
    _Hex2Utf8: function (s) {
        var _retS = "";
        var _tempS = "";
        var _s = "";
        if (s.length == 16) {
            _tempS = "1110" + s.substring(0, 4);
            _tempS += "10" + s.substring(4, 10);
            _tempS += "10" + s.substring(10, 16);
            var __s = "0123456789ABCDEF";
            for (var i = 0; i < 3; i++) {
                _retS += "%";
                _s = _tempS.substring(i * 8, (eval(i) + 1) * 8);
                _retS += __s.charAt(_URICode._Dig2Dec(_s.substring(0, 4)));
                _retS += __s.charAt(_URICode._Dig2Dec(_s.substring(4, 8)));
            }
            return _retS;
        }
        return "";
    },
    _deCode: function (text) {
        text = text.replace(/\·/g, " ");
        var _text = escape(text);
        var _t = _text.split("%");
        var _v = "";
        if (_t[0] != "") { _v = _t[0]; }
        for (var i = 1, _len = _t.length; i < _len; i++) {
            if (_t[i].substring(0, 1) == "u") {
                _v += _URICode._Hex2Utf8(_URICode._Str2Hex(_t[i].substring(1, 5)));
                if (_t[i].length >= 6) {
                    _v += _t[i].substring(5);
                }
            } else {
                _v += "%" + _t[i];
            }
        }
        return _v;
    }
};
var _scroll = {
    _getTop: function (id) {
        var _o = $(id);
        var _y = 0;
        while (_o.offsetParent) {
            _y += _o.offsetTop;
            _o = _o.offsetParent;
        }
        return _y;
    },
    _to: function (y) {
        var _clientHeight = _._client().h;
        y = _clientHeight > y ? y : _clientHeight;
        window.scrollTo(0, y);
    }
};
var _t = {
    _set: function (key, value) {
        if (window.localStorage) { localStorage[key] = value; }
    },
    _get: function (key) {
        return window.localStorage ? (localStorage[key] || "") : "";
    }
};
var _download = function (link) {
    if (_user._useAndroid() && _user._inWeixin()) {
        setTimeout(function () {
            _g(_link._downloadGuide);
        }, 500);
    }
    _g(link);
};
var _download = function (link) {
    if (_user._useAndroid() && _user._inWeixin()) {
        setTimeout(function () {
            _g(_link._downloadGuide);
        }, 500);
    }
    _g(link);
};
var _cover = {
    _flag: false,
    _resize: function (id) {
        $(id).style.width = (_._client().w > _._client().bw ? _._client().w : _._client().bw) + "px";
        $(id).style.height = (_._client().h > _._client().bh ? _._client().h : _._client().bh) + "px";
    },
    _resizeAll: function () {
        if ($("cover2")) { _cover._resize("cover2"); }
        _cover._resize("cover");
    },
    _show: function (id) {
        _cover._flag = true;
        _._show(id);
        if (_user._useIOs()) {
            _cover._resizeAll();
            _e._add(window, "resize", _cover._resizeAll);
            _e._add(window, "scroll", _cover._resizeAll);
        } else {
            with ($(id).style) {
                position = "fixed";
                width = "100%";
                height = "100%";
            }
        }
    },
    _hide: function (id) {
        _._hide(id);
        if (($("cover2") && !_._isHide("cover2")) || !_._isHide("cover")) { return; };
        _cover._flag = false;
        if (!_user._useIOs()) { return; }
        _e._del(window, "resize", _cover._resizeAll);
        _e._del(window, "scroll", _cover._resizeAll);
    }
};


var _placeholder = {
    _support: function () {
        return "placeholder" in document.createElement("input");
    },
    _add: function (o) {
        var _ph = o.getAttribute("placeholder") || "";
        if (_ph == "" || o.getAttribute("noplaceholder")) { return; }
        var _holder = document.createElement("div");
        _holder.className = "form_placeholder";
        _holder.innerHTML = _ph;
        o.parentNode.parentNode.insertBefore(_holder, o.parentNode);
        o.parentNode.style.marginTop = "0";
    },
    _init: function (formId) {
        if (_placeholder._support()) { return; }
        var _input = $(formId).getElementsByTagName("input");
        for (var i = 0, _len = _input.length; i < _len; i++) {
            _placeholder._add(_input[i]);
        }
        var _textarea = $(formId).getElementsByTagName("textarea");
        for (var i = 0, _len = _textarea.length; i < _len; i++) {
            _placeholder._add(_textarea[i]);
        }
    }
};
var _pic = {
    _srcs: [],
    _getWidth: function (o) {
        with (new Image()) {
            src = o.src;
            return parseInt(width);
        }
    },
    _resizeAll: function () {
        var _img = $("dt_content").getElementsByTagName("img"),
		_parentWidth = $("dt_content").offsetWidth - 22;
        for (var i = 0, len = _img.length; i < len; i++) {
            if (_img[i].parentNode.className == "dt_content_pic") {
                var _imgWidth = _pic._getWidth(_img[i]);
                if (_imgWidth > _parentWidth) {
                    _img[i].style.width = _parentWidth + "px";
                } else {
                    _img[i].style.width = _imgWidth + "px";
                }
            }
        }
    },
    _resize: function (o) {
        var _parentWidth = o.parentNode.offsetWidth;
        var _imgWidth = _pic._getWidth(o);
        if (_imgWidth > _parentWidth) {
            o.style.width = _parentWidth + "px";
        } else {
            o.style.width = _imgWidth + "px";
        }
    },
    _preview: function (src) {
        if (_user._inHudongba()) {
            HudongbaJsBridge["showPicPreview"](src, _pic._srcs.join("|"));
            return;
        }
        if (typeof window.WeixinJSBridge != 'undefined') {
            WeixinJSBridge.invoke('imagePreview', {
                'current': src,
                'urls': _pic._srcs
            });
        }
    },
    _srcInit: function (img) {
        img.src = img.getAttribute("data-src");
        img.setAttribute("data-src", "");
        img.parentNode.style.display = "block";
        img.onload = function () { _pic._resize(this); };
    },
    _previewInit: function (img) {
        _pic._srcs.push(img.src);
        img.onclick = function () { _pic._preview(this.src); };
    },
    _init: function (type) {
        setTimeout(function () {
            if (type == 1) {
                if (!$("dt_content")) { return; }
                var _img = $("dt_content").getElementsByTagName("img");
                for (var i = 0, len = _img.length; i < len; i++) {
                    if (_img[i].parentNode.className == "dt_content_pic") {
                        _pic._srcInit(_img[i]);
                        _pic._previewInit(_img[i]);
                    }
                }
                _e._add(window, "resize", _pic._resizeAll);
            } else {
                if (!$("dt_pic")) { return; }
                _pic._srcInit($("dt_img"));
                _pic._previewInit($("dt_img"));
                _e._add(window, "resize", function () { _pic._resize($("dt_img")); });
            }
        }, 1000);
    }
};
var _login = {
    _center: function () {
        _._center("login");
    },
    _show: function () {
        _cover._show("cover");
        _._show("login");
        $("cover").onclick = _login._hide;
        _login._center();
        _e._add(window, "resize", _login._center);
    },
    _hide: function () {
        _cover._hide("cover");
        _._hide("login");
        $("cover").onclick = null;
        _e._del(window, "resize", _login._center);
    }
};

var _toast = {
    _center: function () {
        $("toast").style.left = _._zero(_._client().bw - $("toast").offsetWidth) / 2 + "px";
        $("toast").style.bottom = "80px";
    },
    _show: function (text, fun) {
        _._show("toast");
        $("toast").innerHTML = text;
        _toast._center();
        _e._add(window, "resize", _toast._center);
        setTimeout(function () {
            _toast._hide(fun);
        }, 3 * 1000);
    },
    _hide: function (fun) {
        _._hide("toast");
        _e._del(window, "resize", _toast._center);
        if (fun) { (fun)(); }
    }
};
var _alert = {
    _center: function () {
        _._center("alert");
    },
    _show: function (title, text, buttonText, fun, cancelText) {
        _cover._show("cover");
        if (title != "") {
            $("alert_title").innerHTML = title;
            _._show("alert_title");
        } else {
            _._hide("alert_title");
        }
        $("alert_text").innerHTML = text;
        $("alert_button_ok").innerHTML = buttonText;
        $("alert_button_ok").onclick = function () {
            _alert._hide();
            if (fun) { (fun)(); }
        }
        if (cancelText) {
            $("alert_button_ok").className = "button_1";
            _._show("alert_cancel");
            $("alert_cancel").innerHTML = cancelText;
        }
        _._show("alert");
        _alert._center();
        _e._add(window, "resize", _alert._center);
        $("cover").onclick = _alert._hide;
    },
    _hide: function () {
        _cover._hide("cover");
        _._hide("alert");
        $("cover").onclick = null;
        _e._del(window, "resize", _alert._center);
    }
};
var _loading = {
    _center: function () {
        _._center("loading_center");
    },
    _show: function (text) {
        _cover._show("cover");
        $("loading_center_text").innerHTML = text;
        _._show("loading_center");
        _loading._center();
        _e._add(window, "resize", _loading._center);
    },
    _hide: function () {
        _cover._hide("cover");
        _._hide("loading_center");
        _e._del(window, "resize", _loading._center);
    }
};
var _loadingBottom = {
    _init: function (text, fun) {
        $("loading_bottom").innerHTML = text;
        $("loading_bottom").onclick = fun;
        _._show("loading_bottom");
    },
    _noMore: function (text) {
        $("loading_bottom").innerHTML = text;
        $("loading_bottom").onclick = null;
        _._show("loading_bottom");
    },
    _loading: function () {
        $("loading_bottom").innerHTML = "<img src='" + _imgCdn + "images/loading.gif'>&nbsp;加载中…";
        $("loading_bottom").onclick = null;
    },
    _hide: function () {
        _._hide("loading_bottom");
    }
};
var _sucess = {
    _center: function () {
        _._center("sucess");
    },
    _show: function (text, fun) {
        _cover._show("cover");
        $("sucess_text").innerHTML = text;
        _._show("sucess");
        _sucess._center();
        _e._add(window, "resize", _sucess._center);
        setTimeout(function () {
            _sucess._hide();
            if (fun) { (fun)(); }
        }, 2 * 1000);
    },
    _hide: function () {
        _cover._hide("cover");
        _._hide("sucess");
        _e._del(window, "resize", _sucess._center);
    }
};
var _pay = {
    _orderId: "",
    _clickTrouble: false,
    _pay: function (orderId) {
        _pay._orderId = orderId;
        if (_user._inHudongba() && !_user._useLowHudongba()) {
            HudongbaJsBridge["getPayPage"](orderId);
        } else if (!_user._inMobile()) {
            _dtSucess._hide();
            _payGuide._show();
            $("dt_pay_form").action = _link._alipay;
            $("dt_pay_form_order_id").value = orderId;
            $("dt_pay_form").submit();
        } else {
            _g(_link._alipay + "?order_id=" + orderId);
        }
    },
    _paid: function () {
        with ($("dt_join_post")) {
            innerHTML = "已报名，已付款";
            onclick = null;
            className = "button_8";
        }
        _dtSucess._hide();
        _joinBar._del();
    },
    _trouble: function () {
        _pay._clickTrouble = true;
        _pay._check();
    },
    _check: function () {
        _loading._show("请稍候");
        _$(_api._checkOrder, "order_id=" + _pay._orderId, _pay._checkOk);
    },
    _checkOk: function (code, json) {
        _loading._hide();
        if (code != 200) { _toast._show("网络错误，请稍后重试"); return; }
        if (json.state.toString() != "0") {
            if (json.state.toString() == "1") {
                _pay._rePay();
                return;
            }
            _toast._show(json.error, function () {
                _user._error(json.state, "review");
            });
            return;
        }
        _payGuide._hide();
        _pay._paid();
        if (_pay._clickTrouble == true) {
            _sucess._show("你已支付");
        }
    },
    _rePay: function () {
        _payGuide._hide();
        _alert._show("", "你当前没有支付成功", "重新付款", function () { _alert._hide(); _pay._pay(_pay._orderId); }, "取消");
    }
};
var _payGuide = {
    _center: function () {
        _._center("dt_pay_guide");
    },
    _show: function () {
        _cover._show("cover2");
        _._show("dt_pay_guide");
        _payGuide._center();
        _e._add(window, "resize", _payGuide._center);
    },
    _hide: function () {
        _cover._hide("cover2");
        _._hide("dt_pay_guide");
        _e._del(window, "resize", _payGuide._center);
    }
};
var _dtSucess = {
    _center: function () {
        _._center("dt_sucess");
    },
    _show: function (text, orderId, orderMoney) {
        if (_user._inHudongba() && (!orderId || orderId == "0")) { _sucess._show(text, _share._joinSucess); return; }
        _cover._show("cover2");
        if (orderId && orderId != "0") {
            $("dt_sucess_text").innerHTML = text + "，请付款";
            $("dt_sucess_note").innerHTML = "付款金额：&yen;" + orderMoney;
            $("dt_sucess_button_1").firstChild.innerHTML = "去付款";
            $("dt_sucess_button_1").firstChild.onclick = function () { _pay._pay(orderId); };
            $("dt_sucess_button_cancel").innerHTML = "取消";
        } else {
            $("dt_sucess_text").innerHTML = text;
        }
        $("dt_sucess_button_cancel").onclick = function () {
            _dtSucess._hide();
            _share._joinSucess();
        }
        _._show("dt_sucess");
        _dtSucess._center();
        _e._add(window, "resize", _dtSucess._center);
    },
    _hide: function () {
        _cover._hide("cover2");
        _._hide("dt_sucess");
        _e._del(window, "resize", _dtSucess._center);
    }
};
var _followMp = function () {
    if (_user._inWeixin()) {
        _g(_link._followMp);
    } else {
        _alert._show("街米互动微信公众号", "关注请加微信公众号：街米互动<br><img src='" + _imgCdn + "images/qr_mp.png' width='180' height='190'>", "确定");
    }
};
var _$ = function (url, para, fun) {
    var _request = null; try { _request = new XMLHttpRequest(); } catch (e) { try { _request = new ActiveXObject("Msxml2.XMLHTTP"); } catch (e) { try { _request = new ActiveXObject("Microsoft.XMLHTTP"); } catch (e) { return; } } }
    var _method = "POST";
    if (para == "") { _method = "GET"; para = null; }
    _request.open(_method, url, true);
    _request.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    _request.onreadystatechange = function () {
        if (_request.readyState == 4) {
            if (_request.status == 200) {
                if (fun) {
                    try {
                        var json = eval("(" + _request.responseText + ")");
                        (fun)(200, json);
                    } catch (e) { }
                }
            } else {
                if (fun) { (fun)(_request.status); }
            }
        }
    };
    _request.send(para);
};
var _cookie = {
    _set: function (name, value, expires) {

        var Days = 30;
        var exp = new Date();
        exp.setTime(exp.getTime() + Days * 24 * 60 * 60 * 1000);
        document.cookie = name + "=" + escape(value) + ";expires=" + exp.toGMTString();
    },
    _get: function (name) {
        var _cookie = document.cookie;
        var _start = _cookie.indexOf(name + "=");
        if (_start != -1) {
            _start += name.length + 1;
            var _end = _cookie.indexOf(";", _start);
            if (_end == -1) { _end = _cookie.length; }
            return unescape(_cookie.substring(_start, _end));
        }
        return "";
    }
};
var _user = {
    _id: function () { return _cookie._get("W_U_L_I"); },
    _login: function () { return _user._id() != ""; },
    _loginWithSnp: function (snp) {
        switch (snp) {
            case "weixin":
                _g(_user._inWeixin() ? _link._loginWithWeixin : _link._loginWithWeixinMp);
                break;
            case "qq":
                _g(_link._loginWithQq);
                break;
            case "weibo":
                _g(_link._loginWithWeibo);
                break;
        }
    },
    _setLogin: function (i, s) {
        _cookie._set("W_U_L_I", i, 60 * 60 * 24 * 365 * 10);
        _cookie._set("W_U_L_S", s, 60 * 60 * 24 * 365 * 10);
    },
    _error: function (state, type) {
        switch (state.toString()) {
            case "1004":
            case "1006":
            case "2310":
            case "2509":
            case "2709":
            case "2809":
            case "3014":
            case "5303":
            case "5910":
                _user._toLogin();
                break;
            case "2304":
            case "2504":
            case "2704":
                _beforeLogin._mark("");
                break;
            case "2804":
                _option._setTemp("");
                break;
            case "5304":
                _likeBeforeLogin._mark("");
                break;
        }
    },
    _toLogin: function () {
        if (_user._inHudongba()) {
            HudongbaJsBridge["showLoginPage"]();
            return;
        }

        _cookie._set("W_U_L_B_U", location.href.toString());
        if (_user._inWeixin() || _user._inQq()) { _login._show(); return; }
        _g(_link._login);
    },
    _loginBack: function () {

        _g(_cookie._get("W_U_L_B_U") == "" ? _link._loginBackDefault : _cookie._get("W_U_L_B_U"));
    },
    _toLogout: function () {
        if (window.localStorage) { window.localStorage.clear(); }
        _cookie._set("W_U_L_I", "");
        _cookie._set("W_U_L_S", "");
        _cookie._set("W_U_L_B_U", location.href.toString());
        if ($("post_menu")) {
            _postMenu._hide(function () { _g(_link._login); });
        } else {
            _g(_link._login);
        }
    },
    _inWeixin: function () {
        return navigator.userAgent.match(/micromessenger/i) != null;
    },
    _inQq: function () {
        return /(iPad|iPhone|iPod).*?QQ/g.test(navigator.userAgent) || /\bV1_AND_SQ_/.test(navigator.userAgent);
    },
    _inHudongba: function () {
        return navigator.userAgent.match(/jootun\.hudongba/i) != null;
    },
    _inMobile: function () {
        return "ontouchstart" in window;
    },
    _useIOs: function () {
        return navigator.userAgent.match(/ipad|iphone|ipod/i) != null;
    },
    _useAndroid: function () {
        return navigator.userAgent.match(/android/i) != null;
    },
    _useLowWeixin: function () {
        var _v = parseFloat(navigator.userAgent.replace(/.*MicroMessenger\/(\d+)\.(\d+)\.\d+.*/i, "$1.$2"));
        return _v < 5.2;
    },
    _useLowHudongba: function () {
        var _v = parseFloat(navigator.userAgent.replace(/.*jootun\.hudongba\/(\d+)\.(\d+).*/i, "$1.$2"));
        return _v < 2.4;
    },
    _isappinstalled: function () {
        return _cookie._get("IS") == "1";
    },
    _init: function () {
        if (_user._inWeixin()) {
            if (location.href.toString().match(/isappinstalled=1/i) != null) { _cookie._set("IS", "1", 60 * 60 * 24 * 30); }
            $("login_button").innerHTML = "<button class='button_5' ontouchstart='' onclick='_g(_link._loginWithWeixin)'><img width='25' height='20' src='" + _imgCdn + "images/icon_weixin_2.png'>&nbsp;一键登录</button>";
        } else if (_user._inQq()) {
            $("login_button").innerHTML = "<button class='button_6' ontouchstart='' onclick='_g(_link._loginWithQq)'><img width='20' height='20' src='" + _imgCdn + "images/icon_qq.png'>&nbsp;QQ登录</button>";
        }
    }
};
if (_user._inHudongba() && _user._useIOs()) {
    var HudongbaJsBridge = {
        setData: function (pageTitle, weixinIcon, weixinTlIcon, weixinUrl, qqIcon, weiboIcon, url, title, description, sms, invite, infoId, infoType) {
            _g("hudongba://setData::" + _URICode._deCode(pageTitle) + "::" + weixinIcon + "::" + weixinTlIcon + "::" + weixinUrl + "::" + qqIcon + "::" + weiboIcon + "::" + url + "::" + _URICode._deCode(title) + "::" + _URICode._deCode(description) + "::" + _URICode._deCode(sms) + "::" + invite + "::" + infoId + "::" + infoType);
        },
        showSharePanel: function () {
            _g("hudongba://showSharePanel");
        },
        showLoginPage: function () {
            _g("hudongba://showLoginPage");
        },
        showPicPreview: function (pic, pics) {
            _g("hudongba://showPicPreview::" + pic + "::" + pics);
        },
        showReviewPanel: function (infoId, infoType, replyUid, replyUName) {
            _g("hudongba://showReviewPanel::" + infoId + "::" + infoType + "::" + replyUid + "::" + _URICode._deCode(replyUName));
        },
        getModifyPage: function (infoId, infoType, title) {
            _g("hudongba://getModifyPage::" + infoId + "::" + infoType + (title ? "::" + _URICode._deCode(title) : ""));
        },
        getPayPage: function (orderId) {
            _g("hudongba://getPayPage::" + orderId);
        }
    };
}
var _hot = {
    _addItem: function (json) {
        var _items = json.item;
        if (_items == null) { return; }
        for (var i = 0, _len = _items.length; i < _len; i++) {
            var _item = _items[i];
            var _div = document.createElement("div");
            with (_div) {
                className = "hot_item";
                ontouchstart = function () { };
                onclick = function () { _g("/" + _item.type + "/" + _item.id); };
                innerHTML = "<div class='hot_item_icon'><img src='" + _imgCdn + "images/icon_" + _item.type + "_list.png'></div><div class='hot_item_count'>" + _item.join + _hot._action(_item.type) + "</div><div class='hot_item_title'>" + _item.title + "</div><div class='clear'></div>";
            }
            $("dt_hot_main").insertBefore(_div, $("hot_item_1"));
        }
    },
    _action: function (infoType) {
        var _text = "查看";
        switch (infoType) {
            case "vote":
                _text = "投票";
                break;
            case "party":
            case "recruit":
                _text = "报名";
                break;
            case "signature":
                _text = "签名";
                break;
            case "question":
                _text = "回答";
                break;
        }
        return _text;
    },
    _init: function () {
        var _oHead = document.getElementsByTagName("HEAD")[0];
        var _oScript = document.createElement("script");
        _oScript.type = "text/javascript";
        _oScript.src = _hotDomain + "/search/search_json.jhtml?id=" + _info._id + "&type=" + _info._type + "&pageSize=2&total=10&currentPage=1&callback=_hot._addItem";
        _oHead.appendChild(_oScript);
    }
};
var _changeTop = function () {
    if (_user._useIOs()) {
        $("topbar").style.position = "absolute";
    }
};
var _joinForm = {
    _show: function () {
        _._hide("dt_button_join");
        _._show("dt_join_form");
        _placeholder._init("dt_join_form");
        var _height = $("dt_join_form").offsetHeight - 18;
        $("dt_join_form").style.height = "0";
        setTimeout(function () { $("dt_join_form").style.height = _height + "px"; }, 100);
        _changeTop();
    },
    _hide: function () {
        _._show("dt_button_join");
        $("dt_join_form").style.height = "0";
        setTimeout(function () {
            _._hide("dt_join_form");
        }, 400);
    }
};
var _beforeLogin = {
    _mark: function (value) {
        _t._set("before_login_" + _info._type + "_" + _info._id, value);
    },
    _continue: function (fun) {
        if (_t._get("before_login_" + _info._type + "_" + _info._id) != "") {
            (fun)();
        }
    }
};
var _reviewBeforeLogin = {
    _mark: function (value) {
        _t._set("review_before_login_" + _info._type + "_" + _info._id, value);
    },
    _continue: function () {
        if (_t._get("review_before_login_" + _info._type + "_" + _info._id) != "") {
            setTimeout(function () {
                _reviewBeforeLogin._post();
                _review._post();
            }, 500);
        }
    },
    _setTemp: function (key, value) {
        _t._set(key + "_" + _info._type + "_" + _info._id, value);
    },
    _getTemp: function (key) {
        return _t._get(key + "_" + _info._type + "_" + _info._id);
    },
    _post: function () {
        _review._replyUid = _reviewBeforeLogin._getTemp("review_replyUid");
        _review._questionId = _reviewBeforeLogin._getTemp("review_questionId");
        $("dt_review_form_content").setAttribute("placeholder", "继续评论/回复：");
        _reviewBox._show();
    }
};
var _backToRefresh = {
    _mark: function () {
        _t._set("join_sucess", "/" + _info._type + "/" + _info._id);
    },
    _should: function () {
        return _t._get("join_sucess") != "" && document.referrer.toString().indexOf(_t._get("join_sucess")) != -1;
    },
    _clear: function () {
        _t._set("join_sucess", "");
    }
};
var _likeBeforeLogin = {
    _mark: function (value) {
        _t._set("like_before_login_" + _info._type + "_" + _info._id, value);
    },
    _continue: function () {
        if (_t._get("like_before_login_" + _info._type + "_" + _info._id) != "") {
            _like._post();
            setTimeout(function () {
                _scroll._to(_scroll._getTop("dt_active") - 100);
            }, 500);
        }
    }
};
var _reviewBox = {
    _scroll: -1,
    _show: function (uName) {
        if (_info._type == "question" && _user._inHudongba()) { return; }
        if (_user._inHudongba()) {
            HudongbaJsBridge["showReviewPanel"](_info._id, _info._type, _review._replyUid, uName || "");
            return;
        }
        if (!_user._login() && !window.localStorage) { _user._toLogin(); return; }
        _cover._show("cover2");
        _._show("dt_review_box");
        _reviewBox._inputed();
        if (!_user._useIOs()) {
            $("dt_review_form_content").focus();
        }
        $("dt_review_form_content").value = _reviewBox._getTemp("review_content");
        $("cover2").onclick = _reviewBox._hide;
        _changeTop();
    },
    _hide: function () {
        _cover._hide("cover2");
        $("cover2").onclick = null;
        _._hide("dt_review_box");
        _emo._hide("dt_review_box_emo");
    },
    _post: function (questionId) {
        _reviewBox._scroll = _._scroll().y;
        _review._replyUid = 0;
        _review._questionId = questionId || "";
        $("dt_review_form_content").setAttribute("placeholder", "评论：");
        _reviewBox._show();
    },
    _rePost: function (uId, uName, questionId) {
        _reviewBox._scroll = _._scroll().y;
        _review._replyUid = uId;
        _review._questionId = questionId || "";
        $("dt_review_form_content").setAttribute("placeholder", "回复" + uName + "：");
        _reviewBox._show(uName);
    },
    _touch: function () {
        if (!_user._useIOs()) { return; }
        $("cover2").onclick = null;
        setTimeout(function () { $("cover2").onclick = _reviewBox._hide; }, 1000);
        _emo._hide("dt_review_box_emo");
        $("dt_review_form_content").focus();
    },
    _focus: function () {
        if (_user._useIOs()) { return; }
        _emo._hide("dt_review_box_emo");
        with ($("dt_review_form_content")) {
            var _value = value;
            focus();
            value = "";
            value = _value;
        }
    },
    _blur: function () {
        _reviewBox._setTemp("review_content", $("dt_review_form_content").value);
        _reviewBeforeLogin._setTemp("review_replyUid", _review._replyUid);
        _reviewBeforeLogin._setTemp("review_questionId", _review._questionId);
    },
    _inputed: function () {
        $("dt_review_form_post").className = _._trim($("dt_review_form_content").value) == "" ? "button_1_disabled" : "button_1";
    },
    _setTemp: function (key, value) {
        _t._set(key + "_" + _info._type + "_" + _info._id + "_" + _review._replyUid + "_" + _review._questionId, value);
    },
    _getTemp: function (key) {
        return _t._get(key + "_" + _info._type + "_" + _info._id + "_" + _review._replyUid + "_" + _review._questionId);
    },
    _showEmo: function () {
        _emo._show("dt_review_box_emo", function (index) {
            $("dt_review_form_content").value += _emo._text[index];
            _reviewBox._inputed();
            _reviewBox._blur();
        });
    }
};
var _emo = {
    _text: ["[笑]", "[感冒]", "[流泪]", "[发怒]", "[爱慕]", "[吐舌]", "[发呆]", "[可爱]", "[调皮]", "[寒]", "[呲牙]", "[闭嘴]", "[害羞]", "[苦闷]", "[难过]", "[流汗]", "[犯困]", "[惊恐]", "[咖啡]", "[炸弹]", "[西瓜]", "[爱心]", "[心碎]"],
    _indexOf: function (text) {
        if (_emo._text.indexOf) { return _emo._text.indexOf(text); }
        for (var i = 0, _len = _emo._text.length; i < _len; i++) {
            if (_emo._text[i] == text) { return i; }
        }
        return -1;
    },
    _insertFun: null,
    _show: function (id, fun) {
        _emo._insertFun = fun;
        if (!$(id).hasChildNodes()) {
            var _html = "<ul>";
            for (var i = 0; i < 23; i++) {
                _html += "<li class='emo' ontouchstart='' onclick='_emo._insert(" + i + ")'><img src='" + _imgCdn + "images/emo/" + (i + 1) + ".png'></li>";
            }
            _html += "</ul>";
            $(id).innerHTML = _html;
        }
        _._show(id);
    },
    _hide: function (id) {
        _._hide(id);
    },
    _insert: function (index) {
        (_emo._insertFun)(index);
    },
    _toCode: function (content) {
        return content.replace(/\[[\u4e00-\u9fa5]{1,2}\]/g, function (a) {
            var _code = _emo._indexOf(a) + 1;
            return _code == 0 ? a : "[/" + _code + "]";
        });
    }
};

var _reviewLoading = {
    _index: 1,
    _flag: false,
    _trigger: function () {
        if (_cover._flag == false && _reviewLoading._flag == false && _._client().h - _._client().bh - 100 <= _._scroll().y) {
            _reviewLoading._post();
        }
    },
    _init: function () {
        _reviewBeforeLogin._continue();
        _loadingBottom._init("加载评论", _reviewLoading._post);
        setTimeout(_reviewLoading._trigger, 50);
        _e._add(window, "scroll", _reviewLoading._trigger);
    },
    _post: function () {
        if (_reviewLoading._flag == true) { return; }
        _reviewLoading._flag = true;
        _loadingBottom._loading();
        _review._removeError();
        _$(_api._reviewList, "info_id=" + _info._id + "&info_type=" + _info._type + "&page_size=20&page_num=" + _reviewLoading._index, _reviewLoading._ok);
    },
    _ok: function (code, json) {
        _loadingBottom._hide();
        with ($("dt_review_main").style) { border = "1px solid #cccccc"; borderBottom = "0"; }
        if (code != 200 || (code == 200 && json.state.toString() != "0")) {
            var _item = document.createElement("div");
            with (_item) {
                id = "dt_review_error";
                className = "dt_review_item dt_review_error";
                ontouchstart = function () { };
                onclick = _reviewLoading._post;
                innerHTML = "网络错误，点击重新加载";
            }
            $("dt_review_main").appendChild(_item);
            return;
        }
        var _data = _._isArray(json.discuss) ? json.discuss : [], _len = _data.length;
        if (json.next_state.toString() == "0") {
            if (_len > 0) {
                _loadingBottom._noMore("已全部加载");
            } else {
                $("dt_review_main").innerHTML = "<div id='dt_review_error' class='dt_review_item dt_review_error'>还没有人发表评论</div>";
            }
            _e._del(window, "scroll", _reviewLoading._trigger);
        } else {
            _reviewLoading._flag = false;
            _reviewLoading._index++;
            _loadingBottom._init("加载更多", _reviewLoading._post);
        }
        if (_len == 0) { return; }
        for (var i = 0; i < _len; i++) {
            if (!$("dt_review_item_" + _data[i].id)) {
                var _item = _review._itemDom(_data[i].id, _data[i].userId, _data[i].userName, _data[i].replyUserId, _data[i].replyUserName, _data[i].date, _data[i].discussContent);
                $("dt_review_main").appendChild(_item);
            }
        }
    }
};

var _review = {
    _replyUid: "0", _questionId: "",
    _post: function () {
        if (!_user._login()) {
            _reviewBeforeLogin._mark("1");
            _user._toLogin();
            return;
        }
        var _content = _emo._toCode(_._trim($("dt_review_form_content").value));
        if (_content == "") { return; }
        if (_._len(_content) > 200) { _toast._show("评论请在100字以内"); return; }
        _loading._show("请稍候");
        _$(_api._review, "h_share_uid=" + _info._shareUid + "&info_id=" + _info._id + "&info_type=" + _info._type + "&replyUserid=" + _review._replyUid + "&join_question_id=" + _review._questionId + "&content=" + _._encode(_content), _review._ok);
    },
    _ok: function (code, json) {
        _loading._hide();
        if (code != 200) { _toast._show("网络错误，请稍后重试"); return; }
        if (json.state.toString() != "0") {
            _toast._show(json.error, function () {
                _user._error(json.state, "review");
            });
            return;
        }
        if ($("dt_review_main")) {
            _review._removeError();
            with ($("dt_review_main").style) { border = "1px solid #cccccc"; borderBottom = "0"; }
        }
        var _data = json.discuss_info;
        if (!$("dt_review_item" + _data.id)) {
            var _item = _review._itemDom(_data.id, _data.userId, _data.userName, _data.replyUserId, _data.replyUserName, _data.date, _data.discussContent, _data.joinQuestionId);
            if ($("dt_review_main")) {
                with ($("dt_review_main")) {
                    if (hasChildNodes()) {
                        insertBefore(_item, firstChild);
                    } else {
                        appendChild(_item);
                    }
                }
            } else {
                $("dt_answer_" + _data.joinQuestionId + "_review").appendChild(_item);
            }
            setTimeout(function () {
                _scroll._to(_reviewBox._scroll == -1 ? (_scroll._getTop("dt_review_item_" + _data.id) - 100) : _reviewBox._scroll);
            }, 500);
        }
        $("dt_review_form_content").value = "";
        _reviewBox._setTemp("review_content", "");
        _reviewBeforeLogin._setTemp("review_replyUid", "");
        _reviewBeforeLogin._setTemp("review_questionId", "");
        _reviewBeforeLogin._mark("");
        _backToRefresh._mark();
        _reviewBox._hide();
    },
    _okFromApp: function (json) {
        _review._ok(200, json);
    },
    _removeError: function () {
        if ($("dt_review_error")) {
            $("dt_review_error").parentNode.removeChild($("dt_review_error"));
            if (!$("dt_review_main").hasChildNodes()) { $("dt_review_main").style.border = "0"; }
        }
    },
    _itemDom: function (id, userId, userName, replyUserId, replyUserName, date, discussContent, questionId) {
        var _item = document.createElement("div");
        _item.id = "dt_review_item_" + id;
        _item.className = "dt_review_item";
        _item.ontouchstart = function () { };
        _item.onclick = function () { _reviewBox._rePost(userId, userName, questionId || null); };
        var _html = "<div class='dt_review_item_subinfo'><div class='right'>" + date + "</div><div class='left'><a class='dt_nick' ontouchstart='' onclick='_._evCancel(event)' href='/timeline/" + userId + "'>" + userName + "";
        if (replyUserId != "0") {
            _html += "</a>回复<a class='dt_nick' ontouchstart='' onclick='_._evCancel(event)' href='/timeline/" + replyUserId + "'>" + replyUserName + "";
        }
        _html += "：</a></div><div class='clear'></div></div><div class='dt_review_item_content'>" + discussContent + "</div>";
        _item.innerHTML = _html;
        return _item;
    }
};
var _like = {
    _index: 1,
    _post: function () {
        if (!_user._login()) {
            if (!_user._inHudongba()) { _likeBeforeLogin._mark("1"); }
            _user._toLogin();
            return;
        }
        _like._liked();
        _$(_api._like, "h_share_uid=" + _info._shareUid + "&info_type=" + _info._type + "&info_id=" + _info._id, _like._ok);
    },
    _ok: function (code, json) {
        if (code != 200) { _toast._show("网络错误，请稍后重试"); _like._likeFailed(); return; }
        if (json.state.toString() != "0") {
            _toast._show(json.error, function () {
                _user._error(json.state, "like");
            });
            return;
        }
        _backToRefresh._mark();
        _likeBeforeLogin._mark("");
        var _item = _like._itemDom(json.user.user_id, json.user.user_name);
        if (!$("dt_like_list_begin")) { _like._addBegin(); }
        if ($("dt_like_list_begin").nextSibling) {
            $("dt_like_list").insertBefore(_item, $("dt_like_list_begin").nextSibling);
        } else {
            $("dt_like_list").appendChild(_item);
        }
        $("dt_like_count").innerHTML = parseInt($("dt_like_count").innerHTML) + 1;
        _like._showList();
        if (!_._checkCss("transition")) { return; }
        _._opacity("dt_like_list_begin", 0); _._opacity("dt_like_icon", 1);
        setTimeout(function () {
            with ($("dt_like_icon").style) {
                left = "8px";
                top = "8px";
                width = "15px";
                height = "20px";
            }
            _._opacity("dt_like_icon", 1);
        }, 100);
    },
    _getFlag: false,
    _get: function () {
        if (_like._getFlag == true) { return; }
        _like._getFlag = true;
        _$(_api._likeList, "info_type=" + _info._type + "&info_id=" + _info._id + "&page_num=" + _like._index, _like._getOk);
    },
    _getOk: function (code, json) {
        if (code != 200 || (code == 200 && json.state.toString() != "0")) { _like._getFlag = false; return; }
        _likeBeforeLogin._continue();
        var _data = _._isArray(json.like) ? json.like : [], _len = _data.length;
        if (_like._index == 1) {
            _._show("dt_like");
            if (json.user_is_like.toString() == "1") { _like._liked(); }
            $("dt_like_count").innerHTML = json.like_count;
            if (_len > 0) { _like._addBegin(); _like._showList(); }
        }
        if ($("dt_like_more")) { $("dt_like_more").parentNode.removeChild($("dt_like_more")); }
        if (_len == 0) { return; }
        for (var i = 0; i < _len; i++) {
            if (!$("dt_like_list_" + _data[i].user_id)) {
                $("dt_like_list").appendChild(_like._itemDom(_data[i].user_id, _data[i].user_name));
            }
        }
        if (json.next_state.toString() == "1") {
            var _a = document.createElement("a");
            _a.id = "dt_like_more"
            _a.className = "dt_nick";
            _a.ontouchstart = function () { };
            _a.href = "javascript:_like._get()";
            _a.innerHTML = "&nbsp;&nbsp;更多…";
            $("dt_like_list").appendChild(_a);
            _like._getFlag = false;
            _like._index++;
        }
    },
    _showList: function () {
        _._show("dt_like_top");
        _._show("dt_like_main");
    },
    _liked: function () {
        if ($("dt_like").firstChild.nodeType == 3) { return; }
        $("dt_like").innerHTML = $("dt_like").firstChild.innerHTML;
    },
    _likeFailed: function () {
        $("dt_like").innerHTML = "<a ontouchstart='' href='javascript:_like._post()'>" + $("dt_like").innerHTML + "</a>";
    },
    _addBegin: function () {
        var _img = document.createElement("img");
        _img.id = "dt_like_list_begin";
        _img.src = _imgCdn + "images/like_list_icon.png";
        $("dt_like_list").appendChild(_img);
    },
    _itemDom: function (userId, userName) {
        var _a = document.createElement("a");
        _a.id = "dt_like_list_" + userId;
        _a.className = "dt_nick";
        _a.ontouchstart = function () { };
        _a.href = "/timeline/" + userId;
        _a.innerHTML = "&nbsp;&nbsp;" + userName;
        return _a;
    },
    _init: function () {
        setTimeout(_like._get, 500);
    }
};
var _shareInWeixin = {
    _after: null,
    _show: function (fun) {
        _cover._show("cover2");
        _._show("share_weixin");
        $("cover2").onclick = _shareInWeixin._hide;
        _shareInWeixin._after = fun || null;
    },
    _hide: function () {
        _cover._hide("cover2");
        _._hide("share_weixin");
        $("cover2").onclick = null;
        if (_shareInWeixin._after) { (_shareInWeixin._after)(); }
    },
    _hideFromJsBridge: function () {
        if ($("cover2") && $("share_weixin")) {
            _shareInWeixin._hide();
        }
    }
};
var _shareInQq = {
    _after: null,
    _timer: null,
    _show: function (fun) {
        _cover._show("cover2");
        _._show("share_qq");
        $("cover2").onclick = _shareInQq._hide;
        _shareInQq._timer = setTimeout(_shareInQq._hide, 8 * 1000);
        _shareInQq._after = fun || null;
    },
    _hide: function () {
        _cover._hide("cover2");
        _._hide("share_qq");
        $("cover2").onclick = null;
        clearTimeout(_shareInQq._timer);
        if (_shareInQq._after) { (_shareInQq._after)(); }
    }
};
var _share = {
    _fromQr: function () {
        return location.href.toString().match(/.*\?from=qr(_post)*$/i) != null;
    },
    _fromPost: function () {
        return location.href.toString().match(/.*\?from=qr_post$/i) != null;
    },
    _init: function () {
        if (_user._inWeixin()) {
            $("dt_share_weixin_icon_1").src = _imgCdn + "images/icon_weixin.png";
            $("dt_share_weixin_icon_2").src = _imgCdn + "images/icon_weixin_timeline.png";
            $("share_weixin_guide").src = _imgCdn + "images/guide_weixin.png?new";
            _._show("dt_share_weixin");
            if (_share._fromQr()) {
                setTimeout(function () {
                    _shareInWeixin._show(_share._fromPost() ? _manage._installApp : null);
                }, 500);
            }
        } else if (_user._inQq()) {
            $("dt_share_qq_icon_1").src = _imgCdn + "images/icon_qq.png";
            $("dt_share_qq_icon_2").src = _imgCdn + "images/icon_qzone.png";
            $("share_qq_guide").src = _imgCdn + "images/guide_qq.png?new";
            _._show("dt_share_qq");
            if (_share._fromQr()) {
                setTimeout(function () {
                    _shareInQq._show(_share._fromPost() ? _manage._installApp : null);
                }, 500);
            }
        } else if (_user._inHudongba()) {
            $("dt_share_friend_icon").parentNode.removeChild($("dt_share_friend_icon"));
            _._show("dt_share_friend");
        } else if (_user._inMobile()) {
            $("dt_share_friend_icon").src = _imgCdn + "images/icon_share.png";
            _._show("dt_share_friend");
        } else {
            $("dt_share_qr_icon").src = _imgCdn + "images/icon_qr.png";
            _._show("dt_share_qr");
        }
    },
    _toFriend: function () {
        if (_user._inHudongba()) {
            HudongbaJsBridge["showSharePanel"]();
            return;
        }
        _toast._show("请点击菜单上的分享图标进行发送");
    },
    _joinSucess: function () {
        //if(_user._inHudongba()){HudongbaJsBridge["showSharePanel"]();return;}
        /*if(!_user._inMobile()){
	        if(!_._isHide("cover2")){return;}
	        _qr._show();
	    }else if(_user._inWeixin()){
	        if(!_._isHide("cover2")){return;}
	        _shareInWeixin._show();
	    }else if(_user._inQq()){
	        if(!_._isHide("cover2")){return;}
	        _shareInQq._show();
		}*/
    }
};
var _qr = {
    _after: null,
    _from: "",
    _center: function () {
        _._center("share_qr");
    },
    _show: function (fun) {
        var _img = new Image();
        _img.src = _api._getQr + "?from=" + _qr._from + "&info_id=" + _info._id + "&info_type=" + _info._type;
        _img.onload = function () {
            with ($("share_qr_img")) {
                src = _img.src;
                style.width = "150px";
                style.height = "150px";
                style.margin = "0";
            }
        };
        _img.onerror = function () {
            if (_._isHide("share_qr")) { return; }
            _qr._hide();
            _toast._show("网络错误，请稍后重试");
        };
        _cover._show("cover2");
        _._show("share_qr");
        _qr._center();
        _e._add(window, "resize", _qr._center);
        _qr._after = fun || null;
    },
    _hide: function () {
        _cover._hide("cover2");
        _._hide("share_qr");
        _e._del(window, "resize", _qr._center);
        if (_qr._after) { (_qr._after)(); }
    }
};
var _postSucess = {
    _fromApp: function () {
        return _user._inHudongba() && location.href.toString().indexOf("current=postsucess") != -1;
    },
    _fromWeb: function () {
        return document.referrer.indexOf("/post/" + _info._type) != -1;
    },
    _modify: function () {
        if (_user._inHudongba()) {
            if (_user._useLowHudongba()) { HudongbaJsBridge["getModifyPage"](_info._id, _info._type); return; }
            HudongbaJsBridge["getModifyPage"](_info._id, _info._type, _info._title);
        } else {
            if (!window.localStorage) {
                _alert._show("浏览器版本过低", "请登录街米互动App后再进行操作", "一键安装App", function () { _download(_link._appDownload); }, "关闭提示");
                return;
            }
            var _refer = document.referrer;
            _refer = _refer.indexOf("?") != -1 ? _refer.split("?")[0] : _refer;
            _g(_refer + "?id=" + _info._id);
        }
    },
    _share: function () {
        if (!_user._inMobile()) {
            _qr._show();
        } else if (_user._inWeixin()) {
            _shareInWeixin._show(function () { _manage._installApp(true); });
        } else if (_user._inQq()) {
            _shareInQq._show(function () { _manage._installApp(true); });
        } else {
            _share._toFriend();
        }
    },
    _init: function () {
        if (_postSucess._fromApp()) { _._show("dt_post_sucess"); return; }
        if (_postSucess._fromWeb()) {
            _qr._from = "qr_post";
            _._show("dt_post_sucess");
            //$("topbar_back").onclick=_postSucess._modify;	
            _._hide("topbar_menu");
        }
    }
};
var _support = {
    _format: function (id) {
        var _html = $(id).innerHTML;
        if (_user._inMobile()) {
            _html = _html.replace(/([^\d]|\b)(\d[\d\-]{5,11}\d)([^\d]|\b)/g, "$1<a class='dt_support' href='tel:$2'>$2</a>$3");
        }
        if (_user._inHudongba()) {
            _html = _html.replace(/(http:\/\/)?(www\.)?(hudongba\.mobi\/(vote|party|article|recruit|job)\/\w+(\?[\w\-&=#;]*)?)/ig, "<a class='dt_support' href='http://$3'>$1$2$3</a>");
        } else {
            _html = _html.replace(/(http:\/\/)?(www\.)?(((hudongba\.mobi)|(hudong\.ba))(\/[\w\-\/\?&=#;]*)*)/ig, "<a class='dt_support' href='http://$3'>$1$2$3</a>");
        }
        _html = _html.replace(/\'([^\']*)\'/ig, function (a) { return a.replace(/((from)|(isappinstalled))=/ig, ""); });
        $(id).innerHTML = _html;
        _html = null;
    },
    _init: function () {
        if ($("dt_content")) { _support._format("dt_content"); }
        if ($("dt_contact_content")) { _support._format("dt_contact_content"); }
        if ($("dt_vote_result")) { _support._format("dt_vote_result"); }
    }
};

var _manage = {
    _text: function () {
        var _text;
        switch (_info._type) {
            case "vote":
                _text = "即时收取投票数据，随时关注投票进展";
                break;
            case "party":
            case "recruit":
                _text = "查看详细报名信息，导出完整报名记录";
                break;
            case "signature":
                _text = "即时收取签名数据，随时关注签名进展";
                break;
            case "question":
                _text = "即时收取最新答案，轻松评定最佳答案";
                break;
            default:
                _text = "即时收取好友评论，随时关注互动进展";
                break;
        }
        return _text;
    },
    _installApp: function (install) {
        if (!install && _user._isappinstalled()) { return; }
        _alert._show("登录街米互动App，你可以", _manage._text(), "一键安装App", function () { _download(_link._appDownload); }, "关闭提示");
    },
    _init: function () {
        if (_user._inHudongba()) { return; }
        _._show("dt_button_post");
        if (_user._login() && _user._id() == _info._postUid) {
            if ($("dt_list_title_manage")) {
                $("dt_list_title_manage").ontouchstart = function () { };
                $("dt_list_title_manage").onclick = function () {
                    _manage._installApp(true);
                };
                _._show("dt_list_title_manage");
            }
            with ($("dt_button_post").firstChild) {
                innerHTML = "再发起一个互动";
                onclick = function () { _g(_link._post); };
            }
        }
    }
};
var _next = function () {
    _g($("hot_item_0").getAttribute("data-href"));
};

var _joinBar = {
    _scroll: function () {
        if (_cover._flag == false && _._scroll().y < _scroll._getTop("dt_button_join") + $("dt_button_join").offsetHeight - _._client().bh) {
            if (!_._isHide("dt_join_bar")) { return; }
            _._show("dt_join_bar");
            setTimeout(function () { $("dt_join_bar").style.marginBottom = "0"; }, 50);
        } else {
            if (_._isHide("dt_join_bar")) { return; }
            _joinBar._hide();
        }
    },
    _show: function () {
        if (typeof _info == "undefined" || typeof _info._type == "undefined" || typeof _info._lock == "undefined" || typeof _info._join == "undefined" || typeof _info._paid == "undefined" || typeof _info._power == "undefined" || typeof _info._payItemId == "undefined") { return; }
        if ((_info._type == "party" || _info._type == "recruit") && _info._lock == false && (_info._join == "0" || (_info._payItemId != "" && _info._paid == "0")) && _info._power == "0" && !_postSucess._fromApp() && !_postSucess._fromWeb()) {
            setTimeout(_joinBar._scroll, 50);
            _e._add(window, "scroll", _joinBar._scroll);
            _e._add(window, "resize", _joinBar._scroll);
        }
    },
    _hide: function () {
        $("dt_join_bar").style.marginBottom = "-100px";
        setTimeout(function () {
            _._hide("dt_join_bar");
        }, 300);
    },
    _del: function () {
        _e._del(window, "scroll", _joinBar._scroll);
        _e._del(window, "resize", _joinBar._scroll);
        _joinBar._hide();
    }
};
(function () {
    var onBridgeReady = function () {
        WeixinJSBridge.on('menu:share:appmessage', function (argv) {
            _shareInWeixin._hideFromJsBridge();
            WeixinJSBridge.invoke('sendAppMessage', {
                "appid": dataForShare.appId,
                "img_url": dataForShare.weixin_icon,
                "img_width": "180",
                "img_height": "180",
                "link": dataForShare.weixin_url,
                "desc": dataForShare.description,
                "title": dataForShare.title
            }, function (res) { (dataForShare.callback)(); });
        });
        WeixinJSBridge.on('menu:share:timeline', function (argv) {
            _shareInWeixin._hideFromJsBridge();
            (dataForShare.callback)();
            WeixinJSBridge.invoke('shareTimeline', {
                "img_url": dataForShare.weixin_tl_icon,
                "img_width": "120",
                "img_height": "120",
                "link": dataForShare.weixin_url,
                "desc": dataForShare.description,
                "title": dataForShare.title
            }, function (res) { });
        });
        WeixinJSBridge.on('menu:share:weibo', function (argv) {
            _shareInWeixin._hideFromJsBridge();
            WeixinJSBridge.invoke('shareWeibo', {
                "content": dataForShare.title,
                "url": dataForShare.url
            }, function (res) { (dataForShare.callback)(); });
        });
        WeixinJSBridge.on('menu:share:facebook', function (argv) {
            _shareInWeixin._hideFromJsBridge();
            (dataForShare.callback)();
            WeixinJSBridge.invoke('shareFB', {
                "img_url": dataForShare.weibo_icon,
                "img_width": "180",
                "img_height": "180",
                "link": dataForShare.url,
                "desc": dataForShare.description,
                "title": dataForShare.title
            }, function (res) { });
        });
        WeixinJSBridge.on("menu:general:share", function (s) {
            _shareInWeixin._hideFromJsBridge();
            var _img_url_s, _img_width_s, _img_height_s, _link_s;
            switch (s.shareTo) {
                case "friend":
                    _img_url_s = dataForShare.weixin_icon;
                    _img_width_s = "180";
                    _img_height_s = "180";
                    _link_s = dataForShare.weixin_url;
                    break;
                case "timeline":
                    _img_url_s = dataForShare.weixin_tl_icon;
                    _img_width_s = "120";
                    _img_height_s = "120";
                    _link_s = dataForShare.weixin_url;
                    break;
                default:
                    _img_url_s = dataForShare.weibo_icon;
                    _img_width_s = "180";
                    _img_height_s = "180";
                    _link_s = dataForShare.url;
                    break;
            }
            s.generalShare({
                appid: dataForShare.appId,
                img_url: _img_url_s,
                img_width: _img_width_s,
                img_height: _img_height_s,
                link: _link_s,
                desc: dataForShare.description,
                title: dataForShare.title
            }, function (e) { (dataForShare.callback)(); });
        });
    };
    if (typeof WeixinJSBridge == "undefined") {
        if (document.addEventListener) {
            document.addEventListener('WeixinJSBridgeReady', onBridgeReady, false);
        } else if (document.attachEvent) {
            document.attachEvent('WeixinJSBridgeReady', onBridgeReady);
            document.attachEvent('onWeixinJSBridgeReady', onBridgeReady);
        }
    } else {
        onBridgeReady();
    }
})();
(function topbarControl() {
    if (_user._inHudongba()) { return; }
    document.body.style.marginTop = "63px";
    _._show("topbar");
    if (document.referrer.toString() != "") {
        _._show("topbar_back");
        $("topbar_back").ontouchstart = function () { };
        $("topbar_back").onclick = function () {
            if (_backToRefresh._should()) {
                _backToRefresh._clear();
                _g(document.referrer.toString());
                return;
            };
            history.back();
        };
    }
})();
var _setData = function () {
    var _invite = "false", _infoId = "", _infoType = "";
    if (typeof _info != "undefined" && typeof _info._id != "undefined" && typeof _info._type != "undefined") {
        _infoId = _info._id;
        _infoType = _info._type;
        if ((_info._type == "party" || _info._type == "recruit") && _user._login() && _user._id() == _info._postUid) {
            _invite = "true";
        }
    }
    HudongbaJsBridge["setData"]($("topbar_title").innerHTML, dataForShare.weixin_icon, dataForShare.weixin_tl_icon, dataForShare.weixin_url, dataForShare.qq_icon, dataForShare.weibo_icon, dataForShare.url, dataForShare.title, dataForShare.description, dataForShare.sms, _invite, _infoId, _infoType);
};

(function () {
    _e._add(window, "load", _user._init);
    if (_user._inHudongba() && _user._useLowHudongba()) {
        _e._add(window, "load", function () { setTimeout(_setData, 10); });
    }
})();

function colsehot() {
    _cookie._set("hot_1", "");
    _cookie._set("hot_2", "");
    _cookie._set("hot_3", "");
 
}
