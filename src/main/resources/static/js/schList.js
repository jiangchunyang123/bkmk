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
            firstDeadLineMils: firstDeadLine.data("datetimepicker").getDate().Format("yyyyMMddHHmm"),
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
                } else {
                    alert("保存失败：", result.message);
                }
            });
    });
    $.get("/sch/rcd?user.uid=" + "jiangchunyang", function (re) {

        if (re && re.state == 1) {
            for (var index in re.data) {
                var detail = re.data[index];
                appendTimeLine(detail);
            }
            addClasses(re.data);
        }
    });

    function addClasses(data) {

        $('.timeline>li').each(function (index, e) {
            var $head = $(this).find(".hd");
            $(this).find(".buttons").data("detail", data[index]);
            $(this).find("button.check").on('click', function () {
                console.log("data:" + $(this).parent(".buttons").data("detail"));
                checkSchedule($(this).parent(".buttons").data("detail"));
            });
            $(this).find("button.giveup").on('click', function () {
                console.log("data:" + $(this).parent(".buttons").data("detail"));
                giveUpSchedule($(this).parent(".buttons").data("detail"));
            });
            if ($head.length >= 1) {
                var hid = $(this).find(".hidd");
                var badge = $(this).find(".timeline-badge");
                if (index % 2 == 1) {
                    $(this).addClass("timeline-inverted");
                    badge.before("<div class='badge-time'>2017-12-1 10:00:00</div>")
                } else {
                    badge.after("<div class='badge-time-invert'>2017-12-1 10:00:00</div>")
                }
                var schState = hid.text().trim();

                if (schState == '1') {
                    $head.addClass("success");
                } else if (schState == '-1') {
                    $head.addClass("danger");
                }
            }

        });
    }

    function checkSchedule(data) {
        updateSchRcd(data,1);
    }

    function giveUpSchedule(data) {
        updateSchRcd(data,-1);
    }

    function updateSchRcd(data,state) {
        data.state = state;
        $.ajax({
            url: "/sch/rcd",
            data: data,
            type:"PUT",
            success: function (re) {
                alert("success");
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

});