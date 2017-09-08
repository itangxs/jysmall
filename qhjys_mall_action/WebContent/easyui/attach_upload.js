/**
 * Ajax upload
 * Project page - http://valums.com/ajax-upload/
 * Copyright (c) 2008 Andris Valums, http://valums.com
 * Licensed under the MIT license (http://valums.com/mit-license/)
 * Version 2.3 (03.03.2009)
 */

(function () {

    var d = document, w = window;

    /**
     * Get element by id
     */
    function $(element) {
        if (typeof element == "string")
            element = d.getElementById(element);
        return element;
    }

    /**
     * Attaches event to a dom element
     */
    function addEvent(el, type, fn) {
        if (w.addEventListener) {
            el.addEventListener(type, fn, false);
        } else if (w.attachEvent) {
            var f = function () {
                fn.call(el, w.event);
            };
            el.attachEvent('on' + type, f)
        }
    }


    /**
     * Creates and returns element from html chunk
     */
    var toElement = function () {
        var div = d.createElement('div');
        return function (html) {
            div.innerHTML = html;
            var el = div.childNodes[0];
            div.removeChild(el);
            return el;
        }
    }();

    function hasClass(ele, cls) {
        return ele.className.match(new RegExp('(\\s|^)' + cls + '(\\s|$)'));
    }

    function addClass(ele, cls) {
        if (!hasClass(ele, cls)) ele.className += " " + cls;
    }

    function removeClass(ele, cls) {
        var reg = new RegExp('(\\s|^)' + cls + '(\\s|$)');
        ele.className = ele.className.replace(reg, ' ');
    }

    function getOffset(el) {
        if (w.jQuery) {
            return jQuery(el).offset();
        }

        var top = 0, left = 0;
        do {
            top += el.offsetTop || 0;
            left += el.offsetLeft || 0;
        } while (el = el.offsetParent);

        return {
            left: left,
            top: top
        };
    }

    function getBox(el) {
        var left, right, top, bottom;
        var offset = getOffset(el);
        left = offset.left;
        top = offset.top;
        right = left + el.offsetWidth;
        bottom = top + el.offsetHeight;

        return {
            left: left,
            right: right,
            top: top,
            bottom: bottom
        };
    }

    /**
     * Crossbrowser mouse coordinates
     */
    function getMouseCoords(e) {
        // pageX/Y is not supported in IE
        // http://www.quirksmode.org/dom/w3c_cssom.html
        if (!e.pageX && e.clientX) {
            return {
                x: e.clientX + d.body.scrollLeft + d.documentElement.scrollLeft,
                y: e.clientY + d.body.scrollTop + d.documentElement.scrollTop
            };
        }

        return {
            x: e.pageX,
            y: e.pageY
        };

    }

    /**
     * Function generates unique id
     */
    var getUID = function () {
        var id = 0;
        return function () {
            return 'ValumsAjaxUpload' + id++;
        }
    }();

    function fileFromPath(file) {
        return file.replace(/.*(\/|\\)/, "");
    }

    function getExt(file) {
        return (/[.]/.exec(file)) ? /[^.]+$/.exec(file.toLowerCase()) : '';
    }

    // Please use AjaxUpload , Ajax_upload will be removed in the next version
    Ajax_upload = AjaxUpload = function (button, options) {
        if (button.jquery) {
            // jquery object was passed
            button = button[0];
        } else if (typeof button == "string" && /^#.*/.test(button)) {
            button = button.slice(1);
        }
        button = $(button);

        this._input = null;
        this._button = button;
        this._disabled = false;
        this._submitting = false;

        this._settings = {
            // Location of the server-side upload script
            action: 'upload.php',
            // File upload name
            name: 'userfile',
            // Additional data to send
            data: {},
            // Submit file as soon as it's selected
            autoSubmit: true,
            // When user selects a file, useful with autoSubmit disabled
            onChange: function (file, extension) {
            },
            // Callback to fire before file is uploaded
            // You can return false to cancel upload
            onSubmit: function (file, extension) {
            },
            // Fired when file upload is completed
            onComplete: function (file, response) {
            }
        };

        // Merge the users options with our defaults
        for (var i in options) {
            this._settings[i] = options[i];
        }

        this._createInput();
        this._rerouteClicks();
    }

// assigning methods to our class
    AjaxUpload.prototype = {
        setData: function (data) {
            this._settings.data = data;
        },
        disable: function () {
            this._disabled = true;
        },
        enable: function () {
            this._disabled = false;
        },
        // use setData instead, set_data will be removed in the next version
        set_data: function (data) {
            this.setData(data);
        },
        // removes ajaxupload
        destroy: function () {
            if (this._input) {
                if (this._input.parentNode) {
                    this._input.parentNode.removeChild(this._input);
                }
                this._input = null;
            }
        },
        /**
         * Creates invisible file input above the button
         */
        _createInput: function () {
            var self = this;

            var input = d.createElement("input");
            input.setAttribute('type', 'file');
            input.setAttribute('name', this._settings.name);
            input.setAttribute('id', 'idFile');

            var styles = {
                'position': 'absolute', 'margin': '-5px 0 0 -175px', 'padding': 0, 'width': '220px', 'height': '10px', 'opacity': 0, 'cursor': 'pointer', 'display': 'none', 'zIndex': 2147483583 //Max zIndex supported by Opera 9.0-9.2x
                // Strange, I expected 2147483647
            };
            for (var i in styles) {
                input.style[i] = styles[i];
            }

            // Make sure that element opacity exists
            // (IE uses filter instead)
            if (!(input.style.opacity === "0")) {
                input.style.filter = "alpha(opacity=0)";
            }
            d.body.appendChild(input);

            addEvent(input, 'change', function () {
                // get filename from input
                var file = fileFromPath(this.value);
                if (self._settings.onChange.call(self, file, getExt(file)) == false) {
                    if (input) {
                        input.value = "";
//					alert(input.value);
                    }
                    return;
                }
                // Submit form when value is changed
                if (self._settings.autoSubmit) {
                    self.submit();
                }
            });

            this._input = input;
        },
        _rerouteClicks: function () {
            var self = this;

            // IE displays 'access denied' error when using this method
            // other browsers just ignore click()
            // addEvent(this._button, 'click', function(e){
            //   self._input.click();
            // });

            var box, over = false;
            addEvent(self._button, 'mouseover', function (e) {
                if (!self._input || over) return;
                over = true;
                box = getBox(self._button);
            });

            // we can't use mouseout on the button,
            // because invisible input is over it
            addEvent(document, 'mousemove', function (e) {
                var input = self._input;
                if (!input || !over) return;
                if (self._disabled) {
                    removeClass(self._button, 'hover');
                    input.style.display = 'none';
                    return;
                }

                var c = getMouseCoords(e);

                if ((c.x >= box.left) && (c.x <= box.right) &&
                    (c.y >= box.top) && (c.y <= box.bottom)) {

                    input.style.top = c.y + 'px';
                    input.style.left = c.x + 'px';
                    input.style.display = 'block';
                    addClass(self._button, 'hover');
                } else {
                    // mouse left the button
                    over = false;
                    input.style.display = 'none';
                    removeClass(self._button, 'hover');
                }
            });

        },
        /**
         * Creates iframe with unique name
         */
        _createIframe: function () {
            // unique name
            // We cannot use getTime, because it sometimes return
            // same value in safari :(
            var id = getUID();

            // Remove ie6 "This page contains both secure and nonsecure items" prompt
            // http://tinyurl.com/77w9wh
            var iframe = toElement('<iframe src="javascript:false;" name="' + id + '" />');
            iframe.id = id;
            iframe.style.display = 'none';
            d.body.appendChild(iframe);
            return iframe;
        },
        /**
         * Upload file without refreshing the page
         */
        submit: function () {
            var self = this, settings = this._settings;

            if (this._input.value === '') {
                // there is no file
                return;
            }

            // get filename from input
            var file = fileFromPath(this._input.value);

            // execute user event
            if (!(settings.onSubmit.call(this, file, getExt(file)) == false)) {
                // Create new iframe for this submission
                var iframe = this._createIframe();

                // Do not submit if user function returns false
                var form = this._createForm(iframe);
                form.appendChild(this._input);

                form.submit();

                d.body.removeChild(form);
                form = null;
                this._input = null;

                // create new input
                this._createInput();

                var toDeleteFlag = false;
                addEvent(iframe, 'load', function () {
                    if (iframe.src == "about:blank") {
                        // First time around, do not delete.
                        if (toDeleteFlag) {
                            // Fix busy state in FF3
                            setTimeout(function () {
                                d.body.removeChild(iframe);
                            }, 0);
                        }
                        return;
                    }

                    var doc = iframe.contentDocument ? iframe.contentDocument : frames[iframe.id].document;
                    var response = doc.body.innerHTML;

                    settings.onComplete.call(self, file, response);

                    // Reload blank page, so that reloading main page
                    // does not re-submit the post. Also, remember to
                    // delete the frame
                    toDeleteFlag = true;
                    iframe.src = "about:blank"; //load event fired
                });

            } else {
                // clear input to allow user to select same file
                this._input.value = '';
            }
        },
        /**
         * Creates form, that will be submitted to iframe
         */
        _createForm: function (iframe) {
            var settings = this._settings;

            // method, enctype must be specified here
            // because changing this attr on the fly is not allowed in IE 6/7
            var form = toElement('<form method="post" enctype="multipart/form-data"></form>');
            form.style.display = 'none';
            form.action = settings.action;
            form.target = iframe.name;
            d.body.appendChild(form);

            // Create hidden input element for each data key
            for (var prop in settings.data) {
                var el = d.createElement("input");
                el.type = 'hidden';
                el.name = prop;
                el.value = settings.data[prop];
                form.appendChild(el);
            }
            return form;
        }
    };


})();


(function ($) {
    $.extend($.fn, {
        attachupload: function (settings) {
            settings = $.extend({
                url: ctx + '/files/upload',
                attachTypeID: null,
                panelheight: 50,
                data: {},
                name: 'file',
                elementName: 'file',
                region: 'bottom',
                init: null,
                clear: false,
                callback: null
            }, settings);
            $(this).attr("readonly", "readonly").attr("disabled", "disabled");
            $(this).data("settings", settings);
            if (settings.attachTypeID == null) {
                throw new Error("missing attachTypeID config!");
            }
            //get attachType
            $.ajax({
                async: false,
                url: ctx + '/files/type',
                data: {'type': settings.attachTypeID},
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    if (result) {
                        settings.filter = result.suffix;
                        settings.max = result.maxNum;
                    }
                }
            });

            if (isNaN(settings.max)) {
                settings.max = -1;
            } else if (settings.max == 0) {
                settings.max = -1;
            }

            $(this).each(function () {
                var $this = $(this);
                if (!$this.hasClass("ajax-upload"))$this.addClass("ajax-upload");
                $this.initPanel(settings);
                if (settings.init) {
                    $this.appendFiles(settings.init, settings);
                }
                var uploadder =
                    new AjaxUpload($("#btSelect"), {
                        action: settings.url,
                        name: settings.name,
//					data: settings.data,
                        autoSubmit: false,
                        onChange: function (file, extension) {
                            if (settings.filter != null && settings.filter != "") {
                                var filter = settings.filter.split(";").join("|"),
                                    reg = new RegExp(filter);
                                if (reg.test(extension)) {
                                    $this.val(file);
                                    $("#btUpload").attr('class', "btn green");
                                    uploadder.setData($.extend(settings.data, {'type': settings.attachTypeID}));
                                    uploadder.setData($.extend(settings.data, {'fileName': file}));
                                    uploadder.setData($.extend(settings.data, {'extName': extension}));
                                } else {
                                    showCenterWarning("请选择格式为" + filter + "的文件");
                                    $("#btUpload").attr('class', "btn green disabled");
                                    return false;
                                }
                            } else {
                                $this.val(file);
                                $("#btUpload").attr('class', "btn green");
                                uploadder.setData($.extend(settings.data, {'type': settings.attachTypeID}));
                                uploadder.setData($.extend(settings.data, {'fileName': file}));
                                uploadder.setData($.extend(settings.data, {'extName': extension}));
                            }
                            return true;
                        },
                        onSubmit: function (file, extension) {
                            $("#btUpload").attr('class', "btn green disabled");
                        },
                        onComplete: function (file, response) {
                            $this.val("");
                            if (response.indexOf("success") != -1) {
                                //response= response.substring(response.indexOf('{"'));
                                //response = response.substring(0,response.indexOf("</pre"));

                                var result = $.evalJSON(response);
                                if (result.success) {
                                    $this.data("fileId", result.msg);
                                    $this.appendFiles([
                                        {fileID: result.msg, fileName: file}
                                    ], settings, uploadder);
                                    !!settings.callback ? settings.callback.call($this, response) : "";
                                } else {
                                    showCenterWarning("文件" + file + "上传失败,原因:" + result.msg);
                                }
                            } else {
                                showCenterWarning("文件" + file + "上传失败!");
                            }

                        }
                    });
                $this.data("uploadder", uploadder);
                $("#btUpload").click(function () {
                    uploadder.submit();
                });


                $("." + $this.data("element_id")).delegate('.remove', "click", function (e) {
                    $this.removeFiles($(this).closest(".fileItem"), settings, uploadder);
                });

                return $this;
            });
        },
        initPanel: function (settings) {
            var element_id = Math.floor(Math.random() * 10000), $this = $(this),
                panel = $("<div class='uploaded " + element_id + "' style='z-index:900'></div>").appendTo($this.parent());


            if (settings.region == "bottom") {
                panel.css({
                    'height': settings.panelheight,
                    'width': settings.panelwidth,
                    'overflow-x': 'hidden',
                    'overflow-y': 'auto', 'position': 'absolute',
//				'top':$this.position().top+$this.outerHeight(),
                    'left': $this.position().left
                });
            } else if (settings.region == "left") {
                panel.css({
                    'height': settings.panelheight,
                    'width': settings.panelwidth,
                    'overflow-x': 'hidden',
                    'overflow-y': 'auto',
                    'position': 'absolute',
                    //'top':$this.offset().top+$this.outerHeight(),
                    //'left':$this.offset().left
                    'top': $this.position().top,
                    'left': $this.position().left + $this.outerWidth() + 150
                });
            } else if (settings.region == "none") {
                panel.css('display', 'none');
            }

            $this.data("element_id", element_id).attr("ele_name",function () {
                return $(this).attr("name") || settings.elementName
            }).removeAttr("name");

            $("<input type='hidden' name='" + (settings.elementName == null ? settings.name : settings.elementName) + "' class='upload-element'/>")
                .insertBefore($this);
//		if(-1!=settings.max)
//			$("<span class='total'>还可以上传<span class='count'>"+settings.max+"</span>个文件</span>").appendTo(panel);
//		if(settings.clear)
//			$("<span class='clear' style='color:blue;cursor:pointer;float:right;'>clear</span>").appendTo(panel);
        },
        appendFiles: function (files, settings, uploadder) {
            var $this = $(this);
            if (settings.region != "none") {
                $.each(files, function (i, o) {
                    var name = "";
                    var fileName = "";
                    if (o.fileID != null) {
                        $.ajax({
                            url: ctx + '/files/getAttach',
                            type: 'post',
                            cache: false,
                            async: false,
                            data: {'id': o.fileID},//o.fileID
                            dataType: 'json',
                            success: function (result) {
                                if (result != null) {
                                    fileName = result.fileName;
                                    name = "<a href='##' onclick='downFilejj(" + o.fileID + ")'   title='" + fileName + "'>" + fileName + "</a>";
                                }
                            }
                        });
                    }

                    if (name != "") {
                        if ($.browser.msie) {
                            $("<div class='fileItem' fileId='" + o.fileID + "' style='width:100%;' title='" + fileName + "'>"
                                + "<span class='name' style='display:inline-block;overflow:hidden;white-space:nowrap;width:70%;'>" + name + "</span>"
                                + "<span class='remove' style='color:blue;cursor:pointer;'>&nbsp;&nbsp;&nbsp;&nbsp;删除</span></div>")
                                .prependTo("." + $this.data("element_id"));
                        } else {
                            $("<div class='fileItem' fileId='" + o.fileID + "' style='width:100%;' title='" + fileName + "'>"
                                + "<span class='name' style='display:inline-block;overflow:hidden;white-space:nowrap;width:70%;'>" + name + "</span>"
                                + "<span class='remove' style='color:blue;cursor:pointer;'>&nbsp;&nbsp;&nbsp;&nbsp;删除</span></div>")
                                .prependTo("." + $this.data("element_id"));
                        }

                        var temFileID = $(":hidden[name='" + settings.elementName + "']").val();
                        if (temFileID != '') {
                            var fileIDs = temFileID.split(';');
                            if (fileIDs[0] == '') {
                                fileIDs[0] = o.fileID;
                            } else {
                                if (fileIDs[0].indexOf(o.fileID) < 0) {
                                    fileIDs[0] = fileIDs[0] + "," + o.fileID;
                                }
                            }
                            $(":hidden[name='" + settings.elementName + "']").val(fileIDs.join(";"));
                        } else {
                            $(":hidden[name='" + settings.elementName + "']").val(o.fileID + ";");
                        }
                    }
                });
            } else {
                $this.val("上传成功:" + files[0].fileID);
            }
            $this.calCount(settings, uploadder);
        },
        removeFiles: function (files, settings, uploadder) {
            var $this = this;
            $.each(files, function (i, o) {
                var temFileID = $(":hidden[name='" + settings.elementName + "']").val();
                var fileIDs = temFileID.split(';');
                fileIDs[0] = fileIDs[0].replace($(o).attr("fileId"), "");

                if (fileIDs[1] == '') {
                    fileIDs[1] = $(o).attr("fileId");
                } else {
                    if (fileIDs[1].indexOf(o.fileID) < 0) {
                        fileIDs[1] = fileIDs[1] + "," + $(o).attr("fileId");
                    }
                }

                $(":hidden[name='" + settings.elementName + "']").val(fileIDs.join(";"));
                $(o).remove();
                $this.calCount(settings, uploadder);
                $this.removeData("fileId");
            });
        },
        calCount: function (settings, uploadder) {
            if (uploadder == null) {
                uploadder = $(this).data("uploadder");
            }
            $("." + $(this).data("element_id")).find(".count").text(settings.max - $("." + $(this).data("element_id")).find(".fileItem").length);

            var length1 = $("." + $(this).data("element_id")).find(".fileItem").length;
            if ($("." + $(this).data("element_id")).find(".fileItem").length == settings.max && -1 != settings.max) {
                $("#btSelect").attr('class', "btn green disabled");
                $("#btUpload").attr('class', "btn green disabled");
                uploadder.disable();
            } else {
                $("#btSelect").attr('class', "btn green");
                $("#btUpload").attr('class', "btn green disabled");
                uploadder.enable();
            }
        },
        destroy: function () {
            $(this).data("uploadder").destroy();
        },
        getFileId: function () {
            var settings = $(this).data("settings");
            return $(":hidden[name='" + settings.elementName + "']").val();
        },
        isEmpty: function () {
            var settings = $(this).data("settings");
            var temFileID = $(":hidden[name='" + settings.elementName + "']").val();
            if (temFileID != '') {
                var fileIDs = temFileID.split(';');
                if (fileIDs[0] == '') {
                    return true;
                } else {
                    return false;
                }
            } else {
                return true;
            }
        },
        initFiles: function (fileId) {
            $(this).reset();
            var fileids = fileId.split(",");
            for (var i = 0; i < fileids.length; i++) {
                if (fileids[i] != '') {
                    $(this).appendFiles([
                        {fileID: fileids[i]}
                    ], $(this).data("settings"));
                }
            }
        },
        reset: function () {
            if ($(this)) {
                var $this = $(this);
                var uploadder = $this.data('uploadder');
                var settings = $(this).data("settings");
                if (settings) {
                    var files = $("." + $this.data("element_id")).find(".remove").closest(".fileItem");

                    $(":hidden[name='" + settings.elementName + "']").val("");
                    $.each(files, function (i, o) {
                        $(o).remove();
                        $this.calCount(settings, uploadder);
                        $this.removeData("fileId");
                    });
                }

            }
        }
    });

    $(function () {
        $(".ajax-upload").each(function () {
            var name = $(this).attr("name");
            if (name) {
                $(this).removeAttr("name");
                $(this).attr("ele_name", name)
                $(this).attachupload({
                    name: $(this).attr("fileName"),
                    panelheight: $(this).attr("height"),
                    panelwidth: $(this).attr("width"),
                    elementName: name,
                    attachTypeID: $(this).attr("attachType"),
                    data: eval($(this).attr("data")),
                    region: $(this).attr("region")
                });
            }
        });
    });
})(jQuery);

function initFile(obj) {
    var name = $("#" + obj).attr("name");
    if (name) {
        $("#" + obj).removeAttr("name");
        $("#" + obj).attr("ele_name", name)
        $("#" + obj).attachupload({
            name: $("#" + obj).attr("fileName"),
            panelheight: $("#" + obj).attr("height"),
            panelwidth: $("#" + obj).attr("width"),
            elementName: name,
            attachTypeID: $("#" + obj).attr("attachType"),
            data: eval($("#" + obj).attr("data")),
            region: $("#" + obj).attr("region")
        });
    }
}

function downFilejj(fileId) {
    var url = ctx + '/files/down?fileId=' + fileId;
    $('#idown').attr("src", url);
    //CreateDownFrame(url);
}


function CreateDownFrame(url) {
    var oframe = $('<iframe id="downFrame">');
    //修改样式是css，修改属性是attr
    oframe.css("display", "none");
    oframe.attr("src", url);
    //在内部的前面加节点
    $('body').prepend(oframe);

    alert("4");
}


function initFileView(obj, fileID) {
    $("#" + obj).html("");
    if (fileID != '') {
        var fileids = fileID.split(",");
        for (var i = 0; i < fileids.length; i++) {
            if (fileids[i] != '') {
                $.ajax({
                    url: ctx + '/files/getAttach',
                    type: 'post',
                    cache: false,
                    async: false,
                    data: {'id': fileids[i]},//o.fileID
                    dataType: 'json',
                    success: function (result) {
                        if (result != null) {
                            $("#" + obj).append("<a href='##' onclick='downFilejj(" + fileids[i] + ")'  title='" + result.fileName + "'>" + result.fileName + "</a></BR>");
                        }
                    }
                });
            }
        }
    }

}

function initFilePdfView(obj, fileID) {
    if (fileID != '') {
        var height = $("#" + obj).attr("height");
        var width = $("#" + obj).attr("width");
        $.ajax({
            url: ctx + '/files/getAttach',
            type: 'post',
            cache: false,
            async: false,
            data: {'attach.id': o.fileID},//o.fileID
            dataType: 'json',
            success: function (result) {
                if (result != null) {
                    if (navigator.userAgent.indexOf("MSIE") > 0) {
                        height = Number(height) + Number(50);
                        width = Number(width) + Number(30);
                        var myPDF = new PDFObject({
                            url: result.fileUri,
                            width: width,
                            height: height
                        }).embed(obj);
                    } else {
                        var myPDF = new PDFObject({
                            url: result.fileUri,
                            width: width,
                            height: height
                        }).embed(obj);
                    }
                }
            }
        });
    }
}