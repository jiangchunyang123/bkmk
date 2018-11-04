// JavaScript Document
$(function () {
    var dataGrid = function (ele, opt) {
        this.defaults = {
            //id
            id: "",
            //请求url
            url: null,
            //表头格式
            columns: null,
            //是否分页
            pagination: false,
            //是否隔行变色
            isoddcolor: false,
            //是否搜索栏
            searchnation: false,
            //页显示
            pagesize: 20,
            //页索引
            pageindex: 1,
            //总页数
            totalpage: null,
            data: []
        }
        this.settings = $.extend({}, this.defaults, opt);
    }

    dataGrid.prototype = {
        _id: null,
        _op: null,
        init: function () {
            this._id = this.settings.id;
            _op = this;
            this.create();
            this.bindEvent();
        },
        create: function () {
            //初始化元素
            this.InitializeElement();
            //初始化表头
            this.createTableHead();
            //初始化动态行
            this.createTableBody(1);
            //初始化搜索框
            //if(this.settings.searchnation) this.createsearchbox();
            //选择是否分页
            if (this.settings.pagination) this.createTableFoot();
        },
        bindEvent: function () {
            //添加上一页事件
            this.registerUpPage();
            //添加下一页事件
            this.registerNextPage();
            //添加首页事件
            this.registerFirstPage();
            //添加最后一页事件
            this.registerlastPage();
            //添加跳转事件
            this.registerSkipPage();
            //添加鼠标悬浮事件
            this.registermousehover();
            //添加隔行变色
            this.registerchangebgcolor();
            //添加全选全不选事件
            this.registercheckall();
        },
        //初始化元素
        InitializeElement: function () {
            //var id = this.settings.id;
            $("#" + this._id).empty().append("<thead><tr></tr></thead><tbody></tbody>");
        },
        //循环添加表头
        createTableHead: function () {
            var headcols = this.settings.columns;
            for (var i = 0; i < headcols.length; i++) {
                var $tr = $("table[id='" + this._id + "'] thead tr");
                if (headcols[i].field === 'ck') {
                     $tr.append("<th width='50px' align='center'><input name='chkall' type='checkbox'></th>");
                } else {
                    $tr.append("<th width=" + headcols[i].width + " align=" + headcols[i].align + ">" + headcols[i].title + "</th>");
                }
            }
            $tr.append("<th width=60 align='center'>操作</th>");
        },
        getval: function (rowDetail, field) {
            var valArr = field.split("\.");
            var val = rowDetail;
            for(var index in valArr){
               val = val[valArr[index]];
            }
            return val;
        },
        //循环添加行
        createTableBody: function (pn) {
            var columns = _op.settings.columns;
            var json = this.getAjaxDate(_op.settings.url, null);
            //总页数=向上取整(总数/每页数)
            _op.settings.totalpage = Math.ceil((json.total) / _op.settings.pagesize);
            var row = "";
            for (var i = 0; i < _op.settings.data.length; i++) {
                if (i === _op.settings.data.length) break;
                var scheduleRecord= _op.settings.data[i];
                row += "<tr schId='"+scheduleRecord.id+"'>";
                for (var j = 0; j < columns.length; j++) {
                    if (columns[j].field === 'ck') {
                         row += '<td width="50px" align="center"><input name="chk"  type="checkbox"></td>';
                    }else {
                        var val = this.getval(scheduleRecord,columns[j].field);
                        var td = document.createElement("td");
                        td.setAttribute("width",columns[j].width);
                        td.setAttribute("align",columns[j].align);
                        td.innerText = val;
                        // rowsData+= td.scope;
                        row += '<td width=' + columns[j].width + ' align=' + columns[j].align + '>'
                            + val + '</td>';
                    }
                }
                row += "<td><a class=\"btn btn-primary btn-sm finish\">完成</a>" +
                    "<a class=\"btn btn-danger btn-sm giveup\">放弃</a></td></tr>";
            }

            $("table[id='" + this._id + "'] tbody").empty().append(row);
            $("#currentpageIndex").html(pn);
            this.registermousehover();
        },
        //初始化分页
        createTableFoot: function () {
            var footHtml = "<tr><td>";
            footHtml += "<span id='countPage'>第<font id='currentpageIndex'>1</font>/" + _op.settings.totalpage + "页</span>";
            footHtml += "<span id='firstPage'>首页</span>";
            footHtml += "<span id='UpPage'>上一页</span>";
            footHtml += "<span id='nextPage'>下一页</span>";
            footHtml += "<span id='lastPage'>末页</span>";
            footHtml += "<input type='text'/><span id='skippage'>跳转</span>";
            footHtml += "</td></tr>";
            $("table[id='" + this._id + "'] tfoot").append(footHtml);
            $("table[id='" + this._id + "'] tfoot tr td").attr("colspan", "5");
        },
        //添加鼠标悬浮事件
        registermousehover: function () {
            //添加鼠标悬浮事件
            $("table[id='" + this._id + "'] tbody tr").mouseover(function () {
                $(this).addClass("mouseover");
            }).mouseleave(function () {
                $(this).removeClass("mouseover");
            });
        },
        //添加隔行变色事件
        registerchangebgcolor: function () {
            //添加隔行变色
            if (this.settings.isoddcolor) {
                $("table[id='" + this._id + "'] tr:odd").css("background-color", "#A77C7B").css("color", "#fff");
            }
        },
        //添加全选全不选事件
        registercheckall: function () {
            //添加全选全不选事件
            $("input[name='chkall']").click(function () {
                if (this.checked) {
                    $("input[name='chk']").each(function () {
                        $(this).attr("checked", true);
                    });
                } else {
                    $("input[name='chk']").each(function () {
                        $(this).attr("checked", false);
                    });
                }
            });
        },
        //添加首页事件
        registerFirstPage: function () {
            $("#firstPage").click(function () {
                _op.settings.pageindex = 1;
                _op.createTableBody(_op.settings.pageindex);
            });
        },
        //添加上一页事件
        registerUpPage: function () {
            $("table").delegate("#UpPage", "click",
                function () {
                    if (_op.settings.pageindex == 1) {
                        alert("已经是第一页了");
                        return;
                    }
                    _op.settings.pageindex = _op.settings.pageindex - 1;
                    _op.createTableBody(_op.settings.pageindex);
                });
        },
        //添加下一页事件
        registerNextPage: function () {
            $("table").delegate("#nextPage", "click",
                function () {
                    if (_op.settings.pageindex == _op.settings.totalpage) {
                        alert("已经是最后一页了");
                        return;
                    }
                    _op.settings.pageindex = _op.settings.pageindex + 1;
                    _op.createTableBody(_op.settings.pageindex);
                });
        },
        //添加尾页事件
        registerlastPage: function () {
            $("table").delegate("#lastPage", "click",
                function () {
                    _op.settings.pageindex = _op.settings.totalpage;
                    _op.createTableBody(_op.settings.totalpage);
                });
        },
        //添加页数跳转事件
        registerSkipPage: function () {
            $("table").delegate("#skippage", "click",
                function () {
                    var value = $("table[id='" + this._id + "'] tfoot tr td input").val();
                    if (!isNaN(parseInt(value))) {
                        if (parseInt(value) <= _op.settings.totalpage) _op.createTableBody(parseInt(value));
                        else alert("超出页总数");
                    } else alert("请输入数字");
                });
        },
        //添加异步ajax事件
        getAjaxDate: function (url, parms) {
            //定义一个全局变量来接受$post的返回值
            var result;
            //用ajax的同步方式
            $.ajax({
                url: url,
                async: false,
                //改为同步方式
                data: parms,
                success: function (data) {
                    result = data;
                }
            });
            return result;
        }
    }

    $.fn.grid = function (options) {
        var grid = new dataGrid(this, options);
        return this.each(function () {
            grid.init();
        });
    }
})