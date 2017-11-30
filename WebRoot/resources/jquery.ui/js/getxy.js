function getXY(e) {
	e = window.event || e;
	var x = (e.offsetX === undefined) ? getOffset(e).offsetX : e.offsetX;
	var y = (e.offsetY === undefined) ? getOffset(e).offsetY : e.offsetY;
	var pos = {"x":x, "y":y};
	return pos;
	//alert('offsetX:'+posX);
	//alert('offsetY:'+posY);
	//$id("ct").innerHTML = "offsetX:" + posX + "<br/>clientX:" + e.clientX + "<br/>offsetY:" + posY + "<br/>clientY:" + e.clientY;
}
//var $id = function (id) {
//	return document.getElementById(id) || id;
//};
function getOffset(e) {
	var target = e.target;
	if (target.offsetLeft === undefined) {
		target = target.parentNode;
	}
	var pageCoord = getPageCoord(target);
	var eventCoord = {x:window.pageXOffset + e.clientX, y:window.pageYOffset + e.clientY};
	var offset = {offsetX:eventCoord.x - pageCoord.x, offsetY:eventCoord.y - pageCoord.y};
	return offset;
}
function getPageCoord(element) {
	var coord = {x:0, y:0};
	while (element) {
		coord.x += element.offsetLeft;
		coord.y += element.offsetTop;
		element = element.offsetParent;
	}
	return coord;
}

