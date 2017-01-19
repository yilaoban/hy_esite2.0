<%@ page contentType="text/html;charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="/js/jquery-1.8.2.min.js"></script>
<script src="/js/sigma/sigma.core.js"></script>
<script src="/js/sigma/conrad.js"></script>
<script src="/js/sigma/utils/sigma.utils.js"></script>
<script src="/js/sigma/utils/sigma.polyfills.js"></script>
<script src="/js/sigma/sigma.settings.js"></script>
<script src="/js/sigma/classes/sigma.classes.dispatcher.js"></script>
<script src="/js/sigma/classes/sigma.classes.configurable.js"></script>
<script src="/js/sigma/classes/sigma.classes.graph.js"></script>
<script src="/js/sigma/classes/sigma.classes.camera.js"></script>
<script src="/js/sigma/classes/sigma.classes.quad.js"></script>
<script src="/js/sigma/captors/sigma.captors.mouse.js"></script>
<script src="/js/sigma/captors/sigma.captors.touch.js"></script>
<script src="/js/sigma/renderers/sigma.renderers.canvas.js"></script>
<script src="/js/sigma/renderers/sigma.renderers.webgl.js"></script>
<script src="/js/sigma/renderers/sigma.renderers.def.js"></script>
<script src="/js/sigma/renderers/webgl/sigma.webgl.nodes.def.js"></script>
<script src="/js/sigma/renderers/webgl/sigma.webgl.nodes.fast.js"></script>
<script src="/js/sigma/renderers/webgl/sigma.webgl.edges.def.js"></script>
<script src="/js/sigma/renderers/webgl/sigma.webgl.edges.fast.js"></script>
<script src="/js/sigma/renderers/webgl/sigma.webgl.edges.arrow.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.labels.def.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.hovers.def.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.nodes.def.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.edges.def.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.edges.curve.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.edges.arrow.js"></script>
<script src="/js/sigma/renderers/canvas/sigma.canvas.edges.curvedArrow.js"></script>
<script src="/js/sigma/middlewares/sigma.middlewares.rescale.js"></script>
<script src="/js/sigma/middlewares/sigma.middlewares.copy.js"></script>
<script src="/js/sigma/misc/sigma.misc.animation.js"></script>
<script src="/js/sigma/misc/sigma.misc.bindEvents.js"></script>
<script src="/js/sigma/misc/sigma.misc.drawHovers.js"></script>
<!-- END SIGMA IMPORTS -->
<script src="/js/sigma/sigma.parsers.json.js"></script>
<script src="/js/sigma/sigma.layout.forceAtlas2.js"></script>
<div id="content" class="normal_content">
  <div class="toolbar pb10">
	  <a href="javascript:window.history.back()" class="return" title="返回"></a>
	</div>
<div id="container" class="mt20">
  <style>
    #graph-container {
      width:100%;
      height:500px;
      background:#E4E4E4;
    }
  </style>
  <div id="graph-container"></div>
</div>

</div>
<script>
$.post('/page/page_process.action',{"pageid":${pageid}},function(data){
	var s=new sigma({graph:data, 
	  container: 'graph-container',
	  settings: {
	    drawEdges: true
	    }
	  });
	s.startForceAtlas2();
 });

</script>
