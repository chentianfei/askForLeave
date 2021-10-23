
//遍历选中行数据数组对象，根据str的值返回对应参数
function queryAndBindInfo_array(data_array,str){
    var key,key_in;
    //循环data,发现匹配str的就返回对应值
    for(key in data_array){
        for(key_in in data_array[key]){
            if(key_in === str){
                return data_array[key][key_in];
            }
        }
    }

}

//遍历选中行数据对象，根据str的值返回对应参数
function queryAndBindInfo_obj(data_obj,str){
    var key;
    //循环data,发现匹配str的就返回对应值
    for(key in data_obj){
        if(key === str){
            return data_obj[key];
        }
    }

}