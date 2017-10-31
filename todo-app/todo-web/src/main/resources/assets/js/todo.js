$(document).ready(function(){
    $(".update-todo").click(function(evt){
        console.log("todo-id - "+$(this).closest('tr').find("#todo_id").text());
        $("#todo-id").val($(this).closest('tr').find("#todo_id").text());
        console.log("todo-title - "+$(this).closest('tr').find("#todo_title").text());
        $("#title").val($(this).closest('tr').find("#todo_title").text());
        console.log("todo-desc - "+$(this).closest('tr').find("#todo_desc").text());
        $("#description").val($(this).closest('tr').find("#todo_desc").text());
        $("#todo-submit").text("Update Todo");
        $("#todo-form").attr("action","/todo/update");
    });
});
