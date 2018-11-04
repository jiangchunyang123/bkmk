$(document).read(function(){
   console.log(" hello list");
   var querySchUrl = "/sch";
   function loadSchList(){
        $.get(querySchUrl,function(r){
           console.log(r);
        });
   }

   loadSchList();
});