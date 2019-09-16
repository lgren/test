// 判断非空
function isEmpty(value) {
    return value === undefined || value == null || value === 'null' || value === 'undefined' || value.length === 0;
}

// 判断非空
function isNotEmpty(value) {
    return value !== undefined && value !== null && value !== 'null' && value !== 'undefined' && value.length !== 0;
}

// 先解除 在添加
function onOnly($node, types, selector, fn) {
    $node.off(types, selector).on(types, selector, fn);
}