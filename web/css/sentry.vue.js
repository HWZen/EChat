"function"!=typeof Object.assign&&Object.defineProperty(Object,"assign",{value:function(e,t){"use strict";if(null==e)throw new TypeError("Cannot convert undefined or null to object");for(var n=Object(e),r=1;r<arguments.length;r++){var o=arguments[r];if(null!=o)for(var c in o)Object.prototype.hasOwnProperty.call(o,c)&&(n[c]=o[c])}return n},writable:!0,configurable:!0});
!function(n){"use strict";var t={};Object.defineProperty(t,"t",{value:!0});var o=function(n,t){return(o=Object.setPrototypeOf||{__proto__:[]}instanceof Array&&function(n,t){n.__proto__=t}||function(n,t){for(var o in t)t.hasOwnProperty(o)&&(n[o]=t[o])})(n,t)};!function(n){function t(t){var o=this.constructor,i=n.call(this,t)||this;return i.message=t,i.name=o.prototype.constructor.name,Object.setPrototypeOf(i,o.prototype),i}(function(n,t){function i(){this.constructor=n}o(n,t),n.prototype=null===t?Object.create(t):(i.prototype=t.prototype,new i)})(t,n)}(Error);var i={};function r(){return"[object process]"===Object.prototype.toString.call("undefined"!=typeof process?process:0)?global:"undefined"!=typeof window?window:"undefined"!=typeof self?self:i}function e(n){var t=r();if(!("console"in t))return n();var o=t.console,i={};["debug","info","warn","error","log","assert"].forEach(function(n){n in t.console&&o[n].o&&(i[n]=o[n].i,o[n]=o[n].u)});var e=n();return Object.keys(i).forEach(function(n){o[n]=i[n]}),e}var c=r(),u="Sentry Logger ",f=function(){function n(){this.s=!1}return n.prototype.disable=function(){this.s=!1},n.prototype.enable=function(){this.s=!0},n.prototype.log=function(){for(var n=[],t=0;t<arguments.length;t++)n[t]=arguments[t];this.s&&e(function(){c.console.log(u+"[Log]: "+n.join(" "))})},n.prototype.warn=function(){for(var n=[],t=0;t<arguments.length;t++)n[t]=arguments[t];this.s&&e(function(){c.console.warn(u+"[Warn]: "+n.join(" "))})},n.prototype.error=function(){for(var n=[],t=0;t<arguments.length;t++)n[t]=arguments[t];this.s&&e(function(){c.console.error(u+"[Error]: "+n.join(" "))})},n}();c.v=c.v||{};var s;c.v.logger||(c.v.logger=new f);!function(n){n.PENDING="PENDING",n.RESOLVED="RESOLVED",n.REJECTED="REJECTED"}(s||(s={}));var a=function(){function n(t){void 0===t&&(t={}),this.name=n.id,this.h=!0,this.j=t.Vue||r().Vue,!1===t.attachProps&&(this.h=!1)}return n.prototype.l=function(n){if(n.$root===n)return"root instance";var t=n.O?n.$options.name||n.$options.p:n.name;return(t?"component <"+t+">":"anonymous component")+(n.O&&n.$options.g?" at "+n.$options.g:"")},n.prototype.setupOnce=function(t,o){var i=this;if(this.j&&this.j.config){var r=this.j.config.errorHandler;this.j.config.errorHandler=function(t,e,c){var u,f={};u=e,"[object Object]"===Object.prototype.toString.call(u)&&(f.componentName=i.l(e),i.h&&(f.propsData=e.$options.propsData)),void 0!==c&&(f.lifecycleHook=c),o().getIntegration(n)&&o().withScope(function(n){Object.keys(f).forEach(function(t){n.setExtra(t,f[t])}),o().captureException(t)}),"function"==typeof r&&r.call(i.j,t,e,c)}}else console.error("VueIntegration is missing a Vue instance")},n.id="Vue",n}();t.Vue=a,n.Sentry=n.Sentry||{},n.Sentry.Integrations=n.Sentry.Integrations||{},Object.assign(n.Sentry.Integrations,t)}(window);
//# sourceMappingURL=vue.min.js.map
