(function(ac){var ab=ac.env,z=document,L=z.location.href,az=null,au=[],B=ab.compId,C=false,J=navigator.userAgent.toLowerCase(),U="looyu_id",Y="looyu_"+B,ag,h,j=[],at=120,v=ab.file+"/../default/",ae,A=ab.subComp||0,y=(function(){var p=0,m=navigator.plugins;if(m&&m.length>0&&m["Shockwave Flash"]){var M=m["Shockwave Flash"].description.split(" ");for(var D=0;D<M.length;D++){if(isNaN(parseInt(M[D]))){continue}p=M[D]}}else{try{p=parseInt(new ActiveXObject("ShockwaveFlash.ShockwaveFlash").FlashVersion())>>>16}catch(E){}}return p>=9})(),ap=(function(){var m=/^http(?:s?):\/\/([\.\w\d\-\u4E00-\u9FA5]+)/i;return L.match(m)[1]})();var al=function(m){return z.getElementById(m)},ao={strict:z.compatMode=="CSS1Compat",webkit:/webkit/i.test(J),msie:/msie/i.test(J)&&!/opera/i.test(J),gecko:!/webkit/i.test(J)&&/gecko/i.test(J),safari:/safari/i.test(J)};ao.ie6=ao.msie&&!/msie [789]/i.test(J);ao.fix=!ao.msie||(!ao.ie6&&ao.strict);ao.ie6&&z.execCommand("BackgroundImageCache",false,true);var an=function(m){return !m},l=function(p){var m,D=new RegExp("(^| )"+p+"=([^;]*)(;|$)");m=z.cookie.match(D);return m?unescape(m[2]):null},ak=function(m,D,p){p=(p||120*30*24*60*60)*1000;var E=new Date();E.setTime(E.getTime()+p);z.cookie=m+"="+escape(D)+";expires="+E.toGMTString()+";domain="+ap+";path=/"},n=function(m){ak(m,"",-3600)},ai=function(m,D,p){if(y&&az){az.setCookie(m,D,p||-1)}ak(m,D,p)},X=function(m,p){return m.replace(/\$?\{([a-zA-z0-9]*)\}/ig,function(D,E){return p[E]})},o=function(m,p){return{top:(aw().clientHeight-p)/2,left:(aw().clientWidth-m)/2}},u=function(E,m,D){return !E&&o(m,D)||E==1&&{left:5,bottom:5}||E==2&&{right:5,bottom:5}};(function(){var M="DOYOO_USER_ID",D=l(M);if(D){n(M);var E=/(^| )DVC_(\d+)=([^;]*)(;|$)/ig,p,m=new String(z.cookie);while((p=E.exec(m))!=null){var P=p[2];D+="_"+P+":"+p[3];n("DVC_"+P);n("DVI_"+P);n("DV")}ak(U,D)}})();var aE=z.compareDocumentPosition?function(p,m){return p.compareDocumentPosition(m)&16}:function(p,m){return p!==m&&(p.contains?p.contains(m):true)},K=(function(){var D=al("doyoo_panel")||al("doyoo_monitor");if(!z.body){return false}if(!D){return true}var m=z.forms;for(var p=0;p<m.length;p++){if(aE(m[p],D)){return false}}return true})(),q=function(M,m){if(aA(M)){return}var D=v+"swf/"+m,E=' <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="1" height="1" id="{id}"><param name="movie" value="{path}"/><param name="allowScriptAccess" value="always"/><embed src="{path}" allowScriptAccess="always" width="1" height="1" name="{id}" type="application/x-shockwave-flash"></embed></object> ',p=X(E,{path:D,id:M});K?z.write(p):i(window,"load",function(){var P=r("div",{});P.innerHTML=p})},aA=function(m){return ao.msie?window[m]:z[m]},N=function(D,m){ac.resp=null;var p=D+"&x="+(+new Date()),M=z.getElementsByTagName("head")[0],E=z.createElement("script");E.setAttribute("type","text/javascript");M.appendChild(E);E.setAttribute("src",p);var P=function(aG){var aF=ac.resp;H(aG);m&&m(aF)};E.onreadystatechange=function(){if(this.readyState=="loaded"&&!this.called){P(this);this.called=true;}};E.onload=function(){if(!this.called){P(this);this.called=true;}}},g=function(m){if(typeof m=="string"){m=al(m)}m==null||(m.style.display="block")},ad=function(m){if(typeof m=="string"){m=al(m)}(m==null)||(m.style.display="none")},H=function(m){m&&m.parentNode&&m.parentNode.removeChild(m)},i=function(D,P,M){var E=/[a-z]+/ig,p;while((p=E.exec(P))!=null){var m=p[0];if(D.addEventListener){D.addEventListener(m,M,false)}else{if(D.attachEvent){D.attachEvent("on"+m,M)}else{D["on"+m]=M}}}},t=function(m,D,p){if(m.removeEventListener){m.removeEventListener(D,p,false)}else{if(m.detachEvent){m.detachEvent("on"+D,p)}else{m["on"+D]=null}}},ah=function(p){var m=[];aC(p,function(D,E){m.push(D+"="+encodeURIComponent(E)||"")});return m.join("&")},ax=function(){var p=/looyu_ext(\d)=(\w+)(?:&|$)/ig;var D=[],m;while((m=p.exec(L))!=null){D.push("ext"+m[1],m[2])}return D.length?("#params:"+D.join(",")):""},aC=function(D,p){for(var m in D){if(typeof D[m]!="function"&&D.hasOwnProperty(m)){p(m,D[m])}}},aw=function(){return !ao.strict?z.body:z.documentElement},aj=function(){return aw().scrollTop||z.body.scrollTop},aB=function(){return aw().scrollLeft||z.body.scrollLeft},b=function(){return ao.fix?0:aj()},W=function(){l(U)!=ag&&ak(U,ag);ak(Y,l(Y)||h,at);y&&az&&az.upvid&&az.upvid(B,A,ab.vId);setTimeout(W,60000)},ar=function(){var m=/^(https?:\/\/[^\/]*)\/.*$/i;return function(E){if(E==null){return false}m.exec(E);var D=RegExp.$1;m.exec(L);var p=RegExp.$1;return D==p}}(),aa=function(m){if(!m||m.indexOf("http")!=0){return""}if(m.length>500){m=m.substring(0,500)}return m},r=function(p,D,m){var E=z.createElement(p);m=m||z.body;aC(D,function(M,P){P&&E.setAttribute(M,P)});m.appendChild(E);return E},w=function(){var m={c:B,v:ab.vId,u:ab.uId,f:ab.confId,site:A};if(ab.counter){m.ct=ab.counter}if(ab.lang){m.lang=ab.lang}ay(m,{refer:aa(ab.refer),loc:aa(L)});if(ab.reseve&&ab.reseve!="null"){m.r=ab.reseve}m._d=+new Date();return ah(m)},av=function(p){if(!ab.uId){return"#"}var m=ab.chat+"/chat/p.do?";var M=1,E=[];if(!p||typeof p=="object"){if(p&&p.type){E.push("cId="+p.m)}if(p&&(p.sId||p.g)){E.push("command="+(p.force&&"forceChat"||p.sId&&"applyChat"||"inviteChat"));E.push("t="+p.type);E.push(p.sId?("n="+p.sId):("g="+p.g));M=p.g&&7||p.m&&3||p.force&&6||1}else{var D=ac.monParam&&ac.monParam.group;if(D&&D!="0"){E.push("g="+D)}M=5}}else{if(typeof p=="string"&&p){E.push(p);M=2}}E.push("md="+M);return m+E.join("&")+"&"+w()},a=function(E,aF){var D=aF||E&&av(E)||G&&G.talk||av("g=");var P="height=460,width=690,directories=no,location=no,menubar=no,resizable=yes,status=no,toolbar=no,top=100,left=200";try{var m=window.open(D,''+(+new Date()),P);m.focus()}catch(M){if(E.force){window.location=D}}return false},ay=function(D,p){if(arguments[2]){ay(D,arguments[2])}if(D&&p&&typeof p=="object"){for(var m in p){D[m]=p[m]}}return D},Z=function(p,E,m,D,P){P=P||o(200,75);p=(typeof p=="string")?al(p):p;var M=new x(p,E,m,D,P);s(M.start.doyoodg(M));return M},aq=function(D){var m=function(){var M=arguments,aF=M[0],P=aF.style.filter;if(M.length==2){var E=M[1];ao.msie&&(aF.style.filter="alpha(opacity="+E+")")||(aF.style.opacity=E/100)}else{if(ao.msie){return P&&P.indexOf("opacity=")>=0?(parseInt(P.match(/opacity=([^)]*)/)[1])):100}else{return aF.style.opacity*100}}};m(D,0);g(D);var p=function(){var E=m(D);if(E<100){m(D,E+5);setTimeout(p,100)}else{D.style.filter="";D.style.backgroundColor="transparent"}};D.style.backgroundColor="#FFF";p()},f=function(D,aK,aI){var aL=parseInt(D.offsetWidth||500),aF=parseInt(D.offsetHeight||300),aN=aw().clientWidth,aH=aw().clientHeight,p=[0-aF+b(),aB()+aN,aH+b(),0-aL+aB()],P=["top","left"];var aM=p[aK],aJ=aK%2,aG=aJ?(aI.left||(aN-aI.right-aL)):(aI.top||(aH-aI.bottom-aF)),E=aJ?"left":"top";var m=function(aO){D.style[E]=aO+"px"};m(aM);g(D);var M=function(){var aO=aG+(aJ?aB():b());if(Math.abs(aO-aM)>4){aM+=(aO>aM?4:-4);m(aM);setTimeout(M,1)}else{m(aO)}};M()},s=(function(){var E=[],m=false;var p=function(){if(C){return}C=true;for(var P=0;P<E.length;P++){E[P]()}};var D;if(z.addEventListener){D=function(){z.removeEventListener("DOMContentLoaded",D,false);p()}}else{if(z.attachEvent){D=function(){if(z.readyState==="complete"){z.detachEvent("onreadystatechange",D);p()}}}}var M=function(){if(C){return}try{z.documentElement.doScroll("left")}catch(P){setTimeout(M,1);return}p()};return function(aF){if(C||!!ab.ready){aF()}else{E.push(aF);if(!m){m=true;if(z.readyState==="complete"){return p()}if(z.addEventListener){z.addEventListener("DOMContentLoaded",D,false);window.addEventListener("load",p,false)}else{if(z.attachEvent){z.attachEvent("onreadystatechange",D);window.attachEvent("onload",p);var P=false;try{P=window.frameElement==null}catch(aG){}if(document.documentElement.doScroll&&P){M()}}}}}}})(),O=function(m){if(!!ab.ready){m()}else{i(window,"load",m)}},R=function(){var m=0;return function(){return"looyu_dom_"+m++}}();ac.util={openChat:a,chatURL:av,fly:Z};ay(Function.prototype,{doyoodg:function(p,m){var D=this;return function(){var E=m||arguments;return D.apply(p||window,E)}}});Function.prototype.delegate||(Function.prototype.delegate=Function.prototype.doyoodg);var I=function(){this.id=0;this.tasks=[];this.exec=this.execute.doyoodg(this)};ay(I.prototype,{addTask:function(m){if(typeof m=="function"){this.tasks.push(m)}},delTask:function(p){for(var m=0;m<this.tasks.length;m++){if(this.tasks[m]==p){this.tasks.splice(m);break}}},execute:function(){for(var m=0;m<this.tasks.length;m++){!this.tasks[m]||this.tasks[m]()}},start:function(m){if(this.id!=0){this.stop()}this.id=setInterval(this.exec,m)},stop:function(){if(this.id!=0){clearInterval(this.id)}this.id=0},started:function(){return this.id!=0}});var d=new I();var x=function(p,E,m,D,M){ay(this,{obj:p,handle:(typeof E=="string")?al(E):E,width:m||p.offsetWidth,height:D||p.offsetHeight,onmousemove:this.drag.doyoodg(this),onmouseup:this.drop.doyoodg(this),pos:M});if(this.handle){this.handle.style.cursor="move";this.handle.onmousedown=this.beginDrag.doyoodg(this)}};ay(x.prototype,{beginDrag:function(m){m=m||event;this.offs={x:m.offsetX||m.layerX,y:m.offsetY||m.layerY};i(z,"mousemove",this.onmousemove);i(z,"mouseup",this.onmouseup);z.onselectstart=function(){return false}},isMoved:function(D,p){var m=aw();return{x:D>0&&D<m.clientWidth-this.width+aB(),y:p>0&&p<m.clientHeight-this.height+aj()}},drag:function(D){D=D||event;var p=aw(),E=this.obj;var m=this.isMoved(D.clientX-this.offs.x+aB(),D.clientY-this.offs.y+aj());if(m.x){E.style.left=D.clientX-this.offs.x+"px"}if(m.y){E.style.top=D.clientY-this.offs.y+b()+"px"}},drop:function(m){t(z,"mousemove",this.onmousemove);t(z,"mouseup",this.onmouseup);z.onselectstart=null},showDiv:function(){var P=this.obj.style;if(typeof this.lastLeft=="undefined"){this.lastLeft=aB();this.lastTop=aj();var M=(this.pos.bottom?(aw().clientHeight-this.pos.bottom-this.height):this.pos.top);P.top=aj()+M+"px";return}if(P.visibility!="visible"){P.visibility="visible"}var E=aB(),m=aj();var aH=E-this.lastLeft,aF=m-this.lastTop;if(aH!=0){var aG=(P.left?"left":"right"),p=0;try{p=parseInt(P[aG]||0)}catch(D){}P[aG]=p+aH+"px";this.lastLeft=E}if(aF!=0){if(P.top){P.top=parseInt(P.top)+aF+"px"}this.lastTop=m}},start:function(){var m=this,p=function(M){var E=M.parentNode,D=function(P){return P.tagName.toLowerCase()};return(E&&D(E)=="body")};if(!p(this.obj)){O(function(){H(m.obj);z.body.appendChild(m.obj);m.start()});return}if(!ao.fix){this.func=this.showDiv.doyoodg(this);d.addTask(this.func);d.start(250)}else{this.obj.style.position="fixed"}},stop:function(){d.delTask(this.func)}});var V=function(m){this.obj=al("doyoo_monitor");ay(this,m,{width:412,height:158})};var T=function(m){this.obj=al("doyoo_panel");ay(this,m)};var af=function(){};ay(af.prototype,{showDiv:function(){this.obj=al("doyoo_mon_mask");if(!this.obj){this.obj=r("div",{id:"doyoo_mon_mask"});var m=aw();this.obj.style.width=Math.max(m.scrollWidth,m.clientWidth)+"px";this.obj.style.height=Math.max(m.scrollHeight,m.clientHeight)+"px"}g(this.obj)},hideDiv:function(){ad(this.obj)}});var S=new af();var Q=function(){this.defUI()};ay(Q.prototype,{defUI:function(){this.obj=r("div",{id:"looyu_p"});this.obj.innerHTML='<div class="looyu_p_tl looyu_p_t"><div class="looyu_p_tr looyu_p_t"><div class="looyu_p_tc looyu_p_t"><div id="looyu_p_h">X</div>&nbsp;</div></div></div><div class="looyu_p_ml looyu_p_m"><div class="looyu_p_mr  looyu_p_m"><div class="looyu_p_mc"><div id="looyu_p_c"></div></div></div></div><div class="looyu_p_bl  looyu_p_b"><div class="looyu_p_br  looyu_p_b"><div class="looyu_p_bc  looyu_p_b">&nbsp;</div></div></div>';i(al("looyu_p_h"),"click dblclick",this.hideDiv.doyoodg(this))},opt:function(D,M,E,m,aF,P){ay(this,{w:+D+26,h:+M+26,u:E,b:m,p:aF,f:P});var aG=al("looyu_p_c");ay(aG.style,{width:D+"px",height:M+"px"});ay(this.obj.style,{width:this.w+"px",height:this.h+"px"});this.obj.className="looyu_p_"+m;aG.innerHTML='<iframe id="looyu_p_f" frameborder="0" width="100%" height="100%" src="'+E+'"></iframe>';Z(this.obj,null,this.w,this.h,u(aF,this.w,this.h))},showDiv:function(){S.showDiv();var m=u(this.p,this.w,this.h);if(!this.p){m.top+=b();m.left+=aB()}aC(m,function(p){m[p]=m[p]+"px"});ay(this.obj.style,m);!this.f&&g(this.obj);this.f&&(this.f==1?aq(this.obj):f(this.obj,this.f-2,u(this.p,this.w,this.h)))},hideDiv:function(){S.hideDiv();ad(this.obj)}});var k=null;T.chatParam=function(E,m){var D="";if(E=="c"){D="n="+m}else{if(E=="g"){D="g="+(m&&+m||"")}}return D};ay(V.prototype,{defUI:function(M){var aH=function(aP,aO){return"url("+v+"images/monitor/"+aP+"_"+aO+".gif)"};var aG=this.index,P=aG,E=this.obj;if(aG<=8&&aG>0||aG>100){try{var p='<div id="doyoo_mon_left"></div><div id="doyoo_mon_back"><div id="doyoo_mon_head"></div><div id="doyoo_mon_main"></div><div id="doyoo_mon_foot"><a id="doyoo_mon_accept" href="#" onclick="doyoo.util.openChat();doyoo.util.accept();return false;"></a><div id="doyoo_mon_refuse" ></div></div></div><div id="doyoo_mon_right"></div><div class="doyoo_filter"></div>';E.innerHTML=p}catch(aJ){console&&console.log(aJ.message)}al("doyoo_mon_left").style.background=aH("mon_left",aG) +' 50.001% 0 no-repeat';al("doyoo_mon_back").style.background=aH("mon_back",aG);al("doyoo_mon_right").style.background=aH("mon_right",aG)+" no-repeat";if(ab.lang=="en"||ab.lang=="tc"){P=aG+"_"+ab.lang}al("doyoo_mon_accept").style.background=aH("accept",P);al("doyoo_mon_refuse").style.background=aH("refuse",P);ay(this,{width:412,height:158})}else{var aM={tc:"\u5f00\u59cb\u4ea4\u8c08",en:"Chat",sc:"\u5f00\u59cb\u4ea4\u8c08"},aL={tc:"\u7a0d\u540e\u518d\u8bf4",en:"Deny",sc:"\u7a0d\u540e\u518d\u8bf4"};var aI,p='<div id="doyoo_mon_inner" style="position:relative;width:100%;height:100%"><object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="{mw}" height="{mh}"><param name="movie" value="{mbg}"/><param name="wmode" value="transparent"/><embed src="{mbg}"  width="{mw}" height="{mh}" type="application/x-shockwave-flash" wmode="transparent"></embed></object><div id="doyoo_mon_head" style="position:absolute;left:{mtl}px;top:{mtt}px;width:{mtw}px;height:{mth}px"></div><a id="doyoo_mon_accept" href="#" onclick="doyoo.util.openChat();doyoo.util.accept();return false;" style="position:absolute;display:block;left:{mbal}px;bottom:{mbab}px;width:{mbaw}px;height:{mbah}px;background:url({mbabg}) no-repeat;text-align:center">'+aM[ab.lang||"sc"]+'</a><div id="doyoo_mon_refuse"  style="position:absolute;display:block;left:{mbdl}px;bottom:{mbdb}px;width:{mbdw}px;height:{mbdh}px;background:url({mbdbg}) no-repeat;text-align:center">'+aL[ab.lang||"sc"]+'</div><div id="doyoo_mon_main" style="position:absolute;left:{mml}px;top:{mmt}px;width:{mmw}px;height:{mmh}px"></div></div>',D='<div id="doyoo_mon_inner" style="position:relative;width:100%;height:100%;background:url({mbg})"><div id="doyoo_mon_head" style="position:absolute;left:{mtl}px;top:{mtt}px;width:{mtw}px;height:{mth}px"  ></div><a id="doyoo_mon_accept" href="#" onclick="doyoo.util.openChat();doyoo.util.accept();return false;" style="position:absolute;display:block;left:{mbal}px;bottom:{mbab}px;width:{mbaw}px;height:{mbah}px;background:url({mbabg}) no-repeat"></a><div id="doyoo_mon_refuse" href="#" style="position:absolute;display:block;left:{mbdl}px;bottom:{mbdb}px;width:{mbdw}px;height:{mbdh}px;background:url({mbdbg}) no-repeat"></div><div id="doyoo_mon_main" style="position:absolute;left:{mml}px;top:{mmt}px;width:{mmw}px;height:{mmh}px"></div></div>';if(aG>0){var m=v+"images/monitor/"+aG+"_btn.gif",aI={mw:500,mh:200,mbg:v+"swf/"+aG+".swf",mtl:130,mtt:15,mtw:240,mth:30,mbal:150,mbab:40,mbaw:66,mbah:22,mbdl:300,mbdb:40,mbdw:66,mbdh:22,mml:130,mmt:40,mmw:270,mmh:80,mbabg:m,mbdbg:m}}else{aI=this.style;var aK=aI.elepos.split(" "),aN=["mtl","mtt","mtw","mth","mml","mmt","mmw","mmh","mbal","mbab","mbaw","mbah","mbdl","mbdb","mbdw","mbdh"];for(var aF=0;aF<aN.length;aF++){aI[aN[aF]]=parseInt(aK[aF])}}E.innerHTML=X(/^.*\.swf$/.test(aI.mbg)?p:D,aI);ay(this,{width:aI.mw,height:aI.mh})}this.main=al("doyoo_mon_main"),this.head=al("doyoo_mon_head");this.accLink=al("doyoo_mon_accept");this.denyLink=al("doyoo_mon_refuse");this.head=al("doyoo_mon_head");M&&(this._defui=true)},getPos:function(){return u(this.pos,this.width,this.height)},buildMain:function(m){m=m||this.text;this.main.innerHTML=/^https?:\/\/.*/.test(m)?('<iframe frameborder="0" width="100%" height="100%" src="'+m+'"></iframe>'):m},builder:function(){var m=this.obj;if(typeof beforeLooyuMonitorBuilder=="function"){beforeLooyuMonitorBuilder.apply(this)}else{this.defUI(true)}this.head&&(this.head.innerHTML=this.title);this._defui||(this.accLink.onclick=this.accept.doyoodg(this));this.denyLink.onclick=this.deny.doyoodg(this);ay(m.style,{width:this.width+"px",height:this.height+"px"});this.flier=Z(m,this.head,this.width,this.height,this.getPos())},autoShow:function(){if(this.auto>=0&&!this.autoTimer&&(this.offShow||this.status)){var E=new Date();var P=E.getHours(),m=E.getMinutes();function D(aH){var aG=aH.replace(/:/g,"");while(aG.charAt(0)=="0"&&aG.length>1){aG=aG.substring(1)}return parseInt(aG)}var M=D(this.start),p=D(this.end);var aF=D(P+":"+((m<10)?("0"+m):m));if(M<=aF&&p>=aF){this.autoTimer=setTimeout(this.showDiv.doyoodg(this,[true]),this.auto*1000)}}},leave:function(){var p=new Image(1,1);p.src=ab.mon+"/s?"+this.leaveUrl();var m=al("looyu_leave");m&&(m.src="about:block");if(this.timer){clearInterval(this.timer);this.timer=0}},refresh:function(m){this.leave();this.record(m)},destory:function(){this.leave();H(this.obj);clearTimeout(this.autoTimer);this.flier.stop()},bindLeave:function(){if(!ao.safari){i(window,"beforeunload",this.leave.doyoodg(this))}if(ao.safari){var m=al("looyu_leave"),p=ab.mon+"/l.jsp?"+this.leaveUrl();m&&(m.src=p)||r("iframe",{id:"looyu_leave",src:p})}},leaveUrl:function(){return"c=l&i="+B+"&v="+ab.vId+"&p="+ab.pId+"&_t="+ +new Date()},pump:function(E){if(E==null){return}switch(E.c){case"a":var D=E.p.split("|");ab.pId=D[0];this.text=X(this.text,{area:(E.area||"")});this.buildMain();this.autoShow();parent&&parent.doyoo&&(parent.doyoo.env.pId=D[0]);if(D.length>1){ab.mId=D[1]}var p=E.t&&(+E.t)||10;p<3&&(p=3);if(p&&!this.timer){this.timer=window.setInterval(this.driver.doyoodg(this),p*1000)}E.e&&this.doEvent(E.e);break;case"e":var M=E.es;for(var m in M){(M.hasOwnProperty(m) &&typeof M[m]=="object")&&this.doEvent(M[m])}break;case"ac":case"de":break}},driver:function(){var m=ab.mon+"/s?c=e&i="+B+"&v="+ab.vId+"&p="+ab.pId;N(m,this.pump.doyoodg(this))},force:function(m){m&&N(ab.mon+"/s?c=ac&i="+B+"&v="+ab.vId,null);var aI=this.mini,E=av(this.chat);var zA=0;E=E.replace(/command=[^&$]*/, function (){ zA = 1;return 'command=forceChat';});zA||(E=E+'&command=forceChat');m.sId=null;if(aI==3){this.chat.force=false;z.location.href=E;return}var aH=al("doyoo_f_frame");if(aI!=4&&!aH){var p=L+(L.indexOf("?")==-1?"?":"&")+ +new Date();aH=r("iframe",{id:"doyoo_f_frame",src:(aI==1?"about:blank":p),frameborder:"0"});aH.className="doyoo_f_frame";try{if(this.mini==1){var aJ=(function(){aH.style.display="none";var aK=[],aM=z.firstChild,aN=z.getElementsByTagName("html")[0],aL=aN.getAttribute("xmlns");if(ao.msie){ao.strict&&aK.push(aM.text||"")}else{aK.push('<!DOCTYPE html PUBLIC "',aM.publicId,'" "',aM.systemId,'">')}aK.push("<html ",aL?('xmlns="'+aL+'"'):"",">");aK.push(ae,"</html>");aH.style.display="block";return aK.join("")})();var aI=aH.contentWindow.document;aI.writeln(aJ)}z.getElementsByTagName("html")[0].className+=" doyoo_f_original";z.body.scroll="no"}catch(M){}}aH&&(aH.style.top=aj()+"px");var D=al("doyoo_f_chat");if(!D){D=r("div",{id:"doyoo_f_chat"});D.className="doyoo_f_chat";D.style.backgroundImage="url("+v+"images/minichat/"+(this.miniStyle||1)+".gif)";D.innerHTML='<div id="doyoo_f_head"><div id="doyoo_f_close"></div></div><div  id="doyoo_f_main"></div>';g(D);var aG=function(){var aK=al("doyoo_f_work");aK.src="about:blank";ad(D)};i(al("doyoo_f_close"),"click dblclick",aG)}var aF=al("doyoo_f_main");aF.innerHTML='<iframe frameborder="0" id="doyoo_f_work" width="100%" height="100%" style="height:100%" src="'+E+'"/>';g(aF);var P={right:10,bottom:10};f(D,2,P);Z(D,"doyoo_f_head",411,263,P)},doEvent:function(p){var D=function(M){var E=(M.m&&M.m.indexOf("<FORCE>")!=-1)||M.e=="s1";return{type:(M.e=="11"?1:0),force:E,cId:M.c,sId:M.s,dId:M.d,m:E?0:M.m,g:M.g}},m=this;this.chat=D(p);switch(p.e){case"11":case"1":case"s1":if(this.chat.force&&this.mini){setTimeout(function(){m.force(m.chat)},(p.d||0)*1000);return}this.buildMain();this.showDiv();break;case"8":case"9":p.m&&this.buildMain(p.m);this.showDiv();break;case"s0":this.buildMain(p.t);setTimeout(function(){m.showDiv()},(p.d||0)*1000);break;case"s2":k||(k=new Q());k.opt(p.w,p.h,p.url,p.b||0,p.p||0,p.f||0);setTimeout(function(){k.showDiv()},(p.d||0)*1000);break}},hideDiv:function(m){S.hideDiv();ad("doyoo_monitor");F&&!F.hidden&&g(F.obj);if(this.hideTimer){clearTimeout(this.hideTimer);this.hideTimer=0}this.mayLoop(m);this.visible=false},accept:function(){this.hideDiv();var m=this.chat;m&&(m.force=false);m&&N(ab.mon+"/s?c=ac&i="+B+"&v="+ab.vId,null);this._defui||a(m,this.talk);m&&(m.sId=null)},deny:function(){this.hideDiv();var m=this.chat;m&&N(ab.mon+"/s?c=de&i="+B+"&v="+ab.vId+"&n="+m.sId,null)},mayLoop:function(m){this.loop&&(this.loopTimer=setTimeout(this.showDiv.doyoodg(this,[m]),this.loop*1000))},showDiv:function(p){p&&!this.visible&&(this.chat=null);this.talk=av(this.chat);this.hidePanel&&F&&F.close();var m=this.getPos();if(!this.pos){m.top+=b();m.left+=aB()}aC(m,function(D){m[D]=m[D]+"px"});ay(this.obj.style,m);this.mask&&S.showDiv();!this.fx&&g(this.obj);this.fx&&(this.fx==1?aq(this.obj):f(this.obj,this.fx-2,this.getPos()));this.visible=true;window.focus();if(this.autoTimer){clearTimeout(this.autoTimer)}if(this.loopTimer){clearTimeout(this.loopTimer);this.loopTimer=0}if(this.autoHide){this.hideTimer=setTimeout(this.hideDiv.doyoodg(this,[p]),1000*this.autoHide)}},record:function(M){M=M||{};var D=M.t||z.title,p=M.u||L;if(D.length>100){D=D.substring(0,100)}var E={c:"a",i:B,v:ab.vId,u:ab.uId,p:ab.pId||"",ref:ab.refer,site:A,h:D,w:p,scn:(screen.width+"*"+screen.height),t:this.type||"",ct:ab.counter,r:ab.reseve||""};var m=ab.mon+"/s?"+ah(E);N(m,this.pump.doyoodg(this))}});ay(T.prototype,{defUI:function(){this[this.category+"Builder"]()},builder:function(){var M=this.obj,P,D=this;if(this.position!=-1){var m=(this.position==0)?"left":"right";P={top:this.vertical};P[m]=this.horizon;var E={position:"absolute",top:P.top+b()+"px"};E[m]=this.horizon+"px";ay(M.style,E)}if(typeof beforeLooyuPanelBuilder=="function"){beforeLooyuPanelBuilder.apply(this)}else{this.defUI()}if(this.position!=-1){this.flier=Z(this.obj,this.handler,this.width,this.height,P)}g(M);this.closer&&i(this.closer,"click dblclick",function(p){D.close(p);D.hidden=true})},destory:function(){H(this.obj);this.flier.stop()},winBuilder:function(){var D=this.obj;if(this.mode==2){ay(D.style,{width:(this.width+"px"),height:(this.height+"px"),background:("url("+this.panelBG+") no-repeat")});var aM=['<div id="doyoo_panel_main">'],M=this.customers.groups,aL='<div class="doyoo_panel_cell" style="width:100%;height:{height}px;line-height:{height}px;background:url({cellbk}) no-repeat left center;{cellMargin}" onclick="doyoo.util.openChat(\'g={group}\');return false"><span style="{textStyle};height:{height}px;line-height:{height}px;{textMargin}">{groupName}</span></div>';for(var aH=0;aH<M.length;aH++){var aF=M[aH];aM.push(X(aL,{height:this.cellHeight,cellbk:(aF.active?this.cellOnline:this.cellOffline),group:aF.id,groupName:aF.name,cellMargin:this.cellMargin,textStyle:aF.active&&this.onlineText||this.offlineText,textMargin:this.textMargin}))}aM.push("</div>");D.innerHTML=aM.join("");var E=al("doyoo_panel_main"),p=this.panelMain.split(" ");ay(E.style,{left:p[0]+"px",top:p[1]+"px",width:p[2]+"px",height:p[3]+"px"});return}if(this.mode==1){D.innerHTML='<div id="ohead"><div id="ocls" ></div><div id="omin" onclick=""></div><div id="otitle"></div><div id="oentrance"></div></div><div id="ocontent"></div><div id="ofoot"><div onclick=javascript:window.open("http://www.looyu.com")></div></div>';al("otitle").innerHTML=this.title;var aJ=function(aO,aN){return"url("+v+"images/floatwin/"+aN+"_"+aO+".gif)"};al("ohead").style.background=aJ(this.index,"head")+" no-repeat";al("ocontent").style.background=aJ(this.index,"back")+" repeat-y";al("ofoot").style.background=aJ(this.index,"foot")+" no-repeat"}else{if(this.mode==0){this.width+=8;var aI='<div id="nhead"><div id="ncls">X</div><div id="ntitle"></div></div><div id="ncontent" ></div>';var m={sc:"\u7535\u8bdd\uff1a",en:"Number:",tc:"\u96fb\u8a71\uff1a"};if(!an(this.phone)){var P=(an(ab.lang)||"tc,sc,en,".indexOf(ab.lang)==-1)?"sc":ab.lang;aI+='<div id="nphone">'+m[P]+"<div>"+this.phone+"</div></div>"}aI+='<div id="nfoot" onclick=javascript:window.open("http://www.looyu.com")><div></div></div>';D.innerHTML+=aI;D.className="doyoo_pan_flat";try{al("nhead").style.backgroundColor=this.headBgClr}catch(aK){}try{al("nhead").style.color=this.headClr}catch(aK){}try{al("ntitle").innerHTML=this.title}catch(aK){}try{ay(D.style,{borderColor:this.borderClr})}catch(aK){}}}D.style.width=(this.width?this.width:144)+"px";var aG=al((this.mode==1)?"ocontent":"ncontent");aG.innerHTML='<div id="allcontent">'+this.buildList()+"</div>";(this.mode==0)||(aG.style.height=this.height+"px");this.handler=(this.mode==1)?"ohead":"nhead";this.closer=al(this.mode==1?"ocls":"ncls");if(this.mode==0){if(this.cols>20){ay(aG.style,{height:350+"px",overflowY:"auto"});D.style.width=this.width+20+"px"}}},buildList:function(){var aF="",aG=this.customers,aM=aG.groups,E=aG.mode,aH=0;var aN=function(aW,m,aR,aY,aX){var aO=this.mode,aT=["doyoo_offline","doyoo_online","doyoo_offline","doyoo_offline","doyoo_offline","doyoo_other","doyoo_other","doyoo_other"],aU={sc:["\u7559\u8a00","\u81ea\u52a9","\u7535\u8bdd","\u77ed\u4fe1"],en:["MSG","AUTO","CALL","SMS"],tc:["\u7559\u8a00","\u81ea\u52a9","\u96fb\u8a71","\u7c21\u8a0a"]},aV={sc:["\u5728\u7ebf","\u5fd9\u788c","\u79bb\u5f00"],en:["CHAT","BUSY","AWAY"],tc:["\u5728\u7dda","\u5fd9\u788c","\u96e2\u958b"]},aS=ab.lang&&(aV[ab.lang]&&ab.lang)||"sc",aP=(aW!="gw"&&aW!="d"),aQ='<div class="'+(aW=="c"?"group_content":"group_title")+'" ';if(aP){aQ+='onclick=javascript:doyoo.util.openChat("'+T.chatParam(aW,aR)+'") '}aQ+=">";if(aP){aQ+='<div class="'+(aY&&aT[aY]||aT[aX+4])+'">';aQ+='<div class="doyoo_status">'+(aY==0?aU[aS][aX]:aV[aS][aY-1])+"</div>"}aQ+='<div class="doyoo_link" ';aQ+=">"+((aO==1&&aW!="c")?"<span>&gt;&nbsp;</span>":"")+m+"</div></div>";if(aP){aQ+="</div>"}return aQ};for(var aK=0;aK<aM.length;aK++){var aJ=aM[aK],aL=E&&((aJ.mode==2)&&"g"||"gw")||"d",M=E&&(aJ.active&&1||aJ.online&&2),D=(aL=="g")&&(aJ.phone&&2||aJ.sms&&3);aH++;aF+=aN(aL,aJ.name,aJ.id,M,D);if(aL=="d"||aL=="gw"){var p=aJ.customers;for(var aI=0;aI<p.length;aI++){var P=p[aI];aH++;aF+=aN("c",P.name||P.id,P.id,P.status,P.offline)}}}this.cols=aH;return aF},close:function(m){ad("doyoo_panel");var p=m||window.event;window.event&&(p.cancelBubble=true)||p&&p.stopPropagation()},iconBuilder:function(){function m(P){return P.replace("http://file.doyoo.net/WebModule/",v).replace("file.doyoo.net","static.doyoo.net")}var D=this.obj;D.className="doyoo_pan_icon";this.online=m(this.online);this.offline=m(this.offline);ay(D.style,{width:this.width+"px",height:this.height+"px",backgroundImage:"url("+(this.status&&this.online||this.offline)+")"});var M=R(),p=R();D.innerHTML='<div class="doyoo_pan_icon_inner" id="'+M+'"><a href="#" id="'+p+'" style="display:block;width:100%;height:100%;">&nbsp;</a></div>';al(p).onclick=a.doyoodg(this,[T.chatParam(this.mode==0?"c":"g",this.target)]);if(this.closable){var E=r("div",{},al(M));E.className="doyoo_pan_close";this.closer=E}},textBuilder:function(){var m=this.obj;ay(m.style,{color:this.color,fontSize:this.size+"px"});m.innerHTML+=this.content;m.onclick=a.doyoodg(this,[T.chatParam(this.mode==0?"c":"g",this.target)])}});var e=function(p){p=p||{};ag=l(U)||"";var aF=z.referrer,M=l(Y)||"",aH=new RegExp("_"+B+":(\\d+)"),D=ag.match(aH);var m=function(aK){var aJ=new RegExp(aK+":([^,]*)","i");var aI=M.match(aJ);return unescape(aI&&aI[1]||"")};var aG=function(){var aJ,aI=+new Date();if((aJ=/^([^_]+)_([\d]+)/i.exec(p.vid))!=null&&Number(aJ[2])>aI){return aJ[1]}return null};var E=p.ct||D&&D.length&&D[1]||0;var P={uId:p.id||ag.match(/^([^_]+)/)||ab.vId,vId:p.vid&&aG()||m("v")||++E&&ab.vId,counter:E,refer:!aF||ar(aF)?m("ref"):aF,reseve:(typeof reseveKey=="undefined"?(m("r")||ax()):reseveKey)};P.uId!="undefined"||(P.uId=ab.vId);P.uId=/[^\w\d]*([\w\d]+)[^\w\d]*/.exec(P.uId)[1];if(P.uId.length<30){P.uId+=+new Date()}ag=P.uId+"_"+B+":"+E;P.mon=m("mon")||ab.mon;h="v:"+P.vId+",ref:"+escape(P.refer)+",r:"+escape(P.reseve)+",mon:"+P.mon;return P};var G,F,c=false,am=false;var aD=function(){var m=z.getElementsByTagName("object"),E=z.getElementsByTagName("embed");for(var p=0;p<m.length;p++){var D=z.createElement("param");D.name="wmode";D.value="transparent";m[p].appendChild(D)}for(var p=0;p<E.length;p++){E[p].setAttribute("wmode","transparent")}};i(window,"load",function(){ab.ready=true;ab.fixFlash&&aD()});ac.domReady=function(){return !!ab.ready};ac.shareReady=function(m){az=aA("looyuShare")};ac.probe=function(){if(c){return null}c=true;return{c:B,id:"",vid:"",ct:"",site:A}};ac.init=function(p){if(am){return}try{am=true;ay(ab,e(p));K?ac.start():s(ac.start.doyoodg(ac))}catch(m){console&&console.log(m.message)}return{c:B,id:ab.uId,vid:ab.vId,ct:ab.counter,site:A}};ac.start=function(){ae=z.getElementsByTagName("html")[0].innerHTML;ae=ae.replace(/<script[^<>]+doyoo\.net\/(?!j\.jsp)[^<>]+><\/script>|<link[^<>]+doyoo\.net[^<>]+>|<object((?!<\/object>).)*looyuShare((?!<\/object>).)*file\.doyoo\.net((?!<\/object>).)*<\/object>/ig,"");ae=ae.replace(/<div\s+id=['|"]doyoo_[^><]+['|"]\s*>\s*<\/div>/ig,"");ac.panelParam&&(F=new T(ac.panelParam))&&F.builder();var p=window,m=false,D=10;while(p!=top&&(--D)){p=p.parent;if(p.doyoo&&p.doyoo.monitor){m=true;break}}if(p==window||!m){ac.monParam&&(ac.monitor=G=new V(ac.monParam))&&G.builder();G&&G.bindLeave();G&&(doyoo.util.accept=G.accept.doyoodg(G));G&&s(G.record.doyoodg(G))}else{p.doyoo.monitor.refresh({t:z.title,u:L})}W()};s(function(){C=true;var P=ac.sniffer,aI={};if(P){var m=P.ids.split(","),D="looyu_bound",M=P.gids.split(","),E=new RegExp(P.ids.replace(/,/ig,"|"),"ig");for(var aG=0;aG<m.length;aG++){aI[m[aG]]=M[aG]}var aF=function(aK,aL){if(aK.nodeType==3){return aL.getAttribute(D)}return aK.getAttribute(D)};var aJ=function(aK,aL){(aK.nodeType==3&&aL||aK)["setAttribute"](D,1)};var p=function(aN){var aL=aN.parentNode;if(aF(aN,aL)){return}if(aN.nodeType==3&&aL.nodeName!="A"){if(aN.data.search(E)==-1){return}var aK=z.createElement("span");aK.innerHTML=aN.data.replace(E,function(aO){return'<a href="#" onclick="doyoo.util.openChat(\'g='+aI[aO]+"');return false\">"+aO+"</a>"});aL.replaceChild(aK,aN)}else{if(aN.nodeType==1){var aM=E.exec(aN.id)||E.exec(aN.className)||aN.nodeName=="IMG"&&E.exec(aN.src);if(!aM){return}i(aN,"click dblclick",doyoo.util.openChat.doyoodg(null,["g="+aI[aM[0]]]));aN.style.cursor="pointer"}}aJ(aN,aL)};var aH=function(aN){var aM=aN.childNodes;try{p(aN)}catch(aL){}if(aM&&aM.length>0){for(var aK=0;aK<aM.length;aK++){aH(aM[aK])}}};if(m.length){aH(z.body)}}});y?q("looyuShare","looyu2.swf"):ac.init();setTimeout(function(){c||ac.init()},2*1000)})(doyoo);
