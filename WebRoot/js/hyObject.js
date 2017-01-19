function Map() {
 var struct = function(key, value) {
  this.key = key;
  this.value = value;
 }
 
 var put = function(key, value){
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
    this.arr[i].value = value;
    return;
   }
  }
   this.arr[this.arr.length] = new struct(key, value);
 }
 
 var get = function(key) {
  for (var i = 0; i < this.arr.length; i++) {
   if ( this.arr[i].key === key ) {
     return this.arr[i].value;
   }
  }
  return null;
 }
 
 var remove = function(key) {
  var v;
  for (var i = 0; i < this.arr.length; i++) {
   v = this.arr.pop();
   if ( v.key === key ) {
    continue;
   }
   this.arr.unshift(v);
  }
 }
 
 var size = function() {
  return this.arr.length;
 }
 
 var isEmpty = function() {
  return this.arr.length <= 0;
 } 
 this.arr = new Array();
 this.get = get;
 this.put = put;
 this.remove = remove;
 this.size = size;
 this.isEmpty = isEmpty;
}

function hyevent(id,start,end,startnote,endnote,logined,sharecontent,shareimg,sharetype){ 
    this.id=id; 
    this.start=start;
    this.end=end; 
    this.startnote=startnote; 
    this.endnote=endnote; 
    this.logined=logined; 
    this.sharecontent=sharecontent;
    this.shareimg=shareimg; 
    this.sharetype=sharetype; 
       
}
function hyuser(uid,type,featureid,hdid,beforeshare,aftershare,joined){ 
   this.id=uid; 
   this.type=type;
   this.featureid=featureid; 
   this.hdid=hdid; 
   this.beforeshare=beforeshare; 
   this.aftershare=aftershare;
   this.joined=joined; 
}
 
function hyrel(key,value){ 
   this.key=key; 
   this.value=value;
}
 
 var eventMap = new Map();
 var userMap = new Map();
 var relationMap = new Map();
 
/** 
 * 回调函数测试方法 
 *  
 * @param callback 
 *            被回调的方法 
 */  
function hycheck(callback,key){
	if(checkEvent(key)){
	    callback();
	}
}

function checkEvent(key){
	var e = eventMap.get(key);
	var u = userMap.get(key);
	var r = relationMap.get(key);
	var now = new Date().getTime();
	if(e.start > now){
		if(e.startnote !=""){
			hyalert(e.startnote,2000);
		}else{
			hyalert("此活动还未开始",2000);
		}
		return false;
	}
	if(e.end < now){
		if(e.endnote !=""){
			hyalert(e.endnote,2000);
		}else{
			hyalert("此活动已过期",2000);
		}
		return false;
	}
	if(e.logined == "Y" && $("#hy_cd")==1){//是否需要登录
		if($("#hy_visitUser").val() == ""){
			hyalert("请登录",2000);
			return false;
		}
	}
	if(r != null && r.length >0){//是否被关联
		var guo = false;
		for(var i= 0 ;i<r.length;i++){
			var gl =r[i].value;
			var ua = userMap.get(gl);
			if(ua != null && ua.joined=="Y"){
				guo = true;
			}
		}
		if(!guo){
			hyalert("请参看活动说明",2000);
			return false;
		}
	}
	if(e.sharetype=="B"){
		if($("#hy_cd").val()==1 ){
			if(u ==null || u == "" || u.beforeshare =="N"){
				return hyshare(key);
			}
		}
		if($("#hy_cd").val()==2){
			var ua = userMap.get("100-"+$("#hy_pageid").val())
			if(ua != null && ua.joined == 'N'){
				wxshare();
				return false;
			}
		}
	}
	return true;
}

function hyalert(str,time){
	easyDialog.open({
		  container : {
		    content : str
		  },
		  autoClose : time
		});
}

function hyshare(key){
	var e = eventMap.get(key);
	if(e.sharetype =='N'){
		return true;
	}
	var type='';
	if(e.sharetype =="A"){
		type='A';
	}else{
		type='B'
	}
	if($("#hy_cd").val()==2){
		var ua = userMap.get("100-"+$("#hy_pageid").val())
		if(ua != null && ua.joined == 'N'){
			wxshare();
			return false;
		}
	}
	var u = userMap.get(key);
	if(u==null) return false;
	u.joined='Y';
	var str ="<textarea id='hy_area' style='width:100%;height:80px;'>"+e.sharecontent+"</textarea><lable id='hy_msg'></lable><input id='hy_share_button' type='button' value='立刻分享'><input type='button' style='margin-left:10px;' onclick='$(\"#overlay, #easyDialogBox\").remove();' value='取消'>";
	easyDialog.open({
		  container : {
		    content : str
		  }
		});
	$("#hy_share_button").click(function(){
		$.post("/user/bashare.action",{"type":type,"key":key,"content":$("#hy_area").val(),"pic":e.shareimg,"pageid":$("#hy_pageid").val()},function(data){
			if(data=="Y"){
				if(type=="B"){
					userMap.get(key).beforeshare='Y';
				}
				if(type=="A"){
					userMap.get(key).aftershare='Y';
				}
				hyalert("分享成功!",2000);
				return true;
			}else{
				$("#hy_msg").text("分享失败，请刷新之后重试!");
				return false;
			}
		})
	});
}


var timerArr=new Array();
