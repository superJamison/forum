
$("#question").each(function(){                                                               //遍历file_name中的每个子元素
    let v = $(this).children('#description').text();
    if (v.length > 30)
    {
        let new_value = v.substring(0,10)+'......'+v.substring(v.length-5,v.length);
        $(this).children('#description').text(new_value); //设置新的text()
    }
});
