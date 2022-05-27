<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="entity.Msg"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <script type="text/javascript" src="script/jquery-1.7.2.min.js"></script>
    <meta http-equiv="Content-Type" con tent="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit">
    <meta name="description" content="bilibili聊天框测试">
    <meta name="format-detection" content="telephone=no">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="theme-color" content="#4393E2">
    <title>Echat</title>
    <!-- 浏览器兼容测试 -->
    <script>
        if (!Object.defineProperty || !Element.prototype.addEventListener) alert('少年哟～你的浏览器太老了辣～赶快去换个chrome吧～')
        try {
            if (typeof window !== 'undefined' &&
                window.location.hash.indexOf('#/autoreply') === 0 &&
                (/BiliApp/i).test(window.navigator.userAgent)
            ) {
                window.location.replace('https://message.bilibili.com/h5/app/auto-reply#/')
            }
        } catch (e) {
            //
        }
    </script>
    <script>
        function topicSwitch(s) {
            window.location="message.jhtml?topicName="+s+"&topicName=";
        }
    </script>
    <link href="./css/index.9e59bdf8.css" rel="stylesheet">
</head>
<body>
<div data-v-6969394c class="container">
    <div data-v-1c9150a9 data-v-6969394c id="link-message-container" class>
        <div data-v-1c9150a9 class="container">
            <div data-v-1c9150a9 class="space-right">
                <div data-v-1c9150a9 data-no-clip="no-clip" class="space-right-bottom ps">
                    <div data-v-1c9150a9="" class="router-view">
                        <div data-v-1abd495e="" data-v-1c9150a9="" class="d">
                            <div data-v-9beceada="" data-v-1abd495e="" class="bili-im">
                                <!---->
                                <!---->
                                <div data-v-9beceada class="left">
                                    <div data-v-9beceada="" title="如果您的消息展示异常，请按住 alt 按键，然后单击这里进行修复" class="title">
                                        <span data-v-9beceada="">近期消息</span>
                                    </div>
                                    <%int id = -1;%>
                                    <div data-v-9beceada="" class="list-container ps ps--active-y">
                                        <div data-v-9beceada="" class="list">
                                            <c:forEach items="${TopicList}" var="topic">
                                                <c:if test="${topic.getSessionName() == topicName}">
                                                    <div data-v-42a3b689="" data-v-9beceada="" class="list-item active" id="${topic.getSessionName()}" onclick="topicSwitch(this.id)">
                                                        <div data-v-42a3b689="" class="name-box">
                                                            <div data-v-42a3b689="" class="name" title="topic-name">${topic.getSessionName()}</div>
                                                            <div data-v-42a3b689="" title="" class="last-word"></div>
                                                        </div>
                                                        <div data-v-42a3b689="" class="close">
                                                            <svg data-v-42a3b689="" viewBox="0 0 40 40" class="css-1dtzbno">
                                                                <path d="M22.83,20,38.42,4.41a2,2,0,1,0-2.83-2.83h0L20,17.17,4.41,1.58A2,2,0,0,0,1.58,4.41L17.17,20,1.58,35.59a2,2,0,0,0,2.83,2.83L20,22.83,35.59,38.42a2,2,0,1,0,2.83-2.83Z"></path>
                                                            </svg>
                                                        </div>
                                                        <!---->
                                                    </div>
                                                </c:if>
                                                <c:if test="${topic.getSessionName() != topicName}">
                                                    <div data-v-42a3b689="" data-v-9beceada="" class="list-item" id="${topic.getSessionName()}" onclick="topicSwitch(this.id)">
                                                        <div data-v-42a3b689="" class="name-box">
                                                            <div data-v-42a3b689="" class="name" title="topic-name">${topic.getSessionName()}</div>
                                                            <div data-v-42a3b689="" title="" class="last-word"></div>
                                                        </div>
                                                        <div data-v-42a3b689="" class="close">
                                                            <svg data-v-42a3b689="" viewBox="0 0 40 40" class="css-1dtzbno">
                                                                <path d="M22.83,20,38.42,4.41a2,2,0,1,0-2.83-2.83h0L20,17.17,4.41,1.58A2,2,0,0,0,1.58,4.41L17.17,20,1.58,35.59a2,2,0,0,0,2.83,2.83L20,22.83,35.59,38.42a2,2,0,1,0,2.83-2.83Z"></path>
                                                            </svg>
                                                        </div>
                                                        <!---->
                                                    </div>
                                                </c:if>
                                            </c:forEach>
                                        </div>
                                    </div>
                                    <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
                                        <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
                                    </div>
                                    <div class="ps__rail-y" style="top: 0px; right: 0px; height: 813px;">
                                        <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 85px;"></div>
                                    </div>
                                </div>
                                <!---->
                                <!---->
                                <div data-v-9beceada="" class="right">
                                    <!---->
                                    <div data-v-10e42db0="" data-v-9beceada="" class="dialog" style="">
                                        <div data-v-b2e907f6="" data-v-10e42db0="" class="message-list">
                                            <div data-v-b2e907f6="" class="message-list-content">
                                                <div data-v-0f4fd40a="" data-v-b2e907f6="" class="msg-more"><span data-v-0f4fd40a="" class="load-more"
                                                                                                                  style="display: none;"><span data-v-0f4fd40a="" class="icon"></span>查看和他的历史私信消息</span><span
                                                        data-v-0f4fd40a="" class="no-more" style="">没有更多消息了～</span><span data-v-0f4fd40a=""
                                                                                                                         class="loading" style="display: none;">
                                                            <div data-v-0f4fd40a="" class="lds-spinner">
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                                <div></div>
                                                            </div>
                                                        </span><span data-v-0f4fd40a="" class="error" style="display: none;">消息加载失败，<span data-v-0f4fd40a=""
                                                                                                                                          class="btn">点击重新加载</span></span>
                                                </div>
                                                <c:forEach items = "${messages}" var="msg">
                                                    <div data-v-82bd82b8="" data-v-b2e907f6="" class="msg-time"><span data-v-318cad34="" data-v-82bd82b8=""
                                                                                                                      class="time">${msg.sendTime}</span></div>
                                                    <c:if test="${msg.getFromUserId() != msg.getToSessionId()}">
                                                        <div data-v-d5403732="" data-v-b2e907f6="" class="msg-item not-me"><a data-v-d5403732=""
                                                                                                                              href="" title="${msg.getFromUserId()}" target="_blank" class="avatar"
                                                                                                                              style="background-image: url(&quot;https://i0.hdslb.com/bfs/face/45e709f0b719faf7c2eefeb4068700c18951e194.jpg@30w_30h_1c.webp&quot;);"></a>
                                                            <div data-v-d5403732="" class="message">
                                                                <div data-v-d5403732="" data-key="6674637179562596354" class="message-content not-img">
                                                                        ${msg.content}
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                    <c:if test="${msg.getFromUserId() == msg.getToSessionId()}">
                                                        <div data-v-d5403732="" data-v-b2e907f6="" class="msg-item is-me">
                                                            <a data-v-d5403732="" href="//space.bilibili.com/277370943" title="${msg.getFromUserId()}" target="_blank" class="avatar" style="background-image: url(&quot;https://i0.hdslb.com/bfs/face/32950f1fd55ccf45fbd29813ddbb9fc56f7301c4.jpg@30w_30h_1c.webp&quot;);"></a>
                                                            <div data-v-d5403732="" class="message">
                                                                <div data-v-d5403732="" data-key="7083505303328669265" class="message-content is-me not-img">
                                                                        ${msg.content}
                                                                </div>
                                                                <!---->
                                                                <!---->
                                                                <!---->
                                                                <!---->
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                                <div data-v-b2e907f6="" class="msg-push-new" talkerid="321173469" style="display: none;"><a
                                                        href="https://message.bilibili.com/" target="_blank" class="ar-recommend-item"><img
                                                        class="ar-recommend-item__img" data-src=""
                                                        src="data:image/gif;base64,R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7"
                                                        lazy="loading">
                                                    <div class="ar-recommend-item__info">
                                                        <div class="ar-recommend-item__info--title"></div>
                                                        <div class="ar-recommend-item__info--desc">
                                                            <!---->
                                                            <div class="time">1970-1-1</div>
                                                        </div>
                                                        <div class="ar-recommend-item__info--view"><i class="bp-icon-font icon-play-a"></i><span
                                                                class="view">0</span><i class="bp-icon-font icon-danmu-a"></i><span
                                                                class="chat">0</span></div>
                                                    </div>
                                                </a>
                                                </div>
                                                <!---->
                                                <!---->
                                            </div>
                                        </div>
                                        <%--                                            <%int new_msg_num = (Integer)request.getAttribute("new_msgs");%>--%>
                                        <%--                                            <%if(new_msg_num != 0) {%>--%>
                                        <%--                                                <div data-v-10e42db0="" class="new-message-tip" style=""> <!--style="display:none;"-->--%>
                                        <%--                                                    <div data-v-10e42db0="" class="text">您有 8 条新消息</div>--%>
                                        <%--                                                </div>--%>
                                        <%--                                            <%}%>--%>
                                        <div data-v-7762b962="" data-v-10e42db0="" class="send-box">
                                            <div data-v-7762b962="" class="row">
                                                <div data-v-7762b962="" class="space-margin"><label data-v-7762b962="" class="image-upload-btn"></label>
                                                </div>
                                                <div data-v-7762b962="" class="emoji-container"><button data-v-7762b962="" title="表情"
                                                                                                        class="emotion-btn-box"></button>
                                                </div>
                                            </div>
                                            <form name="message box" action="/EChat_Web_exploded/login.jhtml">
                                                <div data-v-715777d4="" data-v-7762b962="" placeholder="回复一下吧～" class="input-box">
                                                    <div data-v-715777d4="" id="editor" class="core-style" style="height: 60px;"><input type="text" name="send"/></div>
                                                </div>
                                                <div data-v-7762b962="" class="row right">
                                                    <button data-v-6cbfef24="" data-v-7762b962=""
                                                            class="btn-box send-btn active" title="enter 发送
                                                                            shift + enter 换行" name="send">发送</button>
                                                </div>
                                            </form>
                                        </div>
                                        <div data-v-7b5a0399="" data-v-10e42db0="">
                                            <div data-v-2d7a5bff="" data-v-7b5a0399="" class="im-popup confirm-popup" style="display: none;">
                                                <div data-v-3c863400="" data-v-2d7a5bff=""
                                                     class="bp-popup-panel p-relative a-move-in-top a-forwards im-popup-shell"
                                                     style="width: 500px; display: none;">
                                                    <div data-v-3c863400="" class="title-ctnr p-relative">
                                                        <h2 data-v-3c863400="" class="popup-title">黑名单</h2>
                                                    </div>
                                                    <div data-v-3c863400="" class="popup-content-ctnr">
                                                        <div data-v-2d7a5bff="" class="content">
                                                            <div data-v-7b5a0399="" class="content-text">加入黑名单后将不再收到该用户的新消息，并自动解除关注关系。</div>
                                                        </div>
                                                        <div data-v-3c863400="" class="popup-btn-ctnr t-center"><button data-v-65bda702=""
                                                                                                                        data-v-3c863400="" class="bl-button panel-btn bl-button--primary bl-button--size"><span
                                                                data-v-65bda702="" class="txt">确定</span></button><button data-v-65bda702=""
                                                                                                                         data-v-3c863400="" class="bl-button panel-btn bl-button--ghost bl-button--size"><span
                                                                data-v-65bda702="" class="txt">取消</span></button></div>
                                                    </div>
                                                    <div data-v-3c863400="" role="button" title="关闭面板"
                                                         class="close-btn p-absolute bg-center bg-no-repeat pointer t-center"><i data-v-3c863400=""
                                                                                                                                 class="bp-icon-font icon-close"></i></div>
                                                </div>
                                                <!---->
                                            </div>
                                        </div>


                                        <!---->
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</div>
</html>