/*
录音
https://github.com/xiangyuecn/Recorder
src: recorder-core.js,engine/wav.js
*/
!function (v) {
  "use strict";
  var p = function () {
  }, R = function (e) {
    return new t(e)
  };
  R.IsOpen = function () {
    var e = R.Stream;
    if (e) {
      var t = (e.getTracks && e.getTracks() || e.audioTracks || [])[0];
      if (t) {
        var a = t.readyState;
        return "live" == a || a == t.LIVE
      }
    }
    return !1
  }, R.BufferSize = 4096, R.Destroy = function () {
    for (var e in console.log("Recorder Destroy"), a) a[e]()
  };
  var a = {};
  R.BindDestroy = function (e, t) {
    a[e] = t
  }, R.Support = function () {
    var e = v.AudioContext;
    if (e || (e = v.webkitAudioContext), !e) return !1;
    var t = navigator.mediaDevices || {};
    return t.getUserMedia || (t = navigator).getUserMedia || (t.getUserMedia = t.webkitGetUserMedia || t.mozGetUserMedia || t.msGetUserMedia), !!t.getUserMedia && (R.Scope = t, R.Ctx && "closed" != R.Ctx.state || (R.Ctx = new e, R.BindDestroy("Ctx", function () {
      var e = R.Ctx;
      e && e.close && e.close()
    })), !0)
  };
  R.SampleData = function (e, t, a, n, r) {
    n || (n = {});
    var o = n.index || 0, s = n.offset || 0, i = n.frameNext || [];
    r || (r = {});
    var c = r.frameSize || 1;
    r.frameType && (c = "mp3" == r.frameType ? 1152 : 1);
    for (var f = 0, l = o; l < e.length; l++) f += e[l].length;
    f = Math.max(0, f - Math.floor(s));
    var u = t / a;
    1 < u ? f = Math.floor(f / u) : (u = 1, a = t), f += i.length;
    var v = new Int16Array(f), p = 0;
    for (l = 0; l < i.length; l++) v[p] = i[l], p++;
    for (var m = e.length; o < m; o++) {
      for (var h = e[o], d = (l = s, h.length); l < d;) {
        var g = Math.floor(l), S = Math.ceil(l), _ = l - g;
        v[p] = h[g] + (h[S] - h[g]) * _, p++, l += u
      }
      s = l - d
    }
    i = null;
    var I = v.length % c;
    if (0 < I) {
      var y = 2 * (v.length - I);
      i = new Int16Array(v.buffer.slice(y)), v = new Int16Array(v.buffer.slice(0, y))
    }
    return { index: o, offset: s, frameNext: i, sampleRate: a, data: v }
  };
  var n = 0;
  
  function t (e) {
    this.id = ++n, R.Traffic && R.Traffic();
    var t = { type: "mp3", bitRate: 16, sampleRate: 16e3, onProcess: p };
    for (var a in e) t[a] = e[a];
    this.set = t, this._S = 9
  }
  
  R.Sync = { O: 9, C: 9 }, R.prototype = t.prototype = {
    open: function (e, a) {
      var t = this;
      e = e || p, a = a || p;
      var n = function () {
        e(), t._SO = 0
      }, r = function (e, t) {
        /Permission|Allow/i.test(e) ? a("用户拒绝了录音权限", !0) : !1 === v.isSecureContext ? a("无权录音(需https)") : /Found/i.test(e) ? a(t + "，无可用麦克风") : a(t)
      }, o = R.Sync, s = ++o.O, i = o.C;
      t._O = t._O_ = s, t._SO = t._S;
      var c = function () {
        if (i != o.C || !t._O) {
          var e = "open被取消";
          return s == o.O ? t.close() : e = "open被中断", a(e), !0
        }
      };
      if (R.IsOpen()) n(); else if (R.Support()) {
        var f = function (e) {
          R.Stream = e, c() || setTimeout(function () {
            c() || (R.IsOpen() ? (!function () {
              var e = R.Ctx, t = R.Stream, a = t._m = e.createMediaStreamSource(t),
                n = t._p = (e.createScriptProcessor || e.createJavaScriptNode).call(e, R.BufferSize, 1, 1);
              a.connect(n), n.connect(e.destination);
              var f = t._call = {};
              n.onaudioprocess = function (e) {
                for (var t in f) {
                  for (var a = e.inputBuffer.getChannelData(0), n = a.length, r = new Int16Array(n), o = 0, s = 0; s < n; s++) {
                    var i = Math.max(-1, Math.min(1, a[s]));
                    i = i < 0 ? 32768 * i : 32767 * i, r[s] = i, o += Math.abs(i)
                  }
                  for (var c in f) f[c](r, o);
                  return
                }
              }
            }(), n()) : a("录音功能无效：无音频流"))
          }, 100)
        }, l = function (e) {
          var t = e.name || e.message || e.code + ":" + e;
          console.error(e), r(t, "无法录音：" + t)
        }, u = R.Scope.getUserMedia({ audio: !0 }, f, l);
        u && u.then && u.then(f)[e && "catch"](l)
      } else r("", "此浏览器不支持录音")
    }, close: function (e) {
      e = e || p;
      this._stop();
      var t = R.Sync;
      if (this._O = 0, this._O_ != t.O) return console.warn("close被忽略"), void e();
      t.C++;
      var a, n = R.Stream;
      if (n) {
        (a = R.Stream)._m && (a._m.disconnect(), a._p.disconnect(), a._p.onaudioprocess = a._p = a._m = null);
        for (var r = n.getTracks && n.getTracks() || n.audioTracks || [], o = 0; o < r.length; o++) {
          var s = r[o];
          s.stop && s.stop()
        }
        n.stop && n.stop()
      }
      R.Stream = 0, e()
    }, mock: function (e, t) {
      var a = this;
      return a._stop(), a.isMock = 1, a.buffers = [e], a.recSize = e.length, a.srcSampleRate = t, a
    }, envStart: function (e, t) {
      var a = this, n = a.set;
      if (a.isMock = e ? 1 : 0, a.buffers = [], a.recSize = 0, a.envInLast = 0, a.envInFirst = 0, a.envInFix = 0, a.envInFixTs = [], n.sampleRate = Math.min(t, n.sampleRate), a.srcSampleRate = t, a.engineCtx = 0, a[n.type + "_start"]) {
        var r = a.engineCtx = a[n.type + "_start"](n);
        r && (r.pcmDatas = [], r.pcmSize = 0)
      }
    }, envResume: function () {
      this.envInFixTs = []
    }, envIn: function (e, t) {
      var a = this, n = a.set, r = a.engineCtx, o = e.length;
      a.recSize += o;
      var s = a.buffers;
      s.push(e);
      var i, c = t / o;
      i = c < 1251 ? Math.round(c / 1250 * 10) : Math.round(Math.min(100, Math.max(0, 100 * (1 + Math.log(c / 1e4) / Math.log(10)))));
      var f = a.srcSampleRate, l = a.recSize, u = Date.now(), v = Math.round(o / f * 1e3);
      a.envInLast = u, 1 == a.buffers.length && (a.envInFirst = u - v);
      var p = a.envInFixTs;
      p.splice(0, 0, { t: u, d: v });
      for (var m = u, h = 0, d = 0; d < p.length; d++) {
        var g = p[d];
        if (3e3 < u - g.t) {
          p.length = d;
          break
        }
        m = g.t, h += g.d
      }
      var S = p[1], _ = u - m;
      if (_ / 3 < _ - h && (S && 1e3 < _ || 6 <= p.length)) {
        var I = u - S.t - v;
        if (v / 5 < I) {
          var y = !n.disableEnvInFix;
          if (console.warn("[" + u + "]" + (y ? "" : "未") + "补偿" + I + "ms"), a.envInFix += I, y) {
            var M = new Int16Array(I * f / 1e3);
            a.recSize += M.length, s.push(M)
          }
        }
      }
      if (r) {
        var w = R.SampleData(s, f, n.sampleRate, r.chunkInfo);
        r.chunkInfo = w, r.pcmSize += w.data.length, l = r.pcmSize, (s = r.pcmDatas).push(w.data), f = w.sampleRate, a[n.type + "_encode"](r, w.data)
      }
      var x = Math.round(l / f * 1e3);
      n.onProcess(s, i, x, f)
    }, start: function () {
      if (R.IsOpen()) {
        console.log("[" + Date.now() + "]Start");
        var e = this, t = (e.set, R.Ctx);
        if (e._stop(), e.state = 0, e.envStart(0, t.sampleRate), e._SO && e._SO + 1 != e._S) console.warn("start被中断"); else {
          e._SO = 0;
          var a = function () {
            e.state = 1, e.resume()
          };
          "suspended" == t.state ? t.resume().then(function () {
            console.log("ctx resume"), a()
          }) : a()
        }
      } else console.error("未open")
    }, pause: function () {
      this.state && (this.state = 2, delete R.Stream._call[this.id])
    }, resume: function () {
      var a = this;
      a.state && (a.state = 1, a.envResume(), R.Stream._call[a.id] = function (e, t) {
        1 == a.state && a.envIn(e, t)
      })
    }, _stop: function (e) {
      var t = this, a = t.set;
      t.isMock || t._S++, t.state && (t.pause(), t.state = 0), !e && t[a.type + "_stop"] && (t[a.type + "_stop"](t.engineCtx), t.engineCtx = 0)
    }, stop: function (a, t, e) {
      var n, r = this, o = r.set;
      console.log("[" + Date.now() + "]Stop " + (r.envInLast ? r.envInLast - r.envInFirst + "ms 补" + r.envInFix + "ms" : "-"));
      var s = function () {
        r._stop(), e && r.close()
      }, i = function (e) {
        t && t(e), s()
      }, c = function (e, t) {
        console.log("[" + Date.now() + "]结束 编码" + (Date.now() - n) + "ms 音频" + t + "ms/" + e.size + "b"), e.size < Math.max(100, t / 2) ? i("生成的" + o.type + "无效") : (a && a(e, t), s())
      };
      if (!r.isMock) {
        if (!r.state) return void i("未开始录音");
        r._stop(!0)
      }
      var f = r.recSize;
      if (f) if (r.buffers[0]) if (r[o.type]) {
        var l = r.engineCtx;
        if (r[o.type + "_complete"] && l) {
          l.pcmDatas;
          var u = Math.round(l.pcmSize / o.sampleRate * 1e3);
          return n = Date.now(), void r[o.type + "_complete"](l, function (e) {
            c(e, u)
          }, i)
        }
        n = Date.now();
        var v = R.SampleData(r.buffers, r.srcSampleRate, o.sampleRate);
        o.sampleRate = v.sampleRate;
        var p = v.data;
        u = Math.round(p.length / o.sampleRate * 1e3);
        console.log("采样" + f + "->" + p.length + " 花:" + (Date.now() - n) + "ms"), setTimeout(function () {
          n = Date.now(), r[o.type](p, function (e) {
            c(e, u)
          }, function (e) {
            i(e)
          })
        })
      } else i("未加载" + o.type + "编码器"); else i("音频被释放"); else i("未采集到录音")
    }
  }, v.Recorder && v.Recorder.Destroy(), (v.Recorder = R).LM = "2019-11-7 21:47:48", R.TrafficImgUrl = "//ia.51.la/go1?id=20469973&pvFlag=1", R.Traffic = function () {
    var e = R.TrafficImgUrl;
    if (e) {
      var t = R.Traffic, a = location.href.replace(/#.*/, "");
      if (!t[a]) t[a] = 1, (new Image).src = e, console.log("Traffic Analysis Image: Recorder.TrafficImgUrl=" + e)
    }
  }
}(window), function () {
  "use strict";
  Recorder.prototype.enc_wav = { stable: !0, testmsg: "比特率取值范围8位、16位" }, Recorder.prototype.wav = function (e, t, a) {
    var n = this.set, r = e.length, o = n.sampleRate, s = 8 == n.bitRate ? 8 : 16, i = r * (s / 8),
      c = new ArrayBuffer(44 + i), f = new DataView(c), l = 0, u = function (e) {
        for (var t = 0; t < e.length; t++, l++) f.setUint8(l, e.charCodeAt(t))
      }, v = function (e) {
        f.setUint16(l, e, !0), l += 2
      }, p = function (e) {
        f.setUint32(l, e, !0), l += 4
      };
    if (u("RIFF"), p(36 + i), u("WAVE"), u("fmt "), p(16), v(1), v(1), p(o), p(o * (s / 8)), v(s / 8), v(s), u("data"), p(i), 8 == s) for (var m = 0; m < r; m++, l++) {
      var h = 128 + (e[m] >> 8);
      f.setInt8(l, h, !0)
    } else for (m = 0; m < r; m++, l += 2) f.setInt16(l, e[m], !0);
    t(new Blob([f.buffer], { type: "audio/wav" }))
  }
}();
