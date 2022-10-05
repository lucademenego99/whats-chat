<%--
  Created by IntelliJ IDEA.
  User: lucademenego
  Date: 02/10/22
  Time: 11:35
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Room</title>
    <%@include file="common.jsp" %>
    <meta http-equiv="refresh" content="15">
</head>
<body>
<div class="flex h-screen w-screen items-center justify-center bg-gradient-to-br from-blue-400 via-blue-200 to-blue-400">
    <div class="flex h-[90vh] w-[80vw] flex-col items-start rounded-md bg-white drop-shadow-md lg:w-[40vw]">
        <%@include file="header.jsp" %>
        <jsp:useBean id="rooms" class="it.unitn.disi.webarch.lucademenego.assignment2.model.RoomsBean"
                     scope="application"/>
        <div class="flex w-full h-full min-h-0 flex-col justify-between overflow-auto gap-5 p-7">
            <div class="flex flex-col flex-initial gap-5">
                <form action="${pageContext.servletContext.contextPath}/user/add-message" method="post"
                      id="message-form" class="flex flex-col">
                    <label for="message-name" class="block text-sm font-medium text-gray-900">Send a message</label>
                    <input type="text" name="message" placeholder="Your message..." id="message-name"
                           class="mb-2 rounded-lg border border-gray-300 bg-gray-50 p-2 text-sm text-gray-900 outline-0 transition-all focus:border-blue-500 focus:ring-blue-500"/>

                    <input type="submit" value="Send"
                           class="w-full cursor-pointer rounded-md bg-blue-600 px-5 py-2.5 text-center text-sm font-medium text-white transition-all hover:bg-blue-700 focus:outline-none focus:ring-4 focus:ring-violet-300"/>
                </form>
                <div class="flex items-center">
                    <div class="flex-grow border-t border-gray-400"></div>
                    <span class="mx-4 flex-shrink text-gray-400"><%= rooms.getSelectedRoom().getName() %></span>
                    <div class="flex-grow border-t border-gray-400"></div>
                </div>
            </div>

            <%= rooms.getSelectedRoom().getMessages().toHTML(
                    "flex flex-col mr-2 rounded-bl-3xl rounded-tl-3xl rounded-tr-xl bg-blue-600 py-3 px-4 text-white",
                    "ml-2 rounded-br-3xl rounded-tr-3xl rounded-tl-xl bg-gray-300 py-3 px-4 text-gray-800",
                    users.getUser().getUsername())
            %>

            <div class="flex w-full flex-initial flex-col gap-1 md:flex-row md:gap-4">
                <form action="${pageContext.servletContext.contextPath}/user/home" method="get" id="exit-room"
                      class="md:flex-1">
                    <input type="submit" value="Exit the room"
                           class="w-full cursor-pointer rounded-md border-2 border-red-500 bg-transparent px-5 py-2.5 text-center text-sm font-medium text-red-500 transition-all hover:bg-red-100 focus:outline-none focus:ring-4 focus:ring-violet-300"/>
                </form>
                <form action="${pageContext.servletContext.contextPath}/user/room/<%=rooms.getSelectedRoom().getName()%>"
                      method="get" id="reload-room" class="md:flex-1">
                    <input type="submit" value="Reload"
                           class="w-full cursor-pointer rounded-md border-2 border-blue-500 bg-transparent px-5 py-2.5 text-center text-sm font-medium text-blue-500 transition-all hover:bg-blue-100 focus:outline-none focus:ring-4 focus:ring-violet-300"/>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
