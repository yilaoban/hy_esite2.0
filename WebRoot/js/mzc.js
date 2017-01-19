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
                    //top = defaultTop + 'px';
                   	top = 600;
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