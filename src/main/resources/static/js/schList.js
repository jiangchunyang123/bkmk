$(document).ready(function () {
    var $newBtn = $('.newPlan');
    $newBtn.on('click',function(){
        console.log('new Btn clicked...');
        $('#detail').modal({
            keyboard:true
        })
    });
    console.log("document ready");
    $.get("/sch?uid="+"193",function(re){
        console.log(re);
    });
});