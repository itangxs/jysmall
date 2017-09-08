window.SOP = {
    config: {
        hostname: 'partners.surveyon.com'
    }
    ,setProperties: function (props) {
        this.props = props;
    }
    ,runJSONPCallback: function (args) {
        this.appendJSONPCallback(args);
    }
    ,runHTMLWidget: function (args) {
        var q = {};
        for (var key in args) {
            q['sop_'+key] = args[key];
        }
        this.appendHTMLWidget(q);
    }
    ,appendJSONPCallback: function (args) {
        this.props.el_id = args.el_id;
        var el = document.createElement('script');
        el.setAttribute('type', 'text/javascript');
        el.setAttribute('src', this.getApiUrl('//'+this.config.hostname+'/api/v1_1/surveys/js'));
        window.document.body.appendChild(el);
    }
    ,appendHTMLWidget: function (args) {
        if (!args['sop_width']) { args['sop_width'] = '200'; }

        var el = document.createElement('iframe');
        el.setAttribute('src', this.getApiUrl('//'+this.config.hostname+'/api/v1_1/surveys/html', args));
        el.setAttribute('border', 0);
        el.setAttribute('hspace', 0);
        el.setAttribute('vspace', 0);
        el.setAttribute('marginheight', 0);
        el.setAttribute('marginwidth', 0);
        el.setAttribute('framespacing', 0);
        el.setAttribute('frameborder', 0);
        el.setAttribute('scrolling', 'no');
        el.setAttribute('width', args['sop_width']);
        el.setAttribute('height', 0);

        var originRegex = /(?:surveyon\.com|researchpanelasia\.com)$/;
        var adjustElHeight = function (el, height) {
            if (height <= 0) { return; }
            el.setAttribute('height', height + 2);
        };
        var onMessageCB = function (e) {
            if (!e.origin.match(originRegex)) { return; }
            adjustElHeight(el, parseInt(e.data));
        };

        if (window.addEventListener) {
            window.addEventListener('message', onMessageCB, false);
            window.addEventListener('unload', function (e) {
                window.removeEventListener('message', onMessageCB, false);
                window.removeEventListener('unload', arguments.callee, false);
            }, false);
        }
        else if (window.attachEvent) {
            window.attachEvent('onmessage', onMessageCB);
            window.attachEvent('onunload', function (e) {
                window.detachEvent('onmessage', onMessageCB);
                window.detachEvent('onunload', arguments.callee);
            });
        }

        var target = document.getElementById(args.sop_el_id);
        if (target) { target.appendChild(el); }
    }
    ,getApiUrl: function (baseUrl, params) {
        if (!params) { params = { }; }
        var url = baseUrl
             + '?app_id=' + this.props['app_id']
             + '&app_mid=' + this.props['app_mid']
             + '&time=' + this.props['time']
             + '&sig=' + this.props['sig'];
        for (var k in params) {
            url += '&'+k+'='+encodeURIComponent(params[k]);
        }
        return url;
    }
};