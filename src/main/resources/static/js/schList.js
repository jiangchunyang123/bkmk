$(document).ready(function () {
    var $newBtn = $('.newPlan');
    $("#firstDeadLine").datetimepicker({format: 'yyyy-mm-dd hh:ii',
        autoclose: true,
        todayBtn: true,
        keyboardNavigation:true});
    $newBtn.on('click', function () {
        console.log('new Btn clicked...');

        var $saveBtn = $('.save');
        var $form = $('.form-horizontal');
        $saveBtn.on('click', function () {
            var title = $form.find('#title');
            var firstDeadLine = $form.find('#firstDeadLine');
            var scheduleNum = $form.find('#scheduleNum');
            var scheduleType = $form.find('input[type="radio"]:checked');
            var remark = $form.find('#remark');
            console.log('dateL',firstDeadLine.data("datetimepicker").getDate());
            var data = {
                title: title.val(),
                firstDeadLine: firstDeadLine.val(),
                scheduleNum: scheduleNum.val(),
                scheduleType: scheduleType.val(),
                remark: remark.val(),
                testDate:firstDeadLine.data("datetimepicker").getDate()
            }
            console.log("data:", JSON.stringify(data));
            $.post('/sch',
                data
                , function (result) {
                    console.log('result:', result);
                });
        });
        $('#detail').modal({
            keyboard: true
        })
    });
    var badge = true;
    $.get("/sch/rcd?user.id=" + "193", function (re) {
        console.log(re);

        if(re&&re.state==1){
            for(var index in re.data){
                var detail = re.data[index];
                appendTimeLine(detail);
            }
            addClasses();
        }
    });

    function addClasses() {

        $('.timeline>li').each(function(index,e){
            var $head = $(this).find(".hd");
            if($head.length>=1){
                var $i = $head.find("i:eq(0)");

                var hid = $(this).find(".hidd");
                if(index%2==1){
                    $(this).addClass("timeline-inverted");
                }
                var schState =hid.text().trim();
                if(schState=='0'){
                    $i.addClass("glyphicon glyphicon-check");
                }else if(schState=='1'){
                    console.log('add..');
                    $i.addClass("glyphicon glyphicon-thumbs-up");
                    $head.addClass("success");
                }else if(schState=='-1'){
                    $i.addClass("glyphicon glyphicon-thumbs-down");
                    $head.addClass("danger");
                }
            }

        });
    }
    /**
     * timeline节点添加
     * @param detail
     */
    function appendTimeLine(detail){
        console.log("kkkkk:",detail.schedule)
        var script = template("tpl-timeLineNode", detail);
        $('.timeline').append(script);
    }

});