	(function($){
        $.fn.UIDialog = function(options){
            var defaults = {
                IE         :$.browser.msie,
                IE6        :$.browser.version == 6,
                FIREFOX    :$.browser.mozilla,
                window     :$(window),
                document   :$(document),
                body       :$(document.body),
                id         :null,  // 需要赋值的ID
                callOnOpen :null,  // 弹出后调用
                callOnClose:null,  // 关闭后调用
                eventType  :null,  // 事件类型 click, blur, change, dblclick, error, focus, load, mousedown, mouseout, mouseup
                idContent  :null,  // 需要添加的id
                width      :'auto', // 宽
                height     :'auto', // 高
                top:		'auto',
                title      :'消息',  // 标题
                content    :'测试内容'  // 内容
            };
            var opts = $.extend({}, defaults, options);

            var instance = {
                // 初始化方法
                init: function(opts, me){
                    var that = this,
                        cache = $.UIDialog.cache;
                    for(var p in opts){ // opts 绑定到 instance
                        this[p] = opts[p];
                    }
                    that.me = me; // 被调用者
                    that.createDialog();
                    that.createMask();
                    that.mask.fadeTo('slow', 0.5).click(function(){
                        that.close();
                    });
                    cache.push(that.dialog);
                    cache.push(that.mask);
                    that.dialog.fadeIn('slow', function(){ that.callOnOpen && that.callOnOpen(); });
                    that.setZIndex();
                },
                // 创建容器
                createDialog: function(){
                    var that = this,
                        dialog = $('<div class="ui-dialog"></div>').appendTo(this.body).hide(),
                        dialogTitle = $('<h1 class="ui-dialog-title"></h1>').appendTo(dialog),
                        dialogBody = $('<div class="ui-dialog-body"></div>').appendTo(dialog),
                        titleClose = null;
                    opts.id && dialog.attr('id', opts.id);
                    that.dialog = dialog;
                    that.title ? dialogTitle.append(that.title) : title.hide();
                    titleClose = $('<a class="ui-close" href="###">关闭</a>').appendTo(dialogTitle).click(function(){ that.close(); });
                    dialogBody.append(that.content);
//                    dialogBody.append(that.me.next().html());
                    that.IE6 && dialog.css({position:'absolute'});
                    that.setSize();
                    return that;
                },
                // 创建模板
                createMask: function(){
                    var that = this,
                        body = that.body,
                        windowWidth = that.window.width(),
                        mask = $('<div class="ui-mask"></div>').appendTo(body).hide();
                    that.mask = mask;
                    mask.css({width: windowWidth, height: that.document.height()});
                    that.IE6 && mask.html('<iframe src="about:blank" style="width:100%;height:100%;position:absolute;top:0;left:0;z-index:-1;filter:alpha(opacity=0)"></iframe>'); // 添加全屏iframe以防止select穿透
                    return that;
                },
                // 设置大小
                setSize: function(){
                    var that = this,
                        dialog = that.dialog,
                        window = that.window,
                        windowWidth = window.width(),
                        windowHeight = that.top,
                        left, top;
                    dialog.css({width: that.width, height: that.height});
                    var defaultTop = Math.floor((windowHeight - dialog.outerHeight()) / 2);
                    left = (windowWidth - dialog.width()) / 2 + 'px';
                    top = defaultTop + 'px';
                    dialog.css({left: left, top: top});
                    return that;
                },
                // 设置堆叠值
                setZIndex: function(){
                    var that = this,
                        dialog = that.dialog,
                        mask = that.mask,
                        indexVal = $.UIDialog.globalIndex++; // 引用全局变量，否则每次初始化后值为原始值
                    dialog && dialog.css({zIndex: indexVal});
                    mask && mask.css({zIndex: indexVal - 1});
                    return that;
                },
                // 关闭
                close: function(){
                    var that = this,
                        cache = $.UIDialog.cache,
                        dialog = cache[cache.length - 2],
                        mask = cache[cache.length - 1];
                    dialog.trigger('unload').unbind('click');
                    mask.trigger('unload').unbind('click');
                    that.slowRemove(dialog).slowRemove(mask);
                    cache.splice(cache.length - 2, 2);
                    return that;
                },
                // 渐隐
                slowRemove: function(ele){
                    var that = this;
                    ele.fadeOut('slow', function(){
                        that.callOnClose && that.callOnClose();
                        $(this).remove();
                    });
                    return that;
                }
            };
            var eventType = opts.eventType;
            if(eventType){ // 如果是 $.fn.UIDialog 方式调用
                return this.each(function(index){
                    $(this).bind(eventType, function(){
                        instance.init(opts, $(this));
//                        return false;
                    });
                });
            } else { // 如果是 $.UIDialog 方式调用
                instance.init(opts, $(this));
            }
        };
        $.UIDialog = function(s){
            $.fn.UIDialog(s);
        };
        $.UIDialog.globalIndex = new Date().getFullYear() + '' + new Date().getHours();  // 全局堆叠值,不能超过最大值(2147483647) From: http://softwareas.com/whats-the-maximum-z-index
        $.UIDialog.cache = []; // 缓存对象
    })(jQuery);

	/*标签页切换*/
	function showTab(n){
		$(".stab").removeClass("tabon");
		$(".stab:eq("+(n-1)+")").addClass("tabon");
		$(".tab_content").hide();
		$("#tab"+n).show();
		pagemark = 0;
		var m = "tab"+n;
	}
	
	/*标签页切换*/
	function switchCat(n){
		$(".cat_content").hide();
		$("#cat_"+n).show();
	}	
	
	(function($){
	$.fn.extend({
	        Scroll:function(opt,callback){
	                //参数初始化
	                if(!opt) var opt={};
	                var _this=this.eq(0).find("ul:first");
	                var        lineH=_this.find("li:first").height(), //获取行高
	                        line=opt.line?parseInt(opt.line,10):parseInt(this.height()/lineH,10), //每次滚动的行数，默认为一屏，即父容器高度
	                        speed=opt.speed?parseInt(opt.speed,10):500, //卷动速度，数值越大，速度越慢（毫秒）
	                        timer=opt.timer?parseInt(opt.timer,10):3000; //滚动的时间间隔（毫秒）
	                if(line==0) line=1;
	                var upHeight=0-line*lineH;
	                //滚动函数
	                scrollUp=function(){
	                        _this.animate({
	                                marginTop:upHeight
	                        },speed,function(){
	                                for(i=1;i<=line;i++){
	                                        _this.find("li:first").appendTo(_this);
	                                }
	                                _this.css({marginTop:0});
	                        });
	                }
	                //鼠标事件绑定
	                _this.hover(function(){
	                        clearInterval(timerID);
	                },function(){
	                        timerID=setInterval("scrollUp()",timer);
	                }).mouseout();
	        }
	})
	})(jQuery);
		
	$(document).ready(function() {
	     $(".modal").mouseover( function() {
	          $(this).addClass("shadow");
	     }).mouseout( function(){
	          $(this).removeClass("shadow");
	     });
	});
	
/*字数*/
    var maxstrlen = 70;
    function checkWord(c) {
        len = maxstrlen;
        var str = c.value;
        myLen = getStrleng(str);
        var wck = $("#sharetab #wordCheck");
        if (myLen > len * 2) {
        	wck.text("-"+Math.ceil((myLen - len * 2) / 2));
        	return false;
        }
        else {
            wck.text(Math.floor((len * 2 - myLen) / 2));
            return true;
        }
    };
    function getStrleng(str) {
        myLen = 0;
        i = 0;
        for (; (i < str.length); i++) {
            if (str.charCodeAt(i) > 0 && str.charCodeAt(i) < 128)
                myLen++;
            else
                myLen += 2;
        }
        return myLen;
    };
    
    
    /**
	 * 时间对象的格式化;
	 */
	Date.prototype.format = function(format) {
	    /*
	     * eg:format="YYYY-MM-dd hh:mm:ss";
	     */
	    var o = {
	        "M+" :this.getMonth() + 1, // month
	        "d+" :this.getDate(), // day
	        "h+" :this.getHours(), // hour
	        "m+" :this.getMinutes(), // minute
	        "s+" :this.getSeconds(), // second
	        "q+" :Math.floor((this.getMonth() + 3) / 3), // quarter
	        "S" :this.getMilliseconds()
	    // millisecond
	    }
	 
	    if (/(y+)/.test(format)) {
	        format = format.replace(RegExp.$1, (this.getFullYear() + "")
	                .substr(4 - RegExp.$1.length));
	    }
	 
	    for ( var k in o) {
	        if (new RegExp("(" + k + ")").test(format)) {
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
	                    : ("00" + o[k]).substr(("" + o[k]).length));
	        }
	    }
	    return format;
	}
	
	function showPopdiv(num,vurl){
		fuckoff();
		if(num == 1){
			$("#videoframe").attr("src",vurl);
		}
		$("#popdiv"+num).fadeIn();
	}
	
	$(document).ready(function(){
		$(".popdiv").click(function(){
		  $(".popdiv").fadeOut();
		});
	})
	
	function transformer(num) {
		$(".popdiv").fadeOut();
		$("#transformer"+num).fadeIn();
		$("#rightdiv").css("width","458px");
		$("#leftdiv").css("width","480px");
		$("#block1").css("width","294px");
		$("#block2").css({"width":"184px","background-position":"85%"});
		$("#block4").css({"width":"230px","background-position":"65%"});
	}
	
	function fuckoff(){
			$(".transformer").hide();
			$("#rightdiv").css("width","255px");
			$("#leftdiv").css("width","683px");
			$("#block1").css("width","355px");
			$("#block2").css({"width":"326px","background-position":"right"});
			$("#block4").css({"width":"433px","background-position":"right"});
			$("#videoframe").attr("src","");
		}
		
	/*翻页*/
	function fanye(pagely,pon,obj){
		var divid = $(obj).parent().attr("id");
		t_slider(pagely,divid,pon);
	}
	
	var pagemark = 0;
	function t_slider(pagely,divid,pon){
		var getdiv = $("#"+divid+" li");
		var li_l = getdiv.length;
		li_l = Math.ceil(li_l/pagely);
		if(pon == "n"){
			pagemark = pagemark + 1;
		} else {
			pagemark = pagemark - 1;
		}
		if(pagemark >= li_l ){
			pagemark = pagemark%li_l;
		} else if (pagemark < 1){
			pagemark = (pagemark+li_l)%li_l;
		}
		getdiv.hide();
		getdiv.slice(pagely*pagemark,pagely+pagely*pagemark).fadeIn();
	}