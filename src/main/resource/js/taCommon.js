/**
 * 批量处理
 * @param batchConfig.url url地址
 * @param batchConfig.submitData 提交到后台的data数据
 * @param batchConfig.dataGridId 优先判断data 当data为null则批量 读取此dataGridId的数据
 * @param batchConfig.data 1.当为单条时不为null 2.批量时此为null
 * @param batchConfig.idFieldName 作为唯一标识的字段名(一般为主键)
 * @param batchConfig.success 成功调用且结果success后的回调函数
 * @param batchConfig.error 失败调用且结果error后的回调函数
 * @param batchConfig.promptText 进行操作的时候弹窗 例如 promptText='删除' 则弹窗 '确认批量删除?'||'确认删除?'
 */
function batchBase(batchConfig) {
    batchConfig = batchConfig || {};
    batchConfig.submitData = batchConfig.submitData || {};
    if (batchConfig.data === null) {// 批量操作
        var dataArr = Base.getGridSelectedRows(batchConfig.dataGridId);
        if (dataArr.length === 0) {
            Base.alert("请至少选中一个!", "error");
            return;
        }
        Base.confirm('确认批量' + batchConfig.promptText + '?', function (yes) {
            if (yes) {
                batchConfig.submitData.ids = dataArr.map(function(o) {
                    return o[batchConfig.idFieldName];
                }).join();
                fnSubmit({url: batchConfig.url, data: batchConfig.submitData, success: batchConfig.success});
            }
        });
    } else {// 单条操作
        Base.confirm('确认' + batchConfig.promptText  + '?', function (yes) {
            if (yes) {
                batchConfig.submitData.ids = batchConfig.data[batchConfig.idFieldName];
                fnSubmit({url: batchConfig.url, data: batchConfig.submitData, success: batchConfig.success});
            }
        });
    }
}

/**
 * submit 简化版
 * @param submitConfig.url url地址
 * @param submitConfig.submitIds 需要传递到后台的对象id或容器id,多个id可以用","隔开
 * @param submitConfig.data 查询参数 会封装成dto["xxx"]
 * @param submitConfig.success 成功调用且结果success后的回调函数
 * @param submitConfig.error 失败调用且结果error后的回调函数
 */
function fnSubmit(submitConfig) {
    submitConfig = submitConfig || {};
    if (submitConfig.data) {
        var submitData = {};
        Object.getOwnPropertyNames(submitConfig.data).forEach(function (o) {
            submitData["dto['" + o + "']"] = submitConfig.data[o];
        });
    }
    Base.submit(submitConfig.submitIds || '', submitConfig.url, submitData, null, null, function(result) {
        submitConfig.success && submitConfig.success(result);
    },  function(result) {
        submitConfig.error && submitConfig.error(result);
    });
}