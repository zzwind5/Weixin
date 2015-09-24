/*
[jmhudong.com] (C)2014 linli Inc.
*/
var _postLink = {
   // _appDownload: "/download?from=post",
    _appDownload: "http://www.123linli.com/download/streetme.apk",
    _map: "http://map.baidu.com"
};
var _postApi = {
    _picUpload: "/m/ajax.aspx?type=189",
    _vote: "/m/ajax.aspx?type=200",
    _party: "/m/ajax.aspx?type=250",
    _article: "/m/ajax.aspx?type=300",
    _recruit: "/post/api:39",
    _job: "/post/api:27"
};
var _postTemp = {
    _set: function (key, value) {
        _post._id == "" ? _t._set(key, value) : _t._set(key + "_m", value);
    },
    _get: function (key) {
        return _post._id == "" ? _t._get(key) : _t._get(key + "_m");
    }
};
var _postPic = {
    _resizeAll: function () {
        var _img = $(_richText._id).getElementsByTagName("img"),
		_parentWidth = $(_richText._id).offsetWidth - 8;
        for (var i = 0, len = _img.length; i < len; i++) {
            if (_img[i].className == "post_pic") {
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
        var _parentWidth;
        if (o.className == "post_pic") {
            _parentWidth = $(_richText._id).offsetWidth - 8;
        } else {
            _parentWidth = o.parentNode.offsetWidth;
        }
        var _imgWidth = _pic._getWidth(o);
        if (_imgWidth > _parentWidth) {
            o.style.width = _parentWidth + "px";
        } else {
            o.style.width = _imgWidth + "px";
        }
    }
};
var _upload = {
    _limit: function (path) {
        return path.match(/\.(gif|jpg|jpeg|png)$/i) == null;
    },
    _clearFile: function (o) {
        var _span = document.createElement("span");
        _span.id = "__tt_";
        o.parentNode.insertBefore(_span, o);
        var _form = document.createElement("form");
        _form.appendChild(o);
        document.body.appendChild(_form);
        _form.reset();
        _span.parentNode.insertBefore(o, _span);
        _span.parentNode.removeChild(_span);
        _form.parentNode.removeChild(_form);
    },
    _ok: null
};
var _checkbox = {
    _check: function (id) {
        _checkbox._setValue(id, (parseInt(_checkbox._getValue(id)) + 1) % 2);
    },
    _getValue: function (id) {
        return $(id).src.indexOf("_yes") == -1 ? 1 : 0;
    },
    _setValue: function (id, value, auto) {
        with ($(id)) {
            src = value == 0 ? src.replace(/_no/, "_yes") : src.replace(/_yes/, "_no");
        }
        if (!auto) { _postTemp._set(id, value); }
    }
};
var _textArea = {
    _update: function (o) {
        o.value = o.value
			.replace(/[\x20]+\n/g, "\n")
			.replace(/[\x20]+($)/g, "$1")
			.replace(/(\n(\r*))*([\x20]{20,})/g, "\n");
    },
    _onPaste: function (o) {
        setTimeout(function () {
            _textArea._update(o);
        }, 200);
    },
    _onBlur: function (o, temp) {
        _textArea._update(o);
        _postTemp._set(temp, o.value);
    }
};
var _richText = {
    _id: "",
    _isEmpty: function () {
        if (_user._inMobile()) {
            return $(_richText._id + "_2").value == "";
        } else {
            return $(_richText._id).innerHTML.replace(/(<br>)/ig, "").replace(/(&nbsp;)/ig, "").replace(/ /ig, "") == "";
        }
    },
    _focus: function () {
        $(_richText._id).focus();
    },
    _update: function () {
        var _html = $(_richText._id).innerHTML;
        _html = _html
		.replace(/img2\.hudongba\.cn\/upload([\w\/\.]+)/ig, ">[pic:$1]<")
		.replace(/img1\.hudongba\.cn\/images\/emo\/(\d+)\.png/ig, ">[emo:$1]<")
		.replace(/<br[^>]*>/ig, "[br:br]")
		.replace(/<\/p[^>]*>/ig, "[br:br]")
		.replace(/<div[^>]*>/ig, "[br:br]")
		.replace(/(<[^>]+>)|>|</ig, "")
		.replace(/\[pic\:([\w\/\.]+)\]/ig, "<img onload=\"_postPic._resize(this)\" class=\"post_pic\" src=\"upload$1\">")
		.replace(/\[emo\:(\d+)\]/ig, "<img class=\"dt_emo\" src=\"images/emo/$1.png\">")
		.replace(/\[br\:br\]/ig, "<br>")
		.replace(/^(<br>)*/ig, "")
		.replace(/(<br>)*$/ig, "");
        $(_richText._id).innerHTML = _html;
    },
    _insertPic: function (url) {
        var _y = _._scroll().y;
        $(_richText._id).innerHTML += "<img  onload=\"_postPic._resize(this)\" class=\"post_pic\" src=\"" + url + "\">";
        _richText._onBlur(_y, true);
    },
    _insertMaxPic: function () {
        if ($(_richText._id + "_upload_type").value == "vote") {
            return $(_richText._id).innerHTML.match(/(<img[^>]*class\=(\"|\')?post_pic(\"|\')?[^>]*>){30,}/i) != null;
        } else {
            return $(_richText._id).innerHTML.match(/(<img[^>]*class\=(\"|\')?post_pic(\"|\')?[^>]*>){10,}/i) != null;
        }
    },
    _maxPic: function () {
        if ($(_richText._id + "_upload_type").value == "vote") {
            return $(_richText._id).innerHTML.match(/(<img[^>]*class\=(\"|\')?post_pic(\"|\')?[^>]*>){31,}/i) != null;
        } else {
            return $(_richText._id).innerHTML.match(/(<img[^>]*class\=(\"|\')?post_pic(\"|\')?[^>]*>){11,}/i) != null;
        }
    },
    _insertEmo: function (index) {
        var _y = _._scroll().y;
        $(_richText._id).innerHTML += "<img class=\"dt_emo\" src=\"images/emo/" + (index + 1) + ".png\">";
        _richText._onBlur(_y, true);
    },
    _onPaste: function () {
        var _y = _._scroll().y;
        setTimeout(function () {
            _richText._update();
            _richText._onBlur(_y, true);
        }, 200);
    },
    _onFocus: function () {
        _richText._placeHolder(true);
        _emo._hide(_richText._id + "_emo");
    },
    _onBlur: function (y, noUpdate) {
        y = y || _._scroll().y;
        _richText._placeHolder();
        if (!noUpdate) { _richText._update(); }
        _postTemp._set(_richText._id, $(_richText._id).innerHTML);
        setTimeout(function () {
            window.scrollTo(0, y);
        }, 100);
    },
    _placeHolder: function (clear) {
        $(_richText._id + "_placeholder").innerHTML = clear ? "" : (_richText._isEmpty() ? $(_richText._id).getAttribute("placeholder") : "");
    },
    _showEmo: function () {
        _emo._show(_richText._id + "_emo", function (index) {
            _richText._insertEmo(index);
        });
    },
    _upload: function () {
        if (_upload._limit($(_richText._id + "_upload_file").value)) {
            _toast._show("只能选择图片文件");
            _upload._clearFile($(_richText._id + "_upload_file"));
            return;
        }
        if (_richText._insertMaxPic()) {
            _toast._show("图片太多了，最多只能上传" + ($(_richText._id + "_upload_type").value == "vote" ? "30" : "10") + "张图片");
            _upload._clearFile($(_richText._id + "_upload_file"));
            return;
        }
        _loading._show("上传中");
        $(_richText._id + "_upload").submit();
    },
    _uploadOk: function (state, error, saveUrl) {
        _loading._hide();
        _upload._clearFile($(_richText._id + "_upload_file"));
        if (state != "0") {
            _toast._show(error);
            return;
        }
        _richText._insertPic(saveUrl);
    },
    _setValue: function (value) {
        if (_user._inMobile()) {
            $(_richText._id + "_2").value = value.replace(/<br[^>]*>/ig, "\n");
        } else {
            $(_richText._id).innerHTML = value;
        }
    },
    _getValue: function (post) {
        if (_user._inMobile()) {
            return post ? _._htmlencode($(_richText._id + "_2").value) : $(_richText._id + "_2").value;
        } else {
            return $(_richText._id).innerHTML;
        }
    },
    _init: function (id, infoType) {
        _richText._id = id;
        $(_richText._id + "_upload").setAttribute("action", _postApi._picUpload);
        $(_richText._id + "_upload_type").value = infoType;
        _upload._ok = _richText._uploadOk;
        if (_user._inMobile()) {
            var _div = document.createElement("div");
            _div.className = "form_textarea";
            _div.innerHTML = "<textarea id=\"" + _richText._id + "_2\"  class=\"textarea\" onpaste=\"_textArea._onPaste(this)\" onblur=\"_textArea._onBlur(this,'" + _richText._id + "')\"  placeholder=\"" + $(_richText._id).getAttribute("placeholder") + "\" style=\"height:" + $(_richText._id).offsetHeight + "px\"></textarea>";
            $(_richText._id + "_richtext").parentNode.insertBefore(_div, $(_richText._id + "_richtext"));
            _._hide(_richText._id + "_richtext");
        } else {
            _._show(_richText._id + "_richtext_bar");
            _richText._placeHolder();
            _postPic._resizeAll();
            _e._add(window, "resize", _postPic._resizeAll);
        }
    }
};
var _postMore = {
    _show: function (auto) {
        _._hide("post_form_more");
        _._show("post_form_more_detail");
        var _height = $("post_form_more_detail").offsetHeight;
        $("post_form_more_detail").style.height = "0";
        setTimeout(function () {
            $("post_form_more_detail").style.height = _height + "px";
        }, 100);
        setTimeout(function () {
            $("post_form_more_detail").style.height = "auto";
            $("post_form_more_detail").style.overflow = "auto";
            if (!auto) { _scroll._to(_._client().h); }
        }, 400);
    },
    _init: function () {
        if (!_user._inMobile()) {
            _._show("post_form_more");
            if (_postTemp._get(_postInvite._id) != "" || (_postJoin._id != "" && _postTemp._get(_postJoin._id) != "")) {
                _postMore._show(true);
            }
        }
    }
};
var _postInvite = {
    _id: "",
    _display: function () {
        if (_checkbox._getValue(_postInvite._id) == 0) {
            _._show(_postInvite._id + "_code");
        } else {
            _._hide(_postInvite._id + "_code");
        }
    },
    _check: function () {
        _checkbox._check(_postInvite._id);
        _postInvite._display();
    },
    _init: function (id) {
        _postInvite._id = id;
        var _value = _postTemp._get(_postInvite._id);
        if (_value === "") { _value = "1"; }
        _checkbox._setValue(_postInvite._id, _value, true);
        _postInvite._display();
    }
};
var _postJoin = {
    _id: "",
    _item: function (item) {
        $(_postJoin._id + "_" + item).className = "join_item_" + (_postJoin._selected(item) ? "no" : "yes");
        _postTemp._set(_postJoin._id, _postJoin._getValue());
    },
    _selected: function (item) {
        return $(_postJoin._id + "_" + item).className.indexOf("_yes") != -1;
    },
    _getValue: function () {
        var _value = "1,2";
        for (var i = 3; i <= 6; i++) {
            if (_postJoin._selected(i)) { _value += "," + i; }
        }
        return _value;
    },
    _setValue: function (value) {
        for (var i = 3; i <= 6; i++) {
            $(_postJoin._id + "_" + i).className = "join_item_" + (("," + value + ",").indexOf("," + i + ",") != -1 ? "yes" : "no");
        }
    },
    _init: function (id) {
        _postJoin._id = id;
    }
};
var _postMap = {
    _id: "",
    _full: function () {
        $("post_map").style.width = _._client().w + "px";
        $("post_map").style.height = _._client().h + "px";
        $("post_map_iframe").style.width = _._client().w + "px";
        $("post_map_iframe").style.height = _._client().h + "px";
    },
    _show: function (para) {
        _._show("post_map");
        $("post_map_iframe").src = _postLink._map + "?" + para;
        _postMap._full();
        _e._add(window, "resize", _postMap._full);
    },
    _hide: function () {
        _._hide("post_map");
        _e._del(window, "resize", _postMap._full);
    },
    _set: function (position, address, auto) {
        $(_postMap._id + "_position").value = position;
        $(_postMap._id + "_address").innerHTML = position == "" ? "标记活动位置，可不标" : address;
        $(_postMap._id + "_flag").firstChild.src = _imgCdn + "images/map_flag_" + (position == "" ? "no" : "yes") + ".png";
        $(_postMap._id + "_address").style.color = position == "" ? "#999999" : "#2da9bf";
        if (!auto) {
            _postTemp._set(_postMap._id + "_position", position);
            _postTemp._set(_postMap._id + "_address", address);
        }
    },
    _get: function () {
        var _position = ",", _address = "";
        if ($(_postMap._id + "_position").value.indexOf(",") != -1) {
            _position = $(_postMap._id + "_position").value;
            _address = $(_postMap._id + "_address").innerHTML;
        }
        return { position: _position, address: _address };
    },
    _init: function (id) {
        _postMap._id = id;
        //if(!_user._inMobile()){
        //_._show(id);
        //}
    }
};
var _datePicker = {
    _id: "",
    _interval: null,
    _timeout: null,
    _showDay: ["日", "一", "二", "三", "四", "五", "六"],
    _showDate: function (t) {
        var _d = new Date(t);
        return (_d.getMonth() + 1) + "月" + _d.getDate() + " 周" + _datePicker._showDay[_d.getDay()];
    },
    _showHour: function (t) {
        return t + "时";
    },
    _showMinute: function (t) {
        return t + "分";
    },
    _getCurrent: function (id) {
        return $(id).getElementsByTagName("div")[2];
    },
    _current: function (id, cur) {
        _datePicker._getCurrent(id).style.color = cur ? "#2da9bf" : "#999999";
    },
    _minStart: function (o, fun) {
        _datePicker._timeout = setTimeout(function () {
            _datePicker._interval = setInterval(fun, 100);
        }, 500);
        _datePicker._stopEvent(o);
    },
    _addStart: function (o, fun) {
        _datePicker._timeout = setTimeout(function () {
            _datePicker._interval = setInterval(fun, 100);
        }, 500);
        _datePicker._stopEvent(o);
    },
    _stopEvent: function (o) {
        o.onmouseup = _datePicker._stop;
        o.onmouseout = _datePicker._stop;
    },
    _stop: function () {
        clearTimeout(_datePicker._timeout);
        clearInterval(_datePicker._interval);
    },
    _min: function (id, fun1, fun2) {
        _datePicker._current(id, false);
        $(id).firstChild.style.marginTop = "0";
        $(id).insertBefore($(id).lastChild, $(id).firstChild);
        var _t = parseInt($(id).firstChild.nextSibling.getAttribute("time"));
        _t = (fun1)(_t);
        $(id).firstChild.innerHTML = (fun2)(_t);
        $(id).firstChild.setAttribute("time", _t);
        $(id).firstChild.style.marginTop = "-30px";
        _datePicker._current(id, true);
    },
    _add: function (id, fun1, fun2) {
        _datePicker._current(id, false);
        $(id).firstChild.style.marginTop = "-60px";
        $(id).appendChild($(id).firstChild);
        var _t = parseInt($(id).lastChild.previousSibling.getAttribute("time"));
        _t = (fun1)(_t);
        $(id).lastChild.innerHTML = (fun2)(_t);
        $(id).lastChild.setAttribute("time", _t);
        $(id).lastChild.style.marginTop = "0";
        $(id).firstChild.style.marginTop = "-30px";
        _datePicker._current(id, true);
    },
    _dateMin: function () {
        _datePicker._min("datepicker_date", function (t) {
            return new Date(t - 86400000 * 1).getTime();
        }, _datePicker._showDate);
    },
    _dateAdd: function () {
        _datePicker._add("datepicker_date", function (t) {
            return new Date(t + 86400000 * 1).getTime();
        }, _datePicker._showDate);
    },
    _hourMin: function () {
        _datePicker._min("datepicker_hour", function (t) {
            return t == 0 ? 23 : t - 1;
        }, _datePicker._showHour);
    },
    _hourAdd: function () {
        _datePicker._add("datepicker_hour", function (t) {
            return t == 23 ? 0 : t + 1;
        }, _datePicker._showHour);
    },
    _minuteMin: function () {
        _datePicker._min("datepicker_minute", function (t) {
            return t == 0 ? 59 : t - 1;
        }, _datePicker._showMinute);
    },
    _minuteAdd: function () {
        _datePicker._add("datepicker_minute", function (t) {
            return t == 59 ? 0 : t + 1;
        }, _datePicker._showMinute);
    },
    _itemInit: function (id, time, fun1, fun2) {
        $(id).innerHTML = "";
        for (var i = 0; i < 5; i++) {
            var _item = document.createElement("div");
            _item.className = "datepicker_item";
            if (i == 0) { _item.style.marginTop = "-30px"; }
            _item.innerHTML = (fun1)(time);
            _item.setAttribute("time", time);
            time = (fun2)(time);
            $(id).appendChild(_item);
        }
        _datePicker._current(id, true);
    },
    _notSupport: function (id) {
        if (!_user._inMobile()) {
            _._show(id + "_input");
        } else {
            var _d = new Date();
            _d.setMonth(_d.getMonth() + 3);
            $(id).value = _d.getFullYear() + "-" + (_d.getMonth() + 1) + "-" + _d.getDate() + " " + _d.getHours() + ":" + _d.getMinutes();
        }
    },
    _init: function (id, date) {
        _datePicker._id = id;
        date = date ? date.replace(/-/g, "/") : "";
        var _d = Date.parse(date) ? new Date(date) : new Date(),
		_hour = _d.getHours() - 2,
		_minute = _d.getMinutes() - 2,
		_date = _d.getTime() - 86400000 * 2;
        _hour = _hour < 0 ? _hour + 24 : _hour;
        _minute = _minute < 0 ? _minute + 60 : _minute;
        _datePicker._itemInit("datepicker_date", _date, _datePicker._showDate, function (t) {
            return new Date(t + 86400000 * 1).getTime()
        });
        _datePicker._itemInit("datepicker_hour", _hour, _datePicker._showHour, function (t) {
            return t == 23 ? 0 : t + 1;
        });
        _datePicker._itemInit("datepicker_minute", _minute, _datePicker._showMinute, function (t) {
            return t == 59 ? 0 : t + 1;
        });
    },
    _getValue: function () {
        var _d = new Date(parseInt(_datePicker._getCurrent("datepicker_date").getAttribute("time"))),
		_date = _d.getFullYear() + "-" + (_d.getMonth() + 1) + "-" + _d.getDate(),
		_hour = _datePicker._getCurrent("datepicker_hour").getAttribute("time"),
		_minute = _datePicker._getCurrent("datepicker_minute").getAttribute("time");
        return _date + " " + _hour + ":" + _minute;
    },
    _onBlur: function () {
        _postTemp._set(_datePicker._id, $(_datePicker._id).value);
    },
    _earilier: function (date) {
        date = date.replace(/-/g, "/");
        if (!Date.parse(date)) { return true; }
        return new Date(date).getTime() < new Date().getTime();
    },
    _show: function (id) {
        _cover._show("cover2");
        _._show("post_datepicker");
        _datePicker._init(id, $(id).value);
        $("cover2").onclick = _datePicker._hide;
        setTimeout(function () { $("post_datepicker").style.marginBottom = "0"; }, 50);
    },
    _hide: function () {
        $("post_datepicker").style.marginBottom = "-200px";
        setTimeout(function () { _cover._hide("cover2"); $("cover2").onclick = null; _._hide("post_datepicker"); }, 300);
    },
    _ok: function () {
        $(_datePicker._id).value = _datePicker._getValue();
        _datePicker._onBlur();
        _datePicker._hide();
    }
};
var _postMenu = {
    _show: function (kind) {
        $("post_menu_note").innerHTML = _user._inMobile() ? "使用App发布信息，支持图文、表情，发布体验更好、管理功能更全！" : "推荐使用互动吧App，轻松管理互动信息，即时收取互动提醒！"
        _cover._show("cover2");
        _._show("post_menu");
        if (_user._login()) {
            _._show("post_menu_button_logout");
            _._hide("post_menu_button_login");
        } else {
            _._hide("post_menu_button_logout");
            _._show("post_menu_button_login");
        }
        $("cover2").onclick = function () { _postMenu._hide(); };
        setTimeout(function () { $("post_menu").style.marginBottom = "0"; }, 50);
    },
    _hide: function (fun) {
        $("post_menu").style.marginBottom = "-250px";
        setTimeout(function () { _cover._hide("cover2"); $("cover2").onclick = null; _._hide("post_menu"); }, 300);
        if (fun) { setTimeout(fun, 500); }
    },
    _download: function () {
        _postMenu._hide(function () { _download(_postLink._appDownload); });
    }
};
var _postBeforeLogin = {
    _mark: function (kind, id) {
        _t._set("post_before_login_" + kind, id);
    },
    _continue: function (kind, fun) {
        var _id = _t._get("post_before_login_" + kind);
        if (_id != "") {
            (fun)(_id == "0" ? "" : _id);
        }
    }
};