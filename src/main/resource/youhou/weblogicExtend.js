// ==UserScript==
// @name         Weblogic拓展
// @namespace    http://tampermonkey.net/
// @version      0.1
// @description  try to take over the world!
// @author       You
// @include      http://*:8001/console/**
// @icon         data:image/gif;base64,R0lGODlhAQABAAAAACH5BAEKAAEALAAAAAABAAEAAAICTAEAOw==
// @require      https://code.jquery.com/jquery-3.4.1.min.js
// @require      file:///Users/lgren/Project/Java/0My/test/src/main/resource/youhou/weblogicExtend.js
// @grant        none
// ==/UserScript==

// 此文件不要动 因为是使用file://直接访问的本地文件
// 匹配 http://*:8001/console/**
// https://code.jquery.com/jquery-3.4.1.min.js
$(function () {
  const Base = {
    // 静态公共字段
    symbol: 'base',
    // 构造方法
    createNew() {
      const t = {}
      // 私有变量
      const _ = {}

      // 私有方法
      const _method = function () {

      };

      // 公有方法
      t.setCache = function (k, v = {}) {
        const cache = JSON.parse(localStorage.getItem('lc') || '{}');
        cache[k] = v;
        localStorage.setItem('lc', JSON.stringify(cache))
        return v
      }
      t.getCache = function (k, defaultV = undefined) {
        return (JSON.parse(localStorage.getItem('lc') || '{}'))[k] || defaultV
      }
      // 锁定并编辑
      t.lockAndEdit = function () {
        const _saveBtn = $('button[name=save]:not(:disabled)')
        if (_saveBtn[0]) {
          _saveBtn.click()
          return false
        }
        return true
      }
      // 释放配置
      t.freedConfig = function () {
        const _cancelBtn = $('button[name=cancel]:not(:disabled)')
        if (_cancelBtn[0]) {
          _cancelBtn.click()
          return false
        }
        return true
      }
      t.switchPortlet = function (s1, s2, s3) {
        disableButtons();
        switchPortlet(s1, s2, s3);
        return false
      }
      t.nextAction = function (s) {
        disableButtons();
        nextAction(s);
        return false
      }
      t.getObjProp = function (obj = {}, packageStr = '') {
        let nowObj = obj
        for (const p of packageStr.split('.')) {
          nowObj = nowObj[p]
        }
        return nowObj
      }
      t.removeNextMethod = function (method) {
        t.setCache('_nextMethod', '')
      }
      t.setNextMethod = function (method) {
        t.setCache('_nextMethod', method)
      }
      t.getNextMethod = function () {
        return t.getCache('_nextMethod', '')
      }
      t.recordStep = function (obj, symbol, beginMethodIndex, ...methods) {
        const result = {symbol, paramsCacheStr: `_${symbol}Params`, begin: null}
        let params = t.getCache(result.paramsCacheStr, {})
        for (let i = 0; i < methods.length; i++) {
          let method = methods[i]
          if (method && methods.length) {
            result[i] = (p = params) => {
              t.setCache(result.paramsCacheStr, p)
              if (i !== methods.length - 1) {
                t.setNextMethod(`${symbol}.${i + 1}`)
              }
              console.log(`${symbol}.${i}`)
              if (method(params) === true) {
                if (i !== methods.length - 1) {
                  let nextMethod = t.getObjProp(obj, t.getNextMethod());
                  t.removeNextMethod();
                  nextMethod()
                }
              }
            };
            if (beginMethodIndex === i) {
              result.begin = result[i]
            }
          }
        }
        obj[symbol] = result
        return result
      }
      t.setFormVal = function (formSelector, val = {}) {
        $(formSelector).find('input').each((i, o) => {
          let v = val[o.name];
          if (v) {
            if (o.type === 'checkbox') {
              if (v.split(',').indexOf(o.value) > -1) {
                o.checked = true
              }
            } else {
              o.value = v;
            }
          }
        })
      }
      t.setCheckbox = function (selector, val = '') {
        const vArr = val.split(',');
        $(selector).each((i, o) => {
          if (vArr.filter(s => o.value.indexOf(s) > -1).length > 0) {
            o.checked = true
          }
        });
      }
      t.getFormVal = function (formSelector) {
        return $(formSelector).serializeArray().reduce((r, o) => {
          r[o.name] = !r[o.name] ? o.value : (r[o.name] + ',' + o.value);
          return r
        }, ({_type: 'configDatasource', step: 0,}))

      }
      // 初始化
      return t
    }
  }

  const Main = {
    // 静态公共字段
    // 如: NAME: '你好呀',
    // 构造方法
    createNew() {
      const t = {}
      // 私有变量
      const {base} = _ = {
        _nextMethod: null,
        base: Base.createNew(),
      }

      // 私有方法
      const generateNodes = function () {
        const _div = $(`<div style=""></div>`)
        $(`body`).prepend(_div)
        _div.append(`<button class="logic">登陆</button>`)
        _div.append(`<button class="configDeploy">部署</button>`)
        _div.append(`<button class="configDatasource">配置数据源</button>`)
      }

      const initEvent = function () {
        const _html = $(`html`);
        _html
          // 登陆
          .on(`click`, `.logic`, e => {
            if ($('#j_username')[0]) {
              $('#j_username').val(`weblogic`)
              $('#j_password').val(`cdgllwzz#2403`)
              $('.formButton').click()
            } else {
              console.log(`已登录`)
            }
          })
          // 部署
          .on(`click`, `.configDeploy`, e => {
            t.configDeploy.begin()
          })
          // 配置数据源
          .on(`click`, `.configDatasource`, e => {
            const x = (e.clientX + 10) + 'px'
            const y = (e.clientY + 10) + 'px'
            let _div = $('.configDatasourceWin')
            // 如果不存在
            if (!_div[0]) {
              _div = $(`<form class="configDatasourceWin" style="position: fixed; left: ${x}; top: ${y}; width: 400px; background-color: #339cce;">
                <div style="display: flex;"><span style="width: 100px;">显示名称</span><input type="text" name="name" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">jndi名称</span><input type="text" name="jndiName" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">数据库名称</span><input type="text" name="dbName" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">ip</span><input type="text" name="dbIp" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">端口</span><input type="text" name="dbPort" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">用户名</span><input type="text" name="dbUser" style="flex: 1;"></div>
                <div style="display: flex;"><span style="width: 100px;">密码</span><input type="text" name="dbPass" style="flex: 1;"></div>
                <div style="display: flex;">
                  <span style="width: 100px;">发布端口</span>
                  <div>
                    <div><label>8003: <input type="checkbox" name="publishPort" value="8003"></label></div>
                    <div><label>8005: <input type="checkbox" name="publishPort" value="8005"></label></div>
                    <div><label>8007: <input type="checkbox" name="publishPort" value="8007"></label></div>
                    <div><label>8009: <input type="checkbox" name="publishPort" value="8009"></label></div>
                    <div><label>8011: <input type="checkbox" name="publishPort" value="8011"></label></div>
                    <div><label>8013: <input type="checkbox" name="publishPort" value="8013"></label></div>
                  </div>
                </div>
                <div>
                  <button class="okBtn">确定</button><button class="closeBtn">关闭</button>
                </div></form>`);
              _html.append(_div)
            }
            base.setFormVal('.configDatasourceWin', base.getCache(t.configDBSource.paramsCacheStr, {}))
          })
          .on(`click`, `.configDatasourceWin .okBtn`, e => {
            const val = base.getFormVal('.configDatasourceWin')
            t.configDBSource.begin(val)
          })
          .on(`click`, `.configDatasourceWin .closeBtn`, e => {
            $('.configDatasourceWin').remove()
          })
      }
      // 公有方法

      // 初始化
      //region 测试录制
      base.recordStep(t, 'testRecord', 0,
        base.lockAndEdit,
        p => console.log('test'),
        base.freedConfig,
      )
      //endregion

      //region 配置数据源
      base.recordStep(t, 'configDBSource', 0,
        base.lockAndEdit,
        p => window.open($('a#linkGlobalJDBCDataSourceTablePage').attr('href'), '_self'),
        p => base.switchPortlet("CreateGlobalJDBCDataSource", "CreateGlobalJDBCDataSourcePortlet", "genericTableForm"),
        p => {
          $('#CreateGlobalJDBCDataSourcePortletname').val(p.name)
          $('#CreateGlobalJDBCDataSourcePortletjndiName').val(p.jndiName)
          base.nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectDatabaseDriver");
        },
        p => base.nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectTransactionOptions"),
        p => base.nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/addConnectionProperties"),
        p => {
          $('#CreateGlobalJDBCDataSourcePortletdatabaseName').val(p.dbName)
          $('#CreateGlobalJDBCDataSourcePortlethostName').val(p.dbIp)
          $('#CreateGlobalJDBCDataSourcePortletport').val(p.dbPort)
          $('#CreateGlobalJDBCDataSourcePortletdatabaseUserName').val(p.dbUser)
          $('#CreateGlobalJDBCDataSourcePortletpassword').val(p.dbPass)
          $('#CreateGlobalJDBCDataSourcePortletconfirmPassword').val(p.dbPass)
          base.nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/testConnection");
        },
        p => base.nextAction("/com/bea/console/actions/jdbc/datasources/createjdbcdatasource/selectTargets"),
        p => {
          base.setCheckbox('[name="CreateGlobalJDBCDataSourcePortlettargetBean.chosenStandaloneServers"]', p.publishPort)
        }
      )
      //endregion

      //region 部署
      base.recordStep(t, 'configDeploy', 0,
        base.lockAndEdit,
        p => window.open($('a#linkAppDeploymentsControlPage').attr('href'), '_self'),
        // p => window.open($('a[title="控制- 选项卡"]').attr('href'), '_self'),
        p => {
          disableButtons();
          self.location.href = "/console/console.portal?_nfpb=true&_pageLabel=AppApplicationInstallPage";
        },
        p => nextAction("/com/bea/console/actions/app/install/selectUploadApp"),
        p => {
          console.log(1232132)
          setTimeout(() => {$('#AppApplicationInstallPortletuploadAppPath').click();}, 1000)
        },
      )
      //endregion

      // 获取由上一次传来的下一层数据
      if ((_._nextMethod = base.getObjProp(t, base.getNextMethod()))) {
        base.removeNextMethod();
        _._nextMethod();
      }

      generateNodes()
      initEvent()
      return t;
    }
  }

  window.main = Main.createNew();
})
