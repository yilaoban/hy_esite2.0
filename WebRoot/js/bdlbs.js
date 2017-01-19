function bdrun(){
	loadScript("http://api.map.baidu.com/getscript?v=2.0&ak=025j9m7SviBpzAzFtN4pczG0&services=&t=20150616161036",function(){
		var bdValue=$("#var_place").val();
		var map = new BMap.Map("bdmap_edit");
		map.centerAndZoom("北京",12);
		var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
				{"input" : "var_place"
				,"location" : map,"onSearchComplete":osc
				
		});
		ac.setInputValue(bdValue);
			ac.addEventListener("onhighlight", function(e) {  //鼠标放在下拉列表上的事件
				var str = "";
				var _value = e.fromitem.value;
				var value = "";
				if (e.fromitem.index > -1) {
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;
				
				value = "";
				if (e.toitem.index > -1) {
					_value = e.toitem.value;
					value = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				}    
				str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
				G("searchResultPanel").innerHTML = str;
			});
			var myValue;
			ac.addEventListener("onconfirm", function(e) {    //鼠标点击下拉列表后的事件
			var _value = e.item.value;
				myValue = _value.province +  _value.city +  _value.district +  _value.street +  _value.business;
				G("searchResultPanel").innerHTML ="onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;
				setPlace();
			});
			function setPlace(){
				map.clearOverlays();    //清除地图上所有覆盖物
				function myFun(){
					var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
					map.centerAndZoom(pp, 18);
					map.addOverlay(new BMap.Marker(pp));    //添加标注
					$("#var_position").val(pp.lng+","+pp.lat);
				}
				var local = new BMap.LocalSearch(map, { //智能搜索
				  onSearchComplete: myFun
				});
				local.search(myValue);
			}
			function G(id) {
				return document.getElementById(id);
			}
			function osc(){
				$(".tangram-suggestion").css("z-index",9999);
			}
	});
}

function loadScript(url, callback) {
    var script = document.createElement("script");
    script.type = "text/javascript";
    // IE
    if (script.readyState) {
        script.onreadystatechange = function () {
            if (script.readyState == "loaded" || script.readyState == "complete") {
                script.onreadystatechange = null;
                callback();
            }
        };
    } else { // others
        script.onload = function () {
            callback();
        };
    }
    script.src = url;
    document.head.appendChild(script);
}