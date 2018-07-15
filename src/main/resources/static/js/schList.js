$(document).ready(function () {
    var $newBtn = $('.newPlan');
    var $saveBtn = $('.save');
    var $form = $('.form-horizontal');

    $("#firstDeadLine").datetimepicker({
        format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        keyboardNavigation: true
    });
    $newBtn.on('click', function () {
        $('#detail').modal({
            keyboard: true
        });
    });

    $saveBtn.on('click', function () {
        var title = $form.find('#title');
        var firstDeadLine = $form.find('#firstDeadLine');
        var scheduleNum = $form.find('#scheduleNum');
        var scheduleType = $form.find('input[type="radio"]:checked');
        var remark = $form.find('#remark');
        var userId = $form.find("#userId");
        var data = {
            title: title.val(),
            firstDeadLine: firstDeadLine.data("datetimepicker").getDate().Format("yyyy-MM-dd HH:mm:ss"),
            scheduleNum: scheduleNum.val(),
            scheduleType: scheduleType.val(),
            remark: remark.val(),
            "user.id": userId.val()
        }
        $.post('/sch',
            data
            , function (result) {
                if (result.state == 1) {
                    alert("保存成功！");
                    $("#detail").modal('hide');
                    queryRecords();
                } else {
                    alert("保存失败：", result.message);
                }
            });
    });

    queryRecords();
    function queryRecords() {
        $('.timeline').empty();
        var params=[];
        params.push("pageSize=20");
        params.push("pageIndex=0");
        params.push("descName=deadLine");
        params.push("direction=Desc");
        $.get("/sch/rcd?user.uid=" + "jcy&"+params.join("&"), function (re) {

            if (re && re.state == 1) {
                for (var index in re.data) {
                    var detail = re.data[index];
                    appendTimeLine(detail);
                }
                addClasses(re.data);
            }
        });
    }

    function addClasses(data) {

        $('.timeline>li').each(function (index, e) {
            var indexData = data[index];
            var $head = $(this).find(".hd");
            $(this).find(".buttons").data("detail", data[index]);
            $(this).find("button.check").on('click', function () {
                var param = $(this).parent(".buttons").data("detail");
                var data={
                    id:param.id,
                    state:1
                }
                updateSchRcd(data);
            });
            $(this).find("button.giveup").on('click', function () {
                var param = $(this).parent(".buttons").data("detail");
                var data={
                    id:param.id,
                    state:-1
                }
                updateSchRcd(data);
            });
            if ($head.length >= 1) {
                var hid = $(this).find(".hidd");
                var badge = $(this).find(".timeline-badge");
                var date = new Date(indexData.deadLine);

                var formateDate = date.isToday()?date.Format("HH:mm"):date.Format("yyyy-MM-dd");

                // if (index % 2 == 1) {
                    $(this).addClass("timeline-inverted");
                    badge.before("<div class='badge-time' align='right'>" + formateDate + "</div>")
                // } else {
                //     badge.after("<div class='badge-time-invert'>" + formateDate + "</div>")
                // }
                var schState = hid.text().trim();

                if (schState == '1') {
                    $head.addClass("success");
                } else if (schState == '-1') {
                    $head.addClass("danger");
                }
            }

        });
    }

    function updateSchRcd(param) {
        $.ajax({
            url: "/sch/rcd",
            data: param,
            type: "PUT",
            success: function (re) {
                if(re.success){
                    alert("success");
                }
            }
        });
    }

    /**
     * timeline节点添加
     * @param detail
     */
    function appendTimeLine(detail) {
        var script = template("tpl-timeLineNode", detail);
        $('.timeline').append(script);
    }

    $(window).scroll(function(){
        var scrollTop = $(this).scrollTop();
        var scrollHeight = $(document).height();
        var windowHeight = $(this).height();
        if(scrollTop + windowHeight == scrollHeight){
            alert("已经到最底部了！");
        }
    });
});