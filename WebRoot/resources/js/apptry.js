function loadServerTree() {
	$(".tree").dynatree({
		minExpandLevel: 2, // 1: root node is not collapsible
		initAjax: {
			url: "treeJson"
		},
		clickFolderMode: 1, // 1:activate, 2:expand, 3:activate and expand
		onClick: function(node, event) {
			switch(node.data.key) {
				case "server":
					current_server_id = node.data.id; break;
				case "emulator":
					current_emulator_id = node.data.id; break;
				default: break;
			}
			var url = node.data.key + "/" + node.data.id;
			loadDetail(url);
      	},
      	onPostInit: function(isReloading, isError) {
      	}
	});
}

function loadDetail(url) {
	$(".detail").load(url);
}

function initDynatree(os, dpi) {
	$("#device2sTree").dynatree({
		minExpandLevel : 2, // 1: root node is not collapsible
		initAjax: {
			url: "emulator/loadEmulatorsDynatree",
			data: {"os": os, "dpi": dpi}
		},
		selectMode : 3, // 1:single, 2:multi, 3:multi-hier 
		checkbox : true, // Show checkboxes.
		onSelect : function(flag, dtnode) {
			var selectedNodes = dtnode.tree.getSelectedNodes();
			var device2s_selected = 0
			$.map(selectedNodes, function(node) {
				if (!node.data.isFolder) {
					device2s_selected++;
				}
			});
			$(".device2s_selected").html(device2s_selected);
		}
	});
	$(".device2s_selected").html(0);
	if(os != "" || dpi != "") {
		$("#device2sTree").dynatree("getRoot").visit(function(dtnode) {
		    dtnode.select();
		});
	} else {
		var count = 0;
		$("#device2sTree").dynatree("getRoot").visit(function(dtnode) {
		    if(!dtnode.data.isFolder) count++;
		});
		$(".device2s_total").html(count);
	}
}

function upload() {
	var bar = $(".bar");
	if ($("#file").val()) {
		$("#uploadForm").attr("action", "application/upload").submit();
		window.setTimeout(updateProgressbar, 1600);
		bar.tooltip('show');
		window.setTimeout(function() {
			bar.tooltip('destroy');
		}, 1600);
	} else {
		$("#file").tooltip('show');
		window.setTimeout(function() {
			$("#file").tooltip('destroy');
		}, 1600);
	}
}
var attempts = 0;
var updateProgressbarFrequency = 200; // 进度条的更新频率，单位ms
function updateProgressbar() {
	var bar = $(".bar");
	$.ajax( {
		url : "application/percentDone",
		async : false,
		dataType : "json",
		success : function(data) {
			if (data == null || data == "" || data == -3) {
				if (attempts++ < 3) {
					window.setTimeout(updateProgressbar, 1600);
				} else {
					alert("网络不稳定，请稍后重试.");
					window.location.reload();
				}
			} else {
				bar.attr("style", "width:" + data + "%;").html(data + "%");
				if (data < 99) {
					window.setTimeout(updateProgressbar, updateProgressbarFrequency);
				} else {
					bar.html(" <i class='icon-ok icon-white'></i> 上传成功. ");
					
					window.setTimeout(function() {
						$("#cloudtestWizard").wizard('next');
					}, 1600);
				}
			}
		}
	});
}
function loadAllOs() {
	$("#osTable").load("emulator/loadAllOs");
}
function loadAllDpi() {
	$("#dpiTable").load("emulator/loadAllDpi");
}

function selectAll(type) {
	$("input:checkbox[name='" + type + "']").attr("checked", "true");
	changeAll();
}

function checkAll(obj, itemName) {
	for ( var i = 0; i < document.getElementsByName(itemName).length; i++) {
		if (obj.checked == true) {
			document.getElementsByName(itemName)[i].checked = true;
		} else {
			document.getElementsByName(itemName)[i].checked = false;
		}
	}
	changeAll();
}

function deselectAll(type) {
	$("input:checkbox[name='" + type + "']").attr("checked", "false");
	changeAll();
}

function cancelCheckAll(checkAllId, itemName) {
	changeAll();
	if (!itemName) {
		document.getElementById(checkAllId).checked = false;
		return;
	}
	for ( var i = 0; i < document.getElementsByName(itemName).length; i++) {
		if (document.getElementsByName(itemName)[i].checked == false) {
			document.getElementById(checkAllId).checked = false;
			return;
		}
	}
	if (i >= document.getElementsByName(itemName).length) {
		document.getElementById(checkAllId).checked = true;
	}
}

function changeAll() {
	var os = "", dpi = "";
	$.each($("input:checked[name='os']"), function(i, n) {
		os += (n.value + ",");
	});
	$.each($("input:checked[name='dpi']"), function(i, n) {
		dpi += (n.value + ",");
	});
	initDynatree(os, dpi);
}

function install() {
	var selectedNodes = $("#device2sTree").dynatree("getSelectedNodes");
	if (selectedNodes.length > 0) {
		var device2s = "";
//		var manufacturer = "";
		$.map(selectedNodes, function(node) {
			if (node.data.isFolder == true) {
//				manufacturer = node.data.title;
			} else {
				device2s += ("device2s=" + node.data.key + "&");
			}
		});
		// submit selected device2s to '../dispatcher/submit'
		$.ajax({
			url : "deployment/install",
			data : device2s,
			dataType : "json",
			type : "POST",
			success : function(data) {
				$("#cloudtestWizard").wizard('next');
			}
		});
	} else {
		alert(" 请选择安装终端！ ");
	}
}

function Map() {
	this.elements = new Array();

	//获取MAP元素个数
	this.size = function() {
		return this.elements.length;
	}

	//判断MAP是否为空
	this.isEmpty = function() {
		return (this.elements.length < 1);
	}

	//删除MAP所有元素
	this.clear = function() {
		this.elements = new Array();
	}

	//向MAP中增加元素（key, value) 
	this.put = function(_key, _value) {
		this.elements.push( {
			key : _key,
			value : _value
		});
	}

	//删除指定KEY的元素，成功返回True，失败返回False
	this.remove = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					this.elements.splice(i, 1);
					return true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//获取指定KEY的元素值VALUE，失败返回NULL
	this.get = function(_key) {
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					return this.elements[i].value;
				}
			}
		} catch (e) {
			return null;
		}
	}

	//获取指定索引的元素（使用element.key，element.value获取KEY和VALUE），失败返回NULL
	this.element = function(_index) {
		if (_index < 0 || _index >= this.elements.length) {
			return null;
		}
		return this.elements[_index];
	}

	//判断MAP中是否含有指定KEY的元素
	this.containsKey = function(_key) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].key == _key) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//判断MAP中是否含有指定VALUE的元素
	this.containsValue = function(_value) {
		var bln = false;
		try {
			for (i = 0; i < this.elements.length; i++) {
				if (this.elements[i].value == _value) {
					bln = true;
				}
			}
		} catch (e) {
			bln = false;
		}
		return bln;
	}

	//获取MAP中所有VALUE的数组（ARRAY）
	this.values = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].value);
		}
		return arr;
	}

	//获取MAP中所有KEY的数组（ARRAY）
	this.keys = function() {
		var arr = new Array();
		for (i = 0; i < this.elements.length; i++) {
			arr.push(this.elements[i].key);
		}
		return arr;
	}
}